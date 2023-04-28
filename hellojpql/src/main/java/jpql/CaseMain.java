package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CaseMain {
    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = enf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{

            Team team = new Team();
            team.setName("관리자");
            em.persist(team); // team 먼저 저장.

            Member member = new Member();
            member.setUsername(null);
            member.setAge(10);
            member.setTeam(team);
            member.setMemberType(MemberType.ADMIN);
            em.persist(member); // member 저장.

            String query =
                    "select " +
                            "case when m.age <= 10 then '학생요금' " +
                            "     when m.age >= 60 then '경로요금' " +
                            "     else '일반요금' " +
                            "end " +
                            "from Member m";

            // username 이 null 이면 이름 없는 사람임.
            String query2 = "select coalesce(m.username, '이름 없는 사람') from Member m ";

            // username 이 관리자면 null 을 반환해라. 관리자 이름 숨기기.
            String query3 = "select nullif(m.username, '관리자') as username from Member m";

            List<String> result = em.createQuery(query3, String.class)
                            .getResultList();

            for (String fee : result) {
                System.out.println("fee : " + fee);
            }

            em.flush();
            em.clear();





            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }
    }
}
