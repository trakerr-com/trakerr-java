package testapp;

import io.trakerr.client.*;
import io.trakerr.ApiException;
import io.trakerr.ApiResponse;
import io.trakerr.model.AppEvent;

import io.trakerr.model.CustomData;
import io.trakerr.model.CustomStringData;
import org.apache.log4j.Logger;

import java.util.concurrent.*;

public class SampleTrakerrApp {
    private static Logger logger = Logger.getLogger(SampleTrakerrApp.class.getName());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String api_key = "<your api key>";

        if (args.length > 0 && api_key == "<your api key>")
            api_key = args[0];

        // Option-1: Use log4j
        logger.error("This is a test log4j exception.", new Exception("Test log4j exception."));

        // create a client
        TrakerrClient client = new TrakerrClient(api_key, "1.0", "development");

        
        // Option-2: Throw exception.
        try {
            throw new Exception("This is a test exception.");
        } catch(Exception e) {
            client.sendException(AppEvent.LogLevelEnum.FATAL, null, e);
            System.out.println("Test exception sent.");
        }

        // Option-3: Send an event (including non-exceptions) manually.
        AppEvent event = client.createAppEvent(AppEvent.LogLevelEnum.INFO, "User got to this state.","System.Exception", "Some message");
        try {
            ApiResponse<Void> response = client.sendEvent(event);

            System.out.println("Sent event: " + response.getStatusCode() + ", data: " + response.toString());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
