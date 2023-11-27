/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetclean6;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Produit {
    private int id;
    private String idtype;
    private String des;

    public Produit(int id, String idtype, String des) {
        this.id = id;
        this.idtype = idtype;
        this.des = des;
    }

    public void sauvegarde(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO produit (id, idtype, des) VALUES (?, ?, ?)")) {
            st.setInt(1, this.getId());
            st.setString(2, this.getIdtype());
            st.setString(3, this.getDes());
            st.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return "produit{" +
                "id=" + id +
                ", idtype='" + idtype + '\'' +
                ", des='" + des + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setRef(String ref) {
        this.idtype = ref;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
