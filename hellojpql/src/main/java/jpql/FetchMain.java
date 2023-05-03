package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;

public class FetchMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
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

            em.flush();
            em.clear();

            String query = "select m From Member m"; // 상태필드. 경로탐색의 끝.
            String queryFetch = "select m From Member m join fetch m.team"; // 한방에 join 함.
            String collectionFetchQuery = "select t From Team t join fetch t.members";
            String distinctQuery = "select distinct t From Team t join fetch t.members";

            String forPagingQuery = "select t From Team t";

            List<Team> result = em.createQuery(forPagingQuery, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();
//            List<Member> memberResult = em.createQuery(queryFetch, Member.class).getResultList();

//            for (Member m : memberResult) {
//                System.out.println("member = " + m.getUsername() + ", " + m.getTeam().getName());
//            }

            for (Team s : result) {
                System.out.println("team = " + s.getName() + ", " + s.getMembers().size());
                for (Member m : s.getMembers()) {
                    System.out.println("member.username = " + m.getUsername());
                }
            }
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }

    }
}
