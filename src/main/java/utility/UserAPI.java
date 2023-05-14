package utility;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import static utility.Constants.*;

public class UserAPI {

    public UserAPI() {

        RestAssured.baseURI = STELLAR_API_URL;
    }

    @Step("Авторизация пользователя через API")
    public ValidatableResponse loginUser (UserLogin userLogin) {
        return given().header("Content-type", "application/json")
                .and()
                .body(userLogin)
                .when()
                .post(PATH_FOR_LOGIN)
                .then();
    }
    @Step("Создаю пользователя через API")
    public ValidatableResponse createUser (UserLogin userLogin) {
        return given().header("Content-type", "application/json")
                .and()
                .body(userLogin)
                .when()
                .post(PATH_FOR_REGISTER)
                .then();
    }

    @Step("Удаляю пользователя через API")
    public void deleteUser (String accessToken) {
        given() .header("Content-type", "application/json")
                .and()
                .header("authorization", accessToken)
                .delete(PATH_FOR_DELETION);
    }

}
