package com.tvestergaard.jpainheritance.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book
{

    @Id
    @Column(nullable = false, length = 255)
    protected String isbn;

    @Column(nullable = false, length = 255)
    protected String title;

    @Column(nullable = false, length = 255)
    protected String author;

    @Column(nullable = false, length = 255)
    protected long price;

    public Book()
    {

    }

    public Book(String isbn, String title, String author, long price)
    {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getIsbn()
    {
        return this.isbn;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getAuthor()
    {
        return this.author;
    }

    public long getPrice()
    {
        return this.price;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setPrice(long price)
    {
        this.price = price;
    }

    @Override public String toString()
    {
        return "Book{" +
               "isbn='" + isbn + '\'' +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", price=" + price +
               '}';
    }
}
