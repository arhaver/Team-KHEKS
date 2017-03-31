package database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import reference.BookRef;

public class BookDAO extends AbstractDAO<BookRef>{

    public BookDAO(Database database) {
        super(database, "book", "authors, title, publisher, year, address");
    }

    @Override
    protected BookRef createFromResultSet(ResultSet rs) throws Exception{
        String authors = rs.getString("authors");
        String title = rs.getString("title");
        String publisher = rs.getString("publisher");
        String year = rs.getString("year");
        String address = rs.getString("address");
        
        return new BookRef(authors, title, publisher, year, address);
    }

    @Override
    protected List<Object> decompose(BookRef t) {
        List list = new ArrayList();
        
        list.add(t.getAuthors());
        list.add(t.getTitle());
        list.add(t.getYear());
        list.add(t.getPublisher());
        list.add(t.getAddress());
        
        return list;
    }
    
}
