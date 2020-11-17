package com.example.roomexampleandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomexampleandroid.R;
import com.example.roomexampleandroid.entity.Source;

import java.util.List;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceViewHolder> {
    private LayoutInflater inflater;
    private List<Source> sources;


    public SourceAdapter(Context context, List<Source> sources) {
        this.sources = sources;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SourceAdapter.SourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new SourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SourceViewHolder holder, int position) {
        final Source source = sources.get(position);
        holder.sourceId.setText(source.getId());
        holder.sourceName.setText(source.getName());
        holder.sourceDescription.setText(source.getDescription());
        holder.sourceURL.setText(source.getUrl());
        holder.sourceCategory.setText(source.getCategory());
        holder.sourceLanguage.setText(source.getLanguage());
        holder.sourceCountry.setText(source.getCountry());
    }


    @Override
    public int getItemCount() {
        return sources.size();
    }

    public static class SourceViewHolder extends RecyclerView.ViewHolder {
        TextView sourceName, sourceId, sourceDescription, sourceURL, sourceCategory, sourceLanguage, sourceCountry;

        public SourceViewHolder(View view) {
            super(view);

            sourceName = view.findViewById(R.id.sourceName);
            sourceId = view.findViewById(R.id.sourceIdItem);
            sourceDescription = view.findViewById(R.id.sourceDescription);
            sourceURL = view.findViewById(R.id.sourceURL);
            sourceCategory = view.findViewById(R.id.sourceCategory);
            sourceLanguage = view.findViewById(R.id.sourceLanguage);
            sourceCountry = view.findViewById(R.id.sourceCountry);

        }
    }
}
