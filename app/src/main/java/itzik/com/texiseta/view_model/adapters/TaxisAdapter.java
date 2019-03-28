package itzik.com.texiseta.view_model.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import itzik.com.texiseta.R;
import itzik.com.texiseta.models.TaxisModel;
import itzik.com.texiseta.utils.UtilsDisplay;
import itzik.com.texiseta.utils.UtilsView;

public class TaxisAdapter extends RecyclerView.Adapter<TaxisAdapter.ViewHolder> {

    public static final String TAG = TaxisAdapter.class.getSimpleName();


    private final LayoutInflater layoutInflater;
    private List<TaxisModel> taxisList;

    public TaxisAdapter(Context context, List<TaxisModel> list){
        layoutInflater = LayoutInflater.from(context);
        this.taxisList = list;
    }


    /**
     *
     * @param viewGroup
     * @param viewType \\ if have type holder
     * @return create view holder first time not in cash
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View viewApp = layoutInflater.inflate(R.layout.item_taxis_holder,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(viewApp);
        return viewHolder;
    }


    /**
     *
     * @param viewHolder
     * @param position
     * bind view holder to recycler after create or  in cash
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        TaxisModel item = taxisList.get(position);
        Log.d(TAG, "onBindViewHolder: item? "+item+"\nposition? "+position);
        viewHolder.bindHolder(item.getTaxisName(),item.getTaxisEta());
    }


    public void changeDataItems(List<? extends TaxisModel> list){
        if(list!=null){
            if(taxisList == null){
                taxisList = new ArrayList<>();
            }
            taxisList.clear();
            taxisList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return taxisList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout container;
        private ImageView iconView;
        private TextView nameTaxisView;
        private TextView etaView;

        public ViewHolder (View view){
            super(view);
            container = (RelativeLayout)view.findViewById(R.id.container);
            iconView = (ImageView) view.findViewById(R.id.icon);
            nameTaxisView = (TextView)view.findViewById(R.id.taxis_name);
            nameTaxisView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
            etaView = (TextView)view.findViewById(R.id.eta);
            etaView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
            updateLayoutView();
        }

        public void bindHolder(String name,int eta){
           // update view
           nameTaxisView.setText(name);
           etaView.setText(String.valueOf(eta)+"m");
            Log.d(TAG, "bindHolder: ");
        }

        private void updateLayoutView(){
            RelativeLayout.LayoutParams param = null;
            int h,w;
            Point screenSize = UtilsDisplay.getScreenSizeVars(itemView.getContext());
            Log.d(TAG, "updateHolder: container height?  "+screenSize.y);
            h = (int)(screenSize.y*0.9f);
            param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    h);
            container.setLayoutParams(param);

            param = new RelativeLayout.LayoutParams((int)(screenSize.x*0.15f),
                    (int)(screenSize.x*0.15f));
            param.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            param.addRule(RelativeLayout.CENTER_VERTICAL|RelativeLayout.CENTER_HORIZONTAL);
            param.setMargins((int)(screenSize.x*0.1f),0,0,0);
            iconView.setLayoutParams(param);
            iconView.setImageResource(R.drawable.blue_square);

            w =  (int)(screenSize.x*0.4f);
            param = new RelativeLayout.LayoutParams(w, ViewGroup.LayoutParams.WRAP_CONTENT);
            param.addRule(RelativeLayout.RIGHT_OF,iconView.getId());
            param.addRule(RelativeLayout.CENTER_VERTICAL | RelativeLayout.CENTER_HORIZONTAL);
            param.setMargins((int)(screenSize.x*0.1f),0,0,0);
            nameTaxisView.setLayoutParams(param);

            w =  (int)(screenSize.x*0.2f);
            param = new RelativeLayout.LayoutParams(w, ViewGroup.LayoutParams.WRAP_CONTENT);
            param.addRule(RelativeLayout.CENTER_VERTICAL|RelativeLayout.CENTER_HORIZONTAL);
            param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            param.setMargins(0,0,(int)(screenSize.x*0.05f),0);
            etaView.setLayoutParams(param);

            View separator = new View(itemView.getContext());
            separator.setId(UtilsView.generateViewId());
            separator.setBackgroundColor(Color.BLUE);
            param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    1);
            param.addRule(RelativeLayout.ALIGN_LEFT,iconView.getId());
            param.addRule(RelativeLayout.ALIGN_RIGHT,etaView.getId());
            container.addView(separator,param);
            Log.d(TAG, "updateLayoutView: finish");
        }
    }
}
