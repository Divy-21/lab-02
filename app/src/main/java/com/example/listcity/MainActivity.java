package com.example.listcity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button button_add;
    Button button_confirm;
    Button button_delete;
    EditText textbox;
    private int selectedPos = -1;
    private boolean addingCity = false;
    private View selectedView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        String[] cities = {"Edmonton", "Vancouver", "Sydney", "Berlin", "Vienna", "Tokyo",
                "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        button_delete = findViewById(R.id.button_delete);
        button_add = findViewById(R.id.button_add);
        button_confirm = findViewById(R.id.button_confirm);
        textbox = findViewById(R.id.edit_city_name);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPos = position;
                if (selectedView != null) {
                    selectedView.setBackgroundColor(Color.TRANSPARENT);
                }
                view.setBackgroundColor(Color.LTGRAY);
                selectedView = view;
                String cityName = dataList.get(position);
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!addingCity) {
                    addingCity = true;
                    textbox.setVisibility(View.VISIBLE);
                    button_confirm.setVisibility(View.VISIBLE);
                    button_add.setText("CANCEL");
                } else {
                    addingCity = false;
                    textbox.setVisibility(View.GONE);
                    button_confirm.setVisibility(View.GONE);
                    button_add.setText("ADD CITY");
                }
            }
        });

        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityToAdd = textbox.getText().toString().trim();
                if (!cityToAdd.isEmpty()) {
                    dataList.add(cityToAdd);
                    textbox.setText("");
                    textbox.setVisibility(View.GONE);
                    button_confirm.setVisibility(View.GONE);
                    button_add.setText("ADD CITY");
                    addingCity = false;}
                else {
                    textbox.setText("");
                    textbox.setVisibility(View.GONE);
                    button_confirm.setVisibility(View.GONE);
                    button_add.setText("ADD CITY");
                    addingCity = false;
                }
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPos != -1 && selectedPos < dataList.size()) {
                    dataList.remove(selectedPos);
                    cityAdapter.notifyDataSetChanged();
                    selectedPos = -1;
                    selectedView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

    }
}