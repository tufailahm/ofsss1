package com.training.jpa.criteria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "item100")
@Entity
@org.hibernate.annotations.NamedQuery(name = "Item_findByName", 
query = "from Item where name = :itemName")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String color;
    private String grade;
    private String name;

    public String getColor() {
        return color;
    }

    public String getGrade() {
        return grade;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Item [id=" + id + ", color=" + color + ", grade=" + grade + ", name=" + name + "]";
	}

}
