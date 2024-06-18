package demoQA;

import demoQA.helper.configHelper;
import io.restassured.http.ContentType;
import org.apache.struts.config.ConfigHelper;

import static io.restassured.RestAssured.given;

public class ApiSteps {



    String url = configHelper.getUrl();

        public String createUser() {
            String body = "{\"userName\": \"" + configHelper.getLogin()+ "\",\"password\": \"" + configHelper.getPassword() + "\"}";
            String userId = given()
                    .log().all()
                    .body(body)
                    .contentType(ContentType.JSON)
                    .header("authorization", "Basic SGV5YnllOkdHd3AxMjM0NTY3OCE=")
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
                    .log().all()
                    .contentType(ContentType.JSON)
                    .header("authorization", "Basic SGV5YnllOkdHd3AxMjM0NTY3OCE=")
                    .when()
                    .delete(url + "/Account/v1/User/" + userId)
                    .then()
                    .log().all()
                    .statusCode(200);
        }

        public void addBook(String userId, String isbn){
            String body = "{\n" +
                    "  \"userId\": \"" + userId + "\",\n" +
                    "  \"collectionOfIsbns\": [\n" +
                    "    {\n" +
                    "      \"isbn\": \"" + isbn + "\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
            given()
                    .contentType(ContentType.JSON)
                    .header("authorization", "Basic SGV5YnllOkdHd3AxMjM0NTY3OCE=")
                    .body(body)
                    .when()
                    .post(url + "/BookStore/v1/Books")
                    .then()
                    .log().all()
                    .statusCode(201);

        }

}
