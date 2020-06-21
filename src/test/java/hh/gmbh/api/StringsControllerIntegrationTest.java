package hh.gmbh.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hh.gmbh.model.AuthRequest;
import hh.gmbh.model.GmbhStringRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static hh.gmbh.TestConstants.TEST_STRING;
import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Slf4j
public class StringsControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper objectMapper;

    private String token;

    @Before
    public void setup() {
        RestAssured.port = this.port;
        token = given()
                .contentType(ContentType.JSON)
                .body(AuthRequest.builder().username("user").password("password").build())
                .when().post("/auth/signin")
                .andReturn().jsonPath().getString("token");
        log.debug("Got token:" + token);
    }

    @Test
    public void testSave() {
        given()
                .contentType(ContentType.JSON)
                .body(GmbhStringRequest.builder().value(TEST_STRING).build())

                .when()
                .post("/strings/add")

                .then()
                .statusCode(403);
    }

    @Test
    public void testSaveWithAuth() {
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(GmbhStringRequest.builder().value(TEST_STRING).build())

                .when()
                .post("/strings/add")

                .then()
                .statusCode(200);
    }

    @Test
    public void testSaveWithInvalidAuth() {
        given()
                .header("Authorization", "Bearer " + "invalidtoken")
                .contentType(ContentType.JSON)
                .body(GmbhStringRequest.builder().value(TEST_STRING).build())

                .when()
                .post("/strings/add")

                .then()
                .statusCode(500);
    }


}
