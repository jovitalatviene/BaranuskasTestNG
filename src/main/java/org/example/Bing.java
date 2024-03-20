package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Bing {
    private static WebDriver narsykle;

    public static void setup() {
        System.setProperty(
                "webdriver.chrome.driver",
                "drivers/chromedriver120.exe"
        );

        narsykle = new ChromeDriver();
        narsykle.get("https://www.bing.com");
    }
    public static void searchByKeyword(String keyword){
        WebElement paieskosLaukelis = narsykle.findElement(By.id("sb_form_q"));
        paieskosLaukelis.sendKeys(keyword);
        //paieskosLaukelis.sendKeys(Keys.ENTER);
        WebElement searchIcon = narsykle.findElement(By.id("search_icon"));
        //searchIcon.click();

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) narsykle;
        javascriptExecutor.executeScript("arguments[0].click()", searchIcon);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getResultInt() {
        WebElement result = narsykle.findElement(By.className("sb_count"));
        System.out.println(result.getText());

        String paieskosResultatai = result.getText()
                .replaceAll("[a-zA-Z]", "")
                .replaceAll("[ąčęėįšųūž]", "")
                .replaceAll("[ ,]", "");

        return Integer.parseInt(paieskosResultatai);
    }
    public static String compareResult(int resultInt) {
        System.out.println(resultInt);
        if (resultInt >= 50000) {
            return "Jaunimas dar neužmiršo Anykščių šilelio.";
        } else {
            return "Rašytojas nelabai populiarus internetinėse platybėse.";
        }
    }

    public static void main(String[] args) {
        System.out.println("Selenium+Maven+JUnit");

        setup();

        searchByKeyword("Baranauskas");

        int resultInt = getResultInt();
        String outCome = compareResult(resultInt);
        System.out.println(outCome);

        close();
    }
    public static void close() {
        narsykle.close();
    }
}