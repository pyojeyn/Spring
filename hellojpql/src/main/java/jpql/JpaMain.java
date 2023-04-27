package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // **엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //  <persistence-unit name="hello">

        // **엔티티 매니저는 쓰레드간에 공유 x 사용하고 버려야함
        EntityManager em = emf.createEntityManager();

        /* jpa 는 트랜젝션 중요함. 트랜젝션 안에서만 놀아야함. */
        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("mike");
            member.setAge(11);
            em.persist(member);

            Member query1
                    = em.createQuery("select m from Member m where m.username = :username", Member.class) // 타입이 명확하다. Member.class
            .setParameter("username", "mike")
            .getSingleResult();
            System.out.println("singleResult = "+ query1.getUsername());

            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class); // 타입이 명확하다. String.class

            Query query3 = em.createQuery("select m.username, m.age from Member m"); // 반환 타입이 명확하지 않다.

            List<Address> resultList = em.createQuery("select o.address from Order o", Address.class).getResultList();





            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
