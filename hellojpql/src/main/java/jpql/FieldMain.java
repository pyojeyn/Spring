package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class FieldMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("관리자1");
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);

            em.flush();
            em.clear();

            String query = "select m.username From Member m"; // 상태필드. 경로탐색의 끝.
            String query2 = "select m.team From Member m";  // 단일 값 연관 경로. 묵시적 내부 조인 발생. 탐색O
/*
    [query2] 를 돌리면 묵시적 내부 join 이 일어남. -> 조심히 써야하무니다.
    select
        team1_.id as id1_3_,
        team1_.name as name2_3_
    from
        Member member0_
    inner join
        Team team1_
    on member0_.TEAM_ID=team1_.id
*/

            String query3 = "select t.members From Team t";

/*  query3 을 날리면 묵시적 inner join 이 발생함.
select
    members1_.id as id1_0_,
    members1_.age as age2_0_,
    members1_.memberType as memberTy3_0_,
    members1_.TEAM_ID as TEAM_ID5_0_,
    members1_.username as username4_0_
from
    Team team0_
inner join
    Member members1_
on team0_.id=members1_.TEAM_ID
 */

            String query4 = "select m.username From Team t join t.members m";
            // 컬렉션 값 연관 경로 : From 절에서 명시적 조인을 통해 별칭을 얻으면 별칭을 통해 탐색 기가능

            List result = em.createQuery(query4, Collection.class).getResultList();

            for (Object s : result) {
                System.out.println("s = " + s);
            }

            // 결론. 묵시적 join 쓰면 X
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }

    }
}
