package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //  <persistence-unit name="hello">
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        try {
// 여기 실행 안됨. 아오;
            Book book = new Book();
            book.setAuthor("제인");
            book.setName("jpa");
            em.persist(book);


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
