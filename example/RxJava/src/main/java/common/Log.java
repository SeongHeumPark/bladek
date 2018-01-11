package common;

/**
 * @author seongheum.park
 */
public class Log {
    public static void i(Object obj) {
        System.out.println(getThreadName() + " | value = " + obj);
    }

    public static void it(Object obj) {
        long time = System.currentTimeMillis() - CommonUtils.startTime;
        System.out.println(getThreadName() + " | " + time + " | value = " + obj);
    }

    public static void d(Object obj) {
        System.out.println(getThreadName() + " | debug = " + obj);
    }

    public static void dt(Object obj) {
        long time = System.currentTimeMillis() - CommonUtils.startTime;
        System.out.println(getThreadName() + " | " + time + " | debug = " + obj);
    }

    public static void e(Object obj) {
        System.out.println(getThreadName() + " | error = " + obj);
    }

    public static void v(Object obj) {
        System.out.println(getThreadName() + " | " + obj);
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
