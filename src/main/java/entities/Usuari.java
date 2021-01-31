/*
Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
*/

package entities;


import util.HibernateConn;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;

import javax.persistence.*;


@Entity
@Table(name = "usuari")
public class Usuari {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "usuari")
    private String usuari;
    @Column(name = "contrasenya")
    private String contrasenya;
    
    private Set<Dades> dada = new HashSet<>();

    //Cpnstructors

    public Usuari() {
    }

    public Usuari(String usuari, String contrasenya) {
        this.usuari = usuari;
        this.contrasenya = contrasenya;
    }

    //gets i sets


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
    
    public Set<Dades> getDades(){
        return dada;
    }
    
    public void setDades(Set<Dades> dada){
        this.dada= dada;
    }
    
    public void addDades(Dades dada){
        this.dada.add(dada);
    }

    @Override
    public String toString() {
        return "usuari.hbm.xml{" +
                "id=" + id +
                ", usuari='" + usuari + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                '}';
    }


    public void insertarUsuari(String usuari, String contrasenya) {
        // creamos conexion
        Session session = HibernateConn.getMiFactory().getCurrentSession();
        session.beginTransaction();
        //iniciamos transaccion, creamos un modulo y pasamos los parametros
        Usuari user = new Usuari();
        user.setUsuari(usuari);
        user.setContrasenya(contrasenya);
        session.save(user);
        session.getTransaction().commit();

    }
}
