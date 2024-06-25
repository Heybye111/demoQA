package ru.inno.certification3;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import demoQA.helper.Api.ApiSteps;
import demoQA.helper.Pojo.AddListOfBooks;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import demoQA.helper.configHelper;
import ru.inno.certification3.Pages.LoginPage;
import ru.inno.certification3.Pages.MainPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты на DemoQa")
public class TestDemoQa {

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
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        apiSteps.deleteUser(userId);
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Проверка пустой таблицы")
    @Description("Сценарий 1. Проверка, что при создании нового пользователя, у него будет пустая таблица с книгами")
    @Tag("positive")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Heybye")
    public void checkEmptyTable() {
        loginPage.auth();
        mainPage.checkEmptyLines();
    }

    @Test
    @DisplayName("Проверка книг в таблице")
    @Description("Сценарий 2. Проверка, что добавленные книги отображаются в таблице пользователя")
    @Tag("positive")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Heybye")
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
    @DisplayName("Проверка удаления книг из таблицы")
    @Description("Сценарий 3. Проверка, что можно удалить все книги из таблицы пользователя")
    @Tag("positive")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Heybye")
    public void checkAddAndDeleteBooks() throws IOException {
        String books = new String(Files.readAllBytes(Paths.get("src/test/resources/addTwoBooks.json")));
        books = books.replace("##userId##", userId);
        AddListOfBooks listOfBooksToAdd = mapper.readValue(books, AddListOfBooks.class);
        apiSteps.addBook(listOfBooksToAdd);
        loginPage.auth();
        mainPage.selectTenRows();
        int bookCount = mainPage.countOfBooksInTable();
        assertEquals(2, bookCount);
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
        mainPage.deleteAllBooks();
        int finalBookCount = mainPage.countOfBooksInTable();
        assertEquals(0, finalBookCount);
    }
}

