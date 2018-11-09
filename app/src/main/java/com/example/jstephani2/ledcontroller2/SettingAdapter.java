package com.example.jstephani2.ledcontroller2;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;

public class SettingAdapter extends RealmRecyclerViewAdapter<LedSetting, SettingAdapter.ViewHolder> {
    public SettingAdapter (OrderedRealmCollection<LedSetting> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView settingName;
        TextView settingDescription;
    }
}
