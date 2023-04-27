package jpql;

import javax.persistence.*;
import java.util.List;

public class ProjectionMain {
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

            List resultList = em.createQuery("select m.age, m.username from Member m").getResultList();
            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();

            MemberDTO memberDTO = result.get(0);
            System.out.println("memberDTO.getAge()=="+memberDTO.getAge());
            System.out.println("memberDTO.getName()=="+memberDTO.getUsername());

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
