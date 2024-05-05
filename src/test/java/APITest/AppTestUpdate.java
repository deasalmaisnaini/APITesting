package APITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AppTestUpdate {
	
    private static final String BASE_URL = "https://dummyapi.io/data/v1/user";
    private static final String VALID_APP_ID = "662c1846fc6498241d22b20b";
    private static final String USER_ID = "663626c5b72aea0a17ea75df";
    private static final String AUTH_HEADER = "app-id";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testUpdateUserTitleValid() {
        given()
            .header(AUTH_HEADER, VALID_APP_ID)
            .contentType(ContentType.JSON)
            .body("{\"title\": \"dr\"}")
        .when()
            .put("/" + USER_ID)
        .then()
            .statusCode(200)
            .body("title", equalTo("dr"));
    }
    
    @Test
    public void testUpdateUserFirstNameValid() {
        given()
            .header(AUTH_HEADER, VALID_APP_ID)
            .contentType(ContentType.JSON)
            .body("{\"firstName\": \"rachma\"}")
        .when()
            .put("/" + USER_ID)
        .then()
            .statusCode(200)
            .body("firstName", equalTo("rachma"));
    }
    
    @Test
    public void testUpdateUserLastNameValid() {
        given()
            .header(AUTH_HEADER, VALID_APP_ID)
            .contentType(ContentType.JSON)
            .body("{\"lastName\": \"alia\"}")
        .when()
            .put("/" + USER_ID)
        .then()
            .statusCode(200)
            .body("lastName", equalTo("alia"));
    }
    
    @Test
    public void testUpdateUserRegisterDate() {
        given()
            .header(AUTH_HEADER, VALID_APP_ID)
            .contentType(ContentType.JSON)
            .body("{\"registerDate\": \"2023-04-28T04:12:38.601Z\"}")
        .when()
            .put("/" + USER_ID)
        .then()
            .statusCode(400)
            .body("error", equalTo("INPUT_NOT_VALID"));
    }
    
    
    @Test
    public void testUpdateUserFirstNameInvalid() {
        given()
            .header(AUTH_HEADER, VALID_APP_ID)
            .contentType(ContentType.JSON)
            .body("{\"firstName\": \"a\"}")
        .when()
            .put("/" + USER_ID)
        .then()
            .statusCode(400)
            .body("error", equalTo("INPUT_NOT_VALID"));
    }
    
    
    
    
   
}
