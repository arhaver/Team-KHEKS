
package bibtex;

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
//            if(dao instanceof BookDAO) list = makeBookRef(list, dao);
//            else if(dao instanceof ArticleDAO) list = makeArticleRef(list, dao);
//            else throw new Exception("Tyhjä lista");
        }
  
        return list;
    }
    
    @Override
    public List<String> makeBookBibTex(DAO<BookRef> bookDAO, List<String> lines) throws Exception{
        if(bookDAO == null) return lines;
        lines = makeBookRef(lines, bookDAO);
        return lines;
    }
    
    @Override
    public List<String> makeArticleBibTex(DAO<ArticleRef> articleDAO, List<String> lines) throws Exception{
        if(articleDAO == null) return lines;
        lines = makeArticleRef(lines, articleDAO);
        return lines;
    }
    
    private List<String> makeBookRef(List<String> list, DAO dao) throws Exception{
        List<BookRef> refs = dao.findAll();
        for(Reference ref : refs) {
            Map<String, String> strings = ref.getAllFields();
            //if(strings.get("bibTexId") == null) strings.put("bibTexId", generateId(strings, ref));
            list.add("@book{" +strings.get("bibTexId") +",");
            list.add("  author = {" +strings.get("authors") +"},");
            list.add("  title = {" +strings.get("title") +"},");
            list.add("  year = {" +ref.getYear() +"},");
            list.add("  publisher = {" +strings.get("publisher") +"},");
            list.add("}\n");
        }
        return list;
    }
    
    private List<String> makeArticleRef(List<String> list, DAO dao) throws Exception{
        List<ArticleRef> refs = dao.findAll();
        for(Reference ref : refs) {
            Map<String, String> strings = ref.getAllFields();
            //if(strings.get("bibTexId") == null) strings.put("bibTexId", generateId(strings, ref));
            list.add("@article{" +strings.get("bibTexId") +",");
            list.add("  author = {" +strings.get("author") +"},");
            list.add("  title = {" +strings.get("title") +"},");
            list.add("  journal = {" +strings.get("journal") +"},");
            list.add("  volume = {" +strings.get("volume") +"},");
            list.add("  number = {" +strings.get("number") +"},");
            list.add("  month = " +strings.get("month") +",");
            list.add("  year = {" +ref.getYear() +"},");
            list.add("  pages = {" +strings.get("pages") +"},");
            list.add("  publisher = {" +strings.get("publisher") +"},");
            list.add("  address = {" +strings.get("address") +"}");
            list.add("}\n");
        }
        return list;
    }
    
    private String generateId(Map<String, String> strings, Reference ref) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(strings.get("author").charAt(0));
        System.out.println("String Builder luotu");
        if(strings.get("author").contains(",") || strings.get("author").contains("and")) {
            String authors = strings.get("author");
            int i = authors.indexOf(",");
            if(i == -1) i = authors.indexOf("and");
            String cut = authors.substring(i);
            System.out.println("ifissä: " +cut);
            while (cut.contains(",") || cut.contains("and")) {
                i = cut.indexOf(" ");
                cut = cut.substring(i);
                sb.append(cut.charAt(1));
                System.out.println("while: " +i);
            }
        }
        String year = Integer.toString(ref.getYear());
        if(year.length() <= 2) sb.append(year);
        else sb.append(year.subSequence(year.length() - 3, year.length() - 1));
        if(sb.length() == 0) throw new Exception("Ongelma Id:n luomisessa");
        return sb.toString();
    }
}
