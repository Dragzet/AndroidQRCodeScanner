package com.example.qr_generator_reader;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QRHistory extends Fragment {

    private RecyclerView recyclerView;
    private TextAdapter textAdapter;
    private List<String> textList;

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
        textList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS history (type TEXT, info TEXT)");
        Cursor query = db.rawQuery("SELECT * FROM history;", null);
        while (query.moveToNext())
        {
            String type = query.getString(0);
            String info = query.getString(1);
            if (!textList.contains(type + "\n" + info))
                textList.add(type + "\n" + info);
        }
        Collections.reverse(textList);
        query.close();
        db.close();


        textAdapter = new TextAdapter(getContext(), textList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(textAdapter);

    }

}