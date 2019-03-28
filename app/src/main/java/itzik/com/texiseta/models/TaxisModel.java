package itzik.com.texiseta.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import itzik.com.texiseta.TaxisApp;

public class TaxisModel implements Parcelable{

    public static final String TAG = TaxisApp.class.getSimpleName();


    private String name;
    private int eta;

    public TaxisModel(String name,int eta){
        this.name = name;
        this.eta = eta;
    }

    protected TaxisModel(Parcel in) {
        name = in.readString();
        eta = in.readInt();
        Log.d(TAG, String.format("TaxisModel: name? %s\teta? %d",name,eta));
    }

    @Override
    public String toString(){
        String desc = String.format("TaxisModel desc: name? %s\teta? %d",name,eta);
        return desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(name!=null){
            dest.writeString(name);
        }
        dest.writeInt(eta);
    }


    public String getTaxisName(){
        return name;
    }

    public int getTaxisEta(){
        return eta;
    }

    public static final Creator<TaxisModel> CREATOR = new Creator<TaxisModel>() {
        @Override
        public TaxisModel createFromParcel(Parcel in) {
            return new TaxisModel(in);
        }

        @Override
        public TaxisModel[] newArray(int size) {
            return new TaxisModel[size];
        }
    };
}
