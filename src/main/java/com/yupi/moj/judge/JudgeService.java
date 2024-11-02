package com.yupi.moj.judge;

import com.yupi.moj.model.entity.QuestionSubmit;
import com.yupi.moj.model.vo.QuestionSubmitVO;

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
