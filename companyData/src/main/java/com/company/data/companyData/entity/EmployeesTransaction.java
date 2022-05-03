package com.company.data.companyData.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class EmployeesTransaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;

    @Column(name = "date")
    @CreatedDate
    private LocalDate date = LocalDate.now();

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "transaction_type")
    private Integer transactionType;

    @Column(name = "details")
//    @Pattern(regexp = "^[A-Z]{1}[a-z]{2}[a-z]*", message = "Please Enter Valid Name")
    private String details;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "employees_id")
    private Employees employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @JsonIgnore
    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "EmployeesTransaction{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                ", details='" + details + '\'' +
                ", employees=" + employees +
                '}';
    }

    public EmployeesTransaction(int id, LocalDate date, Integer amount, Integer transactionType, String details, Employees employees) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
        this.details = details;
        this.employees = employees;
    }

    public EmployeesTransaction() {

    }

    public void updateEmployeesTransactionData(EmployeesTransaction employeesTransaction) {
        amount = employeesTransaction.getAmount() != null ? employeesTransaction.getAmount() : amount;
        transactionType = employeesTransaction.getTransactionType() != null ? employeesTransaction.getTransactionType() : transactionType;
        details = employeesTransaction.getDetails() != null ? employeesTransaction.getDetails() : details;
    }

}
