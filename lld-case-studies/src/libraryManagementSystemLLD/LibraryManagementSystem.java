import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import repo.*;
import models.*;
import factory.ItemFactory;

public class LibraryManagementSystem {
    private static final LibraryManagementSystem INSTANCE = new LibraryManagementSystem();
    private final BookCatalog catalog= new BookCatalog();
    private final MemberRepo members = new MemberRepo();
    private final Map<String,BookCopy> copies= new HashMap<>();

    private LibraryManagementSystem() {}
    public static LibraryManagementSystem getInstance() { return INSTANCE; }

    //addItem
    public List<BookCopy> addItem(ItemType type,String id, String title,String author, int numCopies){
        List<BookCopy> bookCopies= new ArrayList<>();
        LibraryItem item= ItemFactory.createItem(type,id,title,author);
        catalog.addItem(id, item);
        for(int i=0;i<numCopies;i++){
            String copyId=id + "-c" + (i+1);
            BookCopy copy= new BookCopy(item, copyId);
            copies.put(copyId, copy);
            bookCopies.add(copy);
            item.addCopy(copy);
        }
        System.out.println("Added " + numCopies + " copies of '" + title + "'");
        return bookCopies;
    }

    //addMember
    public Member addMember(String id, String name){
        Member newMember= new Member(id, name);
        members.addMember(newMember);
        return newMember;
    }

    //checkout
    public void checkout(String memberId, String copyId){
        Member member= members.getMember(memberId);
        BookCopy copy= copies.get(copyId);
        if(member!=null && copy!=null){
            copy.checkout(member);
        }else{
            System.out.println("Invalid member or copy ID.");
        }
    }

    //return item
    public void returnItem(String copyId){
        BookCopy copy= copies.get(copyId);
        if(copy!=null){
            copy.returnItem();
        }else{
            System.out.println("Error: Invalid copy ID.");
        }
    }

    //placeHold
    public void placeHold(String memberId, String itemId){
        Member member= members.getMember(memberId);
        LibraryItem item= catalog.get(itemId);

        if(member!=null && item!=null){
            item.getCopies().stream().filter(c->!c.isAvailable()).findFirst().ifPresent(copy->copy.placeHold(member));
        }
    }

    //search
    public List<BookCopy> search(String title){
        return catalog.searchAvailableCopiesForTitle(title);
    }

    //printCatalog
    public void printCatalog() {
        System.out.println("\n--- Library Catalog ---");
        catalog.getCatalog().values().forEach(item -> System.out.printf("ID: %s, Title: %s, Author/Publisher: %s, Available: %d\n",
                item.getId(), item.getTitle(), item.getAuthorOrPublisher(), item.getAvailableCopyCount()));
        System.out.println("-----------------------\n");
    }
}
