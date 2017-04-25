package commandlineUI.menu.adder;

import database.DAO;
import io.IO;
import java.util.Map;
import reference.ArticleRef;
import reference.Reference;

public class AbstractEditor extends AbstractAdder{

    private ArticleRef trueRef;
    private ArticleRef editRef;
    
    public AbstractEditor(DAO dao, IO io, String[] startLines, String[] askPrompts) {
        super(dao, io, startLines, askPrompts);
    }

    public void setRef(ArticleRef ref){
        this.trueRef = ref;
        this.editRef = new ArticleRef();
        
        Map<String, String> allFields = trueRef.getAllFields();
        
        for(String fieldName : allFields.keySet()){
            editRef.setField(fieldName, allFields.get(fieldName));
        }
    }

    public ArticleRef getTrueRef() {
        return trueRef;
    }

    public ArticleRef getEditRef() {
        return editRef;
    }

    @Override
    protected Reference createReferenceSkeleton() {
        return editRef;
    }
    
}
