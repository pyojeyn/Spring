package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JoinMain {
    public static void main(String[] args) {
        // 0703 어 이거 뭐지?
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Team team = new Team();
            team.setName("teamA");
            em.persist(team); // team 먼저 저장.

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(23);
            member.setTeam(team);
            member.setMemberType(MemberType.ADMIN);
            em.persist(member); // member 저장.

            em.flush();
            em.clear();

            String query1 = "select t from Member m inner join m.team t where t.name = :teamName"; // inner join
            String query2 = "select m from Member m left outer join m.team t"; // left (outer) join
            String query3 = "select m from Member m, Team t where m.username = t.name"; // cross join

            String query4 = "select m from Member m left join m.team t on t.name = 'teamA'";
            String query5 = "select m from Member m left join Team t on m.username = t.name";
            List<Member> result = em.createQuery(query5, Member.class)
                            .getResultList();

            String query6 = "select m.username, 'HELLO', true From Member m " +
                    "where m.memberType = :userType";

            List<Object[]> result2 = em.createQuery(query6)
                            .setParameter("userType", MemberType.ADMIN)
                                    .getResultList();
            System.out.println("====================================");
            for(Object[] objects : result2){
                System.out.println("0 = " + objects[0]);
                System.out.println("1 = " + objects[1]);
                System.out.println("2 = " + objects[2]);
            }

            System.out.println("result.size=" + result.size());


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
    }
}
