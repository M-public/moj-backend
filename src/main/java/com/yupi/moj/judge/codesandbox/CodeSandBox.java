package com.yupi.moj.judge.codesandbox;

import com.yupi.moj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.moj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @description: 代码沙箱接口定义
 * @throws:
 * @return:
 */
public interface CodeSandBox {

    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
