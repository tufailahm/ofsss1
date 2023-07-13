package com.training.jpa.join;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee199")
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public static Employee create(String name, Task... tasks) {
        Employee e = new Employee();
        e.name = name;
        if (tasks != null) {
            e.tasks = Arrays.asList(tasks);
        }
        return e;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}