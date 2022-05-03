https://velog.io/@ha0kim/2020-12-28-AOP-%ED%8A%B9%EC%A7%95-%EB%B0%8F-%EB%8F%99%EC%9E%91%EC%9B%90%EB%A6%AC

https://jojoldu.tistory.com/71

AOP란? SPRING의 핵심개념중 하나인 DI가 애플리케이션 모듈들 간의 결합도를 낮춘다면, AOP는 애플리케이션 전체에 걸쳐 사용되는 기능을 재사용하도록

지원하는것.



AOP란 제 3자의 관점에서 프로젝트 구조를 바라보자는것

부가기능 관점에서 바라보면 각각의 SERVICE는 수행시간 측정을 나타내는 BEFORE메소드와

AFTER 메소드가 공통된다. 부가기능 측면에서 공통된요소를 추출하는것. AOP를 크로스컷팅이라고 불리기도한다.

OOP 비즈니스 로직의 모듈화.

AOP 인프라 혹은 부가기능의 모듈화
 : 대표적으로 로깅, 트랜잭션, SECURITY등
 
 
결국은 공통된 기능을 재사용하는 기법입니다.
OOP에선 공통된 기능을 재사용하는 방법으로 상속이나 위임을 사용합니다.
하지만 전체 어플리케이션에서 여기저기에서 사용되는 부가기능들을 상속이나 위임으로 처리하기에는 깔끔하게 모듈화가 어렵습니다.

AOP의 장점은 2가지입니다.

어플리케이션 전체에 흩어진 공통 기능이 하나의 장소에서 관리된다는 점
다른 서비스 모듈들이 본인의 목적에만 충실하고 그외 사항들은 신경쓰지 않아도 된다는 점


AOP TERMS
=

**TARGET()**: 부가기능 부여할 대상. 핵심기능을담당하는 getBoards,나 getUsers를 하는 서비스들


**애스팩트(aspect)**: 객체지향 모듈을 오브젝트라고 부르는것과 비슷하게 부가기능 모듈을 애스팩트라고 부르며, 핵심기능에 부가되어 의미를 갖는 특별한 모듈

애스팩트는 부가될 기능을 정의한 어드바이스와 어드바이스를 어디에 적용할지 결정하는 포인트컷을 함꼐 갖고 있음.

aop라는 뜻 자체가 어플리케이션의 핵심적인 기능에서 부가적인 기능을 분리해서. 애스팩트라는 독특한 모듈로 만들어서 설계하고 개발


**advice.** 실질적으로 부가기능을 담은 구현체.

어드바이스의 경우. 타겟 오브젝트에 종속되지 않기에 순수하게 부가기능에만 집중.

애스팩트가 무엇을, 언제 할지 정의

포인트컷pointcut: 부가기능이 적용될 대상을 선정, 즉 어드바이스를 적용할 조인포인트 선별하는 기능


***조인포인트 (JoinPoint)***


어드바이스가 적용될 수 있는 위치를 얘기합니다.

다른 AOP 프레임워크와 달리 Spring에서는 메소드 조인포인트만 제공하고 있습니다.

(그래서 여러 책이나 문서에서 조인포인트에 대해 생략하기도 합니다. 무조건 메소드 단위로만 지정하기 때문입니다.)

따라서 Spring 프레임워크 내에서 조인포인트라 하면 메소드를 가리킨다고 생각하셔도 됩니다.

타 프레임워크에서는 예외 발생할 경우, 필드값이 수정될 경우 등도 지원하고 있습니다.

인트로덕션 (Introduction)

타겟 클래스에 코드 변경없이 신규 메소드나 멤버변수를 추가하는 기능을 얘기합니다.

위빙.weaving

지정된 객체에 애스팩트를 적용. 새로운 프록시 객체를 생성하는 과정. 예를들어~ A라는 객체에 트랜잭션 애스팩트가 지정되어있다면, a라는 객체가 실행되기전

커넥션 오픈하고 실행이 끝나면. 커넥션을 종료하는 기능이 추가된 프록시 객체 생성, 이 프록시 객체가 앞으로 a객체가 호출되는 시점에서 사용


boardservice에 애스팩트 적용

즉, Spring AOP 에서는 Dynamic Proxy 기법을 이용해서, Proxy 클래스를 덧씌워 AOP 를 구현한다.


Spring AOP 동작 원리
Spring AOP는 Proxy를 기반으로 한 Runtime Weaving 방식이다


Spring AOP 구현 방식
=

1. XML 기반의 POJO 클래스를 이용한 AOP 구현

부가기능을 제공하는 Advice 클래스를 작성한다.

XML 설정 파일에 <aop:config>를 이용해서 애스펙트를 설정한다. (어드바이스와 포인트컷을 설정함)

2. @Aspect 어노테이션을 이용한 AOP 구현

@Aspect 어노테이션을 이용해서 부가기능을 제공하는 Aspect 클래스를 작성한다.

이 때 Aspect 클래스는 어드바이스를 구현하는 메서드와 포인트컷을 포함한다.

XML 설정 파일에 <aop:aspectj-autoproxy />를 설정한다.


만약 내가 작성한 모든 메서드의 실행 전후로 로그를 남기고 싶다면?


데이터베이스에 대한 쓰기 작업 전후의 트랜잭션 관리를 일일이 명시하지 않고 자동으로 하고 싶다면?

전통적인 클래스 관점의 OOP 세계에서는 깔끔하게 대응하기가 쉽지 않다.

AOP 도입으로 로그 출력 기능 구현 (JDK Dynamic Proxy)


1. 스프링 @AOP를 사용하기 위해서는 다음과 같은 의존성을 추가해야 한다.

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>

```

2. 아래와 같이 @Aspect 어노테이션을 붙여 이 클래스가 Aspect를 나타내는 클래스라는 것을 명시하고 @Component를 붙여 스프링 빈으로 등록한다.

```
@Component
@Aspect
public class PerfAspect {

    @Around("execution(* com.saelobi..*.EventService.*(..))")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable{
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed(); // 메서드 호출 자체를 감쌈
        System.out.println(System.currentTimeMillis() - begin);
        return retVal;
    }
}
```

@Around 어노테이션은 타겟 메서드를 감싸서 특정 Advice를 실행한다는 의미이다.
위 코드의 Advice는 타겟 메서드가 실행된 시간을 측정하기 위한 로직을 구현하였다.

execution( com.saelobi...EventService.*(..))가 의미하는 바는
com.saelobi 아래의 패키지 경로의 EventService 객체의 모든 메서드에 이 Aspect를 적용하겠다는 의미다.


3. target인 EventService는 Interface이다.

```
public interface EventService {

    void createEvent();

    void publishEvent();

    void deleteEvent();
}
```

4. interface를 implements 하여, JDK dynamic proxy가 사용되며 오버라이딩 메서드들이 proxy를 사용한다.

```
@Component
public class SimpleEventService implements EventService {

    @Override
    public void createEvent() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Created an event");
    }

    @Override
    public void publishEvent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();;
        }
        System.out.println("Published an event");
    }

    public void deleteEvent() {
        System.out.println("Delete an event");
    }
}
```


5.런타임 위빙방식으로 실행되면, 프록시가 생성되어 로그 출력

5. 런타임 위빙 방식으로 실행되면, 프록시가 생성되어 로그 출력을 하게된다.

```
@Service
public class AppRunner implements ApplicationRunner {

@Autowired
EventService eventService;

@Override
public void run(ApplicationArguments args) throws Exception {
    eventService.createEvent();
    eventService.publishEvent();
    eventService.deleteEvent();
}
}
```

Created an event
1003
Published an event
1000
Delete an event
0


![다운로드 (2)](https://user-images.githubusercontent.com/75001605/166568301-327ea97c-0cef-4dff-89fc-8f169d0f528b.png)

AOP(SpringAOP/AspectJ) 관련 참고 : https://ehdvudee.tistory.com/22
https://skasha.tistory.com/45
프록시 관련 참고 : https://dahye-jeong.gitbook.io/til/spring/2020-03-21-ioc/2020-04-10-aop-dynamicproxy

