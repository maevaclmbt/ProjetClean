/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetclean6;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Produit {
    private int id;
    private String ref;
    private String des;
    // ------------------------
    private List<Produit_operation_c> operations = new ArrayList<>();//
    // ------------------------

    public Produit(int id, String ref, String des) {
        this.id = id;
        this.ref = ref;
        this.des = des;
    }

    public void sauvegarde(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO produit (id, ref, des) VALUES (?, ?, ?)")) {
            st.setInt(1, this.getId());
            st.setString(2, this.getRef());
            st.setString(3, this.getDes());
            st.executeUpdate();
        }
    }
    
    
    // ------------------------
    public void ajouterOperation(TypeOperation operation, int ordreExecution) { //
        Produit_operation_c lien = new Produit_operation_c(this.id, operation.getId(), ordreExecution); //
        this.operations.add(lien); //
    } //

    public List<Produit_operation_c> getOperations() { //
        return operations; //
    } //
    
  // ------------------------
    
    
    @Override
    public String toString() {
        return "produit{" +
                "id=" + id +
                ", idoperation='" + ref + '\'' +
                ", des='" + des + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
