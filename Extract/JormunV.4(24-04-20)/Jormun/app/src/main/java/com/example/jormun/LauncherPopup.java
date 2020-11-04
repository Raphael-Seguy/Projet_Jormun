package com.example.jormun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

            }
        });
        return vwLauncherPopup;
    }

    public String getsCurrToken() {
        return sCurrToken;
    }

    public void setsCurrToken(String sCurrToken) {
        this.sCurrToken = sCurrToken;
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
