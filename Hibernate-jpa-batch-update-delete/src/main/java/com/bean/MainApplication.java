package com.bean;

import antlr.DocBookCodeGenerator;
import com.bean.dao.BookDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entity");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BookDao bookDao = new BookDao();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("******************* Inserting Record ********************");
        System.out.println("No of techers");
        int count = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < count; i++) {
            entityManager.getTransaction().begin();
            Teacher teacher = new Teacher();
            System.out.println("Enter Techer's name and salary and desgination");
            String name = bufferedReader.readLine();
            int sal = Integer.parseInt(bufferedReader.readLine());
            String desig = bufferedReader.readLine();
            teacher.setName(name);
            teacher.setSalary(sal);
            teacher.setDesig(desig);
            entityManager.persist(teacher);
            entityManager.getTransaction().commit();
            System.out.println(teacher);
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Teacher> from = criteriaQuery.from(Teacher.class);

        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = entityManager.createQuery(select);
        List<Object> list = typedQuery.getResultList();
        do{
        System.out.println("1:fetch ALL Record\n2:Selection from name and salary\n3:Fetch By Order \n4:Exit\nEnter your choice::");
        int ch = Integer.parseInt(bufferedReader.readLine());
        switch (ch) {
            case 1:
                bookDao.FetchAll();
                break;
            case 2:
                bookDao.SelectionByNameAndDesignation();
                break;
            case 3:
                bookDao.FetchOrderBySalary();
                break;
            case 4:
                System.exit(0);
                break;
            case 5:
                bookDao.minTeacher();
                break;
            case 6:
              bookDao.TeacherA();
                break;
            case 7:
                bookDao.Teacherbetn();
                break;
            case 8:
                bookDao.updateSalary(20000);
                break;
            case 9:
                bookDao.batchDelete(40000);
                break;



        }

        }while (true);
    }
}
