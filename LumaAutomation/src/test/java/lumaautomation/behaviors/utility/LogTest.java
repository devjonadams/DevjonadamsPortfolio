package lumaautomation.behaviors.utility;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/***
 * Simple helper function that logs to the console.
 * Note: Console Logging must happen within the thread of performables that it is called from.
 * Otherwise, the logging will happen out of sync with the test case.
 */
public class LogTest {

    /***
     * Log to Console as a performable.
     * Note: This log will appear as a "Step" of behavior in the Serenity Report.
     * @param inLogText The text to log to the console.
     * @return Performable.
     */
    public static Performable Log(String inLogText) {

        return Task.where("[{0} " + String.format("Test Log]: %s", inLogText),
                actor ->  {
                    System.out.printf("[Test]: " + inLogText + "%n");
                });
    }
}
