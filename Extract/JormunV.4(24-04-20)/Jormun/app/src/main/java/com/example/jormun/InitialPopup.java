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
        return vwInitialPopup;
    }

    ////////////
    ///Action///
    ////////////

    public void InitialSubscribeClicked(View vwCurrent){
        Popup ppupCurrent = new SubscriptionPopup();
        ppupCurrent.setFragmgrCurrent(getFragmgrCurrent());
        ppupCurrent.setFlmgrArchivist(getFlmgrArchivist());
        ppupCurrent.setDbmgrSecretary(getDbmgrSecretary());
        FragmentTransaction fragtrans = getFragmgrCurrent().beginTransaction();
        fragtrans.addToBackStack(null);
        fragtrans.replace(R.id.map,ppupCurrent,null);
        fragtrans.commit();
    }
    public void InitialPlayClicked(View vwCurrent){
        Popup ppupCurrent;
        if(getFlmgrArchivist().CheckTokenExistence()){
            if(getFlmgrArchivist().CheckIfAlive()!=null){
                ppupCurrent = new LauncherPopup();
            }else{
                ppupCurrent = new ConnectionPopup();
            }
        }else{
            ppupCurrent = new SubscriptionPopup();
        }
        ppupCurrent.setFragmgrCurrent(getFragmgrCurrent());
        ppupCurrent.setFlmgrArchivist(getFlmgrArchivist());
        ppupCurrent.setDbmgrSecretary(getDbmgrSecretary());
        FragmentTransaction fragtrans = getFragmgrCurrent().beginTransaction();
        fragtrans.addToBackStack(null);
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
}
