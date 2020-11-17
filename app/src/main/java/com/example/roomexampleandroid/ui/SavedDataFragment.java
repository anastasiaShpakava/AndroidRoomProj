package com.example.roomexampleandroid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomexampleandroid.R;
import com.example.roomexampleandroid.adapter.SavedDataAdapter;
import com.example.roomexampleandroid.entity.Source;
import com.example.roomexampleandroid.repository.DataBaseRepository;
import com.example.roomexampleandroid.viewModel.SourceViewModel;

import java.util.ArrayList;
import java.util.List;

public class SavedDataFragment extends Fragment {
    private SourceViewModel sourceViewModel;
    private DataBaseRepository dataBaseRepository;
    private RecyclerView recyclerView;
    private List<Source> sourceList;
    private SavedDataAdapter adapter;

    public SavedDataFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved_data,container,false);

        DataSharedPreference sharedPreference = DataSharedPreference.getSPInstance();
        String api_key = sharedPreference.loadText(getActivity());

        dataBaseRepository = new DataBaseRepository(getActivity());
        sourceViewModel = ViewModelProviders.of(this).get(SourceViewModel.class);

        recyclerView = view.findViewById(R.id.recyclerViewSavedFragment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        sourceList = new ArrayList<>();

        adapter = new SavedDataAdapter(getActivity(), sourceList);
        recyclerView.setAdapter(adapter);

        sourceViewModel.getAllSources().observe(this, new Observer<List<Source>>() {
            @Override
            public void onChanged(List<Source> sources) {
              adapter.setSourceList(sources);
            }
        });
        dataBaseRepository.getSourceListTodb(api_key);
        return view;
    }

}
