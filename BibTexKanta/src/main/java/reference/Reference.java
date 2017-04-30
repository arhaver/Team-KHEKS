/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reference;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author mikko
 */
public interface Reference {

    public Map<String, String> getAllFields();

    public String getField(String fieldName);

    public boolean setField(String fieldName, String value);

    public int getYear();

    public boolean setYear(int year);

    public boolean readyForDb();

    public boolean isValidString(String fieldName, String value);

    public boolean isValidYear(int value);
        
    public boolean addTag (String tag);
    
    public boolean removeTag (String tag);
    
    public Set<String> getTags();
    
    public void setTags(Set<String> tags);
    
    public boolean hasTag(String tag);
}
