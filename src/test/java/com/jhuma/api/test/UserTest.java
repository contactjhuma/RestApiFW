package com.jhuma.api.test;

import com.github.javafaker.Faker;
import com.jhuma.api.endpoins.UserEndPoints;
import com.jhuma.api.payload.User;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;



public class UserTest {

    Faker feker;
    User userPayload;
    public Logger logger;
    @BeforeClass
    public void setup(){

        feker=new Faker();
        userPayload = new User();
        userPayload.setId(feker.idNumber().hashCode());

        userPayload.setUsername(feker.name().username());
        userPayload.setFirstName(feker.name().firstName());
        userPayload.setLastName(feker.name().lastName());
        userPayload.setEmail(feker.internet().safeEmailAddress());
        userPayload.setPassword(feker.internet().password(5,10));
        userPayload.setPhone(feker.phoneNumber().cellPhone());


        //Log
        logger= LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void testPostUser(){
        feker=new Faker();
        userPayload = new User();
        userPayload.setId(feker.idNumber().hashCode());

        userPayload.setUsername(feker.name().username());
        userPayload.setFirstName(feker.name().firstName());
        userPayload.setLastName(feker.name().lastName());
        userPayload.setEmail(feker.internet().safeEmailAddress());
        userPayload.setPassword(feker.internet().password(5,10));
        userPayload.setPhone(feker.phoneNumber().cellPhone());

        //System.out.println(userPayload.getUsername());
      //  logger.info("..............Create user...............");
         Response response=UserEndPoints.ctreateUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    //    logger.info(".............. user is created...............");


    }


    @Test(priority =2)
    public void testGetUserByName(){
        Response response=UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode() ,200);





    }

   @Test(priority =3)
    public void testtUserUpadteByName(){
       Faker feker;
       User userPayload1;
        //Upadate data using payload
        feker=new Faker();
        userPayload1 = new User();

        userPayload1.setFirstName(feker.name().firstName());
        userPayload1.setLastName(feker.name().lastName());
        userPayload1.setEmail(feker.internet().safeEmailAddress());

        Response response=UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload1);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode() ,200);

        //checking data after update
        Response responseAfterUpdate=UserEndPoints.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);


    }

    @Test(priority =4)
    public void testtUserDteleteByName() {
        Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);

        }
    }

