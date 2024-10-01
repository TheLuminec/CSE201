import java.util.List;

public class Description {
    private String text;
    private List<String> conditions;

    public String getText() {
        return text;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }
}
