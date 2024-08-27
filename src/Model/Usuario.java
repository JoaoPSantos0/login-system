
package Model;

import java.util.Date;

public class Usuario {
    protected String name;
    protected Date dataNacmiento;
    protected String password;
    protected String email;
    protected int id;

    public Usuario( String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDataNacmiento() {
        return dataNacmiento;
    }

    public void setDataNacmiento(Date dataNacmiento) {
        this.dataNacmiento = dataNacmiento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
