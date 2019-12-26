package com.gmedia.designgtv;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmedia.designgtv.model.KategoriModel;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder> {
    private List<KategoriModel> kategoriModels;
    Context context;
    int selectedPosition=-1;


    public KategoriAdapter(Context mContext, List<KategoriModel> kategoriModels){
        this.kategoriModels = kategoriModels;
        this.context = mContext;
    }

    @NonNull
    @Override
    public KategoriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final KategoriAdapter.ViewHolder holder, final int position) {
        holder.btnKategori.setText(kategoriModels.get(position).getNama());
        if(selectedPosition==position)
            holder.btnKategori.setTextColor(Color.parseColor("#fff"));
        else
            holder.btnKategori.setTextColor(Color.parseColor("#f0f0f0"));

        holder.llKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=position;
                notifyDataSetChanged();
                Toast.makeText(context, "Position "+position, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return kategoriModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnKategori;
        private LinearLayout llKategori;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnKategori = itemView.findViewById(R.id.btn_kategori);
            llKategori = itemView.findViewById(R.id.ll_kategori);
        }
    }
}
