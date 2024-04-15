package com.smartfinancetracker.service.serviceimpl;

import com.smartfinancetracker.dao.TransactionDao;
import com.smartfinancetracker.models.Transaction;
import com.smartfinancetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionDao transactionDao;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDao){
        super();
        this.transactionDao = transactionDao;
    }

    @Override
    public List<Transaction> sortTransactions() {
        List<Transaction> sortedTransactionsbyDate = transactionDao.findAll();
        mergeSortTransactionsByDate(sortedTransactionsbyDate);
        return sortedTransactionsbyDate;
    }

    // Method to sort the transactions using Merge Sort
    public void mergeSortTransactionsByDate(List<Transaction> transactions) {
        if (transactions.size() > 1) {
            int mid = transactions.size() / 2;
            List<Transaction> leftHalf = new ArrayList<>(transactions.subList(0, mid));
            List<Transaction> rightHalf = new ArrayList<>(transactions.subList(mid, transactions.size()));

            mergeSortTransactionsByDate(leftHalf);
            mergeSortTransactionsByDate(rightHalf);

            merge(transactions, leftHalf, rightHalf);
        }
    }

    // Method to merge two halves
    private void merge(List<Transaction> transactions, List<Transaction> leftHalf, List<Transaction> rightHalf) {
        int leftIndex = 0, rightIndex = 0, mergedIndex = 0;

        while (leftIndex < leftHalf.size() && rightIndex < rightHalf.size()) {
            if (leftHalf.get(leftIndex).getDate().isBefore(rightHalf.get(rightIndex).getDate()) ||
                    leftHalf.get(leftIndex).getDate().isEqual(rightHalf.get(rightIndex).getDate())) {
                transactions.set(mergedIndex, leftHalf.get(leftIndex));
                leftIndex++;
            } else {
                transactions.set(mergedIndex, rightHalf.get(rightIndex));
                rightIndex++;
            }
            mergedIndex++;
        }

        // Copy remaining elements of leftHalf, if any
        while (leftIndex < leftHalf.size()) {
            transactions.set(mergedIndex, leftHalf.get(leftIndex));
            leftIndex++;
            mergedIndex++;
        }

        // Copy remaining elements of rightHalf, if any
        while (rightIndex < rightHalf.size()) {
            transactions.set(mergedIndex, rightHalf.get(rightIndex));
            rightIndex++;
            mergedIndex++;
        }
    }
}
