package org.semicorp.mscseleniumtests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Chrome driver used in testing (15 Sept 2024)
//https://chromedriver.storage.googleapis.com/index.html?path=128.0.6613.138/.

public class UITest {

    private int randomNumber;

    public static void test_missingModuleSelection_shouldDisplayErrorMessage(String applicationUrl) {
        System.setProperty("webdriver.chrome.driver", "/Users/sever/chromedriver");

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        // User Login
        driver.get(applicationUrl + "/index.php?c=login");
        WebElement textEdit_username = driver.findElement(By.id("editUsername"));
        textEdit_username.sendKeys("test_user");
        WebElement textEdit_password = driver.findElement(By.id("editPassword"));
        textEdit_password.sendKeys("password");
        WebElement loginButton = driver.findElement(By.id("btnLogins"));
        loginButton.click();

        // Select button with the text 'Ask Question'
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement askQuestionButtonElement = driver.findElement(By.id("btn-ask-question-primary"));
        WebElement button_AskQuestion = wait.until(ExpectedConditions.elementToBeClickable(askQuestionButtonElement));
        if(button_AskQuestion.isDisplayed()) {
            button_AskQuestion.click();
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        WebElement button_SubmitAddQuestionFormElement = driver.findElement(By.id("btn-submit-question"));

        // Scroll into view and click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button_SubmitAddQuestionFormElement);
        // Use JavaScript to click [used this one because selenium click did not work for some reason]
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button_SubmitAddQuestionFormElement);

        // Wait for the error message to be visible
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("form_question_module-error")));

        // Check if the error message is displayed
        if (errorMessage.isDisplayed()) {
            System.out.println("Error message is displayed: " + errorMessage.getText());
        } else {
            System.out.println("Error message is not displayed.");
        }

        driver.quit();
    }

    public static void test_insufficientTagCount_shouldDisplayErrorMessage(String applicationUrl) {
        System.setProperty("webdriver.chrome.driver", "/Users/sever/chromedriver");

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        // User Login
        driver.get(applicationUrl + "/index.php?c=login");
        WebElement textEdit_username = driver.findElement(By.id("editUsername"));
        textEdit_username.sendKeys("test_user");
        WebElement textEdit_password = driver.findElement(By.id("editPassword"));
        textEdit_password.sendKeys("password");
        WebElement loginButton = driver.findElement(By.id("btnLogins"));
        loginButton.click();


        // Select button with the text 'Ask Question'
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement askQuestionButtonElement = driver.findElement(By.id("btn-ask-question-primary"));
        WebElement button_AskQuestion = wait.until(ExpectedConditions.elementToBeClickable(askQuestionButtonElement));
        if(button_AskQuestion.isDisplayed()) {
            button_AskQuestion.click();
        }

        // Fill in the form fields

        // Fill in the Add Question form
        WebElement textEdit_QuestionTitle = driver.findElement(By.id("form_question_title"));
        textEdit_QuestionTitle.clear();
        textEdit_QuestionTitle.sendKeys("Selenium Test Question - How to create REST API with Python and Flask");

        // Question body
        WebElement textArea_QuestionBody = driver.findElement(By.id("form_question_problem"));
        textArea_QuestionBody.clear();
        textArea_QuestionBody.sendKeys("REST API enables communication between systems using standard HTTP methods for CRUD operations.");

        // Module name
        WebElement textEdit_ModuleName = driver.findElement(By.id("form_question_module"));
        textEdit_ModuleName.clear();
        textEdit_ModuleName.sendKeys("Advances in Data Management");

        // Tags [Add just 1 tag while 2 is the minimum]
        WebElement textEdit_Tags = driver.findElement(By.id("form_question_tags_input"));
        textEdit_Tags.clear();
        textEdit_Tags.sendKeys("rest");
        textEdit_Tags.sendKeys(Keys.SPACE);


        // Submitting question
        WebElement button_SubmitAddQuestionFormElement = driver.findElement(By.id("btn-submit-question"));

        // Scroll into view and click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button_SubmitAddQuestionFormElement);
        // Use JavaScript to click [this actually works better than selenium click]
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button_SubmitAddQuestionFormElement);

        // Wait for the form error message to be present and visible
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#form-errors .alert.alert-danger")));

        // Check if the error message contains the expected text
        String expectedErrorMessage = "Min 2 tags required";
        if (errorMessageElement.isDisplayed() && errorMessageElement.getText().contains(expectedErrorMessage)) {
            System.out.println("Error message is displayed: " + errorMessageElement.getText());
        } else {
            System.out.println("Error message is not displayed or doesn't contain the expected text.");
            return;
        }

        driver.quit();
    }

    public static void test_shouldAddQuestion(String applicationUrl) {
        System.setProperty("webdriver.chrome.driver", "/Users/sever/chromedriver");

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        // User Login
        driver.get(applicationUrl + "/index.php?c=login");
        WebElement textEdit_username = driver.findElement(By.id("editUsername"));
        textEdit_username.sendKeys("test_user");
        WebElement textEdit_password = driver.findElement(By.id("editPassword"));
        textEdit_password.sendKeys("password");
        WebElement loginButton = driver.findElement(By.id("btnLogins"));
        loginButton.click();


        // Select button with the text 'Ask Question'
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement askQuestionButtonElement = driver.findElement(By.id("btn-ask-question-primary"));
        WebElement button_AskQuestion = wait.until(ExpectedConditions.elementToBeClickable(askQuestionButtonElement));
        if(button_AskQuestion.isDisplayed()) {
            button_AskQuestion.click();
        }



        // Fill in the form fields
        // Fill in the Add Question form
        WebElement textEdit_QuestionTitle = driver.findElement(By.id("form_question_title"));
        textEdit_QuestionTitle.clear();
        textEdit_QuestionTitle.sendKeys("Selenium Test Question - How to create REST API with Python and Flask");

        // Question body
        WebElement textArea_QuestionBody = driver.findElement(By.id("form_question_problem"));
        textArea_QuestionBody.clear();
        textArea_QuestionBody.sendKeys("REST API enables communication between systems using standard HTTP methods for CRUD operations.");

        // Module name
        WebElement textEdit_ModuleName = driver.findElement(By.id("form_question_module"));
        textEdit_ModuleName.clear();
        textEdit_ModuleName.sendKeys("Advances in Data Management");

        // Tags [Add just 1 tag while 2 is the minimum]
        WebElement textEdit_Tags = driver.findElement(By.id("form_question_tags_input"));
        textEdit_Tags.clear();
        textEdit_Tags.sendKeys("rest");
        textEdit_Tags.sendKeys(Keys.SPACE);
        textEdit_Tags.sendKeys("API");
        textEdit_Tags.sendKeys(Keys.SPACE);


        // Submitting question
        WebElement button_SubmitAddQuestionFormElement = driver.findElement(By.id("btn-submit-question"));

        // Scroll into view and click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button_SubmitAddQuestionFormElement);
        // Use JavaScript to click [this actually works better than selenium click]
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button_SubmitAddQuestionFormElement);


        // Check if confirmation message was shown.
        // Wait for the alert to appear (using class or another locator)
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Wait until the alert is visible
        WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));

        // Verify that the alert contains the expected text
        String expectedText = "Question added successfully. The status is: PENDING";
        if (alertElement.isDisplayed() && alertElement.getText().contains(expectedText)) {
            System.out.println("Success: The alert with the correct message is shown.");
        } else {
            System.out.println("Alert is not displayed or the text is incorrect.");
        }

        driver.quit();
    }

    public static void test_shouldReturnTrueIfQuestionExists_and_ShouldAddAnswer(String applicationUrl) {
        System.setProperty("webdriver.chrome.driver", "/Users/sever/chromedriver");

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        // User Login
        driver.get(applicationUrl + "/index.php?c=login");
        WebElement textEdit_username = driver.findElement(By.id("editUsername"));
        textEdit_username.sendKeys("test_user");
        WebElement textEdit_password = driver.findElement(By.id("editPassword"));
        textEdit_password.sendKeys("password");
        WebElement loginButton = driver.findElement(By.id("btnLogins"));
        loginButton.click();


        // Go to questions list page
        driver.get(applicationUrl + "/index.php?c=user&v=questions");

        // Check if the question exists in the list of questions
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Selenium Test Question - How to create REST API")));

        if (link.isDisplayed()) {
            System.out.println("The link is present: " + link.getText());
        } else {
            System.out.println("The link is not present.");
        }
        link.click(); // Go to question details page

        // Click add answer button
        WebElement button_AddAnswer = driver.findElement(By.id("add-answer-button"));
        button_AddAnswer.click();

        WebElement textarea_AnswerBody = driver.findElement(By.id("form_answer_field"));
        textarea_AnswerBody.clear();

        // Draw random number to add to text to differentiate text
        int min = 1;
        int max = 10000;
        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
        textarea_AnswerBody.sendKeys("#" + random_int + " - Sed vel nibh malesuada, rhoncus eros quis, ornare justo. Proin ut sem nunc. Etiam quis porta neque. Duis sed augue non nisl molestie scelerisque at eget metus. Sed tempor dui sit amet risus consequat");

        WebElement button_SubmitAnswer = driver.findElement(By.id("btn-submit-question"));
        // Scroll into view and click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button_SubmitAnswer);
        // Use JavaScript to click [this actually works better than selenium click]
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button_SubmitAnswer);

        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        boolean isTextPresent = wait.until(ExpectedConditions.textToBePresentInElement(button_AddAnswer, "Answer submitted with status PENDING."));

        // Check if the text "PENDING" is displayed
        if (isTextPresent) {
            System.out.println("The button text is now 'PENDING'. The answer was added");
        } else {
            System.out.println("The button text did not change to 'PENDING' within 4 seconds.");
        }

        driver.quit();
    }

}
