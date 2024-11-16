package com.meng.moj.judge.strategy;

import com.meng.moj.model.dto.question.JudgeCase;
import com.meng.moj.judge.codesandbox.model.JudgeInfo;
import com.meng.moj.model.entity.Question;
import com.meng.moj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @DESCRIPTION: 判题上下文 用于定义在判题策略中传递的上下文信息  不确定要传入参数时可采用
 * @AUTHOR: MENGLINGQI
 * @TIME: 2024/10/27 11:55
 **/
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;


}
