package models.state;

import models.BookCopy;
import models.Member;
import service.TransactionService;

public class CheckedOutState implements ItemState {

	@Override
	public void checkout(BookCopy copy, Member member) {
        System.out.println("Cannot checkout an alrady checked out book");
	}

	@Override
	public void returnItem(BookCopy copy) {
        TransactionService.getInstance().endLoan(copy);
        System.out.println(copy.getId()+"returned");

        if(copy.getItem().hasObservers()){
            copy.setState(new OnHoldState());
            copy.getItem().notifyObservers();
        }else{
            copy.setState(new AvailableState());
        }
	}

	@Override
	public void placeHold(BookCopy copy, Member member) {
        copy.getItem().addObserver(member);
        System.out.println(member.getName() + " placed a hold on '" + copy.getItem().getTitle() + "'");
	}
    
}
