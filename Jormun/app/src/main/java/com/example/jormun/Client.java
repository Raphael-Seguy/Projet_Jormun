package com.example.jormun;

import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class Client{
    public static final String USER_BUSY = "BUSY";
    public static final String USER_FREE = "FREE";
    public static final String USER_LOGGIN = "LOGGIN";
    public static final String USER_EXITING = "EXITING";

    OutputStream outToServer;
    Socket sktClient;
    DataOutputStream dtoutstrmToServer;
    DataInputStream dtinstrmBackToClient;
    InputStream inFromServer;
    String sUsername;
    private Boolean bPosServerConnected;

    String sTempStatus;
    LatLng ltlngTempPosition;

    public Client(String sIp, int iPort,float fTemp){
        Socket sktTemp;
        CheckConnectionThread chckthrdConnectionConfirmation = new CheckConnectionThread();
        String sMessage;
        try {
            setbPosServerConnected(false);
            System.out.println("Attempting connection");
            sktTemp = new Socket(sIp, iPort);
            sktTemp.setSoTimeout(1000);
            setSktClient(sktTemp);
            getSktClient().setSoTimeout(2000);
            setOutToServer(sktTemp.getOutputStream());
            setDtoutstrmToServer(new DataOutputStream(outToServer));
            setInFromServer(sktTemp.getInputStream());
            setDtinstrmBackToClient(new DataInputStream(inFromServer));
            setsUsername(this.sUsername);
            sMessage = encryptPassword(getsUsername()) + "@" + fTemp + "#" + fTemp;
            dtoutstrmToServer.writeUTF(sMessage);
            if ((sMessage = dtinstrmBackToClient.readUTF()).equals("420")) {
                dtoutstrmToServer.writeUTF(encryptPassword(getsUsername()) + "@" + Client.USER_FREE);
            }
            chckthrdConnectionConfirmation.start();
            while (chckthrdConnectionConfirmation.isAlive()) {
            }
        }catch (SocketTimeoutException e){
            return;
        } catch (ConnectException e){
            return;
        } catch (IOException e) {
            return;
        } catch (Exception e){
            return;
        }
    }
    public void SendUserStatus(String sStatus){
        sTempStatus = sStatus;
        Runnable rnbleUserCommunication = new Runnable() {
            @Override
            public void run() {
                try {
                    dtoutstrmToServer.writeUTF(encryptPassword(getsUsername())+"@"+sTempStatus);
                } catch (IOException e) {
                    Log.println(Log.ERROR, "Connection", "Fail to send status update");
                }
            }
        };
        ExecutorService excutor = Executors.newCachedThreadPool();
        excutor.submit(rnbleUserCommunication);
    }
    public void SendUserPosition(LatLng ltlngPosition) {
        ltlngTempPosition = ltlngPosition;
        Runnable rnbleUserCommunication = new Runnable() {
            @Override
            public void run() {
                try {
                    dtoutstrmToServer.writeUTF(encryptPassword(getsUsername()) + "@" + ltlngTempPosition.latitude + "#" + ltlngTempPosition.longitude);
                } catch (IOException e) {
                    Log.println(Log.ERROR, "Connection", "Fail to send position update");
                }
            }
        };
        ExecutorService excutor = Executors.newCachedThreadPool();
        excutor.submit(rnbleUserCommunication);
    }
    private static String encryptPassword(String password)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    public Socket getSktClient() {
        return sktClient;
    }

    public void setSktClient(Socket sktClient) {
        this.sktClient = sktClient;
    }

    public DataOutputStream getDtoutstrmToServer() {
        return dtoutstrmToServer;
    }

    public void setDtoutstrmToServer(DataOutputStream dtoutstrmToServer) {
        this.dtoutstrmToServer = dtoutstrmToServer;
    }

    public DataInputStream getDtinstrmBackToClient() {
        return dtinstrmBackToClient;
    }

    public void setDtinstrmBackToClient(DataInputStream dtinstrmBackToClient) {
        this.dtinstrmBackToClient = dtinstrmBackToClient;
    }

    public InputStream getInFromServer() {
        return inFromServer;
    }

    public void setInFromServer(InputStream inFromServer) {
        this.inFromServer = inFromServer;
    }

    public String getsUsername() {
        return this.sUsername;
    }

    public void setsUsername(String sUsername) {
        this.sUsername = sUsername;
    }

    public OutputStream getOutToServer() {
        return outToServer;
    }

    public void setOutToServer(OutputStream outToServer) {
        this.outToServer = outToServer;
    }
    public Boolean getbPosServerConnected() {
        System.out.println("Hey");
        return bPosServerConnected;
    }

    public void setbPosServerConnected(Boolean bPosServerConnected) {
        this.bPosServerConnected = bPosServerConnected;
    }
    private class CheckConnectionThread extends Thread{
        public void run() {
            String sMessage;
            try {
                dtoutstrmToServer.writeUTF(encryptPassword(getsUsername())+"@"+"Roger");
                if((sMessage = dtinstrmBackToClient.readUTF()).equals("Copy")){
                    setbPosServerConnected(true);
                }
            } catch (IOException e) {
                Log.println(Log.ERROR, "Connection", "Fail to connect to the server");
            }
        }
    }
}