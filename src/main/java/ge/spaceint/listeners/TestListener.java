package ge.spaceint.listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.screenshot;
import static ge.spaceint.utils.HelperFunctions.getDuration;

public class TestListener implements ITestListener {
    private String browserName;
    private long testStartTime;
    private long testEndTime;
    private String testName;

    @Override
    public void onTestStart(ITestResult result) {
        testName = result.getName();
        testStartTime = result.getStartMillis();
        System.out.println(
                "Test '" + testName
                        + " on browser '" + browserName
                        + "' Start Date: " + new Date(testStartTime));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testName = result.getName();
        testEndTime = result.getEndMillis();
        System.out.println(
                "Test '" + testName + "' on browser '" + browserName + "'\n"
                        + "End Date: " + new Date(testEndTime) + "\n"
                        + "Total time needed: " + getDuration(testEndTime - testStartTime) + "\n"
                        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try(InputStream is = Files.newInputStream(Path.of(Objects.requireNonNull(screenshot(OutputType.FILE)).toURI()))){
            Allure.addAttachment("screenshot", is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        testName = result.getName();
        testEndTime = result.getEndMillis();
        System.out.println(
                "Test '" + testName + "' on browser '" + browserName +"'\n"
                        + "Resulted with status: " + result.getStatus() + "\n"
                        + "End Date: " + new Date(testEndTime) + "\n"
                        + "Total time needed: " + getDuration(testEndTime - testStartTime) + "\n"
                        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
        );
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        try(InputStream is = Files.newInputStream(Path.of(Objects.requireNonNull(screenshot(OutputType.FILE)).toURI()))){
            Allure.addAttachment("screenshot", is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        testName = result.getName();
        testEndTime = result.getEndMillis();
        System.out.println(
                "Test '" + testName + "' on browser '" + browserName +"'\n"
                        + "End Date: " + new Date(testEndTime) + "\n"
                        + "Total time needed: " + getDuration(testEndTime - testStartTime) + "\n"
                        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
        );
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        testName = context.getName();
        browserName = context.getCurrentXmlTest().getParameter("browser");
        if(browserName == null) browserName = "chrome"; //default
        long testsStartDate = context.getStartDate().getTime();
        System.out.println("Tests for '" + testName + "' Started At: " + new Date(testsStartDate));
    }

    @Override
    public void onFinish(ITestContext context) {
        testName = context.getName();
        testStartTime = context.getStartDate().getTime();
        testEndTime = context.getEndDate().getTime();
        System.out.println("Tests for '" + testName + "' Finished At: " + new Date(testEndTime));

        String totalExecutionTime = getDuration(testEndTime - testStartTime);
        System.out.println("Total execution time of " + testName + " was " + totalExecutionTime);
    }
}