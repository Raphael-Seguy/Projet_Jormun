package com.example.jormun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ErrorPopup extends Popup {

    private Button btnErrorLeave;
    private Button btnErrorDemo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vwErrorPopup;

        vwErrorPopup = inflater.inflate(R.layout.admin_popup_error,container,false);

        setiIDLayout(R.layout.admin_popup_error);
        setsType("Error");
        setBtnErrorDemo((Button)vwErrorPopup.findViewById(R.id.btnErrorDemo));
        setBtnErrorLeave((Button)vwErrorPopup.findViewById(R.id.btnErrorLeave));

        getBtnErrorDemo().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        getBtnErrorLeave().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        return vwErrorPopup;
    }

    public Button getBtnErrorLeave() {
        return btnErrorLeave;
    }

    public void setBtnErrorLeave(Button btnErrorLeave) {
        this.btnErrorLeave = btnErrorLeave;
    }

    public Button getBtnErrorDemo() {
        return btnErrorDemo;
    }

    public void setBtnErrorDemo(Button btnErrorDemo) {
        this.btnErrorDemo = btnErrorDemo;
    }

}
