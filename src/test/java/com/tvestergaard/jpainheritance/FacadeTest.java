package com.tvestergaard.jpainheritance;

import com.tvestergaard.jpainheritance.entities.Book;
import com.tvestergaard.jpainheritance.entities.EBook;
import com.tvestergaard.jpainheritance.entities.PaperBook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class FacadeTest
{

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ca-jpa-inheritance-test-pu");
    private       Facade               facade;

    @Before
    public void setUp() throws Exception
    {
        facade = new Facade(entityManagerFactory);
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void createEBook()
    {
        EBook eBook = facade.createEBook("978-3-16-148410-4", "Ny historie", "Facade", 1, "http:..", 5);
        assertEquals(eBook, facade.find("978-3-16-148410-4", EBook.class));
    }

    @Test
    public void createPaperBook()
    {
        PaperBook paperBook = facade.createPaperBook("978-3-16-148410-5", "Nyerer historie", "Facade", 1, 1, false);
        assertEquals(paperBook, facade.find("978-3-16-148410-5", PaperBook.class));

    }

    @Test
    public void update()
    {
        PaperBook paperBook = facade.createPaperBook("978-3-16-148410-5", "Endnu nyerer historie", "Facade", 1, 1, false);
        assertFalse(paperBook.isInStock());
        paperBook.setInStock(true);
        facade.update(paperBook);

        assertTrue(facade.find("978-3-16-148410-5", PaperBook.class).isInStock());
    }

    @Test
    public void find()
    {
        PaperBook paperBook = facade.find("978-3-16-148410-0", PaperBook.class);
        assertEquals("978-3-16-148410-0", paperBook.getIsbn());
        assertEquals("Thomas Vestergaard", paperBook.getAuthor());
        assertEquals(1, paperBook.getPrice());
        assertEquals("Sjov historie", paperBook.getTitle());
        assertTrue(paperBook.isInStock());
        assertEquals(1, paperBook.getShippingWeight());

        EBook eBook = facade.find("978-3-16-148410-0", EBook.class);
        assertEquals("978-3-16-148410-0", eBook.getIsbn());
        assertEquals("Thomas Vestergaard", eBook.getAuthor());
        assertEquals(1, eBook.getPrice());
        assertEquals("Sjov historie", eBook.getTitle());
        assertEquals("URL", eBook.getDownloadUrl());
        assertEquals(1, eBook.getSizeMB());
    }

    @Test
    public void remove()
    {
        PaperBook paperBook = facade.createPaperBook("978-3-16-148410-6", "Endnu nyerer historie", "Facade", 1, 1, false);
        EBook     eBook     = facade.createEBook("978-3-16-148410-7", "Ny historie", "Facade", 1, "http:..", 5);

        assertNotNull(facade.find("978-3-16-148410-6", PaperBook.class));
        assertNotNull(facade.find("978-3-16-148410-7", EBook.class));

        facade.remove(paperBook);
        facade.remove(eBook);

        assertNull(facade.find("978-3-16-148410-6", PaperBook.class));
        assertNull(facade.find("978-3-16-148410-7", EBook.class));
    }

    @Test
    public void all()
    {
        List<Book> bookList = facade.all();
        assertTrue(bookList.size() >= 8)n;
    }
}