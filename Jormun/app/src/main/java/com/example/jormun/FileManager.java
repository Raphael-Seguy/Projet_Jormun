package com.example.jormun;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private String sPath;
    private File flTokens;
    private File dirGame;

    public FileManager(String sPath){
        SetEnvironment();
        setsPath(sPath);
        setFlTokens();
        System.out.println(getFlTokens().getAbsolutePath());
        try {
            System.out.println(getFlTokens().getCanonicalPath());
        }catch (IOException e){
        }
    }
    public void SetEnvironment(){
        File flTemp;
        flTemp = new File(Environment.getExternalStorageDirectory()+File.separator+"Jormun");
        if(!flTemp.exists() || !flTemp.isDirectory()){
            flTemp.mkdir();
        }
        setDirGame(flTemp);
    }
    protected void WriteLine(String sLine){
        FileWriter flwrtrEcriture;

        BufferedWriter bufwrtrEcriture;

        try{
            flwrtrEcriture = new FileWriter(getFlTokens());

            bufwrtrEcriture = new BufferedWriter(flwrtrEcriture);

            bufwrtrEcriture.write(sLine);
            bufwrtrEcriture.newLine();

            bufwrtrEcriture.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void Replace(String sUsername){
        FileWriter flwrtrEcriture;

        FileReader flrdrLecture;

        BufferedWriter bufwrtrEcriture;

        BufferedReader bufrdrLecture;

        String sLine;
        String sNewLine;

        Boolean bFound;

        try {
            flrdrLecture = new FileReader(getFlTokens());
            bufrdrLecture = new BufferedReader(flrdrLecture);

            flwrtrEcriture = new FileWriter(getFlTokens());
            bufwrtrEcriture = new BufferedWriter(flwrtrEcriture);

            bFound=false;
            while(!bFound && (sLine=bufrdrLecture.readLine())!=null){
                if(sLine.split("#")[0].equals(sUsername)){
                    sNewLine = sLine.split("#")[0]+"#"+sLine.split("#")[1]+"#True";
                    bFound=true;
                }else{
                    sNewLine=sLine;
                }
                bufwrtrEcriture.write(sNewLine);
            }

            bufrdrLecture.close();
            bufwrtrEcriture.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    protected String Lookup(String sUsername){
        String sLine;
        String[] a_fTemp;

        FileReader flrdrLecteur;

        BufferedReader buffrdrLecture;

        try {
            flrdrLecteur = new FileReader(getFlTokens());
            buffrdrLecture = new BufferedReader(flrdrLecteur);

            while ((sLine = buffrdrLecture.readLine())!=null){
                a_fTemp = sLine.split("#");
                if(a_fTemp[0].equals(sUsername)){
                    return a_fTemp[1];
                }
            }
            return "";
        } catch (IOException e) {
            return "";
        }
    }
    public Boolean CheckTokenExistence() {
        System.out.println("Hello there");
        try{
            if(getFlTokens().exists()){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println("Error");
            return false;
        }
    }
    private void RemoveFile(){
        getFlTokens().delete();
    }
    public void BuildFile(){
        try {
            getFlTokens().createNewFile();
        }catch (IOException e){
        }
    }
    public String CheckIfAlive() {
        String sLine;
        String[] a_fTemp;

        FileReader flrdrLecteur;

        BufferedReader buffrdrLecture;

        try {
            flrdrLecteur = new FileReader(getFlTokens());
            buffrdrLecture = new BufferedReader(flrdrLecteur);

            while ((sLine = buffrdrLecture.readLine())!=null){
                a_fTemp = sLine.split("#");
                if(a_fTemp[2].equals("True")){
                    return a_fTemp[1];
                }
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
    public String getsPath() {
        return sPath;
    }

    public void setsPath(String sPath) {
        this.sPath = sPath;
    }
    public File getFlTokens() {
        return flTokens;
    }

    public void setFlTokens() {
        this.flTokens = new File(getDirGame()+File.separator+getsPath());
    }
    public void setFlTokens(File flTokens) {
        this.flTokens = flTokens;
    }

    public File getDirGame() {
        return dirGame;
    }

    public void setDirGame(File dirGame) {
        this.dirGame = dirGame;
    }
}
