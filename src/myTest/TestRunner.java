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

    private static final String SEPARATOR = "================================================================";
    private static final String SUB_SEPARATOR = "----------------------------------------------------------------";
    private static final String HEADER_START = ">>> ";
    private static final String SUCCESS_MARKER = "[✓] ";
    private static final String FAIL_MARKER = "[✗] ";
    private static final String WARNING_MARKER = "[!] ";
    private static final String INFO_MARKER = "[i] ";

    public static void main(String[] args)
    {
        printHeader();

        System.out.println(INFO_MARKER + "Starting test suite execution...\n");

        Result testResults = JUnitCore.runClasses(TestRunner.class);

        long startTime = System.currentTimeMillis();
        long actualExecutionTime = System.currentTimeMillis() - startTime;

        System.out.println("\n" + INFO_MARKER + "Execution completed!");
        System.out.println(SUB_SEPARATOR);

        elaborateResults(testResults, actualExecutionTime);

        printDetailedStats();
        printFailureDetails();
        printSummary();

        System.exit(numberOfFails > 0 ? 1 : 0);
    }

    private static void printHeader()
    {
        System.out.println(SEPARATOR);
        System.out.println("                    **TEST SUITE EXECUTION**");
        System.out.println(SEPARATOR);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.println(INFO_MARKER + "Start time: " + sdf.format(new Date()));
        System.out.println(INFO_MARKER + "JUnit version: 4.13");
        System.out.println(INFO_MARKER + "Java version: " + System.getProperty("java.version"));
        System.out.println(INFO_MARKER + "JVM: " + System.getProperty("java.vm.name") + " " + System.getProperty("java.vm.version"));

        System.out.println("\n" + HEADER_START + "CONFIGURED TEST CLASSES:");
        System.out.println("  • EnumerationAdapterTest");
        System.out.println("  • IteratorAdapterTest");
        System.out.println("  • ListIteratorAdapterTest");
        System.out.println("  • StackAdapterTest");
        System.out.println("  • SubListAdapterTest");
        System.out.println("  • VectorAdapterTest");

        System.out.println(SEPARATOR);
    }

    private static void elaborateResults(Result result, long actualTime)
    {
        numberOfTests = result.getRunCount();
        numberOfFails = result.getFailureCount();
        numberOfIgnored = result.getIgnoreCount();
        numberOfSuccesses = numberOfTests - numberOfFails;

        failures = result.getFailures();

        time = actualTime;
    }

    private static void printDetailedStats()
    {
        System.out.println("\n" + HEADER_START + "**DETAILED RESULTS:**");
        System.out.println(SUB_SEPARATOR);

        System.out.printf("%-25s: **%d**%n", "Tests run", numberOfTests);

        System.out.printf("%-25s: ", "Tests passed");

        if(numberOfSuccesses == numberOfTests && numberOfTests > 0)
            System.out.printf("**%d** %s", numberOfSuccesses, SUCCESS_MARKER);
        else
            System.out.printf("%d", numberOfSuccesses);

        if(numberOfTests > 0)
            System.out.printf(" (%.1f%%)", (float)numberOfSuccesses / (float)numberOfTests * 100);

        System.out.println();

        System.out.printf("%-25s: ", "Tests failed");

        if(numberOfFails > 0)
            System.out.printf("**%d** %s", numberOfFails, FAIL_MARKER);
        else
            System.out.printf("%d %s", numberOfFails, SUCCESS_MARKER);

        if(numberOfTests > 0)
            System.out.printf(" (%.1f%%)", (float)numberOfFails / (float)numberOfTests * 100);

        System.out.println();

        if(numberOfIgnored > 0)
        {
            System.out.printf("%-25s: **%d** %s", "Tests ignored", numberOfIgnored, WARNING_MARKER);

            if(numberOfTests > 0)
                System.out.printf(" (%.1f%%)", (float)numberOfIgnored / (float)numberOfTests * 100);

            System.out.println();
        }

        System.out.println();

        System.out.printf("%-25s: ", "Total time");

        if(time > 5000)
            System.out.printf("**%d ms** %s", time, WARNING_MARKER);
        else
            System.out.printf("%d ms", time);

        System.out.println();

        if(numberOfTests > 0)
        {
            float avgTime = (float)time / numberOfTests;

            System.out.printf("%-25s: ", "Average time per test");

            if(avgTime > 1000)
                System.out.printf("**%.2f ms** %s", avgTime, WARNING_MARKER);
            else
                System.out.printf("%.2f ms", avgTime);

            System.out.println();
        }

        Runtime runtime = Runtime.getRuntime();

        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        double memoryMB = usedMemory / (1024.0 * 1024.0);

        System.out.printf("%-25s: ", "Memory used");

        if(memoryMB > 100)
            System.out.printf("**%.2f MB** %s%n", memoryMB, WARNING_MARKER);
        else
            System.out.printf("%.2f MB%n", memoryMB);

    }

    private static void printFailureDetails()
    {
        if(numberOfFails > 0)
        {
            System.out.println("\n" + HEADER_START + "**FAILURE DETAILS:** " + FAIL_MARKER);
            System.out.println(SUB_SEPARATOR);

            int failureNumber = 1;

            for(Failure f : failures)
            {
                System.out.println("**" + failureNumber + ") " + getTestMethodName(f.getDescription()) + "** " + FAIL_MARKER);
                System.out.println("   Class: " + f.getDescription().getClassName());

                String message = f.getMessage();

                if(message != null && !message.trim().isEmpty())
                    System.out.println("   **Error:** " + message);
                else
                    System.out.println("   **Error:** " + f.getException().getClass().getSimpleName());

                String trace = f.getTrace();

                if(trace != null)
                {
                    String[] lines = trace.split("\n");
                    System.out.println("   **Stack trace (relevant lines):**");

                    int count = 0;

                    for(String line : lines)
                    {
                        if(line.contains("myTest") || line.contains("myAdapter") || line.contains("myException"))
                        {
                            System.out.println("     >>> " + line.trim());

                            count++;
                            if(count >= 3) break;
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

    private static void printSummary()
    {
        System.out.println(SEPARATOR);
        System.out.println("                         **SUMMARY**");
        System.out.println(SEPARATOR);

        if(numberOfFails == 0)
        {
            System.out.println("**SUCCESS:** All " + numberOfTests + " tests passed! " + SUCCESS_MARKER);
            System.out.println("Test suite completed without errors.");
        }
        else
        {
            System.out.println("**WARNING:** " + numberOfFails + " out of " + numberOfTests + " tests failed. " + FAIL_MARKER);
            System.out.println("Check the failure details reported above.");
        }

        System.out.println();

        System.out.print("Execution completed in ");

        if(time > 10000)
            System.out.println("**" + time + " ms** " + WARNING_MARKER + " (Slow execution)");
        else
            System.out.println("**" + time + " ms**");


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("End time: " + sdf.format(new Date()));

        System.out.println(SEPARATOR);

        if(numberOfFails == 0)
            System.out.println("                    " + SUCCESS_MARKER + " **ALL TESTS PASSED** " + SUCCESS_MARKER);
        else
            System.out.println("                    " + FAIL_MARKER + " **TESTS FAILED** " + FAIL_MARKER);

        System.out.println(SEPARATOR);
    }

    private static String getTestMethodName(Description description)
    {
        String displayName = description.getDisplayName();

        if (displayName.contains("("))
            return displayName.substring(0, displayName.indexOf("("));

        return displayName;
    }

    public static boolean hasTestsPassed()
    {
        return numberOfFails == 0;
    }

    public static int getFailureCount()
    {
        return numberOfFails;
    }

    public static long getExecutionTime()
    {
        return time;
    }
}
