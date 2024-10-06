import java.util.HashSet;
import java.util.Set;

public class Keywords {
    private static final Set<String> keywords = new HashSet<>();

    static {
        // C++ keywords
        keywords.add("class");
        keywords.add("function");
        keywords.add("int");
        keywords.add("print");
        keywords.add("bool");
        keywords.add("void");
        keywords.add("char");
        keywords.add("short");
        keywords.add("long");
        keywords.add("float");
        keywords.add("double");
        keywords.add("if");
        keywords.add("else");
        keywords.add("for");
        keywords.add("while");
        keywords.add("return");
        keywords.add("cin");
        keywords.add("cout");
        // Add more keywords as needed
    }

    public static boolean isKeyword(String word) {
        return keywords.contains(word);
    }
}
