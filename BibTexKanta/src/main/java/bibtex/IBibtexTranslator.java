package bibtex;

import database.DAO;
import java.util.List;

/*
Rajapinta Stringijonon luomiseen DAOista
*/
public interface IBibtexTranslator {
    
    public List<String> bibTex(DAO... daos);
    
}
