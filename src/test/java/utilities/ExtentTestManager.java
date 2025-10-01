package utilities;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized void setTest(ExtentTest t) {
        test.set(t);
    }

    public static synchronized ExtentTest getTest() {
        return test.get();
    }

    public static synchronized void removeTest() {
        test.remove();
    }
}