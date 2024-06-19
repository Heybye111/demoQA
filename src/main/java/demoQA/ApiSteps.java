package demoQA;

import demoQA.POJO.AddListOfBooks;
import demoQA.helper.configHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    String url = configHelper.getUrl();

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

    public void addBook(AddListOfBooks addListOfBooks) {
//        String body = "{\n" +
//                "  \"userId\": \"" + userId + "\",\n" +
//                "  \"collectionOfIsbns\": [\n" +
//                "    {\n" +
//                "      \"isbn\": \"" + isbn + "\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";

//        AddListOfBooks addListOfBooks = new AddListOfBooks(userId, ListOfBooks);

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

    static public List<Map<String, String>> createListOfBooks(List<String> isbns) {
        List<Map<String, String>> ListOfBooks = new ArrayList<>();
        for (String isbn : isbns) {
            ListOfBooks.add(Map.of("isbn", isbn));
        }
        return ListOfBooks;
    }

}
