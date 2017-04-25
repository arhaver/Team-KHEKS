package bibtex;

import database.DAO;
import java.util.List;
import reference.ArticleRef;
import reference.BookRef;

/*
Rajapinta Stringijonon luomiseen DAOista
*/
public interface IBibtexTranslator {
    
    public List<String> bibTex(DAO... daos) throws Exception;

    public List<String> makeBookBibTex(DAO<BookRef> bookDAO, List<String> lines) throws Exception;

    public List<String> makeArticleBibTex(DAO<ArticleRef> articleDAO, List<String> lines) throws Exception;
    
}
