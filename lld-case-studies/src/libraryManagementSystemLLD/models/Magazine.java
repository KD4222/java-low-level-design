package models;

public class Magazine extends LibraryItem {
    private final String publisher;

    public Magazine(String id, String title, String author){
        super(id,title);
        this.publisher=author;
    }

    @Override
    public String getAuthorOrPublisher() {return publisher;}
}
