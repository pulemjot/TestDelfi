import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainTest
{

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
    public void CheckNewsMainPage()
    {
        Logger log = Logger.getLogger(MainTest.class.getName());
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");
        WebDriver driverWeb = new FirefoxDriver();
        //driverWeb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverWeb.get(PageDelfi.HOME_PAGE);
        Title = driverWeb.getTitle();
        Assert.assertEquals("Actual title not equal expected title", Title, "Ziņas - DELFI");
        WebElement content = driverWeb.findElement(PageDelfi.content);
        WebElement news = content.findElement(PageDelfi.news);
        Assert.assertTrue("News are not visible", news.isDisplayed());
        List<WebElement> headersNewsWeb = news.findElements(PageDelfi.headersOfNews);
        Assert.assertNotNull("Not find headers", headersNewsWeb);

        WebDriver driverMob = new FirefoxDriver();
        //driverMob.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverMob.get(MobDelfi.HOME_PAGE);
        List<WebElement> headersNewsMob = driverMob.findElements(MobDelfi.newElement);
        Assert.assertNotNull("Not find headers", headersNewsMob);

        List<NewDelfi> DelfiWeb = new ArrayList<NewDelfi>();
        List<NewDelfi> DelfiMob = new ArrayList<NewDelfi>();
        String webTitle = "";
        String mobTitle= "";
        String webHrefNew ="";
        String mobHrefNew ="";
        String webHrefComments = "";
        String mobHrefComment = "";
        String webCount= "";
        String mobCount= "";
        for(int i=0; i<5;i++)
        {            
            try
            {
                webTitle = headersNewsWeb.get(i).findElement(PageDelfi.titleOfNew).getText();
                mobTitle = headersNewsMob.get(i).findElement(MobDelfi.titleOfNew).getText();
                webHrefNew =headersNewsWeb.get(i).findElement(PageDelfi.titleOfNew).getAttribute("href");
                mobHrefNew =headersNewsMob.get(i).findElement(MobDelfi.titleOfNew).getAttribute("href");
                webCount = headersNewsWeb.get(i).findElement(PageDelfi.countOfComments).getText();
                mobCount = headersNewsMob.get(i).findElement(MobDelfi.countOfComments).getText();
                webHrefComments = headersNewsWeb.get(i).findElement(PageDelfi.countOfComments).getAttribute("href");
                mobHrefComment = headersNewsMob.get(i).findElement(MobDelfi.countOfComments).getAttribute("href");
            }
            catch (Exception ex)
            {
                log.log(Level.WARNING, webTitle+" Exception: ", ex);
            }

            NewDelfi MainPageWeb = new NewDelfi(webTitle, webCount,webHrefNew,webHrefComments);
            NewDelfi MainPageMob = new NewDelfi(mobTitle,mobCount,mobHrefNew,mobHrefComment);
            DelfiWeb.add(MainPageWeb);
            DelfiMob.add(MainPageMob);
            Assert.assertEquals("Titles not equals",MainPageWeb.Title,MainPageMob.Title);
            try
            {
                Assert.assertEquals("Comments not equals "+MainPageWeb.Title,MainPageWeb.CountOfComments,MainPageMob.CountOfComments);
            }
            catch (AssertionError ex)
            {
                log.log(Level.WARNING, "Exception: ", ex);
            }
        }

        for(int i=0; i<5;i++)
        {
            try
            {
                driverWeb.get(DelfiWeb.get(i).HrefNew);
                driverMob.get(DelfiMob.get(i).HrefNew);
                try {
                    String titleWeb = driverWeb.findElement(MainWindowDelfi.titleOfNewOpened).getText();
                    String titleMob = driverMob.findElement(MobileVersionDelfi.titleOfNewOpened).getText();
                    String commentWeb = driverWeb.findElement(MainWindowDelfi.commentsOfNewOpened).getText();
                    String commentMob = driverMob.findElement(MobileVersionDelfi.commentsOfNewOpened).getText();
                    try
                    {
                        Assert.assertEquals(DelfiWeb.get(i).Title, titleWeb);
                        Assert.assertEquals(titleWeb, titleMob);
                        Assert.assertEquals(commentMob, commentWeb);
                    }
                    catch (AssertionError ex)
                    {
                        log.log(Level.WARNING, DelfiWeb.get(i).Title+" Exception: ", ex);
                    }
                } catch (Exception ex) {
                    log.log(Level.WARNING, DelfiWeb.get(i).Title+" Exception: ", ex);
                }
            }
            catch (Exception ex) {
                log.log(Level.WARNING, DelfiWeb.get(i).Title+" Exception: ", ex);
            }
        }
        driverMob.close();
        driverWeb.close();
    }

}
