/*
 *Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
 */
package controller;


import entities.Dades;
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

/**
 *
 * @author Kratos1700
 */
public class DadesController {
    
    // constant per utilitzacio a la encriptacio AES
     private static final String LLAVE_REGISTROS = "cLauSecryeta@23";

    //funcio per a crear registres
    public static boolean insertarDades(String nom, String usuari, String password, String enlac,
            String comentari, int idUser) {
        Session session = HibernateConn.getMiFactory().openSession();

        Codi codi = new Codi();

        try {
            String user = codi.encriptar(usuari, LLAVE_REGISTROS);
            String pass = codi.encriptar(password, LLAVE_REGISTROS);
            String enll = codi.encriptar(enlac, LLAVE_REGISTROS);
            Dades dada = new Dades();
            Usuari usuaria = new Usuari();
            usuaria.setId(UsuarioController.idUser);
            session.beginTransaction();
            dada.setNom(nom);
            dada.setUserr(user);
            dada.setContrasenya(pass);
            dada.setEnllac(enll);
            dada.setComentari(comentari);
            dada.setUsuari(usuaria);
            session.save(dada);
            session.getTransaction().commit();
            session.close();
            return true;

        } catch (HeadlessException | UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | HibernateException e) {
            session.close();
            return false;

        }
    }

    public static boolean eliminarDada(int idRegistre) {
        Session session = HibernateConn.getMiFactory().openSession();
        session.beginTransaction();
        Dades dada = (Dades) session.createCriteria(Dades.class)
                .add(Restrictions.eq("id", idRegistre)).uniqueResult();

        try {
            if (dada != null) {
                session.delete(dada);
                session.getTransaction().commit();
                session.close();
                JOptionPane.showMessageDialog(null, "El registre ha estat borrat!!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al borrar el registre");
                session.close();
                return false;
            }

        } catch (HeadlessException | HibernateException e) {
            session.close();
            return false;
        }
    }

    public static boolean actualitzarDada(int idRegistre, String nom, String usuari, String password, String enlac,
            String comentari) {
        Session session = HibernateConn.getMiFactory().openSession();
        session.beginTransaction();
        Dades dada = (Dades) session.createCriteria(Dades.class)
                .add(Restrictions.eq("id", idRegistre)).uniqueResult();

        Codi codi = new Codi();

        try {
            if (dada != null) {

                String user = codi.encriptar(usuari, LLAVE_REGISTROS);
                String pass = codi.encriptar(password, LLAVE_REGISTROS);
                String enll = codi.encriptar(enlac, LLAVE_REGISTROS);
                dada.setNom(nom);
                dada.setUserr(user);
                dada.setContrasenya(pass);
                dada.setEnllac(enll);
                dada.setComentari(comentari);
                session.update(dada);
                session.getTransaction().commit();
                session.close();

                JOptionPane.showMessageDialog(null, "El registre ha estat actualitzat!!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualitzar el registre");
                session.close();
                return false;
            }

        } catch (HeadlessException | UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | HibernateException e) {
            session.close();
            return false;

        }
    }

}
