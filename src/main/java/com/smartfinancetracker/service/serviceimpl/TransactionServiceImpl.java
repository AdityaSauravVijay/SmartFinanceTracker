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


}
