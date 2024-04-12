package com.example.qr_generator_reader;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QRHistory extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_q_r_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView need = view.findViewById(R.id.need);
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("app.db", null);
        db.execSQL("CREATE TABLE IF NOT EXISTS history (type TEXT, info TEXT)");
        Cursor query = db.rawQuery("SELECT * FROM history;", null);
        while (query.moveToNext())
        {
            String type = query.getString(0);
            String info = query.getString(1);
            need.setText(type + " " + info);
        }

        query.close();
        db.close();

    }
}