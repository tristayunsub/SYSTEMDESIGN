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




1.SecurityContextPersistentFilter

security filterchain에 걸리는 filter

securitycontext를 영속화한다.

이 영속화는 securitycontextrespository라는 리포지토리를 통해 이루어짐

별도 변경이없으면 

HttpSessionSecurityContextRepository이 사용되며 HttpSession 의 Attribute에 SecurityContext라는 녀석이 저장됩니다.

SecurityContext를 영속화 할 뿐만 아니라,

이 녀석은 요청의 세션에서 저장되어 있던 SecurityContext를 꺼내와 SecurityContextHolder라는 홀더에 집어넣어 요청 전반에 걸쳐 SecurityContext를 사용할 수 있게끔 해줍니다


2.SecurityContextHolder?

![images_yaho1024_post_ce1556d5-8bb6-4ccc-8143-f6f94a5a0278_securitycontextholder](https://user-images.githubusercontent.com/75001605/166575965-2f47c960-b3a7-49b2-8e4d-c4ec65f376b4.png)

s where Spring Security stores the details of who is authenticated.
Spring Security does not care how the SecurityContextHolder is populated. If it contains a value, then it is used as the currently authenticated user.

핵심이다. 시큐리티 인증모델의.

이 홀더가 갖고있는 context는 인증객체 가지고있음. 인증에 성공하면 authentication이라는 객체가 만들어짐

(usernamepasswordauthentication을 사용)

 바로 인증에 성공한 인증 객체가 securitycontext에 저장되고. 이 인증 객체를 요청 전반에 걸쳐 사용하기위해
 
 securitycontext와 그것을 담는 홀더가 생김 UsernamePasswordAuthentication
 
 어떻게하면 요청 전달할까?  ThreadLocal을 사용
 
 만약 스레드 스콥을 사용하지 않고 메서드의 파라미터로 넘긴다고 생각해보시면.. 
 
 이는 애초에 불가능한 것이라는 것을 깨닫게 됩니다. 왜냐하면 요청 전반에 걸쳐 사용되는 모든 인터페이스를 바꿔야하기 때문입니다.
 
 SecurityContextHolder는 또 내부에 SecurityContextHolderStrategy라는 필드
 
 strategy 패턴도 참조
 
 실제로 홀더저장에 대한 책임을이 strategy가 수행. 이 녀석은 인터페이스로서 구체화 클래스는 기본으로 3가지 정도 제공
 
 initialize 메소드에서 strategy 이름에따라 사용되는 홀더 전략이 바뀜.
  
 디폴트는 ThreadLocalSecurityContextHolderStrategy 로 사용. threadlocal을 사용하여 SecurityContext를 담는다.
 
 ```
 
 final class ThreadLocalSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

	private static final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<>();

	@Override
	public void clearContext() {
		contextHolder.remove();
	}

	@Override
	public SecurityContext getContext() {
		SecurityContext ctx = contextHolder.get();
		if (ctx == null) {
			ctx = createEmptyContext();
			contextHolder.set(ctx);
		}
		return ctx;
	}

	@Override
	public void setContext(SecurityContext context) {
		Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
		contextHolder.set(context);
	}

	@Override
	public SecurityContext createEmptyContext() {
		return new SecurityContextImpl();
	}

}
```
핵심은 threadlocal 해당기능을 사용하면 thread가 수행하는 호출 스택이 어디에있던 수행하는 녀석이 thread 본인이라면 어디에서든 접근 가능.

덕분에 우리는 간편하게 SecurityContext의 인증 객체에 접근 가능합니다.

Controller에서든 Service에서든 아니면 빈이 아닌 util 클래스를 작성하여 그안에서

SecurityContextHolder.getContext().getAuthentication()만 호출한다면 인증된 사용자의 정보를 얻을 수 있습니다.

https://jaimemin.tistory.com/2007

thread local정리.

 
 
 
 
 
 
 
