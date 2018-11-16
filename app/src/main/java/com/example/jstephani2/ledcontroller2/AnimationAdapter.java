package com.example.jstephani2.ledcontroller2;

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
    public AnimationAdapter (OrderedRealmCollection<LedAnimation> data, boolean autoUpdate) {
        super(data, autoUpdate);
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
        holder.animationName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String fullCode = animation.getCode() + "," + animation.getSetting_vals();
                    MainActivity.outputStream.write(fullCode.getBytes());
                } catch (IOException e) {}
            }
        });
    }
}
