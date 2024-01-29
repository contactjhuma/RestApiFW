package com.jhuma.api.endpoins;

import com.jhuma.api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    //Created for getting url from properties file
    public static ResourceBundle getRoutesURL()
    {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("routes");//Load the properties file
        // .getBoundle("routes) is name of the properties file
        return resourceBundle;
    }

    public static Response ctreateUser(User payload){

        String post_url=getRoutesURL().getString("post_url");

        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        return response;
    }

    public static Response readUser(String username){

        String get_url=getRoutesURL().getString("get_url");
        Response response=given()
                .pathParam("username",username)
                .when()
                .get(get_url);
        return response;



    }

    public static Response updateUser(String username, User payload){

        String update_url=getRoutesURL().getString("update_url");
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username", username)
                .when()
                .put(update_url);
        return response;



    }

    public static Response deleteUser(String username){

        String delete_url=getRoutesURL().getString("delete_url");

        Response response=given()
                .pathParam("username", username)
                .when()
                .delete(delete_url);
        return response;



    }


}
