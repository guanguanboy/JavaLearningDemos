public class ProduceInteger extends Thread{
    //private HoldIntegerUnsynchronized sharedObject;

    private HoldIntegerSynchronized sharedObject;
    public ProduceInteger(HoldIntegerSynchronized shared){
        super("ProduceInteger"); //指定线程的名字
        sharedObject = shared;
    }

    @Override
    public void run() {
        for (int count = 1; count <= 10; count++)
        {
            try {
                Thread.sleep((int)(Math.random() * 3000));
            }catch (InterruptedException e){
                System.err.println(e.toString());
            }

            sharedObject.setSharedInt(count);
        }

        System.err.println(getName() + " finished producing values"
        + "\nTerminating " + getName());
    }
}
