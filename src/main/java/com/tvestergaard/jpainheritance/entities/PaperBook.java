package com.tvestergaard.jpainheritance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class PaperBook extends Book
{

    @Column(nullable = false, length = 255)
    private long shippingWeight;

    @Column(nullable = false)
    private boolean inStock;

    public PaperBook()
    {

    }

    public PaperBook(String isbn, String title, String author, long price, long shippingWeight, boolean inStock)
    {
        super(isbn, title, author, price);
        this.shippingWeight = shippingWeight;
        this.inStock = inStock;
    }

    public long getShippingWeight()
    {
        return this.shippingWeight;
    }

    public boolean isInStock()
    {
        return this.inStock;
    }

    public void setShippingWeight(long shippingWeight)
    {
        this.shippingWeight = shippingWeight;
    }

    public void setInStock(boolean inStock)
    {
        this.inStock = inStock;
    }


    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperBook eBook = (PaperBook) o;
        return Objects.equals(isbn, eBook.isbn) &&
               Objects.equals(author, eBook.author) &&
               Objects.equals(title, eBook.title) &&
               Objects.equals(price, eBook.price) &&
               inStock == eBook.inStock &&
               shippingWeight == eBook.shippingWeight;
    }

    @Override public int hashCode()
    {
        return Objects.hash(isbn, author, title, price, inStock, shippingWeight);
    }

    @Override public String toString()
    {
        return "PaperBook{" +
               "  isbn='" + isbn + '\'' +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", price=" + price + '\'' +
               ", shippingWeight=" + shippingWeight +
               ", inStock=" + inStock +
               '}';
    }
}
