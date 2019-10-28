package com.example.aadusoroom.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aadusoroom.R;
import com.example.aadusoroom.entity.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private /*final*/ LayoutInflater inflater;
    private List<User> userList;
    private int contador = 0;

    public UserAdapter(Context context) { //obtener layoutinflater, objeto da forma a los item al crear recycler view
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("xyzyx", "onCreateViewHolder: "+contador);
        contador++;
        /*if(inflater == null) {
            Context context = parent.getContext();
            inflater = LayoutInflater.from(context);
        }*/
        View itemView = inflater.inflate(R.layout.item, parent, false); //dando forma al itemView
        return new UserViewHolder(itemView); //devolver instacia del viewholder
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Log.v("xyzyx", "onBindViewHolder: "+contador);
        if (userList != null) {
            User user = userList.get(position);
            holder.tvItem.setText(user.toString());
        } else {
            holder.tvItem.setText("No user available");
        }
    }

    @Override
    public int getItemCount() { //cuantos elementos voy a visualizar
        Log.v("xyzyx", "getItemCount: "+contador);
        int elementos = 0;
        if(userList != null){
            elementos = userList.size();
        }
        return elementos;
    }

    public void setUser(List<User> userList){
        this.userList = userList;
        notifyDataSetChanged(); //actualizar la listas
    }

    public class UserViewHolder  extends RecyclerView.ViewHolder{
        private final TextView tvItem;

        public UserViewHolder(@NonNull View itemView){
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
        }
    }
}
