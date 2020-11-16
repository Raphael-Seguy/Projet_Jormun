package com.example.projet_jormun;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity_Village extends AppCompatActivity {

    private Handler mainHandler = new Handler();

    private TextView VilNom;
    private TextView VilLvl;
    private Button UpgVil;

    private Button bnt_bat0;
    private Button bnt_bat1;
    private Button bnt_bat2;
    private Button bnt_bat3;
    private Button bnt_bat4;

    private Button btn_quit;
    private Button btn_suiv;
    private Button btn_prec;

    ///bat up

    private ImageView SpriteBat;
    private TextView Nom_bat;
    private ScrollView Scroll_prod_bat;
    private Button btn_up_bat;
    private TextView text_nec_bat;
    private Button btn_back;
    private TextView Text_prod_titre_up;

    //creer Bat
    private ScrollView ListCreat;
    private Button btn_retour_creat;


    //fin crea Bat
    private  TextView NomBat_end;
    private ImageView Sprite_bat_crea_end;
    private ScrollView Prod_bat_crea_end;
    private Button Back_crea_end;
    private Button Yes_crea_end;
    private TextView Titre_prod_crea_end;

    private Button Btn_yesCreate;

    Village vill=new Village();
    ArrayList invRess = new ArrayList();

    private int ActuPage=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village);
        vill=FauxVillage("Ronquières",4,5);
        invRess = FauxInvRes();

        LiaisonCode_affichBat();
        SetNormal();
        Generation_village();
        GenerationListeCrea();
        VerifLvl(vill.Lvl);
        LaunchBase();
    }

    private void LaunchBase()
    {
        Base runnable = new Base();
        new Thread(runnable).start();
    }

    class Base implements Runnable
    {
        boolean Quit=false;
        @Override
        public void run() {
            while (Quit==false)
            {
                btn_suiv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_prec.setVisibility(View.VISIBLE);
                        AffichPageNext();
                        VerifLvl(vill.Lvl);
                    }
                });
                btn_prec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_suiv.setVisibility(View.VISIBLE);
                        AffichBtn();
                        AffichPagePrec();
                        VerifLvl(vill.Lvl);
                    }
                });
                btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetNormal();
                        VerifLvl(vill.Lvl);
                    }
                });

                btn_retour_creat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetNormal();
                        VerifLvl(vill.Lvl);

                    }
                });
                Back_crea_end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        VilNom.setVisibility(View.GONE);
                        VilLvl.setVisibility(View.GONE);
                        UpgVil.setVisibility(View.GONE);

                        bnt_bat0.setVisibility(View.GONE);
                        bnt_bat1.setVisibility(View.GONE);
                        bnt_bat2.setVisibility(View.GONE);
                        bnt_bat3.setVisibility(View.GONE);
                        bnt_bat4.setVisibility(View.GONE);

                        btn_quit.setVisibility(View.VISIBLE);
                        btn_prec.setVisibility(View.GONE);
                        btn_suiv.setVisibility(View.GONE);

                        ListCreat.setVisibility(View.VISIBLE);
                        btn_retour_creat.setVisibility(View.VISIBLE);

                        NomBat_end.setVisibility(View.GONE);
                        Sprite_bat_crea_end.setVisibility(View.GONE);
                        Prod_bat_crea_end.setVisibility(View.GONE);
                        Back_crea_end.setVisibility(View.GONE);
                        Yes_crea_end.setVisibility(View.GONE);
                        Titre_prod_crea_end.setVisibility(View.GONE);
                    }
                });
                btn_quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QuitThisPlace();
                    }
                });
            }
        }
    }
    public void QuitThisPlace() {
        Intent intent = new Intent(Activity_Village.this,
                ActivityBase.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void LiaisonCode_affichBat()
    {
        VilNom=findViewById(R.id.text_nom_vill);
        VilLvl=findViewById(R.id.text_lvl_vil);
        UpgVil = findViewById(R.id.btn_up_vil);

        bnt_bat0=findViewById(R.id.btn_bat0_vil);
        bnt_bat1=findViewById(R.id.btn_bat1_vil);
        bnt_bat2=findViewById(R.id.btn_bat2_vil);
        bnt_bat3=findViewById(R.id.btn_bat3_vil);
        bnt_bat4=findViewById(R.id.btn_bat4_vil);

        btn_quit=findViewById(R.id.btn_quit_vil);
        btn_prec=findViewById(R.id.btn_prec_vil);
        btn_suiv=findViewById(R.id.btn_suiv_vil);

        SpriteBat = findViewById(R.id.Sprite_bat_prec);
        Nom_bat = findViewById(R.id.text_nom_bat_prec);
        Scroll_prod_bat = findViewById(R.id.Scroll_prod_bat_prec);
        btn_up_bat = findViewById(R.id.btn_up_bat_prec);
        text_nec_bat = findViewById(R.id.text_up_nec_bat_prec);
        btn_back = findViewById(R.id.btn_back);
        Text_prod_titre_up=findViewById(R.id.text_titre_prod_up);

        ListCreat=findViewById(R.id.Scorl_creat_liste);
        btn_retour_creat=findViewById(R.id.btn_retour_creat);

        NomBat_end = findViewById(R.id.Nom_bat_creat_end);
        Sprite_bat_crea_end = findViewById(R.id.Sprite_Bat_crea_end);
        Prod_bat_crea_end = findViewById(R.id.Scroll_crea_end_prod);
        Back_crea_end = findViewById(R.id.btn_back_crea_end);
        Yes_crea_end = findViewById(R.id.Btn_Yes_crea_end);
        Titre_prod_crea_end=findViewById(R.id.Pord_titre_crea_end);
    }

    public void Generation_village()
    {
        ArrayList ListBat = vill.listBat;
        Batiment batactu = new Batiment();
        VilNom.setText(vill.nom);
        VilLvl.setText("Lvl"+vill.Lvl);
        if(ListBat.size()>=1)
        {
            batactu = (Batiment) ListBat.get(0);
            bnt_bat0.setBackgroundResource(batactu.image);
            SetColorBat(bnt_bat0,batactu,invRess);
            final Batiment finalBatactu4 = batactu;
            bnt_bat0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AffichBatprec(0,bnt_bat0, finalBatactu4);
                }
            });
            if(ListBat.size()>=2)
            {
                batactu = (Batiment) ListBat.get(1);
                bnt_bat1.setBackgroundResource(batactu.image);
                SetColorBat(bnt_bat1,batactu,invRess);
                final Batiment finalBatactu3 = batactu;
                bnt_bat1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AffichBatprec(1,bnt_bat1, finalBatactu3);
                    }
                });
                if(ListBat.size()>=3)
                {
                    batactu = (Batiment) ListBat.get(2);
                    bnt_bat2.setBackgroundResource(batactu.image);
                    SetColorBat(bnt_bat2,batactu,invRess);
                    final Batiment finalBatactu2 = batactu;
                    bnt_bat2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AffichBatprec(2,bnt_bat2, finalBatactu2);
                        }
                    });
                    if (ListBat.size()>=4)
                    {
                        batactu = (Batiment) ListBat.get(3);
                        bnt_bat3.setBackgroundResource(batactu.image);
                        SetColorBat(bnt_bat3,batactu,invRess);
                        final Batiment finalBatactu1 = batactu;
                        bnt_bat3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AffichBatprec(3,bnt_bat3, finalBatactu1);
                            }
                        });
                        if(ListBat.size()>=5)
                        {
                            batactu = (Batiment) ListBat.get(4);
                            bnt_bat4.setBackgroundResource(batactu.image);
                            SetColorBat(bnt_bat4,batactu,invRess);
                            final Batiment finalBatactu = batactu;
                            bnt_bat4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AffichBatprec(4,bnt_bat4, finalBatactu);
                                }
                            });
                        }
                        else
                        {
                            EmptyButon(5);
                        }
                    }
                    else
                    {
                        EmptyButon(4);
                    }
                }
                else
                {
                    EmptyButon(3);
                }
            }
            else
            {
                EmptyButon(2);
            }
        }
        else
        {
            EmptyButon(1);
        }

        VerifLvl(vill.Lvl);

    }
    public void Generation_village2()
    {
        btn_suiv.setVisibility(View.VISIBLE);
        btn_suiv.setVisibility(View.VISIBLE);

        ArrayList ListBat = vill.listBat;
        Batiment batactu = new Batiment();
        VilNom.setText(vill.nom);
        VilLvl.setText("Lvl"+vill.Lvl);
        if(ListBat.size()>=6)
        {
            batactu = (Batiment) ListBat.get(5);
            bnt_bat0.setBackgroundResource(batactu.image);
            SetColorBat(bnt_bat0,batactu,invRess);
            final Batiment finalBatactu4 = batactu;
            bnt_bat0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AffichBatprec(5,bnt_bat0, finalBatactu4);
                }
            });
            if(ListBat.size()>=7)
            {
                batactu = (Batiment) ListBat.get(6);
                bnt_bat1.setBackgroundResource(batactu.image);
                SetColorBat(bnt_bat1,batactu,invRess);
                final Batiment finalBatactu3 = batactu;
                bnt_bat1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AffichBatprec(6,bnt_bat1, finalBatactu3);
                    }
                });
                if(ListBat.size()>=8)
                {
                    batactu = (Batiment) ListBat.get(7);
                    bnt_bat2.setBackgroundResource(batactu.image);
                    SetColorBat(bnt_bat2,batactu,invRess);
                    final Batiment finalBatactu2 = batactu;
                    bnt_bat2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AffichBatprec(7,bnt_bat2, finalBatactu2);
                        }
                    });
                    if (ListBat.size()>=9)
                    {
                        batactu = (Batiment) ListBat.get(8);
                        bnt_bat3.setBackgroundResource(batactu.image);
                        SetColorBat(bnt_bat3,batactu,invRess);
                        final Batiment finalBatactu1 = batactu;
                        bnt_bat3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AffichBatprec(8,bnt_bat3, finalBatactu1);
                            }
                        });
                        if(ListBat.size()>=10)
                        {
                            batactu = (Batiment) ListBat.get(9);
                            bnt_bat4.setBackgroundResource(batactu.image);
                            SetColorBat(bnt_bat4,batactu,invRess);
                            final Batiment finalBatactu = batactu;
                            bnt_bat4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AffichBatprec(9,bnt_bat4, finalBatactu);
                                }
                            });
                        }
                        else
                        {
                            EmptyButon(5);
                        }
                    }
                    else
                    {
                        EmptyButon(4);
                    }
                }
                else
                {
                    EmptyButon(3);
                }
            }
            else
            {
                EmptyButon(2);
            }
        }
        else
        {
            EmptyButon(1);
        }

    }
    public void Generation_village3()
    {
        ArrayList ListBat = vill.listBat;
        Batiment batactu = new Batiment();
        VilNom.setText(vill.nom);
        VilLvl.setText("Lvl"+vill.Lvl);
        if(ListBat.size()>=11)
        {
            batactu = (Batiment) ListBat.get(10);
            bnt_bat0.setBackgroundResource(batactu.image);
            SetColorBat(bnt_bat0,batactu,invRess);
            final Batiment finalBatactu = batactu;
            bnt_bat0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AffichBatprec(10,bnt_bat0, finalBatactu);
                }
            });
            if(ListBat.size()>=12)
            {
                batactu = (Batiment) ListBat.get(11);
                bnt_bat1.setBackgroundResource(batactu.image);
                SetColorBat(bnt_bat1,batactu,invRess);
                final Batiment finalBatactu1 = batactu;
                bnt_bat1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AffichBatprec(11,bnt_bat1, finalBatactu1);
                    }
                });
                if(ListBat.size()>=13)
                {
                    batactu = (Batiment) ListBat.get(12);
                    bnt_bat2.setBackgroundResource(batactu.image);
                    SetColorBat(bnt_bat2,batactu,invRess);
                    final Batiment finalBatactu2 = batactu;
                    bnt_bat2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AffichBatprec(12,bnt_bat2, finalBatactu2);
                        }
                    });
                }
                else
                {
                    EmptyButon(3);
                }
            }
            else
            {
                EmptyButon(2);
            }
        }
        else
        {
            EmptyButon(1);
        }
        bnt_bat3.setVisibility(View.GONE);
        bnt_bat4.setVisibility(View.GONE);
    }
    public void AffichBatprec(final int Batplace, final Button btnCible, final Batiment bat)
    {
        btnCible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////Sprite = sprite bat
                VilNom.setVisibility(View.GONE);
                VilLvl.setVisibility(View.GONE);
                UpgVil.setVisibility(View.GONE);

                bnt_bat0.setVisibility(View.GONE);
                bnt_bat1.setVisibility(View.GONE);
                bnt_bat2.setVisibility(View.GONE);
                bnt_bat3.setVisibility(View.GONE);
                bnt_bat4.setVisibility(View.GONE);
                btn_suiv.setVisibility(View.GONE);
                btn_prec.setVisibility(View.GONE);


                Nom_bat.setText(bat.nom+ " " +bat.lvl);
                Nom_bat.setVisibility(View.VISIBLE);
                /////sprite = sprite bat
                SpriteBat.setVisibility(View.VISIBLE);
                SpriteBat.setImageResource(bat.image);
                SetUpTrue(btnCible,bat,invRess,text_nec_bat,Batplace);
                btn_up_bat.setVisibility(View.VISIBLE);
                AffichProd(bat);
                Scroll_prod_bat.setVisibility(View.VISIBLE);
                text_nec_bat.setVisibility(View.VISIBLE);
                btn_back.setVisibility(View.VISIBLE);
                Text_prod_titre_up.setVisibility(View.VISIBLE);

            }
        });
    }
    public void EmptyButon(int place)
    {
        if(place==1)
        {
            bnt_bat0.setBackgroundResource(R.drawable.add_logo);
            bnt_bat0.setText("");
            bnt_bat0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateBat();
                    ;                }
            });
        }
        if (place<=2)
        {
            bnt_bat1.setBackgroundResource(R.drawable.add_logo);
            bnt_bat1.setText("");
            bnt_bat1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateBat();
                    ;                }
            });
        }
        if (place<=3)
        {
            bnt_bat2.setBackgroundResource(R.drawable.add_logo);
            bnt_bat2.setText("");
            bnt_bat2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateBat();
                    ;                }
            });
        }
        if(place<=4)
        {
            bnt_bat3.setBackgroundResource(R.drawable.add_logo);
            bnt_bat3.setText("");
            bnt_bat3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateBat();
                    ;                }
            });
        }
        if (place<=5)
        {
            bnt_bat4.setBackgroundResource(R.drawable.add_logo);
            bnt_bat4.setText("");
            bnt_bat4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateBat();
                    ;                }
            });
        }

    }
    public void VerifLvl(int LVL) //////Verif du lvl village et donc du nombre d emplacement
    {
        int emplacement = LVL+2;
        for (int Compteur = ActuPage; Compteur > 0; Compteur--)
        {
            emplacement=emplacement-5;
        }
        if(emplacement<5)
        {
            bnt_bat4.setVisibility(View.GONE);
            if(emplacement<4)
            {
                bnt_bat3.setVisibility(View.GONE);
                if(emplacement<3)
                {
                    bnt_bat2.setVisibility(View.GONE);
                    if (emplacement<2)
                    {
                        bnt_bat1.setVisibility(View.GONE);
                    }
                }
            }
            btn_suiv.setVisibility(View.GONE);
        }

    }
    public void AffichPageNext()
    {
        ActuPage+=1;
        if(ActuPage==1)
        {
            Generation_village2();
        }
        else
        {
            Generation_village3();
            btn_suiv.setVisibility(View.GONE);
        }
    }
    public void AffichPagePrec()
    {
        ActuPage=ActuPage-1;
        if(ActuPage==1)
        {
            Generation_village2();
        }
        else
        {
            Generation_village();
            btn_prec.setVisibility(View.GONE);
        }
    }
    public String SetNameBat(Batiment batactu) ////doit renvoyer un sprite et non un texte
    {
        String image;
        if(batactu.type==1)
        {
            if(batactu.lvl<4)
            {
                image="taverne";
            }
            else if (batactu.lvl>=7)
            {
                image="guilde des aventuriers";
            }
            else
            {
                image="auberge";
            }
        }
        else if(batactu.type==2)
        {
            if(batactu.lvl<4)
            {
                image="réparateur";
            }
            else if (batactu.lvl>=7)
            {
                image="guilde des artisans";
            }
            else
            {
                image="Forge";
            }
        }
        else if(batactu.type==3)
        {
            if(batactu.lvl<4)
            {
                image="cabane de bucheron";
            }
            else
            {
                image="Scierie";
            }
        }
        else if (batactu.type==4)
        {
            if(batactu.lvl<4)
            {
                image="baricade";
            }
            else if (batactu.lvl>=7)
            {
                image="Enceinte";
            }
            else
            {
                image="Muraille";
            }
        }
        else if (batactu.type==5)
        {
            if(batactu.lvl<4)
            {
                image="échoppe";
            }
            else if (batactu.lvl>=7)
            {
                image="Marché";
            }
            else
            {
                image="Magasin";
            }
        }
        else if(batactu.type==6)
        {
            if(batactu.lvl<4)
            {
                image="Mine";
            }
            else
            {
                image="Carrière";
            }
        }
        else
        {
            image="enchanteur";
        }

        return image;
    }
    public int SetImage(Batiment batactu) ////doit renvoyer un sprite et non un texte
    {
        int image;
        if(batactu.type==1)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.taverne;
            }
            else if (batactu.lvl>=7)
            {
                image=R.drawable.guilde_aventurier;
            }
            else
            {
                image=R.drawable.auberge;
            }
        }
        else if(batactu.type==2)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.reprateur;
            }
            else if (batactu.lvl>=7)
            {
                image=R.drawable.guilde_artisan;
            }
            else
            {
                image=R.drawable.forgeron;
            }
        }
        else if(batactu.type==3)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.cabane_bucheron;
            }
            else
            {
                image=R.drawable.scierie;
            }
        }
        else if (batactu.type==4)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.baricade;
            }
            else if (batactu.lvl>=7)
            {
                image=R.drawable.enceinte;
            }
            else
            {
                image=R.drawable.muraille;
            }
        }
        else if (batactu.type==5)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.echoppe;
            }
            else if (batactu.lvl>=7)
            {
                image=R.drawable.marche;
            }
            else
            {
                image=R.drawable.magasin;
            }
        }
        else if(batactu.type==6)
        {
            if(batactu.lvl<4)
            {
                image=R.drawable.mine;
            }
            else
            {
                image=R.drawable.cariere;
            }
        }
        else
        {
            image=R.drawable.enchanteur;
        }

        return image;
    }
    public void SetColorBat(Button btn_bat, Batiment bat_actu,ArrayList invress)    ////affiche si possible de LvlUp avec ressource actu
    {
        int LvlBat = bat_actu.lvl;
        int typebat= bat_actu.type;

        boolean up_ok = true;
        boolean Find=false;
        Ressource ressourceActu= new Ressource();
        Ressource ressourceInInv= new Ressource();
        ArrayList Bat_requis=Upressources(typebat,LvlBat);
        for(int Compteur_Bat=0; Compteur_Bat< Bat_requis.size();Compteur_Bat++)
        {
            ressourceActu= (Ressource) Bat_requis.get(Compteur_Bat);
            for (int Compteur_inv =0; Compteur_inv< invRess.size();Compteur_inv++)
            {
                ressourceInInv= (Ressource) invRess.get(Compteur_inv);
                if(ressourceActu.name == ressourceInInv.name)
                {
                    if(ressourceActu.quantite>ressourceInInv.quantite)
                    {
                        up_ok=false;
                    }
                    Find=true;
                }
            }
            if(Find==false)
            {
                up_ok=false;
                Find=true;
            }
        }
        if(up_ok==true)
        {
            btn_bat.setBackgroundResource(bat_actu.image);
        }
    }
    public void SetUpTrue(final Button btn_bat, final Batiment bat_actu, ArrayList invress, TextView ressourceManq, final int Batplace)    ////affiche si possible de LvlUp avec ressource actu
    {
        int LvlBat = bat_actu.lvl;
        int typebat= bat_actu.type;

        boolean up_ok = true;
        boolean Find=false;
        String Manq ="Ressources manquantes:";
        Ressource ressourceActu= new Ressource();
        Ressource ressourceInInv= new Ressource();
        final ArrayList Bat_requis=Upressources(typebat,LvlBat);
        for(int Compteur_Bat=0; Compteur_Bat< Bat_requis.size();Compteur_Bat++)
        {
            ressourceActu= (Ressource) Bat_requis.get(Compteur_Bat);
            for (int Compteur_inv =0; Compteur_inv< invRess.size();Compteur_inv++)
            {
                ressourceInInv= (Ressource) invRess.get(Compteur_inv);
                if(ressourceActu.name == ressourceInInv.name)
                {
                    if(ressourceActu.quantite>ressourceInInv.quantite)
                    {
                        up_ok=false;
                        Manq = Manq + " "+ (ressourceActu.quantite-ressourceInInv.quantite) + " " + ressourceActu.name;
                    }
                    Find=true;
                }
            }
            if(Find==false)
            {
                Manq = Manq + " " + ressourceActu.quantite + " " + ressourceActu.name;
                up_ok=false;
                Find=true;
            }
        }
        if(up_ok==false)
        {
            btn_up_bat.setBackgroundResource(R.drawable.btn_yes_radius_no);
            btn_up_bat.setText("Ressources insufisantes");
            ressourceManq.setText(Manq);
        }
        else
        {
            btn_up_bat.setBackgroundResource(R.drawable.btn_yes_radius_yes);
            btn_up_bat.setText("Ameliorer");
            btn_up_bat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bat_actu.lvl = bat_actu.lvl +1;
                    EnleverRess(Bat_requis,invRess);
                    SetNormal();
                }
            });

        }
    }

    public void AffichProd(Batiment batActu)
    {
        LinearLayout Container = new LinearLayout(this);
        Container.setOrientation(LinearLayout.VERTICAL);
        ArrayList prodSem = batActu.ProdSem;
        Ressource Ress = new Ressource();


        for (int Compteur=0; Compteur<prodSem.size();Compteur++)
        {
            TextView Prod = new TextView(this);
            Ress= (Ressource) prodSem.get(Compteur);
            Prod.setText(""+Ress.quantite+" "+Ress.name+" par semaine");
            Prod.setTextSize(15);
            Prod.setTextColor(Color.rgb(219,223,223));
            Container.addView(Prod);
        }
        Scroll_prod_bat.removeAllViews();
        Scroll_prod_bat.addView(Container);
    }


    public void CreateBat()
    {
        VilNom.setVisibility(View.GONE);
        VilLvl.setVisibility(View.GONE);
        UpgVil.setVisibility(View.GONE);

        bnt_bat0.setVisibility(View.GONE);
        bnt_bat1.setVisibility(View.GONE);
        bnt_bat2.setVisibility(View.GONE);
        bnt_bat3.setVisibility(View.GONE);
        bnt_bat4.setVisibility(View.GONE);

        btn_quit.setVisibility(View.VISIBLE);
        btn_prec.setVisibility(View.GONE);
        btn_suiv.setVisibility(View.GONE);

        ListCreat.setVisibility(View.VISIBLE);
        btn_retour_creat.setVisibility(View.VISIBLE);

    }

    public void GenerationListeCrea()
    {
        LinearLayout ContainerTot = new LinearLayout(this);
        ContainerTot.setOrientation(LinearLayout.VERTICAL);

        LinearLayout ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        ////taverne
        TextView Tavernes =new TextView(this);
        Tavernes.setText("repos");
        SetTextCreer(Tavernes);
        ContainerTot.addView(Tavernes);

        Button Tavern = new Button(this); ///Sprite
        Tavern.setBackgroundResource(R.drawable.taverne);
        SetBatCreer(Tavern);
        Tavern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Tavern",1,1);
            }
        });
        ContainerListe.addView(Tavern);

        Button Auberge = new Button(this);
        Auberge.setBackgroundResource(R.drawable.auberge);
        SetBatCreer(Auberge);
        Auberge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Auberge",1,4);
            }
        });
        ContainerListe.addView(Auberge);

        Button Guilde_des_aventuriers = new Button(this);
        Guilde_des_aventuriers.setBackgroundResource(R.drawable.guilde_aventurier);
        SetBatCreer(Guilde_des_aventuriers);
        Guilde_des_aventuriers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Guilde_des_aventuriers",1,8);
            }
        });
        ContainerListe.addView(Guilde_des_aventuriers);

        ContainerTot.addView(ContainerListe);

        ///Forge
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Forge =new TextView(this);
        Forge.setText("Forges");
        SetTextCreer(Forge);
        ContainerTot.addView(Forge);

        Button reparateur = new Button(this);
        reparateur.setBackgroundResource(R.drawable.reprateur);
        SetBatCreer(reparateur);
        reparateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("reparateur",2,1);
            }
        });
        ContainerListe.addView(reparateur);

        Button forge = new Button(this);
        forge.setBackgroundResource(R.drawable.forgeron);
        SetBatCreer(forge);
        forge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("forge",2,4);
            }
        });
        ContainerListe.addView(forge);

        Button guilde_des_artisans = new Button(this);
        guilde_des_artisans.setBackgroundResource(R.drawable.guilde_artisan);
        SetBatCreer(guilde_des_artisans);
        guilde_des_artisans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("guilde_des_artisans",2,8);
            }
        });
        ContainerListe.addView(guilde_des_artisans);

        ContainerTot.addView(ContainerListe);
        ///Bucheron
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Bucherons =new TextView(this);
        Bucherons.setText("Bucherons");
        SetTextCreer(Bucherons);
        ContainerTot.addView(Bucherons);

        Button Bucheron = new Button(this);
        Bucheron.setBackgroundResource(R.drawable.cabane_bucheron);
        SetBatCreer(Bucheron);
        Bucheron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Bucheron",3,1);
            }
        });
        ContainerListe.addView(Bucheron);

        Button Scierie = new Button(this);
        Scierie.setBackgroundResource(R.drawable.scierie);
        SetBatCreer(Scierie);
        Scierie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Scierie",3,4);
            }
        });
        ContainerListe.addView(Scierie);

        ContainerTot.addView(ContainerListe);
        ///Defenses
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Defenses =new TextView(this);
        Defenses.setText("Defenses");
        SetTextCreer(Defenses);
        ContainerTot.addView(Defenses);

        Button Barricade = new Button(this);
        Barricade.setBackgroundResource(R.drawable.baricade);
        SetBatCreer(Barricade);
        Barricade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Barricade",4,1);
            }
        });
        ContainerListe.addView(Barricade);

        Button Muraille = new Button(this);
        Muraille.setBackgroundResource(R.drawable.muraille);
        SetBatCreer(Muraille);
        Muraille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Muraille",4,4);
            }
        });
        ContainerListe.addView(Muraille);

        Button Enceinte = new Button(this);
        Enceinte.setBackgroundResource(R.drawable.enceinte);
        SetBatCreer(Enceinte);
        ContainerListe.addView(Enceinte);
        Enceinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Enceinte",4,8);
            }
        });
        ContainerTot.addView(ContainerListe);
        ///Vendeurs
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Vendeurs =new TextView(this);
        Vendeurs.setText("Vendeurs");
        SetTextCreer(Vendeurs);
        ContainerTot.addView(Vendeurs);

        Button Echope = new Button(this);
        Echope.setBackgroundResource(R.drawable.echoppe);
        SetBatCreer(Echope);
        Echope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Echope",5,1);
            }
        });
        ContainerListe.addView(Echope);

        Button Magasin = new Button(this);
        Magasin.setBackgroundResource(R.drawable.magasin);
        SetBatCreer(Magasin);
        Magasin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Magasin",5,4);
            }
        });
        ContainerListe.addView(Magasin);

        Button Marche = new Button(this);
        Marche.setBackgroundResource(R.drawable.marche);
        SetBatCreer(Marche);
        Marche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Marche",5,8);
            }
        });
        ContainerListe.addView(Marche);

        ContainerTot.addView(ContainerListe);
        ///Minage
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Minage =new TextView(this);
        Minage.setText("Minage");
        SetTextCreer(Minage);
        ContainerTot.addView(Minage);

        Button Mine = new Button(this);
        Mine.setBackgroundResource(R.drawable.mine);
        SetBatCreer(Mine);
        Mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Mine",6,1);
            }
        });
        ContainerListe.addView(Mine);

        Button Carriere = new Button(this);
        Carriere.setBackgroundResource(R.drawable.cariere);
        SetBatCreer(Carriere);
        Carriere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Carriere",6,4);
            }
        });
        ContainerListe.addView(Carriere);

        ContainerTot.addView(ContainerListe);
        ///Enchantement
        ContainerListe = new LinearLayout(this);
        ContainerListe.setOrientation(LinearLayout.HORIZONTAL);

        TextView Enchantement =new TextView(this);
        Enchantement.setText("Enchantement");
        SetTextCreer(Enchantement);
        ContainerTot.addView(Enchantement);

        Button Enchanteur = new Button(this);
        Enchanteur.setBackgroundResource(R.drawable.enchanteur);
        SetBatCreer(Enchanteur);
        Enchanteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBatYEs("Enchanteur",7,1);
            }
        });
        ContainerListe.addView(Enchanteur);

        ContainerTot.addView(ContainerListe);
        ListCreat.removeAllViews();
        ListCreat.addView(ContainerTot);

    }
    public void SetBatCreer(TextView bat)
    {
        bat.setHeight(350);
        bat.setWidth(400);
        bat.setTextColor(Color.rgb(86,87,87));
    }
    public void SetTextCreer(TextView Titre)
    {
        Titre.setHeight(80);
        Titre.setTextSize(18);
        Titre.setGravity(Gravity.CENTER);
        Titre.setTextColor(Color.rgb(219,223,223));
    }
    public void CreateBatYEs(String Bat_nom,int type,int Lvl) ///+, Sprite
    {
        ///afficher section
        ListCreat.setVisibility(View.GONE);
        btn_retour_creat.setVisibility(View.GONE);
        Batiment newBat = Faux_bat(type,Lvl);


        NomBat_end.setText(Bat_nom);
        NomBat_end.setVisibility(View.VISIBLE);
        ////set Sprite
        Sprite_bat_crea_end.setVisibility(View.VISIBLE);
        Gen_prod_end(newBat);
        Prod_bat_crea_end.setVisibility(View.VISIBLE);

        Back_crea_end.setVisibility(View.VISIBLE);
        Verif_end_Ress(newBat,invRess);
        Yes_crea_end.setVisibility(View.VISIBLE);
        Titre_prod_crea_end.setVisibility(View.VISIBLE);

    }

    public void Gen_prod_end(Batiment bat)
    {
        LinearLayout Container = new LinearLayout(this);
        Container.setOrientation(LinearLayout.VERTICAL);
        ArrayList prodSem = bat.ProdSem;
        Ressource Ress = new Ressource();

        for (int Compteur=0; Compteur<prodSem.size();Compteur++)
        {
            TextView Prod = new TextView(this);
            Ress= (Ressource) prodSem.get(Compteur);
            Prod.setText(""+Ress.quantite+" "+Ress.name+" par semaine");
            Prod.setTextColor(Color.rgb(219,223,223));
            Container.addView(Prod);
        }
        Prod_bat_crea_end.removeAllViews();
        Prod_bat_crea_end.addView(Container);
    }
    public void Verif_end_Ress(final Batiment bat_actu, ArrayList invress)    ////affiche si possible de LvlUp avec ressource actu
    {
        int LvlBat = bat_actu.lvl;
        int typebat= bat_actu.type;

        boolean up_ok = true;
        boolean Find=false;
        String Manq ="Ressources manquantes:";
        Ressource ressourceActu= new Ressource();
        Ressource ressourceInInv= new Ressource();
        final ArrayList Bat_requis=Upressources(typebat,LvlBat);
        for(int Compteur_Bat=0; Compteur_Bat< Bat_requis.size();Compteur_Bat++)
        {
            ressourceActu= (Ressource) Bat_requis.get(Compteur_Bat);
            for (int Compteur_inv =0; Compteur_inv< invRess.size();Compteur_inv++)
            {
                ressourceInInv= (Ressource) invRess.get(Compteur_inv);
                if(ressourceActu.name == ressourceInInv.name)
                {
                    if(ressourceActu.quantite>ressourceInInv.quantite)
                    {
                        up_ok=false;
                        Manq = Manq + " "+ (ressourceActu.quantite-ressourceInInv.quantite) + " " + ressourceActu.name;
                    }
                    Find=true;
                }
            }
            if(Find==false)
            {
                Manq = Manq + " " + ressourceActu.quantite + " " + ressourceActu.name;
                up_ok=false;
                Find=true;
            }
        }
        if(up_ok==false)
        {
            Yes_crea_end.setBackgroundResource(R.drawable.btn_yes_radius_no);
            Yes_crea_end.setText(Manq);
            Yes_crea_end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
        else
        {
            Yes_crea_end.setBackgroundResource(R.drawable.btn_yes_radius_yes);
            Yes_crea_end.setText("Creer");
            Yes_crea_end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddenewBat(bat_actu);
                    EnleverRess(Bat_requis,invRess);
                }
            });

        }
    }
    public void AddenewBat(Batiment newBat)
    {
        vill.listBat.add(newBat);
        LiaisonCode_affichBat();
        SetNormal();
        Generation_village();
        GenerationListeCrea();
        VerifLvl(vill.Lvl);
        LaunchBase();
    }


    public ArrayList Upressources(int Type, int Lvl)
    {
        ArrayList List_ressources_req = new ArrayList();
        if(Type==1)
        {
            List_ressources_req=UpTavern(Lvl);
        }
        else if(Type==2)
        {
            List_ressources_req=UpForge(Lvl);
        }
        else if(Type==3)
        {
            List_ressources_req=UpBucheron(Lvl);
        }
        else if(Type==4)
        {
            List_ressources_req=UpDefense(Lvl);
        }
        else if(Type==5)
        {
            List_ressources_req=UpMagasin(Lvl);
        }
        else if(Type==6)
        {
            List_ressources_req=UpMine(Lvl);
        }
        else if(Type==7)
        {
            List_ressources_req=UpEnchanteur(Lvl);
        }

        return List_ressources_req;
    }
    ///verif des ressources nécessaire à evolution
    public ArrayList UpTavern(int Lvl)
    {
        ArrayList listeRess = new ArrayList();
        listeRess.add(FausseRessource("Bois",(int)(25+Math.pow(5,(Lvl/2)))));
        listeRess.add(FausseRessource("pierre",(int)(5+Math.pow(3,(Lvl/2)))));

        return listeRess;
    }
    public ArrayList UpForge(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(FausseRessource("Bois",(int)(12+Math.pow(3,(Lvl/2)))));
        listeRess.add(FausseRessource("pierre",(int)(3+Math.pow(2,(Lvl/2)))));
        listeRess.add(FausseRessource("fer",(int)(1+Math.pow(1.2,(Lvl/2)))));

        return listeRess;
    }
    public ArrayList UpBucheron(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(FausseRessource("Bois",(int)(12+Math.pow(2,(Lvl/2)))));

        return listeRess;
    }
    public ArrayList UpDefense(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(FausseRessource("Bois",(int)(30+Math.pow(4,(Lvl/2)))));
        listeRess.add(FausseRessource("pierre",(int)(3+Math.pow(3,(Lvl/2)))));

        return listeRess;
    }
    public ArrayList UpMagasin(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(FausseRessource("Bois",(int)(8+Math.pow(2.5,(Lvl/2)))));
        listeRess.add(FausseRessource("tissus",(int)(1+Math.pow(1.5,(Lvl/2)))));
        listeRess.add(FausseRessource("pierre",(int)(1+Math.pow(2,(Lvl/2)))));

        return listeRess;
    }
    public ArrayList UpMine(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(FausseRessource("Bois",(int)(18+Math.pow(3.5,(Lvl/2)))));
        listeRess.add(FausseRessource("pierre",(int)(14+Math.pow(4,(Lvl/2)))));

        return listeRess;
    }
    public ArrayList UpEnchanteur(int Lvl)
    {
        ArrayList listeRess= new ArrayList();
        listeRess.add(FausseRessource("Bois",(int)(15+Math.pow(3.5,(Lvl/2)))));
        listeRess.add(FausseRessource("pierre",(int)(25+Math.pow(2,(Lvl/2)))));
        listeRess.add(FausseRessource("Crystal blanc",(int)(1+Math.pow(4,(Lvl/2)))));

        return listeRess;
    }



    public void SetNormal()
    {
        VilNom.setVisibility(View.VISIBLE);
        VilLvl.setVisibility(View.VISIBLE);
        UpgVil.setVisibility(View.VISIBLE);

        bnt_bat0.setVisibility(View.VISIBLE);
        bnt_bat1.setVisibility(View.VISIBLE);
        bnt_bat2.setVisibility(View.VISIBLE);
        bnt_bat3.setVisibility(View.VISIBLE);
        bnt_bat4.setVisibility(View.VISIBLE);

        btn_quit.setVisibility(View.VISIBLE);
        if(ActuPage==0)
        {
            btn_prec.setVisibility(View.GONE);
        }
        else
        {
            btn_prec.setVisibility(View.VISIBLE);
        }
        if (ActuPage==3)
        {
            btn_suiv.setVisibility(View.GONE);
        }
        else
        {
            btn_suiv.setVisibility(View.VISIBLE);
        }

        SpriteBat.setVisibility(View.GONE);
        Nom_bat.setVisibility(View.GONE);
        Scroll_prod_bat.setVisibility(View.GONE);
        btn_up_bat.setVisibility(View.GONE);
        text_nec_bat.setVisibility(View.GONE);
        btn_back.setVisibility(View.GONE);
        Text_prod_titre_up.setVisibility(View.GONE);

        ListCreat.setVisibility(View.GONE);
        btn_retour_creat.setVisibility(View.GONE);

        NomBat_end.setVisibility(View.GONE);
        Sprite_bat_crea_end.setVisibility(View.GONE);
        Prod_bat_crea_end.setVisibility(View.GONE);
        Back_crea_end.setVisibility(View.GONE);
        Yes_crea_end.setVisibility(View.GONE);
        Titre_prod_crea_end.setVisibility(View.GONE);
    }

    public void EnleverRess(ArrayList RessDemandee, ArrayList RessPossede)
    {
        Ressource ressourceActu= new Ressource();
        Ressource ressourceInInv= new Ressource();
        for(int Compteur_Bat=0; Compteur_Bat< RessDemandee.size();Compteur_Bat++)
        {
            ressourceActu= (Ressource) RessDemandee.get(Compteur_Bat);
            for (int Compteur_inv =0; Compteur_inv< invRess.size();Compteur_inv++)
            {
                ressourceInInv= (Ressource) invRess.get(Compteur_inv);
                if(ressourceActu.name == ressourceInInv.name)
                {
                    ressourceInInv.quantite = ressourceInInv.quantite - ressourceActu.quantite;
                }
            }
        }
    }

    public void AffichBtn()
    {
        bnt_bat0.setVisibility(View.VISIBLE);
        bnt_bat1.setVisibility(View.VISIBLE);
        bnt_bat2.setVisibility(View.VISIBLE);
        bnt_bat3.setVisibility(View.VISIBLE);
        bnt_bat4.setVisibility(View.VISIBLE);
    }
    //////////simulation Db
    public Village FauxVillage(String Name, int LVL, int pv)
    {
        Village vill = new Village();
        vill.nom=Name;
        vill.Lvl=LVL;
        vill.PV=pv;
        vill.listBat=FausseListBat();

        return vill;
    }
    class Village
    {
        public void LoadVillage()
        {

        }
        public void SaveVillage()
        {

        }
        String nom;
        int Lvl;
        int PV;
        ArrayList listBat;
    }
    public ArrayList FausseListBat()
    {
        ArrayList ListBat = new ArrayList();
        ListBat.add(Faux_bat(1,1));

        return  ListBat;
    }
    public Batiment Faux_bat(int Type, int LVL)
    {
        Batiment newBat = new Batiment();
        newBat.type= Type;
        newBat.lvl = LVL;
        newBat.ProdSem = FausseListProd();
        newBat.nom=SetNameBat(newBat);
        newBat.image = SetImage(newBat);

        return newBat;
    }
    class Batiment
    {
        public void LoadBatiment()
        {

        }
        public void SaveBatiment()
        {

        }
        int type;
        int lvl;
        int image;
        String nom;
        ArrayList ProdSem;
    }
    public ArrayList FausseListProd()
    {
        ArrayList ListeProd = new ArrayList();
        ListeProd.add(FausseRessource("Or",1));

        return ListeProd;
    }
    public Ressource FausseRessource(String name,int Quant)
    {
        Ressource newRessource = new Ressource();
        newRessource.name=name;
        newRessource.quantite=Quant;

        return newRessource;
    }
    class Ressource
    {
        public void LoadRessource()
        {

        }
        public void SaveRessource()
        {

        }
        String name;
        int quantite;
    }

    public ArrayList FauxInvRes()
    {
        ArrayList InvRes = new ArrayList();
        InvRes.add(FausseRessource("Or",4));
        InvRes.add(FausseRessource("Bois",115));

        return InvRes;
    }


}
