
import com.rollbar.api.payload.data.Person;
import com.rollbar.notifier.provider.Provider;

import java.util.Random;

public class MyPersonProvider implements Provider<Person> {

    // @Override
    public Person provide() {

        int max = 20;
        int min = 1;
        Random rand = new Random();
        int randomNumId = rand.nextInt((max - min) + 1) + min;

        //   System.out.println(randomNum);
        Person command = null;
        command = new Person.Builder().id(String.valueOf(randomNumId)).build();
        return command;

    }
}