/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetclean2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noemie
 */
public class Machine {

    private int id;
    private String reference;
    private String description;
    private double puissance;

    protected Machine(int id, String reference, String description, int puissance) {
        this.id = id;
        this.reference = reference;
        this.description = description;
    }


    public static List<Machine> toutesLesMachines(Connection con) throws SQLException {
        List<Machine> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id,reference,description, puissance from Machine")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String reference = rs.getString(2);
                    String description = rs.getString(3);
                    int puissance = rs.getInt(4);
                    res.add(new Machine(id, reference, description, puissance));
                }
            }
        }
        return res;
    }
        

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    
    public String getDescription() {
        return description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public Double getPuissance() {
        return puissance;
    }

   
    public void puissance(Double puissance) {
        this.puissance = puissance;
    }


}
