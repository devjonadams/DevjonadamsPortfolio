package lumaautomation.behaviors.utility;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class LogTest {

    public static Performable Log(String inLogText) {

        return Task.where("[{0} " + String.format("Test Log]: %s", inLogText),
                actor ->  {
                    System.out.printf("[Test]: " + inLogText + "%n");
                });
    }
}
