package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class Example {
    private WebDriver driver;
    static String resourceFolder = "src/test/java/Software";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/Software/Windows/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }

    @Test
    public void testGooglePage(){
        WebElement searchbox = driver.findElement(By.name("q"));
        searchbox.clear();
        searchbox.sendKeys("Hola hola!!");
        searchbox.submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("Hola hola!! - Buscar con Google", driver.getTitle());

    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
