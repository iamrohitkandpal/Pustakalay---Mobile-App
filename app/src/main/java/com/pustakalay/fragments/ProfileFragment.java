package com.pustakalay.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.pustakalay.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        prefs = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        loadUserData();
        setupLogout();
        return binding.getRoot();
    }

    private void loadUserData() {
        String username = prefs.getString("username", "Guest");
        String email = prefs.getString("email", "guest@example.com");
        
        binding.tvUsername.setText(username);
        binding.tvEmail.setText(email);
    }

    private void setupLogout() {
        binding.btnLogout.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                .setTitle("Logout")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (dialog, which) -> performLogout())
                .setNegativeButton("No", null)
                .show();
        });
    }

    private void performLogout() {
        prefs.edit().clear().apply();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        requireActivity().finish();
    }
} 