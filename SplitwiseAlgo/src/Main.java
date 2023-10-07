import algorithms.GreedyAlgorithm;
import algorithms.RoundTrip;
import algorithms.SettlementStrategy;
import models.ExpensePaidBy;
import models.ExpenseSharedBy;
import models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List <ExpensePaidBy> paidByList=new ArrayList<>();
        List <ExpenseSharedBy> sharedByList=new ArrayList<>();

        paidByList.add(new ExpensePaidBy("A", 4000));
        paidByList.add(new ExpensePaidBy("B", 2000));
        paidByList.add(new ExpensePaidBy("C", 6000));

        sharedByList.add(new ExpenseSharedBy("A", 1000));
        sharedByList.add(new ExpenseSharedBy("B", 1000));
        sharedByList.add(new ExpenseSharedBy("C", 1000));
        sharedByList.add(new ExpenseSharedBy("D", 1000));
        sharedByList.add(new ExpenseSharedBy("A", 1000));
        sharedByList.add(new ExpenseSharedBy("B", 1000));
        sharedByList.add(new ExpenseSharedBy("C", 3000));
        sharedByList.add(new ExpenseSharedBy("D", 3000));


        List <Transaction> transactions=settleExpense(paidByList, sharedByList, new GreedyAlgorithm());

        System.out.println(transactions);
    }

    static List<Transaction> settleExpense(List <ExpensePaidBy> paidByList, List <ExpenseSharedBy> sharedByList, SettlementStrategy settlementStrategy){

        // create balance sheet

        HashMap <String, Integer> balanceSheet = new HashMap<>();
        for (ExpensePaidBy payer: paidByList){
            String name=payer.getName();
            int amount=payer.getAmount();
            if (balanceSheet.containsKey(name)){
                balanceSheet.put(name, balanceSheet.get(name)+amount);
            }else{
                balanceSheet.put(name, + amount);
            }
        }

        for (ExpenseSharedBy sharedBy: sharedByList){
            String name=sharedBy.getName();
            int amount=sharedBy.getAmount();
            if (balanceSheet.containsKey(name)){
                balanceSheet.put(name, balanceSheet.get(name)-amount);
            }else{
                balanceSheet.put(name, - amount);
            }
        }

        // settlement via greedy or roundtrip algorithms

        List <Transaction> res= settlementStrategy.settleBalance(balanceSheet);

        return res;

    }
}