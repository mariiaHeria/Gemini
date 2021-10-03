package gemini.pages;

import com.github.javafaker.Faker;
import gemini.utils.Brow_Util;
import gemini.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Client_Registration_page {
    WebDriver driver = Driver.getDriver();
    final Logger LOGGER = Logger.getLogger(Client_Registration_page.class.getName());

    public Client_Registration_page() {
        PageFactory.initElements(driver, this);
    }

    public static final String FIRST_NAME_INPUT = "First name";
    public static final String MIDDLE_NAME_INPUT = "Middle name";
    public static final String LAST_NAME_INPUT = "Last name";
    public static final String BUSINESS_NAME_INPUT = "Business name";
    public static final String EMAIL = "email";
    public static final String CONTINUE = "continue";
    public static final String ALERT = "alert";



    @FindBy(how = How.NAME, using = "company.legalName")
    protected WebElement legal_business_name_input;

    @FindBy(how = How.NAME, using = "personal.legalName.firstName")
    protected WebElement legal_first_name_input;

    @FindBy(how = How.NAME, using = "personal.legalName.middleName")
    protected WebElement legal_middle_name_input;

    @FindBy(how = How.NAME, using = "personal.legalName.lastName")
    protected WebElement legal_last_name_input;

    @FindBy(how = How.NAME, using = "personal.email")
    protected WebElement email_input;

    @FindBy(xpath = "//body/div/div/div/div/div/div/form[@method='POST']/fieldset[@name='Company Information']/div/div/label[@data-testid='companyTypeDropdown-label']/div/div[1]/div[1]")
    protected WebElement company_type_dropbox;

    @FindBy(xpath = "//div[@class='usStateDropdown css-2b097c-container']")
    protected WebElement state_dropbox;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement continue_button;

    @FindBy(xpath = "//div[@class='AlertBody']")
    protected WebElement alert_body;


    public WebElement getElementClientPage(String clickable) {
        switch (clickable) {
            case BUSINESS_NAME_INPUT:
                return legal_business_name_input;
            case FIRST_NAME_INPUT:
                return legal_first_name_input;
            case MIDDLE_NAME_INPUT:
                return legal_middle_name_input;
            case LAST_NAME_INPUT:
                return legal_last_name_input;
            case EMAIL:
                return email_input;
            case CONTINUE:
                return continue_button;
            case ALERT:
                return alert_body;
            default:
                LOGGER.info("Client Registration --> getElement() --> wrong input --> " + clickable);
                throw new NoSuchElementException(clickable);
        }
    }

    public void creat_account_client_Registration_Positive() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        getElementClientPage(BUSINESS_NAME_INPUT).sendKeys(faker.name().title());
        getElementClientPage(FIRST_NAME_INPUT).sendKeys(firstName);
        getElementClientPage(MIDDLE_NAME_INPUT).sendKeys(faker.name().firstName());
        getElementClientPage(LAST_NAME_INPUT).sendKeys(lastName);

        getElementClientPage(EMAIL).sendKeys(firstName + "." + lastName + "@mail.com");
        company_type_dropbox.click();

        String word = "Money Services Business or Money Transmitter";
        WebElement companyType = driver.findElement(By.xpath("//div[*='"+word+"']"));
       companyType.click();

       state_dropbox.click();
        String state = "AK";
        WebElement chooseState = driver.findElement(By.xpath("//div[*='"+state+"']"));
        chooseState.click();

        Brow_Util.clickOn(getElementClientPage(CONTINUE));
        Driver.getDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }

    public void creat_account_client_Registration_Negative() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        getElementClientPage(FIRST_NAME_INPUT).sendKeys(firstName);
        getElementClientPage(MIDDLE_NAME_INPUT).sendKeys(faker.name().firstName());
        getElementClientPage(LAST_NAME_INPUT).sendKeys(lastName);

        getElementClientPage(EMAIL).sendKeys(firstName + "." + lastName + "@mail.com");
        company_type_dropbox.click();

        String word = "Money Services Business or Money Transmitter";
        WebElement companyType = driver.findElement(By.xpath("//div[*='"+word+"']"));
        companyType.click();

//        state_dropbox.click();
//        String state = "AK";
//        WebElement chooseState = driver.findElement(By.xpath("//div[*='"+state+"']"));
//        chooseState.click();

        Brow_Util.clickOn(getElementClientPage(CONTINUE));
        Driver.getDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }


}
