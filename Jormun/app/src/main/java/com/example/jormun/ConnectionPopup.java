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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class ConnectionPopup extends Popup {

    private EditText edtxtUsername;
    private EditText edtxtPassword;

    private Button btnConfirm;

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
        setEdtxtPassword((EditText)vwConnectionPopup.findViewById(R.id.edttxtConPassword));
        setEdtxtUsername((EditText) vwConnectionPopup.findViewById(R.id.edttxtConUsername));

        getBtnConfirm().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                ConnectUser(vwCurrent);
            }
        });
        return vwConnectionPopup;
    }
    private void ConnectUser(View vwCurrent){
        String sUserTk;
        String sCatchAnswer;

        if(CheckIfNull(getEdtxtPassword().getText()) && CheckIfNull(getEdtxtUsername().getText())) {
            sUserTk=getFlmgrArchivist().Lookup(""+getEdtxtUsername().getText());
            if(sUserTk==""){
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
                if(sCatchAnswer.split("#")[0].equals("Result")){
                    getFlmgrArchivist().WriteLine(getEdtxtUsername().getText()+"#"+sCatchAnswer.split("#")[1]+"#True");
                }
                Toast.makeText(vwCurrent.getContext(), getsAnswer(),Toast.LENGTH_LONG).show();
            }else{
                setsPostRequest("sRequest=Connection#PARAMSEP#sUsername=" + getEdtxtUsername().getText()+"&sPassword="+getEdtxtPassword().getText()+"&sToken="+sUserTk);

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
                if(sCatchAnswer.split("#")[0].equals("Result")){
                    getFlmgrArchivist().Replace(getEdtxtUsername().getText()+"");
                }
                Toast.makeText(vwCurrent.getContext(), getsAnswer(),Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(vwCurrent.getContext(),"Erreur Formulaire incomplet",Toast.LENGTH_LONG).show();
            UnderlineError();
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
