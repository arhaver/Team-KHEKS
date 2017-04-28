package service.search.matcher;

import reference.Reference;

public class YearMatcher implements Matcher{
    
    private int year;

    public YearMatcher(int year) {
        this.year = year;
    }

    @Override
    public boolean matches(Reference ref) {
        return year == ref.getYear();
    }

    public int getYear() {
        return year;
    }
}
