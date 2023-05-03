import java.util.ArrayList;
import java.util.function.Consumer;

interface LambdaFunction{
     String Lambda(String b);
}
public class Lambda {
    public static void main(String[] args) {
        Consumer<Integer> myLambda = (n) -> {
            System.out.println(n);
        };
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.forEach(myLambda);
        LambdaFunction ab = (n)->{
            return "The value is "+n;
        };
        System.out.println(ab.Lambda("Ramesh"));
    }
}