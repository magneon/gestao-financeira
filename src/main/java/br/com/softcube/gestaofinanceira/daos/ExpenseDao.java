package br.com.softcube.gestaofinanceira.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softcube.gestaofinanceira.models.Expense;

public interface ExpenseDao extends JpaRepository<Expense, Long> {

}
