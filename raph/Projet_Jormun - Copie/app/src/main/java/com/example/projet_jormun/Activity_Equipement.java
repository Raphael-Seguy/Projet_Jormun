package com.example.projet_jormun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class Activity_Equipement extends AppCompatActivity {
    private Handler mainHandler = new Handler();
    boolean Quit;

    //Liste de sort
    //totale
    ArrayList<String> SkillsTotal = new ArrayList();
    //choisis
    ArrayList SkillsChoisis = new ArrayList();

    //liste des objets
    //totale
    ArrayList ItemsTotal = new ArrayList();
    ArrayList btnItemsTotal = new ArrayList();
    //equipement choisi
    ArrayList EquipChoisis = new ArrayList();
    //choisis objets
    ArrayList ObjetsChoisis = new ArrayList();

    private int activateSpel=3;
    private boolean activeObjet = false;
    private int activateObjet=3;

    int Action;
    //bloc du haut
    private ImageView SpriteHero;
    private TextView Name;
    private String HeroName;
    //stats
    private TextView IconArmure;
    private TextView ValeurArmure;
    private int ArmureHero;
    private TextView IconArmuremag;
    private TextView ValeurArmuremag;
    private int ArmuremagHero;
    private TextView IconDegat;
    private TextView ValeurDegat;
    private int Degathero;
    private TextView IconDegatmag;
    private TextView ValeurDegatmag;
    private int DegatmagHero;
    private TextView IconEsquive;
    private TextView ValeurEsquive;
    private int EsquiveHero;
    //classes
    private TextView Classes;
    private TextView PvText;
    private int PvHero;

    //modificateur de stats
    private int Heroarmure_plus;
    private double Heroarmure_mult;
    //armure magique
    private int Heroarmuremag_plus;
    private double Heroarmuremag_mult;
    //esquive
    private int Heroesquive_plus;
    private double Heroesquive_mult;
    //degat
    private int Herodegat_plus;
    private double Herodegat_multp;
    //degat magique
    private int Herodegatmag_plus;
    private double Herodegatmag_multp;



    //btn_retour
    private Button Retour;
    //btn menu
    private Button Skills;
    private Button Stuff;
    private Button inv;

    private ScrollView List_spels;
    private ScrollView List_objets;
    private Button Quit_liste;
    //spels choisis
    private Button Spel0;
    private String Spel0Name;
    private Button Spel1;
    private String Spel1Name;
    private Button Spel2;
    private String Spel2Name;
    private Button Spel3;
    private String Spel3Name;
    //equipement
    private Button Equip_tete;
    private Item Equip_teteItem;
    private String Equip_teteName;
    private Button Equip_torse;
    private Item Equip_torseItem;
    private String Equip_torseName;
    private Button Equip_jambe;
    private Item Equip_jambeItem;
    private String Equip_jambeName;
    private Button Equip_armeDr;
    private Item Equip_armeDrItem;
    private String Equip_armeDrName;
    private Button Equip_armeGch;
    private Item Equip_armeGchItem;
    private String Equip_armeGchName;
    private Button Equip_accDr;
    private Item Equip_accDrItem;
    private String Equip_accDrName;
    private Button Equip_accGch;
    private Item Equip_accGchItem;
    private String Equip_accGchName;
    //objets choisis
    private Button Objet0;
    private Item Objet0item;
    private String Objet0Name;
    private Button Objet1;
    private Item Objet1item;
    private String Objet1Name;
    private Button Objet2;
    private Item Objet2item;
    private String Objet2Name;
    private Button Objet3;
    private Item Objet3item;
    private String Objet3Name;

    private TextView Titre_spels;
    private TextView Titre_equipement;
    private TextView Titre_objet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        Intent intent = getIntent();
        Hero actuHero = intent.getParcelableExtra("hero");
        SkillsTotal = intent.getStringArrayListExtra("ListeSkils");
        SkillsChoisis = intent.getStringArrayListExtra("SkillsChoisis");


        //Generation du hero
        GenerationStatHero(actuHero);
        //Liste d'items
        GenerationItem();
        //Liste equipements
        GenerationEquip();
        //Liste d'objet choisis
        GenerationObjets();
        ///elements de la partie sup

        SpriteHero = findViewById(R.id.Sprite_hero);
        Name = findViewById(R.id.NameText);
        PvText = findViewById(R.id.TextPv);


        Titre_spels = findViewById(R.id.titre_liste_skils);
        Titre_equipement=findViewById(R.id.titre_liste_equip);
        Titre_objet=findViewById(R.id.titre_liste_objets);
        //Stats
        IconArmure = findViewById(R.id.armure_icon);
        ValeurArmure = findViewById(R.id.Valeur_armure);
        IconArmuremag = findViewById(R.id.armuremag_icon);
        ValeurArmuremag = findViewById(R.id.Valeur_armuremag);
        IconDegat = findViewById(R.id.degat_icon);
        ValeurDegat = findViewById(R.id.Valeur_degat);
        IconDegatmag = findViewById(R.id.degatmag_icon);
        ValeurDegatmag = findViewById(R.id.Valeur_degatmag);
        IconEsquive = findViewById(R.id.esquive_icon);
        ValeurEsquive = findViewById(R.id.Valeur_esquive);
        //classes
        Classes = findViewById(R.id.Text_classes);

        changeVue(actuHero);
        //btn_retour
        Retour = findViewById(R.id.btn_back);
        //btn_titre
        Skills = findViewById(R.id.btn_open_skill);
        Stuff = findViewById(R.id.btn_open_stuff);
        inv = findViewById(R.id.btn_open_inv);


        Titre_spels.setVisibility(View.GONE);
        List_objets = findViewById(R.id.Liste_objet);
        List_objets.setVisibility(View.GONE);
        Titre_equipement.setVisibility(View.GONE);
        Quit_liste = findViewById(R.id.btn_quit_scroll);
        Quit_liste.setVisibility(View.GONE);
        Titre_objet.setVisibility(View.GONE);
        List_spels = findViewById(R.id.Liste_spel);
        List_spels.setVisibility(View.GONE);

        //Sorts sélectionnés
        Spel0 = findViewById(R.id.btn_Spel0);
        Spel0Name = (String) SkillsChoisis.get(0);
        Spel0.setText(Spel0Name);
        Spel1 = findViewById(R.id.btn_Spel1);
        Spel1Name = (String) SkillsChoisis.get(1);
        Spel1.setText(Spel1Name);
        Spel2 = findViewById(R.id.btn_Spel2);
        Spel2Name = (String) SkillsChoisis.get(2);
        Spel2.setText(Spel2Name);
        Spel3 = findViewById(R.id.btn_Spel3);
        Spel3Name = (String) SkillsChoisis.get(3);
        Spel3.setText(Spel3Name);

        //stuff équipé
        Equip_tete = findViewById(R.id.btn_equip_tete);
        Equip_teteItem = (Item) EquipChoisis.get(0);
        Equip_teteName = Equip_teteItem.Name;
        Equip_tete.setText(Equip_teteName);

        Equip_torse = findViewById(R.id.btn_equip_torse);
        Equip_torseItem = (Item) EquipChoisis.get(1);
        Equip_torseName = Equip_torseItem.Name;
        Equip_torse.setText(Equip_torseName);

        Equip_jambe = findViewById(R.id.btn_equip_jambe);
        Equip_jambeItem = (Item) EquipChoisis.get(2);
        Equip_jambeName = Equip_jambeItem.Name;
        Equip_jambe.setText(Equip_jambeName);

        Equip_armeDr = findViewById(R.id.btn_equip_armedr);
        Equip_armeDrItem = (Item) EquipChoisis.get(3);
        Equip_armeDrName = Equip_armeDrItem.Name;
        Equip_armeDr.setText(Equip_armeDrName);

        Equip_armeGch= findViewById(R.id.btn_equip_armegch);
        Equip_armeGchItem = (Item) EquipChoisis.get(4);
        Equip_armeGchName = Equip_armeGchItem.Name;
        Equip_armeGch.setText(Equip_armeGchName);

        Equip_accDr = findViewById(R.id.btn_equip_accdr);
        Equip_accDrItem = (Item) EquipChoisis.get(5);
        Equip_accDrName = Equip_accDrItem.Name;
        Equip_accDr.setText(Equip_accDrName);

        Equip_accGch = findViewById(R.id.btn_equip_accgch);
        Equip_accGchItem = (Item) EquipChoisis.get(6);
        Equip_accGchName = Equip_accGchItem.Name;
        Equip_accGch.setText(Equip_accGchName);

        //objets équipés
        Objet0 = findViewById(R.id.btn_objet0);
        Objet0item = (Item) ObjetsChoisis.get(0);
        Objet0Name = Objet0item.Name;
        Objet0.setText(Objet0Name);
        Objet1 = findViewById(R.id.btn_objet1);
        Objet1item = (Item) ObjetsChoisis.get(1);
        Objet1Name = Objet1item.Name;
        Objet1.setText(Objet1Name);
        Objet2 = findViewById(R.id.btn_objet2);
        Objet2item = (Item) ObjetsChoisis.get(2);
        Objet2Name = Objet2item.Name;
        Objet2.setText(Objet2Name);
        Objet3 = findViewById(R.id.btn_objet3);
        Objet3item = (Item) ObjetsChoisis.get(3);
        Objet3Name = Objet3item.Name;
        Objet3.setText(Objet3Name);

        Objet0.setVisibility(View.GONE);
        Objet1.setVisibility(View.GONE);
        Objet2.setVisibility(View.GONE);
        Objet3.setVisibility(View.GONE);

        Equip_tete.setVisibility(View.GONE);
        Equip_torse.setVisibility(View.GONE);
        Equip_jambe.setVisibility(View.GONE);
        Equip_armeDr.setVisibility(View.GONE);
        Equip_armeGch.setVisibility(View.GONE);
        Equip_accDr.setVisibility(View.GONE);
        Equip_accGch.setVisibility(View.GONE);

        LookStuf();
        RefreshStat();
        StartGestion();
    }

    public void StartGestion()
    {
        Gestion runnable = new Gestion();
        GenerateSkils();
        GeneratebtnItem();
        new Thread(runnable).start();
    }

    class Gestion implements Runnable
    {
        @Override
        public void run()
        {
            Quit=false;
            while(Quit==false)
            {
                Skills.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Open_skills();
                    }
                });
                //choixSpel
                Spel0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=1;
                        activateSpel=0;
                        Open_Scroll(Action);
                    }
                });
                Spel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=1;
                        activateSpel=1;
                        Open_Scroll(Action);
                    }
                });
                Spel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=1;
                        activateSpel=2;
                        Open_Scroll(Action);
                    }
                });
                Spel3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=1;
                        activateSpel=3;
                        Open_Scroll(Action);
                    }
                });

                Stuff.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Open_stuff();
                    }
                });
                //choix equipement
                Equip_tete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        activateObjet=0;
                        Open_Scroll(Action);
                        Affichitem(1);
                    }
                });
                Equip_torse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        activateObjet=1;
                        Open_Scroll(Action);
                        Affichitem(2);
                    }
                });
                Equip_jambe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        activateObjet=2;
                        Open_Scroll(Action);
                        Affichitem(3);
                    }
                });
                Equip_armeDr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        activateObjet=3;
                        Open_Scroll(Action);
                        Affichitem(4);
                    }
                });
                Equip_armeGch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        activateObjet=4;
                        Open_Scroll(Action);
                        Affichitem(4);
                    }
                });
                Equip_accDr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        activateObjet=5;
                        Open_Scroll(Action);
                        Affichitem(5);
                    }
                });
                Equip_accGch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=2;
                        activeObjet=false;
                        activateObjet=6;
                        Open_Scroll(Action);
                        Affichitem(5);
                    }
                });
                inv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Open_inv();
                    }
                });
                //choix objet
                Objet0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=3;
                        activeObjet=true;
                        activateObjet=0;
                        Open_Scroll(Action);
                        Affichitem(10);
                    }
                });
                Objet1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Action=3;
                        activeObjet=true;
                        activateObjet=1;
                        Open_Scroll(Action);
                        Affichitem(10);
                    }
                });
                Objet2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=3;
                        activeObjet=true;
                        activateObjet=2;
                        Open_Scroll(Action);
                        Affichitem(10);
                    }
                });
                Objet3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action=3;
                        activeObjet=true;
                        activateObjet=3;
                        Open_Scroll(Action);
                        Affichitem(10);
                    }
                });

                Quit_liste.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Close_Scroll(Action);
                    }
                });

                Retour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QuitThisPlace();
                    }
                });
            }

        }
    }
    public void QuitThisPlace() {
        Intent intent = new Intent(Activity_Equipement.this,
                ActivityBase.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void GenerateSkils()
    {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        String actuSpelName="";
        for (int i=0; i<SkillsTotal.size();i++)
        {
            actuSpelName= (String) SkillsTotal.get(i);
            final Button newBtn = new Button(this);
            newBtn.setWidth(100);
            newBtn.setHeight(80);
            newBtn.setPadding(30,40,0,0);
            newBtn.setText(actuSpelName);
            newBtn.setId(i);
            newBtn.setVisibility(View.VISIBLE);
            final int finalI = i;
            newBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ExchangeSpel(activateSpel, finalI);
                    Close_Scroll(Action);
                }
            });
            linearLayout.addView(newBtn);
        }
        List_spels.addView(linearLayout);
    }
    public void GeneratebtnItem()
    {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        String actuItemName="";

        for (int i=0; i<ItemsTotal.size();i++)
        {
            final Item Actuitem = (Item) ItemsTotal.get(i);
            actuItemName= Actuitem.Name;
            final Button newBtn = new Button(this);
            newBtn.setWidth(100);
            newBtn.setHeight(80);
            newBtn.setPadding(30,40,0,0);
            newBtn.setText(actuItemName);
            newBtn.setId(i);
            newBtn.setVisibility(View.VISIBLE);
            final int finalI = i;
            newBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ExchangeItem(activateObjet, finalI,activeObjet);
                    Close_Scroll(Action);
                }
            });
            btnItemsTotal.add(newBtn);
            linearLayout.addView(newBtn);
        }
        List_objets.addView(linearLayout);
    }


    public void Open_skills()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Spel0.setVisibility(View.VISIBLE);
                Spel1.setVisibility(View.VISIBLE);
                Spel2.setVisibility(View.VISIBLE);
                Spel3.setVisibility(View.VISIBLE);

                Equip_tete.setVisibility(View.GONE);
                Equip_torse.setVisibility(View.GONE);
                Equip_jambe.setVisibility(View.GONE);
                Equip_armeDr.setVisibility(View.GONE);
                Equip_armeGch.setVisibility(View.GONE);
                Equip_accDr.setVisibility(View.GONE);
                Equip_accGch.setVisibility(View.GONE);

                Objet0.setVisibility(View.GONE);
                Objet1.setVisibility(View.GONE);
                Objet2.setVisibility(View.GONE);
                Objet3.setVisibility(View.GONE);
            }
        });

    }
    public void Open_stuff()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Spel0.setVisibility(View.GONE);
                Spel1.setVisibility(View.GONE);
                Spel2.setVisibility(View.GONE);
                Spel3.setVisibility(View.GONE);

                Equip_tete.setVisibility(View.VISIBLE);
                Equip_torse.setVisibility(View.VISIBLE);
                Equip_jambe.setVisibility(View.VISIBLE);
                Equip_armeDr.setVisibility(View.VISIBLE);
                Equip_armeGch.setVisibility(View.VISIBLE);
                Equip_accDr.setVisibility(View.VISIBLE);
                Equip_accGch.setVisibility(View.VISIBLE);

                Objet0.setVisibility(View.GONE);
                Objet1.setVisibility(View.GONE);
                Objet2.setVisibility(View.GONE);
                Objet3.setVisibility(View.GONE);
            }
        });

    }
    public void Open_inv()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Spel0.setVisibility(View.GONE);
                Spel1.setVisibility(View.GONE);
                Spel2.setVisibility(View.GONE);
                Spel3.setVisibility(View.GONE);

                Equip_tete.setVisibility(View.GONE);
                Equip_torse.setVisibility(View.GONE);
                Equip_jambe.setVisibility(View.GONE);
                Equip_armeDr.setVisibility(View.GONE);
                Equip_armeGch.setVisibility(View.GONE);
                Equip_accDr.setVisibility(View.GONE);
                Equip_accGch.setVisibility(View.GONE);

                Objet0.setVisibility(View.VISIBLE);
                Objet1.setVisibility(View.VISIBLE);
                Objet2.setVisibility(View.VISIBLE);
                Objet3.setVisibility(View.VISIBLE);
            }
        });

    }
    public void Open_Scroll(final int Action)
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {

                Quit_liste.setVisibility(View.VISIBLE);
                Retour.setVisibility(View.GONE);
                Skills.setVisibility(View.GONE);
                Stuff.setVisibility(View.GONE);
                inv.setVisibility(View.GONE);
                Name.setVisibility(View.GONE);
                PvText.setVisibility(View.GONE);
                if(Action==1)
                {
                    Spel0.setVisibility(View.GONE);
                    Spel1.setVisibility(View.GONE);
                    Spel2.setVisibility(View.GONE);
                    Spel3.setVisibility(View.GONE);
                    List_spels.setVisibility(View.VISIBLE);
                    Titre_spels.setVisibility(View.VISIBLE);
                }
                else if(Action==2)
                {
                    Equip_tete.setVisibility(View.GONE);
                    Equip_torse.setVisibility(View.GONE);
                    Equip_jambe.setVisibility(View.GONE);
                    Equip_armeDr.setVisibility(View.GONE);
                    Equip_armeGch.setVisibility(View.GONE);
                    Equip_accDr.setVisibility(View.GONE);
                    Equip_accGch.setVisibility(View.GONE);
                    List_objets.setVisibility(View.VISIBLE);
                    Titre_equipement.setVisibility(View.VISIBLE);
                }
                else
                {
                    Objet0.setVisibility(View.GONE);
                    Objet1.setVisibility(View.GONE);
                    Objet2.setVisibility(View.GONE);
                    Objet3.setVisibility(View.GONE);
                    List_objets.setVisibility(View.VISIBLE);
                    Titre_objet.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    public void Affichitem(int TypeBase)
    {
        for(int compteur=0; compteur < btnItemsTotal.size(); compteur++)
        {
            Item TestItem = (Item) ItemsTotal.get(compteur);
            Button BtnItem = (Button) btnItemsTotal.get(compteur);
            if(TestItem.type != TypeBase)
            {
                BtnItem.setVisibility(View.GONE);
            }
            else
            {
                BtnItem.setVisibility(View.VISIBLE);
            }
        }
    }
    public void Close_Scroll(final int Action)
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {

                Quit_liste.setVisibility(View.GONE);
                Retour.setVisibility(View.VISIBLE);
                Skills.setVisibility(View.VISIBLE);
                Stuff.setVisibility(View.VISIBLE);
                inv.setVisibility(View.VISIBLE);
                Name.setVisibility(View.VISIBLE);
                PvText.setVisibility(View.VISIBLE);
                if(Action==1)
                {
                    Spel0.setVisibility(View.VISIBLE);
                    Spel1.setVisibility(View.VISIBLE);
                    Spel2.setVisibility(View.VISIBLE);
                    Spel3.setVisibility(View.VISIBLE);
                    List_spels.setVisibility(View.GONE);
                    Titre_spels.setVisibility(View.GONE);
                }
                else if(Action==2)
                {
                    Equip_tete.setVisibility(View.VISIBLE);
                    Equip_torse.setVisibility(View.VISIBLE);
                    Equip_jambe.setVisibility(View.VISIBLE);
                    Equip_armeDr.setVisibility(View.VISIBLE);
                    Equip_armeGch.setVisibility(View.VISIBLE);
                    Equip_accDr.setVisibility(View.VISIBLE);
                    Equip_accGch.setVisibility(View.VISIBLE);
                    List_objets.setVisibility(View.GONE);
                    Titre_equipement.setVisibility(View.GONE);
                }
                else
                {
                    List_objets.setVisibility(View.GONE);
                    Objet0.setVisibility(View.VISIBLE);
                    Objet1.setVisibility(View.VISIBLE);
                    Objet2.setVisibility(View.VISIBLE);
                    Objet3.setVisibility(View.VISIBLE);
                    Titre_objet.setVisibility(View.GONE);
                }
            }
        });
    }

    public void ExchangeSpel(int Spelactive, int Listeplace )
    {

        final String skilChoisi= (String) SkillsTotal.get(Listeplace);
        SkillsChoisis.set(Spelactive,skilChoisi) ;
        RefreshSpelChoisis();
    }
    public void ExchangeItem(int Objetactive, int Listeplace, boolean Objet)
    {

        final Item ItemChoisi= (Item) ItemsTotal.get(Listeplace);

        if(Objet==true)
        {
            final Item OldItem = (Item) ObjetsChoisis.get(Objetactive);
            ObjetsChoisis.set(Objetactive,ItemChoisi) ;
            ItemsTotal.set(Listeplace,OldItem);
            RefreshbtnItem(Listeplace,OldItem.Name);
            RefreshObjetChoisis();
        }
        else
        {
            Item oldStuf = (Item) EquipChoisis.get(Objetactive);
            EquipChoisis.set(Objetactive,ItemChoisi);
            ItemsTotal.set(Listeplace,oldStuf);
            LosePower(oldStuf);
            GainPower(ItemChoisi);
            RefreshStat();
            RefreshbtnItem(Listeplace,oldStuf.Name);
            RefreshEquipChoisis();
        }

    }
    public void RefreshSpelChoisis()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Spel0Name= (String) SkillsChoisis.get(0);
                Spel0.setText(Spel0Name);
                Spel1Name= (String) SkillsChoisis.get(1);
                Spel1.setText(Spel1Name);
                Spel2Name= (String) SkillsChoisis.get(2);
                Spel2.setText(Spel2Name);
                Spel3Name= (String) SkillsChoisis.get(3);
                Spel3.setText(Spel3Name);
            }
        });
    }
    public void RefreshObjetChoisis()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Objet0item = (Item) ObjetsChoisis.get(0);
                Objet0Name = Objet0item.Name;
                Objet0.setText(Objet0Name);

                Objet1item = (Item) ObjetsChoisis.get(1);
                Objet1Name = Objet1item.Name;
                Objet1.setText(Objet1Name);

                Objet2item = (Item) ObjetsChoisis.get(2);
                Objet2Name = Objet2item.Name;
                Objet2.setText(Objet2Name);

                Objet3item = (Item) ObjetsChoisis.get(3);
                Objet3Name = Objet3item.Name;
                Objet3.setText(Objet3Name);
            }
        });
    }
    public void RefreshEquipChoisis()
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Equip_teteItem = (Item) EquipChoisis.get(0);
                Equip_teteName = Equip_teteItem.Name;
                Equip_tete.setText(Equip_teteName);

                Equip_torseItem = (Item) EquipChoisis.get(1);
                Equip_torseName = Equip_torseItem.Name;
                Equip_torse.setText(Equip_torseName);

                Equip_jambeItem = (Item) EquipChoisis.get(2);
                Equip_jambeName = Equip_jambeItem.Name;
                Equip_jambe.setText(Equip_jambeName);

                Equip_armeDrItem = (Item) EquipChoisis.get(3);
                Equip_armeDrName = Equip_armeDrItem.Name;
                Equip_armeDr.setText(Equip_armeDrName);

                Equip_armeGchItem = (Item) EquipChoisis.get(4);
                Equip_armeGchName = Equip_armeGchItem.Name;
                Equip_armeGch.setText(Equip_armeGchName);

                Equip_accDrItem = (Item) EquipChoisis.get(5);
                Equip_accDrName = Equip_accDrItem.Name;
                Equip_accDr.setText(Equip_accDrName);

                Equip_accGchItem = (Item) EquipChoisis.get(6);
                Equip_accGchName = Equip_accGchItem.Name;
                Equip_accGch.setText(Equip_accGchName);

            }
        });
    }
    public void RefreshbtnItem(int place,String oldName)
    {
        Button ItemPris = (Button) btnItemsTotal.get(place);

        ItemPris.setText(oldName);
    }
    public static class Item
    {
        public void LoadItem()
        {

        }
        public void SaveItem()
        {

        }
        String Name;
        //stats
        int Armureplus=0;
        double Armuremultp=0;
        int Armuremagplus=0;
        double Armuremagmultp=0;
        int Degatplus=0;
        double Degatmultp=0;
        int Degatmagplus=0;
        double Degatmagmultp=0;
        int Esquiveplus=0;
        double Esquivemultp=0;
        //description
        String Description="";
        int type;
    }
    //Generation pour simuler db
    public void GenerationItem()
    {
        Item objet = new Item();
        objet.Name ="Larme bleu";
        objet.Description =" Il s'agit d'une larme qui boost vos compétences";
        objet.type=10;
        ItemsTotal.add(objet);

        Item objet1 = new Item();
        objet1.Name ="Bombe";
        objet1.Description =" Il s'agit d'une larme qui boost vos bombe";
        objet1.type=10;
        ItemsTotal.add(objet1);

        Item objet2 = new Item();
        objet2.Name ="sable";
        objet2.Description =" Il s'agit d'une sable qui boost vos bombe";
        objet2.type=10;
        ItemsTotal.add(objet2);

        Item objet3 = new Item();
        objet3.Name ="caillou";
        objet3.Description =" Il s'agit d'une caillou qui boost vos bombe";
        objet3.type=10;
        ItemsTotal.add(objet3);

        Item objet4 = new Item();
        objet4.Name ="casque de fer";
        objet4.Description =" Il s'agit d'une casque qui boost vos bombe";
        objet4.Armureplus = 20;
        objet4.type=1;
        ItemsTotal.add(objet4);

        Item objet5 = new Item();
        objet5.Name ="Torse de fer";
        objet5.Description =" Il s'agit d'une Torse qui boost vos bombe";
        objet5.Armuremultp = 0.3;
        objet5.type=2;
        ItemsTotal.add(objet5);

        Item objet6 = new Item();
        objet6.Name ="Jambe de fer";
        objet6.Description =" Il s'agit d'une Jambe qui boost vos bombe";
        objet6.Armuremagplus = 30;
        objet6.type=3;
        ItemsTotal.add(objet6);

        Item objet7 = new Item();
        objet7.Name ="epee de fer";
        objet7.Description =" Il s'agit d'une epee qui boost vos bombe";
        objet7.Degatplus=40;
        objet7.type=4;
        ItemsTotal.add(objet7);

        Item objet8 = new Item();
        objet8.Name ="collier";
        objet8.Description =" Il s'agit d'une collier qui boost vos bombe";
        objet8.Degatmagmultp=1;
        objet8.type=5;
        ItemsTotal.add(objet8);

    }
    public void GenerationEquip()
    {
        Item objet = new Item();
        objet.Name ="tete bleu";
        objet.Description =" Il s'agit d'une tete bleu qui boost vos compétences";
        objet.Armuremagplus =100;
        objet.type=1;
        EquipChoisis.add(objet);

        Item objet1 = new Item();
        objet1.Name ="torse noir";
        objet1.Description =" Il s'agit d'une torse qui boost vos bombe";
        objet1.type=2;
        EquipChoisis.add(objet1);

        Item objet2 = new Item();
        objet2.Name ="jambe rouge ";
        objet2.Description =" Il s'agit d'une rouge qui boost vos bombe";
        objet2.type=3;
        EquipChoisis.add(objet2);

        Item objet3 = new Item();
        objet3.Name ="arme az";
        objet3.Description =" Il s'agit d'une az qui boost vos bombe";
        objet3.type=4;
        EquipChoisis.add(objet3);

        Item objet4 = new Item();
        objet4.Name ="arme qz";
        objet4.Description =" Il s'agit d'une qz qui boost vos bombe";
        objet4.type=4;
        EquipChoisis.add(objet4);

        Item objet5 = new Item();
        objet5.Name ="bracelet az";
        objet5.Description =" Il s'agit d'une bracelet qui boost vos bombe";
        objet5.type=5;
        EquipChoisis.add(objet5);

        Item objet6 = new Item();
        objet6.Name ="bracelet qz";
        objet6.Description =" Il s'agit d'une bracelet qzqui boost vos bombe";
        objet6.type=5;
        EquipChoisis.add(objet6);
    }
    public void GenerationObjets()
    {
        Item objet = new Item();
        objet.Name ="caillou bleu";
        objet.Description =" Il s'agit d'une caillou bleu qui boost vos compétences";
        objet.type=10;
        ObjetsChoisis.add(objet);

        Item objet1 = new Item();
        objet1.Name ="Bombe noir";
        objet1.Description =" Il s'agit d'une bombe qui boost vos bombe";
        objet1.type=10;
        ObjetsChoisis.add(objet1);

        Item objet2 = new Item();
        objet2.Name ="sable rouge ";
        objet2.Description =" Il s'agit d'une rouge qui boost vos bombe";
        objet2.type=10;
        ObjetsChoisis.add(objet2);

        Item objet3 = new Item();
        objet3.Name ="caillou az";
        objet3.Description =" Il s'agit d'une az qui boost vos bombe";
        objet3.type=10;
        ObjetsChoisis.add(objet3);
    }
    public void GenerationStatHero(Hero actu)
    {
        PvHero = actu.pv;
        HeroName =actu.Name;

        ArmureHero = actu.armureHero;
        ArmuremagHero=actu.armuremagHero;
        Degathero = actu.degathero;
        DegatmagHero = actu.degatmaghero;
        EsquiveHero = actu.esquivehero;

        Heroarmure_plus=actu.heroarmure_plus;
        Heroarmure_mult =actu.heroarmure_mult;

        Heroarmuremag_plus =actu.heroarmuremag_plus;
        Heroarmuremag_mult =actu.heroarmuremag_mult;

        Herodegat_plus =actu.herodegat_plus;
        Herodegat_multp=actu.herodegat_mult;

        Herodegatmag_plus=actu.herodegatmag_plus;
        Herodegatmag_multp=actu.heordegatmag_mult;

        Heroesquive_plus=actu.heroesquiveplus;
        Heroesquive_mult=actu.heroesquive_mult;
    }
    public void changeVue(Hero Actu)
    {
        System.out.println(PvHero);
        PvText.setText(Integer.toString(PvHero));
        Name.setText(HeroName);
        SpriteHero.setImageResource(Actu.Image);
        Classes.setText(""+Actu.Classe1 + "  " + Actu.Classe2 );

        ValeurArmure.setText(Integer.toString(ArmureHero));
        ValeurArmuremag.setText(Integer.toString(ArmuremagHero));
        ValeurDegat.setText(Integer.toString(Degathero));
        ValeurDegatmag.setText(Integer.toString(DegatmagHero));
        ValeurEsquive.setText(Integer.toString(EsquiveHero));

    }


    public void LosePower(Item itemLose)
    {
        if(itemLose.Armureplus!=0)
        {
            Heroarmure_plus =Heroarmure_plus-itemLose.Armureplus;
        }
        if(itemLose.Armuremultp!=0)
        {
            Heroarmure_mult = Heroarmure_mult-itemLose.Armuremultp;
        }
        if(itemLose.Armuremagplus!=0)
        {
            Heroarmuremag_plus = Heroarmuremag_plus - itemLose.Armuremagplus;
        }
        if(itemLose.Armuremagmultp!=0)
        {
            Heroarmuremag_mult =Heroarmuremag_mult-itemLose.Armuremagmultp;
        }
        if(itemLose.Degatplus!=0)
        {
            Herodegat_plus =Herodegat_plus-itemLose.Degatplus;
        }
        if(itemLose.Degatmultp!=0)
        {
            Herodegat_multp =Herodegat_multp - itemLose.Degatmultp;
        }
        if(itemLose.Degatmagplus!=0)
        {
            Herodegatmag_plus = Herodegatmag_plus-itemLose.Degatmagplus;
        }
        if(itemLose.Degatmagmultp!=0)
        {
            Herodegatmag_multp = Herodegatmag_multp -itemLose.Degatmagmultp;
        }
        if(itemLose.Esquiveplus!=0)
        {
            Heroesquive_plus = Heroesquive_plus-itemLose.Esquiveplus;
        }
        if(itemLose.Esquivemultp!=0)
        {
            Heroesquive_mult = Heroesquive_mult -itemLose.Esquivemultp;
        }
    }
    public void GainPower(Item itemAdd)
    {
        if(itemAdd.Armureplus!=0)
        {
            Heroarmure_plus =Heroarmure_plus+itemAdd.Armureplus;
        }
        if(itemAdd.Armuremultp!=0)
        {
            Heroarmure_mult = Heroarmure_mult+itemAdd.Armuremultp;
        }
        if(itemAdd.Armuremagplus!=0)
        {
            Heroarmuremag_plus = Heroarmuremag_plus + itemAdd.Armuremagplus;
        }
        if(itemAdd.Armuremagmultp!=0)
        {
            Heroarmuremag_mult =Heroarmuremag_mult+itemAdd.Armuremagmultp;
        }
        if(itemAdd.Degatplus!=0)
        {
            Herodegat_plus =Herodegat_plus+itemAdd.Degatplus;
        }
        if(itemAdd.Degatmultp!=0)
        {
            Herodegat_multp =Herodegat_multp + itemAdd.Degatmultp;
        }
        if(itemAdd.Degatmagplus!=0)
        {
            Herodegatmag_plus = Herodegatmag_plus+itemAdd.Degatmagplus;
        }
        if(itemAdd.Degatmagmultp!=0)
        {
            Herodegatmag_multp = Herodegatmag_multp +itemAdd.Degatmagmultp;
        }
        if(itemAdd.Esquiveplus!=0)
        {
            Heroesquive_plus = Heroesquive_plus+itemAdd.Esquiveplus;
        }
        if(itemAdd.Esquivemultp!=0)
        {
            Heroesquive_mult = Heroesquive_mult +itemAdd.Esquivemultp;
        }
    }

    public void LookStuf()
    {
        for(int compteur=0; compteur<EquipChoisis.size();compteur++)
        {
            Item Stuff = (Item) EquipChoisis.get(compteur);
            GainPower(Stuff);
        }
    }


    public void RefreshStat()
    {
        final int TotArmure = (int)((ArmureHero + Heroarmure_plus)*Heroarmure_mult);
        final int TotArmuremag = (int)((ArmuremagHero + Heroarmuremag_plus)*Heroarmuremag_mult);
        final int TotDegat = (int)((Degathero + Herodegat_plus)*Herodegat_multp);
        final int TotDegatmag = (int)((DegatmagHero + Herodegatmag_plus)*Herodegatmag_multp);
        final int TotEsquive = (int)((EsquiveHero + Heroesquive_plus)*Heroesquive_mult);

        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                ValeurArmure.setText("" + TotArmure);
                ValeurArmuremag.setText(""+TotArmuremag);
                ValeurDegat.setText(""+TotDegat);
                ValeurDegatmag.setText(""+TotDegatmag);
                ValeurEsquive.setText(""+TotEsquive);
            }
        });
    }


}