package models;

import java.time.LocalDate;

public class BookLoan {
    private final BookCopy book;
    private final Member member;
    @SuppressWarnings("unused")
	private final LocalDate checkoutDate;

    //defaulting to max 7 days loan

	public BookLoan(BookCopy book, Member member) {
        this.book=book;
        this.member=member;
        this.checkoutDate= LocalDate.now();
    }

	public BookCopy getCopy() {
		return book;
	}
	public Member getMember() {
		return member;
	}
}
