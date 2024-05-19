package com.example.qr_generator_reader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import com.example.qr_generator_reader.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        replaceFragment(new QRGenerator());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.GeneratorPage) replaceFragment(new QRGenerator());
            if (item.getItemId() == R.id.ScannerPage){
                Intent intent = new Intent(this, QRScanner.class);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.HistoryPage) replaceFragment(new QRHistory());
            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment).commit();
    }
}