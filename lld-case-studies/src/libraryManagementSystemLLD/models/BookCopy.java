package models;

import models.state.*;

public class BookCopy{
    private final LibraryItem item;
    private final String copyId;
    private ItemState currentState;

	public BookCopy(LibraryItem item,String id) {
		this.item = item;
		this.copyId = id;
		this.currentState= new AvailableState();
	}
    
    public void checkout(Member member){currentState.checkout(this,member);}
    public void returnItem(){currentState.returnItem(this);}
    public void placeHold(Member member){currentState.placeHold(this,member);}

    public void setState(ItemState state){this.currentState=state;}
    public String getId(){return copyId;}
    public LibraryItem getItem(){return item;}
    public boolean isAvailable(){return currentState instanceof AvailableState;}
}