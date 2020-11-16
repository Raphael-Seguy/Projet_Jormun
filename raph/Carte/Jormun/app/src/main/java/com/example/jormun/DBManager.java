package com.example.jormun;

import android.util.JsonReader;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

    public final String  sSEND = "SENDJSON";
    public final String  sREQUEST = "REQUESTJSON";

    private String sThrdAnswer;
    private String sUrl;

    private Boolean bConCheck;

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
    public String threadedRequest(final String sType,final String sQuery){
        Thread thrdRequest = new Thread(){
            @Override
            public void run() {
                setsThrdAnswer(AskDB(sType,sQuery));
            }
        };
        thrdRequest.start();
        while (thrdRequest.isAlive()){};
        return getsThrdAnswer();
    }
    public String AskDB(String sQuery){
        return AskDB_GETRequest(sQuery);
    }
    public String AskDB(String sType, String sQuery){
        String sTemp;

        switch (sType) {
            case "POST":
                if(sQuery.contains("#PARAMSEP#")){
                    return AskDB_POSTGETRequest(sQuery.split("#PARAMSEP#")[0],sQuery.split("#PARAMSEP#")[1]);
                }else{
                    return AskDB_POSTRequest(sQuery);
                }
            case "FILE":
                sTemp = sQuery.split("@")[0];
                sQuery = sQuery.split("@")[1];
                if(sTemp.equals(sREQUEST)){
                    return SendJSON();
                }else{
                    if(sQuery.contains("#")){
                        return  "";
                    }
                    return "";
                }
            default:
                return AskDB_GETRequest(sQuery);
        }
    }
    public String RequestMapPointsJSON(float fPositionLat,float fPositionLong){
        JSONObject jsonResponse;
        try{
            HttpsURLConnection.setDefaultHostnameVerifier(getHstnmverfHostNameManager());

            URL url = new URL(getsUrl() + "?sRequest=FetchSmallerMap");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            // read the response
            final Reader reader = new InputStreamReader(con.getInputStream());
            final BufferedReader br = new BufferedReader(reader);
            String sAnswer = "";
            String sLine;
            while ((sLine = br.readLine()) != null) {
                sAnswer+=sLine;
            }
            return "";
        }catch (Exception e){
            e.printStackTrace();
            return "ERROR";
        }
    }
    public JSONObject SendUsTestJson(){
        JSONObject jsnResponse;
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(getHstnmverfHostNameManager());

            URL url = new URL(getsUrl() + "?sRequest=testJson");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            // read the response
            final Reader reader = new InputStreamReader(con.getInputStream());
            final BufferedReader br = new BufferedReader(reader);
            String sAnswer = "";
            String sLine;
            while ((sLine = br.readLine()) != null) {
                sAnswer+=sLine;
            }
            return new JSONObject(sAnswer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String SendJSON(){
        JSONObject js = new JSONObject();
        try {
            js.put("Test","TEst");
            js.put("Billy",4);
            js.put("Lucky","Luck");

            String sJson = js.toString();
            System.out.println(sJson);
            URL url = new URL(getsUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(sJson.getBytes(StandardCharsets.UTF_8));
                os.close();
            }

            // read the response
            final Reader reader = new InputStreamReader(conn.getInputStream());
            final BufferedReader br = new BufferedReader(reader);
            String sAnswer = "";
            String sLine;
            while ((sLine = br.readLine()) != null) {
                sAnswer+=sLine;
            }
            br.close();
            conn.disconnect();

            return sAnswer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    private String ReceiveJSON(){
        return "";
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
    public String getsThrdAnswer() {
        return sThrdAnswer;
    }

    public void setsThrdAnswer(String sThrdAnswer) {
        this.sThrdAnswer = sThrdAnswer;
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
