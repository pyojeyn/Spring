package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // HTTP 요철을 통해 매핑된 URL 이 호출되면 서블릿 컨테이너는 이 메서드를 실행한다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request); //request = org.apache.catalina.connector.RequestFacade@6760ff4c    톰캣쪽 라이브러리
        System.out.println("response = " + response);//response = org.apache.catalina.connector.ResponseFacade@549e0228 톰캣쪽 라이브러리

        String username = request.getParameter("username");

        System.out.println("username = " + username);

        // 웹 브라우저에 응답
        response.setContentType("text/plain"); // 단순 문자  헤더
        response.setCharacterEncoding("utf-8"); // 인코딩 정보   헤더
        response.getWriter().write("hello " + username); // http 메시지 바디에 데이터가 들어감

    }
}
//http://localhost:8080/hello?username=pyo 로 호출
//?username=pyo 는 쿼리파라미터이고 서블릿에서는 getParameter 로 가져올 수 있다.

/*
* @WebServlet 서블릿 애노테이션
* - name: 서블릿 이름
* - urlPatterns: URL 매핑
* */