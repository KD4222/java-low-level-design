package models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final String memberId;
    private List<BookLoan> loans = new ArrayList<>();
    private final String name;

	public Member(String memberId, String name) {
		this.memberId = memberId;
		this.name = name;
	}

    public void update(LibraryItem item){
        System.out.println("NOTIFICATION FOR: " + name + ": the book: " + item.getTitle() + " is now available");
    }

	public String getMemberId() {
		return memberId;
	}

	public List<BookLoan> getLoans() {
		return loans;
	}

	public void addLoan(BookLoan loan) {
		this.loans.add(loan);
	}

    public void removeLoan(BookLoan loan){
        this.loans.remove(loan);
    }

	public String getName() {
		return name;
	}
    
}
