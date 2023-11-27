/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetclean6;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Represents the OpavantOpapres class.
 * Contains variables "opavant" and "opapres".
 */
public class PrecedeOperation {
    private int opavant;
    private int opapres;

    // Constructor
    public PrecedeOperation(int opavant, int opapres) {
        this.opavant = opavant;
        this.opapres = opapres;
    }

    // Getter and Setter methods for opavant
    public int getOpavant() {
        return opavant;
    }

    public void setOpavant(int opavant) {
        this.opavant = opavant;
    }

    // Getter and Setter methods for opapres
    public int getOpapres() {
        return opapres;
    }

    public void setOpapres(int opapres) {
        this.opapres = opapres;
    }

    // Method to save OpavantOpapres object to the database
    public void sauvegarde(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "insert into precedence (Opavant, Opapres) values (?, ?)")) {
            st.setInt(1, this.getOpavant());
            st.setInt(2, this.getOpapres());
            st.executeUpdate();
        }
    }

    // Override toString method for better representation
    @Override
    public String toString() {
        return "OpavantOpapres{" +
                "opavant=" + opavant +
                ", opapres=" + opapres +
                '}';
    }
}
