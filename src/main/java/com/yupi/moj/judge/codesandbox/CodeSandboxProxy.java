package com.yupi.moj.judge.codesandbox;

import com.yupi.moj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.moj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.PrivateKey;

/**
 * @DESCRIPTION: 代码沙箱代理类，为了解决所有调用代码沙箱都需要打印日志的问题
 * @AUTHOR: MENGLINGQI
 * @TIME: 2024/10/27 10:06
 **/
@Slf4j
@AllArgsConstructor
public class CodeSandboxProxy implements CodeSandBox {


    private final CodeSandBox codeSandBox;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest){
        log.info("代码沙箱请求信息：" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        log.info("代码沙箱返回信息：" + executeCodeResponse);
        return executeCodeResponse;
    }


}
