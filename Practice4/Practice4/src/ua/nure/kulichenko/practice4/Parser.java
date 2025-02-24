package ua.nure.kulichenko.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser implements Iterable<String> {

    private static final String ENCODING = "Cp1251";
    private static final String REGEXP = "\\p{Lu}.*?\\.";
    private Matcher patternMatcher;

    public Matcher getPatternMatcher() {
        return patternMatcher;
    }

    public final void setPatternMatcher(Matcher patternMatcher) {
        this.patternMatcher = patternMatcher;
    }

    public Parser(File file) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile(REGEXP);
        Scanner s = new Scanner(file, ENCODING);
        while (s.hasNextLine()) {
            sb.append(s.nextLine()).append(" ");
            setPatternMatcher(p.matcher(sb));
        }
        s.close();
    }

    @Override
    public Iterator<String> iterator() {
        return new ParserIterator(getPatternMatcher());
    }

    private static class ParserIterator implements Iterator<String> {

        private Matcher matcher;

        public ParserIterator(Matcher matcher) {
            this.matcher = matcher;
        }

        @Override
        public boolean hasNext() {
            return matcher.find();
        }

        @Override
        public String next() {
            return matcher.group().replace("  ", " ");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
