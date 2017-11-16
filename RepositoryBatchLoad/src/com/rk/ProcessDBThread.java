package com.rk;

import java.util.ArrayList;

import org.hibernate.Session;

import com.rk.dao.MCR_Bean;
import com.rk.util.DBSessionUtil;

public class ProcessDBThread extends Thread{
	ArrayList<MCR_Bean> list = new ArrayList<MCR_Bean>();
	String thread ;
	public ProcessDBThread(ArrayList<MCR_Bean> list,String threadName) {
		this.list = list;
		this.thread = threadName;
	}
	public void run() {
		System.out.println("Thread started: "+thread+ " size: "+list.size());
		
		Long startTime = System.currentTimeMillis();
		Session session = DBSessionUtil.getInstance().getSession();
		System.out.println("Thread "+thread+" get session.. ");
		int j = 0;
		session.beginTransaction();
		System.out.println("Thread "+thread+" startTransaction.. ");
    		for(MCR_Bean bean : list) {
    			j++;
    			session.saveOrUpdate(bean);
                if ( j % 30 == 0 ) { 
                	DBSessionUtil.flushSession(session);
                	System.out.println("Thread: "+thread+" inserting: "+j);
                }
    		}
		  session.getTransaction().commit();
		  System.out.println("Thread "+thread+" commit transaction.. ");
          DBSessionUtil.flushAndCloseSession(session);
          Long endTime = System.currentTimeMillis();
          System.out.println("Thread: "+thread+" Time Taken to complete : "+(endTime-startTime)+" rows inserted: "+list.size());  
	}
}
