package kr.co.swadpia.config.aspect.common;

public class TransactionContext {
    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void set(String value) {
        context.set(value);
    }

    public static String get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}