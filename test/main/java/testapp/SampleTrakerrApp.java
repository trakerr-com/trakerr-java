package testapp;

import io.trakerr.client.*;
import io.trakerr.ApiException;
//import io.trakerr.ApiResponse; For synchronized calls.
import io.trakerr.model.AppEvent;

import io.trakerr.model.CustomData;
import io.trakerr.model.CustomStringData;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.concurrent.*;

public class SampleTrakerrApp {
    private static Logger logger = Logger.getLogger(SampleTrakerrApp.class.getName());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String api_key = "<api-key>";

        if (args.length > 0 && api_key == "<api-key>")
            api_key = args[0];

        // Option-1: Use log4j
        logger.error("This is a test log4j exception.", new Exception("Test log4j exception."));

        // create a client
        TrakerrClient client = new TrakerrClient(api_key, "1.0", "development");

        
        // Option-2: Throw exception.
        try {
            throw new Exception("This is a test exception.");
        } catch(Exception e) {
            client.sendExceptionAsync(AppEvent.LogLevelEnum.WARNING, null, e, null);
            System.out.println("Test exception sent.");
        }


        // Option-3: Send an error with custom data
        try {
            throw new Exception("This is a test exception.");
        } catch(Exception e) {
            AppEvent errevnt = client.createAppEvent(AppEvent.LogLevelEnum.FATAL, null, e);
            errevnt.eventUser("jill@trakerr.io");
            errevnt.eventSession("20");

            try {
                client.sendEventAsync(errevnt,null);
            } catch (ApiException senderr) {
                senderr.printStackTrace();
            }
            System.out.println("Test custom exception sent.");
        }

        // Option-4: Send an event (including non-exceptions) manually.
        AppEvent event = client.createAppEvent(AppEvent.LogLevelEnum.INFO, "User got to this state.","System.Exception", "Some message");
        event.eventUser("john@trakerr.io");
        event.eventSession("17");
        event.setContextOperationTimeMillis(1000L);

        CustomStringData msg = new CustomStringData();
        msg.customData1("I'm a custom string!");

        event.customProperties(new CustomData().stringData(msg));
        try {
            client.sendEventAsync(event, null);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.readLine();
        } catch (IOException e){
            //user input wait gone wrong.
        }
        System.exit(0);
    }
}
