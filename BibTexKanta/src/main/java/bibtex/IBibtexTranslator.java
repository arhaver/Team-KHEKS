package bibtex;

import database.DAO;
import java.util.List;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;

/*
Rajapinta Stringijonon luomiseen DAOista
*/
public interface IBibtexTranslator {

    public List<String> makeBookBibTex(DAO<BookRef> bookDAO, List<String> lines);

    public List<String> makeArticleBibTex(DAO<ArticleRef> articleDAO, List<String> lines);

    public List<String> makeInproceedingsBibTex(DAO<InproceedingsRef> inDAO, List<String> lines);
    
}