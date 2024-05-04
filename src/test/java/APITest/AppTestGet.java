package APITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AppTestGet {

    private static final String BASE_URL = "https://dummyapi.io/data/v1/user";
    private static final String USER_ID_VALID = "60d0fe4f5311236168a109cb";
    private static final String USER_ID_NOT_EXIST = "60d0fe4f5312236168a109cb";
    private static final String VALID_APP_ID = "662c1846fc6498241d22b20b";

    private int initialUserCount;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @AfterMethod
    public void verifyDatabaseUnchanged() {
        // Here you can add assertions to ensure that the database state remains unchanged.
        // For example, you can compare the current number of users with the initialUserCount.
    }

    @Test
    public void testGetUserByIdWithoutAuthorization() {
        // Test Case 1 (TCG1) : Request without valid app-id
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/" + USER_ID_VALID)
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_MISSING"));

        // Here you can add additional assertions if needed
    }

    @Test
    public void testGetUserByIdWithInvalidAppId() {
        // Test Case 2 (TCG2) : Request with invalid app-id
        given()
            .header("app-id", "12345678")
            .contentType(ContentType.JSON)
        .when()
            .get("/" + USER_ID_VALID)
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_NOT_EXIST"));

        // Here you can add additional assertions if needed
    }

    @Test
    public void testGetUserByIdWithValidAuthorization() {
        // Test Case 3 (TCG3) : Request with valid app-id and user id
        given()
            .header("app-id", VALID_APP_ID)
            .contentType(ContentType.JSON)
        .when()
            .get("/" + USER_ID_VALID)
        .then()
            .statusCode(200); // Assuming successful response with status code 200

        // Here you can add additional assertions if needed
    }

    @Test
    public void testGetUserByIdWithInvalidUserId() {
        // Test Case 4 (TCG5) : Request with valid app-id but invalid user id
        given()
            .header("app-id", VALID_APP_ID)
            .contentType(ContentType.JSON)
        .when()
            .get("/" + USER_ID_NOT_EXIST)
        .then()
            .statusCode(404) // Assuming resource not found with status code 404
            .body("error", equalTo("RESOURCE_NOT_FOUND")); // Assuming the response contains the expected error message

        // Here you can add additional assertions if needed
    }

    @Test
public void testGetUserByIdWithInvalidUserIdFormat() {
    // Test Case 5 (TCG6) : Request with valid app-id but invalid user id format
    given()
        .header("app-id", VALID_APP_ID)
        .contentType(ContentType.JSON)
    .when()
        .get("/1234567abcd")
    .then()
        .statusCode(400) // Assuming bad request with status code 400
        .body("error", equalTo("PARAMS_NOT_VALID")); // Assuming the response contains the expected error message

    // Here you can add additional assertions if needed
}

}
