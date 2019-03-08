public class ThreadTest2 implements Runnable{
    @Override
    public synchronized void run() {
        for (int i=0; i<10; i++){  //这里也可以使用块同步，使用synchronized(this)将for循环包含起来
            System.out.println(" " + i);
            try {
                Thread.sleep(30);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Runnable r = new ThreadTest2();

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
    }
}
