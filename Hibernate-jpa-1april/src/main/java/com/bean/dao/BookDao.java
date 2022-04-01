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

        Query query = manager.createQuery("select  tid ,tname from Teacher t  where t.name LIKE A%");
        double res = (double)query.getSingleResult();
        System.out.println("res is"+res);


    }
    public static void Teacherbetn(){

        Query query = manager.createQuery("select  tid ,tname from Teacher   where  salary between 10000 and 25000");
        List<Teacher> t = query.getResultList();
        for (int i = 0; i < t.size(); i++) {
            System.out.println(t.get(i));
        }


    }


}
