/*
Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
*/

package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HibernateConn {

    private static final SessionFactory miFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            //creamos session a hibernate pasamos el config y le decimos a que clase vamos a trabajar.
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        } catch (Throwable e) {
            System.out.println("ERROR AL INICIALIZAR EL SESSIONFACTORY" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    // retornem la sessio
    public static SessionFactory getMiFactory() {
        return miFactory;
    }

}
