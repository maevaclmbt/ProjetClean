package com.mycompany.projetclean6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ToutesLesOperations_c {
    private int id;
    private int idtypeoperation;
    private int idProduit;

    public ToutesLesOperations_c(int id, int idtypeoperation, int idProduit) {
        this.id = id;
        this.idtypeoperation = idtypeoperation;
        this.idProduit = idProduit;
    }

    public void sauvegarde(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO toutes_les_opérations (id, idtypeoperation, idproduit) VALUES (?, ?, ?)")) {
            st.setInt(1, this.getId());
            st.setInt(2, this.getIdTypeOperation());
            st.setInt(3, this.getIdProduit());
            st.executeUpdate();
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getIdTypeOperation() {
        return idtypeoperation;
    }

    public int getIdProduit() {
        return idProduit;
    }


   

    public void SetIdTypeOperation(int idtypeoperation) {
        this.idtypeoperation = idtypeoperation;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "ToutesLesOperations{" +
                "id=" + id +
                ", idType=" + idtypeoperation +
                ", idProduit=" + idProduit + '}';
    }
}
