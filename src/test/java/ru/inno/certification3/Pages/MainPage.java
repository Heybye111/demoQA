package ru.inno.certification3.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public void checkEmptyLines() {
        $(".rt-noData").shouldHave(text("No rows found"));
    }

    public void selectTenRows() {
        $(byAttribute("aria-label", "rows per page")).$(byValue("20")).click();
    }

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

    public void deleteAllBooks() throws InterruptedException {
        Thread.sleep(5000);
        //$(By.xpath("//button[text()='Delete All Books']"));
        $(".di > button");
        Thread.sleep(10000);
        $("#closeSmallModal-ok").click();
        confirm();
    }
}
