package com.jhuma.api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;


    public void onStart(ITestContext context) {
      String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        repName ="Test-Report"+timeStamp+".html";
        sparkReporter=new ExtentSparkReporter("/Users/jhumamakal/Documents/envision leaning/Java/practicals/RestApiFW/reports/"+repName);
        sparkReporter.config().setReportName("Pet store user Api");
        sparkReporter.config().setDocumentTitle("RestAssureApiProject");
        sparkReporter.config().setTheme(Theme.DARK);

        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("Application","Pet store user Api");
        extent.setSystemInfo("Tester","Jhuma");

    }

    public void onTestSuccess(ITestResult result) {
        test=extent.createTest(result.getName());
        test.log(Status.PASS,"Test Pass");
    }

    public void onTestFailure(ITestResult result) {
        test=extent.createTest(result.getName());
        test.log(Status.FAIL,"Test Fail");
        test.log(Status.FAIL,result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
        test=extent.createTest(result.getName());
        test.log(Status.SKIP,"Test Skip");
        test.log(Status.SKIP,result.getThrowable().getMessage());
    }


    public void onFinish(ITestContext context) {

        extent.flush();
    }



}
