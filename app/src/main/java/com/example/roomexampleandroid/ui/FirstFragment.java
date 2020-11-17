package com.example.roomexampleandroid .ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.roomexampleandroid.R;


public class FirstFragment extends Fragment {
    private TextView textInFragment;
    private EditText enteredApiKey;
    private Button button;
    private static final String ENTERED_API_KEY = "39f328d281294c998df37ec5b9d04305";
    private String savedApiKey;

    public FirstFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);

        textInFragment = view.findViewById(R.id.textInFirstFragment);
        enteredApiKey = view.findViewById(R.id.enteredApiKey);
        button = view.findViewById(R.id.button);
        enteredApiKey.setText(ENTERED_API_KEY);

        savedApiKey = enteredApiKey.getText().toString();

        DataSharedPreference dataSharedPreference = DataSharedPreference.getSPInstance();
        dataSharedPreference.saveInfo(getActivity(),savedApiKey);

        button.setOnClickListener(v -> {
            SourceFragment newsFragment = new SourceFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, newsFragment)
                    .addToBackStack(null).commit();

        });
        return view;
    }
}
