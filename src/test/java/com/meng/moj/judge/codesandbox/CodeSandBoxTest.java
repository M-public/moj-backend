package com.meng.moj.judge.codesandbox;

import com.meng.moj.judge.codesandbox.impl.RemoteCodeSandBox;
import com.meng.moj.judge.codesandbox.model.ExecuteCodeRequest;
import com.meng.moj.judge.codesandbox.model.ExecuteCodeResponse;
import com.meng.moj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootTest
class CodeSandBoxTest {

    @Value("${codesandbox.type:thirdParty}")
    private String type;
    @Test
    void executeCode() {
        CodeSandBox codeSandBox = new RemoteCodeSandBox();
        String code = "int main() { } ";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();

        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);

        Assertions.assertNotNull(executeCodeResponse);
    }

    @Test
    void executeCodeByValue() {
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        String code = "int main() { } ";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();

        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);

//        Assertions.assertNotNull(executeCodeResponse);
    }

    @Test
    void executeCodeByProxy() {
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        codeSandBox = new CodeSandboxProxy(codeSandBox);
        String code = "int main() { } ";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();

        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);

//        Assertions.assertNotNull(executeCodeResponse);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String type = scanner.next();
            CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
            String code = "int main() { } ";
            String language = QuestionSubmitLanguageEnum.JAVA.getValue();
            List<String> inputList = Arrays.asList("1 2", "3 4");

            ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                    .code(code)
                    .language(language)
                    .inputList(inputList)
                    .build();

            ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);

//            Assertions.assertNotNull(executeCodeResponse);
        }
    }
}