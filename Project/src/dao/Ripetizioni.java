package dao;

import java.sql.Time;


public class Ripetizioni {
    private int id_rip;
    private String stato;
    private int giorno;
    private int ora_i;
    private int ora_f;
    private String corso;
    private int id_docente;
    private String username;


    public Ripetizioni(int id_rip, String stato, int giorno, int ora_i, int ora_f, String Corso, int id_docente, String username) {
        this.id_rip = id_rip;
        this.stato = stato;
        this.giorno = giorno;
        this.ora_i = ora_i;
        this.ora_f = ora_f;
        this.corso = corso;
        this.id_docente = id_docente;
        this.username = username;
    }

    public int getId_rip() { return id_rip; }

    public void setId_rip(int id_rip) { this.id_rip = id_rip; }

    public String getStato() { return stato; }

    public void setStato(String stato) { this.stato = stato; }

    public void setGiorno(int giorno) { this.giorno = giorno; }

    public int getOra_i() { return ora_i; }

    public void setOra_i(int ora_i) { this.ora_i = ora_i; }

    public int getOra_f() { return ora_f; }

    public void setOra_f(int ora_f) { this.ora_f = ora_f; }

    public String getCorso() { return corso; }

    public void setCorso(String corso) { this.corso = corso; }

    public int getId_docente() { return id_docente; }

    public void setId_docente(int id_docente) { this.id_docente = id_docente; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getGiorno() { return giorno; }


    public String toString() {
        return id_rip + " " + giorno+ " " + ora_i + " " + ora_f+" "+ corso+ " "+ id_docente;
    }
}