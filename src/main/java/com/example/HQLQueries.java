package com.example;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class HQLQueries {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        // 1. Sort by Price ASC
        System.out.println("Sort by Price ASC");

        Query<Product> q1 = session.createQuery(
                "FROM Product ORDER BY price ASC", Product.class);

        List<Product> list1 = q1.list();

        for(Product p : list1)
        {
            System.out.println(p.getName()+" "+p.getPrice());
        }


        // 2. Sort by Price DESC
        System.out.println("\nSort by Price DESC");

        Query<Product> q2 = session.createQuery(
                "FROM Product ORDER BY price DESC", Product.class);

        List<Product> list2 = q2.list();

        for(Product p : list2)
        {
            System.out.println(p.getName()+" "+p.getPrice());
        }


        // 3. Sort by Quantity (Highest first)

        System.out.println("\nSort by Quantity DESC");

        Query<Product> q3 = session.createQuery(
                "FROM Product ORDER BY quantity DESC", Product.class);

        List<Product> list3 = q3.list();

        for(Product p : list3)
        {
            System.out.println(p.getName()+" "+p.getQuantity());
        }


        // 4. Pagination (First 3)

        System.out.println("\nFirst 3 Products");

        Query<Product> q4 = session.createQuery(
                "FROM Product", Product.class);

        q4.setFirstResult(0);
        q4.setMaxResults(3);

        List<Product> page1 = q4.list();

        for(Product p : page1)
        {
            System.out.println(p.getName());
        }


        // 5. Pagination (Next 3)

        System.out.println("\nNext 3 Products");

        Query<Product> q5 = session.createQuery(
                "FROM Product", Product.class);

        q5.setFirstResult(3);
        q5.setMaxResults(3);

        List<Product> page2 = q5.list();

        for(Product p : page2)
        {
            System.out.println(p.getName());
        }


        // 6. Aggregate COUNT

        Query<Long> count = session.createQuery(
                "SELECT COUNT(*) FROM Product", Long.class);

        System.out.println("\nTotal Products : "+count.uniqueResult());


        // Count quantity > 0

        Query<Long> q6 = session.createQuery(
                "SELECT COUNT(*) FROM Product WHERE quantity > 0", Long.class);

        System.out.println("Products with quantity >0 : "+q6.uniqueResult());


        // GROUP BY description

        System.out.println("\nGroup By Description");

        Query<Object[]> q7 = session.createQuery(
                "SELECT description, COUNT(*) FROM Product GROUP BY description");

        List<Object[]> results = q7.list();

        for(Object[] row : results)
        {
            System.out.println(row[0]+" "+row[1]);
        }


        // Min and Max price

        Query<Object[]> q8 = session.createQuery(
                "SELECT MIN(price), MAX(price) FROM Product");

        Object[] price = q8.uniqueResult();

        System.out.println("\nMin Price : "+price[0]);
        System.out.println("Max Price : "+price[1]);


        // Price range filter

        System.out.println("\nProducts between price 1000 and 10000");

        Query<Product> q9 = session.createQuery(
                "FROM Product WHERE price BETWEEN 1000 AND 10000",
                Product.class);

        List<Product> range = q9.list();

        for(Product p : range)
        {
            System.out.println(p.getName()+" "+p.getPrice());
        }


        // LIKE Queries

        System.out.println("\nNames starting with L");

        Query<Product> q10 = session.createQuery(
                "FROM Product WHERE name LIKE 'L%'", Product.class);

        for(Product p : q10.list())
        {
            System.out.println(p.getName());
        }


        System.out.println("\nNames ending with r");

        Query<Product> q11 = session.createQuery(
                "FROM Product WHERE name LIKE '%r'", Product.class);

        for(Product p : q11.list())
        {
            System.out.println(p.getName());
        }


        System.out.println("\nNames containing 'top'");

        Query<Product> q12 = session.createQuery(
                "FROM Product WHERE name LIKE '%top%'", Product.class);

        for(Product p : q12.list())
        {
            System.out.println(p.getName());
        }


        System.out.println("\nNames with length 5");

        Query<Product> q13 = session.createQuery(
                "FROM Product WHERE LENGTH(name)=5", Product.class);

        for(Product p : q13.list())
        {
            System.out.println(p.getName());
        }

        session.close();
    }
}