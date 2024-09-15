package org.semicorp.mscseleniumtests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MscSeleniumTestsApplicationTests {

    private String applicationUrl = "http://msc-proj/";

    @Test
    void test_missingModuleSelection_shouldDisplayErrorMessage() {
        UITest.test_missingModuleSelection_shouldDisplayErrorMessage(applicationUrl);
    }

    @Test
    void test_insufficientTagCount_shouldDisplayErrorMessage() {
        UITest.test_insufficientTagCount_shouldDisplayErrorMessage(applicationUrl);
    }

}
