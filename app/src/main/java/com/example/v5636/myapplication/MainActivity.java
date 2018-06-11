package com.example.v5636.myapplication;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.commons.net.ftp.FTPClient;

public class MainActivity extends AppCompatActivity {
    public FTPClient IotFtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        if(checkSelfPermission(Manifest.permission.))


         new connecct_to_IOT().execute();

//        if (ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.WR)
//                != PackageManager.PERMISSION_GRANTED) {



    }
    class connecct_to_IOT extends AsyncTask<String, Integer, FTPClient> {
        public FTPClient IotFtp;
        @Override
        protected FTPClient doInBackground(String... strings) {
            ftpconnect Iotftp = new ftpconnect();

            IotFtp = Iotftp.ftpConnect("192.72.189.223", "admin", "Admin", 21,"201806");

            return IotFtp;
        }
        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(FTPClient result) {


        }
//        public FTPClient IotFtp;
//        @Override
//        protected FTPClient doInBackground(FTPClient... ftpClients) {
//            ftpconnect Iotftp = new ftpconnect();
//
//
//
//            IotFtp = Iotftp.ftpConnect("192.72.189.223", "admin", "Admin", 21);
//
//        //    Toast.makeText(getApplicationContext(), "" + result, Toast.LENGTH_LONG).show();
//
//           return null;
//        }
//        protected void onPostExecute(Long result) {
//
//        }





        }

    }






