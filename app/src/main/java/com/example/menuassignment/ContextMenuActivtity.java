package com.example.menuassignment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ContextMenuActivtity extends AppCompatActivity {

    ListView listView;
    Resources resources;
    ArrayAdapter<String>stringArrayAdapter;
  // String[] items={"Food","Grocery","Cosmetics","Cloth"};
    String[]items;



    Button btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_menu);
        init();
        listeners();
        initResources();

        stringArrayAdapter=new ArrayAdapter<String>(ContextMenuActivtity.this,
                android.R.layout.simple_list_item_1,
                items);
        listView.setAdapter(stringArrayAdapter);
        registerForContextMenu(listView);

    }
    private void init()
    {

        listView=findViewById(R.id.ListViewItem);
        btnBack=findViewById(R.id.btnBack);

    }
    private void initResources(){
        resources=getResources();
        items=resources.getStringArray(R.array.item_array);

    }
    private void listeners(){
        btnBack.setOnClickListener(new BtnBackClickListener());
    }

    class BtnBackClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i=new Intent(ContextMenuActivtity.this,MainActivity.class);
            startActivity(i);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        menu.setHeaderIcon(R.drawable.ic_baseline_settings_24);


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int menuitem=item.getItemId();
        switch (menuitem) {
            case R.id.addTocart:
                break;

            case R.id.like:
                break;
            case R.id.Comment:
                break;
            case R.id.dislike:
                break;
        }






        return true;
    }
}
