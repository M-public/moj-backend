package com.yupi.moj.judge.strategy;

import com.yupi.moj.model.dto.questionsubmit.JudgeInfo;

/**
 * @DESCRIPTION: 判题策略（策略模式），为了解决实际情况下判题策略不统一或发生变更的情况
 * @AUTHOR: MENGLINGQI
 * @TIME: 2024/10/27 11:53
 **/
public interface JudgeStrategy {

    /**
     * @description: 执行判题
     * * @param[1] judgeContext
     * @throws:
     * @return: JudgeInfo
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}