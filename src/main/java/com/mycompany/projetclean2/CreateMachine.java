/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetclean2;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Noemie
 */

public class CreateMachine extends VerticalLayout{

    private TextField tfRef;
    private TextField tfDes;
    private TextField tfPuissance;
    private Button btValider;
    
    public CreateMachine() {
        this.tfRef = new TextField("Reference");
        this.tfDes = new TextField("Description");
        this.tfPuissance = new TextField("Puissance");
        this.btValider = new Button("Valider");
        this.btValider.addSingleClickListener((t) -> {
            var reference = this.tfRef.getValue();
            String description = this.tfDes.getValue();
            String puissance = this.tfPuissance.getValue();
            //id=id+1
            
             // Création d'un objet Machine avec les valeurs récupérées
            Machine nouvelleMachine = new Machine(id, reference, description, puissance);

            // Insérer la nouvelle machine dans la base de données
            insererMachine(nouvelleMachine, conn);
        });
    }
    
    private void insererMachine(Machine machine, Connection conn) {
        try {
            // Préparation de la requête SQL pour insérer une nouvelle machine dans votre table
            String sql = "INSERT INTO machines (id,reference, description, puissance) VALUES (?, ?, ?, ?)";

            // Création de la déclaration SQL
            PreparedStatement statement = conn.prepareStatement(sql);

            // Remplacement des paramètres dans la requête SQL avec les valeurs de la machine
            statement.setInt(1, machine.getId());
            statement.setString(2, machine.getRef());
            statement.setString(3, machine.getDes());
            statement.setDouble(4, machine.getPuissance());

            // Exécution de la requête pour insérer la nouvelle machine dans la base de données
            statement.executeUpdate();

            // Fermeture de la déclaration
            statement.close();

            // Gérer les exceptions, les transactions, etc.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//mettre toute la classe dans une boite
// pouvoir fermer la page avec une croix, ou alors que celle-ci se ferme automatiquement