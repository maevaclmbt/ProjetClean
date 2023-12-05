package com.mycompany.projetclean6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Machine_operation_c {
    private int idMachine;
    private int idoperation;
    private int duree;

    public Machine_operation_c(int idMachine, int idoperation, int duree) {
        this.idMachine = idMachine;
        this.idoperation = idoperation;
        this.duree = duree;
    }

    public void sauvegarde(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO machine_operation (idmachine, idoperation, duree) VALUES (?, ?, ?)")) {
            st.setInt(1, this.getIdMachine());
            st.setInt(2, this.getidoperation());
            st.setInt(3, this.getDuree());
            st.executeUpdate();
        }
    }

    // Getters
    public int getIdMachine() {
        return idMachine;
    }

    public int getidoperation() {
        return idoperation;
    }

    public int getDuree() {
        return duree;
    }

    // Setters
    public void setIdMachine(int idMachine) {
        this.idMachine = idMachine;
    }

    public void setidoperation(int idoperation) {
        this.idoperation = idoperation;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "machine_operation{" +
                "idMachine=" + idMachine +
                ", idoperation=" + idoperation +
                ", duree=" + duree +
                '}';
    }
}
