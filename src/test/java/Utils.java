import java.util.Random;

public class Utils {

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
}
