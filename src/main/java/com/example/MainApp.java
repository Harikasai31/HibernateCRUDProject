package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApp {

public static void main(String[] args) {

Session session = HibernateUtil.getSessionFactory().openSession();

Transaction tx = session.beginTransaction();

Product p1 = new Product("Laptop","Dell Laptop",60000,10);
Product p2 = new Product("Mouse","Wireless Mouse",800,50);

session.save(p1);
session.save(p2);

Product product = session.get(Product.class,1);

System.out.println("Product Name: "+product.getName());

product.setPrice(65000);

session.update(product);

Product deleteProduct = session.get(Product.class,2);

session.delete(deleteProduct);

tx.commit();

session.close();

System.out.println("CRUD Operations Completed");

}

}