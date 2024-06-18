package ru.inno.certification3.Pages;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public void enterPassword(){
        $("#password").sendKeys("GGwp12345678!");
    }

    public void enterUsername(){
        $("#userName").sendKeys("Heybye");
    }

    public void enterLoginButton(){
        $("#login").click();
    }


}
