package APITest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AppTestDelete {

    private static final String BASE_URL = "https://dummyapi.io/data/v1/user";
    private static final String USER_ID_VALID = "60d0fe4f5311236168a109cf";
    private static final String VALID_APP_ID = "662c1846fc6498241d22b20b";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testDeleteUserWithoutAuthorization() {
        // Test Case 1 (TCD1) : Request without valid app-id
        given()
            .contentType(ContentType.JSON)
        .when()
            .delete("/" + USER_ID_VALID)
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_MISSING"));

    }

    @Test
    public void testDeleteUserWithInvalidAppId() {
        // Test Case 2 (TCD2) : Request with invalid app-id
        given()
            .header("app-id", "12345678")
            .contentType(ContentType.JSON)
        .when()
            .delete("/" + USER_ID_VALID)
        .then()
            .statusCode(403)
            .body("error", equalTo("APP_ID_NOT_EXIST"));

    }

    @Test
public void testDeleteUserWithDeletedUserId() {
    // Test Case 4 (TCD4) : Request with valid app-id but user id has been deleted previously
    given()
        .header("app-id", VALID_APP_ID)
        .contentType(ContentType.JSON)
    .when()
        .delete("/60d0fe4f5311236168a109ca")
    .then()
        .statusCode(404) // Assuming resource not found with status code 404
        .body("error", equalTo("RESOURCE_NOT_FOUND")); // Assuming the response contains the expected error message

    // Here you can add additional assertions if needed
}

@Test
public void testDeleteUserWithNonExistentUserId() {
    // Test Case 5 (TCD5) : Request with valid app-id but user id does not exist in the system
    given()
        .header("app-id", VALID_APP_ID)
        .contentType(ContentType.JSON)
    .when()
        .delete("/60d0fe4f5321236168a109cb")
    .then()
        .statusCode(404) // Assuming resource not found with status code 404
        .body("error", equalTo("RESOURCE_NOT_FOUND")); // Assuming the response contains the expected error message

    // Here you can add additional assertions if needed
}

@Test
    public void testDeleteUserWithValidAuthorization() {
        // Test Case 3 (TCD3) : Request with valid app-id and user id
        given()
            .header("app-id", VALID_APP_ID)
            .contentType(ContentType.JSON)
        .when()
            .delete("/" + USER_ID_VALID)
        .then()
            .statusCode(200) // Assuming successful response with status code 200
            .body("id", equalTo(USER_ID_VALID)); // Assuming the response contains the deleted user's ID

        // Here you can add additional assertions if needed
    }

}

