
package bibtex;

import database.ArticleDAO;
import database.BookDAO;
import database.DAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import reference.ArticleRef;
import reference.BookRef;
import reference.Reference;

public class Translator implements IBibtexTranslator {

    
    @Override
    public List<String> bibTex(DAO... daos) throws Exception{
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < daos.length; i++) {
            DAO dao = daos[i];
            if(dao instanceof BookDAO) list = makeBookRef(list, dao);
            else if(dao instanceof ArticleDAO) list = makeArticleRef(list, dao);
            else throw new Exception("Tyhjä lista");
        }
  
        return list;
    }
    
    private List<String> makeBookRef(List<String> list, DAO dao) {
        List<BookRef> refs = dao.findAll();
        for(Reference ref : refs) {
            Map<String, String> strings = ref.getAllFields();
            list.add("@book{" +strings.get("bibTexId") +",\n");
            list.add("author = {" +strings.get("authors") +"},\n");
            list.add("title = {" +strings.get("title") +"},\n");
            list.add("year = {" +strings.get("year") +"},\n");
            list.add("publisher = {" +strings.get("publisher") +"},\n");
            list.add("}\n");
        }
        return list;
    }
    
    private List<String> makeArticleRef(List<String> list, DAO dao) {
        List<ArticleRef> refs = dao.findAll();
        for(Reference ref : refs) {
            Map<String, String> strings = ref.getAllFields();
            list.add("@article{" +strings.get("bibTexId") +",\n");
            list.add("author = {" +strings.get("author") +"},\n");
            list.add("title = {" +strings.get("title") +"},\n");
            list.add("journal = {" +strings.get("journal") +"},\n");
            list.add("volume = {" +strings.get("volume") +"},\n");
            list.add("number = {" +strings.get("number") +"},\n");
            list.add("month = " +strings.get("month") +",\n");
            list.add("year = {" +strings.get("year") +"},\n");
            list.add("pages = {" +strings.get("pages") +"},\n");
            list.add("publisher = {" +strings.get("publisher") +"},\n");
            list.add("address = {" +strings.get("address") +"}\n");
            list.add("}\n");
        }
        return list;
    }
}
