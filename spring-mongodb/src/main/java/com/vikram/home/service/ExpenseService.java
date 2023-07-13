package com.vikram.home.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vikram.home.entity.Expense;
import com.vikram.home.repo.ExpenseRepository;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {
 
    private final ExpenseRepository expenseRepository;
 
    public void addExpense(Expense expense) {
        expenseRepository.insert(expense);
    }
    
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    
    public void updateExpense(Expense expense) {
        Expense savedExpense = expenseRepository.findById(expense.getId()).orElseThrow(() -> new RuntimeException(String.format("Cannot Find Expense by ID %s", expense.getId())));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount()); 
        expenseRepository.save(expense);
    }
    
    public Expense getExpense(String name) {
        return expenseRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Cannot Find Expense by Name - %s", name)));
    }
    
    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}