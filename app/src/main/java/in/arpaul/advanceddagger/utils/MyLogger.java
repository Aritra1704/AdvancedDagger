package in.arpaul.advanceddagger.utils;

import com.arpaul.utilitieslib.LogUtils;

public class MyLogger {
    private LogUtils logUtils;

    public MyLogger(boolean isEnabled) {
        logUtils = new LogUtils(isEnabled);
    }

    public void d(String tag, String message) {
        logUtils.debugLog(tag, message);
    }
}
