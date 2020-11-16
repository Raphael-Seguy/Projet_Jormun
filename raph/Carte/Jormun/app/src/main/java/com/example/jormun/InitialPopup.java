package com.example.jormun;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

public class InitialPopup extends Popup {

    private Button btnInitPlay;
    private Button btnInitConnect;
    private Button btnInitSubscribe;
    private Button btnInitLeave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vwInitialPopup;

        vwInitialPopup = inflater.inflate(R.layout.admin_popup_initial,container,false);
        setiIDLayout(R.layout.admin_popup_initial);
        setBtnInitLeave((Button)vwInitialPopup.findViewById(R.id.btnInitLeave));
        setBtnInitPlay((Button)vwInitialPopup.findViewById(R.id.btnInitPlay));
        setBtnInitSubscribe((Button)vwInitialPopup.findViewById(R.id.btnInitSubscribe));
        setBtnInitConnect((Button)vwInitialPopup.findViewById(R.id.btnInitConnect));

        getBtnInitSubscribe().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                InitialSubscribeClicked(vwCurrent);
            }
        });
        getBtnInitLeave().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                InitialLeaveClicked(vwCurrent);
            }
        });
        getBtnInitPlay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                InitialPlayClicked(vwCurrent);
            }
        });
        getBtnInitConnect().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vwCurrent) {
                InitialConnectClicked(vwCurrent);
            }
        });
        return vwInitialPopup;
    }

    ////////////
    ///Action///
    ////////////
    public void InitialConnectClicked(View vwCurrent){
        Popup ppupCurrent = new ConnectionPopup();
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

    public void InitialSubscribeClicked(View vwCurrent){
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
    public void InitialPlayClicked(View vwCurrent){
        Popup ppupCurrent;
        if(getInterndbSecretary().CheckToken()>0 && !getInterndbSecretary().CheckAliveToken(getDbmgrSecretary()).equals("0#0")){
            ppupCurrent = new LauncherPopup();
        }else{
            ppupCurrent = new ConnectionPopup();
        }

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
    public void InitialLeaveClicked(View vwCurrent){
        System.exit(0);
    }

    public Button getBtnInitPlay() {
        return btnInitPlay;
    }

    public void setBtnInitPlay(Button btnInitPlay) {
        this.btnInitPlay = btnInitPlay;
    }

    public Button getBtnInitSubscribe() {
        return btnInitSubscribe;
    }

    public void setBtnInitSubscribe(Button btnInitSubscribe) {
        this.btnInitSubscribe = btnInitSubscribe;
    }
    public Button getBtnInitLeave() {
        return btnInitLeave;
    }

    public void setBtnInitLeave(Button btnLeave) {
        this.btnInitLeave = btnLeave;
    }

    public Button getBtnInitConnect() {
        return btnInitConnect;
    }

    public void setBtnInitConnect(Button btnInitConnect) {
        this.btnInitConnect = btnInitConnect;
    }


}
