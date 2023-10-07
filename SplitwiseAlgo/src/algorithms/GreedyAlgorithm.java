package algorithms;

import models.Transaction;
import models.UserForPQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyAlgorithm implements SettlementStrategy {

    @Override
    public List<Transaction> settleBalance(HashMap<String, Integer> balanceSheet) {
        PriorityQueue<UserForPQ> paidLess = new PriorityQueue<>();
        PriorityQueue<UserForPQ> paidMore = new PriorityQueue<>();
        List <Transaction> res= new ArrayList<>();

        for (String key: balanceSheet.keySet()){
            String user= key;
            int balance=balanceSheet.get(user);

            if (balance<0){
                paidLess.add(new UserForPQ(user, -balance));
            }else if (balance>0){
                paidMore.add(new UserForPQ(user, balance));
            }
        }

        while (paidLess.size()>0 && paidMore.size()>0){
            UserForPQ fromUser= paidLess.remove();
            UserForPQ toUser=paidMore.remove();

            int fromAmount = fromUser.getAmount();
            int toAmount= toUser.getAmount();

            String from= fromUser.getName();
            String to=toUser.getName();

            int tranAmount = Math.min(fromAmount, toAmount);

            Transaction t = new Transaction(from,to, tranAmount);

            res.add(t);

            fromAmount-=tranAmount;
            toAmount-=tranAmount;

            if (fromAmount>0){
                paidLess.add(new UserForPQ(from, fromAmount));
            }
            if (toAmount>0){
                paidMore.add(new UserForPQ(to, toAmount));
            }
        }

        return res;
    }
}
