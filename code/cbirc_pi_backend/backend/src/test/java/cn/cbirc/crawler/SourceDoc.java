package cn.cbirc.crawler;

public class SourceDoc {
    String title;
    String abstractContent;
    String text;
    int docId;
    String indexNo;
    String interviewTypeName;
    String agencyTypeName;
    String builddate;
    String docSubtitle;
    String documentNo ;

    public SourceDoc(String title, String abstractContent, String text,
                     int docId, String indexNo, String interviewTypeName,
                     String agencyTypeName, String builddate, String docSubtitle,
                     String documentNo) {
        this.title = title;
        this.abstractContent = abstractContent;
        this.text = text;
        this.docId = docId;
        this.indexNo = indexNo;
        this.interviewTypeName = interviewTypeName;
        this.agencyTypeName = agencyTypeName;
        this.builddate = builddate;
        this.docSubtitle = docSubtitle;
        this.documentNo = documentNo;
    }

    @Override
    public String toString() {
        return "SourceDoc{" +
                "title='" + title + '\'' +
                ", abstractContent='" + abstractContent + '\'' +
                ", text='" + text + '\'' +
                ", docId=" + docId +
                ", indexNo='" + indexNo + '\'' +
                ", interviewTypeName='" + interviewTypeName + '\'' +
                ", agencyTypeName='" + agencyTypeName + '\'' +
                ", builddate='" + builddate + '\'' +
                ", docSubtitle='" + docSubtitle + '\'' +
                ", documentNo='" + documentNo + '\'' +
                '}';
    }
}
