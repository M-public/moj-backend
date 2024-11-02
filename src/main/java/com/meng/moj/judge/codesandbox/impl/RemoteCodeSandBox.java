package com.meng.moj.judge.codesandbox.impl;

import com.meng.moj.judge.codesandbox.CodeSandBox;
import com.meng.moj.judge.codesandbox.model.ExecuteCodeRequest;
import com.meng.moj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @DESCRIPTION: 远程代码沙箱（实际调用接口）
 * @AUTHOR: MENGLINGQI
 * @TIME: 2024/10/25 10:41
 **/
public class RemoteCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
