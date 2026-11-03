package models.state;

import models.BookCopy;
import models.Member;
import service.TransactionService;

public class OnHoldState implements ItemState {

	@Override
	public void checkout(BookCopy copy, Member member) {
		if(copy.getItem().isObserver(member)){
            TransactionService.getInstance().createLoan(copy,member);
            copy.getItem().removeObserver(member);
            copy.setState(new CheckedOutState());
            System.out.println("Hold fulfilled. " + copy.getId() + " checked out by " + member.getName());
        }else{
            System.out.println("This item is on hold for another member.");
        }
	}

	@Override
	public void returnItem(BookCopy copy) {
		System.out.println("Invalid action. Item is on hold, not checked out.");
	}

	@Override
	public void placeHold(BookCopy copy, Member member) {
		System.out.println("Item is already on hold.");
	}
    
}
