package br.com.softcube.expensemanagement.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softcube.expensemanagement.models.Expense;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseDao extends JpaRepository<Expense, Long> {

    @Query(value = "SELECT e FROM expense e WHERE e.expenseDate BETWEEN :dtIni AND :dtEnd")
    List<Expense> findByDate(LocalDate dtIni, LocalDate dtEnd);
}
