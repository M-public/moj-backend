package com.yupi.moj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.moj.annotation.AuthCheck;
import com.yupi.moj.common.BaseResponse;
import com.yupi.moj.common.ErrorCode;
import com.yupi.moj.common.ResultUtils;
import com.yupi.moj.constant.UserConstant;
import com.yupi.moj.exception.BusinessException;
import com.yupi.moj.model.dto.question.QuestionQueryRequest;
import com.yupi.moj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.moj.model.entity.Question;
import com.yupi.moj.model.entity.User;
import com.yupi.moj.service.QuestionSubmitService;
import com.yupi.moj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 提交记录id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }



    /**
     * 分页获取题目提交列表
     *
     * @param questionQueryRequest
     * @return
     */
//    @PostMapping("/list/page")
//    public BaseResponse<Page<Question>> listQuestionSubmitByPage(@RequestBody QuestionQueryRequest questionQueryRequest) {
//        long current = questionQueryRequest.getCurrent();
//        long size = questionQueryRequest.getPageSize();
//        Page<Question> questionPage = questionService.page(new Page<>(current, size),
//                questionService.getQueryWrapper(questionQueryRequest));
//        return ResultUtils.success(questionPage);
//    }

}
