public class ThreadFive {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(() -> {
                long tid = Thread.currentThread().getId();
                System.out.println("Thread " + tid + " está sendo executada.");
            });
            thread.start();
        }
    }
}