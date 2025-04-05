package lumaautomation.engine;

public class EngineHelperData {

    public static final int VERY_SHORT_WAIT = 1;                    // 1 Second
    public static final int SHORT_WAIT = 3 * VERY_SHORT_WAIT;       // 3 Seconds
    public static final int NORMAL_WAIT = 20 * SHORT_WAIT;          // 1 Minute
    public static final int LONG_WAIT = 3 * NORMAL_WAIT;            // 3 Minutes
    public static final int MAX_WAIT = 5 * NORMAL_WAIT;             // 5 Minutes

    public static final int MAX_POLLING_TIME = LONG_WAIT;
    public static final int POLLING_INTERVAL = SHORT_WAIT;
}
