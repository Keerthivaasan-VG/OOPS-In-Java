class OddThread extends Thread {
    private int n;

    public OddThread(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println("Odd numbers:");
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
                try {
                    Thread.sleep(100); // simulate delay
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
}

class EvenThread extends Thread {
    private int n;

    public EvenThread(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println("Even numbers:");
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
}

public class OddEvenThreads {
    public static void main(String[] args) {
        int range = 20; // You can change the range as needed

        OddThread odd = new OddThread(range);
        EvenThread even = new EvenThread(range);

        odd.start();
        even.start();

        try {
            odd.join();
            even.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Both threads have completed execution.");
    }
}
