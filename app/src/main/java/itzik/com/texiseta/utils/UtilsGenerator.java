package itzik.com.texiseta.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import itzik.com.texiseta.models.TaxisModel;

public class UtilsGenerator {

    public static final String TAG = UtilsGenerator.class.getSimpleName();

    public static List<TaxisModel> createMockTaxisItems(){
        int min = 1,max = 100,timeRandom;
        Random r = new Random();
        ArrayList<TaxisModel> list = new ArrayList<>();
        TaxisModel taxisModel= null;
        timeRandom = r.nextInt(max - min + 1) + min;
        Log.d(TAG, "createMockTaxisItems: random 1? "+ timeRandom);
        taxisModel = new TaxisModel("Castle",timeRandom);
        list.add(taxisModel);
        timeRandom = r.nextInt(max - min + 1) + min;
        Log.d(TAG, "createMockTaxisItems: random 2? "+ timeRandom);
        taxisModel = new TaxisModel("Shekem",timeRandom);
        list.add(taxisModel);
        timeRandom = r.nextInt(max - min + 1) + min;
        Log.d(TAG, "createMockTaxisItems: random 2? "+ timeRandom);
        taxisModel = new TaxisModel("Habima",timeRandom);
        list.add(taxisModel);
        timeRandom = r.nextInt(max - min + 1) + min;
        Log.d(TAG, "createMockTaxisItems: random 3? "+ timeRandom);
        taxisModel = new TaxisModel("Gordon",timeRandom);
        list.add(taxisModel);
        timeRandom = r.nextInt(max - min + 1) + min;
        Log.d(TAG, "createMockTaxisItems: random 4? "+ timeRandom);
        taxisModel = new TaxisModel("Azrieli",timeRandom);
        list.add(taxisModel);
        timeRandom = r.nextInt(max - min + 1) + min;
        Log.d(TAG, "createMockTaxisItems: random 5? "+ timeRandom);
        taxisModel = new TaxisModel("Hadera",timeRandom);
        list.add(taxisModel);
        return list;


    }

}
