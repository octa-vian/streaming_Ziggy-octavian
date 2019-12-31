package com.gmedia.designgtv;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gmedia.designgtv.model.ItemModel;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>  {

    private Context mContext;
    private List<ItemModel> musicModels;

    public ItemAdapter(Context mContext, List<ItemModel> musicModels) {
        this.mContext = mContext;
        this.musicModels = musicModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ItemModel m= musicModels.get(position);

        Glide.with(mContext)
                .load(m.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder)
                .override(400, 400)
                .centerCrop()
                .transform(new RoundedCornersTransformation(30,0))
                .into(holder.imgMusic);
        holder.tvTitle.setText(m.getTitle());
    }

    @Override
    public int getItemCount() {
        return musicModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMusic;
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imgMusic = itemView.findViewById(R.id.img_usic);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
