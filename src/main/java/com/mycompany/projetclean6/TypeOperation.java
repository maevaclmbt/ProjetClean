package com.mycompany.projetclean6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TypeOperation {
    private int id;
    private String des;

    public TypeOperation(int id, String des) {
        this.id = id;
        this.des = des;
    }

    public void sauvegarde(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "insert into type_operation (id, des) values (?, ?)")) {
            st.setInt(1, this.getId());
            st.setString(2, this.getDes());
            st.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return "TypeOperation{" +
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
