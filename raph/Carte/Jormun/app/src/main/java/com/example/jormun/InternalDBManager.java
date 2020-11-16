package com.example.jormun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;

import org.json.JSONException;
import org.json.JSONObject;


public class InternalDBManager {

    private static final int VERSION_BDD = 1;

    private static final String NOM_BDD = "game.db";

    private DirectoryManager dirmgrCurrent;

    private String sInternAnswer;

    private SQLiteDatabase bdd;

    private InternalDBBuilder GameDBSQLITE;

    public InternalDBManager(Context context,DirectoryManager dirmgrInput){
        //On crée la BDD et sa table
        GameDBSQLITE = new InternalDBBuilder(context, NOM_BDD, null, VERSION_BDD);
        dirmgrCurrent = dirmgrInput;
    }

    public String saveJSON(String sToken,String sType,JSONObject jsnToSave){
        getDirmgrCurrent().saveJson(sToken,sType,jsnToSave);
        return getDirmgrCurrent().getsPrevSavedFilePath();
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = GameDBSQLITE.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }
    /**
     * This is where you do opérations on db
     *
     */
    //Gestion du system de token#START
    public void UnbondEveryone(){
        Cursor crsSelect = getBDD().rawQuery("SELECT "+InternalDBBuilder.COL_infouser_TOKEN+" FROM "+InternalDBBuilder.TABLE_infouser,null);
        if(crsSelect.getCount()>0){
            crsSelect.moveToFirst();
            do {
                ChangePlayerState(crsSelect.getString(crsSelect.getColumnIndex(InternalDBBuilder.COL_infouser_TOKEN)),false);
                if(!crsSelect.isLast()){
                    crsSelect.moveToNext();
                }
            }while (!crsSelect.isLast());
        }
    }
    public String FetchCurrentToken(){
        Cursor crsToken = getBDD().rawQuery("SELECT "+InternalDBBuilder.COL_infouser_TOKEN+" FROM "+InternalDBBuilder.TABLE_infouser+" WHERE "+InternalDBBuilder.COL_infouser_ACTIVE+"=1",null);
        if(crsToken.getCount()==1){
            crsToken.moveToFirst();
            return  crsToken.getString(crsToken.getColumnIndex(InternalDBBuilder.COL_infouser_TOKEN));
        }else{
            return "";
        }
    }
    public void RemovePlayer(String sToken){
        getBDD().delete(InternalDBBuilder.TABLE_worldmapmin,InternalDBBuilder.COL_worldmapmin_OBJECTREF+" = 'PLAYER' AND "+InternalDBBuilder.COL_worldmapmin_TOKENREF+" = ?;",new String[]{sToken});
    }
    public void UpdatingUser(DBManager dbmgrServer,String sToken,float fLat, float fLong){
        ContentValues cvInsert = new ContentValues();
        ContentValues cvUpdate;

        cvInsert.put(InternalDBBuilder.COL_worldmapmin_OBJECTREF,"PLAYER");
        cvInsert.put(InternalDBBuilder.COL_worldmapmin_TOKENREF,sToken);
        cvInsert.put(InternalDBBuilder.COL_worldmapmin_POINTLAT,fLat);
        cvInsert.put(InternalDBBuilder.COL_worldmapmin_POINTLONG,fLong);
        Cursor crsCurrent = getBDD().rawQuery("SELECT * FROM "+InternalDBBuilder.TABLE_worldmapmin+" WHERE "+InternalDBBuilder.COL_worldmapmin_OBJECTREF+" = 'PLAYER' AND "+InternalDBBuilder.COL_worldmapmin_TOKENREF+" = ?;", new String[]{sToken});

        if(crsCurrent.getCount()==1){
            cvUpdate = new ContentValues();
            cvUpdate.put(InternalDBBuilder.COL_worldmapmin_POINTLAT,fLat);
            cvUpdate.put(InternalDBBuilder.COL_worldmapmin_POINTLONG,fLong);
            getBDD().update(InternalDBBuilder.TABLE_worldmapmin,cvUpdate,InternalDBBuilder.COL_worldmapmin_OBJECTREF+"='PLAYER' AND "+InternalDBBuilder.COL_worldmapmin_TOKENREF+"=?"
                    ,new String[]{sToken});
        }else if(crsCurrent.getCount()>1){
            getBDD().delete(InternalDBBuilder.TABLE_worldmapmin,InternalDBBuilder.COL_worldmapmin_OBJECTREF+" = 'PLAYER' AND "+InternalDBBuilder.COL_worldmapmin_TOKENREF+" = ?;",new String[]{sToken});
            getBDD().insert(InternalDBBuilder.TABLE_worldmapmin,null,cvInsert);
        }else{
            getBDD().insert(InternalDBBuilder.TABLE_worldmapmin,null,cvInsert);
        }
    }
    public float CalculateSphericalDistance(float latPoint1,float longPoint1,float latPoint2,float longPoint2){
        double R = 6378.137; // Radius of earth in KM
        double dLat = latPoint2 * Math.PI / 180 - latPoint1 * Math.PI / 180;
        double dLon = longPoint2 * Math.PI / 180 - longPoint1 * Math.PI / 180;
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(latPoint1 * Math.PI / 180) * Math.cos(latPoint2 * Math.PI / 180) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        return (float)(d * 1000);
    }
    public String GetUsername(double dLat,double dLong,DBManager dbServer){
        String sUsername;
        String sClosestToken;

        Boolean bFirst;

        float fLat;
        float fShortestDistance;
        float fTemp;
        float fLong;

        Cursor crsCurrent = getBDD().rawQuery("SELECT "+InternalDBBuilder.COL_worldmapmin_TOKENREF+","+InternalDBBuilder.COL_worldmapmin_POINTLAT+","+InternalDBBuilder.COL_worldmapmin_POINTLONG+" FROM "+InternalDBBuilder.TABLE_worldmapmin+" WHERE "+InternalDBBuilder.COL_worldmapmin_OBJECTREF+"='PLAYER';",null);
        bFirst=true;
        if(crsCurrent.getCount()>0){
            crsCurrent.moveToFirst();
            fShortestDistance=10000000;
            sClosestToken="";
            do{
                fLat = Float.parseFloat(crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_worldmapmin_POINTLAT)));
                fLong = Float.parseFloat(crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_worldmapmin_POINTLONG)));
                fTemp=CalculateSphericalDistance(fLat,fLong,(float)(dLat),(float)(dLong));
                if(bFirst){
                    bFirst=false;
                    fShortestDistance=fTemp;
                    sClosestToken=crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_worldmapmin_TOKENREF));
                }else if(fTemp<fShortestDistance){
                    fShortestDistance=fTemp;
                    sClosestToken=crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_worldmapmin_TOKENREF));
                }
                if(!crsCurrent.isLast()){
                    crsCurrent.moveToNext();
                }
            }while(!crsCurrent.isLast());
            if(!sClosestToken.equals("")) {
                sUsername = dbServer.threadedRequest(DBManager.sREQUEST_GET, "sRequest=GetUsername&sToken=" + sClosestToken);
            }else{
                sUsername="NONE_FOUND_CLOSE";
            }
        }else{
            sUsername = "UNDEFINED";
        }
        return sUsername;
    }
    public String CheckIfUserExist(String sUsername){
        String sToken;

        try {
            Cursor crsCurrent = getBDD().rawQuery("SELECT " + InternalDBBuilder.COL_infouser_TOKEN + " FROM " + InternalDBBuilder.TABLE_infouser + " WHERE " + InternalDBBuilder.COL_infouser_USERNAME + " = ?;", new String[]{sUsername});
            if (crsCurrent.getCount() == 0) {
                return "0#Not-Found";
            }else{
                crsCurrent.moveToFirst();
                sToken = crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_infouser_TOKEN));
                if (crsCurrent.getCount() > 1) {
                    return "-2#Too-Many-Registered";
                } else {
                    return "1#Registered#" + sToken;
                }
            }
        }catch (SQLiteException e){
            return "-1#Error";
        }
    }
    public int CheckToken(){
        try {
            String sQuery = "SELECT "+InternalDBBuilder.COL_infouser_TOKEN+" FROM "+InternalDBBuilder.TABLE_infouser;
            Cursor crsCurrent = getBDD().rawQuery(sQuery,null);
            return crsCurrent.getCount();
        }catch (SQLiteException e){
            e.printStackTrace();
            return -1;
        }
    }
    public String CheckAliveToken(DBManager dbCurrent){
        String sQuery;
        String sQuery2;

        try {
            sQuery="SELECT "+InternalDBBuilder.COL_infouser_TOKEN+" FROM "+InternalDBBuilder.TABLE_infouser+" WHERE "+InternalDBBuilder.COL_infouser_ACTIVE+"=1;";
            Cursor crsCurrent = getBDD().rawQuery(sQuery,new String[]{});
            if(crsCurrent.getCount()>0) {
                crsCurrent.moveToFirst();
                setsInternAnswer(crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_infouser_TOKEN)));
                sQuery2="sRequest=CheckDevice&sToken="+getsInternAnswer();
                if (dbCurrent.threadedRequest(DBManager.sREQUEST_GET,sQuery2).equals("REGISTERED")){
                    return "1#"+getsInternAnswer();
                }else {
                    sQuery2 = InternalDBBuilder.COL_currentteam_TOKEN+" = ?;";
                    getBDD().delete(InternalDBBuilder.TABLE_infouser,sQuery2,new String[]{getsInternAnswer()});
                    return "0#0";
                }
            }else{
                return "0#0";
            }
        }catch (SQLiteException e){
            e.printStackTrace();
            return "-1#0";
        }
    }
    public void PrintTable(String sTableName){
        Cursor crsRequest = getBDD().rawQuery("SELECT * FROM infouser;",null);
        StringBuilder sTable;
        if(crsRequest.getCount()>0){
            crsRequest.moveToFirst();
            sTable = new StringBuilder();
            for (String sTemp : crsRequest.getColumnNames()){
                sTable.append("| ").append(sTemp);
            }
            sTable.append(" |\n");
            do {
                for (String sTemp : crsRequest.getColumnNames()){
                    sTable.append("| ").append(crsRequest.getString(crsRequest.getColumnIndex(sTemp)));
                }
                sTable.append(" |\n");
                if(!crsRequest.isLast()){
                    crsRequest.moveToNext();
                }
            }while (!crsRequest.isLast());
            System.out.println(sTable);
        }else{
            System.out.println("TABLE EMPTY");
        }
    }
    public int EncodeTable(DBManager dbServer,String sToken,String sTypeobject,int iIdObject){
        String sAnswer = "";
        String sElementToken;
        String sTemp;
        JSONObject jsnTemp;

        try{
            if(sTypeobject.contains("structure")){
                sAnswer=dbServer.threadedRequest(DBManager.sREQUEST_GET,"sRequest=FetchNonUserStructure&sToken="+sToken+"&sID="+iIdObject);
                if(!sAnswer.equals("")){
                    int iTemp;
                    sTemp=sAnswer.split("@")[1];
                    jsnTemp = new JSONObject(sTemp);
                    sElementToken = sAnswer.split("@")[0];
                    iTemp = StructureSize();
                    sTemp = saveJSON(sElementToken,DirectoryManager.DIR_structure,jsnTemp);
                    SavePlayersStructure(sElementToken, sTemp);
                    return iTemp;
                }else{
                    return GetStructureID(sToken);
                }
            }else if(sTypeobject.contains("storagevillage")){
                sAnswer=dbServer.threadedRequest(DBManager.sREQUEST_GET,"sRequest=FetchNonUserVillage&sToken="+sToken+"&sID="+iIdObject);
                if(!sAnswer.equals("")){
                    sTemp=sAnswer.split("@")[1];
                    jsnTemp = new JSONObject(sTemp);
                    sElementToken = sAnswer.split("@")[0];
                    sTemp = saveJSON(sElementToken,DirectoryManager.DIR_village,jsnTemp);
                    SavePlayersVillage(sElementToken, sTemp);
                    return GetNewVillageID(sElementToken);
                }else{
                    return GetNewVillageID(sToken);
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return -1;
    }
    public int StructureSize(){
        Cursor crsCurrent = getBDD().rawQuery("SELECT * FROM "+InternalDBBuilder.TABLE_structure+";",null);
        return crsCurrent.getCount();
    }
    public int GetVillageID(String sToken){
        String sID;

        Cursor crsCurrent = getBDD().rawQuery("SELECT "+InternalDBBuilder.COL_village_IDVILLAGE+" FROM "+InternalDBBuilder.TABLE_village+" WHERE "+InternalDBBuilder.COL_village_TOKEN+"=?;",new String[]{sToken});

        sID=crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_village_IDVILLAGE));
        System.out.println("L'id du village est "+sID);
        return Integer.parseInt(sID);
    }
    public int GetStructureID(String sToken){
        String sID;

        Cursor crsCurrent = getBDD().rawQuery("SELECT "+InternalDBBuilder.COL_structure_IDSTRUCTURE+" FROM "+InternalDBBuilder.TABLE_structure+" WHERE "+InternalDBBuilder.COL_structure_TOKEN+"=?;",new String[]{sToken});
        crsCurrent.moveToFirst();

        sID=crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_structure_IDSTRUCTURE));
        System.out.println("L'id du structure est "+sID);
        return Integer.parseInt(sID);
    }
    public int GetNewVillageID(String sElementToken){
        String sTemp;
        Cursor crsCurrent = getBDD().rawQuery("SELECT "+InternalDBBuilder.COL_village_IDVILLAGE+" FROM "+InternalDBBuilder.TABLE_village+" WHERE "+InternalDBBuilder.COL_village_TOKEN+"=?;",new String[]{sElementToken});
        crsCurrent.moveToFirst();

        sTemp = crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_village_IDVILLAGE));
        System.out.println("L'id du village est "+sTemp);
        return Integer.parseInt(crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_village_IDVILLAGE)));
    }
    public int GetNewStructureID(String sElementToken){
        Cursor crsCurrent = getBDD().rawQuery("SELECT "+InternalDBBuilder.COL_structure_IDSTRUCTURE+" FROM "+InternalDBBuilder.TABLE_structure+" WHERE "+InternalDBBuilder.COL_structure_TOKEN+"=?;",new String[]{sElementToken});
        crsCurrent.moveToFirst();
        return Integer.parseInt(crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_structure_IDSTRUCTURE)));
    }
    public void RegisterObject(float fPositionLat,float fPositionLong,String sTypeobject,int iIdOldObject,int iNewIdRef,String sElementKey){
        String sLookup;
        ContentValues cvUpdates;
        ContentValues cvInsert;

        try {
            sLookup = "SELECT "+InternalDBBuilder.COL_worldmapmin_IDPOINT+" FROM "+InternalDBBuilder.TABLE_worldmapmin+" WHERE "+InternalDBBuilder.COL_worldmapmin_POINTLAT+"=? AND "+InternalDBBuilder.COL_worldmapmin_POINTLONG+"=? AND "+
                    InternalDBBuilder.COL_worldmapmin_OBJECTREF+"=? AND "+InternalDBBuilder.COL_worldmapmin_IDREF+"=? AND "+InternalDBBuilder.COL_worldmapmin_ELEMENTKEY+"=? AND "+InternalDBBuilder.COL_worldmapmin_IDOLDREF+"=?;";

            Cursor crsCurrent = getBDD().rawQuery(sLookup,new String[]{""+fPositionLat,""+fPositionLong,sTypeobject,""+iNewIdRef,""+iIdOldObject,sElementKey});
            if(crsCurrent.getCount()>0){
                cvUpdates = new ContentValues();
                cvUpdates.put(InternalDBBuilder.COL_worldmapmin_POINTLONG,fPositionLong);
                cvUpdates.put(InternalDBBuilder.COL_worldmapmin_POINTLAT,fPositionLat);
                cvUpdates.put(InternalDBBuilder.COL_worldmapmin_OBJECTREF,sTypeobject);
                cvUpdates.put(InternalDBBuilder.COL_worldmapmin_ELEMENTKEY,sElementKey);
                cvUpdates.put(InternalDBBuilder.COL_worldmapmin_IDOLDREF,iIdOldObject);
                getBDD().update(InternalDBBuilder.TABLE_worldmapmin,cvUpdates,InternalDBBuilder.COL_worldmapmin_IDPOINT+"=?",new String[]{""+iNewIdRef});
            }else{
                cvInsert = new ContentValues();
                cvInsert.put(InternalDBBuilder.COL_worldmapmin_POINTLONG,fPositionLong);
                cvInsert.put(InternalDBBuilder.COL_worldmapmin_POINTLAT,fPositionLat);
                cvInsert.put(InternalDBBuilder.COL_worldmapmin_OBJECTREF,sTypeobject);
                cvInsert.put(InternalDBBuilder.COL_worldmapmin_ELEMENTKEY,sElementKey);
                cvInsert.put(InternalDBBuilder.COL_worldmapmin_IDOLDREF,iIdOldObject);
                cvInsert.put(InternalDBBuilder.COL_worldmapmin_IDREF,iNewIdRef);
                getBDD().insert(InternalDBBuilder.TABLE_worldmapmin,null,cvInsert);
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    public void RegisterNewPlayer(String sToken,String sUsername){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(InternalDBBuilder.COL_infouser_TOKEN, sToken);
            contentValues.put(InternalDBBuilder.COL_infouser_USERNAME, sUsername);
            contentValues.put(InternalDBBuilder.COL_infouser_ACTIVE, true);
            long result = getBDD().insert(InternalDBBuilder.TABLE_infouser, null, contentValues);
            if (result == -1){
                System.out.println("Insert Failed");
            }else{
                ChangePlayerState(sToken, true);
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    public void ChangePlayerState(String sToken,boolean bState){
        try {
            ContentValues cvUpdate = new ContentValues();
            cvUpdate.put(InternalDBBuilder.COL_infouser_ACTIVE,bState);
            getBDD().update(InternalDBBuilder.TABLE_infouser,cvUpdate,InternalDBBuilder.COL_infouser_TOKEN+"=?",new String[]{sToken});
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    //Gestion du systeme de token#END
    public void SavePlayersStructure(String sToken,String sPath){
        try {
            ContentValues cvInsert = new ContentValues();
            cvInsert.put(InternalDBBuilder.COL_structure_PATHJSON,sPath);
            cvInsert.put(InternalDBBuilder.COL_structure_TOKEN,sToken);
            getBDD().insert(InternalDBBuilder.TABLE_structure,"",cvInsert);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    public void SavePlayersVillage(String sToken,String sPath){
        try {
            ContentValues cvInsert = new ContentValues();
            cvInsert.put(InternalDBBuilder.COL_village_PATHJSON,sPath);
            cvInsert.put(InternalDBBuilder.COL_village_TOKEN,sToken);
            getBDD().insert(InternalDBBuilder.TABLE_village,"",cvInsert);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    public void SavePlayersHero(String sToken,String sPath){
        try {
            ContentValues cvInsert = new ContentValues();
            cvInsert.put(InternalDBBuilder.COL_hero_PATHJSON,sPath);
            cvInsert.put(InternalDBBuilder.COL_hero_TOKEN,sToken);
            getBDD().insert(InternalDBBuilder.TABLE_hero,"",cvInsert);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    public void SavePlayersTeam(String sToken,String sPath){
        try {
            ContentValues cvInsert = new ContentValues();
            cvInsert.put(InternalDBBuilder.COL_currentteam_PATHJSON,sPath);
            cvInsert.put(InternalDBBuilder.COL_currentteam_TOKEN,sToken);
            getBDD().insert(InternalDBBuilder.TABLE_currentteam,"",cvInsert);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    public String getsInternAnswer() {
        return sInternAnswer;
    }

    public void setsInternAnswer(String sInternAnswer) {
        this.sInternAnswer = sInternAnswer;
    }

    public DirectoryManager getDirmgrCurrent() {
        return dirmgrCurrent;
    }

    public void setDirmgrCurrent(DirectoryManager dirmgrCurrent) {
        this.dirmgrCurrent = dirmgrCurrent;
    }

}