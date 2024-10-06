import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, String> variables = new HashMap<>();

    public void setVariable(String name, String value) {
        variables.put(name, value);
    }

    public String getVariable(String name) {
        return variables.getOrDefault(name, "undefined");
    }
}
