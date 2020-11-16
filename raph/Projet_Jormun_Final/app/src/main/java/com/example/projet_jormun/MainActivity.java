package com.example.projet_jormun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Animation fade_in;
    public Animation fade_out;

    private Handler mainHandler = new Handler();
    private Button OpenLhero;
    private Button OpenLlieux;
    private Button OpenLvillages;
    private Button OpenLinventaires;
    private Button OpenLquetes;
    private Button OpenLRess;
    private Button btn_Quit;

    private Button back;

    private TextView Nom_player;
    private int PlayerCoordx;
    private int PlayerCoordy;
    private ImageView Sprite_player;

    //////////Scroll de l'Equipe ///////////
    //Liste de Héros de l'équipe
    private TextView NomHero0;
    private Button SpriteHero0;
    private TextView ClassesHero0;
    private TextView DescrptHero0;

    private TextView NomHero1;
    private Button SpriteHero1;
    private TextView ClassesHero1;
    private TextView DescrptHero1;

    private TextView NomHero2;
    private Button SpriteHero2;
    private TextView ClassesHero2;
    private TextView DescrptHero2;

    private Button NextPageHero;
    private Button PrecPageHero;

    private boolean firstpageHero = true;
    ///////////fin scroll Equipe/////////////
    ////////// Scroll de la liste des lieux////////
    private Button Lieu0;
    private TextView Lieu0_coord;
    private TextView Lieu0_dist;

    private Button Lieu1;
    private TextView Lieu1_coord;
    private TextView Lieu1_dist;

    private Button Lieu2;
    private TextView Lieu2_coord;
    private TextView Lieu2_dist;

    private Button Lieu3;
    private TextView Lieu3_coord;
    private TextView Lieu3_dist;

    private Button Lieu4;
    private TextView Lieu4_coord;
    private TextView Lieu4_dist;

    private Button Lieu5;
    private TextView Lieu5_coord;
    private TextView Lieu5_dist;

    private Button Lieu6;
    private TextView Lieu6_coord;
    private TextView Lieu6_dist;

    private Button Lieu7;
    private TextView Lieu7_coord;
    private TextView Lieu7_dist;
    /////////////fin scroll lieux///////

    //////////////////Scroll Villages/////////////////
    private Button Vil0;
    private TextView Vil0_name;
    private TextView Vil0_coord;

    private Button Vil1;
    private TextView Vil1_name;
    private TextView Vil1_coord;

    private Button Vil2;
    private TextView Vil2_name;
    private TextView Vil2_coord;

    private Button Vil3;
    private TextView Vil3_name;
    private TextView Vil3_coord;
    //////////fin Scroll villages////////////:

    ///////////Scroll inv //////////////
    private ImageView Sprite_inv_hero;
    private TextView Text_inv_nom;
    private ScrollView Scroll_inv_actu;
    private Button btn_inv_prec;
    private Button btn_inv_suiv;

    //aff inv prec
    private Button Hero_inv_prec;
    private TextView Nom_inv_prec;
    private ImageView Sprite_inv_prec;
    private TextView Descr_inv_prec;
    private Button Quit_inv_prec;

    private Button Btn_inv_prec_hero0;
    private Button Btn_inv_prec_hero1;
    private Button Btn_inv_prec_hero2;
    private Button Btn_inv_prec_hero3;
    private Button Btn_inv_prec_hero4;
    private Button Btn_inv_prec_hero5;

    //fin aff prec

    ////////fin Scroll inv///////////:
    ///////////////////scroll Quete////
    private TextView Text_quete_titre;
    private ScrollView Scroll_quete;

    ///aff rec
    private TextView Titre_rec;

    private ImageView Sprite_rec;
    private TextView Nom_rec;
    private ScrollView Stats_rec;
    private TextView Descr_rec;
    private Button Back_rec;
    //fin aff rec

    ///////ress scroll//////
    private ScrollView liste_ress;
    private TextView Titre_ress;
    ////////fin scroll ress//

    ArrayList ListHeros = new ArrayList();
    ArrayList ListLieux = new ArrayList();
    ArrayList ListVil = new ArrayList();
    ArrayList ListQuet = new ArrayList();
    ArrayList ListRess= new ArrayList();

    private boolean Quit =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        PlayerCoordx =50;
        PlayerCoordy =400;

        OpenLhero = findViewById(R.id.Open_liste_hero);
        OpenLlieux = findViewById(R.id.Open_liste_lieux);
        OpenLvillages = findViewById(R.id.Open_liste_vil);
        OpenLinventaires = findViewById(R.id.Open_liste_inv);
        OpenLquetes = findViewById(R.id.Open_liste_quetes);
        OpenLRess = findViewById(R.id.open_Ress);
        btn_Quit = findViewById(R.id.btn_Quit);
        back = findViewById(R.id.btn_back);

        Nom_player = findViewById(R.id.Text_nom);

        Sprite_player = findViewById(R.id.Sprite_player);
        ListHeros = GetListeHero(); ////Generation fausse Liste Hero
        ListLieux = FausseListeLieux(); ////Generation fausse liste Lieux
        ListVil = FausseListeVill();//Generation fausse liste villages
        ListQuet = QueteListe(); //Generation fausse liste Quete
        ListRess = RessListe();


        LiaisonElementCode_Equip();
        LiaisonElementCode_Lieux();
        LiaisonElementCode_vil();
        LiaisonElementCode_Inv();
        LiaisonElementCode_quete();
        liste_ress=findViewById(R.id.Scroll_liste_ress);
        Titre_ress=findViewById(R.id.titre_ress);



        GenerationHero(); //set des textView etc avec valeur de l'equipe
        GenerationLieux(); //set des ..... avec valeur lieux
        GenerationVil(); //set des textView avec valeur vill
        GenerationQuete(); //set des textView avec valeur
        GenerationRess();

        SetNormal();

        LaunchBase();
    }

    private void LaunchBase()
    {
        Base runnable = new Base();
        new Thread(runnable).start();
    }
    class Base implements Runnable
    {
        int invHero =0;
        @Override
        public void run() {
            while(Quit==false)
            {
                ///////////////////Menu Equipe/////
                OpenLhero.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HideNoraml();
                        AffichScrollHeroP1();
                    }
                });
                NextPageHero.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AffichScrollHeroP2();
                    }
                });
                PrecPageHero.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AffichScrollHeroP1();
                    }
                });
                ////////////////////Fin Equipe
                //////////////// Menu Lieux////////////////:
                OpenLlieux.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HideNoraml();
                        AffichLieux();
                    }
                });
                ///////////fin Menu lieu
                //////////////////Menu Villages
                OpenLvillages.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HideNoraml();
                        AffichVil();
                    }
                });
                ////////////fin Menu Villages
                ////////////////Menu inventaire///////////////////
                OpenLinventaires.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        invHero =0;
                        HideNoraml();
                        GenerationInv(invHero);
                        Set_prec_nom_hero();
                    }
                });
                btn_inv_suiv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        invHero =invHero+1;
                        HideInvPrec();
                        GenerationInv(invHero);
                    }
                });
                btn_inv_prec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        invHero =invHero-1;
                        HideInvPrec();
                        GenerationInv(invHero);
                    }
                });
                Quit_inv_prec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HideInvPrec();
                        GenerationInv(invHero);
                    }
                });
                /////////////fin menu inventaire
                OpenLquetes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HideNoraml();
                        AfficQuete();
                    }
                });
                Back_rec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HideRec();
                        Text_quete_titre.setVisibility(View.VISIBLE);
                        Scroll_quete.setVisibility(View.VISIBLE);

                    }
                });
                ///////////REssources
                OpenLRess.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HideNoraml();
                        AffichRess();
                    }
                });
                btn_Quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Quit=true;
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetNormal();
                    }
                });
            }

        }
    }


    //////////Liste Heros////////////
    public ArrayList GetListeHero()
    {
        ArrayList elementList = FausseEquip();
        return elementList;
    }
    public void LiaisonElementCode_Equip()
    {
        NomHero0 = findViewById(R.id.TextNom0);
        SpriteHero0 = findViewById(R.id.btn_hero0);
        ClassesHero0 = findViewById(R.id.TextClasse0);
        DescrptHero0 = findViewById(R.id.TextDescr0);

        NomHero1 = findViewById(R.id.TextNom1);
        SpriteHero1 = findViewById(R.id.btn_hero1);
        ClassesHero1 = findViewById(R.id.TextClasse1);
        DescrptHero1 = findViewById(R.id.TextDescr1);

        NomHero2 = findViewById(R.id.TextNom2);
        SpriteHero2 = findViewById(R.id.btn_hero2);
        ClassesHero2 = findViewById(R.id.TextClasse2);
        DescrptHero2 = findViewById(R.id.TextDescr2);

        PrecPageHero=findViewById(R.id.btn_precHero);
        NextPageHero=findViewById(R.id.btn_nextHero);
    }
    public void GenerationHero() //Recupération info des trois premiers héros
    {
        Hero hero = (Hero) ListHeros.get(0);
        NomHero0.setText(hero.Name);
        SpriteHero0.setText(hero.Image);
        ClassesHero0.setText(hero.Classe1 + " / " + hero.Classe2);
        DescrptHero0.setText(hero.description);
        if(hero.Travel==true)
        {
            SpriteHero0.setBackgroundColor(Color.rgb(200,0,0));
        }

        SpriteHero0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityEquipement();
            }
        });
        if(ListHeros.size()>=2)
        {
            hero = (Hero) ListHeros.get(1);
            NomHero1.setText(hero.Name);
            SpriteHero1.setText(hero.Image);
            ClassesHero1.setText(hero.Classe1 + " / " + hero.Classe2);
            DescrptHero1.setText(hero.description);
            if(hero.Travel==true)
            {
                SpriteHero1.setBackgroundColor(Color.rgb(200,0,0));
            }
            SpriteHero1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivityEquipement();
                }
            });
            if(ListHeros.size()>=3)
            {
                hero = (Hero) ListHeros.get(2);
                NomHero2.setText(hero.Name);
                SpriteHero2.setText(hero.Image);
                ClassesHero2.setText(hero.Classe1 + " / " + hero.Classe2);
                DescrptHero2.setText(hero.description);
                if(hero.Travel==true)
                {
                    SpriteHero2.setBackgroundColor(Color.rgb(200,0,0));
                }
                SpriteHero2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivityEquipement();
                    }
                });

            }

        }

    }
    public void GenerationHeroP2() //Recupération info des trois derniers héros
    {
        Hero hero = (Hero) ListHeros.get(3);
        NomHero0.setText(hero.Name);
        SpriteHero0.setText(hero.Image);
        ClassesHero0.setText(hero.Classe1 + " / " + hero.Classe2);
        DescrptHero0.setText(hero.description);
        if(hero.Travel==true)
        {
            SpriteHero0.setBackgroundColor(Color.rgb(200,0,0));
        }

        SpriteHero0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityEquipement();
            }
        });
        if(ListHeros.size()>=5)
        {
            hero = (Hero) ListHeros.get(4);
            NomHero1.setText(hero.Name);
            SpriteHero1.setText(hero.Image);
            ClassesHero1.setText(hero.Classe1 + " / " + hero.Classe2);
            DescrptHero1.setText(hero.description);
            if(hero.Travel==true)
            {
                SpriteHero1.setBackgroundColor(Color.rgb(200,0,0));
            }

            SpriteHero1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivityEquipement();
                }
            });
            if(ListHeros.size()>=6)
            {
                hero = (Hero) ListHeros.get(5);
                NomHero2.setText(hero.Name);
                SpriteHero2.setText(hero.Image);
                ClassesHero2.setText(hero.Classe1 + " / " + hero.Classe2);
                DescrptHero2.setText(hero.description);
                if(hero.Travel==true)
                {
                    SpriteHero2.setBackgroundColor(Color.rgb(200,0,0));
                }

                SpriteHero2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivityEquipement();
                    }
                });

            }

        }

    }

    public void AffichScrollHeroP1()    //affichage 3premiers Heros
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                AffichP1();
            }
        });
    }
    public void AffichScrollHeroP2()       //affichage des 3derniers Heros
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                AffichP2();
            }
        });
    }

    public void AffichEquip() //afficahge des infos Equipe
    {
        ///Liste Hero
        NomHero0.setVisibility(View.VISIBLE);
        SpriteHero0.setVisibility(View.VISIBLE);
        ClassesHero0.setVisibility(View.VISIBLE);
        DescrptHero0.setVisibility(View.VISIBLE);

        NomHero1.setVisibility(View.VISIBLE);
        SpriteHero1.setVisibility(View.VISIBLE);
        ClassesHero1.setVisibility(View.VISIBLE);
        DescrptHero1.setVisibility(View.VISIBLE);

        NomHero2.setVisibility(View.VISIBLE);
        SpriteHero2.setVisibility(View.VISIBLE);
        ClassesHero2.setVisibility(View.VISIBLE);
        DescrptHero2.setVisibility(View.VISIBLE);

    }
    public void AffichP1() //afficahge 3premiers héros
    {
        GenerationHero();
        AffichEquip();
        if(ListHeros.size()>3)
        {
            NextPageHero.setVisibility(View.VISIBLE);
            PrecPageHero.setVisibility(View.GONE);
        }
    }
    public void AffichP2() //affichage 3derniers héros
    {
        GenerationHeroP2();
        AffichEquip();
        NextPageHero.setVisibility(View.GONE);
        PrecPageHero.setVisibility(View.VISIBLE);
    }
    //////////////////////////////

    ////////////:Liste Lieux///////
    public void LiaisonElementCode_Lieux() //liaison element apli et code
    {
        Lieu0 = findViewById(R.id.btn_lieu0);
        Lieu0_coord = findViewById(R.id.text_lieu0_coord);
        Lieu0_dist = findViewById(R.id.text_lieu0_dist);

        Lieu1 = findViewById(R.id.btn_lieu1);
        Lieu1_coord = findViewById(R.id.text_lieu1_coord);
        Lieu1_dist = findViewById(R.id.text_lieu1_dist);

        Lieu2 = findViewById(R.id.btn_lieu2);
        Lieu2_coord = findViewById(R.id.text_lieu2_coord);
        Lieu2_dist = findViewById(R.id.text_lieu2_dist);

        Lieu3 = findViewById(R.id.btn_lieu3);
        Lieu3_coord = findViewById(R.id.text_lieu3_coord);
        Lieu3_dist = findViewById(R.id.text_lieu3_dist);

        Lieu4 = findViewById(R.id.btn_lieu4);
        Lieu4_coord = findViewById(R.id.text_lieu4_coord);
        Lieu4_dist = findViewById(R.id.text_lieu4_dist);

        Lieu5 = findViewById(R.id.btn_lieu5);
        Lieu5_coord = findViewById(R.id.text_lieu5_coord);
        Lieu5_dist = findViewById(R.id.text_lieu5_dist);

        Lieu6 = findViewById(R.id.btn_lieu6);
        Lieu6_coord = findViewById(R.id.text_lieu6_coord);
        Lieu6_dist = findViewById(R.id.text_lieu6_dist);

        Lieu7 = findViewById(R.id.btn_lieu7);
        Lieu7_coord = findViewById(R.id.text_lieu7_coord);
        Lieu7_dist = findViewById(R.id.text_lieu7_dist);
    }
    public void GenerationLieux() //  connection appli element apli
    {
        Lieu lieu = new Lieu();
        int distanceJoueurLieu=0;
        if(ListLieux.size()>=1)
        {
            lieu = (Lieu) ListLieux.get(0);
            Lieu0.setText(lieu.name);
            Lieu0_coord.setText(lieu.coordx + "x / " + lieu.coordy +" y");
            distanceJoueurLieu =(int) Math.sqrt( (Math.pow((lieu.coordx-PlayerCoordx),2) + Math.pow((lieu.coordy-PlayerCoordy),2)) );
            if(distanceJoueurLieu<0)
            {
                distanceJoueurLieu = distanceJoueurLieu + (distanceJoueurLieu*2);
            }
            Lieu0_dist.setText(""+distanceJoueurLieu);
            ChangeBackgroundBtn(Lieu0,lieu.type);

            if(ListLieux.size()>=2)
            {
                lieu = (Lieu) ListLieux.get(1);
                Lieu1.setText(lieu.name);
                Lieu1_coord.setText(lieu.coordx + "x / " + lieu.coordy +" y");
                distanceJoueurLieu =(int) Math.sqrt( (Math.pow((lieu.coordx-PlayerCoordx),2) + Math.pow((lieu.coordy-PlayerCoordy),2)) );
                if(distanceJoueurLieu<0)
                {
                    distanceJoueurLieu = distanceJoueurLieu + (distanceJoueurLieu*2);
                }
                Lieu1_dist.setText(""+distanceJoueurLieu);
                ChangeBackgroundBtn(Lieu1,lieu.type);

                if(ListLieux.size()>=3)
                {
                    lieu = (Lieu) ListLieux.get(2);
                    Lieu2.setText(lieu.name);
                    Lieu2_coord.setText(lieu.coordx + "x / " + lieu.coordy +" y");
                    distanceJoueurLieu =(int) Math.sqrt( (Math.pow((lieu.coordx-PlayerCoordx),2) + Math.pow((lieu.coordy-PlayerCoordy),2)) );
                    if(distanceJoueurLieu<0)
                    {
                        distanceJoueurLieu = distanceJoueurLieu + (distanceJoueurLieu*2);
                    }
                    Lieu2_dist.setText(""+distanceJoueurLieu);
                    ChangeBackgroundBtn(Lieu2,lieu.type);

                    if(ListLieux.size()>=4)
                    {
                        lieu = (Lieu) ListLieux.get(3);
                        Lieu3.setText(lieu.name);
                        Lieu3_coord.setText(lieu.coordx + "x / " + lieu.coordy +" y");
                        distanceJoueurLieu =(int) Math.sqrt( (Math.pow((lieu.coordx-PlayerCoordx),2) + Math.pow((lieu.coordy-PlayerCoordy),2)) );
                        if(distanceJoueurLieu<0)
                        {
                            distanceJoueurLieu = distanceJoueurLieu + (distanceJoueurLieu*2);
                        }
                        Lieu3_dist.setText(""+distanceJoueurLieu);
                        ChangeBackgroundBtn(Lieu3,lieu.type);

                        if(ListLieux.size()>=5)
                        {
                            lieu = (Lieu) ListLieux.get(4);
                            Lieu4.setText(lieu.name);
                            Lieu4_coord.setText(lieu.coordx + "x / " + lieu.coordy +" y");
                            distanceJoueurLieu =(int) Math.sqrt( (Math.pow((lieu.coordx-PlayerCoordx),2) + Math.pow((lieu.coordy-PlayerCoordy),2)) );
                            if(distanceJoueurLieu<0)
                            {
                                distanceJoueurLieu = distanceJoueurLieu + (distanceJoueurLieu*2);
                            }
                            Lieu4_dist.setText(""+distanceJoueurLieu);
                            ChangeBackgroundBtn(Lieu4,lieu.type);

                            if(ListLieux.size()>=6)
                            {
                                lieu = (Lieu) ListLieux.get(5);
                                Lieu5.setText(lieu.name);
                                Lieu5_coord.setText(lieu.coordx + "x / " + lieu.coordy +" y");
                                distanceJoueurLieu =(int) Math.sqrt( (Math.pow((lieu.coordx-PlayerCoordx),2) + Math.pow((lieu.coordy-PlayerCoordy),2)) );
                                if(distanceJoueurLieu<0)
                                {
                                    distanceJoueurLieu = distanceJoueurLieu + (distanceJoueurLieu*2);
                                }
                                Lieu5_dist.setText(""+distanceJoueurLieu);
                                ChangeBackgroundBtn(Lieu5,lieu.type);

                                if(ListLieux.size()>=7)
                                {
                                    lieu = (Lieu) ListLieux.get(6);
                                    Lieu6.setText(lieu.name);
                                    Lieu6_coord.setText(lieu.coordx + "x / " + lieu.coordy +" y");
                                    distanceJoueurLieu =(int) Math.sqrt( (Math.pow((lieu.coordx-PlayerCoordx),2) + Math.pow((lieu.coordy-PlayerCoordy),2)) );
                                    if(distanceJoueurLieu<0)
                                    {
                                        distanceJoueurLieu = distanceJoueurLieu + (distanceJoueurLieu*2);
                                    }
                                    Lieu6_dist.setText(""+distanceJoueurLieu);
                                    ChangeBackgroundBtn(Lieu6,lieu.type);

                                    if(ListLieux.size()>=8)
                                    {
                                        lieu = (Lieu) ListLieux.get(7);
                                        Lieu7.setText(lieu.name);
                                        Lieu7_coord.setText(lieu.coordx + "x / " + lieu.coordy +" y");
                                        distanceJoueurLieu =(int) Math.sqrt( (Math.pow((lieu.coordx-PlayerCoordx),2) + Math.pow((lieu.coordy-PlayerCoordy),2)) );
                                        if(distanceJoueurLieu<0)
                                        {
                                            distanceJoueurLieu = distanceJoueurLieu + (distanceJoueurLieu*2);
                                        }
                                        Lieu7_dist.setText(""+distanceJoueurLieu);
                                        ChangeBackgroundBtn(Lieu7,lieu.type);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void ChangeBackgroundBtn(Button actu,int type) //changment background en fonction du type de structure
    {
        if(type==1)
        {
            actu.setBackgroundResource(R.drawable.bois_repeat);
        }
        else if(type==2)
        {
            actu.setBackgroundResource(R.drawable.foret_repeat);
            actu.setTextColor(Color.rgb(250,250,250));
        }
        else if(type==3)
        {
            actu.setBackgroundResource(R.drawable.temple_repeat);
        }
        else if(type==4)
        {
            actu.setBackgroundResource(R.drawable.vampire_repeat);
            actu.setTextColor(Color.rgb(250,250,250));
        }
        else
        {
            actu.setBackgroundResource(R.drawable.faille_repeat);
            actu.setTextColor(Color.rgb(250,250,250));
        }
    }
    public void AffichLieux() // affichage de liste lieux
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                Lieu0.setVisibility(View.VISIBLE);
                Lieu0_coord.setVisibility(View.VISIBLE);
                Lieu0_dist.setVisibility(View.VISIBLE);

                Lieu1.setVisibility(View.VISIBLE);
                Lieu1_coord.setVisibility(View.VISIBLE);
                Lieu1_dist.setVisibility(View.VISIBLE);

                Lieu2.setVisibility(View.VISIBLE);
                Lieu2_coord.setVisibility(View.VISIBLE);
                Lieu2_dist.setVisibility(View.VISIBLE);

                Lieu3.setVisibility(View.VISIBLE);
                Lieu3_coord.setVisibility(View.VISIBLE);
                Lieu3_dist.setVisibility(View.VISIBLE);

                Lieu4.setVisibility(View.VISIBLE);
                Lieu4_coord.setVisibility(View.VISIBLE);
                Lieu4_dist.setVisibility(View.VISIBLE);

                Lieu5.setVisibility(View.VISIBLE);
                Lieu5_coord.setVisibility(View.VISIBLE);
                Lieu5_dist.setVisibility(View.VISIBLE);

                Lieu6.setVisibility(View.VISIBLE);
                Lieu6_coord.setVisibility(View.VISIBLE);
                Lieu6_dist.setVisibility(View.VISIBLE);

                Lieu7.setVisibility(View.VISIBLE);
                Lieu7_coord.setVisibility(View.VISIBLE);
                Lieu7_dist.setVisibility(View.VISIBLE);
            }
        });
    }

    ///////////////////////// Liste Villages
    public void LiaisonElementCode_vil()
    {
        Vil0 = findViewById(R.id.btn_vil0_sprite);
        Vil0_coord = findViewById(R.id.text_vil0_coord);
        Vil0_name = findViewById(R.id.text_vil0_nom);

        Vil1 = findViewById(R.id.btn_vil1_sprite);
        Vil1_coord = findViewById(R.id.text_vil1_coord);
        Vil1_name = findViewById(R.id.text_vil1_nom);

        Vil2 = findViewById(R.id.btn_vil2_sprite);
        Vil2_coord = findViewById(R.id.text_vil2_coord);
        Vil2_name = findViewById(R.id.text_vil2_nom);

        Vil3 = findViewById(R.id.btn_vil3_sprite);
        Vil3_coord = findViewById(R.id.text_vil3_coord);
        Vil3_name = findViewById(R.id.text_vil3_nom);
    }
    public void GenerationVil()
    {
        Village village = new Village();
        int distanceJoueurVill =0;
        if(ListVil.size()>=1)
        {
            village = (Village) ListVil.get(0);
            Vil0_name.setText(village.name);
            distanceJoueurVill = (int) Math.sqrt( (Math.pow((village.coordx-PlayerCoordx),2) + Math.pow((village.coordy-PlayerCoordy),2)) );
            Vil0_coord.setText("(" + village.coordx + " / " + village.coordy + ") Distance: " + distanceJoueurVill);
            setSpriteVill(village.lvl,Vil0);

            if(ListVil.size()>=2)
            {
                village = (Village) ListVil.get(1);
                Vil1_name.setText(village.name);
                distanceJoueurVill = (int) Math.sqrt( (Math.pow((village.coordx-PlayerCoordx),2) + Math.pow((village.coordy-PlayerCoordy),2)) );
                Vil1_coord.setText("(" + village.coordx + " / " + village.coordy + ") Distance: " + distanceJoueurVill);
                setSpriteVill(village.lvl,Vil1);

                if(ListVil.size()>=3)
                {
                    village = (Village) ListVil.get(2);
                    Vil2_name.setText(village.name);
                    distanceJoueurVill = (int) Math.sqrt( (Math.pow((village.coordx-PlayerCoordx),2) + Math.pow((village.coordy-PlayerCoordy),2)) );
                    Vil2_coord.setText("(" + village.coordx + " / " + village.coordy + ") Distance: " + distanceJoueurVill);
                    setSpriteVill(village.lvl,Vil2);

                    if(ListVil.size()>=4)
                    {
                        village = (Village) ListVil.get(3);
                        Vil3_name.setText(village.name);
                        distanceJoueurVill = (int) Math.sqrt( (Math.pow((village.coordx-PlayerCoordx),2) + Math.pow((village.coordy-PlayerCoordy),2)) );
                        Vil3_coord.setText("(" + village.coordx + " / " + village.coordy + ") Distance: " + distanceJoueurVill);
                        setSpriteVill(village.lvl,Vil3);
                    }
                }
            }
        }

    }
    public void setSpriteVill(int Lvl, Button village)
    {
        village.setText("");
        if(Lvl <= 4)
        {
            village.setBackgroundResource(R.drawable.hotel_village);
        }
        else if(Lvl <=8)
        {
            village.setBackgroundResource(R.drawable.hotel_ville);
        }
        else
        {
            village.setBackgroundResource(R.drawable.hotel_cite);
        }
        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityVillage();
            }
        });
    }
    private void AffichVil()
    {
        Vil0.setVisibility(View.VISIBLE);
        Vil0_coord.setVisibility(View.VISIBLE);
        Vil0_name.setVisibility(View.VISIBLE);

        Vil1.setVisibility(View.VISIBLE);
        Vil1_coord.setVisibility(View.VISIBLE);
        Vil1_name.setVisibility(View.VISIBLE);

        Vil2.setVisibility(View.VISIBLE);
        Vil2_coord.setVisibility(View.VISIBLE);
        Vil2_name.setVisibility(View.VISIBLE);

        Vil3.setVisibility(View.VISIBLE);
        Vil3_coord.setVisibility(View.VISIBLE);
        Vil3_name.setVisibility(View.VISIBLE);
    }
    ///////////////////Liste Inv
    public void LiaisonElementCode_Inv()
    {
        Sprite_inv_hero = findViewById(R.id.Sprite_inv_heroactu);
        Text_inv_nom = findViewById(R.id.text_inv_nomheroactu);
        Scroll_inv_actu = findViewById(R.id.Scroll_inv_actu);
        btn_inv_prec = findViewById(R.id.btn_inv_prec);
        btn_inv_suiv = findViewById(R.id.btn_inv_suiv);

        Hero_inv_prec = findViewById(R.id.Spritehero_inv_prec);
        Nom_inv_prec = findViewById(R.id.text_invprec_nom);
        Sprite_inv_prec = findViewById(R.id.Sprite_inv_prec);
        Descr_inv_prec = findViewById(R.id.text_invprec_desc);
        Quit_inv_prec = findViewById(R.id.btn_invprec_quit);

        Btn_inv_prec_hero0=findViewById(R.id.btn_inv_prec_hero0);
        Btn_inv_prec_hero1=findViewById(R.id.btn_inv_prec_hero1);
        Btn_inv_prec_hero2=findViewById(R.id.btn_inv_prec_hero2);
        Btn_inv_prec_hero3=findViewById(R.id.btn_inv_prec_hero3);
        Btn_inv_prec_hero4=findViewById(R.id.btn_inv_prec_hero4);
        Btn_inv_prec_hero5=findViewById(R.id.btn_inv_prec_hero5);
    }
    public void GenerationInv(int Hero)
    {
        Hero heroactu = (MainActivity.Hero) ListHeros.get(Hero);
        //Sprite_inv_hero.setImage(Hero IMAGE)
        Text_inv_nom.setText(heroactu.Name);
        GetInv(heroactu.Inv,Hero);
        Clean_btn_inv_Her();
        AffichInv(Hero);
    }
    public void GetInv(ArrayList inventaire, final int hero)
    {
        Scroll_inv_actu.removeAllViews();
        Item Current_item = new Item();
        LinearLayout newLayout = new LinearLayout(this);
        newLayout.setOrientation(LinearLayout.VERTICAL);
        for (int Compteur=0; Compteur<inventaire.size();Compteur++)
        {
            LinearLayout IconLayout = new LinearLayout(this);
            IconLayout.setOrientation(LinearLayout.HORIZONTAL);

            Current_item = (Item) inventaire.get(Compteur);

            TextView newitembtn = new TextView(this);
            newitembtn.setText(Current_item.nom);
            newitembtn.setVisibility(View.VISIBLE);
            newitembtn.setHeight(100);
            newitembtn.setWidth(600);
            newitembtn.setGravity(Gravity.CENTER);
            newitembtn.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
            newitembtn.setPadding(0,20,0,0);

            TextView Desc = new TextView(this);
            Desc.setText(Current_item.description);
            Desc.setVisibility(View.VISIBLE);
            Desc.setHeight(350);
            Desc.setWidth(530);
            Desc.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);

            Button test = new Button(this);
            test.setVisibility(View.VISIBLE);
            test.setText(Current_item.Image);                                      ////////image de l'item
            test.setHeight(300);
            test.setWidth(400);
            final Item finalCurrent_item = Current_item;
            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideInvSection();
                    affichItemFiche(finalCurrent_item,hero);
                }
            });

            newLayout.addView(newitembtn);

            IconLayout.addView(Desc);
            IconLayout.addView(test);

            IconLayout.setPadding(50,0,0,0);

            newLayout.addView(IconLayout);
        }
        Scroll_inv_actu.addView(newLayout);
        Scroll_inv_actu.setBackgroundResource(R.drawable.radius_scroll_inv);
    }
    public void AffichInv(int place)
    {
        Sprite_inv_hero.setVisibility(View.VISIBLE);
        Text_inv_nom.setVisibility(View.VISIBLE);
        Scroll_inv_actu.setVisibility(View.VISIBLE);
        if(place>0)
        {
            if(place==5)
            {
                btn_inv_suiv.setVisibility(View.GONE);
            }
            btn_inv_prec.setVisibility(View.VISIBLE);
        }
        if(place<5)
        {
            if(place==0)
            {
                btn_inv_prec.setVisibility(View.GONE);
            }
            btn_inv_suiv.setVisibility(View.VISIBLE);
        }
    }
    public void affichItemFiche(final Item objet, final int hero)
    {
        final Hero actuhero = (Hero) ListHeros.get(hero);

        Hero_inv_prec.setText(actuhero.Image);           //afficher sprite hero
        Nom_inv_prec.setText(objet.nom );
        ///Sprite objet
        Descr_inv_prec.setText(objet.description);


        Hero_inv_prec.setVisibility(View.VISIBLE);
        Nom_inv_prec.setVisibility(View.VISIBLE);
        Sprite_inv_prec.setVisibility(View.VISIBLE);
        Descr_inv_prec.setVisibility(View.VISIBLE);
        Quit_inv_prec.setVisibility(View.VISIBLE);

        VerifHeroActif(hero);
        Btn_inv_prec_hero0.setVisibility(View.VISIBLE);
        Btn_inv_prec_hero0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwapItemInv(objet,actuhero,((Hero) ListHeros.get(0)),0);
            }
        });
        if(ListHeros.size()>=2)
        {
            Btn_inv_prec_hero1.setVisibility(View.VISIBLE);
            Btn_inv_prec_hero1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SwapItemInv(objet,actuhero,((Hero) ListHeros.get(1)),1);
                }
            });

            if(ListHeros.size()>=3)
            {
                Btn_inv_prec_hero2.setVisibility(View.VISIBLE);
                Btn_inv_prec_hero2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SwapItemInv(objet,actuhero,((Hero) ListHeros.get(2)),2);
                    }
                });

                if(ListHeros.size()>=4)
                {
                    Btn_inv_prec_hero3.setVisibility(View.VISIBLE);
                    Btn_inv_prec_hero3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SwapItemInv(objet,actuhero,((Hero) ListHeros.get(3)),3);
                        }
                    });

                    if(ListHeros.size()>=5)
                    {
                        Btn_inv_prec_hero4.setVisibility(View.VISIBLE);
                        Btn_inv_prec_hero4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SwapItemInv(objet,actuhero,((Hero) ListHeros.get(4)),4);
                            }
                        });

                        if(ListHeros.size()>=6)
                        {
                            Btn_inv_prec_hero5.setVisibility(View.VISIBLE);
                            Btn_inv_prec_hero5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SwapItemInv(objet,actuhero,((Hero) ListHeros.get(5)),5);
                                }
                            });
                        }
                    }
                }
            }
        }

    }
    public void VerifHeroActif(int heroactu)
    {
        Hero heroLook = new Hero();
        if(heroactu ==0)
        {
            heroLook = (Hero) ListHeros.get(0);
            Hero_inv_prec.setText(heroLook.Name); /////Remplacer par Sprite
            Btn_inv_prec_hero0.setBackgroundResource(R.drawable.hero_inv_prec_yes);
        }
        else if(heroactu ==1)
        {
            heroLook = (Hero) ListHeros.get(1);
            Hero_inv_prec.setText(heroLook.Name); /////Remplacer par Sprite
            Btn_inv_prec_hero1.setBackgroundResource(R.drawable.hero_inv_prec_yes);
        }
        else if(heroactu ==2)
        {
            heroLook = (Hero) ListHeros.get(2);
            Hero_inv_prec.setText(heroLook.Name); /////Remplacer par Sprite
            Btn_inv_prec_hero2.setBackgroundResource(R.drawable.hero_inv_prec_yes);
        }
        else if(heroactu ==3)
        {
            heroLook = (Hero) ListHeros.get(3);
            Hero_inv_prec.setText(heroLook.Name); /////Remplacer par Sprite
            Btn_inv_prec_hero3.setBackgroundResource(R.drawable.hero_inv_prec_yes);
        }
        else if(heroactu ==4)
        {
            heroLook = (Hero) ListHeros.get(4);
            Hero_inv_prec.setText(heroLook.Name); /////Remplacer par Sprite
            Btn_inv_prec_hero4.setBackgroundResource(R.drawable.hero_inv_prec_yes);
        }
        else
        {
            heroLook = (Hero) ListHeros.get(5);
            Hero_inv_prec.setText(heroLook.Name); /////Remplacer par Sprite
            Btn_inv_prec_hero5.setBackgroundResource(R.drawable.hero_inv_prec_yes);
        }

    }
    public void Clean_btn_inv_Her()
    {
        Btn_inv_prec_hero0.setBackgroundResource(R.drawable.hero_inv_prec_not);
        Btn_inv_prec_hero1.setBackgroundResource(R.drawable.hero_inv_prec_not);
        Btn_inv_prec_hero2.setBackgroundResource(R.drawable.hero_inv_prec_not);
        Btn_inv_prec_hero3.setBackgroundResource(R.drawable.hero_inv_prec_not);
        Btn_inv_prec_hero4.setBackgroundResource(R.drawable.hero_inv_prec_not);
        Btn_inv_prec_hero5.setBackgroundResource(R.drawable.hero_inv_prec_not);
    }
    public void SwapItemInv(Item item_select, Hero heroBase, Hero heroDest,int hero)
    {
        heroBase.Inv.remove(item_select);
        heroDest.Inv.add(item_select);
        Clean_btn_inv_Her();
        VerifHeroActif(hero);

    }
    public void hideInvSection()
    {
        Sprite_inv_hero.setVisibility(View.GONE);
        Text_inv_nom.setVisibility(View.GONE);
        Scroll_inv_actu.setVisibility(View.GONE);
        btn_inv_suiv.setVisibility(View.GONE);
        btn_inv_prec.setVisibility(View.GONE);
    }
    public void ShowInvSection()
    {
        Sprite_inv_hero.setVisibility(View.VISIBLE);
        Text_inv_nom.setVisibility(View.VISIBLE);
        Scroll_inv_actu.setVisibility(View.VISIBLE);

    }
    public void HideInvPrec()
    {
        Hero_inv_prec.setVisibility(View.GONE);
        Nom_inv_prec.setVisibility(View.GONE);
        Sprite_inv_prec.setVisibility(View.GONE);
        Descr_inv_prec.setVisibility(View.GONE);
        Quit_inv_prec.setVisibility(View.GONE);

        Btn_inv_prec_hero0.setVisibility(View.GONE);
        Btn_inv_prec_hero1.setVisibility(View.GONE);
        Btn_inv_prec_hero2.setVisibility(View.GONE);
        Btn_inv_prec_hero3.setVisibility(View.GONE);
        Btn_inv_prec_hero4.setVisibility(View.GONE);
        Btn_inv_prec_hero5.setVisibility(View.GONE);
    }
    public void Set_prec_nom_hero()
    {
        Hero ActuH = new Hero();
        ActuH = (Hero) ListHeros.get(0);
        Btn_inv_prec_hero0.setText(ActuH.Name);
        ActuH = (Hero) ListHeros.get(1);
        Btn_inv_prec_hero1.setText(ActuH.Name);
        ActuH = (Hero) ListHeros.get(2);
        Btn_inv_prec_hero2.setText(ActuH.Name);
        ActuH = (Hero) ListHeros.get(3);
        Btn_inv_prec_hero3.setText(ActuH.Name);
        ActuH = (Hero) ListHeros.get(4);
        Btn_inv_prec_hero4.setText(ActuH.Name);
        ActuH = (Hero) ListHeros.get(5);
        Btn_inv_prec_hero5.setText(ActuH.Name);
    }
    ////////////Liste Quetes
    public void LiaisonElementCode_quete()
    {
        Text_quete_titre = findViewById(R.id.text_quete_titre);
        Scroll_quete = findViewById(R.id.Scroll_quete);

        Titre_rec = findViewById(R.id.text_recomp_titre);
        Sprite_rec=findViewById(R.id.Sprite_quete_recomp);
        Nom_rec = findViewById(R.id.text_quete_nom_rec);
        Stats_rec = findViewById(R.id.Scroll_stats_quete_rec);
        Descr_rec = findViewById(R.id.text_quete_desc_rec);
        Back_rec = findViewById(R.id.back_quete_rec);


    }
    public void GenerationQuete()
    {
        LinearLayout ContainerAll = new LinearLayout(this);
        ContainerAll.setOrientation(LinearLayout.VERTICAL);

        Quete currentQuest = new Quete();

        for (int compteur=0;compteur<ListQuet.size();compteur++)
        {
            currentQuest= (Quete) ListQuet.get(compteur);

            LinearLayout ContainerBlock = new LinearLayout(this);
            ContainerBlock.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout LeftBlock = new LinearLayout(this);
            LeftBlock.setOrientation(LinearLayout.VERTICAL);

            LinearLayout RightBlock = new LinearLayout(this);
            RightBlock.setOrientation(LinearLayout.VERTICAL);


            TextView Nom_quet = new TextView(this);
            Nom_quet.setText(currentQuest.name);
            Nom_quet.setHeight(150);
            Nom_quet.setWidth(1000);
            Nom_quet.setGravity(Gravity.CENTER);
            Nom_quet.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);

            /////////:block de gauche
            TextView Coord_quet = new TextView(this);
            Coord_quet.setHeight(120);
            Coord_quet.setWidth(700);
            Coord_quet.setGravity(Gravity.CENTER);
            Coord_quet.setText(""+currentQuest.Coordx+"x / " + currentQuest.Coordy +"y");
            Coord_quet.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);

            TextView Object_quet = new TextView(this);
            Object_quet.setWidth(700);
            Object_quet.setHeight(280);
            Object_quet.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            Object_quet.setGravity(Gravity.CENTER_HORIZONTAL);
            if(currentQuest.But <= currentQuest.Compteur)
            {
                Object_quet.setText("Complétée");
            }
            else
            {
                Object_quet.setText(currentQuest.Compteur + " / " + currentQuest.But + " " + currentQuest.Cible);
            }

            ContainerAll.addView(Nom_quet);
            LeftBlock.addView(Coord_quet);
            LeftBlock.addView(Object_quet);

            //////////////:block de droite


            Button btn_rec = new Button(this);
            btn_rec.setHeight(450);
            btn_rec.setWidth(500);
            btn_rec.setText(currentQuest.recompense.nom);
            final Quete finalCurrentQuest = currentQuest;
            btn_rec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AffichRec(finalCurrentQuest.recompense);
                }
            });

            RightBlock.addView(btn_rec);
            ////////////

            ContainerBlock.addView(LeftBlock);
            ContainerBlock.addView(RightBlock);

            ContainerAll.addView(ContainerBlock);

        }
        Scroll_quete.addView(ContainerAll);
    }
    public void AfficQuete()
    {
        Text_quete_titre.setVisibility(View.VISIBLE);
        Scroll_quete.setVisibility(View.VISIBLE);
    }
    public void AffichRec(Item recomp)
    {
        hideListQuest();
        ///set Sprite
        Titre_rec.setVisibility(View.VISIBLE);
        Sprite_rec.setVisibility(View.VISIBLE);
        Nom_rec.setText(recomp.nom);
        Nom_rec.setVisibility(View.VISIBLE);
        Descr_rec.setText(recomp.description);
        Descr_rec.setVisibility(View.VISIBLE);
        //////// Definition stats
        LinearLayout ListeStats = new LinearLayout(this);
        ListeStats.setOrientation(LinearLayout.VERTICAL);
        if(recomp.armurePlus!=0)
        {
            TextView armurePlus = new TextView(this);
            armurePlus.setText("+"+recomp.armurePlus +" armure");
            setSizeRecStat(armurePlus);
            ListeStats.addView(armurePlus);
        }
        if(recomp.armureMult!=0)
        {
            TextView armureMult = new TextView(this);
            armureMult.setText("+"+(recomp.armureMult*10) +"% armure");
            setSizeRecStat(armureMult);
            ListeStats.addView(armureMult);
        }
        if(recomp.armurmagPlus!=0)
        {
            TextView armuremagPlus = new TextView(this);
            armuremagPlus.setText(""+recomp.armurmagPlus +" armure mag");
            setSizeRecStat(armuremagPlus);
            ListeStats.addView(armuremagPlus);
        }
        if(recomp.armuremagMult!=0)
        {
            TextView armuremagMult = new TextView(this);
            armuremagMult.setText("+"+(recomp.armuremagMult*10) +"% armure mag");
            setSizeRecStat(armuremagMult);
            ListeStats.addView(armuremagMult);
        }
        if(recomp.degatPlus!=0)
        {
            TextView degatPlus = new TextView(this);
            degatPlus.setText(""+recomp.degatPlus +" degat");
            setSizeRecStat(degatPlus);
            ListeStats.addView(degatPlus);
        }
        if(recomp.degatMult!=0)
        {
            TextView degatMult = new TextView(this);
            degatMult.setText("+"+(recomp.degatMult*10) +"% degat");
            setSizeRecStat(degatMult);
            ListeStats.addView(degatMult);
        }
        if(recomp.degatmagPlus!=0)
        {
            TextView degatmagPlus = new TextView(this);
            degatmagPlus.setText(""+recomp.degatmagPlus +" degat mag");
            setSizeRecStat(degatmagPlus);
            ListeStats.addView(degatmagPlus);
        }
        if(recomp.degatmagMult!=0)
        {
            TextView degatmagMult = new TextView(this);
            degatmagMult.setText("+"+(recomp.degatmagMult*10) +"% degat mag");
            setSizeRecStat(degatmagMult);
            ListeStats.addView(degatmagMult);
        }
        if(recomp.esquivePlus!=0)
        {
            TextView esquivePlus = new TextView(this);
            esquivePlus.setText(""+recomp.esquivePlus +" esquive");
            setSizeRecStat(esquivePlus);
            ListeStats.addView(esquivePlus);
        }
        if(recomp.esquiveMult!=0)
        {
            TextView esquiveMult = new TextView(this);
            esquiveMult.setText("+"+(recomp.esquiveMult*10) +"% armure");
            setSizeRecStat(esquiveMult);
            ListeStats.addView(esquiveMult);
        }
        Stats_rec.removeAllViews();
        Stats_rec.addView(ListeStats);
        Stats_rec.setVisibility(View.VISIBLE);

        Back_rec.setVisibility(View.VISIBLE);
        back.setVisibility(View.GONE);

    }
    public void setSizeRecStat(TextView stat)
    {
        stat.setHeight(60);
        stat.setWidth(100);
        stat.setTextColor(Color.rgb(160,144,138));
        stat.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
    }
    public void HideRec()
    {
        Titre_rec.setVisibility(View.GONE);
        Sprite_rec.setVisibility(View.GONE);
        Nom_rec.setVisibility(View.GONE);
        Stats_rec.setVisibility(View.GONE);
        Descr_rec.setVisibility(View.GONE);
        Back_rec.setVisibility(View.GONE);

        back.setVisibility(View.VISIBLE);
    }
    public void hideListQuest()
    {
        Text_quete_titre.setVisibility(View.GONE);
        Scroll_quete.setVisibility(View.GONE);
    }
    ///////Liste Ress
    public void AffichRess()
    {
        liste_ress.setVisibility(View.VISIBLE);
        Titre_ress.setVisibility(View.VISIBLE);

    }
    public void GenerationRess()
    {
        LinearLayout ContainerAll = new LinearLayout(this);
        ContainerAll.setOrientation(LinearLayout.VERTICAL);

        Ressoure currentRess = new Ressoure();

        for (int compteur=0;compteur<ListRess.size();compteur++)
        {
            currentRess= (Ressoure) ListRess.get(compteur);

            LinearLayout ContainerBlock = new LinearLayout(this);
            ContainerBlock.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout LeftBlock = new LinearLayout(this);
            LeftBlock.setOrientation(LinearLayout.VERTICAL);

            LinearLayout RightBlock = new LinearLayout(this);
            RightBlock.setOrientation(LinearLayout.VERTICAL);


            TextView Nom_quet = new TextView(this);
            Nom_quet.setText(currentRess.Nom);
            Nom_quet.setHeight(150);
            Nom_quet.setWidth(1000);
            Nom_quet.setGravity(Gravity.CENTER);
            Nom_quet.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);

            /////////:block de gauche
            TextView Quant_Ress = new TextView(this);
            Quant_Ress.setHeight(120);
            Quant_Ress.setWidth(700);
            Quant_Ress.setGravity(Gravity.CENTER);
            Quant_Ress.setText("Quantité: " + currentRess.Quantite);
            Quant_Ress.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);



            ContainerAll.addView(Nom_quet);
            LeftBlock.addView(Quant_Ress);

            //////////////:block de droite


            Button btn_rec = new Button(this);
            btn_rec.setHeight(450);
            btn_rec.setWidth(500);
            btn_rec.setText(currentRess.image);

            RightBlock.addView(btn_rec);
            ////////////

            ContainerBlock.addView(LeftBlock);
            ContainerBlock.addView(RightBlock);

            ContainerAll.addView(ContainerBlock);

        }
        liste_ress.addView(ContainerAll);
    }
    public void SetNormal() //affichage de base
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                OpenLhero.setVisibility(View.VISIBLE);
                OpenLlieux.setVisibility(View.VISIBLE);
                OpenLvillages.setVisibility(View.VISIBLE);
                OpenLinventaires.setVisibility(View.VISIBLE);
                OpenLquetes.setVisibility(View.VISIBLE);
                OpenLRess.setVisibility(View.VISIBLE);
                btn_Quit.setVisibility(View.VISIBLE);
                Nom_player.setVisibility(View.VISIBLE);
                Sprite_player.setVisibility(View.VISIBLE);

                back.setVisibility(View.GONE);

                ///Liste Hero
                NomHero0.setVisibility(View.GONE);
                SpriteHero0.setVisibility(View.GONE);
                ClassesHero0.setVisibility(View.GONE);
                DescrptHero0.setVisibility(View.GONE);

                NomHero1.setVisibility(View.GONE);
                SpriteHero1.setVisibility(View.GONE);
                ClassesHero1.setVisibility(View.GONE);
                DescrptHero1.setVisibility(View.GONE);

                NomHero2.setVisibility(View.GONE);
                SpriteHero2.setVisibility(View.GONE);
                ClassesHero2.setVisibility(View.GONE);
                DescrptHero2.setVisibility(View.GONE);

                PrecPageHero.setVisibility(View.GONE);
                NextPageHero.setVisibility(View.GONE);

                /////////Liste Lieux/////
                Lieu0.setVisibility(View.GONE);
                Lieu0_coord.setVisibility(View.GONE);
                Lieu0_dist.setVisibility(View.GONE);

                Lieu1.setVisibility(View.GONE);
                Lieu1_coord.setVisibility(View.GONE);
                Lieu1_dist.setVisibility(View.GONE);

                Lieu2.setVisibility(View.GONE);
                Lieu2_coord.setVisibility(View.GONE);
                Lieu2_dist.setVisibility(View.GONE);

                Lieu3.setVisibility(View.GONE);
                Lieu3_coord.setVisibility(View.GONE);
                Lieu3_dist.setVisibility(View.GONE);

                Lieu4.setVisibility(View.GONE);
                Lieu4_coord.setVisibility(View.GONE);
                Lieu4_dist.setVisibility(View.GONE);

                Lieu5.setVisibility(View.GONE);
                Lieu5_coord.setVisibility(View.GONE);
                Lieu5_dist.setVisibility(View.GONE);

                Lieu6.setVisibility(View.GONE);
                Lieu6_coord.setVisibility(View.GONE);
                Lieu6_dist.setVisibility(View.GONE);

                Lieu7.setVisibility(View.GONE);
                Lieu7_coord.setVisibility(View.GONE);
                Lieu7_dist.setVisibility(View.GONE);

                ////////Liste Villages/////////////:

                Vil0.setVisibility(View.GONE);
                Vil0_coord.setVisibility(View.GONE);
                Vil0_name.setVisibility(View.GONE);

                Vil1.setVisibility(View.GONE);
                Vil1_coord.setVisibility(View.GONE);
                Vil1_name.setVisibility(View.GONE);

                Vil2.setVisibility(View.GONE);
                Vil2_coord.setVisibility(View.GONE);
                Vil2_name.setVisibility(View.GONE);

                Vil3.setVisibility(View.GONE);
                Vil3_coord.setVisibility(View.GONE);
                Vil3_name.setVisibility(View.GONE);

                ////////List inventaire//////////
                Sprite_inv_hero.setVisibility(View.GONE);
                Text_inv_nom.setVisibility(View.GONE);
                Scroll_inv_actu.setVisibility(View.GONE);
                btn_inv_prec.setVisibility(View.GONE);
                btn_inv_suiv.setVisibility(View.GONE);

                Hero_inv_prec.setVisibility(View.GONE);
                Nom_inv_prec.setVisibility(View.GONE);
                Sprite_inv_prec.setVisibility(View.GONE);
                Descr_inv_prec.setVisibility(View.GONE);
                Quit_inv_prec.setVisibility(View.GONE);

                Btn_inv_prec_hero0.setVisibility(View.GONE);
                Btn_inv_prec_hero1.setVisibility(View.GONE);
                Btn_inv_prec_hero2.setVisibility(View.GONE);
                Btn_inv_prec_hero3.setVisibility(View.GONE);
                Btn_inv_prec_hero4.setVisibility(View.GONE);
                Btn_inv_prec_hero5.setVisibility(View.GONE);
                ///////liste Quete//////////::
                Text_quete_titre.setVisibility(View.GONE);
                Scroll_quete.setVisibility(View.GONE);

                ////liste ress////
                Titre_ress.setVisibility(View.GONE);
                liste_ress.setVisibility(View.GONE);


                Titre_rec.setVisibility(View.GONE);
                Sprite_rec.setVisibility(View.GONE);
                Nom_rec.setVisibility(View.GONE);
                Stats_rec.setVisibility(View.GONE);
                Descr_rec.setVisibility(View.GONE);
                Back_rec.setVisibility(View.GONE);
            }
        });
    }
    public void HideNoraml() //caché menu de base
    {
        mainHandler.post(new Runnable() {
            @Override
            public void run()
            {
                OpenLhero.setVisibility(View.GONE);
                OpenLlieux.setVisibility(View.GONE);
                OpenLvillages.setVisibility(View.GONE);
                OpenLinventaires.setVisibility(View.GONE);
                OpenLquetes.setVisibility(View.GONE);
                OpenLRess.setVisibility(View.GONE);
                btn_Quit.setVisibility(View.GONE);
                Nom_player.setVisibility(View.GONE);
                Sprite_player.setVisibility(View.GONE);

                back.setVisibility(View.VISIBLE);
            }
        });
    }



    ///////////////////////////////////Lien OPEN///////////////
    public void openActivityEquipement() {
        Intent intent = new Intent(this, Activity_Equipement.class);
        startActivity(intent);
    }
    public void openActivityCombat() {
        Intent intent = new Intent(this, Activity_Combat.class);
        startActivity(intent);
    }
    public void openActivityVillage() {
        Intent intent = new Intent(this, Activity_Village.class);
        startActivity(intent);
    }

    ////////////////////fin lien OPEN//////////


    ///simulation db

    ////fausse equipe
    public ArrayList FausseEquip()
    {
        ArrayList FausseListe = new ArrayList();
        ArrayList FauxInv =new ArrayList();
        FauxInv = InvH0();
        FausseListe.add(FauxHero("Boby","J'aime les choux","Ranger 45","Barbare 31","x484215",FauxInv));
        FauxInv = InvH1();
        FausseListe.add(FauxHero("R","J'aime les ari","mage 45","Ranger 31","x788789",FauxInv));
        FauxInv = InvH2();
        FausseListe.add(FauxHero("QS","J'aime les zer","soldat 45","flamme 31","x121231345",FauxInv));
        FauxInv = InvH3();
        FausseListe.add(FauxHero("wcx","J'aime les azrt","merce 45","soldat 31","x7898544",FauxInv));
        FauxInv = InvH4();
        FausseListe.add(FauxHero("rezr","J'aime les fsd","flamme 45","zerty 31","x21051",FauxInv));
        FauxInv = InvH5();
        FausseListe.add(FauxHero("qsd","J'aime les choux","zerty 45","Barbare 31","x01531",FauxInv));


        return FausseListe;
    }
    public Hero FauxHero(String nom,String descr, String Cl1, String Cl2, String Im, ArrayList invent)
    {
        Hero newHero = new Hero();
        newHero.Name = nom;
        newHero.description = descr;
        newHero.Classe1 = Cl1;
        newHero.Classe2 = Cl2;
        newHero.Image = Im;
        newHero.Inv = invent;

        return newHero;
    }
    class Hero
    {
        public void LoadHero()
        {

        }
        public void SaveHero()
        {

        }
        String Name;
        String description;
        String Classe1;
        String Classe2;
        String Image;
        ArrayList Inv;
        Boolean Travel = false;
    }

    ///Fausse liste de lieux

    public ArrayList FausseListeLieux()
    {
        ArrayList FausseListe = new ArrayList();
        FausseListe.add(FauxLieu("Forêt de Soignies",44,532,2));
        FausseListe.add(FauxLieu("Forêt de  Cox",14,42,2));
        FausseListe.add(FauxLieu("bois de Soignies",44,532,1));
        FausseListe.add(FauxLieu("Chateau de Soignies",144,2,4));
        FausseListe.add(FauxLieu("Eglise de Soignies",74,51,3));
        FausseListe.add(FauxLieu("Enfer de Soignies",74,51,5));

        return FausseListe;
    }
    public Lieu FauxLieu(String Name,int Cox, int Coy, int Type)
    {
        Lieu newLieu = new Lieu();
        newLieu.name = Name;
        newLieu.coordx = Cox;
        newLieu.coordy = Coy;
        newLieu.type = Type;

        return newLieu;
    }
    class Lieu
    {
        public void LoadLieu()
        {

        }
        public void SaveLieu()
        {

        }
        String name;
        int coordx;
        int coordy;
        int type;
    }

    /////Fausse liste de Villages
    public ArrayList FausseListeVill()
    {
        ArrayList FausseListe = new ArrayList();
        FausseListe.add(FauxVil("Ronquières",47,98,2));
        FausseListe.add(FauxVil("NewPort",7,98,10));
        FausseListe.add(FauxVil("nivelles",47,8,5));

        return FausseListe;
    }
    public Village FauxVil(String Name,int Cox, int Coy, int lvl)
    {
        Village newVil = new Village();
        newVil.name = Name;
        newVil.coordx = Cox;
        newVil.coordy = Coy;
        newVil.lvl = lvl;

        return newVil;
    }
    class Village
    {
        public void LoadVillage()
        {

        }
        public void SaveVillage()
        {

        }
        String name;
        int coordx;
        int coordy;
        int lvl;
    }
    /////faux inventaire
    public ArrayList InvH0()
    {
        ArrayList inventaire = new ArrayList();
        inventaire.add(Ajoutitem("45","tete0",1,"c est une tete0",0,0,10,0,0,0.3,0,0,0,0));
        inventaire.add(Ajoutitem("45","torse0",2,"c est une torse0",0,0,0,0.4,0,0,40,0,0,0));
        inventaire.add(Ajoutitem("45","jambe0",3,"c est une jambe0",10,0,0,0,0,0,40,0,0,0));
        inventaire.add(Ajoutitem("45","Potionbleu",10,"Donne un boost de force",0,0,0,0,0,0,0,0,0,0));


        return  inventaire;
    }
    public ArrayList InvH1()
    {
        ArrayList inventaire = new ArrayList();
        inventaire.add(Ajoutitem("45","tete1",1,"c est une tete1",0,0,10,0,0,0.3,0,0,0,0));
        inventaire.add(Ajoutitem("45","torse1",2,"c est une torse1",0,0,0,0.4,0,0,40,0,0,0));
        inventaire.add(Ajoutitem("45","jambe1",3,"c est une jambe1",10,0,0,0,0,0,40,0,0,0));
        inventaire.add(Ajoutitem("45","PotionNoire",10,"Donne un boost de hite",0,0,0,0,0,0,0,0,0,0));


        return  inventaire;
    }
    public ArrayList InvH2()
    {
        ArrayList inventaire = new ArrayList();
        inventaire.add(Ajoutitem("45","tete2",2,"c est une tete2",0,0,20,0,0,0.3,0,0,0,0));
        inventaire.add(Ajoutitem("45","torse2",2,"c est une torse2",0,0,0,0.4,0,0,40,0,0,0));
        inventaire.add(Ajoutitem("45","jambe2",3,"c est une jambe2",20,0,0,0,0,0,40,0,0,0));
        inventaire.add(Ajoutitem("45","PotionNoire",10,"Donne un boost de hite",0,0,0,0,0,0,0,0,0,0));


        return  inventaire;
    }
    public ArrayList InvH3()
    {
        ArrayList inventaire = new ArrayList();
        inventaire.add(Ajoutitem("45","tete3",3,"c est une tete3",0,0,30,0,0,0.3,0,0,0,0));
        inventaire.add(Ajoutitem("45","torse3",3,"c est une torse3",0,0,0,0.4,0,0,40,0,0,0));
        inventaire.add(Ajoutitem("45","jambe3",3,"c est une jambe3",30,0,0,0,0,0,40,0,0,0));
        inventaire.add(Ajoutitem("45","PotionNoire",10,"Donne un boost de hite",0,0,0,0,0,0,0,0,0,0));


        return  inventaire;
    }
    public ArrayList InvH4()
    {
        ArrayList inventaire = new ArrayList();
        inventaire.add(Ajoutitem("45","tete4",4,"c est une tete4",0,0,40,0,0,0.4,0,0,0,0));
        inventaire.add(Ajoutitem("45","torse4",4,"c est une torse4",0,0,0,0.4,0,0,40,0,0,0));
        inventaire.add(Ajoutitem("45","jambe4",4,"c est une jambe4",40,0,0,0,0,0,40,0,0,0));
        inventaire.add(Ajoutitem("45","PotionNoire",10,"Donne un boost de hite",0,0,0,0,0,0,0,0,0,0));


        return  inventaire;
    }
    public ArrayList InvH5()
    {
        ArrayList inventaire = new ArrayList();
        inventaire.add(Ajoutitem("45","tete5",5,"c est une tete5",0,0,50,0,0,0.5,0,0,0,0));
        inventaire.add(Ajoutitem("45","torse5",5,"c est une torse5",0,0,0,0.5,0,0,50,0,0,0));
        inventaire.add(Ajoutitem("45","jambe5",5,"c est une jambe5",50,0,0,0,0,0,50,0,0,0));
        inventaire.add(Ajoutitem("45","PotionNoire",10,"Donne un boost de hite",0,0,0,0,0,0,0,0,0,0));


        return  inventaire;
    }

    public Item Ajoutitem(String image,String Name, int Type, String Descrpt, int ArmPlus, double ArmMult,int ArmmagPlus, double ArmmagMult, int DegPlus, double DegMult, int DegmagPlus, double DegmagMult, int EsqPlus, double EsqMult)
    {
        Item item = new Item();
        item.Image = image;
        item.nom = Name;
        item.type = Type;
        item.description=Descrpt;
        item.armurePlus = ArmPlus;
        item.armureMult = ArmMult;
        item.armurmagPlus =ArmmagPlus;
        item.armuremagMult = ArmmagMult;
        item.degatPlus = DegPlus;
        item.degatMult = DegMult;
        item.degatmagPlus = DegmagPlus;
        item.degatmagMult = DegmagMult;
        item.esquivePlus = EsqPlus;
        item.esquiveMult = EsqMult;

        return item;
    }
    class Item
    {
        public void LoadItem()
        {

        }
        public void SaveItem()
        {

        }
        String Image;
        String nom;
        int type;
        String description;
        int armurePlus =0;
        double armureMult=0.0;
        int armurmagPlus =0;
        double armuremagMult =0;
        int degatPlus =0;
        double degatMult =0.0;
        int degatmagPlus=0;
        double degatmagMult=0.0;
        int esquivePlus=0;
        double esquiveMult=0.0;
    }

    ////Fausse liste de quêtes///

    public ArrayList QueteListe()
    {
        ArrayList listeQuete = new ArrayList();
        Item recompenseQuete = Ajoutitem("x151","damocles",1,"c est une damo",0,0,10,0,0,0.3,0,0,0,0);
        listeQuete.add(AjoutQuete("Quete de bob","loup",4,10,200,100,recompenseQuete));
        listeQuete.add(AjoutQuete("Quete de bob","loup",4,10,200,100,recompenseQuete));
        listeQuete.add(AjoutQuete("Quete de bob","loup",4,10,200,100,recompenseQuete));
        recompenseQuete = Ajoutitem("x151","Sword",1,"c est une Sword",0,0,10,0,0,0.3,0,0,0,0);
        listeQuete.add(AjoutQuete("Quete de rob","dragon",10,10,200,100,recompenseQuete));

        return listeQuete;
    }

    public Quete AjoutQuete(String Nom, String Cible, int tue, int atue, int coorx, int coory, Item Recompense)
    {
        Quete newQuete = new Quete();
        newQuete.name = Nom;
        newQuete.Cible = Cible;
        newQuete.Compteur = tue;
        newQuete.But = atue;
        newQuete.Coordx = coorx;
        newQuete.Coordy = coory;
        newQuete.recompense = Recompense;

        return newQuete;
    }

    class Quete
    {
        public void LoadQuete()
        {

        }
        public void SaveQuete()
        {

        }
        String name;
        String Cible; ///////string nom mob;
        int Compteur;////////nombre de cible tué
        int But; ///////nombre de cible à tué
        int Coordx;
        int Coordy;
        Item recompense;
    }

    ///Fausse liste Ressource

    public ArrayList RessListe()
    {
        ArrayList listeRess = new ArrayList();
        listeRess.add(addRess("bois",15,"x45"));
        listeRess.add(addRess("pierre",15,"x45"));
        listeRess.add(addRess("fer",15,"x45"));
        listeRess.add(addRess("or",15,"x45"));
        return listeRess;
    }
    public Ressoure addRess(String Name,int taille,String image)
    {
        Ressoure newRess = new Ressoure();
        newRess.Nom = Name;
        newRess.Quantite = taille;
        newRess.image = image;
        return newRess;
    }
    class Ressoure
    {
        String Nom;
        int Quantite;
        String image;
    }
}
