package ru.inno.certification3;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.databind.ObjectMapper;
import demoQA.ApiSteps;
import demoQA.POJO.AddListOfBooks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import demoQA.helper.configHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.certification3.Pages.LoginPage;
import ru.inno.certification3.Pages.MainPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static demoQA.ApiSteps.createListOfBooks;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestDemoQa {


    private static final Logger log = LoggerFactory.getLogger(TestDemoQa.class);
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    ApiSteps apiSteps = new ApiSteps();
    String userId;
    String url = configHelper.getUrl();
    ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setUp() {
        userId = apiSteps.createUser();

        open(url + "/login");

    }
//    @BeforeAll
//    public static void beforeAll() {
//        Configuration.startMaximized = true;
//
//    }

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
    public void checkBooksInTable() throws IOException {
        String books = new String(Files.readAllBytes(Paths.get("src/test/resources/addSixBooks.json")));
        books = books.replace("##userId##", userId);
        AddListOfBooks listOfBooksToAdd = mapper.readValue(books, AddListOfBooks.class);
        apiSteps.addBook(listOfBooksToAdd);
        loginPage.auth();
        mainPage.selectTenRows();
        int bookCount = mainPage.countOfBooksInTable();
        assertEquals(6, bookCount);
    }

    @Test
    public void checkAddAndDeleteBooks() throws IOException, InterruptedException {

        String books = new String(Files.readAllBytes(Paths.get("src/test/resources/addTwoBooks.json")));
        books = books.replace("##userId##", userId);
        AddListOfBooks listOfBooksToAdd = mapper.readValue(books, AddListOfBooks.class);
        apiSteps.addBook(listOfBooksToAdd);
        loginPage.auth();
        mainPage.selectTenRows();
        int bookCount = mainPage.countOfBooksInTable();
        assertEquals(2, bookCount);
        mainPage.deleteAllBooks();
        int finalBookCount = mainPage.countOfBooksInTable();
        assertEquals(0, finalBookCount);
    }

    @Test
    public void test1() throws InterruptedException, IOException {

        String str = new String(Files.readAllBytes(Paths.get("src/test/resources/addSixBooks.json")));
        str = str.replace("##userId##", userId);
        AddListOfBooks listOfBooksToAdd = mapper.readValue(str, AddListOfBooks.class);

        apiSteps.addBook(listOfBooksToAdd);
        loginPage.auth();
        Thread.sleep(2000);

    }
}

