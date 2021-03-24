
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class RollbarAPI {

    private static final String command = " curl https://api.rollbar.com/api/1/status/ping";
    private static final String myaccesstoken = "<post_server_item>";
    private static final String myenv = "DEV";
    private static final String buildNum = "1.9";
    private static final String myUser = "ianianf";
    private static final String myExpectedResponse = "pong";

    private String urlBase = "https://api.rollbar.com/api/1/status/ping";
    private String deployURL = "https://api.rollbar.com/api/1/deploy";

    private static final String mysecondcommand = "curl https://api.rollbar.com/api/1/deploy/ -F access_token=" + myaccesstoken +
            " -F environment=" + myenv +
            " -F revision=" + buildNum +
            " -F local_username=" + myUser;

   /* public static boolean checkRollbarAPI() {

        boolean isAPIUp = false;

        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String response = br.readLine();
            if (response.equals(myExpectedResponse)) {

                System.out.println("Rollbar API test Passed.");
                isAPIUp = true;
            } else {
                System.out.println("Rollbar API test failed");
            }
            //  System.out.println(response);
        } catch (IOException e) {
            System.out.println("Can't check Rollbar API status: " +e.getMessage());
            e.printStackTrace();
        }
        return isAPIUp;
    }*/
    public boolean RollbarAPIcheck() throws Exception {

        //
        // Return a string with the response JSON 
        //
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;
        boolean value = false;
        String line = null;

        try {

          //  String fullUrl = String.format(urlBase);
            // allow multiple TLS versions
            System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

            // create the HttpURLConnection
            url = new URL(urlBase);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

         //   connection.addRequestProperty("X-Rollbar-Access-Token", token);
            connection.addRequestProperty("Content-Type", "application/json; charset=utf-8");

            // just want to do an HTTP GET here
            connection.setRequestMethod("GET");

            // uncomment this if you want to write output to this url
            connection.setDoOutput(true);

            // give it 15 seconds to respond
            connection.setReadTimeout(15 * 1000);
            connection.connect();

            int responseCode = connection.getResponseCode();
           // System.out.println("Response Code of the object is " +responseCode);

            if (responseCode == 200) {

                System.out.println("Rollbar API is up");
                value = true;
            } else
            {
                System.out.println("Rollbar API is down");
            }

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.defaultCharset()));
            stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return value;
    }

    /*public static void getOccurrences(String token, String env, String vers) {

      System.out.println("Calling getOccurrences() method now.");

       try {

           // allow multiple TLS versions
           System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

           // create the HttpURLConnection
           url = new URL(deployURL);
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           connection.addRequestProperty("X-Rollbar-Access-Token", token);
           connection.addRequestProperty("environment", env);
           connection.addRequestProperty("revision", vers);
           connection.addRequestProperty("rollbar_username", "ianianf");
           connection.addRequestProperty("local_username", "iflanagan");
           connection.addRequestProperty("comment", "Deployment from Java REST code");
           connection.addRequestProperty("Content-Type", "application/json; charset=utf-8");
           connection.addRequestProperty("Content-Length", "1000");
           // connection.setRequestProperty("Content-Length", "1000"); // Content-Length:0


           // connection.setRequestProperty("Content-Length", "20");
           // just want to do an HTTP GET here
           connection.setRequestMethod("POST");

           // uncomment this if you want to write output to this url
           connection.setDoOutput(true);

           // give it 15 seconds to respond
           connection.setReadTimeout(15 * 1000);
           connection.connect();

           int responseCode = connection.getResponseCode();
           System.out.println("Code is: " +responseCode);
           //    System.out.println("Rollbar Deploy response is : " +connection.getResponseMessage());

           if (responseCode == 200) {
               System.out.println("Deploy was successful");
               value = true;
           } else {
               System.out.println("Deploy was unsuccessful");
           }
           // read the output from the server
           reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.defaultCharset()));
           stringBuilder = new StringBuilder();

           while ((line = reader.readLine()) != null) {
               stringBuilder.append(line + "\n");
           }

           String resp = stringBuilder.toString();
           System.out.println("\nRollbar API response is: from deploy is: " +resp);

        } catch (Exception e ) {
           System.out.println("Can't Call getOccurrences() method now." +e.getMessage());
            e.printStackTrace();
        } finally {

           // close the reader; this can throw an exception too, so
           // wrap it in another try/catch block.
           if (reader != null) {
               try {
                   reader.close();
               } catch (IOException io) {
                   io.printStackTrace();
               }
           }
       }
    }
*/
    public boolean deploy(String token, String env, String vers) {

        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;
        boolean value = false;
        String line = null;

        try
        {
            // allow multiple TLS versions
            System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

            // create the HttpURLConnection
            url = new URL(deployURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("X-Rollbar-Access-Token", token);
            connection.addRequestProperty("environment", env);
            connection.addRequestProperty("revision", vers);
            connection.addRequestProperty("rollbar_username", "ianianf");
            connection.addRequestProperty("local_username", "iflanagan");
            connection.addRequestProperty("comment", "Deployment from Java REST code");
            connection.addRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.addRequestProperty("Content-Length", "1000");
           // connection.setRequestProperty("Content-Length", "1000"); // Content-Length:0


           // connection.setRequestProperty("Content-Length", "20");
            // just want to do an HTTP GET here
            connection.setRequestMethod("POST");

            // uncomment this if you want to write output to this url
            connection.setDoOutput(true);

            // give it 15 seconds to respond
            connection.setReadTimeout(15 * 1000);
            connection.connect();

            int responseCode = connection.getResponseCode();
            System.out.println("Code is: " +responseCode);
        //    System.out.println("Rollbar Deploy response is : " +connection.getResponseMessage());

            if (responseCode == 200) {
                System.out.println("Deploy was successful");
                value = true;
            } else {
                System.out.println("Deploy was unsuccessful");
            }
            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.defaultCharset()));
            stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            String resp = stringBuilder.toString();
            System.out.println("\nRollbar API response is: from deploy is: " +resp);

        } catch (Exception e ) {
            System.out.println("Error in deploy " +e.getMessage());

        }
        finally {

            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        }
        return value;
    }
}
