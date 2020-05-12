package com.example.jormun;

import android.app.Activity;
import android.text.Layout;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Popup extends Fragment {
    private FragmentTransaction fragtransCurrent;

    private int iIDLayout;

    private String sType;

    private DBManager dbmgrSecretary;

    private FileManager flmgrArchivist;

    private FragmentManager fragmgrCurrent;

    public FragmentManager getFragmgrCurrent() {
        return fragmgrCurrent;
    }

    public void setFragmgrCurrent(FragmentManager fragmgrCurrent) {
        this.fragmgrCurrent = fragmgrCurrent;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    public int getiIDLayout() {
        return iIDLayout;
    }

    public void setiIDLayout(int iIDLayout) {
        this.iIDLayout = iIDLayout;
    }

    public DBManager getDbmgrSecretary() {
        return dbmgrSecretary;
    }

    public void setDbmgrSecretary(DBManager dbmgrSecretary) {
        this.dbmgrSecretary = dbmgrSecretary;
    }

    public FileManager getFlmgrArchivist() {
        return flmgrArchivist;
    }

    public void setFlmgrArchivist(FileManager flmgrArchivist) {
        this.flmgrArchivist = flmgrArchivist;
    }
}
