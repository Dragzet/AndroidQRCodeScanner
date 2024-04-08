package com.example.qr_generator_reader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.InputStream;
import java.net.URL;


public class QRGenerator extends Fragment {

    private Button generateButton;
    private TextView urlText;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_q_r_generator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        urlText = view.findViewById(R.id.imageUrl);
        imageView = view.findViewById(R.id.imageView);
        generateButton = view.findViewById(R.id.generateButton);
        generateButton.setOnClickListener(v -> {
            String image_url = "https://api.qrserver.com/v1/create-qr-code/?data=" + urlText.getText() +"&size=300x300";
            setQRCode(image_url, imageView);
        });
    }

    protected void setQRCode(String imageUrl, ImageView imageView)
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());
                    imageView.post(new Runnable()
                    {
                        public void run()
                        {
                            if(bitmap !=null)
                            {
                                imageView.setImageBitmap(bitmap);
                            }
                        }
                    });
                } catch (Exception e)
                {
                    Log.d("YAMATR", e.toString());
                }
            }
        }).start();
    }

}
