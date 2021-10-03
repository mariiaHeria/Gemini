package gemini.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Brow_Util {

    public static WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);

    public static void waitUntilVis(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilClick(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void clickOn(WebElement element){
        try {
           element.click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();
            executor.executeScript("arguments[0].click();", element);
        }
    }

}

