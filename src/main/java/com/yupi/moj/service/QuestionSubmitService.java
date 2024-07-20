package com.yupi.moj.service;

import com.yupi.moj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.moj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.moj.model.entity.User;

/**
* @author 12059
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-06-30 22:30:57
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);


}
