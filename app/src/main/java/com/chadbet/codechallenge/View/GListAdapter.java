package com.chadbet.codechallenge.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chadbet.codechallenge.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GListAdapter extends RecyclerView.Adapter<GListAdapter.ViewHolder>{

    private Context context;
    private List<GObject> gObjectList;

    public GListAdapter(Context context, List<GObject> list) {
        this.gObjectList = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_g_query, parent, false);
        return new ViewHolder(context, rootView);
    }

    @Override
    public void onBindViewHolder(GListAdapter.ViewHolder holder, int position) {
        final GObject gObject = gObjectList.get(position);
        final String name = gObject.getName();
//        final String city = gObject.getCity();
//        final String state = gObject.getState();
        final String endDate = gObject.getEndDate();
        holder.tvName.setText(name);
//        holder.tvCity.setText(city);
//        holder.tvState.setText(state);
        holder.tvEndDate.setText(endDate);
        fetchIcon(gObject.iconURL, holder);
    }

    @Override
    public int getItemCount() {
        return gObjectList.size();
    }

    private void fetchIcon(String url, GListAdapter.ViewHolder holder) {
        Picasso.with(context).load(url).into(holder.ivIcon);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        private TextView tvName;
        private TextView tvCity;
        private TextView tvState;
        private TextView tvEndDate;
        private ImageView ivIcon;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            this.tvState = (TextView) itemView.findViewById(R.id.row_tv_city_state);
            this.tvEndDate = (TextView) itemView.findViewById(R.id.row_tv_end_date);
            this.tvName = (TextView) itemView.findViewById(R.id.row_tv_name);
            this.ivIcon = (ImageView) itemView.findViewById(R.id.row_icon);
        }

    }
}
