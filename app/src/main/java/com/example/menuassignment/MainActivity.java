package com.example.menuassignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private CheckBox enable;
private ImageView imgBackground;
private Button btnNext;
private final int Menu_setting=1,Menu_phone_setting=2,Menu_Display_setting=3,Menu_set_wallpaper=4,Menu_help=5,Menu_Exit=6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listeners();



    }
    private  void init()
    {
        enable=findViewById(R.id.checkBox);
        imgBackground=findViewById(R.id.imgBackground);
        btnNext=findViewById(R.id.btnNext);


    }
    private  void listeners()
    {

            btnNext.setOnClickListener(new BtnNextClickListener());

    }

    class BtnNextClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i=new Intent(MainActivity.this,ContextMenuActivtity.class);
            startActivity(i);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SubMenu MainMenu=menu.addSubMenu(1,Menu_setting,1,"Settings")
                .setIcon(R.drawable.ic_baseline_settings_24);

        MainMenu.add(1,Menu_phone_setting,2,"Phone Setting");
        MainMenu.add(1,Menu_Display_setting,3,"Display Setting");
        MainMenu.add(1,4,Menu_set_wallpaper,"Set Wallpaper");

        MenuItem menuItem=menu.add(2,Menu_help,4,"Help");

        menu.add(3,Menu_Exit,5,"Exit");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        switch (itemId)
        {
            case Menu_setting:
                mt("Settings");
                break;
            case Menu_phone_setting:
                mt("Phone Settings");
                break;
            case Menu_Display_setting:
                mt("Display Settings");
                break;
            case Menu_set_wallpaper:
                mt("set Wallpaper");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
                break;


            case Menu_help:
                mt("help");
                break;
            case Menu_Exit:
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure you want to exit");
                DialogInterface.OnClickListener clickListener=new AlertButtonClickListener();
                builder.setPositiveButton("Yes",clickListener);
                builder.setNegativeButton("No",clickListener);

                AlertDialog dialog=builder.create();
                dialog.show();

                mt("Exit");


                break;


        }





        return true;
    }
    class AlertButtonClickListener implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which==DialogInterface.BUTTON_POSITIVE)
            {
                mt("Yes Clicked ");
                finish();
            }

            if(which==DialogInterface.BUTTON_NEGATIVE)
            {
                mt("No Clicked ");
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

       menu.setGroupEnabled(1,enable.isChecked());
       menu.findItem(Menu_setting).setVisible(true);



        return  true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Intent intent=getIntent();



        Bitmap bitmap = (Bitmap) data.getExtras().get("data");

        imgBackground.setImageBitmap(bitmap);
    }




    private void mt(String text) {
        Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
    }
}