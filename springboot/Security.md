https://devwithpug.github.io/spring/spring-security-1/
스프링 시큐리티 아키텍쳐

https://velog.io/@yaho1024/spring-security-delegatingFilterProxy

명령어공부~


client, filter chain. filter. delegatingfilterproxy

filterchainproxy

flter

servlet



1.securityfilterchain.
=

스프링 시큐리티는 파이프라인과같은 필터ㅍ턴으로 존재. 이러한 패턴은 체인으로 묶여있음.

서블릿 필터는 스프링에서 정의된 빈을 주입해서 사용 x

따라서 특정이름의 빈을 찾아 해당 빈에게 요청 위임.


![images_yaho1024_post_0dc7723f-7e9e-4255-aff3-c1d3714a277a_delegatingfilterproxy](https://user-images.githubusercontent.com/75001605/166559140-e902aa63-2796-4a39-a567-a385c497efaa.png)


delegatingfilterproxy?: 안에 beanfilter가 존재. servlet container 기반의 필터 위에서. 동작시키기 위해. delegatingfilterproxy라는 클래스를 이용한다.

요것은 ioc컨테이너에서 관리하는 빈이아닌 표준 서블릿 필터 구현. 내부에 위임대상(filterchainproxy)를 갖고있음

이 위임대상은 서블릿 컨테이너와 spring ioc 컨테이너의 다리역할한다

주의할 점. filterchain은 applicationfilterchain이다.  우리가 나중에 살펴볼 securityfilterchain과는 또 다름

DelegatingFilterProxy는 서블릿 필터이며, Spring IOC 컨테이가 관리는 Filter Bean을 갖고 있고 

이 Filter Bean은 FilterChainProxy이며 이 객체안에서 Security와 관련된 일들이 벌어진다고 생각할 수 있겠습니다.











