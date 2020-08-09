package model;

import dao.*;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class Model {

    private static String URL;
    private static String username;
    private static String password;

    public Model(String URL, String Username, String Password) {
        this.URL = URL;
        this.username = Username;
        this.password = Password;
        registerDriver();
    }

    public static void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    /**
     * UTENTI
     **/

    public static Utenti autenticazione(String username, String password) {
        Connection conn1 = null;
        Utenti u = null;
        try {
            conn1 = openConnection();

            username = "\"" + username + "\"";
            password = "\"" + password + "\"";
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `utenti` \n" +
                    "WHERE utenti.username = " + username + " AND utenti.password = " + password + ";");
            if (rs.next()) {
                u = new Utenti(rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getBoolean("Admin"));
            }
        } catch (SQLException e) {
            System.out.println("Error communicating with the database: " + e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return u;
    }

    // info degli utenti
    public static ArrayList<Utenti> getUtenti() {
        Connection conn1 = null;
        ArrayList<Utenti> out = new ArrayList<>();
        try {
            conn1 = openConnection();

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM utenti");
            while (rs.next()) {
                Utenti u = new Utenti(rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("admin"));
                out.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally{
            closeConnection(conn1);
        }
        return out;
    }

    //Inserisco Utente nel DB
    public static boolean InserisciUt(Utenti utenti) {
        Connection conn1 = null;
        try {
            conn1 = openConnection();

            String Username = "\"" + utenti.getUsername() + "\"";
            String Password = "\"" + utenti.getPassword() + "\"";
            Statement st = conn1.createStatement();
            /*ResultSet rs = */
            st.executeQuery("INSERT INTO Utenti(Username, Password, Ruolo, id_utente)VALUES (" + Username + "," + Password + "," + Ruolo + "," + id_utente + ")");
            System.out.println("L'utente: " + Username + " é stato inserito ");
            return true;
        } catch (SQLException e) {
            System.out.println("Error communicating with the database: " + e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return false;
    }

    //Rimuovo utente dal DB
    public static boolean RimuovereUt(Utenti utenti) {
        Connection conn1 = null;
        try {
            conn1 = openConnection();

            String Username = "'" + utenti.getUsername() + "'";
            String Password = "'" + utenti.getPassword() + "'";
            System.out.println("L'utente da rimuovere é: " + Username + " " + Password);
            Statement st = conn1.createStatement();
            /*ResultSet rs =*/
            st.executeQuery("DELETE FROM utenti WHERE utenti.username=" + Username + "AND utenti.password= " + Password + "AND utenti.Ruolo= " + Ruolo + "AND utenti.id_utenti= " + id_utente + "");
            System.out.println("L'utente: " + Username + " é stato rimosso ");
            return true;
        } catch (SQLException e) {
            System.out.println("Error communicating with the database: " + e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return false;
    }

    /**
     * DOCENTI
     **/

    public static ArrayList<Docenti> getDocenti() {
        Connection conn1 = null;
        ArrayList<Docenti> doc = new ArrayList<>();
        try {
            conn1 = openConnection();

            Statement st1 = conn1.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT * FROM docenti");
            while (rs1.next()) {
                Docenti d = new Docenti(rs1.getString("Nome"), rs1.getString("Cognome"), rs1.getInt("id_docente"));
                doc.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return doc;
    }
    //Inseriamo nuovi docenti

    public static boolean InserisciDocenti(Docenti docenti) {
        Connection conn1 = null;
        try {
            conn1 = openConnection();

            String Nome = "\"" + docenti.getNome() + "\"";
            String Cognome = "\"" + docenti.getCognome() + "\"";
            String id_docente = "\"" + docenti.getId_docente() + "\"";
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM docenti");
            st.executeQuery("INSERT INTO Docenti (Nome,Cognome) VALUE (" + Nome + "," + Cognome + "," + id_docente + ")");
            System.out.println("Docente: " + Nome + " " + Cognome + " aggiunto nel DB");
            return true;
        } catch (SQLException e) {
            System.out.println("Error communicating with the database: " + e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return false;
    }

    //Rimuovo Docenti

    public static boolean RimuovereDoc(Docenti docenti) {
        Connection conn1 = null;
        try {
            conn1 = openConnection();

            String Nome = "'" + docenti.getNome() + "'";
            String Cognome = "'" + docenti.getCognome() + "'";
            String id_docente = "'" + docenti.getId_docente() + "'";
            System.out.println("Il docente da rimuovere é: " + Nome + " " + Cognome);
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM docenti ");
            st.executeUpdate("DELETE FROM docenti WHERE docenti.Nome=" + Nome + "AND docenti.Cognome= " + Cognome + "AND docenti.id_docente= " + id_docente + "");
            return true;
        } catch (SQLException e) {
            System.out.println("Error communicating with the database: " + e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return false;
    }

    public static ArrayList<Docenti> DisponibilitàDoc(String id_rip) throws SQLException {
        Connection conn1 = null;
        ArrayList<Docenti> docenti = new ArrayList<>();
        try {
            conn1 = openConnection();

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT docenti.id_docente FROM docenti join  ripetizioni.id_docente WHERE docenti.id_docente = ripetizioni.id_docente and storico.stato = 'attiva' " + id_rip);
            while (rs.next()) {
                int id_docente = rs.getInt("id_docente");
                String Nome = rs.getString("Nome");
                String Cognome = rs.getString("Cognome");
                Docenti d = new Docenti(Nome, Cognome, id_docente);
                docenti.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error communicating with the database: " + e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return docenti;
    }

    /**
     * Corsi
     **/
    public static ArrayList<Corso> getCorsi() {
        Connection conn1 = null;
        ArrayList<Corso> out2 = new ArrayList<>();
        try {
            conn1 = openConnection();

            Statement st2 = conn1.createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT * FROM corsi");
            while (rs2.next()) {
                Corso c = new Corso(rs2.getString("titolo"),
                        rs2.getInteger("id_docente"));
                out2.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return out2;
    }

    //Aggiungo corsi nel DB
    public static boolean InserisciCorso(Corso corso) {
        Connection conn1 = null;
        try {
            conn1 = openConnection();

            String Titolo = "\"" + corso.getTitolo() + "\"";
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT Corso.Titolo " +
                    "FROM `corsi` " +
                    "WHERE corso.Titolo = " + Titolo + "");
            if (rs.isBeforeFirst()) {
                System.out.println("Già presente nel DB");
                return false;
            }
            st.executeUpdate("INSERT INTO Corsi (Titolo) VALUE (" + Titolo + ")");
            System.out.println("Corso: " + corso.getTitolo() + "é stato aggiunto nel DB.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error communicating with the database: " + e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return false;
    }

    public static boolean RimuoviCorso(Corso corso) {
        Connection conn1 = null;
        try {
            conn1 = openConnection();

            String Titolo = "'" + corso.getTitolo() + "'";
            System.out.println("Il corso da rimuovere é: " + Titolo);
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT `corso.titolo`  FROM `corsi` " +
                    "WHERE `corsi.titolo` = " + Titolo + "");
            if (!rs.isBeforeFirst()) {
                System.out.println("Non é presente nel DB.");
                return false;
            }
            st.executeUpdate("DELETE FROM corsi WHERE corsi.Titolo=" + Titolo + "");
            System.out.println("Corso: " + Titolo + "rimosso.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error communicating with the database: " + e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return false;
    }

    /**
     * Ripetizioni
     **/
    public static ArrayList<Ripetizioni> getRipetizioni() {
        Connection conn1 = null;
        ArrayList<Ripetizioni> out3 = new ArrayList<>();
        try {
            conn1 = openConnection();

            Statement st3 = conn1.createStatement();
            ResultSet rs3 = st3.executeQuery("SELECT * FROM ripetizioni");
            while (rs3.next()) {
                Ripetizioni r = new Ripetizioni(rs3.getInt("id_rip"), rs3.getrs3.getInt("Giorno"), rs3.getTime("Ora_i"), rs3.getTime("Ora_f"), rs3.getString("Corso"), rs3.getInt("Id_docente"));
                out3.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return out3;
    }

    // Visualizzo la disponibilità delle ripetizioni

    public static ArrayList<Ripetizioni> DisponibilitàRip() {
        Connection conn1 = null;
        ArrayList<Ripetizioni> ripetizioni = new ArrayList<>();
        try {
            conn1 = openConnection();

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT ID_rip FROM ripetizioni JOIN storico ON ripetizioni.ID_rip=storico.ID_rip AND storico.stato=NULL ");
            while (rs.next()) {
                if (rs.wasNull()) {
                    Ripetizioni r = new Ripetizioni(rs.getInt("id_rip"), rs.getInt("Giorno"), rs.getTime("Ora_i"), rs.getTime("Ora_f"), rs.getString("Corso"), rs.getInt("Id_docente"));
                    ripetizioni.add(r);
                } else {
                    System.out.println("Non abbiamo ripetizioni disponibili");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error communicating with the database: " + e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return ripetizioni;
    }


    /**
     * Storico
     **/

    /*
    public static ArrayList<Storico> queryDB5() {
        Connection conn1 = null;
        ArrayList<Storico> out4 = new ArrayList<>();
        try {
            conn1 = openConnection();

            Statement st4 = conn1.createStatement();
            ResultSet rs4 = st4.executeQuery("SELECT * FROM storico");
            while (rs4.next()) {
                Storico s = new Storico(rs4.getInt("ID_rip"), rs4.getString("Stato"), rs4.getInt("id_utente"));
                out4.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return out4;
    }

    //modifica stato prenotazione

    public static Storico update(int ID_rip, String Stato, int id_utente) {
        Connection conn1 = null;
        Storico storico = null;
        try {
            conn1 = openConnection();


            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Storico");
            st.executeUpdate("UPDATE Storico"
                    + "SET Stato=?"
                    + "WHERE Id_rip = ?");
            if(rs.next()){
                storico=new Storico(rs.getInt("id_rip"), rs.getString("Stato"), rs.getInt("id_utente"));
            }
        } catch (SQLException e) {
            System.out.println("Error communicating with the database: " + e.getMessage());
        } finally {
            if (conn1 != null) {
                closeConnection(conn1);
            }
        }
        return storico;
    }
    */

    /**
     * Connection
     **/

    private static Connection openConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, username, password);
            if (conn != null) {
                System.out.println("Connected to the database test");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static void closeConnection(Connection conn){
        try{
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}