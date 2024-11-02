package com.yupi.moj.judge.codesandbox;

import com.yupi.moj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.yupi.moj.judge.codesandbox.impl.RemoteCodeSandBox;
import com.yupi.moj.judge.codesandbox.impl.ThirdPartyCodeSandBox;

/**
 * @DESCRIPTION: 代码沙箱工厂类（静态工厂模式）
 * @AUTHOR: MENGLINGQI
 * @TIME: 2024/10/25 11:53
 **/
public class CodeSandBoxFactory {

    /**
     * 注：返回一个接口，不是一个实例
     * @param type 沙箱类型
     */
    public static CodeSandBox newInstance(String type){
        switch(type){
            case "example":
                return new ExampleCodeSandBox();
            case "remote":
                return new RemoteCodeSandBox();
            case "thirdParty":
                return new ThirdPartyCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}
