package net.kisangan.totalfitness.ui.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import net.kisangan.totalfitness.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}