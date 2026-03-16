package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class InsertProducts {

public static void main(String[] args) {

Session session = HibernateUtil.getSessionFactory().openSession();
Transaction tx = session.beginTransaction();

Product p1 = new Product("Laptop","Electronics",60000,10);
Product p2 = new Product("Mouse","Electronics",800,50);
Product p3 = new Product("Keyboard","Electronics",2000,30);
Product p4 = new Product("Monitor","Electronics",12000,15);
Product p5 = new Product("Printer","Office",7000,8);
Product p6 = new Product("Scanner","Office",5000,5);
Product p7 = new Product("Chair","Furniture",3000,20);
Product p8 = new Product("Table","Furniture",8000,12);

session.save(p1);
session.save(p2);
session.save(p3);
session.save(p4);
session.save(p5);
session.save(p6);
session.save(p7);
session.save(p8);

tx.commit();
session.close();

System.out.println("Products Inserted");

}
}