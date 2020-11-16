package com.example.jormun;

import android.os.Bundle;
import android.os.Looper;
import android.util.JsonWriter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.common.internal.IGmsServiceBroker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class LauncherPopup extends Popup {

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
        getMpactCurrent().SetCurrentToken(getInterndbSecretary().FetchCurrentToken());
        if(getMpactCurrent().GrabCurrentToken().equals("")){
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
        Thread thrdCLeanup = new Thread(){

            public void run() {
                Looper.prepare();
                getMpactCurrent().Update();
            }
        };
        thrdCLeanup.start();
        while(thrdCLeanup.isAlive()){}
        Hero hrotmp;
        JSONObject jsnHeroes;
        getMpactCurrent().StartUpdateThread();
        jsnHeroes = GrabUserHeroes(getMpactCurrent().GrabCurrentToken());
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
            hrotmp.SaveHero(getInterndbSecretary().getDirmgrCurrent(),getMpactCurrent().GrabCurrentToken()+".json");
        }
        JSONObject jsnTeam = GrabUserTeam(getMpactCurrent().GrabCurrentToken());
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
    public JSONObject GrabUserTeam(String sToken){
        String sQuery;
        String sTemp;
        String sAnswer;
        JSONObject jsnTeam;
        try{
            sQuery="sRequest=FetchUserTeam#PARAMSEP#sToken="+sToken;
            sAnswer=getDbmgrSecretary().threadedRequest(DBManager.sREQUEST_POST,sQuery);

            if(!sAnswer.equals("")){
                jsnTeam = new JSONObject(sAnswer);
            }else {
                jsnTeam = new JSONObject();
            }

            sTemp = getInterndbSecretary().saveJSON(sToken,DirectoryManager.DIR_team,jsnTeam);
            getInterndbSecretary().SavePlayersTeam(sToken, sTemp);
        }catch (Exception e) {
            jsnTeam=null;
            e.printStackTrace();
        }
        return jsnTeam;
    }
    public JSONObject GrabUserHeroes(String sToken) {
        String sQuery;
        String sTemp;
        String sAnswer;

        JSONObject jsnHeroes;
        try{
            sQuery="sRequest=FetchUserHeroes#PARAMSEP#sToken="+sToken;
            sAnswer = getDbmgrSecretary().threadedRequest(DBManager.sREQUEST_POST,sQuery);
            if(!sAnswer.equals("")){
                jsnHeroes = new JSONObject(sAnswer);
            }else {
                jsnHeroes = new JSONObject();
            }
            sTemp = getInterndbSecretary().saveJSON(sToken,DirectoryManager.DIR_hero,jsnHeroes);
            getInterndbSecretary().SavePlayersHero(sToken, sTemp);
        }catch (Exception e) {
            jsnHeroes = null;
            e.printStackTrace();
        }
        return jsnHeroes;
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
