/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetclean6;


import static com.mycompany.projetclean6.ProjetClean6.debut;
import com.mycompagny.exeptions.ExceptionsUtils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author maeva
 */
public class ProjetClean6 {
    
    private Connection conn;
    
    public ProjetClean6(Connection conn) {
        this.conn = conn;
    }
 

    
   public static void debut() {
    try  {
        Connection conn = connectSurServeurM3();
        System.out.println("Connecté");
        ProjetClean6 projetClean5Instance = new ProjetClean6(conn);
        //initialise(conn);
        System.out.println("Voulez-vous lancer le Menu machine ? ('oui' ou 'non')");
        String repMach = Lire.S();

        while (!repMach.equals("oui") && !repMach.equals("non")) {
            System.out.println("Veuillez entrer une réponse correcte : 'oui' ou 'non'");
            repMach = Lire.S();
        }

        if (repMach.equals("oui")) {
            
            projetClean5Instance.MenuMachine();
        }
        System.out.println("Voulez-vous lancer le Menu Produit ? ('oui' ou 'non')");
        String repProd = Lire.S();

        while (!repProd.equals("oui") && !repProd.equals("non")) {
            System.out.println("Veuillez entrer une réponse correcte : 'oui' ou 'non'");
            repProd = Lire.S();
        }

        if (repProd.equals("oui")) {
           //ProjetClean5 projetClean5Instance = new ProjetClean5(conn);
            projetClean5Instance.MenuProduit();
        }
        
        System.out.println("Voulez-vous lancer le Menu Operation ? ('oui' ou 'non')");
        String repTypeOp = Lire.S();

        while (!repTypeOp.equals("oui") && !repTypeOp.equals("non")) {
            System.out.println("Veuillez entrer une réponse correcte : 'oui' ou 'non'");
            repTypeOp = Lire.S();
        }

        if (repTypeOp.equals("oui")) {
           //ProjetClean5 projetClean5Instance = new ProjetClean5(conn);
            projetClean5Instance.MenuOperation();
        }


        
    } catch (SQLException ex) {
        throw new Error("Connexion impossible", ex);
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
        try (Statement st = conn.createStatement()){ //en cas d'erreur
            st.executeUpdate(
                "create table machine (\n"
                        + "    id integer not null primary key AUTO_INCREMENT,\n"
                        + "    ref varchar(15) not null,\n"
                        + "    des text not null,\n" // Ajout de la virgule manquante ici
                        + "    puissance double not null,\n"
                        + ")\n"
        );
            st.executeUpdate(
                    "create table toutes_les_opérations (\n"
                    + "    id integer not null primary key ,\n"
                    + "    idtypeoperation integer not null,\n"
                    + "    idproduit integer not null,\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table typeoperation (\n"
                    + "    id integer not null primary key AUTO_INCREMENT,\n"
                    + "    des varchar(30) not null,\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table realise (\n"
                    + "    idmachine integer not null,\n"
                    + "    idtype integer not null,\n"
                    + "    durée integer not null,\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table produit (\n"
                        + "    id integer primary key AUTO_INCREMENT ,\n"   //enelever primary key AUTO_INCREMENT si pblm 
                        + "    ref varchar(15) not null,\n"
                        + "    des text not null,\n" // Ajout de la virgule manquante ici
                        + ")\n"
            );
            st.executeUpdate(
                    "create table precede_operation (\n"
                    + "    opavant integer not null,\n"
                    + "    opapres integer not null,\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "alter table precede_operation \n"
                    + "    add constraint fk_precede_operation_opapres \n"
                    + "    foreign key (opapres) references toutes_les_opérations(id) \n"
            );
            st.executeUpdate(
                    "alter table precede_operation \n"
                    + "    add constraint fk_precede_operation_opavant \n"
                    + "    foreign key (opavant) references toutes_les_opérations(id) \n"
            );
            st.executeUpdate(
                    "alter table toutes_les_opérations \n"
                    + "    add constraint fk_produit_idproduit \n"
                    + "    foreign key (idproduit) references produit(id) \n"
            );
            st.executeUpdate(
                    "alter table toutes_les_opérations \n"
                    + "    add constraint fk_typeoperation_idtype_tlo \n"
                    + "    foreign key (idtypeoperation) references typeoperation(id) \n" // normal de rajouter foreign key si deja mise dans la creation de la table ?
            );
            st.executeUpdate( ///!\ à faire pendant le prochain TP 
                    "alter table realise \n"
                    + "    add constraint fk_machine_idmachine \n"
                    + "    foreign key (idmachine) references machine(id) \n"
            );
            st.executeUpdate( ///!\ à faire pendant le prochain TP 
                    "alter table realise \n"
                    + "    add constraint fk_typeoperation_idtype_r \n"
                    + "    foreign key (idtype) references typeoperation(id) \n"
            );
            
            conn.commit();
        } catch (SQLException ex){ // en cas d'erreur ici 
            conn.rollback();
        throw ex;
    } finally {
            conn.setAutoCommit (true); //tru = laisse l'ordi enregistrer 
        }
    } 
        
        
        public static void initialise(Connection conn) throws SQLException{
            Produit tel = new Produit(7,"H23","robot");
            tel.sauvegarde(conn);
            
            
        }
        
        
      public void MenuMachine() throws SQLException {
        
        int rep = -1;
        
        while (rep != 0) {
            try (Statement st = this.conn.createStatement()) {
            int i = 1;
            System.out.println("Menu machine");
            System.out.println("================");
            System.out.println((i++) + ") chercher une machine");
            System.out.println((i++) + ") ajouter une machine");
            System.out.println((i++) + ") lister les machines");
            System.out.println((i++) + ") créer une table");
            System.out.println("0) Fin");
            System.out.print("Votre choix : ");
            rep = Lire.i();
            try {
                int j = 1;
                if (rep == j++) {
                    System.out.println("Quelle est l'id de la machine que vous cherchez ?");
                    int Uid = Lire.i();
                    ResultSet r = st.executeQuery("select * from machine where id=" + Uid + ";");
                    while (r.next()) {
                        int id = r.getInt(1);
                        String ref = r.getString(2);
                        String des = r.getString(3);
                        int puissance = r.getInt(4);
                        System.out.println(id + " : " + ref + " : " + des + " : " + puissance);
                    }
                } else if (rep == j++) {
                    System.out.println("Quelle est la ref de votre nouvelle machine ?");
                    String Uref = Lire.S();
                    System.out.println("Quelle est la description de votre nouvelle machine ?");
                    String Udes = Lire.S();
                    System.out.println("Quelle est la puissance de votre nouvelle machine ?");
                    int Upuissance = Lire.i();
                    st.executeUpdate("INSERT INTO `machine` (`id`, `ref`, `des`, `puissance`) VALUES (null, '" + Uref + "', '" + Udes + "', " + Upuissance + ");");
                } else if (rep == j++) {
                    ResultSet machines = st.executeQuery("SELECT * FROM machine");
                    while (machines.next()) {
                        int id = machines.getInt("id");
                        String ref = machines.getString("ref");
                        String des = machines.getString("des");
                        int puissance = machines.getInt("puissance");
                        System.out.println(id + " : " + ref + " : " + des + " : " + puissance);
                    }
                } else if (rep == j++) {
                    

                    StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
                    System.out.println("Entrez le nom de la table ?");
                    String nomTable = Lire.S();
                    query.append(nomTable).append(" (");
                    
                    System.out.println("Combien de colonnes souhaitez-vous dans votre table ?");
                    int nombreColonnes = Lire.i();

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
                    System.out.println("Table '" + nomTable + "' créée avec succès (ou déjà existante).");
                }

            } catch (SQLException ex) {
                 System.out.println(ExceptionsUtils.messageEtPremiersAppelsDansPackage(ex, "com.mycompagny", 5));//throw ex;  //on voudrait juste qu'il affiche l'erreur mais qu'on puisse continuer à l'utiliser --> 
            }
        }
    } 
}


            public void MenuProduit() throws SQLException {

            int rep = -1;

            while (rep != 0) {
                try (Statement st = this.conn.createStatement()) {
                int i = 1;
                System.out.println("Menu produit");
                System.out.println("================");
                System.out.println((i++) + ") chercher un produit");
                System.out.println((i++) + ") ajouter un produit");
                System.out.println((i++) + ") lister les produits");
                System.out.println((i++) + ") créer une table");
                System.out.println("0) Fin");
                System.out.print("Votre choix : ");
                rep = Lire.i();
                try {
                    int j = 1;
                    if (rep == j++) {
                        System.out.println("Quelle est l'id du produit que vous cherchez ?");
                        int Uid = Lire.i();
                        ResultSet r = st.executeQuery("select * from produit where id=" + Uid + ";");
                        while (r.next()) {
                            int id = r.getInt(1);
                            String ref = r.getString(2);
                            String des = r.getString(3);
                            System.out.println(id + " : " + ref + " : " + des );
                        }
                    } else if (rep == j++) {
                        System.out.println("Quelle est la ref de votre nouveau produit ?");
                        String Uref = Lire.S();
                        System.out.println("Quelle est la description de votre nouveau produit ?");
                        String Udes = Lire.S();
                        Produit nouveauProduit = new Produit(0, Uref, Udes);
                        nouveauProduit.sauvegarde(conn);
                    } else if (rep == j++) {
                        ResultSet produit = st.executeQuery("SELECT * FROM produit");
                        while (produit.next()) {
                            int id = produit.getInt("id");
                            String ref = produit.getString("ref");
                            String des = produit.getString("des");
                            System.out.println(id + " : " + ref + " : " + des );
                        }
                        
                    } else if (rep == j++) {


                        StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
                        System.out.println("Entrez le nom de la table ?");
                        String nomTable = Lire.S();
                        query.append(nomTable).append(" (");

                        System.out.println("Combien de colonnes souhaitez-vous dans votre table ?");
                        int nombreColonnes = Lire.i();

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
                        System.out.println("Table '" + nomTable + "' créée avec succès (ou déjà existante).");
                    }

                } catch (SQLException ex) {
                     System.out.println(ExceptionsUtils.messageEtPremiersAppelsDansPackage(ex, "com.mycompagny", 5));//throw ex;  //on voudrait juste qu'il affiche l'erreur mais qu'on puisse continuer à l'utiliser --> 
                }
            }
        } 
}
                public void MenuOperation() throws SQLException {

            int rep = -1;

            while (rep != 0) {
                try (Statement st = this.conn.createStatement()) {
                int i = 1;
                System.out.println("Menu operation");
                System.out.println("================");
                System.out.println((i++) + ") chercher une operation");
                System.out.println((i++) + ") ajouter une operation");
                System.out.println((i++) + ") lister les operations");
                System.out.println((i++) + ") créer une table");
                System.out.println("0) Fin");
                System.out.print("Votre choix : ");
                rep = Lire.i();
                try {
                    int j = 1;
                    if (rep == j++) {
                        System.out.println("Quelle est l'id de l'operation que vous cherchez ?");
                        int Uid = Lire.i();
                        ResultSet r = st.executeQuery("select * from typeoperation where id=" + Uid + ";");
                        while (r.next()) {
                            int id = r.getInt(1);
                            String des = r.getString(2);
                            System.out.println(id + " : "  + des );
                        }
                    } else if (rep == j++) {
                        System.out.println("Quelle est la description de votre nouvelle operation ?");
                        String Udes = Lire.S();
                       st.executeUpdate("INSERT INTO `typeoperation` (`id`, `des`) VALUES (null, '" + Udes + ");");
                    } else if (rep == j++) {
                        ResultSet typeoperation = st.executeQuery("SELECT * FROM typeoperation");
                        while (typeoperation.next()) {
                            int id = typeoperation.getInt("id");
                            String des = typeoperation.getString("des");
                            System.out.println(id + " : " + des );
                        }
                        
                    } else if (rep == j++) {


                        StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
                        System.out.println("Entrez le nom de la table ?");
                        String nomTable = Lire.S();
                        query.append(nomTable).append(" (");

                        System.out.println("Combien de colonnes souhaitez-vous dans votre table ?");
                        int nombreColonnes = Lire.i();

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
                        System.out.println("Table '" + nomTable + "' créée avec succès (ou déjà existante).");
                    }

                } catch (SQLException ex) {
                     System.out.println(ExceptionsUtils.messageEtPremiersAppelsDansPackage(ex, "com.mycompagny", 5));//throw ex;  //on voudrait juste qu'il affiche l'erreur mais qu'on puisse continuer à l'utiliser --> 
                }
            }
        } 
}    
         public static void deleteSchema(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement()) {
            // pour être sûr de pouvoir supprimer, il faut d'abord supprimer les liens
            // puis les tables
            // suppression des liens
            try {
                st.executeUpdate("alter table toutes_les_opérations drop constraint fk_typeoperation_idtype");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate("alter table toutes_les_opérations drop constraint fk_produit_idproduit");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("alter table precede_operation drop constraint fk_precede_operation_opapres");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate("alter table precede_operation drop constraint fk_precede_operation_opavant");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate("alter table realise drop constraint fk_machine_idmachine");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate("alter table realise drop constraint fk_typeoperation_idtype");
            } catch (SQLException ex) {
            }
            // je peux maintenant supprimer les tables
            try {
                st.executeUpdate("drop table machine");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table toutes_les_opérations");
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
            try {
                st.executeUpdate("drop table precede_operation");
            } catch (SQLException ex) {
            }
        }
    }

         
    public static void main(String[] args) {
        System.out.println("execution du projet");
        debut();
    }
    
    
    
    
    
}
// classes initiale de fbdb que cg m'a fait enlever
/*
    public static void main(String[] args) {
        System.out.println("execution du projet");
        debut();
    }


    public static void debut() {
    try  {
        Connection conn = connectSurServeurM3();
        System.out.println("connecté");
        creeSchema(conn);
        deleteSchema(conn);
        initialise(conn);
    } catch (SQLException ex) {
        throw new Error("Connection impossible", ex);
    }
}*/



 /*   
     public void MenuMachine() throws SQLException {
        
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
            System.out.print("Votre choix : ");
            rep = Lire.i();
            try {
                int j = 1;
                if (rep == j++) {
                    System.out.println("Quelle est l'id de la machine que vous cherchez ?");
                    int Uid = Lire.i();
                    ResultSet r = st.executeQuery("select * from machine where id=" + Uid + ";");
                    while (r.next()) {
                        int id = r.getInt(1);
                        String ref = r.getString(2);
                        String des = r.getString(3);
                        int puissance = r.getInt(3);
                        System.out.println(id + " : " + ref + " : " + des + " : " + puissance);
                    }
                } else if (rep == j++) {
                    System.out.println("Quelle est la ref de votre nouvelle machine ?");
                    String Uref = Lire.S();
                    System.out.println("Quelle est la description de votre nouvelle machine ?");
                    String Udes = Lire.S();
                    System.out.println("Quelle est la puissance de votre nouvelle machine ?");
                    int Upuissance = Lire.i();
                    st.executeUpdate("INSERT INTO `machine` (`id`, `ref`, `des`, `puissance`) VALUES (null, '" + Uref + "', '" + Udes + "', " + Upuissance + ");");
                } else if (rep == j++) {
                    ResultSet machines = st.executeQuery("SELECT * FROM machine");
                    while (machines.next()) {
                        int id = machines.getInt("id");
                        String ref = machines.getString("ref");
                        String des = machines.getString("des");
                        int puissance = machines.getInt("puissance");
                        System.out.println(id + " : " + ref + " : " + des + " : " + puissance);
                    }
                } else if (rep == j++) {
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
                throw ex;
            }
        }
    } 
}
         
*/