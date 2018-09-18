package com.tvestergaard.jpainheritance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class EBook extends Book
{

    @Column(nullable = false, length = 255)
    private String downloadUrl;

    @Column(nullable = false, length = 255)
    private long sizeMB;

    public EBook()
    {

    }

    public EBook(String isbn, String title, String author, long price, String downloadUrl, long sizeMB)
    {
        super(isbn, title, author, price);
        this.downloadUrl = downloadUrl;
        this.sizeMB = sizeMB;
    }

    public String getDownloadUrl()
    {
        return this.downloadUrl;
    }

    public long getSizeMB()
    {
        return this.sizeMB;
    }

    public void setDownloadUrl(String downloadUrl)
    {
        this.downloadUrl = downloadUrl;
    }

    public void setSizeMB(long sizeMB)
    {
        this.sizeMB = sizeMB;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EBook eBook = (EBook) o;
        return Objects.equals(isbn, eBook.isbn) &&
               Objects.equals(author, eBook.author) &&
               Objects.equals(title, eBook.title) &&
               Objects.equals(price, eBook.price) &&
               sizeMB == eBook.sizeMB &&
               Objects.equals(downloadUrl, eBook.downloadUrl);
    }

    @Override public int hashCode()
    {
        return Objects.hash(isbn, author, title, price, downloadUrl, sizeMB);
    }

    @Override public String toString()
    {
        return "EBook{" +
               "  isbn='" + isbn + '\'' +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", price=" + price + '\'' +
               ", downloadUrl='" + downloadUrl + '\'' +
               ", sizeMB=" + sizeMB +
               '}';
    }
}
