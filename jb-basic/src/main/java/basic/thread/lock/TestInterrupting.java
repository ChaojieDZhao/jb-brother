package basic.thread.lock;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestInterrupting {
    private static ExecutorService service = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException{
        Future<?> f = service.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting: " + r.getClass().getName());
        f.cancel(true); //interrupts if running
        System.out.println("interrupted send to: " + r.getClass().getName());

    }
    public static void main(String[] args) throws Exception{
//        test(new SleepBlocked());
//        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(10);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }
}

class SleepBlocked implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println("Exiting SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream is;

    public IOBlocked(InputStream is) {
        this.is = is;
    }

    @Override
    public void run() {
        try {
            System.out.print("waiting for read:");
            is.read();
        } catch (IOException e) {
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted IO Blocked");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable {
    public SynchronizedBlocked() {
        new Thread(){
            @Override
            public void run() {
                f();
            }
        }.start();
    }

    public synchronized void f() {
        while(true) { //Never release lock
            Thread.yield();
        }
    }
    @Override
    public void run() {
        System.out.println("try to call f()");
        f();
        System.out.println("Exiting SynchronizedBlocked.run()");
    }


}