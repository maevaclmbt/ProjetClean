/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetclean6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author maeva
 */
public class MachineMaeva {
    private int id;
    private String ref;
    private String des;
    private double puissance;
    
             
         public MachineMaeva (int id, String ref, String des, double puissance){
             this.id=id;
             this.ref=ref;
             this.des=des;
             this.puissance=puissance;
             
         }
         
         public void sauvegarde (Connection conn) throws SQLException{
             try (PreparedStatement st= conn.prepareStatement(
                     "insert into machine (id, ref, des, puissance) values (?,?,?,?)")){
                st.setInt(1, this.getId());
                st.setString(2, this.getRef());
                st.setString(3, this.getDes());
                st.setDouble(4, this.getPuissance());
                st.executeUpdate();
             }                 
         }
                @Override
         public String toString() {
             return "Machine{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", des='" + des + '\'' +
                ", puissance='" + puissance + '\'' +
                '}';
         }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the ref
     */
    public String getRef() {
        return ref;
    }

    /**
     * @param ref the ref to set
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * @return the des
     */
    public String getDes() {
        return des;
    }

    /**
     * @param des the des to set
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     * @return the puissance
     */
    public double getPuissance() {
        return puissance;
    }

    /**
     * @param puissance the puissance to set
     */
    public void setPuissance(double puissance) {
        this.puissance = puissance;
    }
            
}
