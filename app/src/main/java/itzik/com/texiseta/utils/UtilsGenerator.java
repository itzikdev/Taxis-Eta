package itzik.com.texiseta.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import itzik.com.texiseta.models.TaxisModel;

public class UtilsGenerator {

    public static final String TAG = UtilsGenerator.class.getSimpleName();

    private static final  String[] taxisName = new String[]{"Castle","Shekem","Habima","Gordon","Azrieli","Hadera"};

    public static List<TaxisModel> createMockTaxisItems(){
        int min = 1,max = 100,timeRandom;
        Random r = new Random();
        ArrayList<TaxisModel> list = new ArrayList<>();
        for(int i = 0 ; i<taxisName.length ; i++){
            timeRandom = r.nextInt(max - min + 1) + min;
            TaxisModel taxisModel = new TaxisModel(taxisName[i],timeRandom);
            Log.d(TAG, String.format("createMockTaxisItems: taxis info ?\n%s ",taxisModel));
            list.add(taxisModel);
        }
        return list;
    }

}
