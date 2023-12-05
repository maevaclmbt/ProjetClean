/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetclean6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author maeva
 */
public class TypeOperation {
    private int id;
    private String des;
    
             
         public TypeOperation (int id,  String des){
             this.id=id;
             this.des=des;
             
         }
         
    public void sauvegarde(Connection conn) throws SQLException {
    try (PreparedStatement st = conn.prepareStatement(
            "insert into typeoperation (des) values (?)", Statement.RETURN_GENERATED_KEYS)) {
        st.setString(1, this.getDes());
        st.executeUpdate();

        // Récupérer la clé générée automatiquement (id)
        try (ResultSet generatedKeys = st.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1); // Mettre à jour l'ID dans l'objet Java
            } else {
                throw new SQLException("Échec de la récupération de l'ID généré.");
            }
        }
    }
}

                @Override
         public String toString() {
             return "typeoperation{" +
                "id=" + id +
                ", des='" + des + '\'' +
                '}';
         }
    /**
     * @return the id
     */
    public int getId() {
        return id;
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

       
}
