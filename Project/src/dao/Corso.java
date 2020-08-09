package dao;

public class Corso {
    private String titolo;
    private Integer id_docente;


    public Corso(String titolo, Integer id_docente) {
        this.titolo = titolo;
        this.id_docente = id_docente;
    }

    public String getTitolo() { return titolo; }

    public Integer getId_docente() { return id_docente; }


    public void setTitolo(String titolo) { this.titolo = titolo; }


    public void setId_docente(Integer id_docente) { this.id_docente = id_docente; }


    @Override
    public String toString() { return"corso: " + this.titolo;}
}

