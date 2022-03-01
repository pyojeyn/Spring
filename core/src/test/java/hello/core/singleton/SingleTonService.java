package hello.core.singleton;

public class SingleTonService {
    //1. static 영역에 객체를 딱 1개만 생성해둔다.
    private static final SingleTonService instance = new SingleTonService(); // 자기 자신을  내부에 private static 으로 선언

    //2. public 으로 열어서 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingleTonService getInstance(){
        return instance;
    }

    //3. 생성자를 private 으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다
    private SingleTonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
   }


}
