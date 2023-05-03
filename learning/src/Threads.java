public class Threads extends Thread{
    public static void main(String[] args) {
        Threads thread = new Threads();
        thread.start();
        while (thread.isAlive()){
            System.out.println("Thread is working!");
        }
        System.out.println("Thread work done");
    }
    public void run(){
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}