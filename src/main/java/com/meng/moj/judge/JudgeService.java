package com.meng.moj.judge;

import com.meng.moj.model.entity.QuestionSubmit;

/**
 * @description: 判题服务
 * @throws:
 * @return:
 */
public interface JudgeService {

    /**
     * @description: 判题
     * @throws:
     * @return:
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
