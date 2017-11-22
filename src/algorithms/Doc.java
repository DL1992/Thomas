package algorithms;

public class Doc {
    private int docLength;
    private String docName;
    private String content;

    public Doc(int docLength, String docName, String content) {
        this.docLength = docLength;
        this.docName = docName;
        this.content = content;
    }

    public int getDocLength() {
        return docLength;
    }

    public String getDocName() {
        return docName;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("doc name is: %s. doc length is: %d. doc content is: %s", docName, docLength, content);
    }
}
