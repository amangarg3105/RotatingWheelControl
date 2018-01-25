package com.example.aman.rotatingwheelcontrol;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.aman.rotatingwheelcontrol.GalleryActivity.bmp;

/**
 * Created by Aman on 24-01-2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private String[] filepath;

    public MyAdapter(Context context,  String[] fpath) {
        filepath = fpath;
        this.context = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gridview_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {

        //  viewHolder.title.setText(galleryList.get(i).getImage_title());

        int targetWidth = 300;
        int targetHeight = 300;
        BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
        bmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filepath[i], bmpOptions);
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
        bmp = BitmapFactory.decodeFile(filepath[i], bmpOptions);
        viewHolder.img.setImageBitmap(bmp);
        //image.setScaleType(ImageView.ScaleType.FIT_XY);
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        bmp = null;

        // viewHolder.img.setImageResource((galleryList.get(i).getImage_ID()));
        // Picasso.with(context).load(filepath[i]).resize(240, 120).into(viewHolder.img);
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filepath.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView img;
        public ViewHolder(View view) {
            super(view);

            title = (TextView)view.findViewById(R.id.title);
            img = (ImageView) view.findViewById(R.id.img);
        }
    }

}