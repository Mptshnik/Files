package com.example.fileapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<String> _files = new ArrayList<>();
    private ListView _filesListView;
    private Button _getFilesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _filesListView = (ListView) findViewById(R.id.filesListView);
        _getFilesButton = (Button) findViewById(R.id.getFilesButton);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }

        _getFilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFiles();
            }
        });
    }

    private void loadFiles()
    {
        File sdcard = Environment.getExternalStorageDirectory();


        File[] files = sdcard.listFiles();

        for (int i = 0; i < files.length; i++)
        {
            _files.add(files[i].getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                _files);
        _filesListView.setAdapter(adapter);

    }
}