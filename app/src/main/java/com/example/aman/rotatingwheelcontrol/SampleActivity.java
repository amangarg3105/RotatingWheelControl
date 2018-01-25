package com.example.aman.rotatingwheelcontrol;



import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class SampleActivity extends AppCompatActivity implements CircleLayout.OnItemSelectedListener,
        CircleLayout.OnItemClickListener, CircleLayout.OnRotationFinishedListener, CircleLayout.OnCenterClickListener {
    public static final String ARG_LAYOUT = "layout";
    String target = null;
    protected CircleLayout circleLayout;
    protected TextView selectedTextView;
      Context context;
    protected CircleImageView circleImageView;
    private MediaScannerConnection conn;
    File sdDir;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view by passed extra
        Bundle extras = getIntent().getExtras();

        setContentView(R.layout.sample);
context = getApplicationContext();
        // Set listeners
        circleLayout = (CircleLayout) findViewById(R.id.circle_layout);
        circleImageView = (CircleImageView)findViewById(R.id.center_image);
        circleLayout.setOnItemSelectedListener(this);
        circleLayout.setOnItemClickListener(this);
        circleLayout.setOnRotationFinishedListener(this);
        circleLayout.setOnCenterClickListener(this);

        selectedTextView = (TextView) findViewById(R.id.selected_textView);

        String name = null;
        View view = circleLayout.getSelectedItem();
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        }
        Log.d("Oncreate",name);
target = "Screenshot";
       sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

    //   sdDir = Environment.getExternalStorageDirectory();
        Log.d("sdDir",sdDir.toString());
        file = new File(sdDir,"Screenshots");
     //   file = new File(sdDir + "/WhatsApp" + "/WhatsApp Images");
        Log.d("FilePath", file.toString());
        final File[] allFiles = file.listFiles();
       /* for(int i=0;i<allFiles.length;i++)
        {
           *//* Log.d("all file path"+i, allFiles[i]+allFiles.length);*//*
        }*/
        selectedTextView.setText(name);
        selectedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*  startScan();*/

                Intent i = new Intent(SampleActivity.this,GalleryActivity.class);
                i.putExtra("folder",file);
                startActivity(i);

            }
        });

    }

   /* private void startScan() {
        Log.d("Connected","success"+conn);
        if(conn!=null)
        {
            conn.disconnect();
        }
        conn = new MediaScannerConnection(this,this);
        conn.connect();
    }*/


    @Override
    public void onItemSelected(View view) {
        final String name;
        Bitmap bImage;
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        } else {
            name = null;
        }
String logger = "logger";

        selectedTextView.setText(name);
Log.d("OnItemSelected",name);
        switch (view.getId()) {
          /*  case R.id.main_calendar_image:
                // Handle calendar selection
                bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_calendar);
                circleImageView.setImageBitmap(bImage);
                break;*/
           /* case R.id.main_cloud_image:
                // Handle cloud selection
                bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_cloud);
                circleImageView.setImageBitmap(bImage);
                break;*/
            case R.id.main_key_image:
                // Handle key selection
                bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.whatsapp);
                circleImageView.setImageBitmap(bImage);
                target = "whatsapp";
                Log.d(logger,target);
                break;
            case R.id.main_mail_image:
                // Handle mail selection
                bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.screenshot);
                circleImageView.setImageBitmap(bImage);
                target = "Screenshot";
                Log.d(logger,target);
                break;
           /* case R.id.main_profile_image:
                // Handle profile selection
                bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_profile);
                circleImageView.setImageBitmap(bImage);
                break;*/
            case R.id.main_tap_image:
                // Handle tap selection
                bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.camera);
                circleImageView.setImageBitmap(bImage);
                target = "TAG";
                Log.d(logger,target);
                break;
        }
        if( target.equals("Screenshot")) {
            sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
          //  Log.d("sdDir",sdDir.toString());
            file = new File(sdDir,"Screenshots");
        } else if(target.equals("whatsapp")) {
            sdDir = Environment.getExternalStorageDirectory();
            file = new File(sdDir + "/WhatsApp" + "/WhatsApp Images");
        } else if(target.equals("TAG")) {
            sdDir = Environment.getExternalStorageDirectory();
            file = new File(sdDir + "/DCIM" + "/Camera");
        }
        final File[] allFiles = file.listFiles();

    }

    @Override
    public void onItemClick(View view) {
       /* String name = null;
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        }

        String text = getResources().getString(R.string.start_app, name);
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

        switch (view.getId()) {
            case R.id.main_calendar_image:
                // Handle calendar click
                break;
            case R.id.main_cloud_image:
                // Handle cloud click
                break;
            case R.id.main_key_image:
                // Handle key click
                break;
            case R.id.main_mail_image:
                // Handle mail click
                break;
            case R.id.main_profile_image:
                // Handle profile click
                break;
            case R.id.main_tap_image:
                // Handle tap click
                break;
        }*/
    }

    @Override
    public void onRotationFinished(View view) {
        Animation animation = new RotateAnimation(0, 360, view.getWidth() / 2, view.getHeight() / 2);
        animation.setDuration(250);
        view.startAnimation(animation);
    }

    @Override
    public void onCenterClick() {
      //  Toast.makeText(getApplicationContext(), R.string.center_click, Toast.LENGTH_SHORT).show();
        if( target.equals("Screenshot")) {
            sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            //  Log.d("sdDir",sdDir.toString());
            file = new File(sdDir,"Screenshots");
        } else if(target.equals("whatsapp")) {
            sdDir = Environment.getExternalStorageDirectory();
            file = new File(sdDir + "/WhatsApp" + "/WhatsApp Images");
        } else if(target.equals("TAG")) {
            sdDir = Environment.getExternalStorageDirectory();
            file = new File(sdDir + "/DCIM" + "/Camera");
        }

        Intent i = new Intent(SampleActivity.this,GalleryActivity.class);
        i.putExtra("folder",file);
        startActivity(i);
    }

  /*  public void onAddClick(View view) {
        CircleImageView newMenu = new CircleImageView(this);
        newMenu.setBackgroundResource(R.drawable.circle);
        newMenu.setImageResource(R.drawable.ic_voice);
        newMenu.setName(getString(R.string.voice_search));
        circleLayout.addView(newMenu);
    }

    public void onRemoveClick(View view) {
        if (circleLayout.getChildCount() > 0) {
            circleLayout.removeViewAt(circleLayout.getChildCount() - 1);
        }
    }*/

}
