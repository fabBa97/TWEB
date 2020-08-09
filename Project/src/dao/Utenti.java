package dao;

public class Utenti {
    private String username;
    private String password;
    private boolean isAdmin;

    public Utenti(int id_utente, String username, String password, String ruolo) {

        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Boolean isIsAdmin() {
        return isAdmin;
    }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    public String toString() {
        return id_utente + " " + username + " " + password + " " + ruolo;
    }
}
