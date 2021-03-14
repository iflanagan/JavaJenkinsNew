import java.util.ArrayList;
import java.util.Random;

import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;
import com.rollbar.notifier.Rollbar;

public class Main {

    public static final String myToken = "<";

    public static void main(String[] args) throws Exception {

        String myHash = genHash(10);

      //  1455882dc9324564af9bc3e59aca1d77

       /*
        Rollbar rollbar = Rollbar.init(withAccessToken("899c43725fc047f6a06d5319548f5e5d").build());
        rollbar.log(myHash+ " Hello, Rollbar from main()");
        rollbar.error(myHash+ " Error now");
        rollbar.close(true);*/


    }
    public static void generateNullpointerException() throws NullPointerException {
        String test = null;
        test.toString();
    }

    public static void unhandledErrors() {

        int quotient = 0;

        try {

            quotient = 1 / 0;

        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }


    public static String genHash(int length) {

        // create a string of lowercase characters and numbers
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        // concatentate  strings
        String alphaNumeric =  lowerAlphabet + numbers;
        // create random string builder object
        StringBuilder sb = new StringBuilder();
        // create an object of Random class
        Random random = new Random();

        for(int i = 0; i < length; i++) {
            // generate random index number
            int index = random.nextInt(alphaNumeric.length());
            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);
            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        System.out.println("Random String is: " + randomString);
        return randomString;

    }

    public static void genArrayOutofBounds() {

        System.out.println("Calling genArrayOutofBounds() method");
        ArrayList<String> lis = new ArrayList<>();
        lis.add("My");
        lis.add("Name");
        System.out.println(lis.get(2));
    }
}
