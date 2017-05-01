
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
            if(strings.get("authors") != null) list.add("  author = {" +makeSpecialChars(strings.get("authors")) +"},");
            if(strings.get("title") != null) list.add("  title = {" +makeSpecialChars(strings.get("title")) +"},");
            if(ref.getYear() != -1) list.add("  year = {" +ref.getYear() +"},");
            if(strings.get("publisher") != null) list.add("  publisher = {" +makeSpecialChars(strings.get("publisher")) +"},");
            if(strings.get("address") != null) list.add("   address = {" +makeSpecialChars(strings.get("address")) +"},");
            if(strings.get("series") != null) list.add("    series = {" +makeSpecialChars(strings.get("series")) +"},");
            if(strings.get("edition") != null) list.add("   edition = {" +makeSpecialChars(strings.get("edition")) +"},");
            if(strings.get("month") != null) list.add(" month = {" +makeSpecialChars(strings.get("month")) +"},");
            if(strings.get("note") != null) list.add("  note = {" +makeSpecialChars(strings.get("note")) +"}");
            list.add("}\n");
        }
        return list;
    }
    
    private List<String> makeArticleRef(List<String> list, DAO dao) {
        List<ArticleRef> refs = dao.findAll();
        for(Reference ref : refs) {
            Map<String, String> strings = ref.getAllFields();
            list.add("@article{" +strings.get("bibTexId") +",");
            if(strings.get("authors") != null) list.add("  author = {" +makeSpecialChars(strings.get("authors")) +"},");
            if(strings.get("title") != null) list.add("  title = {" +makeSpecialChars(strings.get("title")) +"},");
            if(strings.get("journal") != null) list.add("  journal = {" +makeSpecialChars(strings.get("journal")) +"},");
            if(strings.get("volume") != null) list.add("  volume = {" +makeSpecialChars(strings.get("volume")) +"},");
            if(strings.get("number") != null) list.add("  number = {" +makeSpecialChars(strings.get("number")) +"},");
            if(strings.get("month") != null) list.add("  month = " +makeSpecialChars(strings.get("month")) +",");
            if(ref.getYear() != -1) list.add("  year = {" +ref.getYear() +"},");
            if(strings.get("pages") != null) list.add("  pages = {" +makeSpecialChars(strings.get("pages")) +"},");
            if(strings.get("publisher") != null) list.add("  publisher = {" +makeSpecialChars(strings.get("publisher")) +"},");
            if(strings.get("address") != null) list.add("  address = {" +makeSpecialChars(strings.get("address")) +"},");
            if(strings.get("note") != null) list.add("  note = {" +makeSpecialChars(strings.get("note")) +"}");
            list.add("}\n");
        }
        return list;
    }

    private List<String> makeInRef(List<String> list, DAO<InproceedingsRef> inDAO) {
        List<InproceedingsRef> refs = inDAO.findAll();
        for(Reference ref : refs) {
            Map<String, String> strings = ref.getAllFields();
            list.add("@inproceedings{" +strings.get("bibTexId") +",");
            if(strings.get("authors") != null) list.add("  author = {" +makeSpecialChars(strings.get("authors")) +"},");
            if(strings.get("title") != null) list.add("  title = {" +makeSpecialChars(strings.get("title")) +"},");
            if(strings.get("booktitle") != null) list.add("  booktitle = {" +makeSpecialChars(strings.get("booktitle")) +"},");
            if(strings.get("pages") != null) list.add("  pages = {" +makeSpecialChars(strings.get("pages")) +"},");
            if(strings.get("organization") != null) list.add("  organization = {" +makeSpecialChars(strings.get("organization")) +"},");
            if(strings.get("month") != null) list.add("  month = " +makeSpecialChars(strings.get("month")) +",");
            if(ref.getYear() != -1) list.add("  year = {" +ref.getYear() +"},");
            if(strings.get("publisher") != null) list.add("  publisher = {" +makeSpecialChars(strings.get("publisher")) +"},");
            if(strings.get("address") != null) list.add("  address = {" +makeSpecialChars(strings.get("address")) +"},");
            if(strings.get("editor") != null) list.add("    editor = {" +makeSpecialChars(strings.get("editor")) +"},");
            if(strings.get("number") != null) list.add("    number = {" +makeSpecialChars(strings.get("number")) +"},");
            if(strings.get("series") != null) list.add("    series = {" +makeSpecialChars(strings.get("series")) +"},");
            if(strings.get("note") != null) list.add("  note = {" +makeSpecialChars(strings.get("note")) +"}");
            list.add("}\n");
        }
        return list;
    }
    
    private String makeSpecialChars(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == 'ä' || chars[i] == 'Ä') {
                if(chars[i] == 'ä') {
                    sb.append("\\\"{a}");
                    continue;
                } else {
                    sb.append("\\\"{A}");
                    continue;
                }
            } if(chars[i] == 'ö' || chars[i] == 'Ö') {
                if(chars[i] == 'ö') {
                    sb.append("\\\"{o}");
                    continue;
                } else {
                    sb.append("\\\"{O}");
                    continue;
                }
            } if(chars[i] == 'ø' || chars[i] == 'Ø') {
                if(chars[i] == 'ø') {
                    sb.append("\\{o}");
                    continue;
                } else {
                    sb.append("\\{O}");
                    continue;
                }
            } if(chars[i] == 'å' || chars[i] == 'Å') {
                if(chars[i] == 'å') {
                    sb.append("\\a{a}");
                    continue;
                } else {
                    sb.append("\\a{A}");
                    continue;
                }
            } if(chars[i] == 'ê') {
                sb.append("\\^{e}");
                continue;
            } if(chars[i] == 'í') {
                sb.append("\\'{i}");
                continue;
            } if(chars[i] == 'ß') {
                sb.append("\\s{s}");
                continue;
            }  
            sb.append(chars[i]);
        }
        return sb.toString();
    }
}