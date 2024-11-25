/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickcash;

/**
 *
 * @author jo17-dev
 * Cette classe contient les controls de bases ( logout, changement de page(il vas catch l'expeception ici est afficher les messages d'erreurs
 */
public class BasicControls {
    public BasicControls(){
        
    }
    
    public void logout(){
        System.out.println("_____deconnexion_____");
        System.exit(0);
    }
    
    public void listeClient(){
        System.out.println("Redirection vers lite Client");
    }
}
