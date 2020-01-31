package br.com.softcube.expensemanagement.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softcube.expensemanagement.daos.ExpenseDao;
import br.com.softcube.expensemanagement.models.Expense;
import br.com.softcube.expensemanagement.models.dtos.ExpenseDTO;
import br.com.softcube.expensemanagement.models.forms.ExpenseForm;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseDao daoExpense;

	public List<Expense> getExpensesWithoutFilter() {
		return daoExpense.findAll();
	}

	public ResponseEntity<ExpenseDTO> getExpensesDetailById(Long id) {
		Optional<Expense> expense = daoExpense.findById(id);
		if (expense.isPresent()) {
			return ResponseEntity.ok().body(new ExpenseDTO(expense.get()));			
		}		
		return ResponseEntity.notFound().build();
	}

	public Expense createExpense(@Valid ExpenseForm form) {
		Expense expense = form.convertFormToModel();
		daoExpense.save(expense);
		
		return expense;
	}

	@Transactional
	public ResponseEntity<Expense> updateExpense(Long id, @Valid ExpenseForm form) {
		Optional<Expense> expense = daoExpense.findById(id);
		if (expense.isPresent()) {
			form.updateModel(expense.get());
			
			return ResponseEntity.ok().body(expense.get());			
		}
		
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<?> removeExpenseById(Long id) {
		Optional<Expense> expense = daoExpense.findById(id);
		if (expense.isPresent()) {
			daoExpense.delete(expense.get());
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}	
}