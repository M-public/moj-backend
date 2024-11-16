package com.meng.moj.judge;

import cn.hutool.json.JSONUtil;
import com.meng.moj.common.ErrorCode;
import com.meng.moj.exception.BusinessException;
import com.meng.moj.judge.codesandbox.CodeSandBox;
import com.meng.moj.judge.codesandbox.CodeSandBoxFactory;
import com.meng.moj.judge.codesandbox.CodeSandboxProxy;
import com.meng.moj.judge.codesandbox.model.ExecuteCodeRequest;
import com.meng.moj.judge.codesandbox.model.ExecuteCodeResponse;
import com.meng.moj.judge.strategy.JudgeContext;
import com.meng.moj.model.dto.question.JudgeCase;
import com.meng.moj.judge.codesandbox.model.JudgeInfo;
import com.meng.moj.model.entity.Question;
import com.meng.moj.model.entity.QuestionSubmit;
import com.meng.moj.model.enums.QuestionSubmitStatusEnum;
import com.meng.moj.service.QuestionService;
import com.meng.moj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @DESCRIPTION: description
 * @AUTHOR: MENGLINGQI
 * @TIME: 2024/10/27 10:41
 **/
@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:thirdParty}")
    private String type;

    // 引入对应的服务，为了进行后续查询相关信息。
    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;


    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {

        // 1）传入题目的提交 id 获取到题目对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();

        Question question = questionService.getById(questionId);
        if (question == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目信息不存在");
        }

        // 2）如果不是等待状态则不判题，为了解决重复判题造成的资源浪费情况
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目正在判题中");
        }
        // 3）更新题目提交信息状态
        Long id = questionSubmit.getId();
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "判题开始题目状态更新失败");
        }

        // 4）调用沙箱，获取到执行结果
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        codeSandBox = new CodeSandboxProxy(codeSandBox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 获取题目的输入用例（提前录入好的）
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();

        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();

        // 5）根据沙箱的执行结果 设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestionSubmit(questionSubmit);

        // 调用判题策略管理器决定使用哪种判题策略
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);

        // 判题结束 再次修改判题状态
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "判题结束题目状态更新失败");
        }

        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionId);

        return questionSubmitResult;
    }
}
