package com.example.projet_jormun;

import android.graphics.drawable.BitmapDrawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

class Hero implements Parcelable
{
    protected Hero(Parcel in) {
        Name = in.readString();
        description = in.readString();
        Classe1 = in.readString();
        Classe2 = in.readString();
        Image = in.readInt();
        pv = in.readInt();
        degathero = in.readInt();
        degatmaghero = in.readInt();
        armureHero = in.readInt();
        armuremagHero = in.readInt();
        esquivehero = in.readInt();
        heroarmure_plus = in.readInt();
        heroarmure_mult = in.readDouble();
        heroarmuremag_plus = in.readInt();
        heroarmuremag_mult = in.readDouble();
        herodegat_plus = in.readInt();
        herodegat_mult = in.readDouble();
        herodegatmag_plus = in.readInt();
        heordegatmag_mult = in.readDouble();
        heroesquiveplus = in.readInt();
        heroesquive_mult = in.readDouble();
        byte tmpTravel = in.readByte();
        Travel = tmpTravel == 0 ? null : tmpTravel == 1;
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    public Hero() {

    }

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
    int Image = R.drawable.hero_image_test;
    ArrayList Inv;
    ArrayList ObjetChoisis;
    ArrayList EquipChoisis;
    ArrayList SkillTotal;
    ArrayList SkillChoisis;
    int pv;
    int degathero;
    int degatmaghero;
    int armureHero;
    int armuremagHero;
    int esquivehero;
    int heroarmure_plus;
    double heroarmure_mult;
    int heroarmuremag_plus;
    double heroarmuremag_mult;
    int herodegat_plus;
    double herodegat_mult;
    int herodegatmag_plus;
    double heordegatmag_mult;
    int heroesquiveplus;
    double heroesquive_mult;
    Boolean Travel = false;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(description);
        dest.writeString(Classe1);
        dest.writeString(Classe2);
        dest.writeInt(Image);
        dest.writeInt(pv);
        dest.writeInt(degathero);
        dest.writeInt(degatmaghero);
        dest.writeInt(armureHero);
        dest.writeInt(armuremagHero);
        dest.writeInt(esquivehero);
        dest.writeInt(heroarmure_plus);
        dest.writeDouble(heroarmure_mult);
        dest.writeInt(heroarmuremag_plus);
        dest.writeDouble(heroarmuremag_mult);
        dest.writeInt(herodegat_plus);
        dest.writeDouble(herodegat_mult);
        dest.writeInt(herodegatmag_plus);
        dest.writeDouble(heordegatmag_mult);
        dest.writeInt(heroesquiveplus);
        dest.writeDouble(heroesquive_mult);
        dest.writeByte((byte) (Travel == null ? 0 : Travel ? 1 : 2));
    }
}