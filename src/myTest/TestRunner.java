package myTest;

import myAdapter.*;
import myException.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;
import org.junit.runner.Description;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestRunner class for executing the complete test suite for the adapter implementation.
 *
 * <p>The test suite includes the following test classes:
 * <ul>
 *   <li>{@link EnumerationAdapterTest}</li>
 *   <li>{@link IteratorAdapterTest}</li>
 *   <li>{@link ListIteratorAdapterTest}</li>
 *   <li>{@link StackAdapterTest}</li>
 *   <li>{@link SubListAdapterTest}</li>
 *   <li>{@link VectorAdapterTest}</li>
 * </ul>
 *
 * <p>This runner provides comprehensive output including:
 * <ul>
 *   <li>Execution header with environment information</li>
 *   <li>Detailed test statistics (pass/fail/ignore counts)</li>
 *   <li>Failure details with relevant stack traces</li>
 *   <li>Performance metrics (execution time, memory usage)</li>
 *   <li>Summary of test execution results</li>
 * </ul>
 *
 * <p>The runner exits with status code 1 if any tests fail, 0 if all tests pass.
 *
 * <h2>Testing Framework Information</h2>
 * <p>This test suite utilizes JUnit 4.13 testing framework with Hamcrest 1.3
 * matchers for assertions. These dependencies are located as follows:</p>
 * <ul>
 *   <li>JUnit 4.13: <code>lib/junit-4.13.jar</code></li>
 *   <li>Hamcrest Core 1.3: <code>Matcher/hamcrest-core-1.3.jar</code></li>
 * </ul>
 *
 * @author   Zoppolato Tommaso Vittorio (matricola: 2101844)
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    EnumerationAdapterTest.class,
    IteratorAdapterTest.class,
    ListIteratorAdapterTest.class,
    StackAdapterTest.class,
    SubListAdapterTest.class,
    VectorAdapterTest.class
})
public class TestRunner
{
    private static int numberOfTests = 0;
    private static int numberOfSuccesses = 0;
    private static int numberOfFails = 0;
    private static int numberOfIgnored = 0;
    private static long time = 0;
    private static List<Failure> failures;

    private static final String BOLD = "\033[1m";
    private static final String RESET = "\033[0m";

    private static final String SEPARATOR = "================================================================";
    private static final String SUB_SEPARATOR = "----------------------------------------------------------------";
    private static final String HEADER_START = ">>> ";
    private static final String SUCCESS_MARKER = "[" + BOLD + "+" + RESET + "]";
    private static final String FAIL_MARKER = "[" + BOLD + "-" + RESET + "]";
    private static final String WARNING_MARKER = "[!]";
    private static final String INFO_MARKER = "[i]";

    /**
     * Default constructor.
     */
    TestRunner()
    {}

    /**
     * Main entry point for the test runner.
     * Executes all test classes and prints the results.
     *
     * @param args CLI parameters (unused)
     */
    public static void main(String[] args)
    {
        printHeader();

        System.out.println(INFO_MARKER + " Starting test suite execution...\n");

        long startTime = System.currentTimeMillis();

        Result testResults = JUnitCore.runClasses(TestRunner.class);

        long actualExecutionTime = System.currentTimeMillis() - startTime;

        System.out.println("\n" + INFO_MARKER + " Execution completed!");
        System.out.println(SUB_SEPARATOR);

        elaborateResults(testResults, actualExecutionTime);

        printDetailedStats();
        printFailureDetails();
        printSummary();

        System.exit(numberOfFails > 0 ? 1 : 0);
    }

    /**
     * Prints the execution header with environment information and test configuration.
     */
    private static void printHeader()
    {
        System.out.println(SEPARATOR);
        System.out.println("                    " + BOLD + " TEST SUITE EXECUTION " + RESET);
        System.out.println(SEPARATOR);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.println(INFO_MARKER + " Start time: " + BOLD + sdf.format(new Date()) + RESET);
        System.out.println(INFO_MARKER + " JUnit version: " + BOLD + "4.13" + RESET);
        System.out.println(INFO_MARKER + " Java version: " + BOLD + System.getProperty("java.version") + RESET);

        System.out.println("\n" + HEADER_START + BOLD + "CONFIGURED TEST CLASSES:" + RESET);
        System.out.println("  - EnumerationAdapterTest");
        System.out.println("  - IteratorAdapterTest");
        System.out.println("  - ListIteratorAdapterTest");
        System.out.println("  - StackAdapterTest");
        System.out.println("  - SubListAdapterTest");
        System.out.println("  - VectorAdapterTest");

        System.out.println(SEPARATOR);
    }

    /**
     * Processes and stores the test execution results.
     *
     * @param result the JUnit test result object
     * @param actualTime the actual execution time in milliseconds
     */
    private static void elaborateResults(Result result, long actualTime)
    {
        numberOfTests = result.getRunCount();
        numberOfFails = result.getFailureCount();
        numberOfIgnored = result.getIgnoreCount();
        numberOfSuccesses = numberOfTests - numberOfFails;

        failures = result.getFailures();

        time = actualTime;
    }

    /**
     * Prints detailed statistics about test execution including:
     * - Number of tests run, passed, failed, and ignored
     * - Execution time metrics
     * - Memory usage information
     */
    private static void printDetailedStats()
    {
        System.out.println("\n" + HEADER_START + BOLD + " DETAILED RESULTS: " + RESET);
        System.out.println(SUB_SEPARATOR);

        System.out.printf("%-25s: " + BOLD + "%d" + RESET + "%n", "Tests run", numberOfTests);

        System.out.printf("%-25s: ", "Tests passed");

        if(numberOfSuccesses == numberOfTests && numberOfTests > 0)
            System.out.printf(BOLD + "%d" + RESET + " %s", numberOfSuccesses, SUCCESS_MARKER);
        else
            System.out.printf("%d", numberOfSuccesses);

        if(numberOfTests > 0)
            System.out.printf(" (%.1f%%)", (float)numberOfSuccesses / (float)numberOfTests * 100);

        System.out.println();

        System.out.printf("%-25s: ", "Tests failed");

        if(numberOfFails > 0)
            System.out.printf(BOLD + "%d" + RESET + " %s", numberOfFails, FAIL_MARKER);
        else
            System.out.printf("%d %s", numberOfFails, SUCCESS_MARKER);

        if(numberOfTests > 0)
            System.out.printf(" (%.1f%%)", (float)numberOfFails / (float)numberOfTests * 100);

        System.out.println();

        if(numberOfIgnored > 0)
        {
            System.out.printf("%-25s: " + BOLD + "%d" + RESET + " %s", "Tests ignored", numberOfIgnored, WARNING_MARKER);

            if(numberOfTests > 0)
                System.out.printf(" (%.1f%%)", (float)numberOfIgnored / (float)numberOfTests * 100);

            System.out.println();
        }

        System.out.println();

        System.out.printf("%-25s: ", "Total time");

        System.out.printf(BOLD + "%d ms" + RESET, time);

        System.out.println();

        if(numberOfTests > 0)
        {
            float avgTime = (float)time / numberOfTests;

            System.out.printf("%-25s: ", "Average time per test");

            System.out.printf("%.2f ms", avgTime);

            System.out.println();
        }

        Runtime runtime = Runtime.getRuntime();

        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        double memoryMB = usedMemory / (1024.0 * 1024.0);

        System.out.printf("%-25s: ", "Memory used");

        System.out.printf("%.2f MB%n", memoryMB);

    }

    /**
     * Prints detailed information about test failures including:
     * - Test method name and class
     * - Error messages
     * - Relevant stack trace lines (filtered to show only project-related packages)
     */
    private static void printFailureDetails()
    {
        if(numberOfFails > 0)
        {
            System.out.println("\n" + HEADER_START + BOLD + " FAILURE DETAILS: " + RESET + " " + FAIL_MARKER);
            System.out.println(SUB_SEPARATOR);

            int failureNumber = 1;

            for(Failure f : failures)
            {
                System.out.println(BOLD + failureNumber + ") " + getTestMethodName(f.getDescription()) + RESET + " " + FAIL_MARKER);
                System.out.println("   Class: " + f.getDescription().getClassName());

                String message = f.getMessage();

                if(message != null && !message.trim().isEmpty())
                    System.out.println("   " + BOLD + "Error:" + RESET + " " + message);
                else
                    System.out.println("   " + BOLD + "Error:" + RESET + " " + f.getException().getClass().getSimpleName());

                String trace = f.getTrace();

                if(trace != null)
                {
                    String[] lines = trace.split("\n");
                    System.out.println("   " + BOLD + "Stack trace (relevant lines):" + RESET);

                    int count = 0;

                    for(String line : lines)
                    {
                        if(line.contains("myTest") || line.contains("myAdapter") || line.contains("myException"))
                        {
                            System.out.println("     >>> " + line.trim());

                            count++;
                            if(count >= 5) break;
                        }
                    }

                    if(count == 0 && lines.length > 0)
                        System.out.println("     >>> " + lines[0].trim());
                }

                System.out.println();
                failureNumber++;
            }
        }
    }

    /**
     * Prints a comprehensive summary of the test execution including:
     * - Overall success/failure status
     * - Total execution time
     * - End timestamp
     * - Visual status indicator
     */
    private static void printSummary()
    {
        System.out.println(SEPARATOR);
        System.out.println("                         " + BOLD + " SUMMARY " + RESET);
        System.out.println(SEPARATOR);

        if(numberOfFails == 0)
        {
            System.out.println(BOLD + "SUCCESS:" + RESET + " All " + BOLD + numberOfTests + RESET + " tests passed! " + SUCCESS_MARKER);
            System.out.println("Test suite completed without errors.");
        }
        else
        {
            System.out.println(BOLD + "WARNING:" + RESET + " " + BOLD + numberOfFails + RESET + " out of " + BOLD + numberOfTests + RESET + " tests failed. " + FAIL_MARKER);
            System.out.println("Check the failure details reported above.");
        }

        System.out.println();

        System.out.print("Execution completed in ");

        System.out.println(BOLD + time + " ms" + RESET);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("End time: " + BOLD + sdf.format(new Date()) + RESET);

        System.out.println(SEPARATOR);

        if(numberOfFails == 0)
            System.out.println("                    " + SUCCESS_MARKER + " " + BOLD + " ALL TESTS PASSED " + RESET + " " + SUCCESS_MARKER);
        else
            System.out.println("                    " + FAIL_MARKER + " " + BOLD + " TESTS FAILED " + RESET + " " + FAIL_MARKER);

        System.out.println(SEPARATOR);
    }

    /**
     * Extracts the test method name from a JUnit Description object.
     *
     * @param description the JUnit test description
     * @return the clean test method name without class information
     */
    private static String getTestMethodName(Description description)
    {
        String displayName = description.getDisplayName();

        if (displayName.contains("("))
            return displayName.substring(0, displayName.indexOf("("));

        return displayName;
    }

    /**
     * Checks if all tests have passed successfully.
     *
     * @return true if all tests passed, false otherwise
     */
    public static boolean hasTestsPassed()
    {
        return numberOfFails == 0;
    }

    /**
     * Returns the number of failed tests.
     *
     * @return the count of failed tests
     */
    public static int getFailureCount()
    {
        return numberOfFails;
    }

    /**
     * Returns the total execution time of the test suite.
     *
     * @return execution time in milliseconds
     */
    public static long getExecutionTime()
    {
        return time;
    }
}
