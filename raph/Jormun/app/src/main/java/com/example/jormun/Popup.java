package com.example.jormun;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Popup extends Fragment {
    private FragmentTransaction fragtransCurrent;

    private Team currTeam;

    private int iIDLayout;

    private String sType;

    private DBManager dbmgrSecretary;

    private InternalDBManager interndbSecretary;

    private FragmentManager fragmgrCurrent;

    private MapsActivity mpactCurrent;

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

    public InternalDBManager getInterndbSecretary() {
        return interndbSecretary;
    }

    public void setInterndbSecretary(InternalDBManager interndbSecretary) {
        this.interndbSecretary = interndbSecretary;
    }

    public Team getCurrTeam() {
        return currTeam;
    }

    public void setCurrTeam(Team currTeam) {
        this.currTeam = currTeam;
    }

    public MapsActivity getMpactCurrent() {
        return mpactCurrent;
    }

    public void setMpactCurrent(MapsActivity mpactCurrent) {
        this.mpactCurrent = mpactCurrent;
    }

}
