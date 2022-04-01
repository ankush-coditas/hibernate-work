package com.bean;

import javax.persistence.*;

@Entity
@Table(name = "Teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Teacher_ID")
    private int id;
    @Column(name = "Teacher_Name")
    private String name;
    @Column(name="Teacher_Salary")
    private double salary;
    @Column(name="Techer_Designation")
    private String desig;

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public Teacher(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Teacher() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", desig='" + desig + '\'' +
                '}';
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
