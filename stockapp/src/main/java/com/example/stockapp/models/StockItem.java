package com.example.stockapp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stockitem")
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATEOFENTRY")
    private Date dateOfEntry;

    @Column(name = "DATEOFRECEIPT")
    private Date dateOfReceipt;

    @Column(name = "LETTERNUMBER", length = 2000)
    private String letterNumber;

    @Column(name = "LETTERDATE")
    private Date letterDate;

    @Column(name = "SIZE")
    private String size;

    @Column(name = "SUBCATEGORY1")
    private String subCategory1;

    @Column(name = "SUBCATEGORY2")
    private String subCategory2;

    @Column(name = "SUBCATEGORY3")
    private String subCategory3;

    @Column(name = "COST")
    private Double cost;

    @Column(name = "QUANTITY")
    private Long quantity;

    //@Column(name = "SOURCE")
    @ManyToOne
    @JoinColumn(name="SOURCE")
    private Source source;

    // @Column(name = "STOCKTYPE")
    @ManyToOne
    @JoinColumn(name="STOCKTYPE")
    private StockType stockType;

    // @Column(name = "CATEGORY")
    @ManyToOne
    @JoinColumn(name="CATEGORY")
    private Category category;

    // @Column(name = "DEPARTMENT")
    @ManyToOne
    @JoinColumn(name="DEPARTMENT")
    private Department department;

    // @Column(name = "STOCKITEMTYPE")
    @ManyToOne
    @JoinColumn(name="STOCKITEMTYPE")
    private StockItemType stockItemType;

    @Column(name = "SENTTO")
    private String sentTo;

    @Column(name = "RECEIVEDFROM")
    private String receivedFrom;

    @Column(name = "REMARKS", length = 2000 )
    private String remarks;

    public StockItem() {
    }

    public StockItem(String name) {
        this.name = name;
    }

    public Long getId() {
		return id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public Date getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(Date dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public String getLetterNumber() {
        return letterNumber;
    }

    public void setLetterNumber(String letterNumber) {
        this.letterNumber = letterNumber;
    }

    public Date getLetterDate() {
        return letterDate;
    }

    public void setLetterDate(Date letterDate) {
        this.letterDate = letterDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSubCategory1() {
        return subCategory1;
    }

    public void setSubCategory1(String subCategory1) {
        this.subCategory1 = subCategory1;
    }

    public String getSubCategory2() {
        return subCategory2;
    }

    public void setSubCategory2(String subCategory2) {
        this.subCategory2 = subCategory2;
    }

    public String getSubCategory3() {
        return subCategory3;
    }

    public void setSubCategory3(String subCategory3) {
        this.subCategory3 = subCategory3;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public StockType getStockType() {
        return stockType;
    }

    public void setStockType(StockType stockType) {
        this.stockType = stockType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public StockItemType getStockItemType() {
        return stockItemType;
    }

    public void setStockItemType(StockItemType stockItemType) {
        this.stockItemType = stockItemType;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getReceivedFrom() {
        return receivedFrom;
    }

    public void setReceivedFrom(String receivedFrom) {
        this.receivedFrom = receivedFrom;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
	public String toString() {
		StringBuilder str = new StringBuilder("StockItem id = " + id);

		if(getName() != null && !String.valueOf(getName()).isEmpty()) {
			str.append(", Name - " + getName());
		}

		if(getDepartment() != null && !String.valueOf(getDepartment()).isEmpty()) {
			str.append(", Dept - " + getDepartment());
		}

		if(getCategory() != null && !String.valueOf(getCategory()).isEmpty()) {
			str.append(", Category - " + getCategory());
		}

		if(getQuantity() != null && !String.valueOf(getQuantity()).isEmpty()) {
			str.append(", Qty - " + getQuantity());
		}

		if(getDateOfEntry() != null && !String.valueOf(getDateOfEntry()).isEmpty()) {
			str.append(", DtOfEntry - " + getDateOfEntry());
		}

		if(getDateOfReceipt() != null && !String.valueOf(getDateOfReceipt()).isEmpty()) {
			str.append(", DtOfRecpt - " + getDateOfReceipt());
		}

		if(getLetterNumber() != null && !String.valueOf(getLetterNumber()).isEmpty()) {
			str.append(", LetterNo. - " + getLetterNumber());
		}

		if(getSource() != null && !String.valueOf(getSource()).isEmpty()) {
			str.append(", Source - " + getSource());
		}

		if(getStockType() != null && !String.valueOf(getStockType()).isEmpty()) {
			str.append(", StockType - " + getStockType() + "}");
        }
        
        if(getStockItemType() != null && !String.valueOf(getStockItemType()).isEmpty()) {
			str.append(", StockItemType - " + getStockItemType() + "}");
		}

		return str.toString();
	}

}