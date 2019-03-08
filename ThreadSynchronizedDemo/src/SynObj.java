public class SynObj {
    public synchronized void showA(){
        System.out.println("showA..");

        try{
            Thread.sleep(3000);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void showB()
    {
        synchronized (this){
            System.out.println("ShowB..");
        }
    }

    public void showC(){
        String s="1";
        synchronized (s){
            System.out.println("showC..");
        }
    }
}

/*
这段代码的打印结果是，showA…..showC…..会很快打印出来，showB…..会隔一段时间才打印出来，那么showB为什么不能像showC那样很快被调用呢？

　　在启动线程1调用方法A后，接着会让线程1休眠3秒钟，这时会调用方法C，注意到方法C这里用synchronized进行加锁，这里锁的对象是s这个字符串对象。但是方法B则不同，是用当前对象this进行加锁，注意到方法A直接在方法上加synchronized，这个加锁的对象是什么呢？显然，这两个方法用的是一把锁。

　　*由这样的结果，我们就知道这样同步方法是用什么加锁的了，由于线程1在休眠，这时锁还没释放，导致线程2只有在3秒之后才能调用方法B，由此，可知两种加锁机制用的是同一个锁对象，即当前对象。
　　另外，同步方法直接在方法上加synchronized实现加锁，同步代码块则在方法内部加锁，很明显，同步方法锁的范围比较大，而同步代码块范围要小点，一般同步的范围越大，性能就越差，一般需要加锁进行同步的时候，肯定是范围越小越好，这样性能更好*。
 */

/**
 *    　　一、当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
 *      二、然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
 *      三、尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
 */