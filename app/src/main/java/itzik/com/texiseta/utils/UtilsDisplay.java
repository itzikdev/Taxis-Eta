package itzik.com.texiseta.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class UtilsDisplay {

    public static final String TAG = UtilsDisplay.class.getSimpleName();

    public static Point getScreenSizeVars(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Log.d(TAG, "Screen Width ? " + size.x + " , Height ? " + size.y);
        return size;
    }
}
