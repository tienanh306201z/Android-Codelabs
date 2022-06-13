package com.alva.emailui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alva.emailui.R;
import com.alva.emailui.models.Email;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<Email> items;

    public EmailAdapter(Context mContext, ArrayList<Email> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.list_tile, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Email item = items.get(position);

        holder.title.setText(item.getTitle());
        holder.description1.setText(item.getDescription1());
        holder.description2.setText(item.getDescription2());
        holder.time.setText(item.getTime());
        holder.textButton.setText(item.getAvatarChar());
        holder.textButton.setBackgroundColor(Color.parseColor(item.getAvatarColor()));

        holder.itemView.setTag(item);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public MaterialButton textButton;
        public AppCompatTextView title, description1, description2, time;

        public ViewHolder(View itemView) {
            super(itemView);
            textButton = itemView.findViewById(R.id.button_text);
            title = itemView.findViewById(R.id.title);
            description1 = itemView.findViewById(R.id.description_1);
            description2 = itemView.findViewById(R.id.description_2);
            time = itemView.findViewById(R.id.time_view);
        }
    }
}