package gemini.pages;

import com.github.javafaker.Faker;
import gemini.utils.Brow_Util;
import gemini.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class Login_in_to_Gemini_page extends Client_Registration_page {
    WebDriver driver = Driver.getDriver();
   final Logger LOGGER = Logger.getLogger(Login_in_to_Gemini_page.class.getName());

    public Login_in_to_Gemini_page() {
        PageFactory.initElements(driver, this);
    }

    public static final String EMAIL_INPUT_BOX = "Email input box";
    public static final String PASSWORD_INPUT_BOX = "Password input box";
    public static final String REMEMBER_EMAIL_CHECK_BOX = "Email remember check box";
    public static final String SUBMIT_BUTTON = "Submit button";
    public static final String CREATE_NEW_ACCOUNT_BUTTON = "Create new account button";
    public static final String CREATE_NEW_BUSINESS_ACCOUNT_BUTTON = "Create new business account button";
    public static final String SUCCESS_MESSAGE= "Success message";

    @FindBy(css = "input[name='email']")
    protected WebElement emailInputBox;

    @FindBy(css = "input[name='password']")
    protected WebElement passwordInputBox;

    @FindBy(css = "input[name='rememberEmail']")
    protected WebElement rememberEmailCheckBox;

    @FindBy(css = "button[type='submit']")
    protected WebElement submitButton;

    @FindBy(xpath = "//a[@data-testid='goToRegister']")
    protected WebElement createNewAccountButton;

    @FindBy(xpath = "//a[@data-testid='register-go-to-institution-register']")
    protected WebElement createNewBusinessAccountButton;

    @FindBy(xpath = "//a[@data-testid='cookiePolicyAgreement-close']")
    protected WebElement cookies;

    @FindBy(xpath = "//h3")
    protected WebElement success_message;

    public WebElement getElementLoginPage(String clickable){
        switch (clickable){
            case EMAIL_INPUT_BOX:
                return emailInputBox;
            case PASSWORD_INPUT_BOX:
                return passwordInputBox;
            case REMEMBER_EMAIL_CHECK_BOX:
                return rememberEmailCheckBox;
            case SUBMIT_BUTTON:
                return submitButton;
            case CREATE_NEW_ACCOUNT_BUTTON:
                return createNewAccountButton;
            case CREATE_NEW_BUSINESS_ACCOUNT_BUTTON:
               return createNewBusinessAccountButton;
            case SUCCESS_MESSAGE:
                return success_message;
            default:
                LOGGER.info("Login page --> getElement() --> wrong input --> "+clickable);
                throw new NoSuchElementException(clickable);
        }
    }


    public void getToClientRegistrationPage() {
        getElementLoginPage(CREATE_NEW_ACCOUNT_BUTTON).click();
        acceptCookies();
        Brow_Util.clickOn(getElementLoginPage(CREATE_NEW_BUSINESS_ACCOUNT_BUTTON));
    }


    public void acceptCookies(){
        try{
            cookies.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
