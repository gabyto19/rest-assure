package ge.spaceint.listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ReportListener implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for(ISuite suite: suites){
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            suiteResults.values().forEach(el -> {
                ITestContext testContext = el.getTestContext();
                Collection<ITestNGMethod> failedTests = testContext.getFailedTests().getAllMethods();
                failedTests.forEach(System.out::println);
            });
        }
    }
}