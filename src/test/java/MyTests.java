import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;
import com.rollbar.notifier.Rollbar;
import org.junit.jupiter.api.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyTests {

    private static String myHash = Utils.genHash(10);
    private static String test = null;
    private static Rollbar rollbar;

   @BeforeEach
   public void setup() {

       System.out.println("\nStarting setup() method before test starts\n");

       rollbar = Rollbar.init(withAccessToken(MyConfig.myToken)
               .environment(MyConfig.myENv)
               .codeVersion(MyConfig.myVersion)
               .person(new MyPersonProvider())
               .server(new ServerProvider())
               .enabled(true)
               .build());

       rollbar.info("Starting setup() method before test starts");

   }

   @AfterEach
   public void teardown() {

       try {
           rollbar.info("Close Rollbar Connection");
           rollbar.close(true);
       } catch (Exception e) {
           System.out.println("Can't close rollbar connection " +e.getMessage());
       }
   }

    @Test
    public void dividebyZeroError() {

        System.out.println("Running dividebyZeroError() test now");

        try
        {
            System.out.println("Try loop");
            int value = 1 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Exception " +e.getMessage());
            rollbar.error(e," Divide by Zero Error");

        } finally
        {
            try
            {
                rollbar.close(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        System.out.println("End test method");
    }
    @Test
    public void generatNullPointerError(){

        try
        {
            test.toString();

        } catch (NullPointerException e) {
            rollbar.error(e,"Null Pointer Exception");

        }
    }
    @Test
    public void filenotFoundError() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("myfile.txt");
        } catch (FileNotFoundException e) {
            rollbar.error(e,"File not found Exception " +e.getMessage());
            e.printStackTrace();
        }
    }
    @Test
    public void arrayOutOfBoundsError() {

        int myArray[] = { 1,2,3 };

        try {
            int myValue = myArray[3];
            System.out.println(myValue);
        } catch (ArrayIndexOutOfBoundsException e) {
            rollbar.critical(e," Out of bounds array exception " +e.getMessage().toString());
        }
    }
    @Test
    public void generateRandomError() {

        String myHash = Utils.genHash(10);

        System.out.println("Calling generateRandomError()  method");
        rollbar.info(myHash+ " Information Message");
    }
    @Test
    public void unhandledException() {

        System.out.println("Calling unhandledException() test");
        int quotient = 1 / 0;
        System.out.println("Quotient: " +quotient);

    }
}
