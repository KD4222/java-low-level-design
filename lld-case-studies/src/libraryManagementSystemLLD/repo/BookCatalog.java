package repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import models.*;

public class BookCatalog {
    private final Map<String,LibraryItem> catalog= new HashMap<>();

    public Map<String, LibraryItem> getCatalog() {
        return catalog;
    }

    public void addItem(String id,LibraryItem item){
        catalog.put(id, item);
    }

    public LibraryItem get(String id){
        return catalog.get(id);
    }

    public List<BookCopy> searchAvailableCopiesForTitle(String title){
        return catalog.get(title).getCopies().stream().filter(BookCopy::isAvailable).collect(Collectors.toList());
    }

}
