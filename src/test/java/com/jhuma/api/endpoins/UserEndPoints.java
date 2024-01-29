package com.jhuma.api.endpoins;
import com.jhuma.api.payload.User;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

//created to perform CURD oparations Create, Read, Update and Delete
public class UserEndPoints {

    public static Response ctreateUser(User payload){

        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);
        return response;
    }

    public static Response readUser(String username){

        Response response=given()
                .pathParam("username",username)
                .when()
                .get(Routes.get_url);
        return response;



    }

    public static Response updateUser(String username, User payload){

        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username", username)
                .when()
                .put(Routes.update_url);
        return response;



    }

    public static Response deleteUser(String username){

        Response response=given()
                .pathParam("username", username)
                .when()
                .delete(Routes.delete_url);
        return response;



    }


}
