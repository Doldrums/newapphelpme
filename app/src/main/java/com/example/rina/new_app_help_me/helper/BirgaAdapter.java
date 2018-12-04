package com.example.rina.new_app_help_me.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rina.new_app_help_me.R;
import com.example.rina.new_app_help_me.models.Birga;

import java.util.List;

public class BirgaAdapter extends RecyclerView.Adapter<BirgaAdapter.ViewHolder> {
    private List<Birga> messages;
    private Context mCtx;

    public BirgaAdapter(List<Birga> messages, Context mCtx) {
        this.messages = messages;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_messages, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BirgaAdapter.ViewHolder holder, int position) {
        final Birga message = messages.get(position);
        holder.textViewName.setText(message.getFrom());
        holder.textViewTitle.setText(message.getTitle());
        holder.textViewMessage.setText(message.getBirga());
        holder.textViewTime.setText(message.getSent());
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewTitle;
        public TextView textViewMessage;
        public TextView textViewTime;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewMessage = (TextView) itemView.findViewById(R.id.textViewMessage);
            textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
        }
    }

}
