package com.dildarkhan.tmpgroupca;

/**
 * <p>A chronometer to measure execution time.</p>
 *
 * Usage:
 * <pre><code>
 * Chronometer c = new Chronometer(myLogIdentifier);
 * ... // lines of code to measure
 * c.logElapsedTime();
 *
 * c.restart();
 * ... // lines of code to measure
 * c.logElapsedTime();
 * </code></pre>
 *
 */


import android.os.SystemClock;
import android.util.Log;

/**
 * Created by DILDARKHAN PATHAN
 * Email Id: m.dildarkhan@gmail.com
 */
public class Chronometer {
    private static final String TAG = "TimingHelper";

    private long startTime;
    private final String logIdentifier;

    public Chronometer(final String logIdentifier) {
        Log.d(TAG, logIdentifier + " :: Starting chronometer");
        this.logIdentifier = logIdentifier;
        startTime = getCurrentTime();
    }

    public void restart() {
        Log.d(TAG, logIdentifier + " :: Restarting chronometer");
        startTime = getCurrentTime();
    }

    public long getElapsedTime() {
        return getCurrentTime() - startTime;
    }

    public void logElapsedTime() {
        Log.i(TAG, logIdentifier + " :: " + getElapsedTime());
    }

    private long getCurrentTime() {
        return SystemClock.elapsedRealtime();
    }

}