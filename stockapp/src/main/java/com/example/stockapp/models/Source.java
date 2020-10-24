package com.example.stockapp.models;

import javax.persistence.*;

@Entity
@Table(name = "source")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Source() {
    }

    public Source(String name) {
        this.name = name;
    }

    public long getId() {
		return id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
	public String toString() {
		return "Source [id=" + id + ", name=" + name + "]";
	}
}