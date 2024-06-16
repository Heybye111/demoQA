import org.junit.jupiter.api.Test;
import demoQA.helper.configHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainTest {


    @Test
    public void test() throws InterruptedException {
        //Configuration.timeout = 10_000;
        String url = configHelper.getUrl();
        open(url);
        $("#userName").sendKeys("Heybye");
        $("#password").sendKeys("GGwp12345678!");
        $("#login").click();
        Thread.sleep(5000);
        $(".rt-noData").shouldHave(text("No rows found"));
    }
}

