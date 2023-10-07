package algorithms;

import models.Transaction;

import java.util.HashMap;
import java.util.List;

public interface SettlementStrategy {
    List <Transaction> settleBalance(HashMap <String, Integer> balanceSheet);
}
