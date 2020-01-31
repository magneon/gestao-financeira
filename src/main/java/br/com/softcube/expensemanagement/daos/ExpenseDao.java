package br.com.softcube.expensemanagement.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softcube.expensemanagement.models.Expense;

public interface ExpenseDao extends JpaRepository<Expense, Long> {

}
