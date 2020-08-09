package model;

import dao.*;
import model.Model;

import java.util.ArrayList;

public class MySQL {
    public static void main(String[] args) {
        Model.registerDriver();
        ArrayList<Utenti> utenti = Model.queryDB1();
        for (Utenti u: utenti)
            System.out.println(u);
        System.out.println("FINE");

        ArrayList<Docenti> docenti = Model.queryDB2();
        for (Docenti d: docenti)
            System.out.println(d);
        System.out.println("FINE");

        ArrayList<Corso> corso = Model.queryDB3();
        for (Corso c: corso)
            System.out.println(c);
        System.out.println("FINE");

        ArrayList<Ripetizioni> ripetizioni = Model.queryDB4();
        for (Ripetizioni r: ripetizioni)
            System.out.println(r);
        System.out.println("FINE");

        ArrayList<Storico> storico = Model.queryDB5();
        for (Storico s: storico)
            System.out.println(s);
        System.out.println("FINE");
    }
}
