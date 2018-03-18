package Lesson4;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest
{
    public static By titleOfNew = By.cssSelector("[class=\"top2012-big\"]");
    public static By titleText = By.cssSelector("[class=\"top2012-title\"]");
    public static By countComment = By.cssSelector("[class=\"comment-count\"]");
    private static final String HOME_PAGE = "http://www.delfi.lv/news/zinas/";

    @Test
    public void firstFiveArticlesTest()
    {
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");
        WebDriver driverWeb = new FirefoxDriver();
        driverWeb.manage().window().maximize();
        driverWeb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driverWeb.get(HOME_PAGE);
        List<WebElement> headersNewsWeb = driverWeb.findElements(titleOfNew);
        List<Article> articlesFive = new ArrayList<Article>();

        for(int i=0; i<5;i++)
        {
            Article article = new Article();
            WebElement elem = headersNewsWeb.get(i);

            article.setTitle(elem.findElement(titleText).getText());
            if (elem.findElements(countComment).isEmpty()){
                article.setCommentCount(0);
            }else{
                article.setCommentCount(elem.findElement(countComment).getText());
            }
            article.setLink(elem.findElement(titleText).getAttribute("href"));
            articlesFive.add(article);
        }

    }


}
