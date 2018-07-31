package com.dnjagi.carval;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.dnjagi.carval.global.GlobalVarible;
import com.otaliastudios.cameraview.AspectRatio;
import com.otaliastudios.cameraview.CameraUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class PicturePreviewActivity extends Activity {

    private int count;
    private Bitmap[] thumbnails;
    private boolean[] thumbnailsselection;
    private String[] arrPath;
    private ImageAdapter imageAdapter;
    ArrayList<String> f = new ArrayList<String>();// list of file paths
    File[] listFile;

    private static WeakReference<byte[]> image;

    public static void setImage(@Nullable byte[] im) {
        image = im != null ? new WeakReference<>(im) : null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_preview);
        final ImageView imageView = findViewById(R.id.image);
        //   final MessageView nativeCaptureResolution = findViewById(R.id.nativeCaptureResolution);
        // final MessageView actualResolution = findViewById(R.id.actualResolution);
        // final MessageView approxUncompressedSize = findViewById(R.id.approxUncompressedSize);
        // final MessageView captureLatency = findViewById(R.id.captureLatency);

        final long delay = getIntent().getLongExtra("delay", 0);
        final int nativeWidth = getIntent().getIntExtra("nativeWidth", 0);
        final int nativeHeight = getIntent().getIntExtra("nativeHeight", 0);
        byte[] b = image == null ? null : image.get();
        if (b == null) {
            finish();
            return;
        }

    /*    if(GlobalVarible.RefreshGrid)
        {
            getFromSdcard();
            GridView imagegrid = (GridView) findViewById(R.id.PhoneImageGrid);
            imageAdapter = new ImageAdapter();
            imagegrid.setAdapter(imageAdapter);
            GlobalVarible.RefreshGrid = false;
        }*/

        CameraUtils.decodeBitmap(b, 1000, 1000, new CameraUtils.BitmapCallback() {
            @Override
            public void onBitmapReady(Bitmap bitmap) {
                //   imageView.setImageBitmap(bitmap);
                String fileName = String.valueOf(new Date()) + ".jpeg";
                //   createDirectoryAndSaveFile(bitmap,fileName);
                createFolders(bitmap);
                // approxUncompressedSize.setTitle("Approx. uncompressed size");
                // approxUncompressedSize.setMessage(getApproximateFileMegabytes(bitmap) + "MB");


                getFromSdcard();
                GridView imagegrid = (GridView) findViewById(R.id.PhoneImageGrid);
                imageAdapter = new ImageAdapter();
                imagegrid.setAdapter(imageAdapter);
            }
        });
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(this);
        SD_CARD_PATH = sharedPref.getString("sd_card_path", Environment
                .getExternalStorageDirectory().getAbsolutePath());

    }

    private static final String BASE_FOLDER = "/Android/data/com.dnjagi.carval/files"; //possible fix for KitKat
    private static final String FAV_FOLDER = "/fav/";
    private static final String TEMP_FOLDER = "/temp/";
    private static final String SERIALIZED_FOLDER = "/upload/";
    private static String SD_CARD_PATH;

    private void createFolders(Bitmap imageToSave) {
        // create temp and fav folders
        File mFolder = new File(SD_CARD_PATH + BASE_FOLDER + TEMP_FOLDER);
        String fileName = "";

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = mdformat.format(calendar.getTime());

        fileName = strDate;
        GlobalVarible.fileRoot = "KCC7874H";
        mFolder = new File(SD_CARD_PATH + BASE_FOLDER + SERIALIZED_FOLDER + "/" + GlobalVarible.fileRoot + "/" + UUID.randomUUID().toString() + "_" + fileName + ".png");
        if (!mFolder.exists()) {
            mFolder.mkdirs();
        }

        if (mFolder.exists()) {
            mFolder.delete();
        }
        GlobalVarible.imgpath = SD_CARD_PATH + BASE_FOLDER + SERIALIZED_FOLDER + "/" + GlobalVarible.fileRoot;
        try {
            FileOutputStream out = new FileOutputStream(mFolder);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void getFromSdcard() {
        File file = new File(GlobalVarible.imgpath);

        if (file.isDirectory()) {
            listFile = file.listFiles();


            for (int i = 0; i < listFile.length; i++) {

                f.add(listFile[i].getAbsolutePath());

            }
        }
    }

    private static float getApproximateFileMegabytes(Bitmap bitmap) {
        return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024 / 1024;
    }


    public class ImageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ImageAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return f.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(
                        R.layout.gelleryitem, null);
                holder.imageview = (ImageView) convertView.findViewById(R.id.thumbImage);
                holder.closeImageview = (ImageView) convertView.findViewById(R.id.itemCheckBox);
                holder.closeImageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int y = 0;
                      String path =  f.get(position);

                        File fdelete = new File(path);
                        if (fdelete.exists()) {
                            if (fdelete.delete()) {
                                System.out.println("file Deleted :" + path);
                            } else {
                                System.out.println("file not Deleted :" +path);
                            }
                        }
                        notifyDataSetChanged();
                        GlobalVarible.RefreshGrid = true;
                        finish();
                        startActivity(getIntent());
                    }
                });
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            Bitmap myBitmap = BitmapFactory.decodeFile(f.get(position));
            holder.imageview.setImageBitmap(myBitmap);
            return convertView;
        }
    }


    class ViewHolder {
        ImageView imageview;
        ImageView closeImageview;
    }
}
