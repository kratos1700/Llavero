/*
 * Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
 */
package controller;


import entities.Usuari;
import util.Codi;
import util.HibernateConn;
import java.awt.HeadlessException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
//import javax.persistence.criteria.CriteriaQuery;


/**
 *
 * @author Kratos1700
 */
public class UsuarioController {

    //variables per pasar info
    public static int idUser;
    public static String usuar;
    // constant per utilitzacio a la encriptacio AES
    public static final String LLAVE_USUARIO = "LlaveroSecret!@";
    
    
    //funcio per al login
    public static boolean Login(String usuari, String password) {
        Session session = HibernateConn.getMiFactory().openSession();
        Usuari user = (Usuari) session.createCriteria(Usuari.class)
                .add(Restrictions.eq("usuari", usuari)).uniqueResult();
         Codi codi = new Codi();
        
        try {
            if (user != null) {
                String pass = codi.encriptar(password, LLAVE_USUARIO);
                
                if (user.getContrasenya().equals(pass)) {
                    JOptionPane.showMessageDialog(null, "Benvingut al sistema " + user.getUsuari());
                   idUser = user.getId();
                   usuar = user.getUsuari(); 
           
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Contrasenya no valida ");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "L'usuari no existeix! Crea l'usuari primer.");
                return false;
            }
        } catch (HeadlessException | UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            return false;
        }
    }

    // funcio per a crear usuari
    public static boolean crearUsuari(String usuari, String password) {
        Session session = HibernateConn.getMiFactory().openSession();
        Codi codi = new Codi();

        Usuari usu = (Usuari) session.createCriteria(Usuari.class)
                .add(Restrictions.eq("usuari", usuari)).uniqueResult();

        try {
            if (usu != null) {
                if (usu.getUsuari().equals(usuari)) {
                    JOptionPane.showMessageDialog(null, "L'usuari " + usu.getUsuari() + " ja existeix, Utilitzi un altre nom!!");
                    return false;
                } else {

                    JOptionPane.showMessageDialog(null, "Error al crear l'usuari ");
                    return false;

                }

            } else {
               

                String pass = codi.encriptar(password, LLAVE_USUARIO);
                Usuari user = new Usuari();
                session.beginTransaction();
                user.setUsuari(usuari);
                user.setContrasenya(pass);

                session.save(user);
                session.getTransaction().commit();
                session.close();
                JOptionPane.showMessageDialog(null, "L'usuari " + user.getUsuari() + " ha estat creat!!");
                return true;

            }

        } catch (HeadlessException | UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | HibernateException e) {
            return false;
        }

    }

}
