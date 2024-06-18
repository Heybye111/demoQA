package ru.inno.certification3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import demoQA.helper.configHelper;
import ru.inno.certification3.Pages.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestDemoQa {

    LoginPage loginPage = new LoginPage();

    @BeforeAll
    public static void setUp(){

    }


    @Test
    public void test() throws InterruptedException {
        String url = configHelper.getUrl();
        open(url);
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.enterLoginButton();
        Thread.sleep(5000);

    }
}

