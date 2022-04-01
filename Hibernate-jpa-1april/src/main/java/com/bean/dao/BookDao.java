package com.bean.dao;

import com.bean.Teacher;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

public class BookDao {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity");
    static EntityManager manager = factory.createEntityManager();
    static CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

    public static void FetchAll() {
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> from = criteriaQuery.from(Teacher.class);
        CriteriaQuery<Teacher> select = criteriaQuery.select(from);
        TypedQuery<Teacher> allQuery = manager.createQuery(select);
        List<Teacher> list = allQuery.getResultList();

        for(Teacher t : list) {
            System.out.println(t);
        }
    }

    public static void SelectionByNameAndDesignation() {
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Teacher> from = criteriaQuery.from(Teacher.class);
        criteriaQuery.multiselect(from.get("name"), from.get("desig"));
        List<Object[]> list = manager.createQuery(criteriaQuery).getResultList();

        for(Object[] obj : list) {
            System.out.println(Arrays.toString(obj));
        }
    }

    public static void FetchOrderBySalary() {
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> from = criteriaQuery.from(Teacher.class);
        CriteriaQuery<Teacher> select = criteriaQuery.select(from);

        criteriaQuery.orderBy(criteriaBuilder.asc(from.get("salary")));
        List<Teacher> list = manager.createQuery(criteriaQuery).getResultList();

        for(Teacher t : list) {
            System.out.println(t.getSalary());
        }
    }

    public static void minTeacher(){

        Query query = manager.createQuery("select MIN(e.salary) from Teacher e");
        double res = (double)query.getSingleResult();
        System.out.println("res is"+res);


    }
    public static void TeacherA(){

         Query query = manager.createQuery("Select t from Teacher t where t.name like 'A%'");
    List<Teacher> list1 = (List<Teacher>) query.getResultList();
    for(Teacher t:list1)
    {
        System.out.println("Teacher name: " +t.getName());
    }

    }
    public static void Teacherbetn(){

          Query query = manager.createQuery("Select t from Teacher t where t.salary between 10000 and 25000");
    List<Teacher> list1 = (List<Teacher>) query.getResultList();
    for(Teacher t:list1)
    {
        System.out.println(t.getName() + " Salary is " + t.getSalary());
    }


    }


}
