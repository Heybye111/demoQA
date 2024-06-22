package demoQA.helper.Api;

import demoQA.helper.Pojo.AddListOfBooks;
import demoQA.helper.configHelper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    String url = configHelper.getUrl();

    @Step("Создать пользака")
    public String createUser() {
        String body = "{\"userName\": \"" + configHelper.getLogin() + "\",\"password\": \"" + configHelper.getPassword() + "\"}";
        String userId = given()
                .log().all()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(url + "/Account/v1/User")
                .then()
                .log().all()
                .statusCode(201)
                .extract().path("userID");
        return userId;
    }

    @Step("Удалить пользака")
    public void deleteUser(String userId) {
        given()
                .auth()
                .preemptive()
                .basic(configHelper.getLogin(), configHelper.getPassword())
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .delete(url + "/Account/v1/User/" + userId)
                .then()
                .log().all()
                .statusCode(204);
    }

    @Step("Добавить книги в таблицу пользаку")
    public void addBook(AddListOfBooks addListOfBooks) {
        given().log().all()
                .auth()
                .preemptive()
                .basic(configHelper.getLogin(), configHelper.getPassword())
                .contentType(ContentType.JSON)
                .body(addListOfBooks)
                .when()
                .post(url + "/BookStore/v1/Books")
                .then()
                .log().all()
                .statusCode(201);

    }
}
