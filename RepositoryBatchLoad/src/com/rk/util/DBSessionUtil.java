package com.rk.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.MetadataSources;
import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistryBuilder;


public class DBSessionUtil {


    private static DBSessionUtil me;
    private Configuration config;
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    
    private DBSessionUtil() throws HibernateException {
    	// A SessionFactory is set up once for an application!
    	/*final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    			.configure() // configures settings from hibernate.cfg.xml
    			.build();*/
    	Configuration conf = new Configuration();
        conf.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
    	System.out.println("standardServiceRegistry");
    	try {
    		//sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    		sessionFactory = conf.buildSessionFactory(serviceRegistry);
    		System.out.println("sessionfactory");
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
    		// so destroy it manually.
    		//StandardServiceRegistryBuilder.destroy( registry );
    	}}
 
    public static synchronized DBSessionUtil getInstance() throws HibernateException {
        if (me == null) {
            me = new DBSessionUtil();
        }
 
        return me;
    }
 
    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }
 
    private void reconnect() throws HibernateException {
        this.sessionFactory = config.buildSessionFactory();
    } 
    
    public static void closeSession(Session session) {
		if (session != null && session.isOpen()) {
            session.flush();
			session.clear();
			session.close();
		}
	}
	
	public static void flushSession(Session session){
		if (session != null && session.isOpen()){
			session.flush();
			session.clear();
		}
	}

	
	public static void flushAndCloseSession(Session session){
		if (session != null && session.isOpen()){
			session.flush();
			session.clear();
			session.close();
			//sessionFactory.close();
			//StandardServiceRegistryBuilder.destroy(sessionFactory.getSessionFactoryOptions().getServiceRegistry());
		}
	}

}
