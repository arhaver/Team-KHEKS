package reference;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractReference implements Reference {

    private final int CURRENT_YEAR = 2017;
    protected Map<String, String> fields;
    protected int year;

    public AbstractReference() {
        fields = new HashMap<>();

        fields.put("title", null);
        fields.put("authors", null);
        fields.put("bibTexId", null);
        year = -1;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public boolean setYear(int year) {
        if (isValidYear(year)) {
            this.year = year;
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidYear(int year) {
        return (0 < year && year <= CURRENT_YEAR);
    }

    @Override
    public boolean isValidString(String field, String input) {
        int minLength;
        switch (field) {
            case "volume":
                minLength = 0;
                break;
            case "number":
                minLength = 0;
                break;
            default:
                minLength = 2;
        }
        return input != null && input.length() > minLength;
    }

    @Override
    public abstract boolean readyForDb();

    @Override
    public Map<String, String> getAllFields() {
        return fields;
    }

    @Override
    public String getField(String fieldName) {
        if (fields.containsKey(fieldName)) {
            return fields.get(fieldName);
        }
        return null;
    }

    @Override
    public boolean setField(String fieldName, String value) {
        if (fields.containsKey(fieldName) && isValidString(fieldName, value)) {
            fields.put(fieldName, value);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        String response = "";
        if (fields.get("title") != null) {
            response += "title: " + fields.get("title") + "\n";
        }
        if (fields.get("authors") != null) {
            response += "authors: " + fields.get("authors") + "\n";
        }
        if (fields.get("publisher") != null) {
            response += "publisher: " + fields.get("publisher") + "\n";
        }
        if (fields.get("address") != null) {
            response += "address: " + fields.get("address") + "\n";
        }
        if (fields.get("booktitle") != null) {
            response += "booktitle: " + fields.get("booktitle") + "\n";
        }
        if (fields.get("bibTexId") != null) {
            response += "BibTexId: " + fields.get("bibTexId") + "\n";
        }
        if (fields.get("volume") != null) {
            response += "volume: " + fields.get("volume") + "\n";
        }

        if (fields.get("number") != null) {
            response += "number: " + fields.get("number") + "\n";
        }

        if (fields.get("journal") != null) {
            response += "journal: " + fields.get("journal") + "\n";
        }

        if (fields.get("pages") != null) {
            response += "pages: " + fields.get("pages") + "\n";
        }

        if (this.year > 0) {
            response += "year: " + this.year + "\n";
        }
        return response;

    }

}
