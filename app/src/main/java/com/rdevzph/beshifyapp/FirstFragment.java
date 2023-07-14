package com.rdevzph.beshifyapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.rdevzph.beshifyapp.databinding.FragmentFirstBinding;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.beshy.setOnClickListener(view1 -> {

            String inputText = binding.edittxt.getText().toString();
            if (inputText.isEmpty()) {
                Snackbar.make(view1, "Text cannot be empty!", Snackbar.LENGTH_SHORT).show();
            }else {
                String outputText = inputText.replace(" ", "🤸");
                binding.output.setText(outputText);

                // Copy the updated text to the clipboard
                ClipboardManager clipboard = (ClipboardManager) view1.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", outputText);
                clipboard.setPrimaryClip(clip);

                // Display a snackbar message to indicate that the text has been copied
                Snackbar.make(view1, "Copied to clipboard: "+outputText, Snackbar.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}