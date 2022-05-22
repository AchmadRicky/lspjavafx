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
public class session {
    private static String id_akun;
    private static String level;
    private static String username;
    
    public static String getId(){
    return id_akun;
    }
    public static void setGetId(String id_akun){
    session.id_akun=id_akun;
    }
     public static String getLevel(){
        return level;
    }
    public static void setGetLevel(String level){
        session.level = level;
    }
    public static String getUsername(){
        return username;
    }
    public static void setGetUsername(String username){
        session.username = username;
    }
}
