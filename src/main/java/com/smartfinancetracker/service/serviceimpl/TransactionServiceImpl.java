package com.smartfinancetracker.service.serviceimpl;

import com.smartfinancetracker.dao.TransactionDao;
import com.smartfinancetracker.models.Transaction;
import com.smartfinancetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionRepository) {
        super();
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ArrayList<Transaction> saveTransactions(ArrayList<Transaction> transactions){
        List<Transaction> saveTransactions = transactionRepository.saveAll(transactions);
        return new ArrayList<>(saveTransactions);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction){
        Transaction savedTransaction = transactionRepository.save(transaction);
        return savedTransaction;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) throws Exception {
        Transaction item = transactionRepository.findById(transaction.getTransactionId())
                .orElseThrow(() -> new Exception("Category not found"));

        item.setName(transaction.getName());
        item.setAmount(transaction.getAmount());
        item.setDescription(transaction.getDescription());
        item.setType(transaction.getType());
        item.setDate(transaction.getDate());
        item.setCategoryId(transaction.getCategoryId());

        return transactionRepository.save(item);
    }

    @Override
    public void deleteTransaction(Long transactionId){
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions(Long userId){
        List<Transaction> allList = transactionRepository.findByUserId(userId);
        return allList;
    }

    public Optional<Transaction> getTransaction(Long transactionId){
        return transactionRepository.findById(transactionId);
    }

    @Override
    public List<Transaction> sortTransactionsByDate(Long userId) {
        List<Transaction> sortedTransactionsbyDate = transactionRepository.findByUserId(userId);
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
