package com.ImageUploader;

import android.content.Context;

import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private List<Upload> uploadList;
    private OnItemClickListener listener;

    public ImageAdapter(Context context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(view);
    }



    /***Bounds data with CardView***/
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Upload currentUpload=uploadList.get(position);
        Log.d("Uri",currentUpload.getImageURL().toString());
        holder.name.setText(currentUpload.getNameImage());
        Picasso.get().load(currentUpload.getImageURL()).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class ImageViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView name;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {

            super(itemView);
            name=itemView.findViewById(R.id.text_view_name);
            imageView=itemView.findViewById(R.id.image_view_upload);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {

            if(listener!=null)
            {
                //position of clicked item
                int position=getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION)
                {
                    listener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem edit=menu.add(Menu.NONE,1,1,"Edit");
            MenuItem delete=menu.add(Menu.NONE,2,2,"Delete");
            edit.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(listener!=null)
            {
                //position of clicked item
                int position=getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION)
                {
                  switch (item.getItemId()){
                      case 1: listener.onEditClick(position);
                      return true;
                      case 2: listener.onDeleteClick(position);
                      return true;
                  }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;

    }


}

