import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainWindowDelfi
{
    public String HOME_PAGE = "http://www.delfi.lv/news/zinas/";
    public static By content = By.id("content");
    public static By news = By.id("column1");
    public static By headersOfNews = By.cssSelector("h3");
    public static By titleOfNew = By.cssSelector("a:first-child");
    public static By countOfComments = By.cssSelector("[class=\"comment-count\"]");
    public static By titleOfNewOpened = By.cssSelector("#article h1 span");
    public static By commentsOfNewOpened = By.cssSelector("#article [class=\"article-title\"] [class=\"comment-count\"]");
}
