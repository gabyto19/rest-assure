package ge.spaceint.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.time.Duration;
import java.time.LocalDateTime;

import static ge.spaceint.data.HelperData.DATE_TIME_FORMATTER;

public class SuitListener implements ISuiteListener {
    private LocalDateTime suiteStartTime;
    private String suiteName;

    @Override
    public void onStart(ISuite suite) {
        suiteName = suite.getName();
        suiteStartTime = LocalDateTime.now();
        System.out.println("Suite '" + suiteName + "' Start Date: " + suiteStartTime.format(DATE_TIME_FORMATTER));
    }

    @Override
    public void onFinish(ISuite suite) {
        suiteName = suite.getName();
        LocalDateTime suiteEndTime = LocalDateTime.now();
        System.out.println("Suite '" + suiteName + "' End Date: " + suiteEndTime.format(DATE_TIME_FORMATTER));

        Duration duration = Duration.between(suiteStartTime, suiteEndTime);
        System.out.println("Total Time For Suite '"+ suiteName + "': " + duration.getSeconds() + " Seconds");
    }
}