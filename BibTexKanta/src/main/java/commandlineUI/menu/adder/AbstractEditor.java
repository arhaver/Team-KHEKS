package commandlineUI.menu.adder;

import database.DAO;
import io.IO;
import java.util.Map;
import reference.ArticleRef;
import reference.Reference;

public class AbstractEditor extends AbstractAdder{

    private Reference trueRef;
    private Reference editRef;
    
    public AbstractEditor(DAO dao, IO io, String[] startLines, String[] askPrompts) {
        super(dao, io, startLines, askPrompts);
    }

    public void setRef(Reference ref){
        this.trueRef = ref;
        this.editRef = new ArticleRef();
        
        Map<String, String> allFields = trueRef.getAllFields();
        
        for(String fieldName : allFields.keySet()){
            editRef.setField(fieldName, allFields.get(fieldName));
        }
        
        editRef.setYear(trueRef.getYear());
    }

    public Reference getTrueRef() {
        return trueRef;
    }

    public Reference getEditRef() {
        return editRef;
    }

    @Override
    protected Reference createReferenceSkeleton() {
        return editRef;
    }
    
}
