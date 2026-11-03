import service.BalanceService;
import service.SplitService;
import repository.UserRepo;
import models.User;
import models.split.SplitType;
import java.util.List;
import models.Expense;
import models.split.Split;

public class ExpenseManager {
    final BalanceService balanceService = new BalanceService();
    final SplitService splitService = new SplitService();
    final UserRepo userRepo = new UserRepo();

    void addExpense(String paidBy, double amount, String description, List<String> participants, SplitType splitType, List<Double> splitData){
        User paidByUser = userRepo.getUserById(paidBy);
        if(paidByUser == null){
            paidByUser=addUser(java.util.UUID.randomUUID().toString(), "0000000000", paidBy);
        }
        List<User> participantUsers = participants.stream().map(id -> {
            if(userRepo.getUserById(id)==null){
                addUser(java.util.UUID.randomUUID().toString(), "0000000000", id);
            }
            return userRepo.getUserById(id);
        }).toList();
        Expense expense = new Expense(amount, paidByUser, participantUsers, description);
        List<Split> splits;
        switch(splitType){
            case EQUAL:
                splits = splitService.createEqualSplits(expense, splitType);
                break;
            case EXACT:
                splits = splitService.createExactSplits(expense, splitType, splitData);
                break;
            case PERCENT:
                splits = splitService.createPercentageSplits(expense, splitType, splitData);
                break;
            default:
                throw new IllegalArgumentException("Invalid Split Type");
        }
        System.out.println("Expense added: " + description + " of amount " + amount + " paid by " + paidByUser.getName() + " split among " + participants);
        System.out.println("Splits: ");
        for(Split split : splits){
            System.out.println(split.getUser().getId() + " owes " + split.getAmount());
        }
        expense.assignSplits(splits);
        balanceService.addBalance(expense);
    }
    
    User addUser(String name, String phone, String id){
        User user = new User(name, phone, id);
        userRepo.addUser(user);
        return user;
    }

    void showBalance(String userId){
        User user = userRepo.getUserById(userId);
        balanceService.showBalance(user);
    }

    void showAllBalances(){
        balanceService.showAllBalances();
    }

}
