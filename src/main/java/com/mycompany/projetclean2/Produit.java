package com.mycompany.projetclean2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product {
    private int id;
    private String ref;
    private String des;

    public Product(int id, String ref, String des) {
        this.id = id;
        this.ref = ref;
        this.des = des;
    }

    public void sauvegarde(Connection conn) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO product (id, ref, des) VALUES (?, ?, ?)")) {
            st.setInt(1, this.getId());
            st.setString(2, this.getRef());
            st.setString(3, this.getDes());
            st.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
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
