package com.example.shana.filedemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private File fPhoneDicectory,fExternalStoragePublicDirectory,fExternalStorageDirectory;
    private File fDataDirectory,fDownloadCacheDirectory,fRootDirectory;
    private String path,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.tv);
        intData();
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Button btn= (Button) findViewById(R.id.external_storage_directory);
            btn.setEnabled(false);
        }
    }

    private void intData() {
        fPhoneDicectory=this.getFilesDir();
        fExternalStoragePublicDirectory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        fExternalStorageDirectory=Environment.getExternalStorageDirectory();
        fDataDirectory=Environment.getDataDirectory();
        fDownloadCacheDirectory=Environment.getDownloadCacheDirectory();
        fRootDirectory=Environment.getRootDirectory();
    }

    public void doClick(View view){
        switch (view.getId()){
            case R.id.phone_dicectory:
                showPhoneDicectory();
                break;
            case R.id.external_storage_public_directory:
                showExternalStoragePublicDirectory();
                break;
            case R.id.external_storage_directory:
                showExternalStorageDirectory();
                break;
            case R.id.data_storage:
                showDataStorage();
                break;
            case  R.id.download_cache_directory:
                showDownloadCacheDirectory();
                break;
            case  R.id.root_directory:
                showRootDirectory();
                break;
        }

    }

    private void showRootDirectory() {
        path=fRootDirectory.getAbsolutePath();
        listFile(path);
    }

    private void showDownloadCacheDirectory() {
        path=fDownloadCacheDirectory.getAbsolutePath();
        listFile(path);
    }

    private void showDataStorage() {
        path=fDataDirectory.getAbsolutePath();
        listFile(path);
    }

    private void showExternalStorageDirectory() {
        path=fExternalStorageDirectory.getPath();
        listFile(path);

    }

    private void showExternalStoragePublicDirectory() {
        path=fExternalStoragePublicDirectory.getAbsolutePath();
        listFile(path);

    }

    private void showPhoneDicectory() {
       path= fPhoneDicectory.getPath();
        try {
            FileOutputStream fos=openFileOutput("test.txt",MODE_PRIVATE);
            fos.write("hello".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listFile(path);
    }

    private boolean listFile(String path) {
        name="路径:"+path+"\n文件清单:\n";
        File file=new File(path);
        if(file.listFiles()!=null&&file.listFiles().length>0){
            for(File f:file.listFiles()){
                name+=f.getName()+"\n";
            }
        }
        tv.setText(name);
        return false;
    }

}
