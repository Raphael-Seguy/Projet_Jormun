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
import androidx.fragment.app.FragmentTransaction;

public class SubscriptionPopup extends Popup {

    Button btnSubscribe;

    EditText edttxtSubPassword;
    EditText edttxtSubUsername;
    EditText edttxtSubMail;

    String sPostRequest;
    String sAnswer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vwSubscriptionPopup;

        vwSubscriptionPopup = inflater.inflate(R.layout.admin_popup_subscription,container,false);
        setiIDLayout(R.layout.admin_popup_subscription);
        setsType("Subscription");
        setBtnSubscribe((Button)vwSubscriptionPopup.findViewById(R.id.btnSubConfirm));
        setEdttxtSubMail((EditText)vwSubscriptionPopup.findViewById(R.id.edttxtSubMail));
        setEdttxtSubPassword((EditText)vwSubscriptionPopup.findViewById(R.id.edttxtSubPassword));
        setEdttxtSubUsername((EditText)vwSubscriptionPopup.findViewById(R.id.edttxtSubUsername));

        getBtnSubscribe().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                ConfirmClicked(vwCurrent);
            }
        });

        return vwSubscriptionPopup;
    }
    private void ConfirmClicked(View vwCurrent){
        String sCatchAnswer;

        if(CheckIfNull(getEdttxtSubMail().getText()) && CheckIfNull(getEdttxtSubPassword().getText())  && CheckIfNull(getEdttxtSubUsername().getText())) {
            setsPostRequest("sRequest=Subscription#PARAMSEP#sUsername=" + getEdttxtSubUsername().getText()+"&sPassword="+getEdttxtSubPassword().getText()+"&sMail="+getEdttxtSubMail().getText());

            Thread thrdRequest = new Thread(){
                @Override
                public void run() {
                    setsAnswer(getDbmgrSecretary().AskDB(DBManager.sREQUEST_POST,getsPostRequest()));
                }
            };
            thrdRequest.start();
            while(thrdRequest.isAlive()){
            }
            sCatchAnswer = getsAnswer();
            if(sCatchAnswer.split("#")[0].equals("Result")){
                Popup PopupConnection = new ConnectionPopup();
                PopupConnection.setMpactCurrent(getMpactCurrent());
                PopupConnection.setDbmgrSecretary(getDbmgrSecretary());
                PopupConnection.setInterndbSecretary(getInterndbSecretary());
                PopupConnection.setFragmgrCurrent(getFragmgrCurrent());
                PopupConnection.setCurrTeam(getCurrTeam());
                FragmentTransaction fragtrans = getFragmentManager().beginTransaction();
                fragtrans.addToBackStack("Start");
                fragtrans.add(R.id.map,PopupConnection,null);
                fragtrans.commit();
            }
            Toast.makeText(vwCurrent.getContext(), getsAnswer(),Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(vwCurrent.getContext(),"Erreur Formulaire incomplet",Toast.LENGTH_LONG).show();
            UnderlineError();
        }
    }
    private void UnderlineError(){
        EditText edittxtCurrent;

        edittxtCurrent = getEdttxtSubUsername();
        if(CheckIfNull(edittxtCurrent.getText())){
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.coloorRightInput));
        }else{
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.coloorWrongInput));
        }
        edittxtCurrent = getEdttxtSubPassword();
        if(CheckIfNull(edittxtCurrent.getText())){
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.coloorRightInput));
        }else{
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.coloorWrongInput));
        }
        edittxtCurrent = getEdttxtSubMail();
        if(CheckIfNull(edittxtCurrent.getText())){
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.coloorRightInput));
        }else{
            edittxtCurrent.setHintTextColor(getResources().getColor(R.color.coloorWrongInput));
        }
    }
    private Boolean CheckIfNull(Editable edittblText){
        if(edittblText!=null && edittblText.length()>0){
            return true;
        }
        return false;
    }
    public Button getBtnSubscribe() {
        return btnSubscribe;
    }

    public void setBtnSubscribe(Button btnSubscribe) {
        this.btnSubscribe = btnSubscribe;
    }

    public EditText getEdttxtSubPassword() {
        return edttxtSubPassword;
    }

    public void setEdttxtSubPassword(EditText edttxtSubPassword) {
        this.edttxtSubPassword = edttxtSubPassword;
    }

    public EditText getEdttxtSubUsername() {
        return edttxtSubUsername;
    }

    public void setEdttxtSubUsername(EditText edttxtSubUsername) {
        this.edttxtSubUsername = edttxtSubUsername;
    }

    public EditText getEdttxtSubMail() {
        return edttxtSubMail;
    }

    public void setEdttxtSubMail(EditText edttxtSubMail) {
        this.edttxtSubMail = edttxtSubMail;
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
}
