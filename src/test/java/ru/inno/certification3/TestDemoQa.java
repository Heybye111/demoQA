package ru.inno.certification3;

import demoQA.ApiSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import demoQA.helper.configHelper;
import ru.inno.certification3.Pages.LoginPage;
import ru.inno.certification3.Pages.MainPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDemoQa {

    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    ApiSteps apiSteps = new ApiSteps();


    @BeforeAll
    public static void setUp(){

    }


    @BeforeEach
    public void auth(){
        String userId = apiSteps.createUser();
        String url = configHelper.getUrl();
        open(url + "/login");
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.enterLoginButton();
    }
    @AfterEach
    public void tearDown(){
        apiSteps.deleteUser(userId);
    }


    @Test
    public void checkEmptyTable()  {
        mainPage.checkEmptyLines();
    }

    @Test
    public void checkBooksInTable() {
        //добавить 6 книг
        mainPage.selectTenRows();
        int bookCount = mainPage.checkBooksInEveryRow();
        assertEquals(6, bookCount);
    }

    @Test
    public void checkAddAndDeleteBooks(){
        //Добавить 2 книги
        int bookCount = mainPage.checkBooksInEveryRow();
        assertEquals(2, bookCount);
        mainPage.deleteAllBooks();
        mainPage.acceptDeleteAllBooks();
        mainPage.acceptAlert();
        int finalBookCount = mainPage.checkBooksInEveryRow();
        assertEquals(0, bookCount);


    }
}

