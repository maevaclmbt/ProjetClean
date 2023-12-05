package com.mycompany.projetclean6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ToutesLesOperations_c {
    private int id;
    private int idoperationoperation;
    private int idProduit;

    public ToutesLesOperations_c(int id, int idoperationoperation, int idProduit) {
        this.id = id;
        this.idoperationoperation = idoperationoperation;
        this.idProduit = idProduit;
    }

    public void sauvegarde(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO produit_operation (id, idoperationoperation, idproduit) VALUES (?, ?, ?)")) {
            st.setInt(1, this.getId());
            st.setInt(2, this.getidoperationOperation());
            st.setInt(3, this.getIdProduit());
            st.executeUpdate();
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getidoperationOperation() {
        return idoperationoperation;
    }

    public int getIdProduit() {
        return idProduit;
    }


   

    public void SetidoperationOperation(int idoperationoperation) {
        this.idoperationoperation = idoperationoperation;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "ToutesLesOperations{" +
                "id=" + id +
                ", idoperation=" + idoperationoperation +
                ", idProduit=" + idProduit + '}';
    }
}
