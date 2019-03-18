import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class IceCream {
    private static Random rand = new Random(47);
    static final String[] FLAVORS = {
            "Chocolate", "Strawbeery", "Vanilla Fudge Swirl",
            "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };

    /*
    在flavorSet方法中返回了一个数组，返回一个数组与返回其它任何对象（实质上是返回引用）
    没有什么区别，数组是在flavorSet方法中创建，还是在别的地方被创建，这一点并不重要。
    当使用完毕后，垃圾回收器负责清理数组，而只要还使用它，此数组就会一直存在。
     */
    public static String[] flavorSet(int n){
        if (n > FLAVORS.length){
            throw new IllegalArgumentException("Set too big");
        }

        String[] results = new String[n];
        boolean[] picked = new boolean[FLAVORS.length];
        for(int i = 0; i < n; i++){
            int t;
            do {
                t = rand.nextInt(FLAVORS.length);
            }while (picked[t]); //这个循环的目的是寻找一个未被pick过的味道
            picked[t] = true;
            results[i] = FLAVORS[t];
        }

        return results;
    }

    public static void main(String[] args){
        //挑选其次，每次挑选3个味道
        for(int i = 0; i < 7; i++){
            System.out.println(Arrays.toString(flavorSet(3)));
        }
    }
}
