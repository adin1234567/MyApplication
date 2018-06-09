package com.example.v5636.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.net.SocketException;

public class ftpconnect  {
    public FTPClient mFTPClient=null;

    public boolean ftpConnect(String host, String username, String password, int port) {

        Log.d("ftpconnect", "" + "Running ftp connect");
        mFTPClient = new FTPClient();
        try {

            Log.d("ftpconnect", "" + "Trying to make connection");
            Log.d("ftpconnect", "" + "Trying to make connection to Host:" + host + " ,Username:" + username + " ,Password:" + password);
            // connecting to the host
            mFTPClient.connect(host,port);

            Log.d("ftpconnect", "Connecting to host");
            mFTPClient.login(username, password);
            if (!FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
                Log.d("ftpconnect", "" + "FTP Error");
            } else {
                Log.d("ftpconnect", "" + "FTP Success");
            }

//            Log.d("TAG",""+FTPReply.isPositiveCompletion(mFTPClient.getReplyCode()));
            int replyCode = mFTPClient.getReplyCode();
            Log.d("ftpconnect", "Replycode" + replyCode);

            // now check the reply code, if positive mean connection success
            if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {

                // login using username & password
                boolean status = mFTPClient.login(username, password);


//                mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);


//                mFTPClient.enterLocalPassiveMode();

                return status;
            }
        } catch (SocketException e) {
            e.printStackTrace();
            Log.d("ftpconnect", "FTP address may be wrong");


        } catch (Exception e) {
            Log.d("ftpconnect", "Exception!" + " Error with: *" + e.getMessage());
        }
        return false;
    }


}
