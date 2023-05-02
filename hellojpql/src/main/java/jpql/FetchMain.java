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
            /*
            queryFetch : 페치 조인으로회원과 팀을 함께 조회해서 지연 로딩X
            @ManyToOne(fetch= FETCHTYPE.LAZY) 해도 join fetch 가 우선적으로 적용됨.
             select
                member0_.id as id1_0_0_,
                team1_.id as id1_3_1_,
                member0_.age as age2_0_0_,
                member0_.memberType as memberTy3_0_0_,
                member0_.TEAM_ID as TEAM_ID5_0_0_,
                member0_.username as username4_0_0_,
                team1_.name as name2_3_1_
            from
                Member member0_
            inner join
                Team team1_
            on member0_.TEAM_ID=team1_.id
             */

            String collectionFetchQuery = "select t From Team t join fetch t.members";

            String distinctQuery = "select distinct t From Team t join fetch t.members";

            List<Team> result = em.createQuery(distinctQuery, Team.class).getResultList();

            for (Team s : result) {
                System.out.println("team = " + s.getName() + ", " + s.getMembers().size());
                for (Member m : s.getMembers()) {
                    System.out.println("member.username= " + m.getUsername());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }

    }
}
