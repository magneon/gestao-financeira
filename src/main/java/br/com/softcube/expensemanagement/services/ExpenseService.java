package br.com.softcube.expensemanagement.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

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
		return daoExpense.findAll().stream().sorted(Comparator.comparing(Expense::getExpenseDate).reversed()).collect(Collectors.toList());
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

	public ResponseEntity<List<ExpenseDTO>> getExpensesByPeriod(String period) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.forLanguageTag("pt-BR"));
		TemporalAccessor temporalAccessor = formatter.parse(period);
		LocalDateTime date = LocalDateTime.now().withYear(temporalAccessor.get(ChronoField.YEAR)).withMonth(temporalAccessor.get(ChronoField.MONTH_OF_YEAR));

		LocalDate dtIni = date.with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
		LocalDate dtEnd = date.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate();

		List<Expense> expenses = daoExpense.findByDate(dtIni, dtEnd);
		if (!expenses.isEmpty()) {
			List<ExpenseDTO> expensesDTO = expenses.stream().map(expense -> new ExpenseDTO(expense)).sorted(Comparator.comparing(ExpenseDTO::getDate).reversed()).collect(Collectors.toList());

			return ResponseEntity.ok().body(expensesDTO);
		}
		return null;
	}
}