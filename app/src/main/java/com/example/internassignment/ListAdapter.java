package com.example.internassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    Context context;
    ArrayList<ListItem> list;

    public ListAdapter(Context context, ArrayList<ListItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

           holder._name.setText(list.get(position).getName());
           holder._status.setText(list.get(position).getStatus());
           holder._agency.setText(list.get(position).getAgency());
           holder._wiki.setText(list.get(position).getWiki());
        Picasso.get().load(list.get(position).getImage()).into(holder._image);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{

        TextView _name;
        TextView _status;
        TextView _agency;
        TextView _wiki;
        ImageView _image;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            _name = itemView.findViewById(R.id.itemName);
            _agency = itemView.findViewById(R.id.itemAgency);
            _image = itemView.findViewById(R.id.itemImage);
            _status = itemView.findViewById(R.id.itemStatus);
            _wiki = itemView.findViewById(R.id.itemWiki);

        }
    }
}
