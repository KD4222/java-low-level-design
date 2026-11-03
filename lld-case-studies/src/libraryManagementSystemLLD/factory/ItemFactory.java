package factory;

import models.*;

public class ItemFactory {
    public static LibraryItem createItem(ItemType type, String id, String title, String author){
        switch(type){
            case BOOK: return new Book(id,title,author);
            case MAGAZINE : return new Magazine(id,title,author);
            default: throw new IllegalArgumentException("Unknown item type");
        }
    }
}
