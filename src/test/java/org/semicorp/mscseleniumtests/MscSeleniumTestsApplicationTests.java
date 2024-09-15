package org.semicorp.mscseleniumtests;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MscSeleniumTestsApplicationTests {

    private String applicationUrl = "http://msc-proj/";

    @Test
    @Order(1)
    void test_missingModuleSelection_shouldDisplayErrorMessage() {
        UITest.test_missingModuleSelection_shouldDisplayErrorMessage(applicationUrl);
    }

    @Test
    @Order(2)
    void test_insufficientTagCount_shouldDisplayErrorMessage() {
        UITest.test_insufficientTagCount_shouldDisplayErrorMessage(applicationUrl);
    }

    @Test
    @Order(3)
    void test_shouldAddTestQuestion() {
        UITest.test_shouldAddQuestion(applicationUrl);
    }

    @Test
    @Order(4)
    void test_shouldReturnTrueIfQuestionExists_and_ShouldAddAnswer() {
        UITest.test_shouldReturnTrueIfQuestionExists_and_ShouldAddAnswer(applicationUrl);
    }

}
