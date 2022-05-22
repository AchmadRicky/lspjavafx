/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikacafe;

/**
 *
 * @author SAMSUNG
 */
public class data_user {
    Integer id_akun;
    String username,level,password;

    public data_user(Integer id_akun, String username, String level, String password) {
        this.id_akun = id_akun;
        this.username = username;
        this.level = level;
        this.password = password;
    }

    public Integer getId_akun() {
        return id_akun;
    }

    public void setId_akun(Integer id_akun) {
        this.id_akun = id_akun;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
}
