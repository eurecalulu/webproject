package cn.cbirc.crawler;

public class Interpretation {
    String title;
    String abstactContent;
    String text;
    String time;
    int docId;

    public Interpretation(String title, String abstactContent, String text, int docId,String time) {
        this.title = title;
        this.abstactContent = abstactContent;
        this.text = text;
        this.docId = docId;
        this.time=time;
    }

    @Override
    public String toString() {
        return "Interpretation{" +
                "title='" + title + '\'' +
                ", abstactContent='" + abstactContent + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", docId=" + docId +
                '}';
    }
}
