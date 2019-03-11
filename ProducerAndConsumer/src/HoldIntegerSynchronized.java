public class HoldIntegerSynchronized {
    private int sharedInt = -1;
    private boolean writeable = true;

    public synchronized void setSharedInt(int value){
        while (false == writeable){

            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.err.println(Thread.currentThread().getName() +
                " setting sharedInt to " + value);

        sharedInt = value;

        writeable = false;

        notify();
    }

    public synchronized int getSharedInt(){
        while(true == writeable){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        writeable = true;

        notify();

        System.err.println(Thread.currentThread().getName() +
                " retrieving sharedInt value " + sharedInt);

        return sharedInt;
    }
}
