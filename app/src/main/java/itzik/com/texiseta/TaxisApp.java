package itzik.com.texiseta;

import android.app.Application;
import android.util.Log;

public class TaxisApp extends Application {

    public static final  String TAG = TaxisApp.class.getSimpleName();



    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate:");
    }


    @Override
    public void onTerminate() {
        Log.d(TAG, "onTerminate:");
        super.onTerminate();
    }
}
