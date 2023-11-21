/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetclean2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author maeva
 */
public class ProjetClean2 {
    
    public void menuUtilisateur2() throws SQLException {
        
        int rep = -1;
        
        while (rep != 0) {
            try (Statement st = this.conn.createStatement()) {
            int i = 1;
            System.out.println("Menu utilisateur");
            System.out.println("================");
            System.out.println((i++) + ") chercher une machine");
            System.out.println((i++) + ") ajouter une machine");
            System.out.println((i++) + ") lister les machines");
            System.out.println((i++) + ") créer une table");
            System.out.println("0) Fin");
            rep = NewClass_bdddiscussion.entreeEntier("Votre choix : ");
            try {
                int j = 1;
                if (rep == j++) {
                   System.out.println("quelle est l'id de la machine que vous cherchez ?");
                   int Uid=Lire.i();
                   ResultSet r = st.executeQuery("select * from machine where id="+ Uid+ ";"); // on peut mettre "select * from machine where id=2"
                   while (r.next()){
                       int id =r.getInt(1);
                       String ref =r.getString(2);
                       String des = r.getString(3);
                       int puissance =r.getInt(3);
                       System.out.println(id +" : "+ref+" : "+ des+" : "+ puissance);
                   }
                } else if (rep == j++) {
                    System.out.println("quelle est la ref de votre nouvelle machine ?");
                    String Uref=Lire.S();
                    System.out.println("quelle est la description de votre nouvelle machine ?");
                    String Udes=Lire.S();
                    System.out.println("quelle est la puissance de votre nouvelle machine ?");
                    int Upuissance=Lire.i();
                     st.executeUpdate(
                     "INSERT INTO `machine` (`id`, `ref`, `des`, `puissance`) VALUES (null, Uref, Udes, Upuissance);"); //VALUES (null, 'Uref ', 'Udes', 'Upuissance');
                } else if (rep == j++) {
                   // Lister les machines
                   ResultSet machines = st.executeQuery("SELECT * FROM machine");
                   while (machines.next()) {
                    int id = machines.getInt("id");
                    String ref = machines.getString("ref");
                    String des = machines.getString("des");
                    int puissance = machines.getInt("puissance");
                    System.out.println(id + " : " + ref + " : " + des + " : " + puissance);
                    }
                } else if (rep == j++) {
                    // Créer une table avec des colonnes définies par l'utilisateur

                    System.out.println("Combien de colonnes souhaitez-vous dans votre table ?");
                    int nombreColonnes = Lire.i();

                    StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS nouvelle_table (");

                    for (int colonne = 1; colonne <= nombreColonnes; colonne++) {
                        System.out.println("Entrez le nom de la colonne " + colonne + ": ");
                        String nomColonne = Lire.S();
        
                        System.out.println("Entrez le type de données pour la colonne " + nomColonne + " (ex: INT, VARCHAR(255), etc.) : ");
                        String typeColonne = Lire.S();
        
                        query.append(nomColonne).append(" ").append(typeColonne);
        
                        if (colonne != nombreColonnes) {
                            query.append(", ");
                        }
                    }
                    query.append(")");

                    st.executeUpdate(query.toString());
                    System.out.println("Table 'nouvelle_table' créée avec succès (ou déjà existante).");
                }
    
                
            } catch (SQLException ex) {
                System.out.println(ExceptionsUtils.messageEtPremiersAppelsDansPackage(ex, "fr.insa.beuvron", 5));
            }
        }
        }
      }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("on va dead ça");
        System.out.println("de fou");
    }
    
    
    
    
    
}
