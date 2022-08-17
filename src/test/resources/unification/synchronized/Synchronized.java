public class Example {
    public int getValue(Object obj) {
        synchronized (obj) {
        }
        return 0;
    }
}