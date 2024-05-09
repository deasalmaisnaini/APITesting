package APITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AppTestCreate {

    private static final String BASE_URL = "https://dummyapi.io/data/v1/user/create";
    private static final String VALID_APP_ID = "66303d367989b4290e1960f2";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    // TCC1
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
    // TCC2
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

    @Test
    // TCC30
    public void testCreateUserWithInvalidDateOfBirthFormat() {
        String invalidDateOfBirth = "2000-12-03"; // format tahun menjadi YYYY-DD-MM, seharusnya MM-DD-YYYY
        given()
                .header("app-id", VALID_APP_ID)
                .contentType(ContentType.JSON)
                .body("{\"title\":\"miss\",\"firstName\":\"dea\",\"lastName\":\"salsa\",\"gender\":\"female\",\"email\":\"lliie123@example.com\",\"dateOfBirth\":\""
                        + invalidDateOfBirth
                        +
                        "\",\"phone\":\"03123459890\",\"picture\":\"https://example.com/dea.jpg\",\"location\":{\"street\":\"Ciwaruga\",\"city\":\"Parongpong\",\"state\":\"Parongpong\",\"country\":\"Parongpong\",\"timezone\":\"Parongpong\"}}")
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(400)
                .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    // TCC3
    public void testCreateNonExistingUser() {
        given()
                .header("app-id", VALID_APP_ID)
                .contentType(ContentType.JSON)
                .body("{\"title\":\"mr\",\"firstName\":\"Hong\",\"lastName\":\"Jisoo\",\"gender\":\"male\",\"email\":\"honggjjiii1@example.com\",\"dateOfBirth\":\"2/12/2000\",\"phone\":\"0876787890\",\"picture\":\"https://example.com/emilysmith.jpg\",\"location\":{\"street\":\"Ciwaruga\",\"city\":\"Bandung Barat\",\"state\":\"Jawa Barat\",\"country\":\"Indonesia\",\"timezone\":\"+7.00\"}}")
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("mr"))
                .body("firstName", equalTo("Hong"))
                .body("lastName", equalTo("Jisoo"))
                .body("gender", equalTo("male"))
                .body("email", equalTo("honggjjiii1@example.com"))
                .body("dateOfBirth", equalTo("2000-02-12T00:00:00.000Z"))
                .body("phone", equalTo("0876787890"))
                .body("picture", equalTo("https://example.com/emilysmith.jpg"))
                .body("location.street", equalTo("Ciwaruga"))
                .body("location.city", equalTo("Bandung Barat"))
                .body("location.state", equalTo("Jawa Barat"))
                .body("location.country", equalTo("Indonesia"))
                .body("location.timezone", equalTo("+7.00"));
    }

    @Test
    // TCC44
    public void testCreateUserWithLastNameAsNumber() {
        given()
                .header("app-id", VALID_APP_ID)
                .contentType(ContentType.JSON)
                .body("{\"title\":\"mr\",\"firstName\":\"Hong\",\"lastName\":90898,\"gender\":\"male\",\"email\":\"kenaappaa37@example.com\",\"dateOfBirth\":\"2/12/2000\",\"phone\":\"0876787890\",\"picture\":\"https://example.com/emilysmith.jpg\",\"location\":{\"street\":\"Ciwaruga\",\"city\":\"Bandung Barat\",\"state\":\"Jawa Barat\",\"country\":\"Indonesia\",\"timezone\":\"+7.00\"}}")
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(400)
                .body("error", equalTo("Bad Request"));
    }

}
