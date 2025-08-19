package myTest;

import myAdapter.*;
import myException.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    VectorAdapterTester.class,
    EnumerationAdapterTester.class,
    IteratorAdapterTester.class,
    SubListAdapterTester.class,
    ListIteratorAdapterTester.class,
    StackAdapterTester.class,
})
public class TestRunner
{
    private static int numberOfTests = 0;
    private static int numberOfSuccesses = 0;
    private static int numberOfFails = 0;
    private static long time = 0;
    private static List<Failure> failures;

    public static void main(String[] args)
    {
        System.out.println("TestRunner has been called. Going to start test executions...\n");
        Result testResults;

        testResults = JUnitCore.runClasses(TestRunner.class);

        System.out.println("\nTests completed!");

        elaborateResults(testResults);
        printStats();
    }

    private static void elaborateResults(Result result)
    {
        numberOfTests = result.getRunCount();
        numberOfFails = result.getFailureCount();
        numberOfSuccesses = numberOfTests - numberOfFails;
        failures = result.getFailures();
        time = result.getRunTime();
    }

    private static void printStats()
    {
        System.out.println("Tests done: " + numberOfTests);
        System.out.println("Successfull tests: " + numberOfSuccesses + " (" + (float)numberOfSuccesses / (float)numberOfTests * 100 + "%)");
        System.out.println("Failed tests: " + numberOfFails + " (" + (float)numberOfFails / (float)numberOfTests * 100 + "%)");
        System.out.println("Elapsed time: " + time + "ms");
        System.out.println("Mean time per test case: " + (float)time / numberOfTests + "ms");

        if(numberOfFails > 0)
        {
            System.out.println("Failed tests are: ");

            for(Failure f : failures)
                System.out.println(" - " + f);

        }
        else
            System.out.println("All " + numberOfTests + " tests were done successfully.");
    }
}
