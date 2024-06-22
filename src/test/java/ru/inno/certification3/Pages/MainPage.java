package ru.inno.certification3.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    @Step("Проверить, что таблица пустая")
    public void checkEmptyLines() {
        $(".rt-noData").shouldHave(text("No rows found"));
    }

    @Step("Сделать таблицу на 10 строк")
    public void selectTenRows() {
        $(byAttribute("aria-label", "rows per page")).$(byValue("20")).click();
    }

    @Step("Посчитать кол-во книг в таблице")
    public int countOfBooksInTable() {
        ElementsCollection rows = $$("div.rt-tr-group");
        int booksCount = 0;
        for (SelenideElement row : rows) {
            if (!row.$$("div.rt-td img").isEmpty()) {
                booksCount++;
            }
        }
        return booksCount;
    }

    @Step("Удалить все книги из таблицы")
    public void deleteAllBooks() throws InterruptedException {
        //$(By.xpath("//button[text()='Delete All Books']"));
        $(".di > button").click();
        $("#closeSmallModal-ok").click();
        confirm();
    }
}
