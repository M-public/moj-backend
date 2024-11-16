package com.meng.moj.judge.codesandbox.impl;

import com.meng.moj.judge.codesandbox.CodeSandBox;
import com.meng.moj.judge.codesandbox.model.ExecuteCodeRequest;
import com.meng.moj.judge.codesandbox.model.ExecuteCodeResponse;
import com.meng.moj.judge.codesandbox.model.JudgeInfo;
import com.meng.moj.model.enums.JudgeInfoMessageEnum;
import com.meng.moj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * @DESCRIPTION: 示例代码沙箱（仅为了跑通代码流程）
 * @AUTHOR: MENGLINGQI
 * @TIME: 2024/10/25 10:41
 **/
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());

        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);

        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
