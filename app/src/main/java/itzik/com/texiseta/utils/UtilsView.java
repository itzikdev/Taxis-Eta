package itzik.com.texiseta.utils;

import android.os.Build;
import android.util.Log;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;

public class UtilsView {

    public static final String TAG = UtilsView.class.getSimpleName();

    private static final AtomicInteger viewIdGenerator = new AtomicInteger(15000000);

    public static int generateViewId(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return generateUniqueViewId();
        } else {
            return View.generateViewId();
        }
    }



    private static int generateUniqueViewId() {
        final int result = viewIdGenerator.get();
        while (true) {
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (viewIdGenerator.compareAndSet(result, newValue)) {
                break;
            }
        }
        Log.d(TAG, "generateUniqueViewId:   "+result);
        return result;
    }
}
