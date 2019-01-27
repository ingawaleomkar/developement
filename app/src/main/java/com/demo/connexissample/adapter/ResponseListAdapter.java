package com.demo.connexissample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.connexissample.R;
import com.demo.connexissample.model.ResponseList;

import java.util.List;

/**
 * Created by samrat on 22/1/19.
 */

public class ResponseListAdapter extends RecyclerView.Adapter<ResponseListAdapter.RecyclerViewHolder> {

    private Context context;
    private List<ResponseList> responseList;


    public ResponseListAdapter(Context context, List<ResponseList> responseList) {
        this.context = context;
        this.responseList = responseList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.textView.setText(responseList.get(position).getFirstName());


        Glide.with(context).load(responseList.get(position).getAvatar())
                .thumbnail(0.5f)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_avatar);
            textView = itemView.findViewById(R.id.text_name);
        }
    }
}
