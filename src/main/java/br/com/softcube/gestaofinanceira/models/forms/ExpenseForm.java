package br.com.softcube.gestaofinanceira.models.forms;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.softcube.gestaofinanceira.models.Expense;
import br.com.softcube.gestaofinanceira.models.enums.ExpenseType;

public class ExpenseForm {

	@NotNull @NotEmpty
	private String name;
	private String description;
	//FIXME Criar um validador do Bean Validation para o tipo Double
	//@NotNull @NotEmpty
	private Double value;
	//FIXME Criar um validador do Bean Validation para o tipo LocalDate
	//@NotNull @NotEmpty
	private LocalDate date;
	@NotNull @NotEmpty
	private String type;

	public Expense convertFormToModel() {
		Expense expense = new Expense();
		expense.setExpenseName(this.getName());
		expense.setExpenseDescription(this.getDescription());
		expense.setExpenseValue(this.getValue());
		expense.setExpenseDate(this.getDate());
		expense.setExpenseType(ExpenseType.valueOf(this.getType()));
		
		return expense;
	}
	
	public void updateModel(Expense expense) {
		expense.setExpenseName(this.getName());
		expense.setExpenseDescription(this.getDescription());
		expense.setExpenseValue(this.getValue());
		expense.setExpenseType(ExpenseType.valueOf(this.getType()));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}