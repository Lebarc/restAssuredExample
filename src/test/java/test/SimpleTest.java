package test;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.io.File;

@Epic("API Tests")
@Feature("Auth Tests")
public class SimpleTest {

    @Test()
    @Owner("QAExpert")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify login")
    @Step()
    public void GetToken() {
        RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
        RestAssured.given()
                .when()
                .get("/Hyderabad")
                .then()
                .statusCode(200);
    }

//    @Test
//    public void PostToken() {
//        RestAssured.baseURI = "https://testes.fundacred.org.br/portal-estudante-rest/resources/autenticacao";
//        RequestSpecification request = RestAssured.given();
//        JSONObject requestParams = new JSONObject();
//
//        requestParams.put("cpf", "00318625008");
//        requestParams.put("password", "123456789");
//        request.header("Content-Type", "application/json");
//        request.body(requestParams.toJSONString());
//        Response response = request.post("/autenticar");
//
//        String responseBody = response.getBody().asString();
//        String responseHeader = response.getHeader("token");
//        System.out.println("Response Body is =>  " + responseBody);
//        System.out.println("Header token is =>  " + responseHeader);
//
//        int status = response.getStatusCode();
//        System.out.println("Status is =>  " + status);
//        Assert.assertEquals(status, 200);
//    }

//    @Test()
//    @Owner("QAExpert")
//    @Severity(SeverityLevel.NORMAL)
//    @Description("Test Description : Verify login")
//    public void AutenticarPost() {
//        RestAssured.baseURI = "https://gateway-develop.lidia.com.br/security/api/auth/v1";
//        RequestSpecification request = RestAssured.given()
//        .filter(new AllureRestAssured());
//        JSONObject requestParams = new JSONObject();
//
//        requestParams.put("userName", "'01762667045'");
//        requestParams.put("password", "'lidia@123'");
//        request.header("Content-Type", "application/json");
//        request.header("deviceid", "7035D61F-29C9-4BE5-8A10-8B3B16157AB0");
//        request.body(requestParams.toJSONString());
//        Response response = request.post("/login");
//
//        String responseBody = response.getBody().jsonPath().get("token.value");
//        System.out.println("Response Body is =>  " + responseBody);
//
//        //Assert.assertEquals(response.getStatusCode(), 201);
//        Assert.assertEquals(response.getHeaders().getValue("Content-Type"), "application/json");
//    }

    @Test()
    @Owner("QAExpert")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify login")
    @Step()
    void auth1() {
        RestAssured.baseURI = "https://gateway-develop.lidia.com.br/security/api/auth/v1";

        given().log().all()
                .contentType(ContentType.JSON)
                .header("deviceid", "7035D61F-29C9-4BE5-8A10-8B3B16157AB0")
                //.body(requestParams)
                .body("{\n" +
                        "    \"userName\": \"01762667045\",\n" +
                        "    \"password\": \"lidia@123\"\n" +
                        "}")
                .when()
                .post("/login")
                .then().log().all()
                .statusCode(201);
    }

    //    @Test
//    void auth2() {
//        RestAssured.baseURI = "https://gateway-develop.lidia.com.br/security/api/auth/v1";
//        JSONObject requestParams = new JSONObject();
//        requestParams.put("userName", "01762667045");
//        requestParams.put("password", "lidia@123");
//        given().log().all()
//                .contentType(ContentType.JSON)
//                .header("deviceid", "7035D61F-29C9-4BE5-8A10-8B3B16157AB0")
//                .body(requestParams)
//                .when()
//                .post("/login")
//                .then().log().all()
//                .statusCode(201);
//    }

    @Test()
    @Owner("QAExpert")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify /books schema")
    @Step()
    public void SchemaBooksValidate() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore/v1/Books";
        given()
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("/Users/leonardomincola/Downloads/RestAssuredExample/src/test/java/schema/booksSchema.json")));
    }
}
