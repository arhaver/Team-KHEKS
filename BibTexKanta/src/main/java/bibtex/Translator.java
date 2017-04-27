
package bibtex;

import database.DAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;
import reference.Reference;

public class Translator implements IBibtexTranslator {
    
    @Override
    public List<String> makeBookBibTex(DAO<BookRef> bookDAO, List<String> lines) {
        if(bookDAO == null) return lines;
        lines = makeBookRef(lines, bookDAO);
        return lines;
    }
    
    @Override
    public List<String> makeArticleBibTex(DAO<ArticleRef> articleDAO, List<String> lines) {
        if(articleDAO == null) return lines;
        lines = makeArticleRef(lines, articleDAO);
        return lines;
    }
    
    @Override
    public List<String> makeInproceedingsBibTex(DAO<InproceedingsRef> inDAO, List<String> lines) {
        if(inDAO == null) return lines;
        lines = makeInRef(lines, inDAO);
        return lines;
    }
    
    private List<String> makeBookRef(List<String> list, DAO dao) {
        List<BookRef> refs = dao.findAll();
        for(Reference ref : refs) {
            Map<String, String> strings = ref.getAllFields();
            list.add("@book{" +strings.get("bibTexId") +",");
            list.add("  author = {" +strings.get("authors") +"},");
            list.add("  title = {" +strings.get("title") +"},");
            list.add("  year = {" +ref.getYear() +"},");
            list.add("  publisher = {" +strings.get("publisher") +"},");
            list.add("}\n");
        }
        return list;
    }
    
    private List<String> makeArticleRef(List<String> list, DAO dao) {
        List<ArticleRef> refs = dao.findAll();
        for(Reference ref : refs) {
            Map<String, String> strings = ref.getAllFields();
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

    private List<String> makeInRef(List<String> list, DAO<InproceedingsRef> inDAO) {
        List<InproceedingsRef> refs = inDAO.findAll();
        for(Reference ref : refs) {
            Map<String, String> strings = ref.getAllFields();
            list.add("@inproceedings{" +strings.get("bibTexId") +",");
            list.add("  author = {" +strings.get("authors") +"},");
            list.add("  title = {" +strings.get("title") +"},");
            list.add("  booktitle = {" +strings.get("booktitle") +"},");
            list.add("  pages = {" +strings.get("pages") +"},");
            list.add("  organization = {" +strings.get("organization") +"},");
            list.add("  month = " +strings.get("month") +",");
            list.add("  year = {" +ref.getYear() +"},");
            list.add("  publisher = {" +strings.get("publisher") +"},");
            list.add("  address = {" +strings.get("address") +"}");
            list.add("}\n");
        }
        return list;
    }
}