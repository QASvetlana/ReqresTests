import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.is;


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
    public void checkSingleUser() {

        when()
                .get("https://reqres.in/api/users/2")
                .then()
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.id", is(2))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }
}



