package john.bryce;

public class Helper {

    public static boolean isPrime(long num) {

        for(long i = 2; i < num; i++) {
            if(num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
