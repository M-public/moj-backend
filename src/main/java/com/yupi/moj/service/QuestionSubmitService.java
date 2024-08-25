package com.yupi.moj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.moj.model.dto.question.QuestionQueryRequest;
import com.yupi.moj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.moj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.moj.model.entity.Question;
import com.yupi.moj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.moj.model.entity.User;
import com.yupi.moj.model.vo.QuestionSubmitVO;
import com.yupi.moj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author menglingqi
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

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目提交封装
     *
     * @param questionSubmit
     * @param request
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, HttpServletRequest request);

    /**
     * 分页获取题目提交封装
     *
     * @param questionSubmit
     * @param request
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmit, HttpServletRequest request);


}
