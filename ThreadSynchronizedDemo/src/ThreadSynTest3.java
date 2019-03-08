public class ThreadSynTest3 implements Runnable{
    @Override
    public void run() {
        for (int k=0;k<5;k++){
            System.out.println(Thread.currentThread().getName() + " : for loop :" + k);
        }

        synchronized (this){
            for (int k=0; k<5;k++){
                System.out.println(Thread.currentThread().getName()  + " : synchronized for loop : " + k);
            }
        }
    }

    public static void main(String[] args){
        Runnable r = new ThreadSynTest3();

        Thread t1 = new Thread(r, "t1_name");
        Thread t2 = new Thread(r, "t2_name");

        t1.start();
        t2.start();
    }
}
