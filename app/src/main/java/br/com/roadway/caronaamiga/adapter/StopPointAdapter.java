package br.com.roadway.caronaamiga.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import br.com.roadway.caronaamiga.R;
import br.com.roadway.caronaamiga.controller.MainActivity;
import br.com.roadway.caronaamiga.controller.RideFinalMessageActivity;
import br.com.roadway.caronaamiga.model.StopPoint;

public class StopPointAdapter extends RecyclerView.Adapter<StopPointAdapter.StopPointViewHolder> {

    private List<StopPoint> stopPointDataSet;
    private Context mContext;
    private String rideType;

    public StopPointAdapter(List<StopPoint> stopPointDataSet, Context mContext, String rideType) {
        this.stopPointDataSet = stopPointDataSet;
        this.mContext = mContext;
        this.rideType = rideType;
    }

    @Override
    public StopPointViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.stop_point_item, parent, false);
        return new StopPointViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StopPointViewHolder holder, int position) {
        StopPoint mStopPoint = stopPointDataSet.get(position);

        Glide.with(mContext)
                .load(mStopPoint.getImageResourceId())
                .into(holder.stopPointImage);

        holder.stopPointLayout.setText(mStopPoint.getTitle());

        holder.stopPointLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, RideFinalMessageActivity.class);
                mIntent.putExtra(MainActivity.RIDE_KEY, rideType);
                mContext.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stopPointDataSet.size();
    }

    public class StopPointViewHolder extends RecyclerView.ViewHolder {

        Button stopPointLayout;
        ImageView stopPointImage;

        public StopPointViewHolder(View itemView) {
            super(itemView);

            stopPointLayout = (Button) itemView.findViewById(R.id.choose_stop_point_button);
            stopPointImage = (ImageView) itemView.findViewById(R.id.choose_stop_point_image);
        }
    }
}
