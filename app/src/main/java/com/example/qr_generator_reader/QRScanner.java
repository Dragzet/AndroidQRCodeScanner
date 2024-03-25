package com.example.qr_generator_reader;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.qr_generator_reader.databinding.ActivityQrscannerBinding;
import com.google.zxing.integration.android.IntentIntegrator;

public class QRScanner extends AppCompatActivity {

    ActivityQrscannerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrscannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.scan.setOnClickListener(v ->{
            ActivityResultLauncher<String> request = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if(isGranted){
                    showCam
                }
            })
            IntentIntegrator intentIntegrator = new IntentIntegrator(QRScanner.this);
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setPrompt("Scanner");
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            intentIntegrator.initiateScan();
        });
    }
}
