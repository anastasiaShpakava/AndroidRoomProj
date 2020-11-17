package com.example.roomexampleandroid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.roomexampleandroid.R;
import com.example.roomexampleandroid.adapter.SourceAdapter;
import com.example.roomexampleandroid.entity.Source;
import com.example.roomexampleandroid.viewModel.SourceViewModel;

import java.util.ArrayList;
import java.util.List;



public class SourceFragment extends Fragment {
    private RecyclerView recyclerView;
    private SourceAdapter sourceAdapter;
    private List<Source> sources = new ArrayList<>();
    private SourceViewModel sourceViewModel;
    private Button goToSavedDataButton;

    public SourceFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.source_fragment, container, false);

        DataSharedPreference sharedPreference = DataSharedPreference.getSPInstance();
        String api_key = sharedPreference.loadText(getActivity());

        goToSavedDataButton = view.findViewById(R.id.goToSavedDataButton);

        recyclerView = view.findViewById(R.id.recyclerViewToFragment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        sourceViewModel = ViewModelProviders.of(this).get(SourceViewModel.class);
        sourceViewModel.init(api_key);

        sourceViewModel.getNewsRepository().observe(this, newsResponse -> {
            List<Source> newsArticles = newsResponse.getSources();
            sources.addAll(newsArticles);
            sourceAdapter.notifyDataSetChanged(); //сообщает наблюдателям,что набор данных изменился
        });
        sourceAdapter = new SourceAdapter(getActivity(), sources);
        recyclerView.setAdapter(sourceAdapter);

        goToSavedDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavedDataFragment savedDataFragment = new SavedDataFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, savedDataFragment)
                        .addToBackStack(null).commit();
            }
        });
        return view;
    }
}
