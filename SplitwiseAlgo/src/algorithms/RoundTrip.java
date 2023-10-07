package algorithms;

import models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoundTrip implements SettlementStrategy {
    @Override
    public List<Transaction> settleBalance(HashMap<String, Integer> balanceSheet) {
        List <Transaction> result= new ArrayList<>();

        ArrayList<String> users= new ArrayList<>(balanceSheet.keySet());
        for (int i=0; i<users.size()-1; i++){
            String currenUser=users.get(i);
            String nextUser=users.get(i+1);

            int balanceCurrent= balanceSheet.get(currenUser);
            int balanceNext= balanceSheet.get(nextUser);

            Transaction t=null;

            if (balanceCurrent > 0)
            {
                // paid more so to receive
                t=new Transaction(nextUser, currenUser, balanceCurrent);
                balanceNext+=balanceCurrent;
                balanceCurrent=0;

            }else{
                // should pay more
                t=new Transaction(currenUser, nextUser, -balanceCurrent);
                balanceNext-=balanceCurrent;
                balanceCurrent=0;

            }
            balanceSheet.put(currenUser, balanceCurrent);
            balanceSheet.put(nextUser, balanceNext);

            result.add(t);
        }

        return  result;
    }
}
