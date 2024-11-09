package com.meng.moj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.meng.moj.model.dto.question.JudgeCase;
import com.meng.moj.model.dto.question.JudgeConfig;
import com.meng.moj.model.dto.questionsubmit.JudgeInfo;
import com.meng.moj.model.entity.Question;
import com.meng.moj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * @DESCRIPTION: java程序判题策略
 * @AUTHOR: MENGLINGQI
 * @TIME: 2024/10/27 14:20
 **/
public class JavaLanguageJudgeStratedy implements JudgeStrategy {

    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {

        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();

        // 实际执行消耗资源
        Long actualMemory = judgeInfo.getMemory();
        Long actualTime = judgeInfo.getTime();

        // 定义返回值
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemory(actualMemory);
        judgeInfoResponse.setTime(actualTime);

        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        // 判断输出用例数量是否等于输入用例数量
        if (outputList.size() != inputList.size()){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        // 依次判断每一个输出用例是否跟预期相同
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (judgeCase.getOutput().equals(outputList.get(i))){
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }
        // 判断题目限制条件 内存、执行时间等
        // 获取录入好的限制条件
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long exceptMemory = judgeConfig.getMemoryLimit();
        Long exceptTime = judgeConfig.getTimeLimit();

        if (actualMemory > exceptMemory){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }

        // 假如java程序需要额外增加10秒运行时间(编译耗时)
        long JAVA_PROGRAME_TIME_COST = 10000L;
        if ((actualTime - JAVA_PROGRAME_TIME_COST) > exceptTime){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }

        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}