/*
 * Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
 */
package controller;


import entities.Dades;
import util.HibernateConn;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Kratos1700
 *
 */
public class ConsulaDades {

    public static List recuperarDades(int id) {
        Session miSession = HibernateConn.getMiFactory().openSession();

        try {
            miSession.beginTransaction();

            List dades = (List) miSession.createQuery("FROM Dades Where idUser =" + id).list();

            for (Iterator iterator = dades.iterator(); iterator.hasNext();) {
                Dades dade = (Dades) iterator.next();
                System.out.println("ID: " + dade.getId() + "Nom: " + dade.getNom() + " User: " + dade.getUserr()
                        + " Contrasenya: " + dade.getContrasenya() + " Enllaç: " + dade.getEnllac()
                        + "Comentari: " + dade.getComentari() + " id Usuari: " + dade.getUsuari());
            }
            miSession.getTransaction().commit();
            miSession.close();
            return dades;

        } catch (HibernateException e) {
            return null;
        }

    }

    //funcio per omplir taula 
    public static void omplirTaula(List dades, JTable taula) {
        List<Dades> consulta = dades;

        if (consulta.size() > 0) {
            Iterator resultat = consulta.iterator();
            while (resultat.hasNext()) {
                DefaultTableModel taulaa = (DefaultTableModel) taula.getModel();
                Vector info = new Vector();
                Dades fila = (Dades) resultat.next();
                info.add(fila.getId());
                info.add(fila.getNom());
                info.add(fila.getUserr());
                info.add(fila.getContrasenya());
                info.add(fila.getEnllac());
                info.add(fila.getComentari());
                taulaa.addRow(info);

            }
        }

    }

    //Funcio per buidar taula
    public static void llimpiartaula(JTable taula) {

        while (taula.getRowCount() != 0) {
            ((DefaultTableModel) taula.getModel()).removeRow(0);
        }
    }

}
