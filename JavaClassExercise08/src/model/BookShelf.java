package model;
import java.util.ArrayList;
import java.util.List;

public class BookShelf {
    private List<Book> books;

    public BookShelf()
    {
        books=new ArrayList<>();
    }

    public void addBook(Book book)
    {
        books.add(book);
    }

    public List<Book> getBooks()
    {
        return books;
    }
}
