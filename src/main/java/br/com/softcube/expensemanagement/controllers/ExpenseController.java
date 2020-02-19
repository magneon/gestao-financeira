package br.com.softcube.expensemanagement.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.softcube.expensemanagement.models.Expense;
import br.com.softcube.expensemanagement.models.dtos.ExpenseDTO;
import br.com.softcube.expensemanagement.models.forms.ExpenseForm;
import br.com.softcube.expensemanagement.services.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
	
	@Autowired
	private ExpenseService serviceExpense;
	
	@GetMapping
	public ResponseEntity<List<Expense>> getAllExpenses() {
		return ResponseEntity.ok(serviceExpense.getExpensesWithoutFilter());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ExpenseDTO> detailExpense(@PathVariable Long id) {
		return serviceExpense.getExpensesDetailById(id);
	}

	@GetMapping(value = "/period/{period}")
	public ResponseEntity<List<ExpenseDTO>> getExpensesByPeriod(@PathVariable("period") String period) {
		return serviceExpense.getExpensesByPeriod(period);
	}
	
	@PostMapping
	public ResponseEntity<Expense> createNewExpense(@RequestBody @Valid ExpenseForm form, UriComponentsBuilder builder) {
		Expense expense = serviceExpense.createExpense(form);
		
		URI uri = builder.path("/expense/{id}").buildAndExpand(expense.getId()).toUri();
		
		return ResponseEntity.created(uri).body(expense);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Expense> updateExpense(@PathVariable("id") Long id, @RequestBody @Valid ExpenseForm form) {
		return serviceExpense.updateExpense(id, form);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> removeExpense(@PathVariable("id") Long id) {
		return serviceExpense.removeExpenseById(id);
	}
	
}