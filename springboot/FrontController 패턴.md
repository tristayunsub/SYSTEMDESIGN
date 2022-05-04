Frontcontroller 패턴의 경우 한가지 방식의 컨트롤러 인터페이스만 사용 가능

따라서 여러 인터페이스와 호환 가능하도록 프론트 컨트롤러와 컨트롤러사이 어댑터배치.

A 컨트롤러 인터페이스와 B 컨트롤러 인터페이스가 있다고 가정한다면 각 인터페이스는 110V, 220V와 같이 비유할 수 있습니다. 

이 둘을 모두 수용하기 위해서는 110V에 220V 돼지코 즉, 어댑터를 추가해줘야 합니다.


![다운로드 (3)](https://user-images.githubusercontent.com/75001605/166600201-58b06103-d67f-433f-a7d8-d324a3606510.jpg)

어댑터 패턴을 도입한 MVC 패턴은 아래 그림과 같습니다.

* Handler는 Controller보다 넓은 개념, HandlerAdapter가 있기 때문에 Front Controller는 다양한 Handler를 처리할 수 있음

* HandlerAdapter를 추가함으로써 프레임워크를 유현하고 확장성 있게 설계할 수 있다는 것이 핵심



![다운로드 (6)](https://user-images.githubusercontent.com/75001605/166600503-266c4e82-77e3-414f-9a7e-3efa5db249b0.png)



https://codedot.co.kr/7

어댑터 패턴이란~?

