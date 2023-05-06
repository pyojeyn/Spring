package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlFunctionMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
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


            String query = "select concat('a', 'b') From Member m";
            String query2 = "select locate('d','asdf') From Member m";
            String query3 = "select size(t.members) From Team t";
//            String query4 = "select function('regexp_substr', m.username, '[^,]+', 1, 1) From Member m";


            List<String> result = em.createQuery(query, String.class)
                    .getResultList();


            for (String s : result) {
                System.out.println("s = " + s);
            }

            tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }
    }
}
