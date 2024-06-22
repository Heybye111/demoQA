package ru.inno.certification3.Pages;

import demoQA.helper.configHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    String login = configHelper.getLogin();
    String password = configHelper.getPassword();

    public void enterPassword() {
        $("#password").sendKeys(password);
    }

    public void enterUsername() {
        $("#userName").sendKeys(login);
    }

    public void enterLoginButton() {
        $("#login").click();
    }

    public void auth() {
        enterUsername();
        enterPassword();
        enterLoginButton();
    }
}
