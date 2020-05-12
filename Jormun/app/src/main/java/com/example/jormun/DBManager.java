package com.example.jormun;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.SyncFailedException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class DBManager {
    public final static String sREQUEST_POST = "POST";
    public final static String sREQUEST_GET = "GET";
    public final static String sREQUEST_FILE = "FILE";

    private Boolean bConCheck;
    private String sUrl;
    private URL urlWebSite;
    private MapsActivity mpactCurrent;
    private OkHttpClient okhttp3Client;
    private HostnameVerifier hstnmverfHostNameManager;

    public DBManager(String sUrlSite, MapsActivity mpactInput){
        ConCheck cnchckDBConnectionCheck;

        SetupHostCertificate();
        setsUrl(sUrlSite);
        setMpactCurrent(mpactInput);
        setOkhttp3Client();
        cnchckDBConnectionCheck = new ConCheck();
        cnchckDBConnectionCheck.start();
        while(cnchckDBConnectionCheck.isAlive()){
        }
        System.out.println(getbConCheck());
    }
    public void SetupHostCertificate(){
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };
        // Install the all-trusting trust manager
        try {
            final SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        setHstnmverfHostNameManager(allHostsValid);
    }
    public String AskDB(String sQuery){
        return AskDB_GETRequest(sQuery);
    }
    public String AskDB(String sType, String sQuery){
        switch (sType) {
            case "POST":
                if(sQuery.contains("#PARAMSEP#")){
                    return AskDB_POSTGETRequest(sQuery.split("#PARAMSEP#")[0],sQuery.split("#PARAMSEP#")[1]);
                }else{
                    return AskDB_POSTRequest(sQuery);
                }
            case "FILE":
                return "True";
            default:
                return AskDB_GETRequest(sQuery);
        }
    }
    private String AskDB_GETRequest(String sQuery){
        // Prepare the request.
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(getHstnmverfHostNameManager());

            URL url = new URL(getsUrl() + "?" + sQuery);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");


            final Reader reader = new InputStreamReader(con.getInputStream());
            final BufferedReader br = new BufferedReader(reader);
            String sAnswer = "";
            String sLine;
            while ((sLine = br.readLine()) != null) {
                sAnswer+=sLine;
            }
            br.close();
            return sAnswer;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    private String AskDB_POSTRequest(String sQuery){
        try {
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(getHstnmverfHostNameManager());

            URL url = new URL(getsUrl());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String sRequest = sQuery;
            // Send post request
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(sRequest);
                wr.flush();
            }

            final Reader reader = new InputStreamReader(con.getInputStream());
            final BufferedReader br = new BufferedReader(reader);
            String sLine;
            String sAnswer ="";
            while ((sLine = br.readLine()) != null) {
                sAnswer+=sLine;
            }
            br.close();
            return sAnswer;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    private String AskDB_POSTGETRequest(String sGETQuery,String sPOSTQuery){
        try {
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(getHstnmverfHostNameManager());
            URL url = new URL(getsUrl()+"?"+sGETQuery);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String sRequest = sPOSTQuery;
            // Send post request
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(sRequest);
                wr.flush();
            }

            final Reader reader = new InputStreamReader(con.getInputStream());
            final BufferedReader br = new BufferedReader(reader);
            String sLine;
            String sAnswer ="";
            while ((sLine = br.readLine()) != null) {
                sAnswer+=sLine;
            }
            System.out.println(sAnswer);
            br.close();
            return sAnswer;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public void test(){
        try {
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(getHstnmverfHostNameManager());

            URL url = new URL(getsUrl()+"?request=ConCheck");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String sRequest = "username=Tom&password=12345";
            // Send post request
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(sRequest);
                wr.flush();
            }

            final Reader reader = new InputStreamReader(con.getInputStream());
            final BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public OkHttpClient getOkhttp3Client() {
        return okhttp3Client;
    }
    public void setOkhttp3Client() {
        this.okhttp3Client = new OkHttpClient();
    }
    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }
    public MapsActivity getMpactCurrent() {
        return mpactCurrent;
    }

    public void setMpactCurrent(MapsActivity mpactCurrent) {
        this.mpactCurrent = mpactCurrent;
    }
    public Boolean getbConCheck() {
        return bConCheck;
    }

    public void setbConCheck(Boolean bConCheck) {
        this.bConCheck = bConCheck;
    }
    public URL getUrlWebSite() {
        return urlWebSite;
    }

    public void setUrlWebSite(URL urlWebSite) {
        this.urlWebSite = urlWebSite;
    }


    public HostnameVerifier getHstnmverfHostNameManager() {
        return hstnmverfHostNameManager;
    }

    public void setHstnmverfHostNameManager(HostnameVerifier hstnmverfHostNameManager) {
        this.hstnmverfHostNameManager = hstnmverfHostNameManager;
    }


    private class ConCheck extends Thread{
        public void run() {
            String sDebug;
            if((sDebug=AskDB("sRequest=ConCheck")).equals("True")) {
                setbConCheck(true);
            }else{
                setbConCheck(false);
            }
            System.out.println(sDebug);
        }
    }
}
