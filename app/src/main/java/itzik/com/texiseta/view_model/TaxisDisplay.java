package itzik.com.texiseta.view_model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;
import itzik.com.texiseta.R;
import itzik.com.texiseta.TaxisApp;
import itzik.com.texiseta.models.TaxisModel;
import itzik.com.texiseta.utils.UtilsGenerator;
import itzik.com.texiseta.view_model.adapters.TaxisAdapter;

public class TaxisDisplay extends AppCompatActivity {

    public static final String TAG = TaxisDisplay.class.getSimpleName();


    private Unbinder target;
    private  SortbyEta sortEta;
    private TaxisAdapter taxisAdapter;
    private Disposable timer;
    private RecyclerView recyclerView;



    @BindView(R.id.main_frame)
     RelativeLayout main_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxis_display);
        target = ButterKnife.bind(this);
        initDisplay();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart:");
    }


    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause: ");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy:");
        super.onDestroy();
        timer.dispose();
        target.unbind();
    }

    private void initDisplay(){
        sortEta = new SortbyEta();
        addRecycleView();
        createAddAdapterAndInsert();
        createTimer();
    }

     private void createAddAdapterAndInsert(){
         List<TaxisModel>itemsTaxis = UtilsGenerator.createMockTaxisItems();
         Collections.sort(itemsTaxis,sortEta);
         taxisAdapter = new TaxisAdapter(this,itemsTaxis);
         recyclerView.setAdapter(taxisAdapter);
     }


     private void createTimer(){
        timer = Observable.interval(5000L, TimeUnit.MILLISECONDS)
                 .timeInterval()
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<Timed<Long>>() {
                     @Override
                     public void accept(@NonNull Timed<Long> longTimed) throws Exception {
                         // after 5 second interval
                          List<TaxisModel> list = UtilsGenerator.createMockTaxisItems();
                          Collections.sort(list,sortEta);
                          notifyToAdapter(Collections.unmodifiableList(list));
                         Log.d(TAG, "accept: longTimed? "+longTimed);
                     }
                 });
     }


     private void notifyToAdapter(List<TaxisModel> list){
        if(taxisAdapter!=null){
            taxisAdapter.changeDataItems(list);
            Log.d(TAG, "notifyToAdapter: ");
        }
     }


    private void addRecycleView(){
        recyclerView = new RecyclerView(this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RelativeLayout.LayoutParams PARAM  = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        main_display.addView(recyclerView,PARAM);
        Log.d(TAG, "addRecycleView:");
    }


    class SortbyEta implements Comparator<TaxisModel> {
        // Used for sorting in ascending order of
        // Eta number
        public int compare(TaxisModel l, TaxisModel r) {
            final int leftEta = l.getTaxisEta();
            final int rightEta = r.getTaxisEta();
            if(leftEta>rightEta){
                return 1;
            }else if(leftEta<rightEta){
                return -1;
            }else{
                return 0;
            }
        }
    }
}
