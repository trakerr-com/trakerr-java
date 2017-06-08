package client.unittest;

import io.trakerr.client.TrakerrClient;
import io.trakerr.model.AppEvent;
import io.trakerr.model.CustomData;
import io.trakerr.model.CustomStringData;
import org.junit.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rvm on 6/7/2017.
 */
public class TrakerrClientTest {
    static TrakerrClient tc;

    @BeforeClass
    public static void setUp() throws Exception {
        tc = new TrakerrClient("898152e031aadc285c3d84aeeb3c1e386735434729425", "Java " + System.currentTimeMillis(), "CICD Tests");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        tc.shutdown(false);
    }

    /*@Test
    public void createAppEvent() throws Exception {
    }

    @Test
    public void createAppEventThrowable() throws Exception {
    }

    @Test
    public void sendException() throws Exception {
    }

    @Test
    public void sendExceptionAsync() throws Exception {
    }

    @Test
    public void sendEvent() throws Exception {
    }// fill these out later*/

    @Test
    public void sendEventAsync() throws Exception {
        //debug
        AppEvent appevent = tc.createAppEvent(AppEvent.LogLevelEnum.DEBUG, "debug",
                "Debug", "Test Debug");
        appevent.setContextAppBrowser("Chrome");
        appevent.setContextAppBrowserVersion("67.x");

        appevent.setEventUser("john@traker.io");
        appevent.setEventSession("6");

        appevent.setContextOperationTimeMillis(2000L);
        appevent.contextDevice("pc");
        appevent.contextAppSku("lenovo laptop");
        ArrayList<String> tags = new ArrayList<>();
        tags.add("client");
        tags.add("frontend");
        appevent.contextTags(tags);

        //Would throw ApiError if fail. If not thrown, Success.
        assertTrue(tc.sendEventAsync(appevent, null).isExecuted());

        // error
        try {
            throw new Exception("This is a test exception.");
        } catch(Exception e) {
            AppEvent errevnt = tc.createAppEvent(AppEvent.LogLevelEnum.ERROR, "error", e);
            errevnt.setContextAppBrowser("Chrome");
            errevnt.setContextAppBrowserVersion("67.x");

            errevnt.setEventUser("john@traker.io");
            errevnt.setEventSession("6");

            errevnt.setContextOperationTimeMillis(2000L);
            errevnt.contextDevice("pc");
            errevnt.contextAppSku("lenovo laptop");
            appevent.contextTags(tags);
            assertTrue(tc.sendEventAsync(errevnt,null).isExecuted());
        }

        // info
        AppEvent event = tc.createAppEvent(AppEvent.LogLevelEnum.INFO, "info","info", "Some message");
        event.setContextAppBrowser("Chrome");
        event.setContextAppBrowserVersion("67.x");

        event.setEventUser("john@traker.io");
        event.setEventSession("6");

        event.setContextOperationTimeMillis(2000L);
        event.contextDevice("pc");
        event.contextAppSku("lenovo laptop");
        appevent.contextTags(tags);
        assertTrue(tc.sendEventAsync(event, null).isExecuted());

        // info
        AppEvent warn = tc.createAppEvent(AppEvent.LogLevelEnum.WARNING, "warn","warning", "Some message");
        warn.setContextAppBrowser("Chrome");
        warn.setContextAppBrowserVersion("67.x");

        warn.setEventUser("john@traker.io");
        warn.setEventSession("6");

        warn.setContextOperationTimeMillis(2000L);
        warn.contextDevice("pc");
        warn.contextAppSku("lenovo laptop");
        appevent.contextTags(tags);
        assertTrue(tc.sendEventAsync(warn, null).isExecuted());

        // fatal
        try {
            throw new FileNotFoundException("File Error.");
        } catch(Exception e) {
            AppEvent fatalevent = tc.createAppEvent(AppEvent.LogLevelEnum.FATAL, "fatal", e);
            fatalevent.setContextAppBrowser("Chrome");
            fatalevent.setContextAppBrowserVersion("67.x");

            fatalevent.setEventUser("john@traker.io");
            fatalevent.setEventSession("6");

            fatalevent.setContextOperationTimeMillis(2000L);
            fatalevent.contextDevice("pc");
            fatalevent.contextAppSku("lenovo laptop");
            appevent.contextTags(tags);
            assertTrue(tc.sendEventAsync(fatalevent,null).isExecuted());
        }



    }
}