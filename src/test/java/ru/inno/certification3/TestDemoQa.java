package ru.inno.certification3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demoQA.ApiSteps;
import demoQA.POJO.AddListOfBooks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import demoQA.helper.configHelper;
import ru.inno.certification3.Pages.LoginPage;
import ru.inno.certification3.Pages.MainPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static demoQA.ApiSteps.createListOfBooks;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestDemoQa {

    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    ApiSteps apiSteps = new ApiSteps();
    String userId;
    String url = configHelper.getUrl();
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void auth() {
        userId = apiSteps.createUser();
        open(url + "/login");
    }

    @AfterEach
    public void tearDown() {
        apiSteps.deleteUser(userId);

    }

    @Test
    public void checkEmptyTable() {
        loginPage.auth();
        mainPage.checkEmptyLines();
    }

    @Test
    public void checkBooksInTable() {
        //добавить 6 книг
        loginPage.auth();
        mainPage.selectTenRows();
        int bookCount = mainPage.countOfBooksInTable();
        assertEquals(6, bookCount);
    }

    @Test
    public void checkAddAndDeleteBooks() {
        //Добавить 2 книги
        int bookCount = mainPage.countOfBooksInTable();
        assertEquals(2, bookCount);
        mainPage.deleteAllBooks();
        int finalBookCount = mainPage.countOfBooksInTable();
        assertEquals(0, bookCount);
    }

    @Test
    public void test1() throws InterruptedException, IOException {
        AddListOfBooks addListOfBooks = new AddListOfBooks(userId, createListOfBooks(List.of("9781449365035", "9781491904244")));


//        String str = new String(Files.readAllBytes(Paths.get("src/test/resources/addSixBooks.json")));
//        str = str.replace("##userId##", userId);
//        AddListOfBooks list = mapper.readValue(str, AddListOfBooks.class);


        apiSteps.addBook(addListOfBooks);
        loginPage.auth();
        Thread.sleep(2000);

    }
}

