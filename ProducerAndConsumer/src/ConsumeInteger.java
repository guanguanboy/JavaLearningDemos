public class ConsumeInteger extends Thread{
    //private HoldIntegerUnsynchronized sharedObject;

    private HoldIntegerSynchronized sharedObject;

    public ConsumeInteger( HoldIntegerSynchronized shared){
        super("ConsumeInteger");
        sharedObject = shared;
    }

    public void run(){
        int value;
        int sum = 0;

        do{
            try{
                Thread.sleep((int)(Math.random() * 3000));
            }catch (InterruptedException e){
                System.err.println(e.toString());
            }

            value = sharedObject.getSharedInt();
            sum += value;
        }while (value != 10);

        System.err.println(getName() + " retrieved values totaling: "
            + sum + "\nTerminating " + getName());
    }
}
