package Lab6;

import java.util.ArrayList;

public class Topic {
    private String title;
    private ArrayList<String> content = new ArrayList<>();
    private ArrayList<Option> options = new ArrayList<>();

    public Topic(String title) {
        this.title = title;
    }

    public Option getOption(int i) {
        return options.get(i);
    }

    public int getNumberOfOptions() {
        return options.size();
    }

    public boolean hasOptions() {
        return !options.isEmpty();
    }

    public String[] getContent() {
        String[] arr = new String[content.size()];
        content.toArray(arr);
        return arr;
    }

    public Object getTitle() {
        return title;
    }

    public void addContent(String str) {
        content.add(str);
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public String toString() {
        return title;
    }
}
