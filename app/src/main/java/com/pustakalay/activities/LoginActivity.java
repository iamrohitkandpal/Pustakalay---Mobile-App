package com.pustakalay.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.pustakalay.MainActivity;
import com.pustakalay.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Add login logic here
        binding.btnLogin.setOnClickListener(v -> {
            // Implement login validation
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
