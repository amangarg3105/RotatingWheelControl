package com.example.aman.rotatingwheelcontrol;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class GalleryActivity extends AppCompatActivity {

    private String[]        FilePathStrings;
    private File[]          listFile;
    GridView grid;

    File file;
    File sdDir;
    File pictureFile;
    public static Bitmap bmp = null;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
      //  Intent intent = getIntent();
         file = (File)getIntent().getExtras().get("folder");
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);

        // ArrayList<CreateList> createLists = prepareData();

        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
        {
            Toast.makeText(this, "Error! No SDCARD Found!",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            // Locate the image folder in your SD Card
           /* sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            file = new File(sdDir,"Screenshots");*/
        }
        if (file.isDirectory())
        {
            listFile = file.listFiles();
            FilePathStrings = new String[listFile.length];
            for (int i = 0; i < listFile.length; i++)
            {
                FilePathStrings[i] = listFile[i].getAbsolutePath();
            }
        }
        MyAdapter adapter = new MyAdapter(getApplicationContext(), FilePathStrings);
        recyclerView.setAdapter(adapter);
       /* grid = (GridView)findViewById(R.id.gridview);
        adapter = new GridViewAdapter(this, FilePathStrings);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view,
                                     int position, long id)
            {
                imageview = (ImageView)findViewById(R.id.imageView1);
                int targetWidth = 700;
                int targetHeight = 500;
                BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
                bmpOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(FilePathStrings[position],
                        bmpOptions);
                int currHeight = bmpOptions.outHeight;
                int currWidth = bmpOptions.outWidth;
                int sampleSize = 1;
                if (currHeight > targetHeight || currWidth > targetWidth)
                {
                    if (currWidth > currHeight)
                        sampleSize = Math.round((float)currHeight
                                / (float)targetHeight);
                    else
                        sampleSize = Math.round((float)currWidth
                                / (float)targetWidth);
                }
                bmpOptions.inSampleSize = sampleSize;
                bmpOptions.inJustDecodeBounds = false;
                bmp = BitmapFactory.decodeFile(FilePathStrings[position],
                        bmpOptions);
                imageview.setImageBitmap(bmp);
                imageview.setScaleType(ImageView.ScaleType.FIT_XY);
                bmp = null;

            }
        });
*/
    }

}
