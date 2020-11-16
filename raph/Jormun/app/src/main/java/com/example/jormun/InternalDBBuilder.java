package com.example.jormun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class InternalDBBuilder extends SQLiteOpenHelper {
    //TABLE infouser
    public static final String TABLE_infouser = "infouser";
    public static final String COL_infouser_IDUSER = "IDUSER";
    public static final String COL_infouser_USERNAME = "USERNAME";
    public static final String COL_infouser_TOKEN = "TOKEN";
    public static final String COL_infouser_ACTIVE = "ACTIVE";

    //TABLE worldmapmin
    public static final String TABLE_worldmapmin = "worldmapmin";
    public static final String COL_worldmapmin_IDPOINT = "IDPOIINT";
    public static final String COL_worldmapmin_POINTLAT = "POINTLAT";
    public static final String COL_worldmapmin_POINTLONG = "POINTLONG";
    public static final String COL_worldmapmin_TABLEREF = "TABLEREF";
    public static final String COL_worldmapmin_IDREF = "IDREF";

    //TABLE currentteam
    public static final String TABLE_currentteam = "currentteam";
    public static final String COL_currentteam_IDMEMBER ="IDMEMBER";
    public static final String COL_currentteam_TOKEN = "TOKEN";
    public static final String COL_currentteam_PATHJSON = "PATHJSON";

    //TABLE hero
    public static final String TABLE_hero = "hero";
    public static final String COL_hero_IDHERO = "IDHERO";
    public static final String COL_hero_PATHJSON = "PATHJSON";
    public static final String COL_hero_TOKEN = "TOKEN";

    //TABLE inventory/toRemove

    public static final String TABLE_inventory = "inventory";
    public static final String COL_inventory_IDINVENTORY = "IDINVENTORY";
    public static final String COL_inventory_PATHJSON = "PATHJSON";
    public static final String COL_inventory_IDHERO = "IDHERO";

    //TABLE items/ToRemove

    public static final String TABLE_item = "item";
    public static final String COL_item_IDITEM = "IDITEM";
    public static final String COL_item_PATHJSON = "PATHJSON";
    public static final String COL_item_IDINVENTORY = "IDINVENTORY";

    //TABLE monster

    public static final String TABLE_monster = "monster";
    public static final String COL_monster_IDMONSTER = "IDMONSTER";
    public static final String COL_monster_PATHJSON = "PATHJSON";
    public static final String COL_monster_TOKEN = "TOKEN";

    //TABLE village

    public static final String TABLE_village = "village";
    public static final String COL_village_IDVILLAGE = "IDVILLAGE";
    public static final String COL_village_PATHJSON = "PATHJSON";
    public static final String COL_village_TOKEN = "TOKEN";

    //TABLE building/ToRemove

    public static final String TABLE_building = "sprite/building";
    public static final String COL_building_IDBUILDING = "IDBUILDING";
    public static final String COL_building_PATHJSON = "PATHJSON";
    public static final String COL_building_IDVILLAGE = "IDVILLAGE";

    //TABLE QUEST/ToRemove

    public static final String TABLE_quest = "quest";
    public static final String COL_quest_IDQUEST = "IDQUEST";
    public static final String COL_quest_PATHJSON = "PATHJSON";
    public static final String COL_quest_TOKEN = "TOKEN";


    public InternalDBBuilder(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //On initialise la db interne
        System.out.print("Building db");
        InitializeTables(db);
    }
    public void InitializeTables(SQLiteDatabase db) {
        String[] a_sDatabase = {TABLE_infouser, TABLE_worldmapmin, TABLE_village, TABLE_monster, TABLE_hero, TABLE_currentteam};
        for (String sTable : a_sDatabase) {
            System.out.println(sTable+" Building");
            db.execSQL(CreateAssembler(sTable));

        }
    }
    public boolean DropTables(SQLiteDatabase db){
        String[] a_sDatabase = {TABLE_infouser, TABLE_worldmapmin, TABLE_village, TABLE_monster, TABLE_hero, TABLE_currentteam};

        Boolean bDropped;

        bDropped=true;
        for (String sTable : a_sDatabase) {
            try{
                db.execSQL(DropTable(sTable));
            }catch (SQLiteException e){
            }
        }
        return bDropped;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("'UpgradeDB'");
    }
    private String CreateAssembler(String sTable){
        String sAnswer;
        switch (sTable) {
            case TABLE_currentteam:
                sAnswer = "CREATE TABLE if not exists " + TABLE_currentteam + "(" +
                        COL_currentteam_IDMEMBER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_currentteam_PATHJSON + " VARCHAR(255) NOT NULL," +
                        COL_currentteam_TOKEN + " VARCHAR(255) references " + TABLE_infouser + "(" + COL_infouser_TOKEN + ") ON UPDATE CASCADE ON DELETE CASCADE);";
            case TABLE_hero:
                sAnswer = "CREATE TABLE if not exists " + TABLE_hero + "(" +
                        COL_hero_IDHERO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_hero_PATHJSON + " VARCHAR(255) NOT NULL," +
                        COL_hero_TOKEN + " VARCHAR(255) references " + TABLE_infouser + "(" + COL_infouser_TOKEN + ") ON UPDATE CASCADE ON DELETE CASCADE);";
                break;
            case TABLE_infouser:
                sAnswer = "CREATE TABLE if not exists " + TABLE_infouser + "(" +
                        COL_infouser_IDUSER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_infouser_TOKEN + " VARCHAR(255) NOT NULL,"+
                        COL_infouser_USERNAME + " VARCHAR(255) NOT NULL,"+
                        COL_infouser_ACTIVE + " BOOLEAN DEFAULT 0);";
                break;
            case TABLE_monster:
                sAnswer = "CREATE TABLE if not exists " + TABLE_monster + "(" +
                        COL_monster_IDMONSTER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_monster_PATHJSON + " VARCHAR(255) NOT NULL," +
                        COL_monster_TOKEN + " VARCHAR(255) references " + TABLE_infouser + "(" + COL_infouser_TOKEN + ") ON UPDATE CASCADE ON DELETE CASCADE);";
                break;
            case TABLE_village:
                sAnswer = "CREATE TABLE if not exists " + TABLE_village + "(" +
                        COL_village_IDVILLAGE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_village_PATHJSON + " VARCHAR(255) NOT NULL," +
                        COL_village_TOKEN + " VARCHAR(255) references " + TABLE_infouser + "(" + COL_infouser_TOKEN + ") ON UPDATE CASCADE ON DELETE CASCADE);";
                break;
            case TABLE_worldmapmin:
                sAnswer = "CREATE TABLE if not exists " + TABLE_worldmapmin + "(" +
                        COL_worldmapmin_IDPOINT + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_worldmapmin_POINTLAT + " REAL NOT NULL," +
                        COL_worldmapmin_POINTLONG + " REAL NOT NULL," +
                        COL_worldmapmin_TABLEREF + " VARCHAR(255) NOT NULL," +
                        COL_worldmapmin_IDREF + " INTEGER NOT NULL);";
                break;
            default:
                sAnswer="";
                break;
        }
        return sAnswer;

    }
    private String DropTable(String sTable){
        String sAnswer;

        sAnswer = "DROP TABLE "+sTable;

        return sAnswer;

    }
}
