import io.restassured.http.ContentType;

import static io.restassured.RestAssured.get;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresTests {
    @Test
    void successfulLogin() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"cityslicka\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));


    }

    @Test
    void negativeLogin() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void successfulRegister() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"pistol\"}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));


    }

    @Test
    void negativeRegister() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"sydney@fife\"}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }


    @Test
    void checkSingleUser() {
        Integer response =
                get("https://reqres.in/api/users/2")
                        .then()
                        .extract().path("Michael");


    }
}
