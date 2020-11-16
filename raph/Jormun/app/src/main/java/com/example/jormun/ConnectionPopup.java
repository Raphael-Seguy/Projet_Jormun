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

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;


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
                sFinalTk = getsAnswer().split("#")[1];
                getCurrTeam().setsCurrentToken(sFinalTk);
                if(sCatchAnswer.split("#")[0].equals("Connection")){
                    getInterndbSecretary().RegisterNewPlayer(sCatchAnswer.split("#")[1],getEdtxtUsername().getText().toString());
                    SetupGame(sFinalTk,false);
                }
                Toast.makeText(vwCurrent.getContext(), getsAnswer(),Toast.LENGTH_LONG).show();
            }else if(sUserTk.split("#")[0].equals("1")){
                setsPostRequest("sRequest=Connection#PARAMSEP#sUsername=" + getEdtxtUsername().getText()+"&sPassword="+getEdtxtPassword().getText()+"&sToken="+sUserTk.split("#")[2]);
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
                sFinalTk = getsAnswer().split("#")[1];
                getCurrTeam().setsCurrentToken(sFinalTk);
                if(sCatchAnswer.split("#")[0].equals("Connection")){
                    getInterndbSecretary().ChangePlayerState(sUserTk.split("#")[2],true);
                    SetupGame(sFinalTk,true);
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
    public void SetupGame(String sToken, boolean bPlayerRegistered) {
        GrabUserHeroes(sToken,bPlayerRegistered);
        GrabUserTeam(sToken,bPlayerRegistered);
        getMpactCurrent().setCltCurrent(new Client("91.176.180.132",2501,sToken,getMpactCurrent().getLtlngCurrent(),getMpactCurrent().getLtlngFarRightCorner(),getMpactCurrent().getLtlngFarLeftCorner(),getMpactCurrent().getLtlngNearRightCorner(),getMpactCurrent().getLtlngNearLeftCorner()));
        MoveBackToMapView();
    }
    public void MoveBackToMapView(){
        getFragmgrCurrent().popBackStackImmediate("Maps", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    public void GrabUserTeam(String sToken,boolean bPlayerRegistered){
        String sQuery;
        String sTemp;
        String sAnswer;
        JSONObject jsnTeam;
        try{
            sQuery="sRequest=FetchUserTeam#PARAMSEP#sToken="+sToken;
            sAnswer=getDbmgrSecretary().threadedRequest(DBManager.sREQUEST_POST,sQuery);

            if(sAnswer!=""){
                jsnTeam = new JSONObject(sAnswer);
            }else {
                jsnTeam = new JSONObject();
            }
            sTemp = getInterndbSecretary().saveJSON(sToken,DirectoryManager.DIR_team,jsnTeam);
            if(!bPlayerRegistered) {
                getInterndbSecretary().SavePlayersHero(sToken, sTemp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void GrabUserHeroes(String sToken, boolean bPlayerRegistered) {
        String sQuery;
        String sTemp;
        String sAnswer;

        JSONObject jsnHeroes;
        try{
            sQuery="sRequest=FetchUserHeroes#PARAMSEP#sToken="+sToken;
            sAnswer = getDbmgrSecretary().threadedRequest(DBManager.sREQUEST_POST,sQuery);
            if(sAnswer!=""){
                jsnHeroes = new JSONObject(sAnswer);
            }else {
                jsnHeroes = new JSONObject();
            }
            sTemp = getInterndbSecretary().saveJSON(sToken,DirectoryManager.DIR_hero,jsnHeroes);
            if(!bPlayerRegistered) {
                getInterndbSecretary().SavePlayersHero(sToken, sTemp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
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
