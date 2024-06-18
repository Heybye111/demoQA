package demoQA;

import demoQA.helper.configHelper;
import io.restassured.http.ContentType;
import org.apache.struts.config.ConfigHelper;

import static io.restassured.RestAssured.given;

public class ApiSteps {



    String url = configHelper.getUrlForAddListOfBooks();

//    public void addBook() {
//        given()
//                .body("""
//                        """)
//                .contentType(ContentType.JSON)
//                .when()
//                .post(url)
//                .then()
//                .log().all()
//                .statusCode(200)
//
//
//
//
//
//    }



}
