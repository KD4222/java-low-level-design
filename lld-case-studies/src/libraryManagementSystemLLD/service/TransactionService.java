package service;

import java.util.HashMap;
import java.util.Map;
import models.*;

public class TransactionService {
    public static final TransactionService INSTANCE = new TransactionService();
    private final Map<String,BookLoan> activeLoans= new HashMap<>();

    private TransactionService(){};

	public static TransactionService getInstance() {
		return INSTANCE;
	}
    
    public void createLoan(BookCopy copy, Member member){
        if(activeLoans.containsKey(copy.getId())){
            throw new IllegalArgumentException("The copy is already on loan");
        }
        BookLoan loan= new BookLoan(copy, member);
        activeLoans.put(copy.getId(),loan);
        member.addLoan(loan);
    }

    public void endLoan(BookCopy copy){
        BookLoan loan = activeLoans.remove(copy.getId());
        if(loan!=null){
            loan.getMember().getLoans().remove(loan);
        }
    }
}
