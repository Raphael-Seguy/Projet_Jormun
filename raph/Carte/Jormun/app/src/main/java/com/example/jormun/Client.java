package com.example.jormun;

import android.util.Log;

import com.example.jormun.OtherUser;
import com.google.android.gms.maps.model.LatLng;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client{
    public static final String USER_PAUSE = "BUSY";
    public static final String USER_FREE = "FREE";
    public static final String USER_EXITING = "EXITING";

    public ArrayList<OtherUser> al_ousrUser = new ArrayList<OtherUser>();
    public ArrayList<OtherUser> al_ousrOldUser = new ArrayList<>();

    private LatLng ltlngCenter;
    private LatLng ltlngCornerFL;
    private LatLng ltlngCornerFR;
    private LatLng ltlngCornerNL;
    private LatLng ltlngCornerNR;

    InternalDBManager internDb;

    OutputStream outToServer;

    Socket sktClient;

    private DataOutputStream dtoutstrmToServer;

    private DataInputStream dtinstrmBackToClient;

    InputStream inFromServer;

    String sUsername;
    String sState;

    Runnable rnbleUserSender;
    Runnable rnbleUserReceiver;

    ExecutorService excutor = Executors.newCachedThreadPool();

    private Boolean bPosServerConnected;

    public Client(InternalDBManager internDbInput,String sIp, int iPort, String sToken, final LatLng Center, LatLng FRCORNER, LatLng FLCORNER, LatLng NRCORNER, LatLng NLCORNER){
        rnbleUserSender = new Runnable() {
            @Override
            public void run() {
                try {
                    do {
                        dtoutstrmToServer.writeUTF(getsUsername()+"#"+ltlngCenter.latitude+"/"+ltlngCenter.longitude+"#"+ltlngCornerFR.latitude+"/"+ltlngCornerFR.longitude+"#"+ltlngCornerFL.latitude+"/"+ltlngCornerFL.longitude+"#"+ltlngCornerNR.latitude+"/"+ltlngCornerNR.longitude+"#"+ltlngCornerNL.latitude+"/"+ltlngCornerNL.longitude+"#"+sState);
                    } while (getbPosServerConnected());
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException e){
                    }
                } catch (IOException e) {
                    setbPosServerConnected(false);
                }
            }
        };
        rnbleUserReceiver = new Runnable() {
            @Override
            public void run() {
                String sData;
                String sTemp;
                String sUsertk;
                Float fLat;
                Float fLong;
                try {
                    do {
                        sData = getDtinstrmBackToClient().readUTF();
                        if(sData.contains("@") && sData.contains("#")) {
                            sTemp = sData.split("@")[1];
                            sUsertk = sData.split("@")[0];
                            fLat = Float.parseFloat(sTemp.split("#")[0]);
                            fLong = Float.parseFloat(sTemp.split("#")[1]);
                            if(CheckToken(sUsertk)) {
                                getAl_ousrUser().get(GetIndexOf(sUsertk)).setfLat(fLat);
                                getAl_ousrUser().get(GetIndexOf(sUsertk)).setbUpdated(true);
                                getAl_ousrUser().get(GetIndexOf(sUsertk)).setfLong(fLong);
                            }else {
                                getAl_ousrUser().add(new OtherUser(sUsertk, fLat, fLong));
                            }
                        }else if(sData.contains("@") && sData.split("@")[0].equals("SERVER")){
                            if(sData.split("@")[1].equals("STOPPING")) {
                                setbPosServerConnected(false);
                            }
                        }
                        Thread thrdCLeanup = new Thread(){
                            @Override
                            public void run() {
                                ArrayList<OtherUser>al_Temp;
                                ArrayList<OtherUser>al_Temp2;

                                al_Temp = getAl_ousrUser();
                                for (OtherUser ousrUser: al_Temp) {
                                    if(ousrUser.isbToRemove()){
                                        getAl_ousrOldUser().add(ousrUser);
                                    }
                                }
                                al_Temp2=getAl_ousrOldUser();
                                if(al_Temp2.size()>0){
                                    for(OtherUser ousrCurrent:al_Temp2){
                                        getInternDb().RemovePlayer(ousrCurrent.sToken);
                                        getAl_ousrUser().remove(getAl_ousrUser().indexOf(ousrCurrent));
                                    }
                                    getAl_ousrOldUser().clear();
                                }
                            }
                        };
                        thrdCLeanup.start();
                        while(thrdCLeanup.isAlive()){}
                    } while (getbPosServerConnected());
                } catch (Exception e) {
                    System.out.println("We had an issue");
                    setbPosServerConnected(false);
                }
            }
        };
        setLtlngCenter(Center);
        setLtlngCornerNR(NRCORNER);
        setLtlngCornerNL(NLCORNER);
        setLtlngCornerFR(FRCORNER);
        setLtlngCornerFL(FLCORNER);
        setsUsername(sToken);
        setInternDb(internDbInput);
        new Thread(){
            @Override
            public void run() {
                Socket sktTemp;
                String sIntroduction = getsUsername()+"#"+getLtlngCenter().latitude+"/"+getLtlngCenter().longitude+"#"+getLtlngCornerFR().latitude+"/"+getLtlngCornerFR().longitude+"#"+getLtlngCornerFL().latitude+"/"+getLtlngCornerFL().longitude+"#"+getLtlngCornerNR().latitude+"/"+getLtlngCornerNR().longitude+"#"+getLtlngCornerNL().latitude+"/"+getLtlngCornerNL().longitude;
                try {
                    sktTemp = new Socket("91.176.180.132", 2501);
                    dtoutstrmToServer = new DataOutputStream(sktTemp.getOutputStream());
                    dtinstrmBackToClient = new DataInputStream(sktTemp.getInputStream());
                    dtoutstrmToServer.writeUTF(sIntroduction);
                    if (dtinstrmBackToClient.readUTF().equals("CONNECTED")) {
                        setbPosServerConnected(true);
                        setsState(USER_FREE);
                        excutor.submit(rnbleUserReceiver);
                        excutor.submit(rnbleUserSender);
                    } else {
                        setbPosServerConnected(false);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private int GetIndexOf(String sName) {

        int iIndex;
        int iAnswer;

        iIndex=0;
        iAnswer=-1;
        for(OtherUser ousrCurrent : getAl_ousrUser()) {
            if(ousrCurrent.getsToken().equals(sName)) {
                iAnswer=iIndex;
            }
            iIndex+=1;
        }

        return iAnswer;
    }
    private boolean CheckToken(String sName) {
        boolean bExist;

        bExist=false;

        for(OtherUser ousrCurrent : getAl_ousrUser()) {
            if(ousrCurrent.getsToken().equals(sName)) {
                bExist=true;
            }
        }

        return bExist;
    }
    public void CloseConnection(){
        new Thread(){
            @Override
            public void run() {
                try {
                    dtoutstrmToServer.writeUTF(getsUsername()+"#"+getLtlngCenter().latitude+"/"+getLtlngCenter().longitude+"#"+getLtlngCornerFR().latitude+"/"+getLtlngCornerFR().longitude+"#"+getLtlngCornerFL().latitude+"/"+getLtlngCornerFL().longitude+"#"+getLtlngCornerNR().latitude+"/"+getLtlngCornerNR().longitude+"#"+getLtlngCornerNL().latitude+"/"+getLtlngCornerNL().longitude+"#"+USER_EXITING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
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
        return bPosServerConnected;
    }

    public void setbPosServerConnected(Boolean bPosServerConnected) {
        this.bPosServerConnected = bPosServerConnected;
    }
    public ArrayList<OtherUser> getAl_ousrUser() {
        return al_ousrUser;
    }
    public void setAl_ousrUser(ArrayList<OtherUser> al_ousrUser) {
        this.al_ousrUser = al_ousrUser;
    }
    public String getsState() {
        return sState;
    }
    public void setsState(String sState) {
        this.sState = sState;
    }

    public LatLng getLtlngCenter() {
        return ltlngCenter;
    }

    public void setLtlngCenter(LatLng ltlngCenter) {
        this.ltlngCenter = ltlngCenter;
    }

    public LatLng getLtlngCornerFL() {
        return ltlngCornerFL;
    }

    public void setLtlngCornerFL(LatLng ltlngCornerFL) {
        this.ltlngCornerFL = ltlngCornerFL;
    }

    public LatLng getLtlngCornerFR() {
        return ltlngCornerFR;
    }

    public void setLtlngCornerFR(LatLng ltlngCornerFR) {
        this.ltlngCornerFR = ltlngCornerFR;
    }

    public LatLng getLtlngCornerNL() {
        return ltlngCornerNL;
    }

    public void setLtlngCornerNL(LatLng ltlngCornerNL) {
        this.ltlngCornerNL = ltlngCornerNL;
    }

    public LatLng getLtlngCornerNR() {
        return ltlngCornerNR;
    }

    public void setLtlngCornerNR(LatLng ltlngCornerNR) {
        this.ltlngCornerNR = ltlngCornerNR;
    }

    public InternalDBManager getInternDb() {
        return internDb;
    }

    public void setInternDb(InternalDBManager internDb) {
        this.internDb = internDb;
    }

    public ArrayList<OtherUser> getAl_ousrOldUser() {
        return al_ousrOldUser;
    }

    public void setAl_ousrOldUser(ArrayList<OtherUser> al_ousrOldUser) {
        this.al_ousrOldUser = al_ousrOldUser;
    }

    public void UpdatePosition(LatLng ltlngCenter,LatLng ltlngCornerFL,LatLng ltlngCornerFR,LatLng ltlngCornerNL,LatLng ltlngCornerNR){
        setLtlngCenter(ltlngCenter);
        setLtlngCornerFL(ltlngCornerFL);
        setLtlngCornerFR(ltlngCornerFR);
        setLtlngCornerNL(ltlngCornerNL);
        setLtlngCornerNR(ltlngCornerNR);
    }
    private void GetPosition(){
        System.out.println(getLtlngCenter().latitude+"/"+getLtlngCenter().longitude);
        System.out.println(getLtlngCornerNR().latitude+"/"+getLtlngCornerNR().longitude);
        System.out.println(getLtlngCornerNL().latitude+"/"+getLtlngCornerNL().longitude);
        System.out.println(getLtlngCornerFL().latitude+"/"+getLtlngCornerFL().longitude);
        System.out.println(getLtlngCornerFR().latitude+"/"+getLtlngCornerFR().longitude);
    }
}