/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetclean2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author maeva
 */
public class ProjetClean2 {
    
    private Connection conn;
    public ProjetClean2(Connection conn) {
        this.conn = conn;
    }
 
    public static void debut() {
    try (Connection con = connectSurServeurM3()) {
        System.out.println("connecté");
        creeSchema(con);
    } catch (SQLException ex) {
        throw new Error("Connection impossible", ex);
    }
}
 
    
      public static Connection connectSurServeurM3() throws SQLException { 
        return connectGeneralMySQL("92.222.25.165", 3306, //c'est la qu'on forunit les infos pour se connecter (num serveur, num de port,...)
                "m3_mcolombet01", "m3_mcolombet01", // ici il faut modifier à chaque fois que lquelq'un d'autre utilise avec les bonnes données
                "53fe85dc");
    }
      
         public static Connection connectGeneralMySQL(String host,  //fonctionne avec tous les serveurs : si le serveur changent on ne doit pas changer cette partie, elle est universelle
            int port, String database,
            String user, String pass)
            throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port
                + "/" + database,
                user, pass);
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }
         
         
        public static void creeSchema(Connection conn) throws SQLException { // si erreur provoquée lance exeption type sql + void = renvoit rien
        conn.setAutoCommit(false); // commit = valider modif table, si pas false, chaque enregistrement est ajoutée automatiquement, cette commande force l'arret de la fonctionnalité = enregistrer les info de la nouvelle table 
        /*try (Statement st = conn.createStatement()){ //en cas d'erreur
            st.executeUpdate(
                "create table machine (\n"
                        + "    id integer not null primary key AUTO_INCREMENT,\n"
                        + "    ref varchar(15) not null,\n"
                        + "    des text not null,\n" // Ajout de la virgule manquante ici
                        + "    puissance double not null\n"
                        + ")\n"
        );
            st.executeUpdate(
                    "create table toutes_les_opération (\n"
                    + "    id integer not null primary key AUTO_INCREMENT,\n"
                    + "    idtype integer not null\n"
                    + "    idproduit integer not null\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table typeoperation (\n"
                    + "    id integer not null primary key AUTO_INCREMENT,\n"
                    + "    des varchar(30) not null\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table realise (\n"
                    + "    idMachine integer not null,\n"
                    + "    idType integer not null\n"
                    + "    durée integer not null\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table produit (\n"
                    + "    u1 integer not null,\n"
                    + "    u2 integer not null\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "alter table toutes_les_opération \n"
                    + "    add constraint fk_produit_idproduit \n"
                    + "    foreign key (idproduit) references produit(id) \n"
            );
            st.executeUpdate(
                    "alter table toutes_les_opération \n"
                    + "    add constraint fk_typeoperation_idtype \n"
                    + "    foreign key (idtype) references typeoperation(id) \n" // normal de rajouter foreign key si deja mise dans la creation de la table ?
            );
            st.executeUpdate( ///!\ à faire pendant le prochain TP 
                    "alter table realise \n"
                    + "    add constraint fk_realise_idmachines \n"
                    + "    foreign key (idmachines) references realise(id) \n"
            );
            st.executeUpdate( ///!\ à faire pendant le prochain TP 
                    "alter table realise \n"
                    + "    add constraint fk_realise_idtype \n"
                    + "    foreign key (idtype) references realise(id) \n"
            );
            
            conn.commit();
        } catch (SQLException ex){ // en cas d'erreur ici 
            conn.rollback();
        throw ex;
    } finally {
            conn.setAutoCommit (true); //tru = laisse l'ordi enregistrer 
        }*/
    } 
        
        
        
         public static void deleteSchema(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement()) {
            // pour être sûr de pouvoir supprimer, il faut d'abord supprimer les liens
            // puis les tables
            // suppression des liens
            try {
                st.executeUpdate("alter table realise drop constraint fk_typeoperation_idtype");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate("alter table type operation drop constraint fk_produit_idproduit");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("alter table realise drop constraint fk_realise_idmachines");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate("alter table type operation drop constraint fk_realise_idtype");
            } catch (SQLException ex) {
            }
            // je peux maintenant supprimer les tables
            try {
                st.executeUpdate("drop table machine");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table toutes_les_opération");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table produit");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table typeoperation");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table realise");
            } catch (SQLException ex) {
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("execution du projet");
        debut();
    }
    
    
    
    
    
}
