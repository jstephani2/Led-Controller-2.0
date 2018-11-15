package com.example.jstephani2.ledcontroller2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class AnimationAdapter extends RealmRecyclerViewAdapter<LedAnimation, AnimationAdapter.ViewHolder> {
    public AnimationAdapter (OrderedRealmCollection<LedAnimation> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView animationName;
        TextView animationCode;
        public ViewHolder (View v)
        {
            super(v);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int pos) {
        LedAnimation setting = getData().get(pos);

        holder.animationName.setText(setting.getName());
        holder.animationCode.setText(setting.getCode());

    }
}
