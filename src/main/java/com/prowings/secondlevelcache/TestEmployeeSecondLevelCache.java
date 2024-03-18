package com.prowings.secondlevelcache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.prowings.entity.Employee;

public class TestEmployeeSecondLevelCache {
	
	public static void main(String[] args) {
		
		Employee emp1 = new Employee();
		
		emp1.setId(1);
		emp1.setName("Ram");
		emp1.setAddress("Pune");
		emp1.setDepartment("HR");
		
		Employee emp2 = new Employee();
		
		emp2.setId(2);
		emp2.setName("Sham");
		emp2.setAddress("Mumbai");
		emp2.setDepartment("IT");
		
		Configuration cfg = new Configuration();
		
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		
		Session session1 = sf.openSession();
		
		Transaction txn1 = session1.beginTransaction();
		
//		session1.save(emp1);
//		session1.save(emp2);
		
		Employee e1 = session1.get(Employee.class,1);
		System.out.println("Emp1 from session 1 first time :"+ e1);
		
		Employee e2 = session1.get(Employee.class,1);
		System.out.println("Emp1 from session 1 second time :"+ e2);
		
		txn1.commit();
		session1.close();
		
		System.out.println(">>>>>>>>>>>>>>>>starting second session<<<<<<<<<<<<<<");
		
		Session session2 = sf.openSession();
		
		Transaction txn2 = session2.beginTransaction();
		
		Employee se2 = session2.get(Employee.class,1);
		System.out.println("Emp1 from session 1 second time :"+ se2);
		
		txn2.commit();
		session2.close();
		
		
		
	}

}
