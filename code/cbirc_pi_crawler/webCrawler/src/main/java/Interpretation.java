import lombok.Data;

@Data
public class Interpretation {
    String title;
    String abstactContent;
    String text;
    int docId;

    public Interpretation(String title, String abstactContent, String text, int docId) {
        this.title = title;
        this.abstactContent = abstactContent;
        this.text = text;
        this.docId = docId;
    }

    @Override
    public String toString() {
        return "Interpretation{" +
                "title='" + title + '\'' +
                ", abstactContent='" + abstactContent + '\'' +
                ", text='" + text + '\'' +
                ", docId=" + docId +
                '}';
    }

}
