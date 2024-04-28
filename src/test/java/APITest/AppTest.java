package APITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AppTest {

    private static final String BASE_URL = "https://dummyapi.io/data/v1/user/create";
    private static final String VALID_APP_ID = "662c1846fc6498241d22b20b";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testCreateUserWithoutAppId() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_MISSING"));
    }

    @Test
    public void testCreateUserWithInvalidAppId() {
        given()
            .header("app-id", "invalid_app_id")
            .contentType(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_NOT_EXIST"));
    }

   
}
