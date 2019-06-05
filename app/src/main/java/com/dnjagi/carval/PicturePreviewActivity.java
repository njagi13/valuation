package com.dnjagi.carval;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.data.ImagePathRecord;
import com.dnjagi.carval.data.UploadRecordAPI;
import com.dnjagi.carval.enums.eFileStatus;
import com.dnjagi.carval.utility.Utilities;
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

public class PicturePreviewActivity extends AppCompatActivity {

    private static final String BASE_FOLDER = "/Android/data/com.dnjagi.carval/files"; //possible fix for KitKat
    private static final String FAV_FOLDER = "/fav/";
    private static final String TEMP_FOLDER = "/temp/";
    private static final String SERIALIZED_FOLDER = "/upload/";
    private static WeakReference<byte[]> image;
    private static String SD_CARD_PATH;
    ArrayList<String> mFilePaths = new ArrayList<String>();// list of file paths
    File[] listFile;
    Button buttonFinish;
    ImageView addImage;
    private int count;
    private Bitmap[] thumbnails;
    private boolean[] thumbnailsselection;
    private String[] arrPath;
    private ImageAdapter imageAdapter;

    public static void setImage(@Nullable byte[] im) {
        image = im != null ? new WeakReference<>(im) : null;
    }

    private static float getApproximateFileMegabytes(Bitmap bitmap) {
        return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024 / 1024;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_preview);
        final ImageView imageView = findViewById(R.id.image);
        final EditText recommendedVal = findViewById(R.id.input_recVal);
        buttonFinish = findViewById(R.id.buttonFinish);
        final long delay = getIntent().getLongExtra("delay", 0);
        final int nativeWidth = getIntent().getIntExtra("nativeWidth", 0);
        final int nativeHeight = getIntent().getIntExtra("nativeHeight", 0);
        byte[] b = image == null ? null : image.get();
        if (b == null) {
            finish();
            return;
        }
        addImage = findViewById(R.id.addImage);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
                String fileName = new Date() + ".jpeg";
                //   createDirectoryAndSaveFile(bitmap,fileName);
                createFolders(bitmap);
                // approxUncompressedSize.setTitle("Approx. uncompressed size");
                // approxUncompressedSize.setMessage(getApproximateFileMegabytes(bitmap) + "MB");
                getFromSdcard();
                GridView imagegrid = findViewById(R.id.PhoneImageGrid);
                imageAdapter = new ImageAdapter();
                imagegrid.setAdapter(imageAdapter);
            }
        });
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(this);
        SD_CARD_PATH = sharedPref.getString("sd_card_path", Environment
                .getExternalStorageDirectory().getAbsolutePath());

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Please wait..", Toast.LENGTH_LONG).show();
                buttonFinish.setEnabled(false);
                String recValue = recommendedVal.getText().toString();
                if (GlobalVarible.uploadRecord != null) {
                    GlobalVarible.uploadRecord.RecommendedValue = Integer.parseInt(recValue);
                }
                PostValuation();
            }
        });
    }

    private void PostValuation() {
        UploadRecordAPI uploadRecordAPI = new UploadRecordAPI();
        uploadRecordAPI.CreateValuation(GlobalVarible.uploadRecord);
    }

    private void createFolders(Bitmap imageToSave) {
        // create temp and fav folders
        try {
            File mFolder = new File(SD_CARD_PATH + BASE_FOLDER + TEMP_FOLDER);
            String fileName = "";
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String strDate = mdformat.format(calendar.getTime());
            fileName = strDate;
            GlobalVarible.fileRoot = GlobalVarible.uploadRecord.RegistrationNumber;
            String uploadId = GlobalVarible.uploadRecord.UploadRecordID.toString();
            mFolder = new File(SD_CARD_PATH + BASE_FOLDER + SERIALIZED_FOLDER + "/" + GlobalVarible.fileRoot + "/" + uploadId + "_" + fileName + ".png");
            if (!mFolder.exists()) {
                mFolder.mkdirs();
            }
            if (mFolder.exists()) {
                mFolder.delete();
            }
            GlobalVarible.imgpath = SD_CARD_PATH + BASE_FOLDER + SERIALIZED_FOLDER + "/" + GlobalVarible.fileRoot;
            ImagePathRecord imagePathRecord = new ImagePathRecord();
            //PENDING SUBMISSION SINCE USER HAS NOT CLICKED SEND
            imagePathRecord.FileStatus = eFileStatus.PENDING_SUBMISSION;
            imagePathRecord.ImagePath = SD_CARD_PATH + BASE_FOLDER + SERIALIZED_FOLDER + GlobalVarible.fileRoot + "/" + uploadId + "_";
            imagePathRecord.FileName = fileName;
            imagePathRecord.UploadRecordID = uploadId;
            imagePathRecord.save();
            ArrayList<ImagePathRecord> tt = ImagePathRecord.findAllRecords(ImagePathRecord.class);

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


        } catch (Exception e) {
            Utilities.LogException(e);
        }
    }

    public void deleteAllFiles() {
        File file = new File(GlobalVarible.imgpath);
        if (file.isDirectory()) {
            listFile = file.listFiles();
            for (int i = 0; i < listFile.length; i++) {
                listFile[i].delete();
            }
        }
    }

    public void getFromSdcard() {
        File file = new File(GlobalVarible.imgpath);
        if (file.isDirectory()) {
            listFile = file.listFiles();
            if (listFile.length >= GlobalVarible.RequiredImagesCount) {
                buttonFinish.setVisibility(View.VISIBLE);
                addImage.setVisibility(View.INVISIBLE);
            } else {
                addImage.setVisibility(View.VISIBLE);
                buttonFinish.setVisibility(View.INVISIBLE);
            }
            for (int i = 0; i < listFile.length; i++) {
                mFilePaths.add(listFile[i].getAbsolutePath());
            }
        }
    }


    public class ImageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ImageAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return mFilePaths.size();
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
                holder.imageview = convertView.findViewById(R.id.thumbImage);
                holder.closeImageview = convertView.findViewById(R.id.itemCheckBox);
                holder.closeImageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int y = 0;
                        String path = mFilePaths.get(position);
                        File fdelete = new File(path);
                        if (fdelete.exists()) {
                            if (fdelete.delete()) {
                                System.out.println("file Deleted :" + path);
                            } else {
                                System.out.println("file not Deleted :" + path);
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

            Bitmap myBitmap = BitmapFactory.decodeFile(mFilePaths.get(position));
            holder.imageview.setImageBitmap(myBitmap);
            return convertView;
        }
    }


    class ViewHolder {
        ImageView imageview;
        ImageView closeImageview;
    }
}
