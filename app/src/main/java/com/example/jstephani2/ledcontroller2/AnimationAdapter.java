package com.example.jstephani2.ledcontroller2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class AnimationAdapter extends RealmRecyclerViewAdapter<LedAnimation, AnimationAdapter.ViewHolder> {
    private Context context;
    public AnimationAdapter (Context context, OrderedRealmCollection<LedAnimation> data, boolean autoUpdate) {
        super(data, autoUpdate);
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView animationName;
        public ViewHolder (View v)
        {
            super(v);

            animationName = (TextView) v.findViewById(R.id.name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animation_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int pos) {
        final LedAnimation animation = getItem(pos);
Log.i("hi",holder.toString());
        holder.animationName.setText(animation.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String fullCode = animation.getCode() + "," + animation.getSetting_vals();
                    LedControllerApplication.outputStream.write(fullCode.getBytes());
                } catch (IOException e) {}
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, NewAnimationActivity.class);
                intent.putExtra("animation", animation);
                context.startActivity(intent);
                return true;
            }
        });
    }
}
