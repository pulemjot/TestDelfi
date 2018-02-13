import org.openqa.selenium.By;

public class MobileVersionDelfi
{
    public String HOME_PAGE = "http://m.delfi.lv/";
    public By newElement = By.cssSelector("[class=\"md-mosaic-title\"]");
    public By titleOfNew = By.cssSelector("[class=\"md-scrollpos\"]");
    public By countOfComments = By.cssSelector("[class=\"commentCount\"]");
    public static By titleOfNewOpened = By.cssSelector("#article h1");
    public static By commentsOfNewOpened = By.cssSelector("#article [class=\"article-title\"] [class=\"commentCount\"]");
}
