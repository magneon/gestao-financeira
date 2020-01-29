package br.com.softcube.gestaofinanceira.models.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonRootName;

import br.com.softcube.gestaofinanceira.models.Expense;

@JsonRootName(value = "expense")
public class ExpenseDTO {

	private String name;
	private String description;
	private Double value;
	private LocalDate date;
	private String type;

	public ExpenseDTO(Expense expense) {
		this.name = expense.getExpenseName();
		this.description = expense.getExpenseDescription();
		this.value = expense.getExpenseValue();
		this.date = expense.getExpenseDate();
		this.type = expense.getExpenseType().name();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getValue() {
		return value;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getType() {
		return type;
	}

}