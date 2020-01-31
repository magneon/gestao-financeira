package br.com.softcube.expensemanagement.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.softcube.expensemanagement.models.enums.ExpenseType;

@Entity(name = "expense")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String expenseName;
	private String expenseDescription;
	private Double expenseValue;
	private LocalDate expenseDate;

	@Enumerated(value = EnumType.STRING)
	private ExpenseType expenseType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExpenseName() {
		return expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public Double getExpenseValue() {
		return expenseValue;
	}

	public void setExpenseValue(Double expenseValue) {
		this.expenseValue = expenseValue;
	}

	public LocalDate getExpenseDate() {
		return expenseDate;
	}
	
	public void setExpenseDate(LocalDate expenseDate) {
		this.expenseDate = expenseDate;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

}