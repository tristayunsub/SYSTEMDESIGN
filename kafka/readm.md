https://team-platform.tistory.com/6?category=829378



kafka는 대용량 메세지 처리 성능이 좋다고 하는데.. 

그러면 처리량은? hdd와 ssd에서 차이는? producer와 consumer 수에 따라서 성능 차이가 날까?

여러 consumer group이 같은 토픽을 조회해도 성능차이가 없다던데. 과연그럴까?



어떤 블로그에서는 HDD보다 SSD가 최대 2.7배 성능이 높다고 하고, 어떤 학술 논문집에서는 차이가 거의 없다고 한다.


테스트케이스. 

하드웨어 스펙차이에 따른 성능비교 

브로커 증가에 따른 성능 비교

Producer 및 consumer 수에 따른 성능 비교

consumer group수에 따른 성능비교

테스트 환경
Zookpeer 3노드
Kafka 6노드
Test 장비 4노드


 명령어들
 
producer
topic 토픽이름, record-size : 테스트 레코드 byte, num-records:  생성할 메세지 수, producer-prop 프로듀서 옵션처리, bootstrap.servers:브로커 리스트

throughput: 최대 메시지 처리량 제한




consumer 
messages: 소비할 메세지 수, show-detailed-stats: 통계지푤 ㅣ포트.

group:consumer group

broker-list: 브로커 리스트, reporting-interval: 리포팅 간격





* PRODUCER 테스트

1개의 프로듀서에선 하드웨어 스펙 상관없이 ㅗㅇ일한 처리량

F4S 스펙에서는 N개의 PRODUCER가 늘어나면 처리량은 1/N으로 나눠짐. 전체처리량 같음

F8S 스펙에선 더 높은성능. 처리량이 ㅏㅁㄶ아짐


* CONSUMER에선

1개의 CONSUMER일 경우. SSD가 HDD보다 좀 더 빠름



SSD환경일수록 전체 처리량 많아짐

결론

SSD에 비해서 성능 차이가 있을 뿐이지 HDD도 성능이 엄청 좋다. 
HDD 기준 전체처리량은 Producer 초당 10~12만 메시지(1kb)를 적재, Consumer는 초당 22~24만 메시지를 소비한다. 



카프카의 이해


![99EA16425C46ABA62F](https://user-images.githubusercontent.com/75001605/167772254-31d3977b-73e2-4f2b-a37f-2c5c8bd4ba00.png)


메세지큐? 게임로그 수집을 위한. 중간에 버퍼역할. 

메세지 지향 미들웨어. 비동기 메세지를 사용하느 다른 프로그램 사이의 데이터 송수신을 의미함.

MOM을 구현한 시스템을 메세지 큐



(발행/구독: pub-sub은 메시지를 특정 수신자에게 직접적으로 보내주는 시스템이 아니고, 

메시지를 받기를 원하는 사람이 해당 토픽(topic)을 구독함으로써 메시지를 읽어 올 수 있다.)



* 카프카의 특징

대용량 실시간 로그 처리에 특화, TPS가 우수. 메시지를 메모리에 저장하는 기존 메시징 시ㅡ템과는 달리 파일에 저장하는데

그로인해 카프카 재시작해도 **메시지 유실우려 감소**


```
기존의 rabbitmq는 브로커가 컨슈머에게 메시지를 push해주는 방식.

카프카는 컨슈머가 브로커로부터 메세지를 직접 가져가는 pull 방식으로 동작
```

컨슈머는 자신의 처리능력만큼의 메세지만을 가져와 최적의 성능을 낼수있다는것

여기서 한가지 의문이드는데. 일반적ㅈ으로 파일보다 메모리가 우수한데? 왜 카프카가 성능이 좋을까??


그 이유는. 카프카의 **파일 시스템을 활용한 고성능 디자인**?

하드디스크의 순차적 읽기에 대한 성능은 메모리보다 크게 떨어지지않음.



구성요소 
=

```
topic,partition, offset

producer,consumer, consumer group
broker, zookeeper,

replication
```

토픽과 파티션: 카프카에 저장되는 메세지는 topic으로 분류. topic은 여러개의 partition으로 나눠짐.

그 안에 message의 상대적 위치를 나타내는 offset이 있꼬. 이 offset 정보를 이용해. 이전에 가져간 메세지의 위치 정보

동시에 들어오는 많은 데이터를 여러개의 파티션에 나누어 저장. 병렬처리한다


파티션갯수가 4개고 컨슈머 갯수가 5개이면 컨슈머5는 그냥 아무일도 안하게 된다.(일반적으로 파티션 갯수와 컨슈머 갯수는 동일하게 구성하는 것을 추천한다고 함.)



Broker, Zookeeper : broker는 카프카 서버를 칭한다. 동일한 노드내에서 여러개의 broker서버를 띄울 수 있고, 

Zookeeper는 이러한 분산 메시지큐의 정보를 관리해주는 역할을 한다. 카프카를 띄우기 위해서는 반드시 주키퍼가 실행되어야 한다.



Replication : 카프카에서는 replication 수를 임의로 지정하여 topic를 만들 수 있다. replication-factor에 지정하는데 만약 3으로 하면 replication 수가 3이 된다.


레플리케이션의 필요성? 그냥 데이터 복제가아니라 broker 문제 발생시 broker역할을 다른 broker에서 즉각적으로 대신 수행하게끔


```
leader follower 요소. 모든 데이터의 read/write는 오직 leader에서 이루어지고, follower는 leader와 sync를 유지함으로써~ leader에 문제가 생길경우

follwer들 중 하나가 leader역할 하게되는것
```



![99E5AD425C3FE33B10](https://user-images.githubusercontent.com/75001605/167775911-222e37ab-553c-4298-90db-c9996364fcaf.png)

만약카프카 클러스터 내에 broker 2에서 장애가 발생되었다면. broker2에 있던 topic2의 역할을 대신 수행하기 위해 아래 그림과 같이 broker1에 있는

topic가 leader역할을 하게 되는것.



![997EAC435C3FE33B0F](https://user-images.githubusercontent.com/75001605/167775917-ec6879c5-4e27-4228-ae57-543e7b1839c0.png)



복제된 데이터가 follower들에게 있으니, 메시지의 유실이 없다는 장점이 있지만, 

복제를 하기 위한 시간과 네트워크 비용이 들기 때문에 데이터의 중요도에 따라 ack옵션으로 성능과 데이터의 중요도에 따라 다음과 같이 세부설정이 가능하다.


```
ack (default:1)

0 : 프로듀서는 서버로부터 어떠한 ack도 기다리지 않음. 유실율 높으나 높은 처리량

1 : 리더는 데이터를 기록, 모든 팔로워는 확인하지 않음

-1(또는 all) : 모든 ISR 확인. 무손실
```


ack값을 설정하여 데이터의 무손실에 더 중요성을 둘 것인지 또는 유실을 어느정도 감수 하더라고 속도에 중요성을 둘 것인지를 셋팅할 수 있다.



![998C274D5C3FE33B0A](https://user-images.githubusercontent.com/75001605/167776108-3e040737-5fb8-48f3-8dc2-7dee9519c51c.png)

Producer에서는 메시지를 입력하고,

Consumer에서 메시지를 읽어갈때 Zookeeper에서 broker 및 offset정보를 관리하기 때문에 분산처리가 가능하게 된다.










