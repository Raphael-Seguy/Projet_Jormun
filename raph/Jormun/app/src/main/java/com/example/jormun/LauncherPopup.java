package com.example.jormun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONObject;

public class LauncherPopup extends Popup {
    private String sCurrToken;

    private Button btnLnchrPlay;
    private Button btnChange;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vwLauncherPopup;

        vwLauncherPopup = inflater.inflate(R.layout.admin_popup_launcher,container,false);
        setiIDLayout(R.layout.admin_popup_launcher);
        setBtnChange((Button)vwLauncherPopup.findViewById(R.id.btnChange));
        setBtnLnchrPlay((Button)vwLauncherPopup.findViewById(R.id.btnLnchrPlay));

        getBtnChange().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                Popup PopupConnection = new ConnectionPopup();
                PopupConnection.setDbmgrSecretary(getDbmgrSecretary());
                PopupConnection.setInterndbSecretary(getInterndbSecretary());
                PopupConnection.setFragmgrCurrent(getFragmgrCurrent());
                PopupConnection.setCurrTeam(getCurrTeam());
                PopupConnection.setMpactCurrent(getMpactCurrent());
                FragmentTransaction fragtrans = getFragmentManager().beginTransaction();
                fragtrans.addToBackStack("Start");
                fragtrans.add(R.id.map,PopupConnection,null);
                fragtrans.commit();
            }
        });
        sCurrToken = getInterndbSecretary().FetchCurrentToken();
        if(sCurrToken.equals("")){
            Popup PopupConnection = new ConnectionPopup();
            PopupConnection.setDbmgrSecretary(getDbmgrSecretary());
            PopupConnection.setInterndbSecretary(getInterndbSecretary());
            PopupConnection.setFragmgrCurrent(getFragmgrCurrent());
            PopupConnection.setCurrTeam(getCurrTeam());
            PopupConnection.setMpactCurrent(getMpactCurrent());
            FragmentTransaction fragtrans = getFragmentManager().beginTransaction();
            fragtrans.addToBackStack("Connection");
            fragtrans.add(R.id.map,PopupConnection,null);
            fragtrans.commit();
        }
        getBtnLnchrPlay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupGame();
            }
        });
        return vwLauncherPopup;
    }
    public void SetupGame() {
        GrabUserHeroes(sCurrToken,true);
        GrabUserTeam(sCurrToken,true);
        getMpactCurrent().setCltCurrent(new Client("91.176.180.132",2501,sCurrToken,getMpactCurrent().getLtlngCurrent(),getMpactCurrent().getLtlngFarRightCorner(),getMpactCurrent().getLtlngFarLeftCorner(),getMpactCurrent().getLtlngNearRightCorner(),getMpactCurrent().getLtlngNearLeftCorner()));
        MoveBackToMapView();
    }
    public void GrabUserTeam(String sToken,boolean bPlayerRegistered){
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
            e.printStackTrace();
        }
    }
    public void MoveBackToMapView(){
        getFragmgrCurrent().popBackStack("Maps", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    public Button getBtnLnchrPlay() {
        return btnLnchrPlay;
    }

    public void setBtnLnchrPlay(Button btnPlay) {
        this.btnLnchrPlay = btnPlay;
    }

    public Button getBtnChange() {
        return btnChange;
    }

    public void setBtnChange(Button btnChange) {
        this.btnChange = btnChange;
    }


}
