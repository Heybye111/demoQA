package ru.inno.certification3.Pages;

import demoQA.helper.configHelper;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    String login = configHelper.getLogin();
    String password = configHelper.getPassword();

    @Step("Ввести пароль")
    public void enterPassword() {
        $("#password").sendKeys(password);
    }

    @Step("Ввести имя пользователя")
    public void enterUsername() {
        $("#userName").sendKeys(login);
    }

    @Step("Нажать кнопку авторизации")
    public void enterLoginButton() {
        $("#login").click();
    }

    public void auth() {
        enterUsername();
        enterPassword();
        enterLoginButton();
    }
}
