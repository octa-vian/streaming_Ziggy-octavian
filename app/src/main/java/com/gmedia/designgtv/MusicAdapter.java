package com.gmedia.designgtv;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>  {

    private Context mContext;
    private List<MusicModel> musicModels;

    public MusicAdapter(Context mContext,List<MusicModel> musicModels) {
        this.mContext = mContext;
        this.musicModels = musicModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MusicModel m= musicModels.get(position);
        holder.imgMusic.setImageResource(m.getDrawable());
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

