package com.mycompany.projetclean6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Realise_c {
    private int idMachine;
    private int idType;
    private int duree;

    public Realise_c(int idMachine, int idType, int duree) {
        this.idMachine = idMachine;
        this.idType = idType;
        this.duree = duree;
    }

    public void sauvegarde(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO realise (idmachine, idtype, duree) VALUES (?, ?, ?)")) {
            st.setInt(1, this.getIdMachine());
            st.setInt(2, this.getIdType());
            st.setInt(3, this.getDuree());
            st.executeUpdate();
        }
    }

    // Getters
    public int getIdMachine() {
        return idMachine;
    }

    public int getIdType() {
        return idType;
    }

    public int getDuree() {
        return duree;
    }

    // Setters
    public void setIdMachine(int idMachine) {
        this.idMachine = idMachine;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Realise{" +
                "idMachine=" + idMachine +
                ", idType=" + idType +
                ", duree=" + duree +
                '}';
    }
}
