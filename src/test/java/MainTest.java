import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainTest {

    public MainWindowDelfi PageDelfi = new MainWindowDelfi();
    public MobileVersionDelfi MobDelfi = new MobileVersionDelfi();
    public String Title;

    @Test
    public void CheckTitle()
    {
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(PageDelfi.HOME_PAGE);
        Title = driver.getTitle();
        Assert.assertEquals("Actual title not equal expected title", Title, "Ziņas - DELFI");
        driver.close();
    }
    @Test
    public void CheckNews()
    {
        Logger log = Logger.getLogger(MainTest.class.getName());
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");
        WebDriver driverWeb = new FirefoxDriver();
        driverWeb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverWeb.get(PageDelfi.HOME_PAGE);
        Title = driverWeb.getTitle();
        Assert.assertEquals("Actual title not equal expected title", Title, "Ziņas - DELFI");
        WebElement content = driverWeb.findElement(PageDelfi.content);
        WebElement news = content.findElement(PageDelfi.news);
        Assert.assertTrue("News are not visible", news.isDisplayed());
        List<WebElement> headersNewsWeb = news.findElements(PageDelfi.headersOfNews);
        Assert.assertNotNull("Not find headers", headersNewsWeb);

        WebDriver driverMob = new FirefoxDriver();
        driverMob.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverMob.get(MobDelfi.HOME_PAGE);
        List<WebElement> headersNewsMob = driverMob.findElements(MobDelfi.newElement);
        Assert.assertNotNull("Not find headers", headersNewsMob);

        for(int i=0; i<5;i++)
        {
            try
            {
                String webTitle = headersNewsWeb.get(i).findElement(PageDelfi.titleOfNew).getText();
                String mobTitle = headersNewsMob.get(i).findElement(MobDelfi.titleOfNew).getText();
                Assert.assertEquals(webTitle,mobTitle);
            }
            catch (Exception ex)
            {
                log.log(Level.WARNING, "Exception: ", ex);
            }
            try
            {
                String webCount = headersNewsWeb.get(i).findElement(PageDelfi.countOfComments).getText();
                String mobCount = headersNewsMob.get(i).findElement(MobDelfi.countOfComments).getText();
                Assert.assertEquals(webCount,mobCount);
            }
            catch (Exception ex)
            {
                log.log(Level.WARNING, "Exception: ", ex);
            }
        }
        driverMob.close();
        driverWeb.close();
    }
}
