package com.tvestergaard.jpainheritance;

import com.tvestergaard.jpainheritance.entities.Book;
import com.tvestergaard.jpainheritance.entities.EBook;
import com.tvestergaard.jpainheritance.entities.PaperBook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Facade
{

    private final EntityManagerFactory entityManagerFactory;

    public Facade(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EBook createEBook(String isbn, String title, String author, long price, String downloadUrl, long sizeMB)
    {
        EBook         eBook         = new EBook(isbn, title, author, price, downloadUrl, sizeMB);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(eBook);
            entityManager.getTransaction().commit();
            return eBook;
        } finally {
            entityManager.close();
        }
    }

    public PaperBook createPaperBook(String isbn, String title, String author, long price, long shippingWeight, boolean inStock)
    {
        PaperBook     paperBook     = new PaperBook(isbn, title, author, price, shippingWeight, inStock);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(paperBook);
            entityManager.getTransaction().commit();
            return paperBook;
        } finally {
            entityManager.close();
        }
    }

    public void update(Book book)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(book);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public <T extends Book> T find(String isbn, Class<T> t)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.find(t, isbn);
        } finally {
            entityManager.close();
        }
    }

    public void remove(Book book)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            // Ensure that the book being removed is attached to the current entity manager.
            entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public List<Book> all()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createQuery("SELECT b FROM Book b").getResultList();
        } finally {
            entityManager.close();
        }
    }
}
