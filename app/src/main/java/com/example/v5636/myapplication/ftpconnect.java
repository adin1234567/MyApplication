package com.example.v5636.myapplication;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.SocketException;

public class ftpconnect {
    public FTPClient mFTPClient = null;
    final private String TAG="ftpconnect";

    String dir = Environment.getExternalStorageDirectory() + "/csvs/";


    public FTPClient ftpConnect(String host, String username, String password, int port, String folder) {

        Log.d("ftpconnect", "" + "Running ftp connect");
        mFTPClient = new FTPClient();
        try {

            Log.d(TAG, "" + "Trying to make connection");
            Log.d(TAG, "" + "Trying to make connection to Host:" + host + " ,Username:" + username + " ,Password:" + password);
            // connecting to the host
            mFTPClient.connect(host, port);

            Log.d("ftpconnect", "Connecting to host");
            mFTPClient.login(username, password);
            if (!FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
                Log.d(TAG, "" + "FTP Error");
            } else {
                Log.d(TAG, "" + "FTP Success");
            }

            int replyCode = mFTPClient.getReplyCode();
            Log.d(TAG, "Replycode" + replyCode);

            // now check the reply code, if positive mean connection success
            if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {

                // login using username & password
                boolean status = mFTPClient.login(username, password);


                mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
                Log.d(TAG, "ftp login Status=Success:" + status);

                boolean Change_status = mFTPClient.changeWorkingDirectory("/LOG/Folder1/" + folder);
                Log.d(TAG, "Change directory to:" + folder);
                Log.d(TAG, "Change success:" + Change_status);
                OutputStream outputStream = null;

                //region [File]
                String dir = Environment.getExternalStorageDirectory()
                        + "/test/";
                File fileDir = new File(dir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                final File file = new File(dir);
                if (file.exists()) {
                    file.delete();
                    Log.d(TAG+"delete", "original file deleted");
                }
                //endregion
                outputStream = new BufferedOutputStream(new FileOutputStream(
                        file));

                BufferedInputStream buffIn = null;
             //   mFTPClient.listFiles();

                mFTPClient.retrieveFile("0612_00.csv", outputStream);
                outputStream.close();
                return mFTPClient;
            }

        } catch (SocketException e) {
            e.printStackTrace();
            Log.d("ftpconnect", "FTP address may be wrong");


        } catch (Exception e) {
            Log.d("ftpconnect", "Exception!" + " Error with: *" + e.getMessage());

        }
        return mFTPClient;

    }


}
