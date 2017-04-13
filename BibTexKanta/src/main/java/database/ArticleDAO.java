package database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import reference.ArticleRef;

public class ArticleDAO extends AbstractDAO<ArticleRef> {

    public ArticleDAO(Database database) {
        super(database, "book", "authors, title, publisher, year, address");
    }

    @Override
    protected ArticleRef createFromResultSet(ResultSet rs) throws Exception {
        String authors = rs.getString("authors");
        String title = rs.getString("title");
        String publisher = rs.getString("publisher");
        String year = rs.getString("year");
        String address = rs.getString("address");
        String volume = rs.getString("volume");
        String journal = rs.getString("journal");
        String number = rs.getString("number");

        return new ArticleRef(authors, title, journal, year, volume, number, publisher, address);
    }

    @Override
    protected List<Object> decompose(ArticleRef t) {
        List list = new ArrayList();

        list.add(t.getField("authors"));
        list.add(t.getField("title"));
        list.add(Integer.toString(t.getYear()));
        list.add(t.getField("publisher"));
        list.add(t.getField("address"));

        return list;
    }

}
