
서블릿은 한마디로 http 스펙을 편리하게 사용하도록 지원하ㅡㄴ것.

urlpatterns 필드의 url 호출할 경우 서블릿 코드 실행

서블릿은 서블릿 컨테이너 내 싱글톤 객체로 존재.

* HttpServletRequest 객체는 attribute 임시 저장, 조회 기능과 함께 세션 관리 기능 제공


서블릿 HTTP 요청 시 프로세스
=


![다운로드 (4)](https://user-images.githubusercontent.com/75001605/166587501-6223f9e7-5f37-4321-913c-dcab926ecbdf.png)

WAS가 REQUEST, RESPONSE 객체를 새로 만들어 서블릿 객체 호출

개발자는 REQUEST 객체에서 HTTP 요청 정보를 기반으로 RESPONSE 객체에 응답정보 입력

WAS는 RESPONSE 객체에 담겨있는 내용으로 HTTP 응답정보 생성


서블릿 컨테이너?

서블릿을 지원하는 WAS ex) Tomcat Server

서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기 관리 (https://www.javatpoint.com/life-cycle-of-a-servlet 참고)

동시 요청을 위해 멀티 쓰레드 처리 지원 (개발자가 멀티 쓰레드 관련 코드 신경 쓰지 않아도 됨)

서블릿 객체는 싱글톤으로 관리

요청이 올 때마다 객체 생성하는 것은 비효율적, 최초 로딩 시점에서 서블릿 객체를 미리 생성 후 재활용
싱글톤으로 관리되기 때문에 ***공유 변수 사용 주의***

서블릿 컨테이너 종료 시 함께 종료


출처: https://jaimemin.tistory.com/1792?category=1060172 [꾸준함]



MVC 패턴 이전

사용자의 요청을 서블릿이나 JSP가 전부다 처리 Controller의 부재.

웹브라우저 사용자의 요청을 받은 서블릿이나 jsp는 비즈니스 로직을 처리하고 뷰 렌더링까지 처리

뷰 화면을 위한 html을 만드는 작업과 비즈니스 로직을 모두 서블릿 내에서 처리할 경우. html 코드를 자바코드로 작성해야되서

오타 발생

JSP를 사용함으로써 HTML 작업을 깔끔하게 가져가고 동적으로 변경이 필요한 부분을 자바 코드로 적용함으로써

조금 단순화. 로직과 뷰가 같이 있다는 점이 큰 문제점


MVP 패턴이전 뷰와 로직이 섞여있었음


![다운로드 (3)](https://user-images.githubusercontent.com/75001605/166586856-efcf17ca-1855-4705-ab78-286caa15e906.png)


MVC 패턴을통해

Controller가 http 요청을 받아 파라미터를 검증, 비즈니스 로직을 실행. 그리고 뷰에 전달할 데이터를 모델에 담음

비즈니스 로직을 controller내에서 실행하는 방식이 model1

model2는 비즈니스 로직을 별도 계층인 service 계층에서 실행하고 controller가 service 계층을 호출하는 방식이 model2 방식

model: controller 로 부터 데이터를 전달 받은 데이터를 모델에 전달하는 역할

view: 모델에 담겨있는 데이터를 사용해서 화면을 렌더링, 모델 덕분에 비즈니스 로직을 몰라도 데이터를 활용 가능(역할분리)


그러나 한계점 역시 존재

뷰 렌더링과 컨트롤러 역할 분리한건 좋지만 . 페이지가 늘어남에따라 컨트롤러내 중복코드 다량 발생

view로 이동하는 forward 코드 중복.

view 주소 즉, view path 설정하는 코드 중복

별도 응답을 보낼 필요가 없는 경우 서블릿 내 response 코드가 사용되지 않음

HttpServletRequest, HttpServletResponse를 사용하는 테스트 코드 작성하기 쉽지 않음

정리하자면, 공통 처리가 어려운 것이 문제 (이를 해결하기 위해 Front Controller 패턴 도입)


forward vs redirect

forward는 서버 내부에서 일어나는 호출이기 때문에 URL이 바뀌지 않고 클라이언트가 인지 못함
 
 
 반면, redirect의 경우 클라이언트에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청 (따라서, URL 경로도 변경됨)


WEB-INF의 역할

해당 경로 내 JSP가 있을 경우 외부에서 직접 호출 못함
컨트롤러를 통해서만 JSP를 호출할 수 있게 해주는 역할
