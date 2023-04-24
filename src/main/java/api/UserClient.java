package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import user.UserRegistration;

import static io.restassured.RestAssured.given;

public class UserClient {
    public final static String BASE_URI = "https://stellarburgers.nomoreparties.site/api/auth/";

    public static String getToken(UserRegistration user) {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(String.format("{\"email\": \"%s\", \"password\": \"%s\"}", user.getEmail(), user.getPassword()))
                .post(BASE_URI + "login");
        response.then().statusCode(200);

        return response.jsonPath().getString("accessToken");
    }

    public static Response deleteUser(String token) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .header("authorization", token)
                .when()
                .delete(BASE_URI + "user");
    }

}
