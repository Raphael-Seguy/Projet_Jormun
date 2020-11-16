package com.example.jormun;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Iterator;


public class ConnectionPopup extends Popup {

    private EditText edtxtUsername;
    private EditText edtxtPassword;

    private Button btnConfirm;
    private Button btnSubscribe;

    private String sPostRequest;
    private String sAnswer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vwConnectionPopup;

        vwConnectionPopup = inflater.inflate(R.layout.admin_popup_connexion,container,false);
        setiIDLayout(R.layout.admin_popup_connexion);
        setsType("Connection");
        setBtnConfirm((Button)vwConnectionPopup.findViewById(R.id.btnConConfirm));
        setBtnSubscribe((Button)vwConnectionPopup.findViewById(R.id.btnConSubscribe));
        setEdtxtPassword((EditText)vwConnectionPopup.findViewById(R.id.edttxtConPassword));
        setEdtxtUsername((EditText) vwConnectionPopup.findViewById(R.id.edttxtConUsername));
        getInterndbSecretary().UnbondEveryone();
        getBtnConfirm().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                ConnectUser(vwCurrent);
            }
        });
        getBtnSubscribe().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popup ppupCurrent = new SubscriptionPopup();
                ppupCurrent.setFragmgrCurrent(getFragmgrCurrent());
                ppupCurrent.setInterndbSecretary(getInterndbSecretary());
                ppupCurrent.setDbmgrSecretary(getDbmgrSecretary());
                ppupCurrent.setCurrTeam(getCurrTeam());
                ppupCurrent.setMpactCurrent(getMpactCurrent());
                FragmentTransaction fragtrans = getFragmgrCurrent().beginTransaction();
                fragtrans.addToBackStack("Start");
                fragtrans.replace(R.id.map,ppupCurrent,null);
                fragtrans.commit();
            }
        });
        return vwConnectionPopup;
    }
    private void ConnectUser(View vwCurrent){
        String sUserTk;
        String sFinalTk;
        String sCatchAnswer;

        if(CheckIfNull(getEdtxtPassword().getText()) && CheckIfNull(getEdtxtUsername().getText())) {
            sUserTk=getInterndbSecretary().CheckIfUserExist(getEdtxtUsername().getText().toString());
            if(sUserTk.split("#")[0].equals("0")){
                setsPostRequest("sRequest=Connection#PARAMSEP#sUsername=" + getEdtxtUsername().getText()+"&sPassword="+getEdtxtPassword().getText()+"&sToken=Requesting");

                Thread thrdRequest = new Thread(){
                    @Override
                    public void run() {
                        setsAnswer(getDbmgrSecretary().AskDB(DBManager.sREQUEST_POST,getsPostRequest()));
                    }
                };
                thrdRequest.start();
                while(thrdRequest.isAlive()) {
                }
                sCatchAnswer=getsAnswer();
                sFinalTk = sCatchAnswer.split("#")[1];
                getMpactCurrent().SetCurrentToken(sFinalTk);
                if(sCatchAnswer.split("#")[0].equals("Connection")){
                    getInterndbSecretary().RegisterNewPlayer(sCatchAnswer.split("#")[1],getEdtxtUsername().getText().toString());
                    SetupGame(false);
                }
                Toast.makeText(vwCurrent.getContext(), getsAnswer(),Toast.LENGTH_LONG).show();
            }else if(sUserTk.split("#")[0].equals("1")){
                setsPostRequest("sRequest=Connection#PARAMSEP#sUsername=" + getEdtxtUsername().getText()+"&sPassword="+getEdtxtPassword().getText()+"&sToken="+sUserTk.split("#")[1]);
                Thread thrdRequest = new Thread(){
                    @Override
                    public void run() {
                        setsAnswer(getDbmgrSecretary().AskDB(DBManager.sREQUEST_POST,getsPostRequest()));
                    }
                };
                thrdRequest.start();
                while(thrdRequest.isAlive()){
                }
                sCatchAnswer=getsAnswer();
                sFinalTk = sCatchAnswer.split("#")[1];
                getMpactCurrent().SetCurrentToken(sFinalTk);
                if(sCatchAnswer.split("#")[0].equals("Connection")){
                    getInterndbSecretary().ChangePlayerState(sFinalTk,true);
                    SetupGame(true);
                }
                Toast.makeText(vwCurrent.getContext(), getsAnswer(),Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(vwCurrent.getContext(),"Erreur Token"+sUserTk.split("#")[0],Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(vwCurrent.getContext(),"Erreur Formulaire incomplet",Toast.LENGTH_LONG).show();
            UnderlineError();
        }
    }
    public void SetupGame(boolean bPlayerRegistered) {
        Thread thrdCLeanup = new Thread(){
            @Override
            public void run() {
                getMpactCurrent().Update();
            }
        };
        thrdCLeanup.start();
        while(thrdCLeanup.isAlive()){}
        Hero hrotmp;
        JSONObject jsnHeroes;
        getMpactCurrent().StartUpdateThread();
        jsnHeroes = GrabUserHeroes(getMpactCurrent().GrabCurrentToken(),bPlayerRegistered);
        if(jsnHeroes.keys().hasNext()){
            hrotmp = new Hero();
            for (Iterator<String> it = jsnHeroes.keys(); it.hasNext(); ) {
                String temp = it.next();
                try {
                    hrotmp.LoadHero(new JSONObject(temp));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else{
            hrotmp = new Hero("Tom",Hero.sCLASS_APPRENTIS,Hero.sCLASS_MERCENAIRE);
            System.out.println("Current Token "+getMpactCurrent().GrabCurrentToken());
            hrotmp.SaveHero(getInterndbSecretary().getDirmgrCurrent(),getMpactCurrent().GrabCurrentToken()+".json");
        }
        JSONObject jsnTeam = GrabUserTeam(getMpactCurrent().GrabCurrentToken(),bPlayerRegistered);
        getMpactCurrent().setCltCurrent(new Client(getInterndbSecretary(),"91.176.180.132",2501,getMpactCurrent().GrabCurrentToken(),getMpactCurrent().getLtlngCurrent(),getMpactCurrent().getLtlngFarRightCorner(),getMpactCurrent().getLtlngFarLeftCorner(),getMpactCurrent().getLtlngNearRightCorner(),getMpactCurrent().getLtlngNearLeftCorner()));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GrabUserVillage(getMpactCurrent().GrabCurrentToken());
        GrabUserStructure(getMpactCurrent().GrabCurrentToken());
        GrabPoints(getMpactCurrent().getLtlngNearLeftCorner().latitude,getMpactCurrent().getLtlngNearLeftCorner().longitude,getMpactCurrent().getLtlngFarRightCorner().latitude,getMpactCurrent().getLtlngFarRightCorner().longitude);
        getMpactCurrent().SetOtherUser(getInterndbSecretary());

        MoveBackToMapView();
    }
    public void GrabPoints(double dMinLat,double dMinLong,double dMaxLat,double dMaxLong){
        String sAnswer;
        String sQuery;
        String sTYPE;
        String sELEMENTKEY;
        String[] a_sObject;
        String[] a_sInformation;

        int iIDREF;
        int iID;

        float fLat;
        float fLong;


        sQuery="sRequest=FetchMapPart#PARAMSEP#fMinLat="+dMinLat+"&fMinLong="+dMinLong+"&fMaxLat="+dMaxLat+"&fMaxLong="+dMaxLong;
        sAnswer=getDbmgrSecretary().threadedRequest(DBManager.sREQUEST_POST,sQuery);
        a_sObject = sAnswer.split("#LINESEPARATOR#");
        for(String sLine : a_sObject){
            sLine = sLine.substring(1);
            a_sInformation = sLine.split("@");
            sELEMENTKEY = a_sInformation[5].split(":")[1];
            sTYPE = a_sInformation[3].split(":")[1];
            fLat = Float.parseFloat(a_sInformation[1].split(":")[1]);
            fLong = Float.parseFloat(a_sInformation[2].split(":")[1]);
            iIDREF = Integer.parseInt(a_sInformation[4].split(":")[1]);
            iID = getInterndbSecretary().EncodeTable(getDbmgrSecretary(),getMpactCurrent().GrabCurrentToken(),sTYPE,iIDREF);
            getInterndbSecretary().RegisterObject(fLat,fLong,sTYPE,iIDREF,iID,sELEMENTKEY);
        }
    }

    public void GrabUserStructure(String sToken){
        String sQuery;
        String sTemp;
        String sAnswer;
        JSONObject jsnTeam;
        try{
            sQuery="sRequest=FetchUserStructure#PARAMSEP#sToken="+sToken;
            sAnswer=getDbmgrSecretary().threadedRequest(DBManager.sREQUEST_POST,sQuery);

            if(!sAnswer.equals("")){
                jsnTeam = new JSONObject(sAnswer);
                sTemp = getInterndbSecretary().saveJSON(sToken,DirectoryManager.DIR_structure,jsnTeam);
                getInterndbSecretary().SavePlayersStructure(sToken, sTemp);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void GrabUserVillage(String sToken){
        String sQuery;
        String sTemp;
        String sAnswer;
        JSONObject jsnTeam;
        try{
            sQuery="sRequest=FetchUserVillage#PARAMSEP#sToken="+sToken;
            sAnswer=getDbmgrSecretary().threadedRequest(DBManager.sREQUEST_POST,sQuery);

            if(!sAnswer.equals("")){
                jsnTeam = new JSONObject(sAnswer);
                sTemp = getInterndbSecretary().saveJSON(sToken,DirectoryManager.DIR_village,jsnTeam);
                getInterndbSecretary().SavePlayersVillage(sToken, sTemp);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public JSONObject GrabUserTeam(String sToken,boolean bPlayerRegistered){
        String sQuery;
        String sTemp;
        String sAnswer;
        JSONObject jsnTeam;
        try{
            sQuery="sRequest=FetchUserTeam#PARAMSEP#sToken="+sToken;
            sAnswer=getDbmgrSecretary().threadedRequest(DBManager.sREQUEST_POST,sQuery);

            if(sAnswer!="" && false){
                jsnTeam = new JSONObject(sAnswer);
            }else {
                jsnTeam = new JSONObject();
            }

            sTemp = getInterndbSecretary().saveJSON(sToken,DirectoryManager.DIR_team,jsnTeam);
            if(!bPlayerRegistered) {
                getInterndbSecretary().SavePlayersHero(sToken, sTemp);
            }
        }catch (Exception e) {
            jsnTeam=null;
            e.printStackTrace();
        }
        return jsnTeam;
    }
    public JSONObject GrabUserHeroes(String sToken, boolean bPlayerRegistered) {
        String sQuery;
        String sTemp;
        String sAnswer;

        JSONObject jsnHeroes;
        try{
            sQuery="sRequest=FetchUserHeroes#PARAMSEP#sToken="+sToken;
            sAnswer = getDbmgrSecretary().threadedRequest(DBManager.sREQUEST_POST,sQuery);
            if(sAnswer!="" && false){
                jsnHeroes = new JSONObject(sAnswer);
            }else {
                jsnHeroes = new JSONObject();
            }
            sTemp = getInterndbSecretary().saveJSON(sToken,DirectoryManager.DIR_hero,jsnHeroes);
            if(!bPlayerRegistered) {
                getInterndbSecretary().SavePlayersHero(sToken, sTemp);
            }
        }catch (Exception e) {
            jsnHeroes = null;
            e.printStackTrace();
        }
        return jsnHeroes;
    }
    public void MoveBackToMapView(){
        getFragmgrCurrent().popBackStackImmediate("Maps", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    private boolean CheckIfNull(Editable text) {
        if(text!=null && text.length()>0){
            return true;
        }
        return false;
    }

    private void UnderlineError() {
        EditText edittxtCurrent;

        edittxtCurrent = getEdtxtUsername();
        if(CheckIfNull(edittxtCurrent.getText())){
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.coloorRightInput));
        }else{
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.coloorWrongInput));
        }
        edittxtCurrent = getEdtxtPassword();
        if(CheckIfNull(edittxtCurrent.getText())){
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.coloorRightInput));
        }else{
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.coloorWrongInput));
        }
    }

    public EditText getEdtxtUsername() {
        return edtxtUsername;
    }

    public void setEdtxtUsername(EditText edtxtUsername) {
        this.edtxtUsername = edtxtUsername;
    }

    public EditText getEdtxtPassword() {
        return edtxtPassword;
    }

    public void setEdtxtPassword(EditText edtxtPassword) {
        this.edtxtPassword = edtxtPassword;
    }

    public Button getBtnConfirm() {
        return btnConfirm;
    }

    public void setBtnConfirm(Button btnConfirm) {
        this.btnConfirm = btnConfirm;
    }
    public String getsPostRequest() {
        return sPostRequest;
    }

    public void setsPostRequest(String sPostRequest) {
        this.sPostRequest = sPostRequest;
    }

    public String getsAnswer() {
        return sAnswer;
    }

    public void setsAnswer(String sAnswer) {
        this.sAnswer = sAnswer;
    }

    public Button getBtnSubscribe() {
        return btnSubscribe;
    }

    public void setBtnSubscribe(Button btnSubscribe) {
        this.btnSubscribe = btnSubscribe;
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
}
