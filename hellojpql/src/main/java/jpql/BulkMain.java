package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BulkMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("회원1");
            member.setTeam(teamA);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3= new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            // flush 자동 호출. commit, query
            int resultCnt = em.createQuery("update Member m set m.age = 33")
                            .executeUpdate(); // 바로 DB에 적용. 영속성 컨텍스트에 바뀐값 적용 안됨.

            em.clear(); // 그래서 영속성 컨텍스트 바로 초기화 해줘야함.

            System.out.println("resultCnt= " + resultCnt);

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember= " + findMember.getAge());

            // 반영안됨.
            System.out.println("member.getAge()= " + member.getAge()); //0
            System.out.println("member2.getAge()= " + member2.getAge());//0
            System.out.println("member3.getAge()= " + member3.getAge());//0

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
    }
}
