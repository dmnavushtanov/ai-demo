package com.apidevelopment.demo;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class RestAssuredDemo {

    @Test
    void someTest(){
        RestAssured.baseURI = "https://reqres.in/api";

        // Sending GET request and storing the response
        Response response =
                given().
                        when().
                        get("/users/2").
                        then().
                        assertThat().
                        statusCode(200). // Verify the status code is 200
                        body("data.id", notNullValue()). // Verify 'id' field exists and is not null
                        body("data.id", instanceOf(Integer.class)). // Verify 'id' is of type Integer
                        body("data.email", notNullValue()). // Verify 'email' field exists and is not null
                        body("data.email", instanceOf(String.class)). // Verify 'email' is of type String
                        body("data.first_name", notNullValue()). // Verify 'first_name' field exists and is not null
                        body("data.first_name", instanceOf(String.class)). // Verify 'first_name' is of type String
                        body("data.last_name", notNullValue()). // Verify 'last_name' field exists and is not null
                        body("data.last_name", instanceOf(String.class)). // Verify 'last_name' is of type String
                        body("data.avatar", notNullValue()). // Verify 'avatar' field exists and is not null
                        body("data.avatar", instanceOf(String.class)). // Verify 'avatar' is of type String
                        body("support.url", notNullValue()). // Verify 'support.url' field exists and is not null
                        body("support.url", instanceOf(String.class)). // Verify 'support.url' is of type String
                        body("support.text", notNullValue()). // Verify 'support.text' field exists and is not null
                        body("support.text", instanceOf(String.class)). // Verify 'support.text' is of type String
                        extract().response();

        System.out.println(response.asPrettyString());
    }
}
