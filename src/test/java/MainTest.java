import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import resources.helper.configHelper;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainTest {


    @Test
    public void test() {
        //Configuration.timeout = 10_000;
        String url = configHelper.getUrl();
        open(url);
        $("#userName").sendKeys("heybye");
        $("#password").sendKeys("GGwp12345678!");


    }
}

