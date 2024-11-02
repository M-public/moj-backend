package com.meng.moj.judge;

import com.meng.moj.judge.strategy.DefaultJudgeStrategy;
import com.meng.moj.judge.strategy.JavaLanguageJudgeStratedy;
import com.meng.moj.judge.strategy.JudgeContext;
import com.meng.moj.judge.strategy.JudgeStrategy;
import com.meng.moj.model.dto.questionsubmit.JudgeInfo;
import com.meng.moj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * @DESCRIPTION: 判题策略管理 简化调用，解决因判断使用哪种策略逻辑复杂引起的调用代码臃肿问题
 * @AUTHOR: MENGLINGQI
 * @TIME: 2024/10/27 14:31
 **/
@Service
public class JudgeManager {

    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();

        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();

        if ("java".equals(language))
            judgeStrategy = new JavaLanguageJudgeStratedy();

        return judgeStrategy.doJudge(judgeContext);
    }

}
