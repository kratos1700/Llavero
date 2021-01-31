/*
 * Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
 */
package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Kratos1700
 */

@Entity
@Table(name = "dades")
public class Dades {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "userr")
    private String userr;
    @Column(name = "contrasenya")
    private String contrasenya;
    @Column(name = "enllac")
    private String enllac;
    @Column(name = "comentari")
    private String comentari;
    @Column(name = "usuari")
    private Usuari usuari;
    
    // Constructors

    public Dades() {
    }

    public Dades(String nom, String userr, String contrasenya, String enllac, Usuari usuari) {
        this.nom = nom;
        this.userr = userr;
        this.contrasenya = contrasenya;
        this.enllac = enllac;
        this.usuari = usuari;
    }

    //get i set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUserr() {
        return userr;
    }

    public void setUserr(String userr) {
        this.userr = userr;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getEnllac() {
        return enllac;
    }

    public void setEnllac(String enllac) {
        this.enllac = enllac;
    }

    public String getComentari() {
        return comentari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    @Override
    public String toString() {
        return "Dades{" + "id=" + id + ", nom=" + nom + ", userr=" + userr + ", contrasenya=" + contrasenya + ", enllac=" + enllac + ", comentari=" + comentari + ", usuari=" + usuari + '}';
    }
    
    
    
}
