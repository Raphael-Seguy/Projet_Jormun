package com.example.testcb;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private Handler mainHandler = new Handler();

    private ProgressBar HpHero;
    private ProgressBar hpMob;
    private TextView Degat_Icon;
    private TextView Degat_valeur_actu;
    private TextView Degatmag_Icon;
    private TextView Degatmag_valeur_actu;
    private TextView Armure_Icon;
    private TextView Armure_valeur_actu;
    private TextView Armuremag_Icon;
    private TextView Armuremag_valeur_actu;
    private TextView Esquive_Icon;
    private TextView Esquive_valeur_actu;

    private int maxhpHero;
    private int maxhpMob;
    private int LvlHero;
    private int LvlMob;
    private int armureHero;
    private int armureMagHero;
    private int armureMob;
    private int armureMagMob;
    private int degatHero;
    private int degatMob;
    private int esquiveHero;
    private int esquiveMob;

    private int ihpHero;
    private int ihpMob;
    private boolean Mobdefend;
    private int MobTourdef;
    private boolean Mobdefesquive;

    private boolean fuitereussite;

    private boolean seDefend;
    private int Tourdef;
    private boolean defesquive;
    private int sort_duree_0;
    private int sort_duree_1;
    private int sort_duree_2;
    private int sort_duree_3;

    private int objet_duree_0;
    private int objet_duree_1;
    private int objet_duree_2;
    private int objet_duree_3;

    private int mobsort_duree_0;
    private int mobsort_duree_1;
    private int mobsort_duree_2;
    private int mobsort_duree_3;



    // initialisation des modificateurs pour le héro
        //armure
    private int Heroarmure_plus;
    private int Heroarmure_moins;
    private double Heroarmure_mult;
    private double Heroarmure_div;
        //armure magique
    private int Heroarmuremag_plus;
    private int Heroarmuremag_moins;
    private double Heroarmuremag_mult;
    private double Heroarmuremag_div;
        //esquive
    private int Heroesquive_plus;
    private int Heroesquive_moins;
    private double Heroesquive_mult;
    private double Heroesquive_div;
        //degat
    private int Herodegat_plus;
    private int Herodegat_moins;
    private double Herodegat_multp;
    private double Herodegat_divp;
        //degat magique
    private int Herodegatmag_plus;
    private int Herodegatmag_moins;
    private double Herodegatmag_multp;
    private double Herodegatmag_divp;

    //initialisation des modificateurs pour le mobs


    //élément d'IA du mobs
    private int MobprefMag;
    private int MobprefDef;
    private int MobprefAttack;

    private int MobprefPar;
    private int MobprefEsq ;

    //modificateur du mob//
        //armure
        private int Mobarmure_plus;
        private int Mobarmure_moins;
        private double Mobarmure_mult;
        private double Mobarmure_div;
        //armure magique
        private int Mobarmuremag_plus;
        private int Mobarmuremag_moins;
        private double Mobarmuremag_mult;
        private double Mobarmuremag_div;
        //esquive
        private int Mobesquive_plus;
        private int Mobesquive_moins;
        private double Mobesquive_mult;
        private double Mobesquive_div;
        //degat
        private int Mobdegat_plus;
        private int Mobdegat_moins;
        private double Mobdegat_multp;
        private double Mobdegat_divp;
        //degat magique
        private int Mobdegatmag_plus;
        private int Mobdegatmag_moins;
        private double Mobdegatmag_multp;
        private double Mobdegatmag_divp;

    ////////specificité des sorts du héros////////
    private Button Spel0;
    private String Spel0Name;
    private int Spel0dmg;
    private int Spel0Type;
    private int Spel0duree;
    private boolean Spel0Mag;
            //bonus possible//
        private int Spel0Bon_armure_plus=0;
        private double Spel0Bon_armure_mult=0;
        private int Spel0Bon_armuremag_plus=0;
        private double Spel0Bon_armuremag_mult=0;
        private int Spel0Bon_esquive_plus=0;
        private double Spel0Bon_esquive_mult=0;
        private int Spel0Bon_degat_plus=0;
        private double Spel0Bon_degat_multp=0;
        private int Spel0Bon_degatmag_plus=0;
        private double Spel0Bon_degatmag_multp=0;
                //malus possible//
            private int Spel0Mal_armure_moins=0;
            private double Spel0Mal_armure_div=0;
            private int Spel0Mal_armuremag_moins=0;
            private double Spel0Mal_armuremag_div=0;
            private int Spel0Mal_esquive_moins=0;
            private double Spel0Mal_esquive_div=0;
            private int Spel0Mal_degat_moins=0;
            private double Spel0Mal_degat_divp=0;
            private int Spel0Mal_degatmag_moins=0;
            private double Spel0Mal_degatmag_divp=0;

    private Button Spel1;
    private String Spel1Name;
    private int Spel1dmg;
    private int Spel1Type;
    private int Spel1duree;
    private boolean Spel1Mag;
        //bonus possible//
        private int Spel1Bon_armure_plus=0;
        private double Spel1Bon_armure_mult=0;
        private int Spel1Bon_armuremag_plus=0;
        private double Spel1Bon_armuremag_mult=0;
        private int Spel1Bon_esquive_plus=0;
        private double Spel1Bon_esquive_mult=0;
        private int Spel1Bon_degat_plus=0;
        private double Spel1Bon_degat_multp=0;
        private int Spel1Bon_degatmag_plus=0;
        private double Spel1Bon_degatmag_multp=0;
            //malus possible
            private int Spel1Mal_armure_moins=0;
            private double Spel1Mal_armure_div=0;
            private int Spel1Mal_armuremag_moins=0;
            private double Spel1Mal_armuremag_div=0;
            private int Spel1Mal_esquive_moins=0;
            private double Spel1Mal_esquive_div=0;
            private int Spel1Mal_degat_moins=0;
            private double Spel1Mal_degat_divp=0;
            private int Spel1Mal_degatmag_moins=0;
            private double Spel1Mal_degatmag_divp=0;


    private Button Spel2;
    private String Spel2Name;
    private int Spel2dmg;
    private int Spel2Type;
    private int Spel2duree;
    private boolean Spel2Mag;
        //bonus possible//
        private int Spel2Bon_armure_plus;
        private double Spel2Bon_armure_mult=0;
        private int Spel2Bon_armuremag_plus=0;
        private double Spel2Bon_armuremag_mult=0;
        private int Spel2Bon_esquive_plus=0;
        private double Spel2Bon_esquive_mult=0;
        private int Spel2Bon_degat_plus=0;
        private double Spel2Bon_degat_multp=0;
        private int Spel2Bon_degatmag_plus=0;
        private double Spel2Bon_degatmag_multp=0;
            //malus possible
            private int Spel2Mal_armure_moins=0;
            private double Spel2Mal_armure_div=0;
            private int Spel2Mal_armuremag_moins=0;
            private double Spel2Mal_armuremag_div=0;
            private int Spel2Mal_esquive_moins=0;
            private double Spel2Mal_esquive_div=0;
            private int Spel2Mal_degat_moins=0;
            private double Spel2Mal_degat_divp=0;
            private int Spel2Mal_degatmag_moins=0;
            private double Spel2Mal_degatmag_divp=0;

    private Button Spel3;
    private String Spel3Name;
    private int Spel3dmg;
    private int Spel3Type;
    private int Spel3duree;
    private boolean Spel3Mag;
        //bonus possible//
        private int Spel3Bon_armure_plus=0;
        private double Spel3Bon_armure_mult=0;
        private int Spel3Bon_armuremag_plus=0;
        private double Spel3Bon_armuremag_mult=0;
        private int Spel3Bon_esquive_plus=0;
        private double Spel3Bon_esquive_mult=0;
        private int Spel3Bon_degat_plus=0;
        private double Spel3Bon_degat_multp=0;
        private int Spel3Bon_degatmag_plus=0;
        private double Spel3Bon_degatmag_multp=0;
            //malus possible
            private int Spel3Mal_armure_moins=0;
            private double Spel3Mal_armure_div=0;
            private int Spel3Mal_armuremag_moins=0;
            private double Spel3Mal_armuremag_div=0;
            private int Spel3Mal_esquive_moins=0;
            private double Spel3Mal_esquive_div=0;
            private int Spel3Mal_degat_moins=0;
            private double Spel3Mal_degat_divp=0;
            private int Spel3Mal_degatmag_moins=0;
            private double Spel3Mal_degatmag_divp=0;

    ///Objets selectionnés///
        ///objet0///
    private Button Objet0;
    private String Objet0Name;
    private int Objet0dmg;
    private int Objet0Type;
    private int Objet0duree;
    private boolean Objet0Mag;
        //bonus possible//
        private int Objet0Bon_armure_plus=0;
        private double Objet0Bon_armure_mult=0;
        private int Objet0Bon_armuremag_plus=0;
        private double Objet0Bon_armuremag_mult=0;
        private int Objet0Bon_esquive_plus=0;
        private double Objet0Bon_esquive_mult=0;
        private int Objet0Bon_degat_plus=0;
        private double Objet0Bon_degat_multp=0;
        private int Objet0Bon_degatmag_plus=0;
        private double Objet0Bon_degatmag_multp=0;
            //malus possible//
            private int Objet0Mal_armure_moins=0;
            private double Objet0Mal_armure_div=0;
            private int Objet0Mal_armuremag_moins=0;
            private double Objet0Mal_armuremag_div=0;
            private int Objet0Mal_esquive_moins=0;
            private double Objet0Mal_esquive_div=0;
            private int Objet0Mal_degat_moins=0;
            private double Objet0Mal_degat_divp=0;
            private int Objet0Mal_degatmag_moins=0;
            private double Objet0Mal_degatmag_divp=0;
        ///Objet1///
    private Button Objet1;
    private String Objet1Name;
    private int Objet1dmg;
    private int Objet1Type;
    private int Objet1duree;
    private boolean Objet1Mag;
        //bonus possible
        private int Objet1Bon_armure_plus=0;
        private double Objet1Bon_armure_mult=0;
        private int Objet1Bon_armuremag_plus=0;
        private double Objet1Bon_armuremag_mult=0;
        private int Objet1Bon_esquive_plus=0;
        private double Objet1Bon_esquive_mult=0;
        private int Objet1Bon_degat_plus=0;
        private double Objet1Bon_degat_multp=0;
        private int Objet1Bon_degatmag_plus=0;
        private double Objet1Bon_degatmag_multp=0;
            //malus possible//
            private int Objet1Mal_armure_moins=0;
            private double Objet1Mal_armure_div=0;
            private int Objet1Mal_armuremag_moins=0;
            private double Objet1Mal_armuremag_div=0;
            private int Objet1Mal_esquive_moins=0;
            private double Objet1Mal_esquive_div=0;
            private int Objet1Mal_degat_moins=0;
            private double Objet1Mal_degat_divp=0;
            private int Objet1Mal_degatmag_moins=0;
            private double Objet1Mal_degatmag_divp=0;
        ///Objet2///
    private Button Objet2;
    private String Objet2Name;
    private int Objet2dmg;
    private int Objet2Type;
    private int Objet2duree;
    private boolean Objet2Mag;
            //bonus possible
        private int Objet2Bon_armure_plus=0;
        private double Objet2Bon_armure_mult=0;
        private int Objet2Bon_armuremag_plus=0;
        private double Objet2Bon_armuremag_mult=0;
        private int Objet2Bon_esquive_plus=0;
        private double Objet2Bon_esquive_mult=0;
        private int Objet2Bon_degat_plus=0;
        private double Objet2Bon_degat_multp=0;
        private int Objet2Bon_degatmag_plus=0;
        private double Objet2Bon_degatmag_multp=0;
            //malus possible//
            private int Objet2Mal_armure_moins=0;
            private double Objet2Mal_armure_div=0;
            private int Objet2Mal_armuremag_moins=0;
            private double Objet2Mal_armuremag_div=0;
            private int Objet2Mal_esquive_moins=0;
            private double Objet2Mal_esquive_div=0;
            private int Objet2Mal_degat_moins=0;
            private double Objet2Mal_degat_divp=0;
            private int Objet2Mal_degatmag_moins=0;
            private double Objet2Mal_degatmag_divp=0;
        ///Objet3///
    private Button Objet3;
    private String Objet3Name;
    private int Objet3dmg;
    private int Objet3Type;
    private int Objet3duree;
    private boolean Objet3Mag;
        //bonus possible
        private int Objet3Bon_armure_plus=0;
        private double Objet3Bon_armure_mult=0;
        private int Objet3Bon_armuremag_plus=0;
        private double Objet3Bon_armuremag_mult=0;
        private int Objet3Bon_esquive_plus=0;
        private double Objet3Bon_esquive_mult=0;
        private int Objet3Bon_degat_plus=0;
        private double Objet3Bon_degat_multp=0;
        private int Objet3Bon_degatmag_plus=0;
        private double Objet3Bon_degatmag_multp=0;
            //malus possible//
            private int Objet3Mal_armure_moins=0;
            private double Objet3Mal_armure_div=0;
            private int Objet3Mal_armuremag_moins=0;
            private double Objet3Mal_armuremag_div=0;
            private int Objet3Mal_esquive_moins=0;
            private double Objet3Mal_esquive_div=0;
            private int Objet3Mal_degat_moins=0;
            private double Objet3Mal_degat_divp=0;
            private int Objet3Mal_degatmag_moins=0;
            private double Objet3Mal_degatmag_divp=0;

    /////Sort du mob////
    //Sort0
    private String MobSpel0Name;
    private int MobSpel0dmg;
    private int MobSpel0Type;
    private int MobSpel0duree;
    private boolean MobSpel0Mag;
        //bonus possible//
        private int MobSpel0Bon_armure_plus=0;
        private double MobSpel0Bon_armure_mult=0;
        private int MobSpel0Bon_armuremag_plus=0;
        private double MobSpel0Bon_armuremag_mult=0;
        private int MobSpel0Bon_esquive_plus=0;
        private double MobSpel0Bon_esquive_mult=0;
        private int MobSpel0Bon_degat_plus=0;
        private double MobSpel0Bon_degat_multp=0;
        private int MobSpel0Bon_degatmag_plus=0;
        private double MobSpel0Bon_degatmag_multp=0;
            //malus possible//
            private int MobSpel0Mal_armure_moins=0;
            private double MobSpel0Mal_armure_div=0;
            private int MobSpel0Mal_armuremag_moins=0;
            private double MobSpel0Mal_armuremag_div=0;
            private int MobSpel0Mal_esquive_moins=0;
            private double MobSpel0Mal_esquive_div=0;
            private int MobSpel0Mal_degat_moins=0;
            private double MobSpel0Mal_degat_divp=0;
            private int MobSpel0Mal_degatmag_moins=0;
            private double MobSpel0Mal_degatmag_divp=0;
    //Sort1
    private String MobSpel1Name;
    private int MobSpel1dmg;
    private int MobSpel1Type;
    private int MobSpel1duree;
    private boolean MobSpel1Mag;
        //bonus possible//
        private int MobSpel1Bon_armure_plus=0;
        private double MobSpel1Bon_armure_mult=0;
        private int MobSpel1Bon_armuremag_plus=0;
        private double MobSpel1Bon_armuremag_mult=0;
        private int MobSpel1Bon_esquive_plus=0;
        private double MobSpel1Bon_esquive_mult=0;
        private int MobSpel1Bon_degat_plus=0;
        private double MobSpel1Bon_degat_multp=0;
        private int MobSpel1Bon_degatmag_plus=0;
        private double MobSpel1Bon_degatmag_multp=0;
            //malus possible//
            private int MobSpel1Mal_armure_moins=0;
            private double MobSpel1Mal_armure_div=0;
            private int MobSpel1Mal_armuremag_moins=0;
            private double MobSpel1Mal_armuremag_div=0;
            private int MobSpel1Mal_esquive_moins=0;
            private double MobSpel1Mal_esquive_div=0;
            private int MobSpel1Mal_degat_moins=0;
            private double MobSpel1Mal_degat_divp=0;
            private int MobSpel1Mal_degatmag_moins=0;
            private double MobSpel1Mal_degatmag_divp=0;
    //Sort2
    private String MobSpel2Name;
    private int MobSpel2dmg;
    private int MobSpel2Type;
    private int MobSpel2duree;
    private boolean MobSpel2Mag;
        //bonus possible//
        private int MobSpel2Bon_armure_plus=0;
        private double MobSpel2Bon_armure_mult=0;
        private int MobSpel2Bon_armuremag_plus=0;
        private double MobSpel2Bon_armuremag_mult=0;
        private int MobSpel2Bon_esquive_plus=0;
        private double MobSpel2Bon_esquive_mult=0;
        private int MobSpel2Bon_degat_plus=0;
        private double MobSpel2Bon_degat_multp=0;
        private int MobSpel2Bon_degatmag_plus=0;
        private double MobSpel2Bon_degatmag_multp=0;
            //malus possible//
            private int MobSpel2Mal_armure_moins=0;
            private double MobSpel2Mal_armure_div=0;
            private int MobSpel2Mal_armuremag_moins=0;
            private double MobSpel2Mal_armuremag_div=0;
            private int MobSpel2Mal_esquive_moins=0;
            private double MobSpel2Mal_esquive_div=0;
            private int MobSpel2Mal_degat_moins=0;
            private double MobSpel2Mal_degat_divp=0;
            private int MobSpel2Mal_degatmag_moins=0;
            private double MobSpel2Mal_degatmag_divp=0;
    //Sort2
    private String MobSpel3Name;
    private int MobSpel3dmg;
    private int MobSpel3Type;
    private int MobSpel3duree;
    private boolean MobSpel3Mag;
        //bonus possible//
        private int MobSpel3Bon_armure_plus=0;
        private double MobSpel3Bon_armure_mult=0;
        private int MobSpel3Bon_armuremag_plus=0;
        private double MobSpel3Bon_armuremag_mult=0;
        private int MobSpel3Bon_esquive_plus=0;
        private double MobSpel3Bon_esquive_mult=0;
        private int MobSpel3Bon_degat_plus=0;
        private double MobSpel3Bon_degat_multp=0;
        private int MobSpel3Bon_degatmag_plus=0;
        private double MobSpel3Bon_degatmag_multp=0;
            //malus possible//
            private int MobSpel3Mal_armure_moins=0;
            private double MobSpel3Mal_armure_div=0;
            private int MobSpel3Mal_armuremag_moins=0;
            private double MobSpel3Mal_armuremag_div=0;
            private int MobSpel3Mal_esquive_moins=0;
            private double MobSpel3Mal_esquive_div=0;
            private int MobSpel3Mal_degat_moins=0;
            private double MobSpel3Mal_degat_divp=0;
            private int MobSpel3Mal_degatmag_moins=0;
            private double MobSpel3Mal_degatmag_divp=0;

    //////////btn////////
    private Button attack;
    private Button sorts;
    private Button defendre;
    private Button fuite;
    private Button inventaire;
    private Button back;

    private Button esquive;
    private Button parer;

    private ImageView SpriteHero;
    private ImageView SpriteMob;

    private TextView FinText;
    private Button retour_carte;

    protected void onCreate(Bundle savedInstanceState)
    {
        //phase d'initialisation des données
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        // set des Pv
        this.HpHero = findViewById(R.id.PvHero);
        this.hpMob = findViewById(R.id.PvMob);

        this.Degat_Icon = findViewById(R.id.textdegats);
        this.Degat_valeur_actu =findViewById(R.id.textdegats_valeur);
        this.Degatmag_Icon = findViewById(R.id.textdegatmag);
        this.Degatmag_valeur_actu = findViewById(R.id.textdegatmag_valeur);
        this.Armure_Icon = findViewById(R.id.textarmure);
        this.Armure_valeur_actu = findViewById(R.id.textarmure_valeur);
        this.Armuremag_Icon = findViewById(R.id.textarmuremag);
        this.Armuremag_valeur_actu = findViewById(R.id.textarmuremag_valeur);
        this.Esquive_Icon = findViewById(R.id.textesquive);
        this.Esquive_valeur_actu = findViewById(R.id.textesquive_valeur);
        //infos récupérées de la db
        maxhpHero = 400;
        maxhpMob = 300;
        HpHero.setMax(maxhpHero);
        hpMob.setMax(maxhpMob);
        HpHero.setProgress(maxhpHero);
        hpMob.setProgress(maxhpMob);
        LvlHero = 10;
        LvlMob =30;
        armureHero = 10;
        armureMagHero =5;
        armureMob = 10;
        armureMagMob =5;
        degatHero = 20;
        degatMob =20;
        esquiveHero = 80;
        esquiveMob = 80;

        //infos mobs IA
        MobprefMag =45;
        MobprefDef=5;
        MobprefAttack=20;

        MobprefPar =50;
        MobprefEsq = 10;
        Mobdefesquive=false;
        Mobdefend=false;

        fuitereussite =false;
        //modificateur Mob
            //armure
            Mobarmure_plus = 0;
            Mobarmure_moins=0;
            Mobarmure_mult =1;
            Mobarmure_div =1;
            //armure mag
            Mobarmuremag_plus =0;
            Mobarmuremag_moins =0;
            Mobarmuremag_mult =1;
            Mobarmuremag_div =1;
            //degat
            Mobdegat_plus=0;
            Mobdegat_moins =0;
            Mobdegat_multp=1;
            Mobdegat_divp=1;
            //degat magique
            Mobdegatmag_plus=0;
            Mobdegatmag_moins=0;
            Mobdegatmag_multp=1;
            Mobdegatmag_divp=1;
            //esquive
            Mobesquive_plus =0;
            Mobesquive_moins =0;
            Mobesquive_mult=1;
            Mobesquive_div=1;

        //données nécessaires au programme
        seDefend = false;
        ihpHero = maxhpHero;
        ihpMob = maxhpMob;
        Tourdef=0;
        defesquive=false;
        sort_duree_0 = 0;
        sort_duree_1=0;
        sort_duree_2=0;
        sort_duree_3=0;

        objet_duree_0=0;
        objet_duree_1=0;
        objet_duree_2=0;
        objet_duree_3=0;
        //set des bonus
                //armure
            Heroarmure_plus = 0;
            Heroarmure_moins=0;
            Heroarmure_mult =1;
            Heroarmure_div =1;
                //armure mag
            Heroarmuremag_plus =0;
            Heroarmuremag_moins =0;
            Heroarmuremag_mult =1;
            Heroarmuremag_div =1;
                //degat
            Herodegat_plus=0;
            Herodegat_moins =0;
            Herodegat_multp=1;
            Herodegat_divp=1;
                //degat magique
            Herodegatmag_plus=0;
            Herodegatmag_moins=0;
            Herodegatmag_multp=1;
            Herodegatmag_divp=1;
                //esquive
            Heroesquive_plus =0;
            Heroesquive_moins =0;
            Heroesquive_mult=1;
            Heroesquive_div=1;


        //préparation des boutons
        this.attack = findViewById(R.id.btn_atk);
        this.defendre = findViewById(R.id.btn_def);
        this.sorts = findViewById(R.id.btn_sorts);
        this.fuite = findViewById(R.id.btn_fuite);
        this.inventaire = findViewById(R.id.btn_inv);    // faire le cas de l inventaire
        this.back = findViewById(R.id.btn_retour);
        this.back.setVisibility(View.GONE);

        this.FinText = findViewById(R.id.textEnd);
        this.FinText.setVisibility(View.GONE);
        this.retour_carte = findViewById(R.id.Text_return_map);
        this.retour_carte.setVisibility(View.GONE);


        //préparation des spels choisis             Les effets et nom sont tiré de la db
        this.Spel0 = findViewById(R.id.btn_spel0);
        this.Spel0.setVisibility(View.GONE);
        this.Spel0Name = "brise armure";
        this.Spel0dmg = 0;
        this.Spel0Type =7;
        this.Spel0Mal_armure_moins = 20;
        this.Spel0duree =1;
        this.Spel0Mag = true;
        this.Spel0.setText(""+Spel0Name);

        this.Spel1 = findViewById(R.id.btn_spel1);
        this.Spel1.setVisibility(View.GONE);
        this.Spel1Name = "griffe";
        this.Spel1dmg = 30;
        this.Spel1Type =2;
        this.Spel1duree =0;
        this.Spel1Mag=false;
        this.Spel1.setText(""+Spel1Name);

        this.Spel2 = findViewById(R.id.btn_spel2);
        this.Spel2.setVisibility(View.GONE);
        this.Spel2Name = "pied";
        this.Spel2dmg = 20;
        this.Spel2Type =2;
        this.Spel2duree =2;
        this.Spel2Mag=true;
        this.Spel2.setText(""+Spel2Name);

        this.Spel3 = findViewById(R.id.btn_spel3);
        this.Spel3.setVisibility(View.GONE);
        this.Spel3Name = "heal boost mag";
        this.Spel3dmg = 50;
        this.Spel3Type =15;
        this.Spel3duree =1;
        this.Spel3Bon_degatmag_multp=0.5;
        this.Spel3Mag=false;
        this.Spel3.setText(""+Spel3Name);

        ///préparation des objets choisis
        this.Objet0 = findViewById(R.id.btn_objet0);
        this.Objet0.setVisibility(View.GONE);
        this.Objet0Name = "potion rouge";
        this.Objet0dmg = 50;
        this.Objet0Type = 2;
        this.Objet0duree =2;
        this.Objet0Mag = true;
        this.Objet0.setText(""+Objet0Name);

        this.Objet1 = findViewById(R.id.btn_objet1);
        this.Objet1.setVisibility(View.GONE);
        this.Objet1Name = "potion verte";
        this.Objet1dmg = 50;
        this.Objet1Type = 5;
        this.Objet1duree =2;
        this.Objet1Mag = true;
        this.Objet1.setText(""+Objet1Name);

        this.Objet2 = findViewById(R.id.btn_objet2);
        this.Objet2.setVisibility(View.GONE);
        this.Objet2Name = "potion bleu";
        this.Objet2dmg = 50;
        this.Objet2Type = 3;
        this.Objet2Bon_armure_mult=0.5;
        this.Objet2duree =2;
        this.Objet2Mag = true;
        this.Objet2.setText(""+Objet2Name);

        this.Objet3 = findViewById(R.id.btn_objet3);
        this.Objet3.setVisibility(View.GONE);
        this.Objet3Name = "potion noire";
        this.Objet3dmg = 50;
        this.Objet3Type = 7;
        this.Objet3Mal_degat_divp=0.5;
        this.Objet3duree =2;
        this.Objet3Mag = true;
        this.Objet3.setText(""+Objet3Name);

        //préparation des spels du mob//
        this.MobSpel0Name = "feu1";
        this.MobSpel0dmg = 10;
        this.MobSpel0Type =2;
        this.MobSpel0duree =0;
        this.MobSpel0Mag = true;

        this.MobSpel1Name = "selfheal";
        this.MobSpel1dmg = 10;
        this.MobSpel1Type =5;
        this.MobSpel1duree =1;
        this.MobSpel1Mag = true;

        this.MobSpel2Name = "intimidation";
        this.MobSpel2dmg = 10;
        this.MobSpel2Type =7;
        this.MobSpel2duree =1;
        this.MobSpel2Mal_armure_moins =10;
        this.MobSpel2Mag = true;

        this.MobSpel3Name = "boost de force";
        this.MobSpel3dmg = 10;
        this.MobSpel3Type =3;
        this.MobSpel3duree =2;
        this.MobSpel3Bon_degat_plus =10;
        this.MobSpel3Mag = true;

        //récupération des spells choisis du héro
            //changer nom btn etc image etc
        //préparation des btns de défense
        this.esquive = findViewById(R.id.btn_def_esq);
        this.esquive.setVisibility(View.GONE);
        this.parer = findViewById(R.id.btn_def_par);
        this.parer.setVisibility(View.GONE);
        //récupération des stats de défense
            //récupérer mais cacher les stats de défense
        //récupération des Sprites du héro et du mob
        this.SpriteHero = findViewById(R.id.SpriteHr);
        this.SpriteMob = findViewById(R.id.SpriteMb);
            //set les sprites

        //fin de la phase de préparation début phase de combat
        StartThreadPlayer();


    }

    //Gestion du thread utilisateur
    public void StartThreadPlayer()
    {
        PlayerRunnable runnable = new PlayerRunnable(true);
        new Thread(runnable).start();
    }
    public void StopThreadPlayer(Runnable runnable)
    {
        new Thread(runnable).stop();
    }

    //Gestion du Thread Mob
    public void StartThreadMob()
    {
        MobRunnable runnable = new MobRunnable(true);
        new Thread(runnable).start();
    }
    public void StopThreadMob(MobRunnable runnable)
    {
        new Thread(runnable).stop();
    }

    //Verif des Etats des combattants et fin si mort aussi au prochain
    public void VerifVictoireHero(int Pv)
    {
        if(Pv==0)
        {
            EndScreen();
            VictoryScreen();
        }
        else if(fuitereussite==true)
        {
            EndScreen();
            fuiteScreen();
        }
        else
        {
            StartThreadMob();
        }
    }
    public void VerifVictporeMob(int Pv)
    {
        if(Pv==0)
        {
            EndScreen();
            DefetScreen();
        }
        else
        {
            StartThreadPlayer();
        }
    }


    //Tour du joueur
    class PlayerRunnable implements Runnable
    {
        public boolean PlayerTurn = true;
        private TextView message;
        PlayerRunnable(boolean state)
        {
            this.PlayerTurn = state;
        }

        @Override
        public void run() {
            this.message = findViewById(R.id.Text_cmb);

            resetBonus();
            restMobmalus();

            //on vérifie si des sorts sont toujours en action
            if(sort_duree_0!=0)
            {
                if(Spel0Type%2==0)
                {
                    if(Spel0Mag==true)
                    {
                        int damage = (int)((((((degatHero+Herodegatmag_plus-Herodegatmag_moins)+Spel0dmg)*Herodegatmag_multp)/Herodegatmag_divp)*1.5) - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Spel0Name + " inflige " + finalDamage +" dégats au mob");
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    else
                    {
                        int damage =(int)(((((degatHero+Herodegat_plus-Herodegat_moins)*Herodegat_multp)+Spel0dmg)/Herodegat_divp) - (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Spel0Name + " inflige " + finalDamage +" dégats au mob");
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    RefreshMobPv();
                }
                if(Spel0Type%5==0) //heal
                {
                    int recup = (int)((degatHero+Spel0dmg)*1.3);
                    ihpHero=ihpHero+recup;
                    if(ihpHero > maxhpHero)
                    {
                        recup = recup - (ihpHero-maxhpHero);
                        ihpHero = maxhpHero;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le joueur réucpère " + finalRecup + " pv grâce à " + Spel0Name);
                        }
                    });
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Spel0Type%3==0) //bonus
                {
                    if(Spel0Bon_armure_plus !=0)
                    {
                        Heroarmure_plus = Heroarmure_plus + Spel0Bon_armure_plus;
                    }
                    if(Spel0Bon_armure_mult !=0)
                    {
                        Heroarmure_mult = Heroesquive_mult + Spel0Bon_armure_mult;
                    }
                    if(Spel0Bon_armuremag_plus !=0)
                    {
                        Heroarmuremag_plus = Heroarmuremag_plus + Spel0Bon_armuremag_plus;
                    }
                    if(Spel0Bon_armuremag_mult !=0)
                    {
                        Heroarmuremag_mult = Heroarmuremag_mult + Spel0Bon_armuremag_mult;
                    }
                    if(Spel0Bon_degat_plus !=0)
                    {
                        Herodegat_plus = Herodegat_plus + Spel0Bon_degat_plus;
                    }
                    if(Spel0Bon_degat_multp !=0)
                    {
                        Herodegat_multp = Herodegat_multp + Spel0Bon_degat_multp;
                    }
                    if(Spel0Bon_degatmag_plus !=0)
                    {
                        Herodegatmag_plus = Herodegatmag_plus + Spel0Bon_degatmag_plus;
                    }
                    if(Spel0Bon_degatmag_multp !=0)
                    {
                        Herodegatmag_multp = Herodegatmag_multp + Spel0Bon_degatmag_multp;
                    }
                    if(Spel0Bon_esquive_plus !=0)
                    {
                        Heroesquive_plus = Heroesquive_plus + Spel0Bon_esquive_plus;
                    }
                    if(Spel0Bon_esquive_mult !=0)
                    {
                        Heroesquive_mult = Heroesquive_mult + Spel0Bon_esquive_mult;
                    }
                }
                if(Spel0Type%7==0) //malus
                {
                    if(Spel0Mal_armure_moins!=0)
                    {
                        Mobarmure_moins = Mobarmure_moins + Spel0Mal_armure_moins;
                    }
                    if(Spel0Mal_armure_div!=0)
                    {
                        Mobarmure_div = Mobarmure_div + Spel0Mal_armure_div;
                    }
                    if(Spel0Mal_armuremag_moins!=0)
                    {
                        Mobarmuremag_moins = Mobarmuremag_moins + Spel0Mal_armuremag_moins;
                    }
                    if(Spel0Mal_armuremag_div !=0)
                    {
                        Mobarmuremag_div = Mobarmuremag_div + Spel0Mal_armuremag_div;
                    }
                    if(Spel0Mal_esquive_moins!=0)
                    {
                        Mobesquive_moins = Mobesquive_moins + Spel0Mal_esquive_moins;
                    }
                    if(Spel0Mal_esquive_div!=0)
                    {
                        Mobesquive_div = Mobesquive_div + Spel0Mal_esquive_div;
                    }
                    if(Spel0Mal_degat_moins!=0)
                    {
                        Mobdegat_moins = Mobdegat_moins + Spel0Mal_degat_moins;
                    }
                    if(Spel0Mal_degat_divp!=0)
                    {
                        Mobdegat_divp = Mobdegat_divp + Spel0Mal_degat_divp;
                    }
                    if(Spel0Mal_degatmag_moins!=0)
                    {
                        Mobdegatmag_moins = Mobdegatmag_moins + Spel0Mal_degatmag_moins;
                    }
                    if(Spel0Mal_degatmag_divp!=0)
                    {
                        Mobdegatmag_divp = Mobdegatmag_divp + Spel0Mal_degatmag_divp;
                    }
                }

                sort_duree_0= sort_duree_0-1;
                if(sort_duree_0 ==0)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(Spel0Name + "  se dissipe");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            if(sort_duree_1!=0)
            {
                if(Spel1Type%2==0)
                {
                    if(Spel1Mag==true)
                    {
                        int damage = (int)((((((degatHero+Herodegatmag_plus-Herodegatmag_moins)+Spel1dmg)*Herodegatmag_multp)/Herodegatmag_divp)*1.5) - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Spel1Name + " inflige " + finalDamage +" dégats au mob");
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        int damage =(int)(((((degatHero+Herodegat_plus-Herodegat_moins)*Herodegat_multp)+Spel1dmg)/Herodegat_divp) -(((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Spel1Name + " inflige " + finalDamage +" dégats au mob");
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    RefreshMobPv();
                }
                if(Spel1Type%5==0) //heal
                {
                    int recup = (int)((degatHero+Spel1dmg)*1.3);
                    ihpHero=ihpHero+recup;
                    if(ihpHero > maxhpHero)
                    {
                        recup = recup - (ihpHero-maxhpHero);
                        ihpHero = maxhpHero;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le joueur réucpère " + finalRecup + " pv grâce à " + Spel1Name);
                        }
                    });
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Spel1Type%3==0) //bonus
                {
                    if(Spel1Bon_armure_plus !=0)
                    {
                        Heroarmure_plus = Heroarmure_plus + Spel1Bon_armure_plus;
                    }
                    if(Spel1Bon_armure_mult !=0)
                    {
                        Heroarmure_mult = Heroesquive_mult + Spel1Bon_armure_mult;
                    }
                    if(Spel1Bon_armuremag_plus !=0)
                    {
                        Heroarmuremag_plus = Heroarmuremag_plus + Spel1Bon_armuremag_plus;
                    }
                    if(Spel1Bon_armuremag_mult !=0)
                    {
                        Heroarmuremag_mult = Heroarmuremag_mult + Spel1Bon_armuremag_mult;
                    }
                    if(Spel1Bon_degat_plus !=0)
                    {
                        Herodegat_plus = Herodegat_plus + Spel1Bon_degat_plus;
                    }
                    if(Spel1Bon_degat_multp !=0)
                    {
                        Herodegat_multp = Herodegat_multp + Spel1Bon_degat_multp;
                    }
                    if(Spel1Bon_degatmag_plus !=0)
                    {
                        Herodegatmag_plus = Herodegatmag_plus + Spel1Bon_degatmag_plus;
                    }
                    if(Spel1Bon_degatmag_multp !=0)
                    {
                        Herodegatmag_multp = Herodegatmag_multp + Spel1Bon_degatmag_multp;
                    }
                    if(Spel1Bon_esquive_plus !=0)
                    {
                        Heroesquive_plus = Heroesquive_plus + Spel1Bon_esquive_plus;
                    }
                    if(Spel1Bon_esquive_mult !=0)
                    {
                        Heroesquive_mult = Heroesquive_mult + Spel1Bon_esquive_mult;
                    }
                }
                if(Spel1Type%7==0) //malus
                {
                    if(Spel1Mal_armure_moins!=0)
                    {
                        Mobarmure_moins = Mobarmure_moins + Spel1Mal_armure_moins;
                    }
                    if(Spel1Mal_armure_div!=0)
                    {
                        Mobarmure_div = Mobarmure_div + Spel1Mal_armure_div;
                    }
                    if(Spel1Mal_armuremag_moins!=0)
                    {
                        Mobarmuremag_moins = Mobarmuremag_moins + Spel1Mal_armuremag_moins;
                    }
                    if(Spel1Mal_armuremag_div !=0)
                    {
                        Mobarmuremag_div = Mobarmuremag_div + Spel1Mal_armuremag_div;
                    }
                    if(Spel1Mal_esquive_moins!=0)
                    {
                        Mobesquive_moins = Mobesquive_moins + Spel1Mal_esquive_moins;
                    }
                    if(Spel1Mal_esquive_div!=0)
                    {
                        Mobesquive_div = Mobesquive_div + Spel1Mal_esquive_div;
                    }
                    if(Spel1Mal_degat_moins!=0)
                    {
                        Mobdegat_moins = Mobdegat_moins + Spel1Mal_degat_moins;
                    }
                    if(Spel1Mal_degat_divp!=0)
                    {
                        Mobdegat_divp = Mobdegat_divp + Spel1Mal_degat_divp;
                    }
                    if(Spel1Mal_degatmag_moins!=0)
                    {
                        Mobdegatmag_moins = Mobdegatmag_moins + Spel1Mal_degatmag_moins;
                    }
                    if(Spel1Mal_degatmag_divp!=0)
                    {
                        Mobdegatmag_divp = Mobdegatmag_divp + Spel1Mal_degatmag_divp;
                    }
                }
                sort_duree_1= sort_duree_1-1;
                if(sort_duree_1==0)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(Spel1Name + " se dissipe");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(sort_duree_2!=0)
            {
                if(Spel2Type%2==0)
                {
                    if(Spel2Mag==true)
                    {
                        int damage = (int)((((((degatHero+Herodegatmag_plus-Herodegatmag_moins)+Spel2dmg)*Herodegatmag_multp)/Herodegatmag_divp)*1.5) - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Spel2Name + " inflige " + finalDamage +" dégats au mob");
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    else
                    {
                        int damage =(int)(((((degatHero+Herodegat_plus-Herodegat_moins)*Herodegat_multp)+Spel2dmg)/Herodegat_divp) - (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Spel2Name + " inflige " + finalDamage +" dégats au mob");
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    RefreshMobPv();
                }
                if(Spel2Type%5==0) //heal
                {
                    int recup = (int)((degatHero+Spel2dmg)*1.3);
                    ihpHero=ihpHero+recup;
                    if(ihpHero > maxhpHero)
                    {
                        recup = recup - (ihpHero-maxhpHero);
                        ihpHero = maxhpHero;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le joueur réucpère " + finalRecup + " pv grâce à " + Spel2Name);
                        }
                    });
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Spel2Type%3==0) //bonus
                {
                    if(Spel2Bon_armure_plus !=0)
                    {
                        Heroarmure_plus = Heroarmure_plus + Spel2Bon_armure_plus;
                    }
                    if(Spel2Bon_armure_mult !=0)
                    {
                        Heroarmure_mult = Heroesquive_mult + Spel2Bon_armure_mult;
                    }
                    if(Spel2Bon_armuremag_plus !=0)
                    {
                        Heroarmuremag_plus = Heroarmuremag_plus + Spel2Bon_armuremag_plus;
                    }
                    if(Spel2Bon_armuremag_mult !=0)
                    {
                        Heroarmuremag_mult = Heroarmuremag_mult + Spel2Bon_armuremag_mult;
                    }
                    if(Spel2Bon_degat_plus !=0)
                    {
                        Herodegat_plus = Herodegat_plus + Spel2Bon_degat_plus;
                    }
                    if(Spel2Bon_degat_multp !=0)
                    {
                        Herodegat_multp = Herodegat_multp + Spel2Bon_degat_multp;
                    }
                    if(Spel2Bon_degatmag_plus !=0)
                    {
                        Herodegatmag_plus = Herodegatmag_plus + Spel2Bon_degatmag_plus;
                    }
                    if(Spel2Bon_degatmag_multp !=0)
                    {
                        Herodegatmag_multp = Herodegatmag_multp + Spel2Bon_degatmag_multp;
                    }
                    if(Spel2Bon_esquive_plus !=0)
                    {
                        Heroesquive_plus = Heroesquive_plus + Spel2Bon_esquive_plus;
                    }
                    if(Spel2Bon_esquive_mult !=0)
                    {
                        Heroesquive_mult = Heroesquive_mult + Spel2Bon_esquive_mult;
                    }
                }
                if(Spel2Type%7==0) //malus
                {
                    if(Spel2Mal_armure_moins!=0)
                    {
                        Mobarmure_moins = Mobarmure_moins + Spel2Mal_armure_moins;
                    }
                    if(Spel2Mal_armure_div!=0)
                    {
                        Mobarmure_div = Mobarmure_div + Spel2Mal_armure_div;
                    }
                    if(Spel2Mal_armuremag_moins!=0)
                    {
                        Mobarmuremag_moins = Mobarmuremag_moins + Spel2Mal_armuremag_moins;
                    }
                    if(Spel2Mal_armuremag_div !=0)
                    {
                        Mobarmuremag_div = Mobarmuremag_div + Spel2Mal_armuremag_div;
                    }
                    if(Spel2Mal_esquive_moins!=0)
                    {
                        Mobesquive_moins = Mobesquive_moins + Spel2Mal_esquive_moins;
                    }
                    if(Spel2Mal_esquive_div!=0)
                    {
                        Mobesquive_div = Mobesquive_div + Spel2Mal_esquive_div;
                    }
                    if(Spel2Mal_degat_moins!=0)
                    {
                        Mobdegat_moins = Mobdegat_moins + Spel2Mal_degat_moins;
                    }
                    if(Spel2Mal_degat_divp!=0)
                    {
                        Mobdegat_divp = Mobdegat_divp + Spel2Mal_degat_divp;
                    }
                    if(Spel2Mal_degatmag_moins!=0)
                    {
                        Mobdegatmag_moins = Mobdegatmag_moins + Spel2Mal_degatmag_moins;
                    }
                    if(Spel2Mal_degatmag_divp!=0)
                    {
                        Mobdegatmag_divp = Mobdegatmag_divp + Spel2Mal_degatmag_divp;
                    }
                }
                sort_duree_2= sort_duree_2-1;
                if(sort_duree_2==0)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(Spel2Name + "  se dissipe");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(sort_duree_3!=0)
            {
                if(Spel3Type%2==0)
                {
                    if(Spel3Mag==true)
                    {
                        int damage = (int)((((((degatHero+Herodegatmag_plus-Herodegatmag_moins)+Spel2dmg)*Herodegatmag_multp)/Herodegatmag_divp)*1.5) - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Spel3Name + " inflige " + finalDamage +" dégats au mob");
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        int damage =(int)(((((degatHero+Herodegat_plus-Herodegat_moins)*Herodegat_multp)+Spel3dmg)/Herodegat_divp) -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Spel3Name + " inflige " + finalDamage +" dégats au mob");
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    RefreshMobPv();
                }
                if(Spel3Type%5==0) //heal
                {
                    int recup = (int)((degatHero+Spel3dmg)*1.3);
                    ihpHero=ihpHero+recup;
                    if(ihpHero > maxhpHero)
                    {
                        recup = recup - (ihpHero-maxhpHero);
                        ihpHero = maxhpHero;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le joueur réucpère " + finalRecup + " pv grâce à " + Spel3Name);
                        }
                    });
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Spel3Type%3==0) //bonus
                {
                    if(Spel3Bon_armure_plus !=0)
                    {
                        Heroarmure_plus = Heroarmure_plus + Spel3Bon_armure_plus;
                    }
                    if(Spel3Bon_armure_mult !=0)
                    {
                        Heroarmure_mult = Heroesquive_mult + Spel3Bon_armure_mult;
                    }
                    if(Spel3Bon_armuremag_plus !=0)
                    {
                        Heroarmuremag_plus = Heroarmuremag_plus + Spel3Bon_armuremag_plus;
                    }
                    if(Spel3Bon_armuremag_mult !=0)
                    {
                        Heroarmuremag_mult = Heroarmuremag_mult + Spel3Bon_armuremag_mult;
                    }
                    if(Spel3Bon_degat_plus !=0)
                    {
                        Herodegat_plus = Herodegat_plus + Spel3Bon_degat_plus;
                    }
                    if(Spel3Bon_degat_multp !=0)
                    {
                        Herodegat_multp = Herodegat_multp + Spel3Bon_degat_multp;
                    }
                    if(Spel3Bon_degatmag_plus !=0)
                    {
                        Herodegatmag_plus = Herodegatmag_plus + Spel3Bon_degatmag_plus;
                    }
                    if(Spel3Bon_degatmag_multp !=0)
                    {
                        Herodegatmag_multp = Herodegatmag_multp + Spel3Bon_degatmag_multp;
                    }
                    if(Spel3Bon_esquive_plus !=0)
                    {
                        Heroesquive_plus = Heroesquive_plus + Spel3Bon_esquive_plus;
                    }
                    if(Spel3Bon_esquive_mult !=0)
                    {
                        Heroesquive_mult = Heroesquive_mult + Spel3Bon_esquive_mult;
                    }
                }
                if(Spel3Type%7==0) //malus
                {
                    if(Spel3Mal_armure_moins!=0)
                    {
                        Mobarmure_moins = Mobarmure_moins + Spel3Mal_armure_moins;
                    }
                    if(Spel3Mal_armure_div!=0)
                    {
                        Mobarmure_div = Mobarmure_div + Spel3Mal_armure_div;
                    }
                    if(Spel3Mal_armuremag_moins!=0)
                    {
                        Mobarmuremag_moins = Mobarmuremag_moins + Spel3Mal_armuremag_moins;
                    }
                    if(Spel3Mal_armuremag_div !=0)
                    {
                        Mobarmuremag_div = Mobarmuremag_div + Spel3Mal_armuremag_div;
                    }
                    if(Spel3Mal_esquive_moins!=0)
                    {
                        Mobesquive_moins = Mobesquive_moins + Spel3Mal_esquive_moins;
                    }
                    if(Spel3Mal_esquive_div!=0)
                    {
                        Mobesquive_div = Mobesquive_div + Spel3Mal_esquive_div;
                    }
                    if(Spel3Mal_degat_moins!=0)
                    {
                        Mobdegat_moins = Mobdegat_moins + Spel3Mal_degat_moins;
                    }
                    if(Spel3Mal_degat_divp!=0)
                    {
                        Mobdegat_divp = Mobdegat_divp + Spel3Mal_degat_divp;
                    }
                    if(Spel3Mal_degatmag_moins!=0)
                    {
                        Mobdegatmag_moins = Mobdegatmag_moins + Spel3Mal_degatmag_moins;
                    }
                    if(Spel3Mal_degatmag_divp!=0)
                    {
                        Mobdegatmag_divp = Mobdegatmag_divp + Spel3Mal_degatmag_divp;
                    }
                }
                sort_duree_3= sort_duree_3-1;
                if(sort_duree_3==0)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(Spel3Name + "  se dissipe");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            //on vérifie si des objets sont toujours en action
            if(objet_duree_0!=0)
            {
                //Type de objet ?
                if(Objet0Type%2==0) //offensif
                {
                    if(Objet0Mag==true) //sort de nature magique ?
                    {
                        int damage = (int)(Objet0dmg - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }

                        final int finalDamage = damage;

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Objet0Name+ " inflige " + finalDamage +" dégats ");
                            }
                        });
                        ihpMob = ihpMob - damage;
                    }
                    else
                    {

                        int damage = (int) (Objet0dmg -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Objet0Name + " inflige " + finalDamage +" dégats ");
                            }
                        });

                    }
                    RefreshMobPv();


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Objet0Type%5==0) //heal
                {
                    int recup = Objet0dmg;
                    ihpHero=ihpHero+recup;
                    if(ihpHero > maxhpHero)
                    {
                        recup = recup - (ihpHero-maxhpHero);
                        ihpHero = maxhpHero;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le joueur réucpère " + finalRecup + " pv grâce à " + Objet0Name);
                        }
                    });
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Objet0Type%3==0) //bonus
                {
                    if(Objet0Bon_armure_plus !=0)
                    {
                        Heroarmure_plus = Heroarmure_plus + Objet0Bon_armure_plus;
                    }
                    if(Objet0Bon_armure_mult !=0)
                    {
                        Heroarmure_mult = Heroesquive_mult + Objet0Bon_armure_mult;
                    }
                    if(Objet0Bon_armuremag_plus !=0)
                    {
                        Heroarmuremag_plus = Heroarmuremag_plus + Objet0Bon_armuremag_plus;
                    }
                    if(Objet0Bon_armuremag_mult !=0)
                    {
                        Heroarmuremag_mult = Heroarmuremag_mult + Objet0Bon_armuremag_mult;
                    }
                    if(Objet0Bon_degat_plus !=0)
                    {
                        Herodegat_plus = Herodegat_plus + Objet0Bon_degat_plus;
                    }
                    if(Objet0Bon_degat_multp !=0)
                    {
                        Herodegat_multp = Herodegat_multp + Objet0Bon_degat_multp;
                    }
                    if(Objet0Bon_degatmag_plus !=0)
                    {
                        Herodegatmag_plus = Herodegatmag_plus + Objet0Bon_degatmag_plus;
                    }
                    if(Objet0Bon_degatmag_multp !=0)
                    {
                        Herodegatmag_multp = Herodegatmag_multp + Objet0Bon_degatmag_multp;
                    }
                    if(Objet0Bon_esquive_plus !=0)
                    {
                        Heroesquive_plus = Heroesquive_plus + Objet0Bon_esquive_plus;
                    }
                    if(Objet0Bon_esquive_mult !=0)
                    {
                        Heroesquive_mult = Heroesquive_mult + Objet0Bon_esquive_mult;
                    }

                }
                if(Objet0Type%7==0) //malus
                {
                    if(Objet0Mal_armure_moins!=0)
                    {
                        Mobarmure_moins = Mobarmure_moins + Objet0Mal_armure_moins;
                    }
                    if(Objet0Mal_armure_div!=0)
                    {
                        Mobarmure_div = Mobarmure_div + Objet0Mal_armure_div;
                    }
                    if(Objet0Mal_armuremag_moins!=0)
                    {
                        Mobarmuremag_moins = Mobarmuremag_moins + Objet0Mal_armuremag_moins;
                    }
                    if(Objet0Mal_armuremag_div !=0)
                    {
                        Mobarmuremag_div = Mobarmuremag_div + Objet0Mal_armuremag_div;
                    }
                    if(Objet0Mal_esquive_moins!=0)
                    {
                        Mobesquive_moins = Mobesquive_moins + Objet0Mal_esquive_moins;
                    }
                    if(Objet0Mal_esquive_div!=0)
                    {
                        Mobesquive_div = Mobesquive_div + Objet0Mal_esquive_div;
                    }
                    if(Objet0Mal_degat_moins!=0)
                    {
                        Mobdegat_moins = Mobdegat_moins + Objet0Mal_degat_moins;
                    }
                    if(Objet0Mal_degat_divp!=0)
                    {
                        Mobdegat_divp = Mobdegat_divp + Objet0Mal_degat_divp;
                    }
                    if(Objet0Mal_degatmag_moins!=0)
                    {
                        Mobdegatmag_moins = Mobdegatmag_moins + Objet0Mal_degatmag_moins;
                    }
                    if(Objet0Mal_degatmag_divp!=0)
                    {
                        Mobdegatmag_divp = Mobdegatmag_divp + Objet0Mal_degatmag_divp;
                    }
                }

                objet_duree_0= objet_duree_0-1;
                if(objet_duree_0 ==0)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(Objet0Name + " perd en efficacité");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
            if(objet_duree_1!=0)
            {
                //Type de objet ?
                if(Objet1Type%2==0) //offensif
                {
                    if(Objet1Mag==true) //sort de nature magique ?
                    {
                        int damage = (int)(Objet1dmg - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }

                        final int finalDamage = damage;

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Objet1Name+ " inflige " + finalDamage +" dégats ");
                            }
                        });
                        ihpMob = ihpMob - damage;
                    }
                    else
                    {

                        int damage = (int) (Objet1dmg -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Objet1Name + " inflige " + finalDamage +" dégats ");
                            }
                        });

                    }
                    RefreshMobPv();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Objet1Type%5==0) //heal
                {
                    int recup = Objet1dmg;
                    ihpHero=ihpHero+recup;
                    if(ihpHero > maxhpHero)
                    {
                        recup = recup - (ihpHero-maxhpHero);
                        ihpHero = maxhpHero;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le joueur réucpère " + finalRecup + " pv grâce à " + Objet1Name);
                        }
                    });
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Objet1Type%3==0) //bonus
                {
                    if(Objet1Bon_armure_plus !=0)
                    {
                        Heroarmure_plus = Heroarmure_plus + Objet1Bon_armure_plus;
                    }
                    if(Objet1Bon_armure_mult !=0)
                    {
                        Heroarmure_mult = Heroesquive_mult + Objet1Bon_armure_mult;
                    }
                    if(Objet1Bon_armuremag_plus !=0)
                    {
                        Heroarmuremag_plus = Heroarmuremag_plus + Objet1Bon_armuremag_plus;
                    }
                    if(Objet1Bon_armuremag_mult !=0)
                    {
                        Heroarmuremag_mult = Heroarmuremag_mult + Objet1Bon_armuremag_mult;
                    }
                    if(Objet1Bon_degat_plus !=0)
                    {
                        Herodegat_plus = Herodegat_plus + Objet1Bon_degat_plus;
                    }
                    if(Objet1Bon_degat_multp !=0)
                    {
                        Herodegat_multp = Herodegat_multp + Objet1Bon_degat_multp;
                    }
                    if(Objet1Bon_degatmag_plus !=0)
                    {
                        Herodegatmag_plus = Herodegatmag_plus + Objet1Bon_degatmag_plus;
                    }
                    if(Objet1Bon_degatmag_multp !=0)
                    {
                        Herodegatmag_multp = Herodegatmag_multp + Objet1Bon_degatmag_multp;
                    }
                    if(Objet1Bon_esquive_plus !=0)
                    {
                        Heroesquive_plus = Heroesquive_plus + Objet1Bon_esquive_plus;
                    }
                    if(Objet1Bon_esquive_mult !=0)
                    {
                        Heroesquive_mult = Heroesquive_mult + Objet1Bon_esquive_mult;
                    }

                }
                if(Objet1Type%7==0) //malus
                {
                    if(Objet1Mal_armure_moins!=0)
                    {
                        Mobarmure_moins = Mobarmure_moins + Objet1Mal_armure_moins;
                    }
                    if(Objet1Mal_armure_div!=0)
                    {
                        Mobarmure_div = Mobarmure_div + Objet1Mal_armure_div;
                    }
                    if(Objet1Mal_armuremag_moins!=0)
                    {
                        Mobarmuremag_moins = Mobarmuremag_moins + Objet1Mal_armuremag_moins;
                    }
                    if(Objet1Mal_armuremag_div !=0)
                    {
                        Mobarmuremag_div = Mobarmuremag_div + Objet1Mal_armuremag_div;
                    }
                    if(Objet1Mal_esquive_moins!=0)
                    {
                        Mobesquive_moins = Mobesquive_moins + Objet1Mal_esquive_moins;
                    }
                    if(Objet1Mal_esquive_div!=0)
                    {
                        Mobesquive_div = Mobesquive_div + Objet1Mal_esquive_div;
                    }
                    if(Objet1Mal_degat_moins!=0)
                    {
                        Mobdegat_moins = Mobdegat_moins + Objet1Mal_degat_moins;
                    }
                    if(Objet1Mal_degat_divp!=0)
                    {
                        Mobdegat_divp = Mobdegat_divp + Objet1Mal_degat_divp;
                    }
                    if(Objet1Mal_degatmag_moins!=0)
                    {
                        Mobdegatmag_moins = Mobdegatmag_moins + Objet1Mal_degatmag_moins;
                    }
                    if(Objet1Mal_degatmag_divp!=0)
                    {
                        Mobdegatmag_divp = Mobdegatmag_divp + Objet1Mal_degatmag_divp;
                    }
                }

                objet_duree_1= objet_duree_1-1;
                if(objet_duree_1 ==0)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(Objet1Name + " perd en efficacité");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
            if(objet_duree_2!=0)
            {
                //Type de objet ?
                if(Objet2Type%2==0) //offensif
                {
                    if(Objet2Mag==true) //sort de nature magique ?
                    {
                        int damage = (int)(Objet2dmg - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }

                        final int finalDamage = damage;

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Objet2Name+ " inflige " + finalDamage +" dégats ");
                            }
                        });
                        ihpMob = ihpMob - damage;
                    }
                    else
                    {

                        int damage = (int) (Objet2dmg -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Objet2Name + " inflige " + finalDamage +" dégats ");
                            }
                        });

                    }
                    RefreshMobPv();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Objet2Type%5==0) //heal
                {
                    int recup = Objet2dmg;
                    ihpHero=ihpHero+recup;
                    if(ihpHero > maxhpHero)
                    {
                        recup = recup - (ihpHero-maxhpHero);
                        ihpHero = maxhpHero;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le joueur réucpère " + finalRecup + " pv grâce à " + Objet2Name);
                        }
                    });
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Objet2Type%3==0) //bonus
                {
                    if(Objet2Bon_armure_plus !=0)
                    {
                        Heroarmure_plus = Heroarmure_plus + Objet2Bon_armure_plus;
                    }
                    if(Objet2Bon_armure_mult !=0)
                    {
                        Heroarmure_mult = Heroesquive_mult + Objet2Bon_armure_mult;
                    }
                    if(Objet2Bon_armuremag_plus !=0)
                    {
                        Heroarmuremag_plus = Heroarmuremag_plus + Objet2Bon_armuremag_plus;
                    }
                    if(Objet2Bon_armuremag_mult !=0)
                    {
                        Heroarmuremag_mult = Heroarmuremag_mult + Objet2Bon_armuremag_mult;
                    }
                    if(Objet2Bon_degat_plus !=0)
                    {
                        Herodegat_plus = Herodegat_plus + Objet2Bon_degat_plus;
                    }
                    if(Objet2Bon_degat_multp !=0)
                    {
                        Herodegat_multp = Herodegat_multp + Objet2Bon_degat_multp;
                    }
                    if(Objet2Bon_degatmag_plus !=0)
                    {
                        Herodegatmag_plus = Herodegatmag_plus + Objet2Bon_degatmag_plus;
                    }
                    if(Objet2Bon_degatmag_multp !=0)
                    {
                        Herodegatmag_multp = Herodegatmag_multp + Objet2Bon_degatmag_multp;
                    }
                    if(Objet2Bon_esquive_plus !=0)
                    {
                        Heroesquive_plus = Heroesquive_plus + Objet2Bon_esquive_plus;
                    }
                    if(Objet2Bon_esquive_mult !=0)
                    {
                        Heroesquive_mult = Heroesquive_mult + Objet2Bon_esquive_mult;
                    }

                }
                if(Objet2Type%7==0) //malus
                {
                    if(Objet2Mal_armure_moins!=0)
                    {
                        Mobarmure_moins = Mobarmure_moins + Objet2Mal_armure_moins;
                    }
                    if(Objet2Mal_armure_div!=0)
                    {
                        Mobarmure_div = Mobarmure_div + Objet2Mal_armure_div;
                    }
                    if(Objet2Mal_armuremag_moins!=0)
                    {
                        Mobarmuremag_moins = Mobarmuremag_moins + Objet2Mal_armuremag_moins;
                    }
                    if(Objet2Mal_armuremag_div !=0)
                    {
                        Mobarmuremag_div = Mobarmuremag_div + Objet2Mal_armuremag_div;
                    }
                    if(Objet2Mal_esquive_moins!=0)
                    {
                        Mobesquive_moins = Mobesquive_moins + Objet2Mal_esquive_moins;
                    }
                    if(Objet2Mal_esquive_div!=0)
                    {
                        Mobesquive_div = Mobesquive_div + Objet2Mal_esquive_div;
                    }
                    if(Objet2Mal_degat_moins!=0)
                    {
                        Mobdegat_moins = Mobdegat_moins + Objet2Mal_degat_moins;
                    }
                    if(Objet2Mal_degat_divp!=0)
                    {
                        Mobdegat_divp = Mobdegat_divp + Objet2Mal_degat_divp;
                    }
                    if(Objet2Mal_degatmag_moins!=0)
                    {
                        Mobdegatmag_moins = Mobdegatmag_moins + Objet2Mal_degatmag_moins;
                    }
                    if(Objet2Mal_degatmag_divp!=0)
                    {
                        Mobdegatmag_divp = Mobdegatmag_divp + Objet2Mal_degatmag_divp;
                    }
                }

                objet_duree_2= objet_duree_2-1;
                if(objet_duree_2 ==0)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(Objet2Name + " perd en efficacité");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
            if(objet_duree_3!=0)
            {
                //Type de objet ?
                if(Objet3Type%2==0) //offensif
                {
                    if(Objet3Mag==true) //sort de nature magique ?
                    {
                        int damage = (int)(Objet3dmg - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }

                        final int finalDamage = damage;

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Objet3Name+ " inflige " + finalDamage +" dégats ");
                            }
                        });
                        ihpMob = ihpMob - damage;
                    }
                    else
                    {

                        int damage = (int) (Objet3dmg -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpMob = ihpMob - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(Objet3Name + " inflige " + finalDamage +" dégats ");
                            }
                        });

                    }
                    RefreshMobPv();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Objet3Type%5==0) //heal
                {
                    int recup = Objet3dmg;
                    ihpHero=ihpHero+recup;
                    if(ihpHero > maxhpHero)
                    {
                        recup = recup - (ihpHero-maxhpHero);
                        ihpHero = maxhpHero;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le joueur réucpère " + finalRecup + " pv grâce à " + Objet3Name);
                        }
                    });
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(Objet3Type%3==0) //bonus
                {
                    if(Objet3Bon_armure_plus !=0)
                    {
                        Heroarmure_plus = Heroarmure_plus + Objet3Bon_armure_plus;
                    }
                    if(Objet3Bon_armure_mult !=0)
                    {
                        Heroarmure_mult = Heroesquive_mult + Objet3Bon_armure_mult;
                    }
                    if(Objet3Bon_armuremag_plus !=0)
                    {
                        Heroarmuremag_plus = Heroarmuremag_plus + Objet3Bon_armuremag_plus;
                    }
                    if(Objet3Bon_armuremag_mult !=0)
                    {
                        Heroarmuremag_mult = Heroarmuremag_mult + Objet3Bon_armuremag_mult;
                    }
                    if(Objet3Bon_degat_plus !=0)
                    {
                        Herodegat_plus = Herodegat_plus + Objet3Bon_degat_plus;
                    }
                    if(Objet3Bon_degat_multp !=0)
                    {
                        Herodegat_multp = Herodegat_multp + Objet3Bon_degat_multp;
                    }
                    if(Objet3Bon_degatmag_plus !=0)
                    {
                        Herodegatmag_plus = Herodegatmag_plus + Objet3Bon_degatmag_plus;
                    }
                    if(Objet3Bon_degatmag_multp !=0)
                    {
                        Herodegatmag_multp = Herodegatmag_multp + Objet3Bon_degatmag_multp;
                    }
                    if(Objet3Bon_esquive_plus !=0)
                    {
                        Heroesquive_plus = Heroesquive_plus + Objet3Bon_esquive_plus;
                    }
                    if(Objet3Bon_esquive_mult !=0)
                    {
                        Heroesquive_mult = Heroesquive_mult + Objet3Bon_esquive_mult;
                    }

                }
                if(Objet3Type%7==0) //malus
                {
                    if(Objet3Mal_armure_moins!=0)
                    {
                        Mobarmure_moins = Mobarmure_moins + Objet3Mal_armure_moins;
                    }
                    if(Objet3Mal_armure_div!=0)
                    {
                        Mobarmure_div = Mobarmure_div + Objet3Mal_armure_div;
                    }
                    if(Objet3Mal_armuremag_moins!=0)
                    {
                        Mobarmuremag_moins = Mobarmuremag_moins + Objet3Mal_armuremag_moins;
                    }
                    if(Objet3Mal_armuremag_div !=0)
                    {
                        Mobarmuremag_div = Mobarmuremag_div + Objet3Mal_armuremag_div;
                    }
                    if(Objet3Mal_esquive_moins!=0)
                    {
                        Mobesquive_moins = Mobesquive_moins + Objet3Mal_esquive_moins;
                    }
                    if(Objet3Mal_esquive_div!=0)
                    {
                        Mobesquive_div = Mobesquive_div + Objet3Mal_esquive_div;
                    }
                    if(Objet3Mal_degat_moins!=0)
                    {
                        Mobdegat_moins = Mobdegat_moins + Objet3Mal_degat_moins;
                    }
                    if(Objet3Mal_degat_divp!=0)
                    {
                        Mobdegat_divp = Mobdegat_divp + Objet3Mal_degat_divp;
                    }
                    if(Objet3Mal_degatmag_moins!=0)
                    {
                        Mobdegatmag_moins = Mobdegatmag_moins + Objet3Mal_degatmag_moins;
                    }
                    if(Objet3Mal_degatmag_divp!=0)
                    {
                        Mobdegatmag_divp = Mobdegatmag_divp + Objet3Mal_degatmag_divp;
                    }
                }

                objet_duree_3= objet_duree_3-1;
                if(objet_duree_3 ==0)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(Objet3Name + " perd en efficacité");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            if(Tourdef!=0)
            {
                Tourdef=Tourdef-1;
            }
            // on enlève esquive
            defesquive = false;
            if(ihpMob<0)
            {
                ihpMob=0;
                PlayerTurn =false;
            }
            else
            {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        message.setText("tour du joueur");
                    }
                });
            }
            refreshStats();
            while(PlayerTurn==true)
            {

                //Joueur attaque
                attack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(PlayerTurn==true)
                        {
                            if(Mobdefesquive==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Le joueur attaque mais le mob esquive");
                                        PlayerTurn=false;
                                    }
                                });


                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                int damage = (int)((((degatHero+Herodegat_plus-Herodegat_moins)*Herodegat_multp)/Herodegat_divp) - (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                                if(damage <0)
                                {
                                    damage =0;
                                }
                                ihpMob = ihpMob - damage;
                                final int finalDamage = damage;
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur attaque et inflige " + finalDamage +" dégats");
                                        PlayerTurn=false;
                                    }
                                });
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            RefreshMobPv();

                        }
                    }
                });
                //joueur se défend
                defendre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(PlayerTurn==true)
                        {
                            //affichage des boutons de la défense et on cache les autres
                            attack.setVisibility(View.GONE);
                            defendre.setVisibility(View.GONE);
                            sorts.setVisibility(View.GONE);
                            fuite.setVisibility(View.GONE);
                            inventaire.setVisibility(View.GONE);

                            back.setVisibility(View.VISIBLE);
                            esquive.setVisibility(View.VISIBLE);
                            parer.setVisibility(View.VISIBLE);
                        }

                    }
                });
                    //bouton de défense//////////////
                    esquive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(PlayerTurn==true)
                            {
                                Random rand = new Random();
                                int tentesquive = rand.nextInt(100);
                                if(tentesquive<(((esquiveHero+Heroesquive_plus-Heroesquive_moins)*Heroesquive_mult)/Heroesquive_div))
                                {
                                    defesquive=true;
                                }
                                int damage = degatHero/2 - armureMob;
                                if(damage <0)
                                {
                                    damage =0;
                                }
                                ihpMob = ihpMob - damage;
                                final int finalDamage = damage;
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur tente une esquive et inflige " + finalDamage +" dégats");
                                        PlayerTurn=false;
                                    }
                                });
                                RefreshMobPv();
                            }

                        }
                    });

                    parer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(PlayerTurn==true)
                            {
                                Tourdef=1;
                                if(seDefend ==false)
                                {
                                    Heroarmure_mult = Heroarmure_mult +1;
                                    Heroarmuremag_mult =Heroarmuremag_mult +1;
                                }
                                seDefend =true;
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur se defend");
                                        PlayerTurn=false;
                                    }
                                });
                            }

                        }
                    });

                //Joueur choisis un sort ////////////////////////////////////////////////:
                sorts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(PlayerTurn==true)
                        {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    message.setText("Joueur regarde ses sorts");
                                }
                            });
                            attack.setVisibility(View.GONE);
                            defendre.setVisibility(View.GONE);
                            sorts.setVisibility(View.GONE);
                            fuite.setVisibility(View.GONE);
                            inventaire.setVisibility(View.GONE);

                            back.setVisibility(View.VISIBLE);
                            Spel0.setVisibility(View.VISIBLE);
                            Spel1.setVisibility(View.VISIBLE);
                            Spel2.setVisibility(View.VISIBLE);
                            Spel3.setVisibility(View.VISIBLE);
                        }
                    }
                });
                ///Sorts de La liste choisie
                    Spel0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(PlayerTurn==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur lance " + Spel0Name+ " !");
                                    }
                                });
                                //Type de sort ?
                                if(Spel0Type%2==0) //offensif
                                {
                                    if(Spel0Mag==true) //sort de nature magique ?
                                    {
                                        int damage = (int)((((((degatHero+Herodegatmag_plus-Herodegatmag_moins)+Spel0dmg)*Herodegatmag_multp)/Herodegatmag_divp)*1.5) - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }

                                        final int finalDamage = damage;
                                        if(Mobdefesquive==true)
                                        {
                                            damage = damage/2;
                                            int damagereduce = damage;

                                            final int finaldamagereduce = damagereduce;
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText("Le mob tente une esquive mais " + Spel0Name+ " inflige " + finaldamagereduce +" dégats et vous inflige " + (finalDamage/10) + " dégats");
                                                }
                                            });

                                            ihpMob = ihpMob - damagereduce;
                                        }
                                        else
                                        {
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText(Spel0Name+ " inflige " + finalDamage +" dégats et vous inflige " + (finalDamage/10) + " dégats");
                                                }
                                            });
                                            ihpMob = ihpMob - damage;
                                        }
                                        ihpHero = ihpHero - (int)(damage/10);
                                    }
                                    else
                                    {
                                        if(Mobdefesquive ==true)
                                        {
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText("Hero lance " +Spel0Name + " mais Mob esquive");
                                                }
                                            });
                                        }
                                        else
                                        {
                                            int damage = (int) (((((degatHero+Herodegat_plus-Herodegat_moins)*Herodegat_multp)+Spel0dmg)/Herodegat_divp) -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                                            if(damage <0)
                                            {
                                                damage =0;
                                            }
                                            ihpMob = ihpMob - damage;
                                            final int finalDamage = damage;
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText(Spel0Name + " inflige " + finalDamage +" dégats ");
                                                }
                                            });
                                        }
                                    }
                                    RefreshMobPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Spel0Type%5==0) //heal
                                {
                                    int recup = (int)((degatHero+Spel0dmg)*1.3);
                                    ihpHero=ihpHero+recup;
                                    if(ihpHero > maxhpHero)
                                    {
                                        recup = recup - (ihpHero-maxhpHero);
                                        ihpHero = maxhpHero;
                                    }
                                    final int finalRecup = recup;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("le joueur réucpère " + finalRecup + " pv");
                                        }
                                    });
                                    RefreshHeroPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Spel0Type%3==0) //bonus
                                {
                                    String strmess = "le joueur gagne";
                                    if(Spel0Bon_armure_plus !=0)
                                    {
                                        Heroarmure_plus = Heroarmure_plus + Spel0Bon_armure_plus;
                                        strmess= strmess + " +"+ Spel0Bon_armure_plus+ " points d'armure!";
                                    }
                                    if(Spel0Bon_armure_mult !=0)
                                    {
                                        Heroarmure_mult = Heroesquive_mult + Spel0Bon_armure_mult;
                                        strmess= strmess + " +"+ (Spel0Bon_armure_mult*100) + "% d'armure!";
                                    }
                                    if(Spel0Bon_armuremag_plus !=0)
                                    {
                                        Heroarmuremag_plus = Heroarmuremag_plus + Spel0Bon_armuremag_plus;
                                        strmess= strmess + " +" + Spel0Bon_armuremag_plus+ " points d'armure magique!";
                                    }
                                    if(Spel0Bon_armuremag_mult !=0)
                                    {
                                        Heroarmuremag_mult = Heroarmuremag_mult + Spel0Bon_armuremag_mult;
                                        strmess= strmess + " +" + (Spel0Bon_armuremag_mult*100) + "% d'armure magique!";
                                    }
                                    if(Spel0Bon_degat_plus !=0)
                                    {
                                        Herodegat_plus = Herodegat_plus + Spel0Bon_degat_plus;
                                        strmess= strmess + " +" + Spel0Bon_degat_plus+ " points de dégats!";
                                    }
                                    if(Spel0Bon_degat_multp !=0)
                                    {
                                        Herodegat_multp = Herodegat_multp + Spel0Bon_degat_multp;
                                        strmess= strmess + " +" + (Spel0Bon_degat_multp*100) + "% de dégats!";
                                    }
                                    if(Spel0Bon_degatmag_plus !=0)
                                    {
                                        Herodegatmag_plus = Herodegatmag_plus + Spel0Bon_degatmag_plus;
                                        strmess= strmess + " +" + Spel0Bon_degatmag_plus+ " points de dégats magique!";
                                    }
                                    if(Spel0Bon_degatmag_multp !=0)
                                    {
                                        Herodegatmag_multp = Herodegatmag_multp + Spel0Bon_degatmag_multp;
                                        strmess= strmess + " +" + (Spel0Bon_degatmag_multp*100) + "% de dégats magique!";
                                    }
                                    if(Spel0Bon_esquive_plus !=0)
                                    {
                                        Heroesquive_plus = Heroesquive_plus + Spel0Bon_esquive_plus;
                                        strmess= strmess + " +" + Spel0Bon_esquive_plus+ "% de chance d'esquive suplémentaire!";
                                    }
                                    if(Spel0Bon_esquive_mult !=0)
                                    {
                                        Heroesquive_mult = Heroesquive_mult + Spel0Bon_esquive_mult;
                                        strmess= strmess + " +" + (Spel0Bon_esquive_mult*100) + "% ses chances d'esquiver!";
                                    }
                                    final String finalStrmess = strmess;
                                    message.setText(strmess);

                                }
                                if(Spel0Type%7==0) //malus
                                {
                                    if(Spel0Mal_armure_moins!=0)
                                    {
                                        Mobarmure_moins = Mobarmure_moins + Spel0Mal_armure_moins;
                                    }
                                    if(Spel0Mal_armure_div!=0)
                                    {
                                        Mobarmure_div = Mobarmure_div + Spel0Mal_armure_div;
                                    }
                                    if(Spel0Mal_armuremag_moins!=0)
                                    {
                                        Mobarmuremag_moins = Mobarmuremag_moins + Spel0Mal_armuremag_moins;
                                    }
                                    if(Spel0Mal_armuremag_div !=0)
                                    {
                                        Mobarmuremag_div = Mobarmuremag_div + Spel0Mal_armuremag_div;
                                    }
                                    if(Spel0Mal_esquive_moins!=0)
                                    {
                                        Mobesquive_moins = Mobesquive_moins + Spel0Mal_esquive_moins;
                                    }
                                    if(Spel0Mal_esquive_div!=0)
                                    {
                                        Mobesquive_div = Mobesquive_div + Spel0Mal_esquive_div;
                                    }
                                    if(Spel0Mal_degat_moins!=0)
                                    {
                                        Mobdegat_moins = Mobdegat_moins + Spel0Mal_degat_moins;
                                    }
                                    if(Spel0Mal_degat_divp!=0)
                                    {
                                        Mobdegat_divp = Mobdegat_divp + Spel0Mal_degat_divp;
                                    }
                                    if(Spel0Mal_degatmag_moins!=0)
                                    {
                                        Mobdegatmag_moins = Mobdegatmag_moins + Spel0Mal_degatmag_moins;
                                    }
                                    if(Spel0Mal_degatmag_divp!=0)
                                    {
                                        Mobdegatmag_divp = Mobdegatmag_divp + Spel0Mal_degatmag_divp;
                                    }
                                }
                                PlayerTurn=false;
                                sort_duree_0=Spel0duree;
                            }

                        }
                    });
                    Spel1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(PlayerTurn==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur lance " + Spel1Name+ " !");
                                    }
                                });
                                //Type de sort ?
                                if(Spel1Type%2==0) //offensif
                                {
                                    if(Spel1Mag==true) //sort de nature magique ?
                                    {
                                        int damage = (int)((((((degatHero+Herodegatmag_plus-Herodegatmag_moins)+Spel1dmg)*Herodegatmag_multp)/Herodegatmag_divp)*1.5) - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }

                                        final int finalDamage = damage;
                                        if(Mobdefesquive==true)
                                        {
                                            damage = damage/2;
                                            int damagereduce = damage;

                                            final int finaldamagereduce = damagereduce;
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText("Le mob tente une esquive mais " + Spel1Name+ " inflige " + finaldamagereduce +" dégats et vous inflige " + (finalDamage/10) + " dégats");
                                                }
                                            });

                                            ihpMob = ihpMob - damagereduce;
                                        }
                                        else
                                        {
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText(Spel1Name+ " inflige " + finalDamage +" dégats et vous inflige " + (finalDamage/10) + " dégats");
                                                }
                                            });
                                            ihpMob = ihpMob - damage;
                                        }
                                        ihpHero = ihpHero - (int)(damage/10);
                                    }
                                    else
                                    {
                                        if(Mobdefesquive ==true)
                                        {
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText("Hero lance " +Spel1Name + " mais Mob esquive");
                                                }
                                            });
                                        }
                                        else
                                        {
                                            int damage = (int) (((((degatHero+Herodegat_plus-Herodegat_moins)*Herodegat_multp)+Spel1dmg)/Herodegat_divp) -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                                            if(damage <0)
                                            {
                                                damage =0;
                                            }
                                            ihpMob = ihpMob - damage;
                                            final int finalDamage = damage;
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText(Spel1Name + " inflige " + finalDamage +" dégats ");
                                                }
                                            });
                                        }
                                    }
                                    RefreshMobPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Spel1Type%5==0) //heal
                                {
                                    int recup = (int)((degatHero+Spel1dmg)*1.3);
                                    ihpHero=ihpHero+recup;
                                    if(ihpHero > maxhpHero)
                                    {
                                        recup = recup - (ihpHero-maxhpHero);
                                        ihpHero = maxhpHero;
                                    }
                                    final int finalRecup = recup;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("le joueur réucpère " + finalRecup + " pv");
                                        }
                                    });
                                    RefreshHeroPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Spel1Type%3==0) //bonus
                                {
                                    String strmess = "le joueur gagne";
                                    if(Spel1Bon_armure_plus !=0)
                                    {
                                        Heroarmure_plus = Heroarmure_plus + Spel1Bon_armure_plus;
                                        strmess= strmess + " +"+ Spel1Bon_armure_plus+ " points d'armure!";
                                    }
                                    if(Spel1Bon_armure_mult !=0)
                                    {
                                        Heroarmure_mult = Heroesquive_mult + Spel1Bon_armure_mult;
                                        strmess= strmess + " +"+ (Spel1Bon_armure_mult*100) + "% d'armure!";
                                    }
                                    if(Spel1Bon_armuremag_plus !=0)
                                    {
                                        Heroarmuremag_plus = Heroarmuremag_plus + Spel1Bon_armuremag_plus;
                                        strmess= strmess + " +" + Spel1Bon_armuremag_plus+ " points d'armure magique!";
                                    }
                                    if(Spel1Bon_armuremag_mult !=0)
                                    {
                                        Heroarmuremag_mult = Heroarmuremag_mult + Spel1Bon_armuremag_mult;
                                        strmess= strmess + " +" + (Spel1Bon_armuremag_mult*100) + "% d'armure magique!";
                                    }
                                    if(Spel1Bon_degat_plus !=0)
                                    {
                                        Herodegat_plus = Herodegat_plus + Spel1Bon_degat_plus;
                                        strmess= strmess + " +" + Spel1Bon_degat_plus+ " points de dégats!";
                                    }
                                    if(Spel1Bon_degat_multp !=0)
                                    {
                                        Herodegat_multp = Herodegat_multp + Spel1Bon_degat_multp;
                                        strmess= strmess + " +" + (Spel1Bon_degat_multp*100) + "% de dégats!";
                                    }
                                    if(Spel1Bon_degatmag_plus !=0)
                                    {
                                        Herodegatmag_plus = Herodegatmag_plus + Spel1Bon_degatmag_plus;
                                        strmess= strmess + " +" + Spel1Bon_degatmag_plus+ " points de dégats magique!";
                                    }
                                    if(Spel1Bon_degatmag_multp !=0)
                                    {
                                        Herodegatmag_multp = Herodegatmag_multp + Spel1Bon_degatmag_multp;
                                        strmess= strmess + " +" + (Spel1Bon_degatmag_multp*100) + "% de dégats magique!";
                                    }
                                    if(Spel1Bon_esquive_plus !=0)
                                    {
                                        Heroesquive_plus = Heroesquive_plus + Spel1Bon_esquive_plus;
                                        strmess= strmess + " +" + Spel1Bon_esquive_plus+ "% de chance d'esquive suplémentaire!";
                                    }
                                    if(Spel1Bon_esquive_mult !=0)
                                    {
                                        Heroesquive_mult = Heroesquive_mult + Spel1Bon_esquive_mult;
                                        strmess= strmess + " +" + (Spel1Bon_esquive_mult*100) + "% ses chances d'esquiver!";
                                    }
                                    final String finalStrmess = strmess;
                                    message.setText(strmess);

                                }
                                if(Spel1Type%7==0) //malus
                                {
                                    if(Spel1Mal_armure_moins!=0)
                                    {
                                        Mobarmure_moins = Mobarmure_moins + Spel1Mal_armure_moins;
                                    }
                                    if(Spel1Mal_armure_div!=0)
                                    {
                                        Mobarmure_div = Mobarmure_div + Spel1Mal_armure_div;
                                    }
                                    if(Spel1Mal_armuremag_moins!=0)
                                    {
                                        Mobarmuremag_moins = Mobarmuremag_moins + Spel1Mal_armuremag_moins;
                                    }
                                    if(Spel1Mal_armuremag_div !=0)
                                    {
                                        Mobarmuremag_div = Mobarmuremag_div + Spel1Mal_armuremag_div;
                                    }
                                    if(Spel1Mal_esquive_moins!=0)
                                    {
                                        Mobesquive_moins = Mobesquive_moins + Spel1Mal_esquive_moins;
                                    }
                                    if(Spel1Mal_esquive_div!=0)
                                    {
                                        Mobesquive_div = Mobesquive_div + Spel1Mal_esquive_div;
                                    }
                                    if(Spel1Mal_degat_moins!=0)
                                    {
                                        Mobdegat_moins = Mobdegat_moins + Spel1Mal_degat_moins;
                                    }
                                    if(Spel1Mal_degat_divp!=0)
                                    {
                                        Mobdegat_divp = Mobdegat_divp + Spel1Mal_degat_divp;
                                    }
                                    if(Spel1Mal_degatmag_moins!=0)
                                    {
                                        Mobdegatmag_moins = Mobdegatmag_moins + Spel1Mal_degatmag_moins;
                                    }
                                    if(Spel1Mal_degatmag_divp!=0)
                                    {
                                        Mobdegatmag_divp = Mobdegatmag_divp + Spel1Mal_degatmag_divp;
                                    }
                                }
                                sort_duree_1=Spel1duree;
                                PlayerTurn=false;
                            }

                        }
                    });
                    Spel2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (PlayerTurn==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        message.setText("Joueur lance " + Spel2Name+ " !");
                                    }
                                });
                                //Type de sort ?
                                if(Spel2Type%2==0) //offensif
                                {
                                    if(Spel2Mag==true) //sort de nature magique ?
                                    {
                                        int damage = (int)((((((degatHero+Herodegatmag_plus-Herodegatmag_moins)+Spel2dmg)*Herodegatmag_multp)/Herodegatmag_divp)*1.5) - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }

                                        final int finalDamage = damage;
                                        if(Mobdefesquive==true)
                                        {
                                            damage = damage/2;
                                            int damagereduce = damage;

                                            final int finaldamagereduce = damagereduce;
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText("Le mob tente une esquive mais " + Spel2Name+ " inflige " + finaldamagereduce +" dégats et vous inflige " + (finalDamage/10) + " dégats");
                                                }
                                            });

                                            ihpMob = ihpMob - damagereduce;
                                        }
                                        else
                                        {
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText(Spel2Name+ " inflige " + finalDamage +" dégats et vous inflige " + (finalDamage/10) + " dégats");
                                                }
                                            });
                                            ihpMob = ihpMob - damage;
                                        }
                                        ihpHero = ihpHero - (int)(damage/10);
                                    }
                                    else
                                    {
                                        if(Mobdefesquive ==true)
                                        {
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText("Hero lance " +Spel2Name + " mais Mob esquive");
                                                }
                                            });
                                        }
                                        else
                                        {
                                            int damage = (int) (((((degatHero+Herodegat_plus-Herodegat_moins)*Herodegat_multp)+Spel2dmg)/Herodegat_divp) -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                                            if(damage <0)
                                            {
                                                damage =0;
                                            }
                                            ihpMob = ihpMob - damage;
                                            final int finalDamage = damage;
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText(Spel2Name + " inflige " + finalDamage +" dégats ");
                                                }
                                            });
                                        }
                                    }
                                    RefreshMobPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Spel2Type%5==0) //heal
                                {
                                    int recup = (int)((degatHero+Spel2dmg)*1.3);
                                    ihpHero=ihpHero+recup;
                                    if(ihpHero > maxhpHero)
                                    {
                                        recup = recup - (ihpHero-maxhpHero);
                                        ihpHero = maxhpHero;
                                    }
                                    final int finalRecup = recup;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("le joueur réucpère " + finalRecup + " pv");
                                        }
                                    });
                                    RefreshHeroPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Spel2Type%3==0) //bonus
                                {
                                    String strmess = "le joueur gagne";
                                    if(Spel2Bon_armure_plus !=0)
                                    {
                                        Heroarmure_plus = Heroarmure_plus + Spel2Bon_armure_plus;
                                        strmess= strmess + " +"+ Spel2Bon_armure_plus+ " points d'armure!";
                                    }
                                    if(Spel2Bon_armure_mult !=0)
                                    {
                                        Heroarmure_mult = Heroesquive_mult + Spel2Bon_armure_mult;
                                        strmess= strmess + " +"+ (Spel2Bon_armure_mult*100) + "% d'armure!";
                                    }
                                    if(Spel2Bon_armuremag_plus !=0)
                                    {
                                        Heroarmuremag_plus = Heroarmuremag_plus + Spel2Bon_armuremag_plus;
                                        strmess= strmess + " +" + Spel2Bon_armuremag_plus+ " points d'armure magique!";
                                    }
                                    if(Spel2Bon_armuremag_mult !=0)
                                    {
                                        Heroarmuremag_mult = Heroarmuremag_mult + Spel2Bon_armuremag_mult;
                                        strmess= strmess + " +" + (Spel2Bon_armuremag_mult*100) + "% d'armure magique!";
                                    }
                                    if(Spel2Bon_degat_plus !=0)
                                    {
                                        Herodegat_plus = Herodegat_plus + Spel2Bon_degat_plus;
                                        strmess= strmess + " +" + Spel2Bon_degat_plus+ " points de dégats!";
                                    }
                                    if(Spel2Bon_degat_multp !=0)
                                    {
                                        Herodegat_multp = Herodegat_multp + Spel2Bon_degat_multp;
                                        strmess= strmess + " +" + (Spel2Bon_degat_multp*100) + "% de dégats!";
                                    }
                                    if(Spel2Bon_degatmag_plus !=0)
                                    {
                                        Herodegatmag_plus = Herodegatmag_plus + Spel2Bon_degatmag_plus;
                                        strmess= strmess + " +" + Spel2Bon_degatmag_plus+ " points de dégats magique!";
                                    }
                                    if(Spel2Bon_degatmag_multp !=0)
                                    {
                                        Herodegatmag_multp = Herodegatmag_multp + Spel2Bon_degatmag_multp;
                                        strmess= strmess + " +" + (Spel2Bon_degatmag_multp*100) + "% de dégats magique!";
                                    }
                                    if(Spel2Bon_esquive_plus !=0)
                                    {
                                        Heroesquive_plus = Heroesquive_plus + Spel2Bon_esquive_plus;
                                        strmess= strmess + " +" + Spel2Bon_esquive_plus+ "% de chance d'esquive suplémentaire!";
                                    }
                                    if(Spel2Bon_esquive_mult !=0)
                                    {
                                        Heroesquive_mult = Heroesquive_mult + Spel2Bon_esquive_mult;
                                        strmess= strmess + " +" + (Spel2Bon_esquive_mult*100) + "% ses chances d'esquiver!";
                                    }
                                    final String finalStrmess = strmess;
                                    message.setText(strmess);

                                }
                                if(Spel2Type%7==0) //malus
                                {
                                    if(Spel2Mal_armure_moins!=0)
                                    {
                                        Mobarmure_moins = Mobarmure_moins + Spel2Mal_armure_moins;
                                    }
                                    if(Spel2Mal_armure_div!=0)
                                    {
                                        Mobarmure_div = Mobarmure_div + Spel2Mal_armure_div;
                                    }
                                    if(Spel2Mal_armuremag_moins!=0)
                                    {
                                        Mobarmuremag_moins = Mobarmuremag_moins + Spel2Mal_armuremag_moins;
                                    }
                                    if(Spel2Mal_armuremag_div !=0)
                                    {
                                        Mobarmuremag_div = Mobarmuremag_div + Spel2Mal_armuremag_div;
                                    }
                                    if(Spel2Mal_esquive_moins!=0)
                                    {
                                        Mobesquive_moins = Mobesquive_moins + Spel2Mal_esquive_moins;
                                    }
                                    if(Spel2Mal_esquive_div!=0)
                                    {
                                        Mobesquive_div = Mobesquive_div + Spel2Mal_esquive_div;
                                    }
                                    if(Spel2Mal_degat_moins!=0)
                                    {
                                        Mobdegat_moins = Mobdegat_moins + Spel2Mal_degat_moins;
                                    }
                                    if(Spel2Mal_degat_divp!=0)
                                    {
                                        Mobdegat_divp = Mobdegat_divp + Spel2Mal_degat_divp;
                                    }
                                    if(Spel2Mal_degatmag_moins!=0)
                                    {
                                        Mobdegatmag_moins = Mobdegatmag_moins + Spel2Mal_degatmag_moins;
                                    }
                                    if(Spel2Mal_degatmag_divp!=0)
                                    {
                                        Mobdegatmag_divp = Mobdegatmag_divp + Spel2Mal_degatmag_divp;
                                    }
                                }
                                sort_duree_2=Spel2duree;
                                PlayerTurn=false;
                            }
                        }
                    });
                    Spel3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (PlayerTurn==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur lance " + Spel3Name+ " !");
                                    }
                                });
                                //Type de sort ?
                                if(Spel3Type%2==0) //offensif
                                {
                                    if(Spel3Mag==true) //sort de nature magique ?
                                    {
                                        int damage = (int)((((((degatHero+Herodegatmag_plus-Herodegatmag_moins)+Spel3dmg)*Herodegatmag_multp)/Herodegatmag_divp)*1.5) - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }

                                        final int finalDamage = damage;
                                        if(Mobdefesquive==true)
                                        {
                                            damage = damage/2;
                                            int damagereduce = damage;

                                            final int finaldamagereduce = damagereduce;
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText("Le mob tente une esquive mais " + Spel3Name+ " inflige " + finaldamagereduce +" dégats et vous inflige " + (finalDamage/10) + " dégats");
                                                }
                                            });

                                            ihpMob = ihpMob - damagereduce;
                                        }
                                        else
                                        {
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText(Spel3Name+ " inflige " + finalDamage +" dégats et vous inflige " + (finalDamage/10) + " dégats");
                                                }
                                            });
                                            ihpMob = ihpMob - damage;
                                        }
                                        ihpHero = ihpHero - (int)(damage/10);
                                    }
                                    else
                                    {
                                        if(Mobdefesquive ==true)
                                        {
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText("Hero lance " +Spel3Name + " mais Mob esquive");
                                                }
                                            });
                                        }
                                        else
                                        {
                                            int damage = (int) (((((degatHero+Herodegat_plus-Herodegat_moins)*Herodegat_multp)+Spel3dmg)/Herodegat_divp) - (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                                            if(damage <0)
                                            {
                                                damage =0;
                                            }
                                            ihpMob = ihpMob - damage;
                                            final int finalDamage = damage;
                                            mainHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    message.setText(Spel3Name + " inflige " + finalDamage +" dégats ");
                                                }
                                            });
                                        }
                                    }
                                    RefreshMobPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Spel3Type%5==0) //heal
                                {
                                    int recup = (int)((degatHero+Spel3dmg)*1.3);
                                    ihpHero=ihpHero+recup;
                                    if(ihpHero > maxhpHero)
                                    {
                                        recup = recup - (ihpHero-maxhpHero);
                                        ihpHero = maxhpHero;
                                    }
                                    final int finalRecup = recup;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("le joueur réucpère " + finalRecup + " pv");
                                        }
                                    });
                                    RefreshHeroPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Spel3Type%3==0) //bonus
                                {
                                    String strmess = "le joueur gagne";
                                    if(Spel3Bon_armure_plus !=0)
                                    {
                                        Heroarmure_plus = Heroarmure_plus + Spel3Bon_armure_plus;
                                        strmess= strmess + " +"+ Spel3Bon_armure_plus+ " points d'armure!";
                                    }
                                    if(Spel3Bon_armure_mult !=0)
                                    {
                                        Heroarmure_mult = Heroesquive_mult + Spel3Bon_armure_mult;
                                        strmess= strmess + " +"+ (Spel3Bon_armure_mult*100) + "% d'armure!";
                                    }
                                    if(Spel3Bon_armuremag_plus !=0)
                                    {
                                        Heroarmuremag_plus = Heroarmuremag_plus + Spel3Bon_armuremag_plus;
                                        strmess= strmess + " +" + Spel3Bon_armuremag_plus+ " points d'armure magique!";
                                    }
                                    if(Spel3Bon_armuremag_mult !=0)
                                    {
                                        Heroarmuremag_mult = Heroarmuremag_mult + Spel3Bon_armuremag_mult;
                                        strmess= strmess + " +" + (Spel3Bon_armuremag_mult*100) + "% d'armure magique!";
                                    }
                                    if(Spel3Bon_degat_plus !=0)
                                    {
                                        Herodegat_plus = Herodegat_plus + Spel3Bon_degat_plus;
                                        strmess= strmess + " +" + Spel3Bon_degat_plus+ " points de dégats!";
                                    }
                                    if(Spel3Bon_degat_multp !=0)
                                    {
                                        Herodegat_multp = Herodegat_multp + Spel3Bon_degat_multp;
                                        strmess= strmess + " +" + (Spel3Bon_degat_multp*100) + "% de dégats!";
                                    }
                                    if(Spel3Bon_degatmag_plus !=0)
                                    {
                                        Herodegatmag_plus = Herodegatmag_plus + Spel3Bon_degatmag_plus;
                                        strmess= strmess + " +" + Spel3Bon_degatmag_plus+ " points de dégats magique!";
                                    }
                                    if(Spel3Bon_degatmag_multp !=0)
                                    {
                                        Herodegatmag_multp = Herodegatmag_multp + Spel3Bon_degatmag_multp;
                                        strmess= strmess + " +" + (Spel3Bon_degatmag_multp*100) + "% de dégats magique!";
                                    }
                                    if(Spel3Bon_esquive_plus !=0)
                                    {
                                        Heroesquive_plus = Heroesquive_plus + Spel3Bon_esquive_plus;
                                        strmess= strmess + " +" + Spel3Bon_esquive_plus+ "% de chance d'esquive suplémentaire!";
                                    }
                                    if(Spel3Bon_esquive_mult !=0)
                                    {
                                        Heroesquive_mult = Heroesquive_mult + Spel3Bon_esquive_mult;
                                        strmess= strmess + " +" + (Spel3Bon_esquive_mult*100) + "% ses chances d'esquiver!";
                                    }
                                    final String finalStrmess = strmess;
                                    message.setText(strmess);

                                }
                                if(Spel3Type%7==0) //malus
                                {
                                    if(Spel3Mal_armure_moins!=0)
                                    {
                                        Mobarmure_moins = Mobarmure_moins + Spel3Mal_armure_moins;
                                    }
                                    if(Spel3Mal_armure_div!=0)
                                    {
                                        Mobarmure_div = Mobarmure_div + Spel3Mal_armure_div;
                                    }
                                    if(Spel3Mal_armuremag_moins!=0)
                                    {
                                        Mobarmuremag_moins = Mobarmuremag_moins + Spel3Mal_armuremag_moins;
                                    }
                                    if(Spel3Mal_armuremag_div !=0)
                                    {
                                        Mobarmuremag_div = Mobarmuremag_div + Spel3Mal_armuremag_div;
                                    }
                                    if(Spel3Mal_esquive_moins!=0)
                                    {
                                        Mobesquive_moins = Mobesquive_moins + Spel3Mal_esquive_moins;
                                    }
                                    if(Spel3Mal_esquive_div!=0)
                                    {
                                        Mobesquive_div = Mobesquive_div + Spel3Mal_esquive_div;
                                    }
                                    if(Spel3Mal_degat_moins!=0)
                                    {
                                        Mobdegat_moins = Mobdegat_moins + Spel3Mal_degat_moins;
                                    }
                                    if(Spel3Mal_degat_divp!=0)
                                    {
                                        Mobdegat_divp = Mobdegat_divp + Spel3Mal_degat_divp;
                                    }
                                    if(Spel3Mal_degatmag_moins!=0)
                                    {
                                        Mobdegatmag_moins = Mobdegatmag_moins + Spel3Mal_degatmag_moins;
                                    }
                                    if(Spel3Mal_degatmag_divp!=0)
                                    {
                                        Mobdegatmag_divp = Mobdegatmag_divp + Spel3Mal_degatmag_divp;
                                    }
                                }
                                sort_duree_3=Spel3duree;
                                PlayerTurn=false;
                            }
                        }
                    });
                ///////////////////////////fin liste sort///////////////////////////
                //fuite//////////////////
                fuite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(PlayerTurn==true)
                        {
                            Random rand = new Random();
                            int tentefuite = rand.nextInt(100);
                            int Diffmoblvl =0;
                            int Diffherolvl =0;
                            if(LvlHero > LvlMob)
                            {
                                Diffherolvl = LvlHero - LvlMob;
                            }
                            else
                            {
                                Diffmoblvl = LvlMob - LvlHero;
                            }
                            if(tentefuite< 50+Diffherolvl-Diffmoblvl)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur parvient à fuir");
                                    }
                                });
                                fuitereussite=true;
                            }
                            else
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur ne parvient pas à fuir");
                                    }
                                });
                            }
                            PlayerTurn=false;

                        }
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(PlayerTurn==true)
                        {
                            ecran_de_base();
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    message.setText("Tour du joueur");
                                }
                            });
                        }
                    }
                });
                //affichage des objets  de l inventaire
                inventaire.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(PlayerTurn==true)
                        {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    message.setText("Joueur regarde ses objets");
                                }
                            });
                            attack.setVisibility(View.GONE);
                            defendre.setVisibility(View.GONE);
                            sorts.setVisibility(View.GONE);
                            fuite.setVisibility(View.GONE);
                            inventaire.setVisibility(View.GONE);

                            back.setVisibility(View.VISIBLE);
                            Objet0.setVisibility(View.VISIBLE);
                            Objet1.setVisibility(View.VISIBLE);
                            Objet2.setVisibility(View.VISIBLE);
                            Objet3.setVisibility(View.VISIBLE);
                        }
                    }
                });
                    ////////objets de l'inventaire//////
                    Objet0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(PlayerTurn==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur utilise " + Objet0Name+ " !");
                                    }
                                });
                                //Type de sort ?
                                if(Objet0Type%2==0) //offensif
                                {
                                    if(Objet0Mag==true) //sort de nature magique ?
                                    {
                                        int damage = (int)(Objet0dmg - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }

                                        final int finalDamage = damage;

                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                message.setText(Objet0Name+ " inflige " + finalDamage +" dégats ");
                                            }
                                        });
                                        ihpMob = ihpMob - damage;
                                    }
                                    else
                                    {

                                        int damage = (int) (Objet0dmg -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }
                                        ihpMob = ihpMob - damage;
                                        final int finalDamage = damage;
                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                message.setText(Objet0Name + " inflige " + finalDamage +" dégats ");
                                            }
                                        });

                                    }

                                    RefreshMobPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Objet0Type%5==0) //heal
                                {
                                    int recup = Objet0dmg;
                                    ihpHero=ihpHero+recup;
                                    if(ihpHero > maxhpHero)
                                    {
                                        recup = recup - (ihpHero-maxhpHero);
                                        ihpHero = maxhpHero;
                                    }
                                    final int finalRecup = recup;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("le joueur réucpère " + finalRecup + " pv");
                                        }
                                    });
                                    RefreshHeroPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Objet0Type%3==0) //bonus
                                {
                                    if(Objet0Bon_armure_plus !=0)
                                    {
                                        Heroarmure_plus = Heroarmure_plus + Objet0Bon_armure_plus;
                                    }
                                    if(Objet0Bon_armure_mult !=0)
                                    {
                                        Heroarmure_mult = Heroesquive_mult + Objet0Bon_armure_mult;
                                    }
                                    if(Objet0Bon_armuremag_plus !=0)
                                    {
                                        Heroarmuremag_plus = Heroarmuremag_plus + Objet0Bon_armuremag_plus;
                                    }
                                    if(Objet0Bon_armuremag_mult !=0)
                                    {
                                        Heroarmuremag_mult = Heroarmuremag_mult + Objet0Bon_armuremag_mult;
                                    }
                                    if(Objet0Bon_degat_plus !=0)
                                    {
                                        Herodegat_plus = Herodegat_plus + Objet0Bon_degat_plus;
                                    }
                                    if(Objet0Bon_degat_multp !=0)
                                    {
                                        Herodegat_multp = Herodegat_multp + Objet0Bon_degat_multp;
                                    }
                                    if(Objet0Bon_degatmag_plus !=0)
                                    {
                                        Herodegatmag_plus = Herodegatmag_plus + Objet0Bon_degatmag_plus;
                                    }
                                    if(Objet0Bon_degatmag_multp !=0)
                                    {
                                        Herodegatmag_multp = Herodegatmag_multp + Objet0Bon_degatmag_multp;
                                    }
                                    if(Objet0Bon_esquive_plus !=0)
                                    {
                                        Heroesquive_plus = Heroesquive_plus + Objet0Bon_esquive_plus;
                                    }
                                    if(Objet0Bon_esquive_mult !=0)
                                    {
                                        Heroesquive_mult = Heroesquive_mult + Objet0Bon_esquive_mult;
                                    }

                                }
                                if(Objet0Type%7==0) //malus
                                {
                                    if(Objet0Mal_armure_moins!=0)
                                    {
                                        Mobarmure_moins = Mobarmure_moins + Objet0Mal_armure_moins;
                                    }
                                    if(Objet0Mal_armure_div!=0)
                                    {
                                        Mobarmure_div = Mobarmure_div + Objet0Mal_armure_div;
                                    }
                                    if(Objet0Mal_armuremag_moins!=0)
                                    {
                                        Mobarmuremag_moins = Mobarmuremag_moins + Objet0Mal_armuremag_moins;
                                    }
                                    if(Objet0Mal_armuremag_div !=0)
                                    {
                                        Mobarmuremag_div = Mobarmuremag_div + Objet0Mal_armuremag_div;
                                    }
                                    if(Objet0Mal_esquive_moins!=0)
                                    {
                                        Mobesquive_moins = Mobesquive_moins + Objet0Mal_esquive_moins;
                                    }
                                    if(Objet0Mal_esquive_div!=0)
                                    {
                                        Mobesquive_div = Mobesquive_div + Objet0Mal_esquive_div;
                                    }
                                    if(Objet0Mal_degat_moins!=0)
                                    {
                                        Mobdegat_moins = Mobdegat_moins + Objet0Mal_degat_moins;
                                    }
                                    if(Objet0Mal_degat_divp!=0)
                                    {
                                        Mobdegat_divp = Mobdegat_divp + Objet0Mal_degat_divp;
                                    }
                                    if(Objet0Mal_degatmag_moins!=0)
                                    {
                                        Mobdegatmag_moins = Mobdegatmag_moins + Objet0Mal_degatmag_moins;
                                    }
                                    if(Objet0Mal_degatmag_divp!=0)
                                    {
                                        Mobdegatmag_divp = Mobdegatmag_divp + Objet0Mal_degatmag_divp;
                                    }
                                }

                                PlayerTurn=false;
                                objet_duree_0=Objet0duree;
                            }
                        }
                    });
                    Objet1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(PlayerTurn==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur utilise " + Objet1Name+ " !");
                                    }
                                });
                                //Type de sort ?
                                if(Objet1Type%2==0) //offensif
                                {
                                    if(Objet1Mag==true) //sort de nature magique ?
                                    {
                                        int damage = (int)(Objet1dmg - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }

                                        final int finalDamage = damage;

                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                message.setText(Objet1Name+ " inflige " + finalDamage +" dégats ");
                                            }
                                        });
                                        ihpMob = ihpMob - damage;
                                    }
                                    else
                                    {

                                        int damage = (int) (Objet1dmg -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }
                                        ihpMob = ihpMob - damage;
                                        final int finalDamage = damage;
                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                message.setText(Objet1Name + " inflige " + finalDamage +" dégats ");
                                            }
                                        });

                                    }

                                    RefreshMobPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Objet1Type%5==0) //heal
                                {
                                    int recup = Objet1dmg;
                                    ihpHero=ihpHero+recup;
                                    if(ihpHero > maxhpHero)
                                    {
                                        recup = recup - (ihpHero-maxhpHero);
                                        ihpHero = maxhpHero;
                                    }
                                    final int finalRecup = recup;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("le joueur réucpère " + finalRecup + " pv");
                                        }
                                    });
                                    RefreshHeroPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Objet1Type%3==0) //bonus
                                {
                                    if(Objet1Bon_armure_plus !=0)
                                    {
                                        Heroarmure_plus = Heroarmure_plus + Objet1Bon_armure_plus;
                                    }
                                    if(Objet1Bon_armure_mult !=0)
                                    {
                                        Heroarmure_mult = Heroesquive_mult + Objet1Bon_armure_mult;
                                    }
                                    if(Objet1Bon_armuremag_plus !=0)
                                    {
                                        Heroarmuremag_plus = Heroarmuremag_plus + Objet1Bon_armuremag_plus;
                                    }
                                    if(Objet1Bon_armuremag_mult !=0)
                                    {
                                        Heroarmuremag_mult = Heroarmuremag_mult + Objet1Bon_armuremag_mult;
                                    }
                                    if(Objet1Bon_degat_plus !=0)
                                    {
                                        Herodegat_plus = Herodegat_plus + Objet1Bon_degat_plus;
                                    }
                                    if(Objet1Bon_degat_multp !=0)
                                    {
                                        Herodegat_multp = Herodegat_multp + Objet1Bon_degat_multp;
                                    }
                                    if(Objet1Bon_degatmag_plus !=0)
                                    {
                                        Herodegatmag_plus = Herodegatmag_plus + Objet1Bon_degatmag_plus;
                                    }
                                    if(Objet1Bon_degatmag_multp !=0)
                                    {
                                        Herodegatmag_multp = Herodegatmag_multp + Objet1Bon_degatmag_multp;
                                    }
                                    if(Objet1Bon_esquive_plus !=0)
                                    {
                                        Heroesquive_plus = Heroesquive_plus + Objet1Bon_esquive_plus;
                                    }
                                    if(Objet1Bon_esquive_mult !=0)
                                    {
                                        Heroesquive_mult = Heroesquive_mult + Objet1Bon_esquive_mult;
                                    }

                                }
                                if(Objet1Type%7==0) //malus
                                {
                                    if(Objet1Mal_armure_moins!=0)
                                    {
                                        Mobarmure_moins = Mobarmure_moins + Objet1Mal_armure_moins;
                                    }
                                    if(Objet1Mal_armure_div!=0)
                                    {
                                        Mobarmure_div = Mobarmure_div + Objet1Mal_armure_div;
                                    }
                                    if(Objet1Mal_armuremag_moins!=0)
                                    {
                                        Mobarmuremag_moins = Mobarmuremag_moins + Objet1Mal_armuremag_moins;
                                    }
                                    if(Objet1Mal_armuremag_div !=0)
                                    {
                                        Mobarmuremag_div = Mobarmuremag_div + Objet1Mal_armuremag_div;
                                    }
                                    if(Objet1Mal_esquive_moins!=0)
                                    {
                                        Mobesquive_moins = Mobesquive_moins + Objet1Mal_esquive_moins;
                                    }
                                    if(Objet1Mal_esquive_div!=0)
                                    {
                                        Mobesquive_div = Mobesquive_div + Objet1Mal_esquive_div;
                                    }
                                    if(Objet1Mal_degat_moins!=0)
                                    {
                                        Mobdegat_moins = Mobdegat_moins + Objet1Mal_degat_moins;
                                    }
                                    if(Objet1Mal_degat_divp!=0)
                                    {
                                        Mobdegat_divp = Mobdegat_divp + Objet1Mal_degat_divp;
                                    }
                                    if(Objet1Mal_degatmag_moins!=0)
                                    {
                                        Mobdegatmag_moins = Mobdegatmag_moins + Objet1Mal_degatmag_moins;
                                    }
                                    if(Objet1Mal_degatmag_divp!=0)
                                    {
                                        Mobdegatmag_divp = Mobdegatmag_divp + Objet1Mal_degatmag_divp;
                                    }
                                }

                                PlayerTurn=false;
                                objet_duree_1=Objet1duree;
                            }
                        }
                    });
                    Objet2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(PlayerTurn==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur utilise " + Objet2Name+ " !");
                                    }
                                });
                                //Type de sort ?
                                if(Objet2Type%2==0) //offensif
                                {
                                    if(Objet2Mag==true) //sort de nature magique ?
                                    {
                                        int damage = (int)(Objet2dmg - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }

                                        final int finalDamage = damage;

                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                message.setText(Objet2Name+ " inflige " + finalDamage +" dégats ");
                                            }
                                        });
                                        ihpMob = ihpMob - damage;
                                    }
                                    else
                                    {

                                        int damage = (int) (Objet2dmg -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }
                                        ihpMob = ihpMob - damage;
                                        final int finalDamage = damage;
                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                message.setText(Objet2Name + " inflige " + finalDamage +" dégats ");
                                            }
                                        });

                                    }
                                    RefreshMobPv();

                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Objet2Type%5==0) //heal
                                {
                                    int recup = Objet2dmg;
                                    ihpHero=ihpHero+recup;
                                    if(ihpHero > maxhpHero)
                                    {
                                        recup = recup - (ihpHero-maxhpHero);
                                        ihpHero = maxhpHero;
                                    }
                                    final int finalRecup = recup;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("le joueur réucpère " + finalRecup + " pv");
                                        }
                                    });
                                    RefreshHeroPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Objet2Type%3==0) //bonus
                                {
                                    if(Objet2Bon_armure_plus !=0)
                                    {
                                        Heroarmure_plus = Heroarmure_plus + Objet2Bon_armure_plus;
                                    }
                                    if(Objet2Bon_armure_mult !=0)
                                    {
                                        Heroarmure_mult = Heroesquive_mult + Objet2Bon_armure_mult;
                                    }
                                    if(Objet2Bon_armuremag_plus !=0)
                                    {
                                        Heroarmuremag_plus = Heroarmuremag_plus + Objet2Bon_armuremag_plus;
                                    }
                                    if(Objet2Bon_armuremag_mult !=0)
                                    {
                                        Heroarmuremag_mult = Heroarmuremag_mult + Objet2Bon_armuremag_mult;
                                    }
                                    if(Objet2Bon_degat_plus !=0)
                                    {
                                        Herodegat_plus = Herodegat_plus + Objet2Bon_degat_plus;
                                    }
                                    if(Objet2Bon_degat_multp !=0)
                                    {
                                        Herodegat_multp = Herodegat_multp + Objet2Bon_degat_multp;
                                    }
                                    if(Objet2Bon_degatmag_plus !=0)
                                    {
                                        Herodegatmag_plus = Herodegatmag_plus + Objet2Bon_degatmag_plus;
                                    }
                                    if(Objet2Bon_degatmag_multp !=0)
                                    {
                                        Herodegatmag_multp = Herodegatmag_multp + Objet2Bon_degatmag_multp;
                                    }
                                    if(Objet2Bon_esquive_plus !=0)
                                    {
                                        Heroesquive_plus = Heroesquive_plus + Objet2Bon_esquive_plus;
                                    }
                                    if(Objet2Bon_esquive_mult !=0)
                                    {
                                        Heroesquive_mult = Heroesquive_mult + Objet2Bon_esquive_mult;
                                    }

                                }
                                if(Objet2Type%7==0) //malus
                                {
                                    if(Objet2Mal_armure_moins!=0)
                                    {
                                        Mobarmure_moins = Mobarmure_moins + Objet2Mal_armure_moins;
                                    }
                                    if(Objet2Mal_armure_div!=0)
                                    {
                                        Mobarmure_div = Mobarmure_div + Objet2Mal_armure_div;
                                    }
                                    if(Objet2Mal_armuremag_moins!=0)
                                    {
                                        Mobarmuremag_moins = Mobarmuremag_moins + Objet2Mal_armuremag_moins;
                                    }
                                    if(Objet2Mal_armuremag_div !=0)
                                    {
                                        Mobarmuremag_div = Mobarmuremag_div + Objet2Mal_armuremag_div;
                                    }
                                    if(Objet2Mal_esquive_moins!=0)
                                    {
                                        Mobesquive_moins = Mobesquive_moins + Objet2Mal_esquive_moins;
                                    }
                                    if(Objet2Mal_esquive_div!=0)
                                    {
                                        Mobesquive_div = Mobesquive_div + Objet2Mal_esquive_div;
                                    }
                                    if(Objet2Mal_degat_moins!=0)
                                    {
                                        Mobdegat_moins = Mobdegat_moins + Objet2Mal_degat_moins;
                                    }
                                    if(Objet2Mal_degat_divp!=0)
                                    {
                                        Mobdegat_divp = Mobdegat_divp + Objet2Mal_degat_divp;
                                    }
                                    if(Objet2Mal_degatmag_moins!=0)
                                    {
                                        Mobdegatmag_moins = Mobdegatmag_moins + Objet2Mal_degatmag_moins;
                                    }
                                    if(Objet2Mal_degatmag_divp!=0)
                                    {
                                        Mobdegatmag_divp = Mobdegatmag_divp + Objet2Mal_degatmag_divp;
                                    }
                                }

                                PlayerTurn=false;
                                objet_duree_2=Objet2duree;
                            }
                        }
                    });
                    Objet3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(PlayerTurn==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("Joueur utilise " + Objet3Name+ " !");
                                    }
                                });
                                //Type de sort ?
                                if(Objet3Type%2==0) //offensif
                                {
                                    if(Objet3Mag==true) //sort de nature magique ?
                                    {
                                        int damage = (int)(Objet3dmg - (((armureMagMob + Mobarmuremag_plus -Mobarmuremag_moins)*Mobarmuremag_mult)/Mobarmuremag_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }

                                        final int finalDamage = damage;

                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                message.setText(Objet3Name+ " inflige " + finalDamage +" dégats ");
                                            }
                                        });
                                        ihpMob = ihpMob - damage;
                                    }
                                    else
                                    {

                                        int damage = (int) (Objet3dmg -  (((armureMob+Mobarmure_plus-Mobarmure_moins)*Mobarmure_mult)/Mobarmure_div));
                                        if(damage <0)
                                        {
                                            damage =0;
                                        }
                                        ihpMob = ihpMob - damage;
                                        final int finalDamage = damage;
                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                message.setText(Objet3Name + " inflige " + finalDamage +" dégats ");
                                            }
                                        });

                                    }
                                    RefreshMobPv();

                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Objet3Type%5==0) //heal
                                {
                                    int recup = Objet3dmg;
                                    ihpHero=ihpHero+recup;
                                    if(ihpHero > maxhpHero)
                                    {
                                        recup = recup - (ihpHero-maxhpHero);
                                        ihpHero = maxhpHero;
                                    }
                                    final int finalRecup = recup;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("le joueur réucpère " + finalRecup + " pv");
                                        }
                                    });
                                    RefreshHeroPv();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(Objet3Type%3==0) //bonus
                                {
                                    if(Objet3Bon_armure_plus !=0)
                                    {
                                        Heroarmure_plus = Heroarmure_plus + Objet3Bon_armure_plus;
                                    }
                                    if(Objet3Bon_armure_mult !=0)
                                    {
                                        Heroarmure_mult = Heroesquive_mult + Objet3Bon_armure_mult;
                                    }
                                    if(Objet3Bon_armuremag_plus !=0)
                                    {
                                        Heroarmuremag_plus = Heroarmuremag_plus + Objet3Bon_armuremag_plus;
                                    }
                                    if(Objet3Bon_armuremag_mult !=0)
                                    {
                                        Heroarmuremag_mult = Heroarmuremag_mult + Objet3Bon_armuremag_mult;
                                    }
                                    if(Objet3Bon_degat_plus !=0)
                                    {
                                        Herodegat_plus = Herodegat_plus + Objet3Bon_degat_plus;
                                    }
                                    if(Objet3Bon_degat_multp !=0)
                                    {
                                        Herodegat_multp = Herodegat_multp + Objet3Bon_degat_multp;
                                    }
                                    if(Objet3Bon_degatmag_plus !=0)
                                    {
                                        Herodegatmag_plus = Herodegatmag_plus + Objet3Bon_degatmag_plus;
                                    }
                                    if(Objet3Bon_degatmag_multp !=0)
                                    {
                                        Herodegatmag_multp = Herodegatmag_multp + Objet3Bon_degatmag_multp;
                                    }
                                    if(Objet3Bon_esquive_plus !=0)
                                    {
                                        Heroesquive_plus = Heroesquive_plus + Objet3Bon_esquive_plus;
                                    }
                                    if(Objet3Bon_esquive_mult !=0)
                                    {
                                        Heroesquive_mult = Heroesquive_mult + Objet3Bon_esquive_mult;
                                    }

                                }
                                if(Objet3Type%7==0) //malus
                                {
                                    if(Objet3Mal_armure_moins!=0)
                                    {
                                        Mobarmure_moins = Mobarmure_moins + Objet3Mal_armure_moins;
                                    }
                                    if(Objet3Mal_armure_div!=0)
                                    {
                                        Mobarmure_div = Mobarmure_div + Objet3Mal_armure_div;
                                    }
                                    if(Objet3Mal_armuremag_moins!=0)
                                    {
                                        Mobarmuremag_moins = Mobarmuremag_moins + Objet3Mal_armuremag_moins;
                                    }
                                    if(Objet3Mal_armuremag_div !=0)
                                    {
                                        Mobarmuremag_div = Mobarmuremag_div + Objet3Mal_armuremag_div;
                                    }
                                    if(Objet3Mal_esquive_moins!=0)
                                    {
                                        Mobesquive_moins = Mobesquive_moins + Objet3Mal_esquive_moins;
                                    }
                                    if(Objet3Mal_esquive_div!=0)
                                    {
                                        Mobesquive_div = Mobesquive_div + Objet3Mal_esquive_div;
                                    }
                                    if(Objet3Mal_degat_moins!=0)
                                    {
                                        Mobdegat_moins = Mobdegat_moins + Objet3Mal_degat_moins;
                                    }
                                    if(Objet3Mal_degat_divp!=0)
                                    {
                                        Mobdegat_divp = Mobdegat_divp + Objet3Mal_degat_divp;
                                    }
                                    if(Objet3Mal_degatmag_moins!=0)
                                    {
                                        Mobdegatmag_moins = Mobdegatmag_moins + Objet3Mal_degatmag_moins;
                                    }
                                    if(Objet3Mal_degatmag_divp!=0)
                                    {
                                        Mobdegatmag_divp = Mobdegatmag_divp + Objet3Mal_degatmag_divp;
                                    }
                                }

                                PlayerTurn=false;
                                objet_duree_3=Objet3duree;
                            }
                        }
                    });
            }

            refreshStats();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            VerifVictoireHero(ihpMob);
        }
    }





    //Tour du Mob
    class MobRunnable implements Runnable
    {
        public boolean MobTurn;

        MobRunnable (boolean state)
        {
            this.MobTurn=state;
        }
        private TextView message;
        @Override
        public void run() {

            message = findViewById(R.id.Text_cmb);
            MobresetBonus();
            ResetHeromalus();

            ///verif si sort du mobs toujours en action///
            if(mobsort_duree_0!=0)
            {
                if(MobSpel0Type%2==0) //offensif
                {
                    if(MobSpel0Mag==true) //sort de nature magique ?
                    {
                        int damage = (int)((((((degatMob+Mobdegatmag_plus-Mobdegatmag_moins)+MobSpel0dmg)*Mobdegat_multp)/Mobdegatmag_divp)*1.5) - (((armureMagHero + Heroarmuremag_plus -Heroarmuremag_moins)*Heroarmuremag_mult)/Heroarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }

                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    message.setText(" héro subit " + finalDamage + " de " + MobSpel0Name);
                                }
                            });
                        ihpHero = ihpHero - damage;

                    }
                    else
                    {

                        int damage = (int) (((((degatMob+Mobdegat_plus-Mobdegat_moins)*Mobdegat_multp)+MobSpel0dmg)/Mobdegat_divp) -  (((armureHero+Heroarmure_plus-Heroarmure_moins)*Heroarmure_mult)/Heroarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpHero = ihpHero - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(" héro subit " + finalDamage + " de " + MobSpel0Name);
                            }
                        });

                    }
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(MobSpel0Type%5==0) //heal
                {
                    int recup = (int)((degatMob+MobSpel0dmg)*1.3);
                    ihpMob=ihpMob+recup;
                    if(ihpMob > maxhpMob)
                    {
                        recup = recup - (ihpMob-maxhpMob);
                        ihpMob = maxhpMob;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le mob réucpère " + finalRecup + " pv grâce à " + MobSpel0Name);
                        }
                    });
                    RefreshMobPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(MobSpel0Type%3==0) //bonus
                {
                    if(MobSpel0Bon_armure_plus !=0)
                    {
                        Mobarmure_plus = Mobarmure_plus + MobSpel0Bon_armure_plus;
                    }
                    if(MobSpel0Bon_armure_mult !=0)
                    {
                        Mobarmure_mult = Mobarmure_mult + MobSpel0Bon_armure_mult;
                    }
                    if(MobSpel0Bon_armuremag_plus !=0)
                    {
                        Mobarmuremag_plus = Mobarmuremag_plus + MobSpel0Bon_armuremag_plus;
                    }
                    if(MobSpel0Bon_armuremag_mult !=0)
                    {
                        Mobarmuremag_mult = Mobarmuremag_mult + MobSpel0Bon_armuremag_mult;
                    }
                    if(MobSpel0Bon_degat_plus !=0)
                    {
                        Mobdegat_plus = Mobdegat_plus + MobSpel0Bon_degat_plus;
                    }
                    if(MobSpel0Bon_degat_multp !=0)
                    {
                        Mobdegat_multp = Mobdegat_multp + MobSpel0Bon_degat_multp;
                    }
                    if(MobSpel0Bon_degatmag_plus !=0)
                    {
                        Mobdegatmag_plus = Mobdegatmag_plus + MobSpel0Bon_degatmag_plus;
                    }
                    if(MobSpel0Bon_degatmag_multp !=0)
                    {
                        Mobdegatmag_multp = Mobdegatmag_multp + MobSpel0Bon_degatmag_multp;
                    }
                    if(MobSpel0Bon_esquive_plus !=0)
                    {
                        Mobesquive_plus = Mobesquive_plus + MobSpel0Bon_esquive_plus;
                    }
                    if(MobSpel0Bon_esquive_mult !=0)
                    {
                        Mobesquive_mult = Mobesquive_mult + MobSpel0Bon_esquive_mult;
                    }

                }
                if(MobSpel0Type%7==0) //malus
                {
                    if(MobSpel0Mal_armure_moins!=0)
                    {
                        Heroarmure_moins = Heroarmure_moins + MobSpel0Mal_armure_moins;
                    }
                    if(MobSpel0Mal_armure_div!=0)
                    {
                        Heroarmure_div = Heroarmure_div + MobSpel0Mal_armure_div;
                    }
                    if(MobSpel0Mal_armuremag_moins!=0)
                    {
                        Heroarmuremag_moins = Heroarmuremag_moins + MobSpel0Mal_armuremag_moins;
                    }
                    if(MobSpel0Mal_armuremag_div !=0)
                    {
                        Heroarmuremag_div = Heroarmuremag_div + MobSpel0Mal_armuremag_div;
                    }
                    if(MobSpel0Mal_esquive_moins!=0)
                    {
                        Heroesquive_moins = Heroesquive_moins + MobSpel0Mal_esquive_moins;
                    }
                    if(MobSpel0Mal_esquive_div!=0)
                    {
                        Heroesquive_div = Heroesquive_div + MobSpel0Mal_esquive_div;
                    }
                    if(MobSpel0Mal_degat_moins!=0)
                    {
                        Herodegat_moins = Herodegat_moins + MobSpel0Mal_degat_moins;
                    }
                    if(MobSpel0Mal_degat_divp!=0)
                    {
                        Herodegat_divp = Herodegat_divp + MobSpel0Mal_degat_divp;
                    }
                    if(MobSpel0Mal_degatmag_moins!=0)
                    {
                        Herodegatmag_moins = Herodegatmag_moins + MobSpel0Mal_degatmag_moins;
                    }
                    if(MobSpel0Mal_degatmag_divp!=0)
                    {
                        Herodegatmag_divp = Herodegatmag_divp + MobSpel0Mal_degatmag_divp;
                    }
                }


                mobsort_duree_0= mobsort_duree_0-1;
                if(mobsort_duree_0 ==0)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(MobSpel0Name + " se dissipe");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            if(mobsort_duree_1!=0)
            {
                if(MobSpel1Type%2==0) //offensif
                {
                    if(MobSpel1Mag==true) //sort de nature magique ?
                    {
                        int damage = (int)((((((degatMob+Mobdegatmag_plus-Mobdegatmag_moins)+MobSpel1dmg)*Mobdegat_multp)/Mobdegatmag_divp)*1.5) - (((armureMagHero + Heroarmuremag_plus -Heroarmuremag_moins)*Heroarmuremag_mult)/Heroarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }

                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(" héro subit " + finalDamage + " de " + MobSpel1Name);
                            }
                        });
                        ihpHero = ihpHero - damage;

                    }
                    else
                    {

                        int damage = (int) (((((degatMob+Mobdegat_plus-Mobdegat_moins)*Mobdegat_multp)+MobSpel1dmg)/Mobdegat_divp) -  (((armureHero+Heroarmure_plus-Heroarmure_moins)*Heroarmure_mult)/Heroarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpHero = ihpHero - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(" héro subit " + finalDamage + " de " + MobSpel1Name);
                            }
                        });

                    }
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(MobSpel1Type%5==0) //heal
                {
                    int recup = (int)((degatMob+MobSpel1dmg)*1.3);
                    ihpMob=ihpMob+recup;
                    if(ihpMob > maxhpMob)
                    {
                        recup = recup - (ihpMob-maxhpMob);
                        ihpMob = maxhpMob;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le mob réucpère " + finalRecup + " pv grâce à " + MobSpel1Name);
                        }
                    });
                    RefreshMobPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(MobSpel1Type%3==0) //bonus
                {
                    if(MobSpel1Bon_armure_plus !=0)
                    {
                        Mobarmure_plus = Mobarmure_plus + MobSpel1Bon_armure_plus;
                    }
                    if(MobSpel1Bon_armure_mult !=0)
                    {
                        Mobarmure_mult = Mobarmure_mult + MobSpel1Bon_armure_mult;
                    }
                    if(MobSpel1Bon_armuremag_plus !=0)
                    {
                        Mobarmuremag_plus = Mobarmuremag_plus + MobSpel1Bon_armuremag_plus;
                    }
                    if(MobSpel1Bon_armuremag_mult !=0)
                    {
                        Mobarmuremag_mult = Mobarmuremag_mult + MobSpel1Bon_armuremag_mult;
                    }
                    if(MobSpel1Bon_degat_plus !=0)
                    {
                        Mobdegat_plus = Mobdegat_plus + MobSpel1Bon_degat_plus;
                    }
                    if(MobSpel1Bon_degat_multp !=0)
                    {
                        Mobdegat_multp = Mobdegat_multp + MobSpel1Bon_degat_multp;
                    }
                    if(MobSpel1Bon_degatmag_plus !=0)
                    {
                        Mobdegatmag_plus = Mobdegatmag_plus + MobSpel1Bon_degatmag_plus;
                    }
                    if(MobSpel1Bon_degatmag_multp !=0)
                    {
                        Mobdegatmag_multp = Mobdegatmag_multp + MobSpel1Bon_degatmag_multp;
                    }
                    if(MobSpel1Bon_esquive_plus !=0)
                    {
                        Mobesquive_plus = Mobesquive_plus + MobSpel1Bon_esquive_plus;
                    }
                    if(MobSpel1Bon_esquive_mult !=0)
                    {
                        Mobesquive_mult = Mobesquive_mult + MobSpel1Bon_esquive_mult;
                    }

                }
                if(MobSpel1Type%7==0) //malus
                {
                    if(MobSpel1Mal_armure_moins!=0)
                    {
                        Heroarmure_moins = Heroarmure_moins + MobSpel1Mal_armure_moins;
                    }
                    if(MobSpel1Mal_armure_div!=0)
                    {
                        Heroarmure_div = Heroarmure_div + MobSpel1Mal_armure_div;
                    }
                    if(MobSpel1Mal_armuremag_moins!=0)
                    {
                        Heroarmuremag_moins = Heroarmuremag_moins + MobSpel1Mal_armuremag_moins;
                    }
                    if(MobSpel1Mal_armuremag_div !=0)
                    {
                        Heroarmuremag_div = Heroarmuremag_div + MobSpel1Mal_armuremag_div;
                    }
                    if(MobSpel1Mal_esquive_moins!=0)
                    {
                        Heroesquive_moins = Heroesquive_moins + MobSpel1Mal_esquive_moins;
                    }
                    if(MobSpel1Mal_esquive_div!=0)
                    {
                        Heroesquive_div = Heroesquive_div + MobSpel1Mal_esquive_div;
                    }
                    if(MobSpel1Mal_degat_moins!=0)
                    {
                        Herodegat_moins = Herodegat_moins + MobSpel1Mal_degat_moins;
                    }
                    if(MobSpel1Mal_degat_divp!=0)
                    {
                        Herodegat_divp = Herodegat_divp + MobSpel1Mal_degat_divp;
                    }
                    if(MobSpel1Mal_degatmag_moins!=0)
                    {
                        Herodegatmag_moins = Herodegatmag_moins + MobSpel1Mal_degatmag_moins;
                    }
                    if(MobSpel1Mal_degatmag_divp!=0)
                    {
                        Herodegatmag_divp = Herodegatmag_divp + MobSpel1Mal_degatmag_divp;
                    }
                }


                mobsort_duree_1= mobsort_duree_1-1;
                if(mobsort_duree_1 ==0)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(MobSpel1Name + " se dissipe");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            if(mobsort_duree_2!=0)
            {
                if(MobSpel2Type%2==0) //offensif
                {
                    if(MobSpel2Mag==true) //sort de nature magique ?
                    {
                        int damage = (int)((((((degatMob+Mobdegatmag_plus-Mobdegatmag_moins)+MobSpel2dmg)*Mobdegat_multp)/Mobdegatmag_divp)*1.5) - (((armureMagHero + Heroarmuremag_plus -Heroarmuremag_moins)*Heroarmuremag_mult)/Heroarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }

                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(" héro subit " + finalDamage + " de " + MobSpel2Name);
                            }
                        });
                        ihpHero = ihpHero - damage;

                    }
                    else
                    {

                        int damage = (int) (((((degatMob+Mobdegat_plus-Mobdegat_moins)*Mobdegat_multp)+MobSpel2dmg)/Mobdegat_divp) -  (((armureHero+Heroarmure_plus-Heroarmure_moins)*Heroarmure_mult)/Heroarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpHero = ihpHero - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(" héro subit " + finalDamage + " de " + MobSpel2Name);
                            }
                        });

                    }
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(MobSpel2Type%5==0) //heal
                {
                    int recup = (int)((degatMob+MobSpel2dmg)*1.3);
                    ihpMob=ihpMob+recup;
                    if(ihpMob > maxhpMob)
                    {
                        recup = recup - (ihpMob-maxhpMob);
                        ihpMob = maxhpMob;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le mob réucpère " + finalRecup + " pv grâce à " + MobSpel2Name);
                        }
                    });
                    RefreshMobPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(MobSpel2Type%3==0) //bonus
                {
                    if(MobSpel2Bon_armure_plus !=0)
                    {
                        Mobarmure_plus = Mobarmure_plus + MobSpel2Bon_armure_plus;
                    }
                    if(MobSpel2Bon_armure_mult !=0)
                    {
                        Mobarmure_mult = Mobarmure_mult + MobSpel2Bon_armure_mult;
                    }
                    if(MobSpel2Bon_armuremag_plus !=0)
                    {
                        Mobarmuremag_plus = Mobarmuremag_plus + MobSpel2Bon_armuremag_plus;
                    }
                    if(MobSpel2Bon_armuremag_mult !=0)
                    {
                        Mobarmuremag_mult = Mobarmuremag_mult + MobSpel2Bon_armuremag_mult;
                    }
                    if(MobSpel2Bon_degat_plus !=0)
                    {
                        Mobdegat_plus = Mobdegat_plus + MobSpel2Bon_degat_plus;
                    }
                    if(MobSpel2Bon_degat_multp !=0)
                    {
                        Mobdegat_multp = Mobdegat_multp + MobSpel2Bon_degat_multp;
                    }
                    if(MobSpel2Bon_degatmag_plus !=0)
                    {
                        Mobdegatmag_plus = Mobdegatmag_plus + MobSpel2Bon_degatmag_plus;
                    }
                    if(MobSpel2Bon_degatmag_multp !=0)
                    {
                        Mobdegatmag_multp = Mobdegatmag_multp + MobSpel2Bon_degatmag_multp;
                    }
                    if(MobSpel2Bon_esquive_plus !=0)
                    {
                        Mobesquive_plus = Mobesquive_plus + MobSpel2Bon_esquive_plus;
                    }
                    if(MobSpel2Bon_esquive_mult !=0)
                    {
                        Mobesquive_mult = Mobesquive_mult + MobSpel2Bon_esquive_mult;
                    }

                }
                if(MobSpel2Type%7==0) //malus
                {
                    if(MobSpel2Mal_armure_moins!=0)
                    {
                        Heroarmure_moins = Heroarmure_moins + MobSpel2Mal_armure_moins;
                    }
                    if(MobSpel2Mal_armure_div!=0)
                    {
                        Heroarmure_div = Heroarmure_div + MobSpel2Mal_armure_div;
                    }
                    if(MobSpel2Mal_armuremag_moins!=0)
                    {
                        Heroarmuremag_moins = Heroarmuremag_moins + MobSpel2Mal_armuremag_moins;
                    }
                    if(MobSpel2Mal_armuremag_div !=0)
                    {
                        Heroarmuremag_div = Heroarmuremag_div + MobSpel2Mal_armuremag_div;
                    }
                    if(MobSpel2Mal_esquive_moins!=0)
                    {
                        Heroesquive_moins = Heroesquive_moins + MobSpel2Mal_esquive_moins;
                    }
                    if(MobSpel2Mal_esquive_div!=0)
                    {
                        Heroesquive_div = Heroesquive_div + MobSpel2Mal_esquive_div;
                    }
                    if(MobSpel2Mal_degat_moins!=0)
                    {
                        Herodegat_moins = Herodegat_moins + MobSpel2Mal_degat_moins;
                    }
                    if(MobSpel2Mal_degat_divp!=0)
                    {
                        Herodegat_divp = Herodegat_divp + MobSpel2Mal_degat_divp;
                    }
                    if(MobSpel2Mal_degatmag_moins!=0)
                    {
                        Herodegatmag_moins = Herodegatmag_moins + MobSpel2Mal_degatmag_moins;
                    }
                    if(MobSpel2Mal_degatmag_divp!=0)
                    {
                        Herodegatmag_divp = Herodegatmag_divp + MobSpel2Mal_degatmag_divp;
                    }
                }


                mobsort_duree_2= mobsort_duree_2-1;
                if(mobsort_duree_2 ==0)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(MobSpel2Name + " se dissipe");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            if(mobsort_duree_3!=0)
            {
                if(MobSpel3Type%2==0) //offensif
                {
                    if(MobSpel3Mag==true) //sort de nature magique ?
                    {
                        int damage = (int)((((((degatMob+Mobdegatmag_plus-Mobdegatmag_moins)+MobSpel3dmg)*Mobdegat_multp)/Mobdegatmag_divp)*1.5) - (((armureMagHero + Heroarmuremag_plus -Heroarmuremag_moins)*Heroarmuremag_mult)/Heroarmuremag_div));
                        if(damage <0)
                        {
                            damage =0;
                        }

                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(" héro subit " + finalDamage + " de " + MobSpel3Name);
                            }
                        });
                        ihpHero = ihpHero - damage;

                    }
                    else
                    {

                        int damage = (int) (((((degatMob+Mobdegat_plus-Mobdegat_moins)*Mobdegat_multp)+MobSpel3dmg)/Mobdegat_divp) -  (((armureHero+Heroarmure_plus-Heroarmure_moins)*Heroarmure_mult)/Heroarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpHero = ihpHero - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText(" héro subit " + finalDamage + " de " + MobSpel3Name);
                            }
                        });

                    }
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(MobSpel3Type%5==0) //heal
                {
                    int recup = (int)((degatMob+MobSpel3dmg)*1.3);
                    ihpMob=ihpMob+recup;
                    if(ihpMob > maxhpMob)
                    {
                        recup = recup - (ihpMob-maxhpMob);
                        ihpMob = maxhpMob;
                    }
                    final int finalRecup = recup;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("le mob réucpère " + finalRecup + " pv grâce à " + MobSpel3Name);
                        }
                    });
                    RefreshMobPv();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(MobSpel3Type%3==0) //bonus
                {
                    if(MobSpel3Bon_armure_plus !=0)
                    {
                        Mobarmure_plus = Mobarmure_plus + MobSpel3Bon_armure_plus;
                    }
                    if(MobSpel3Bon_armure_mult !=0)
                    {
                        Mobarmure_mult = Mobarmure_mult + MobSpel3Bon_armure_mult;
                    }
                    if(MobSpel3Bon_armuremag_plus !=0)
                    {
                        Mobarmuremag_plus = Mobarmuremag_plus + MobSpel3Bon_armuremag_plus;
                    }
                    if(MobSpel3Bon_armuremag_mult !=0)
                    {
                        Mobarmuremag_mult = Mobarmuremag_mult + MobSpel3Bon_armuremag_mult;
                    }
                    if(MobSpel3Bon_degat_plus !=0)
                    {
                        Mobdegat_plus = Mobdegat_plus + MobSpel3Bon_degat_plus;
                    }
                    if(MobSpel3Bon_degat_multp !=0)
                    {
                        Mobdegat_multp = Mobdegat_multp + MobSpel3Bon_degat_multp;
                    }
                    if(MobSpel3Bon_degatmag_plus !=0)
                    {
                        Mobdegatmag_plus = Mobdegatmag_plus + MobSpel3Bon_degatmag_plus;
                    }
                    if(MobSpel3Bon_degatmag_multp !=0)
                    {
                        Mobdegatmag_multp = Mobdegatmag_multp + MobSpel3Bon_degatmag_multp;
                    }
                    if(MobSpel3Bon_esquive_plus !=0)
                    {
                        Mobesquive_plus = Mobesquive_plus + MobSpel3Bon_esquive_plus;
                    }
                    if(MobSpel3Bon_esquive_mult !=0)
                    {
                        Mobesquive_mult = Mobesquive_mult + MobSpel3Bon_esquive_mult;
                    }

                }
                if(MobSpel3Type%7==0) //malus
                {
                    if(MobSpel3Mal_armure_moins!=0)
                    {
                        Heroarmure_moins = Heroarmure_moins + MobSpel3Mal_armure_moins;
                    }
                    if(MobSpel3Mal_armure_div!=0)
                    {
                        Heroarmure_div = Heroarmure_div + MobSpel3Mal_armure_div;
                    }
                    if(MobSpel3Mal_armuremag_moins!=0)
                    {
                        Heroarmuremag_moins = Heroarmuremag_moins + MobSpel3Mal_armuremag_moins;
                    }
                    if(MobSpel3Mal_armuremag_div !=0)
                    {
                        Heroarmuremag_div = Heroarmuremag_div + MobSpel3Mal_armuremag_div;
                    }
                    if(MobSpel3Mal_esquive_moins!=0)
                    {
                        Heroesquive_moins = Heroesquive_moins + MobSpel3Mal_esquive_moins;
                    }
                    if(MobSpel3Mal_esquive_div!=0)
                    {
                        Heroesquive_div = Heroesquive_div + MobSpel3Mal_esquive_div;
                    }
                    if(MobSpel3Mal_degat_moins!=0)
                    {
                        Herodegat_moins = Herodegat_moins + MobSpel3Mal_degat_moins;
                    }
                    if(MobSpel3Mal_degat_divp!=0)
                    {
                        Herodegat_divp = Herodegat_divp + MobSpel3Mal_degat_divp;
                    }
                    if(MobSpel3Mal_degatmag_moins!=0)
                    {
                        Herodegatmag_moins = Herodegatmag_moins + MobSpel3Mal_degatmag_moins;
                    }
                    if(MobSpel3Mal_degatmag_divp!=0)
                    {
                        Herodegatmag_divp = Herodegatmag_divp + MobSpel3Mal_degatmag_divp;
                    }
                }


                mobsort_duree_3= mobsort_duree_3-1;
                if(mobsort_duree_3 ==0)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText(MobSpel3Name + " se dissipe");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            /////tour du mobs///
            MobTurn=true;
            if(ihpHero<0)
            {
                ihpHero=0;
                MobTurn =false;
            }
            while (MobTurn==true)
            {
                this.message = findViewById(R.id.Text_cmb);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        message.setText("tour du mob");
                    }
                });

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /////////choix du mobs//////////
                Random rand = new Random();
                int prefMagie = rand.nextInt(100);
                prefMagie = prefMagie + MobprefMag;
                int prefDef = rand.nextInt(100);
                prefDef = prefDef + MobprefDef;
                int prefAttack = rand.nextInt(100);
                prefAttack = prefAttack + MobprefAttack;

                if(prefAttack > prefDef && prefAttack > prefMagie) //mob attaque
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            message.setText("Le mob attaque");
                        }
                    });
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(defesquive==true)
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText("Mais le joueur esquive!");
                            }
                        });
                    }
                    else
                    {
                        int damage = (int)((((degatMob+Mobdegat_plus-Mobdegat_moins)*Mobdegat_multp)/Mobdegat_divp) - (((armureHero+Heroarmure_plus-Heroarmure_moins)*Heroarmure_mult)/Heroarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpHero = ihpHero - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText("Le mob attaque et inflige " + finalDamage + " dégats ");
                            }
                        });
                    }
                    RefreshHeroPv();
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MobTurn = false;
                }

                else if(prefDef>prefAttack && prefDef > prefMagie) //mob se defend
                {
                    int prefpar = rand.nextInt(100);
                    prefpar = prefpar + MobprefPar;
                    int prefEsq = rand.nextInt(100);
                    prefEsq = prefEsq + MobprefEsq;

                    if(prefpar>prefEsq) //mob veut blocker
                    {
                        if(Mobdefend!=true)
                        {
                            Mobarmure_mult=Mobarmure_mult+1;
                            Mobarmuremag_mult = Mobarmuremag_mult+1;
                        }
                        MobTourdef = 1;
                        Mobdefend=true;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText("Le mob se défend");
                            }
                        });
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    else// mob veut esquiver
                    {
                        int tentative = rand.nextInt(100);
                        if(tentative < (((esquiveMob+Mobesquive_plus-Mobesquive_moins)*Mobesquive_mult)/Mobesquive_div))
                        {
                            Mobdefesquive =true;
                        }
                        int damage = (int)(((((degatMob+Mobdegat_plus-Mobdegat_moins)*Mobdegat_multp)/Mobdegat_divp)/2) - (((armureHero+Heroarmure_plus-Heroarmure_moins)*Heroarmure_mult)/Heroarmure_div));
                        if(damage <0)
                        {
                            damage =0;
                        }
                        ihpHero = ihpHero - damage;
                        final int finalDamage = damage;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText("Le mob se prépare à esquiver et inflige " + finalDamage + " dégats ");
                            }
                        });
                        RefreshHeroPv();
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    MobTurn = false;
                }

                else // Mob lance un sort
                {
                    int choixSort = rand.nextInt(4);

                    if(choixSort==3)    //Sort3
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText("Mob lance " + MobSpel3Name+ " !");
                            }
                        });
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(MobSpel3Type%2==0) //offensif
                        {
                            if(MobSpel3Mag==true) //sort de nature magique ?
                            {
                                int damage = (int)((((((degatMob+Mobdegatmag_plus-Mobdegatmag_moins)+MobSpel3dmg)*Mobdegat_multp)/Mobdegatmag_divp)*1.5) - (((armureMagHero + Heroarmuremag_plus -Heroarmuremag_moins)*Heroarmuremag_mult)/Heroarmuremag_div));
                                if(damage <0)
                                {
                                    damage =0;
                                }

                                final int finalDamage = damage;
                                if(defesquive==true)
                                {
                                    damage = damage/2;
                                    int damagereduce = damage;

                                    final int finaldamagereduce = damagereduce;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("hero tente une esquive mais " + MobSpel3Name+ " lui inflige " + finaldamagereduce +" dégats ");
                                        }
                                    });

                                    ihpHero = ihpHero - damagereduce;
                                }
                                else
                                {
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText(MobSpel3Name+ " vous inflige " + finalDamage +" dégats ");
                                        }
                                    });
                                    ihpHero = ihpHero - damage;
                                }
                            }
                            else
                            {
                                if(defesquive ==true)
                                {
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("mob lance " +MobSpel3Name + " mais héro esquive");
                                        }
                                    });
                                }
                                else
                                {
                                    int damage = (int) (((((degatMob+Mobdegat_plus-Mobdegat_moins)*Mobdegat_multp)+MobSpel3dmg)/Mobdegat_divp) -  (((armureHero+Heroarmure_plus-Heroarmure_moins)*Heroarmure_mult)/Heroarmure_div));
                                    if(damage <0)
                                    {
                                        damage =0;
                                    }
                                    ihpHero = ihpHero - damage;
                                    final int finalDamage = damage;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText(MobSpel3Name + " inflige à héro " + finalDamage +" dégats ");
                                        }
                                    });
                                }
                            }
                            RefreshHeroPv();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(MobSpel3Type%5==0) //heal
                        {
                            int recup = (int)((degatMob+MobSpel3dmg)*1.3);
                            ihpMob=ihpMob+recup;
                            if(ihpMob > maxhpMob)
                            {
                                recup = recup - (ihpMob-maxhpMob);
                                ihpMob = maxhpMob;
                            }
                            final int finalRecup = recup;
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    message.setText("le mob réucpère " + finalRecup + " pv");
                                }
                            });
                            RefreshMobPv();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(MobSpel3Type%3==0) //bonus
                        {
                            if(MobSpel3Bon_armure_plus !=0)
                            {
                                Mobarmure_plus = Mobarmure_plus + MobSpel3Bon_armure_plus;
                            }
                            if(MobSpel3Bon_armure_mult !=0)
                            {
                                Mobarmure_mult = Mobarmure_mult + MobSpel3Bon_armure_mult;
                            }
                            if(MobSpel3Bon_armuremag_plus !=0)
                            {
                                Mobarmuremag_plus = Mobarmuremag_plus + MobSpel3Bon_armuremag_plus;
                            }
                            if(MobSpel3Bon_armuremag_mult !=0)
                            {
                                Mobarmuremag_mult = Mobarmuremag_mult + MobSpel3Bon_armuremag_mult;
                            }
                            if(MobSpel3Bon_degat_plus !=0)
                            {
                                Mobdegat_plus = Mobdegat_plus + MobSpel3Bon_degat_plus;
                            }
                            if(MobSpel3Bon_degat_multp !=0)
                            {
                                Mobdegat_multp = Mobdegat_multp + MobSpel3Bon_degat_multp;
                            }
                            if(MobSpel3Bon_degatmag_plus !=0)
                            {
                                Mobdegatmag_plus = Mobdegatmag_plus + MobSpel3Bon_degatmag_plus;
                            }
                            if(MobSpel3Bon_degatmag_multp !=0)
                            {
                                Mobdegatmag_multp = Mobdegatmag_multp + MobSpel3Bon_degatmag_multp;
                            }
                            if(MobSpel3Bon_esquive_plus !=0)
                            {
                                Mobesquive_plus = Mobesquive_plus + MobSpel3Bon_esquive_plus;
                            }
                            if(MobSpel3Bon_esquive_mult !=0)
                            {
                                Mobesquive_mult = Mobesquive_mult + MobSpel3Bon_esquive_mult;
                            }

                        }
                        if(MobSpel3Type%7==0) //malus
                        {
                            if(MobSpel3Mal_armure_moins!=0)
                            {
                                Heroarmure_moins = Heroarmure_moins + MobSpel3Mal_armure_moins;
                            }
                            if(MobSpel3Mal_armure_div!=0)
                            {
                                Heroarmure_div = Heroarmure_div + MobSpel3Mal_armure_div;
                            }
                            if(MobSpel3Mal_armuremag_moins!=0)
                            {
                                Heroarmuremag_moins = Heroarmuremag_moins + MobSpel3Mal_armuremag_moins;
                            }
                            if(MobSpel3Mal_armuremag_div !=0)
                            {
                                Heroarmuremag_div = Heroarmuremag_div + MobSpel3Mal_armuremag_div;
                            }
                            if(MobSpel3Mal_esquive_moins!=0)
                            {
                                Heroesquive_moins = Heroesquive_moins + MobSpel3Mal_esquive_moins;
                            }
                            if(MobSpel3Mal_esquive_div!=0)
                            {
                                Heroesquive_div = Heroesquive_div + MobSpel3Mal_esquive_div;
                            }
                            if(MobSpel3Mal_degat_moins!=0)
                            {
                                Herodegat_moins = Herodegat_moins + MobSpel3Mal_degat_moins;
                            }
                            if(MobSpel3Mal_degat_divp!=0)
                            {
                                Herodegat_divp = Herodegat_divp + MobSpel3Mal_degat_divp;
                            }
                            if(MobSpel3Mal_degatmag_moins!=0)
                            {
                                Herodegatmag_moins = Herodegatmag_moins + MobSpel3Mal_degatmag_moins;
                            }
                            if(MobSpel3Mal_degatmag_divp!=0)
                            {
                                Herodegatmag_divp = Herodegatmag_divp + MobSpel3Mal_degatmag_divp;
                            }
                        }

                        mobsort_duree_3 = MobSpel3duree;
                    }
                    else if(choixSort==2) //Sort2
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText("Mob lance " + MobSpel2Name+ " !");
                            }
                        });
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(MobSpel2Type%2==0) //offensif
                        {
                            if(MobSpel2Mag==true) //sort de nature magique ?
                            {
                                int damage = (int)((((((degatMob+Mobdegatmag_plus-Mobdegatmag_moins)+MobSpel2dmg)*Mobdegat_multp)/Mobdegatmag_divp)*1.5) - (((armureMagHero + Heroarmuremag_plus -Heroarmuremag_moins)*Heroarmuremag_mult)/Heroarmuremag_div));
                                if(damage <0)
                                {
                                    damage =0;
                                }

                                final int finalDamage = damage;
                                if(defesquive==true)
                                {
                                    damage = damage/2;
                                    int damagereduce = damage;

                                    final int finaldamagereduce = damagereduce;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("hero tente une esquive mais " + MobSpel2Name+ " lui inflige " + finaldamagereduce +" dégats ");
                                        }
                                    });

                                    ihpHero = ihpHero - damagereduce;
                                }
                                else
                                {
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText(MobSpel2Name+ " vous inflige " + finalDamage +" dégats ");
                                        }
                                    });
                                    ihpHero = ihpHero - damage;
                                }
                            }
                            else
                            {
                                if(defesquive ==true)
                                {
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("mob lance " +MobSpel2Name + " mais héro esquive");
                                        }
                                    });
                                }
                                else
                                {
                                    int damage = (int) (((((degatMob+Mobdegat_plus-Mobdegat_moins)*Mobdegat_multp)+MobSpel2dmg)/Mobdegat_divp) -  (((armureHero+Heroarmure_plus-Heroarmure_moins)*Heroarmure_mult)/Heroarmure_div));
                                    if(damage <0)
                                    {
                                        damage =0;
                                    }
                                    ihpHero = ihpHero - damage;
                                    final int finalDamage = damage;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText(MobSpel2Name + " inflige à héro " + finalDamage +" dégats ");
                                        }
                                    });
                                }
                            }
                            RefreshHeroPv();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(MobSpel2Type%5==0) //heal
                        {
                            int recup = (int)((degatMob+MobSpel2dmg)*1.3);
                            ihpMob=ihpMob+recup;
                            if(ihpMob > maxhpMob)
                            {
                                recup = recup - (ihpMob-maxhpMob);
                                ihpMob = maxhpMob;
                            }
                            final int finalRecup = recup;
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    message.setText("le mob réucpère " + finalRecup + " pv");
                                }
                            });
                            RefreshMobPv();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(MobSpel2Type%3==0) //bonus
                        {
                            if(MobSpel2Bon_armure_plus !=0)
                            {
                                Mobarmure_plus = Mobarmure_plus + MobSpel2Bon_armure_plus;
                            }
                            if(MobSpel2Bon_armure_mult !=0)
                            {
                                Mobarmure_mult = Mobarmure_mult + MobSpel2Bon_armure_mult;
                            }
                            if(MobSpel2Bon_armuremag_plus !=0)
                            {
                                Mobarmuremag_plus = Mobarmuremag_plus + MobSpel2Bon_armuremag_plus;
                            }
                            if(MobSpel2Bon_armuremag_mult !=0)
                            {
                                Mobarmuremag_mult = Mobarmuremag_mult + MobSpel2Bon_armuremag_mult;
                            }
                            if(MobSpel2Bon_degat_plus !=0)
                            {
                                Mobdegat_plus = Mobdegat_plus + MobSpel2Bon_degat_plus;
                            }
                            if(MobSpel2Bon_degat_multp !=0)
                            {
                                Mobdegat_multp = Mobdegat_multp + MobSpel2Bon_degat_multp;
                            }
                            if(MobSpel2Bon_degatmag_plus !=0)
                            {
                                Mobdegatmag_plus = Mobdegatmag_plus + MobSpel2Bon_degatmag_plus;
                            }
                            if(MobSpel2Bon_degatmag_multp !=0)
                            {
                                Mobdegatmag_multp = Mobdegatmag_multp + MobSpel2Bon_degatmag_multp;
                            }
                            if(MobSpel2Bon_esquive_plus !=0)
                            {
                                Mobesquive_plus = Mobesquive_plus + MobSpel2Bon_esquive_plus;
                            }
                            if(MobSpel2Bon_esquive_mult !=0)
                            {
                                Mobesquive_mult = Mobesquive_mult + MobSpel2Bon_esquive_mult;
                            }

                        }
                        if(MobSpel2Type%7==0) //malus
                        {
                            if(MobSpel2Mal_armure_moins!=0)
                            {
                                Heroarmure_moins = Heroarmure_moins + MobSpel2Mal_armure_moins;
                            }
                            if(MobSpel2Mal_armure_div!=0)
                            {
                                Heroarmure_div = Heroarmure_div + MobSpel2Mal_armure_div;
                            }
                            if(MobSpel2Mal_armuremag_moins!=0)
                            {
                                Heroarmuremag_moins = Heroarmuremag_moins + MobSpel2Mal_armuremag_moins;
                            }
                            if(MobSpel2Mal_armuremag_div !=0)
                            {
                                Heroarmuremag_div = Heroarmuremag_div + MobSpel2Mal_armuremag_div;
                            }
                            if(MobSpel2Mal_esquive_moins!=0)
                            {
                                Heroesquive_moins = Heroesquive_moins + MobSpel2Mal_esquive_moins;
                            }
                            if(MobSpel2Mal_esquive_div!=0)
                            {
                                Heroesquive_div = Heroesquive_div + MobSpel2Mal_esquive_div;
                            }
                            if(MobSpel2Mal_degat_moins!=0)
                            {
                                Herodegat_moins = Herodegat_moins + MobSpel2Mal_degat_moins;
                            }
                            if(MobSpel2Mal_degat_divp!=0)
                            {
                                Herodegat_divp = Herodegat_divp + MobSpel2Mal_degat_divp;
                            }
                            if(MobSpel2Mal_degatmag_moins!=0)
                            {
                                Herodegatmag_moins = Herodegatmag_moins + MobSpel2Mal_degatmag_moins;
                            }
                            if(MobSpel2Mal_degatmag_divp!=0)
                            {
                                Herodegatmag_divp = Herodegatmag_divp + MobSpel2Mal_degatmag_divp;
                            }
                        }

                        mobsort_duree_2 = MobSpel2duree;
                    }
                    else if(choixSort==1) //Sort1
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText("Mob lance " + MobSpel1Name+ " !");
                            }
                        });
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(MobSpel1Type%2==0) //offensif
                        {
                            if(MobSpel1Mag==true) //sort de nature magique ?
                            {
                                int damage = (int)((((((degatMob+Mobdegatmag_plus-Mobdegatmag_moins)+MobSpel1dmg)*Mobdegat_multp)/Mobdegatmag_divp)*1.5) - (((armureMagHero + Heroarmuremag_plus -Heroarmuremag_moins)*Heroarmuremag_mult)/Heroarmuremag_div));
                                if(damage <0)
                                {
                                    damage =0;
                                }

                                final int finalDamage = damage;
                                if(defesquive==true)
                                {
                                    damage = damage/2;
                                    int damagereduce = damage;

                                    final int finaldamagereduce = damagereduce;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("hero tente une esquive mais " + MobSpel1Name+ " lui inflige " + finaldamagereduce +" dégats ");
                                        }
                                    });

                                    ihpHero = ihpHero - damagereduce;
                                }
                                else
                                {
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText(MobSpel1Name+ " vous inflige " + finalDamage +" dégats ");
                                        }
                                    });
                                    ihpHero = ihpHero - damage;
                                }
                            }
                            else
                            {
                                if(defesquive ==true)
                                {
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("mob lance " +MobSpel1Name + " mais héro esquive");
                                        }
                                    });
                                }
                                else
                                {
                                    int damage = (int) (((((degatMob+Mobdegat_plus-Mobdegat_moins)*Mobdegat_multp)+MobSpel1dmg)/Mobdegat_divp) -  (((armureHero+Heroarmure_plus-Heroarmure_moins)*Heroarmure_mult)/Heroarmure_div));
                                    if(damage <0)
                                    {
                                        damage =0;
                                    }
                                    ihpHero = ihpHero - damage;
                                    final int finalDamage = damage;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText(MobSpel1Name + " inflige à héro " + finalDamage +" dégats ");
                                        }
                                    });
                                }
                            }
                            RefreshHeroPv();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(MobSpel1Type%5==0) //heal
                        {
                            int recup = (int)((degatMob+MobSpel1dmg)*1.3);
                            ihpMob=ihpMob+recup;
                            if(ihpMob > maxhpMob)
                            {
                                recup = recup - (ihpMob-maxhpMob);
                                ihpMob = maxhpMob;
                            }
                            final int finalRecup = recup;
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    message.setText("le mob réucpère " + finalRecup + " pv");
                                }
                            });
                            RefreshMobPv();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(MobSpel1Type%3==0) //bonus
                        {
                            if(MobSpel1Bon_armure_plus !=0)
                            {
                                Mobarmure_plus = Mobarmure_plus + MobSpel1Bon_armure_plus;
                            }
                            if(MobSpel1Bon_armure_mult !=0)
                            {
                                Mobarmure_mult = Mobarmure_mult + MobSpel1Bon_armure_mult;
                            }
                            if(MobSpel1Bon_armuremag_plus !=0)
                            {
                                Mobarmuremag_plus = Mobarmuremag_plus + MobSpel1Bon_armuremag_plus;
                            }
                            if(MobSpel1Bon_armuremag_mult !=0)
                            {
                                Mobarmuremag_mult = Mobarmuremag_mult + MobSpel1Bon_armuremag_mult;
                            }
                            if(MobSpel1Bon_degat_plus !=0)
                            {
                                Mobdegat_plus = Mobdegat_plus + MobSpel1Bon_degat_plus;
                            }
                            if(MobSpel1Bon_degat_multp !=0)
                            {
                                Mobdegat_multp = Mobdegat_multp + MobSpel1Bon_degat_multp;
                            }
                            if(MobSpel1Bon_degatmag_plus !=0)
                            {
                                Mobdegatmag_plus = Mobdegatmag_plus + MobSpel1Bon_degatmag_plus;
                            }
                            if(MobSpel1Bon_degatmag_multp !=0)
                            {
                                Mobdegatmag_multp = Mobdegatmag_multp + MobSpel1Bon_degatmag_multp;
                            }
                            if(MobSpel1Bon_esquive_plus !=0)
                            {
                                Mobesquive_plus = Mobesquive_plus + MobSpel1Bon_esquive_plus;
                            }
                            if(MobSpel1Bon_esquive_mult !=0)
                            {
                                Mobesquive_mult = Mobesquive_mult + MobSpel1Bon_esquive_mult;
                            }

                        }
                        if(MobSpel1Type%7==0) //malus
                        {
                            if(MobSpel1Mal_armure_moins!=0)
                            {
                                Heroarmure_moins = Heroarmure_moins + MobSpel1Mal_armure_moins;
                            }
                            if(MobSpel1Mal_armure_div!=0)
                            {
                                Heroarmure_div = Heroarmure_div + MobSpel1Mal_armure_div;
                            }
                            if(MobSpel1Mal_armuremag_moins!=0)
                            {
                                Heroarmuremag_moins = Heroarmuremag_moins + MobSpel1Mal_armuremag_moins;
                            }
                            if(MobSpel1Mal_armuremag_div !=0)
                            {
                                Heroarmuremag_div = Heroarmuremag_div + MobSpel1Mal_armuremag_div;
                            }
                            if(MobSpel1Mal_esquive_moins!=0)
                            {
                                Heroesquive_moins = Heroesquive_moins + MobSpel1Mal_esquive_moins;
                            }
                            if(MobSpel1Mal_esquive_div!=0)
                            {
                                Heroesquive_div = Heroesquive_div + MobSpel1Mal_esquive_div;
                            }
                            if(MobSpel1Mal_degat_moins!=0)
                            {
                                Herodegat_moins = Herodegat_moins + MobSpel1Mal_degat_moins;
                            }
                            if(MobSpel1Mal_degat_divp!=0)
                            {
                                Herodegat_divp = Herodegat_divp + MobSpel1Mal_degat_divp;
                            }
                            if(MobSpel1Mal_degatmag_moins!=0)
                            {
                                Herodegatmag_moins = Herodegatmag_moins + MobSpel1Mal_degatmag_moins;
                            }
                            if(MobSpel1Mal_degatmag_divp!=0)
                            {
                                Herodegatmag_divp = Herodegatmag_divp + MobSpel1Mal_degatmag_divp;
                            }
                        }

                        mobsort_duree_1 = MobSpel1duree;
                    }
                    else //sort0
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                message.setText("Mob lance " + MobSpel0Name+ " !");
                            }
                        });
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(MobSpel0Type%2==0) //offensif
                        {
                            if(MobSpel0Mag==true) //sort de nature magique ?
                            {
                                int damage = (int)((((((degatMob+Mobdegatmag_plus-Mobdegatmag_moins)+MobSpel0dmg)*Mobdegat_multp)/Mobdegatmag_divp)*1.5) - (((armureMagHero + Heroarmuremag_plus -Heroarmuremag_moins)*Heroarmuremag_mult)/Heroarmuremag_div));
                                if(damage <0)
                                {
                                    damage =0;
                                }

                                final int finalDamage = damage;
                                if(defesquive==true)
                                {
                                    damage = damage/2;
                                    int damagereduce = damage;

                                    final int finaldamagereduce = damagereduce;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("hero tente une esquive mais " + MobSpel0Name+ " lui inflige " + finaldamagereduce +" dégats ");
                                        }
                                    });

                                    ihpHero = ihpHero - damagereduce;
                                }
                                else
                                {
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText(MobSpel0Name+ " vous inflige " + finalDamage +" dégats ");
                                        }
                                    });
                                    ihpHero = ihpHero - damage;
                                }
                            }
                            else
                            {
                                if(defesquive ==true)
                                {
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText("mob lance " +MobSpel0Name + " mais héro esquive");
                                        }
                                    });
                                }
                                else
                                {
                                    int damage = (int) (((((degatMob+Mobdegat_plus-Mobdegat_moins)*Mobdegat_multp)+MobSpel0dmg)/Mobdegat_divp) -  (((armureHero+Heroarmure_plus-Heroarmure_moins)*Heroarmure_mult)/Heroarmure_div));
                                    if(damage <0)
                                    {
                                        damage =0;
                                    }
                                    ihpHero = ihpHero - damage;
                                    final int finalDamage = damage;
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            message.setText(MobSpel0Name + " inflige à héro " + finalDamage +" dégats ");
                                        }
                                    });
                                }
                            }
                            RefreshHeroPv();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(MobSpel0Type%5==0) //heal
                        {
                            int recup = (int)((degatMob+MobSpel0dmg)*1.3);
                            ihpMob=ihpMob+recup;
                            if(ihpMob > maxhpMob)
                            {
                                recup = recup - (ihpMob-maxhpMob);
                                ihpMob = maxhpMob;
                            }
                            final int finalRecup = recup;
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    message.setText("le mob réucpère " + finalRecup + " pv");
                                }
                            });
                            RefreshMobPv();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(MobSpel0Type%3==0) //bonus
                        {
                            if(MobSpel0Bon_armure_plus !=0)
                            {
                                Mobarmure_plus = Mobarmure_plus + MobSpel0Bon_armure_plus;
                            }
                            if(MobSpel0Bon_armure_mult !=0)
                            {
                                Mobarmure_mult = Mobarmure_mult + MobSpel0Bon_armure_mult;
                            }
                            if(MobSpel0Bon_armuremag_plus !=0)
                            {
                                Mobarmuremag_plus = Mobarmuremag_plus + MobSpel0Bon_armuremag_plus;
                            }
                            if(MobSpel0Bon_armuremag_mult !=0)
                            {
                                Mobarmuremag_mult = Mobarmuremag_mult + MobSpel0Bon_armuremag_mult;
                            }
                            if(MobSpel0Bon_degat_plus !=0)
                            {
                                Mobdegat_plus = Mobdegat_plus + MobSpel0Bon_degat_plus;
                            }
                            if(MobSpel0Bon_degat_multp !=0)
                            {
                                Mobdegat_multp = Mobdegat_multp + MobSpel0Bon_degat_multp;
                            }
                            if(MobSpel0Bon_degatmag_plus !=0)
                            {
                                Mobdegatmag_plus = Mobdegatmag_plus + MobSpel0Bon_degatmag_plus;
                            }
                            if(MobSpel0Bon_degatmag_multp !=0)
                            {
                                Mobdegatmag_multp = Mobdegatmag_multp + MobSpel0Bon_degatmag_multp;
                            }
                            if(MobSpel0Bon_esquive_plus !=0)
                            {
                                Mobesquive_plus = Mobesquive_plus + MobSpel0Bon_esquive_plus;
                            }
                            if(MobSpel0Bon_esquive_mult !=0)
                            {
                                Mobesquive_mult = Mobesquive_mult + MobSpel0Bon_esquive_mult;
                            }

                        }
                        if(MobSpel0Type%7==0) //malus
                        {
                            if(MobSpel0Mal_armure_moins!=0)
                            {
                                Heroarmure_moins = Heroarmure_moins + MobSpel0Mal_armure_moins;
                            }
                            if(MobSpel0Mal_armure_div!=0)
                            {
                                Heroarmure_div = Heroarmure_div + MobSpel0Mal_armure_div;
                            }
                            if(MobSpel0Mal_armuremag_moins!=0)
                            {
                                Heroarmuremag_moins = Heroarmuremag_moins + MobSpel0Mal_armuremag_moins;
                            }
                            if(MobSpel0Mal_armuremag_div !=0)
                            {
                                Heroarmuremag_div = Heroarmuremag_div + MobSpel0Mal_armuremag_div;
                            }
                            if(MobSpel0Mal_esquive_moins!=0)
                            {
                                Heroesquive_moins = Heroesquive_moins + MobSpel0Mal_esquive_moins;
                            }
                            if(MobSpel0Mal_esquive_div!=0)
                            {
                                Heroesquive_div = Heroesquive_div + MobSpel0Mal_esquive_div;
                            }
                            if(MobSpel0Mal_degat_moins!=0)
                            {
                                Herodegat_moins = Herodegat_moins + MobSpel0Mal_degat_moins;
                            }
                            if(MobSpel0Mal_degat_divp!=0)
                            {
                                Herodegat_divp = Herodegat_divp + MobSpel0Mal_degat_divp;
                            }
                            if(MobSpel0Mal_degatmag_moins!=0)
                            {
                                Herodegatmag_moins = Herodegatmag_moins + MobSpel0Mal_degatmag_moins;
                            }
                            if(MobSpel0Mal_degatmag_divp!=0)
                            {
                                Herodegatmag_divp = Herodegatmag_divp + MobSpel0Mal_degatmag_divp;
                            }
                        }

                        mobsort_duree_0 = MobSpel0duree;

                    }
                    MobTurn=false;
                }


                MobTurn=false;
            }
            VerifVictporeMob(ihpHero);


        }
    }


    public void MobresetBonus()
    {
        if(MobTourdef>0)
        {
            MobTourdef= MobTourdef-1;
            Mobarmure_plus = 0;
            Mobarmure_mult =2;
            Mobarmuremag_plus =0;
            Mobarmuremag_mult =2;
        }
        else
        {
            Mobarmure_plus = 0;
            Mobarmure_mult =1;
            Mobarmuremag_plus =0;
            Mobarmuremag_mult =1;
            Mobdefend = false;
        }
        Mobdegat_plus=0;
        Mobdegat_multp=1;
        Mobdegatmag_plus=0;
        Mobdegatmag_multp=1;
        Mobesquive_plus =0;
        Mobesquive_mult=1;
        Mobdefesquive =false;
    }
    public void ResetHeromalus()
    {
        Heroarmure_moins=0;
        Heroarmure_div =1;
        //armure mag
        Heroarmuremag_moins =0;
        Heroarmuremag_div =1;
        //degat
        Herodegat_moins =0;
        Herodegat_divp=1;
        //degat magique
        Herodegatmag_moins=0;
        Herodegatmag_divp=1;
        //esquive
        Heroesquive_moins =0;
        Heroesquive_div=1;
    }



    public void resetBonus()
    {
        if(Tourdef>0)
        {
            Heroarmure_plus = 0;
            Heroarmure_mult =2;
            Heroarmuremag_plus =0;
            Heroarmuremag_mult =2;
        }
        else
        {
            Heroarmure_plus = 0;
            Heroarmure_mult =1;
            Heroarmuremag_plus =0;
            Heroarmuremag_mult =1;
            seDefend = false;
        }
        Herodegat_plus=0;
        Herodegat_multp=1;
        Herodegatmag_plus=0;
        Herodegatmag_multp=1;
        Heroesquive_plus =0;
        Heroesquive_mult=1;
    }

    public void restMobmalus()
    {
        Mobarmure_moins=0;
        Mobarmure_div =1;
        //armure mag
        Mobarmuremag_moins =0;
        Mobarmuremag_div =1;
        //degat
        Mobdegat_moins =0;
        Mobdegat_divp=1;
        //degat magique
        Mobdegatmag_moins=0;
        Mobdegatmag_divp=1;
        //esquive
        Mobesquive_moins =0;
        Mobesquive_div=1;
    }
    public void refreshStats()
    {
        final int totalDegat = (int)(((degatHero+ Herodegat_plus-Herodegat_moins)*Herodegat_multp)/Herodegat_divp);
        final int totalDegatmag = (int)((((degatHero + Herodegatmag_plus-Herodegatmag_moins)*Herodegatmag_multp)/Herodegatmag_divp)*1.5);
        final int totalArmure = (int)(((armureHero+Heroarmure_plus-Heroarmure_moins)*Heroarmure_mult)/Heroarmure_div);
        final int totalArmuremag = (int)(((armureMagHero+Heroarmuremag_plus-Heroarmuremag_moins)*Heroarmuremag_mult)/Heroarmuremag_div);
        final int totalEsquive = (int)(((esquiveHero+Heroesquive_plus-Heroesquive_moins)*Heroesquive_mult)/Heroesquive_div);
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Degat_valeur_actu.setText(""+totalDegat);
                Degatmag_valeur_actu.setText(""+totalDegatmag);
                Armure_valeur_actu.setText(""+totalArmure);
                Armuremag_valeur_actu.setText(""+totalArmuremag);
                Esquive_valeur_actu.setText(""+totalEsquive);
            }
        });
    }

    public void ecran_de_base()
    {
        //remettre visible btn_de_base
        attack.setVisibility(View.VISIBLE);
        defendre.setVisibility(View.VISIBLE);
        sorts.setVisibility(View.VISIBLE);
        fuite.setVisibility(View.VISIBLE);
        inventaire.setVisibility(View.VISIBLE);
        //btnretour
        this.back.setVisibility(View.GONE);
        //liste de sorts
        this.Spel0.setVisibility(View.GONE);
        this.Spel1.setVisibility(View.GONE);
        this.Spel2.setVisibility(View.GONE);
        this.Spel3.setVisibility(View.GONE);
        //liste des objets
        this.Objet0.setVisibility((View.GONE));
        this.Objet1.setVisibility(View.GONE);
        this.Objet2.setVisibility(View.GONE);
        this.Objet3.setVisibility(View.GONE);
        //bouton de defense
        this.esquive.setVisibility(View.GONE);
        this.parer.setVisibility(View.GONE);
    }


    public void EndScreen()
    {
        mainHandler.post(new Runnable() {
            private TextView message;

            @Override
            public void run() {
                HpHero.setVisibility(View.GONE);
                hpMob.setVisibility(View.GONE);
                Degat_Icon.setVisibility(View.GONE);
                Degat_valeur_actu.setVisibility(View.GONE);
                Degatmag_Icon.setVisibility(View.GONE);
                Degatmag_valeur_actu.setVisibility(View.GONE);
                Armure_Icon.setVisibility(View.GONE);
                Armure_valeur_actu.setVisibility(View.GONE);
                Armuremag_Icon.setVisibility(View.GONE);
                Armuremag_valeur_actu.setVisibility(View.GONE);
                Esquive_Icon.setVisibility(View.GONE);
                Esquive_valeur_actu.setVisibility(View.GONE);

                attack.setVisibility(View.GONE);
                sorts.setVisibility(View.GONE);
                defendre.setVisibility(View.GONE);
                fuite.setVisibility(View.GONE);
                inventaire.setVisibility(View.GONE);
                back.setVisibility(View.GONE);

                esquive.setVisibility(View.GONE);
                parer.setVisibility(View.GONE);

                Spel0.setVisibility(View.GONE);
                Spel1.setVisibility(View.GONE);
                Spel2.setVisibility(View.GONE);
                Spel3.setVisibility(View.GONE);

                Objet0.setVisibility(View.GONE);
                Objet1.setVisibility(View.GONE);
                Objet2.setVisibility(View.GONE);
                Objet3.setVisibility(View.GONE);

                SpriteHero.setVisibility(View.GONE);
                SpriteMob.setVisibility(View.GONE);
                this.message = findViewById(R.id.Text_cmb);
                this.message.setVisibility(View.GONE);
                
            }
        });

    }

    public void DefetScreen()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                FinText.setVisibility(View.VISIBLE);
                retour_carte.setVisibility(View.VISIBLE);
                FinText.setText("Defete");
            }
        });
        CloseCombat runnable = new CloseCombat();
        new Thread(runnable).start();
    }
    public void VictoryScreen()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                FinText.setVisibility(View.VISIBLE);
                retour_carte.setVisibility(View.VISIBLE);
                FinText.setText("Victoire");
            }
        });
        CloseCombat runnable = new CloseCombat();
        new Thread(runnable).start();
    }

    public void fuiteScreen()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                FinText.setVisibility(View.VISIBLE);
                retour_carte.setVisibility(View.VISIBLE);
                FinText.setText("Vous avez fuit le combat");
            }
        });
        CloseCombat runnable = new CloseCombat();
        new Thread(runnable).start();
    }

    class CloseCombat implements Runnable
    {
         @Override
        public void run() {
             final boolean[] Out = {false};
             while(Out[0] ==false)
             {
                 retour_carte.setOnClickListener(new View.OnClickListener()
                 {
                     @Override
                     public void onClick(View v) {
                        Out[0] =true;
                     }
                 });
             }

        }
    }

    public void RefreshHeroPv()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                HpHero.setProgress(ihpHero);
            }
        });
    }
    public void RefreshMobPv()
    {
        mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    hpMob.setProgress(ihpMob);
                }
            });

    }
}




