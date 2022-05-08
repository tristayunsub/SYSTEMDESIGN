https://www.youtube.com/watch?v=Jx9rjPTWYPQ&list=PL9mhQYIlKEheGLT1V_PEby_I9pOXr1o3r&index=5&ab_channel=SKplanetTacademy

large cluster에서 data 처리 위한 알고리즘

hadoop mapreduce는 이 알고리즘을 소프트웨어로 구현한 구현체

key-value 구조가 알고리즘의 핵심

모든 문제를 해결하기에 적합하지는 않을수도있음


map function: (key1, value1) -> (key2, value2)


reduce function: (key2, List of value2) - > (key3, value3)


job에대한 구동 관리는 하둡이 알아서함( 개발자는 비즈니스 로직 구현에 집중)

맵리듀스 구동방식

Local



주요 컴포넌트

* 클라이언트
  * 구현된 맵리듀스 job을 제출하는 실행 주체
* 잡트래커
  *  맵리듀스 job이 수행되는 전체과정을 조정하며, job에 대한 마스터 역할 수행
* 태스트 트래커
  *  job에 대한 분할된 task 수행, 실질적인 data processing의 주체
* 하둡 분산파일 시스템


application daemon

리소스 매니저, 노드매니저 모든 노드에 존재

어플리캐이션 매니저

컨테이너

 job 구동 절차 등등. 간단한 job 해도 오래걸림
 
 그래서 작은데이터 처리하게엔 좀 별로임
 
 erasure coding 가장중요
 
 wordcount 클래스. 
 
 
 wordCountReducer 구현
 
 hadoop jar
 
 하이브로 핸들링하기 편하게 sql로.
 
 아파치 스파크. 하둡이랑만 디펜던시가 있는건아님. 그러나 sql 배치로 던질수있는. RDD라는 데이터 구조를 가지고 다양한걸할수있음
 
 
   
> Erasure Coding은 파일을 N개 블록으로 나누면서 N의 절반인 M개의 Parity 블록을 생성하여 Fault-tolerance를 보장
         > Raid에서 주로 사용하는 Reed-Solomon 알고리즘 사용
 
 >RAID 역시도 스토리지 가상화 기술의 일종 한디스크가 차면 다음 디스크에 저장하는 기본적인 JBOD방식에서 성능과 이터 무결성. 
 >배열 방식은 *strinping*, *mirroring*으로 나뉨
 > RAID는 parity라는 데이터 복구 수단을 두고있음. 원본 데이터를 특정 알고리즘을 활용해 생성할수있는 추가 데이터
 > ***RAID 6의 경우 사용가능한 용ㅇ량은 총디스크용량 - 패리티 용량 = 실제 사용가능한 용량!***
 
 차이점은 RAID는 패리티 보존위치가 DISK ARRAY에 존재해야함, ERASURE 코딩은 패리티 기록은 어디서나 저장가능함.
 
 
 이레이저 코딩도 단점이 존재. 우선 소프트웨어 raid처럼 모든 작업이 소프트웨어로 처리되고 모든 데이터를 인코딩 하는 만큼 패리티 계산에
 
 cpu 오버헤드와 지연 시간이 많이 발생한다.
 
 
 
