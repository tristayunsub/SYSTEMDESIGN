
링크
[grpc 깊게배우기1](https://medium.com/naver-cloud-platform/nbp-%EA%B8%B0%EC%88%A0-%EA%B2%BD%ED%97%98-%EC%8B%9C%EB%8C%80%EC%9D%98-%ED%9D%90%EB%A6%84-grpc-%EA%B9%8A%EA%B2%8C-%ED%8C%8C%EA%B3%A0%EB%93%A4%EA%B8%B0-1-39e97cb3460)

링크
[grpc 깊게배우기2](https://medium.com/naver-cloud-platform/nbp-%EA%B8%B0%EC%88%A0-%EA%B2%BD%ED%97%98-%EC%8B%9C%EB%8C%80%EC%9D%98-%ED%9D%90%EB%A6%84-grpc-%EA%B9%8A%EA%B2%8C-%ED%8C%8C%EA%B3%A0%EB%93%A4%EA%B8%B0-2-b01d390a7190)


저는 현재 앱을 만들기 위해서 공부를 하고 있어요! flutter + golang 으로 grpc 통신으로 작성하려구요.

gRPC 주로 서버와 서버통신에 사용된다고 합니다. MSA 환경에서 서버와 서버 통신에서 REST를 이용하지 않고 gRPC를 사용하는 사례도 점점 증가하고 있다고 하네요. 기본 REST와 다르게 HTTP/2.0 위에서 동작합니다.

다른 언어간에 통신할때 패킷 짜고 파싱하는걸 각각 만들어야 될텐데


Docker와 Kubernetes 덕분에 microservice를 인프라가 인력이 빵빵한 대기업만 사용할수있는것이 아니라 작은회사,개인도 사용할 수 있습니다. Microservice를 사용한다면 필수적으로 따라오는것이 interface문제입니다.  scale up을 예상하거나,  기존 legacy 코드를 마이크로 서비스로 편입시킨다면 gRPC 인터페이스가 괜찮다 라고 생각합니다.
*****

4) 높은 메시지 압축률과 성능

- HTTP/2에 의한 압축뿐만 아니라 protoBuf에 의한 메시지 정의에 의해서 메시지 크기를 획기적으로 줄임

*****

5) 다양한 gRPC 생태계

- 필요에 따라 Authentication, Tracing, Load Balancing, Health Checking, API Gateway 등의 다양한 도구 지원

프로그램 규모가 커질 수록 구성원들의 철학이나 기술 스택이 제각기 다르니 운영도 어려워지는데요. 

이에 gRPC는 앞서 언급한 특징 덕에 이러한 단점을 보완하며 장점을 극대화 시킬 수 있습니다.

*****

google remote procedure call

1회차는 소켓-rpc -rest 를 거쳐 개발된 gprc의 등장 배경과

gRpc의 가장 큰 특징인 HTTP/2와 프로토콜 버퍼에 대해 

2회차. 프로토콜 버퍼의 ENCODING/DECODING 원리

함꼐 읽으면 gRPC에 대해 더욱 더 깊이 알아볼수있다.

자원 한정적인 환경에서도 유용합니다. byte/호출/cpu 수 등으로 과금되는 클라우드 환경에서는 비용 절감의 효과도 생각할 수 있겠네요.

최근엔 시스코, 주니퍼 등 주요 네트워크 장비에서도 grpc를 모두 지원

*****

1. 등장 배경

**Server Client Model**


프로그램은 모놀리틱 구조로 설계되었음. 모든 기능이 한공간에서 구동되다보니 네트워크가 별로  안중요

기술 발전에 따라 소형 컴퓨터 장비들이 등장. 근데 이비싼걸 저가의 워크스테이션 서버로 대ㅔ하고싶어함


근데 메인 프레임워크의 초고사양을 워크스테이션에서는 그대로 제공하기 힘듬

워크스테이션 서버로 분산하고, 넷웤 연결해서 만든 서비스 채택.

소켓이란? 대부분의 언어에서 api형태로 제공하는 편리함 때문에 지금도 사용되고 있지만,

서비스가 고도화 됨에 따라 data formatting 하는것도 어려워짐

*****


2.RPC

이런 소켓의 한계에서 RPC라는 기술이 등장.


네트워크로 연결된 서버상의 프로시저(함수,메소드)등을 원격으로 호출. 네트워크 통신 작업을 챙기기 귀찮으니

통신이나 CALL방식 신경안쓰고. (IDL) 언어 기반으로 다양한 언어 환경에서도 쉽게 확장 가능..

RPC의 핵심개념은 스텁? 하스상드?

서버와 클라이언트는 서로 다른 주소 공간을 사용

함수 호출에 사용된 매개변수를 꼭 변환해줘야함. 안그러면, 메모리 매개 변수에 대한 포인터가 다른 데이터를 가리키게됨

이변환을 담당하는게 스텁.

client 스텁은 함수 호출에 사용된 파라미터의 변환 marshalling, 및 함수 실행 후 서버에서 전달된 결과의 변환을, server stub은 클라이언트가

전달한 매개변수의 역변환 언마샬링 및 함수 실행 결과 반환

*****


> 1.중요포인트 
>    > RPC통신과정
>    > IDL 사용해서 호출규약 정의, 함수명, 인자, 반환값, IDL 파일을 RPCGEN으로 컴파일, STUBCODE가 자동 생성
>    > STUB CODE에 명시된 함수는 원시코드의 형태로, 상세 기능은 SERVER에서 구현
>    > 
>    > CLINET에서 스텁에 정의된 함수 사용할때 -> CLIENT STUB은 RPC RUNTIME 통해 호출. -> 서버는 수신된 프로시저 호출에 대한 처리후 결과값 반환


 최종적으로 Client는 Server의 결과 값을 반환받고, 함수를 Local에 있는 것 처럼 사용할 수 있습니다.

 RPC는 원격지의 것을 끌어다 로컬처럼 사용하고, 확장성 좋은데 왜 쉽게 찾아보지못했을까
 
 RPC가 버려지고 REST가 차지하게됨
 
 그러나 REST도 한계가 존재. 
 
 
 PARAMETER의 응답 값이 명시적이지 않다. 
 
 HTTP 메소드의 형태가 제한적 세부 기능 구현에는 제약이 존재. 
 
 JSON 역시도 간결한 KEY VALUE 구조 기반으로 해결하는듯 했으나.
 
 제공되는 자료형의 한계로 파싱 후 추가 형변환이 필요한 경우가 많아짐.
 
 STRING기반이라 사람이 읽기 편하지만. 바꿔말하자면 데이터 전송 및 처리를 위한
 
 
 **GRPC**
 Serialization이 필요하다.
 
  HTTP/2를 사용한다는 것과 프로토콜 버퍼로 데이터를 전달한다는 점입니다.
  
  Proto file만 배포하면 환경과 프로그램 언어에 구애받지않고 서로 간의 데이터 통신이  가능
  
  
  
>  1. HTTP/2

>       > *1은 기본적으로 클라이언트의 요청이 올때만 서버가 응답하는 구조. 매 요청마다 connection을 생성. cookie 등 많은 메타정보들을 
>       > 장하는 무거운 header가 요청마다 중복 전달되어 비효율적이고 느린 속도 보여줌.
>       > 이걸 바꿔서 connection으로 동시에 여러 개 메시지를 주고 받으며. HEADER를 압축.
>       > header를 압축하여 중복 제거 후 전달하기에 version1에 비해 효율적. 필요시 클라이언트 요청없이도 서버가 리소스 전달 가능.

*****


> 2. 프로토콜 버퍼 PROTOBUF
>       > PROTOCOL 버퍼는. 구글사에서ㅓ 개발한 구조화된 데이터를 직렬화하는 기법
>       > 데이터 표현을 Byte단위로 변환. 아래 json은 82byte소요되지만. protocol buffer는 필드번호, 유형들을 1byte로 받아서 식별. 주어진 length만큼만 읽도록 해서
>       > 33byte만 필요하게됨

*****

> 3.프로토 파일
>      > 1. message and field 프로토파일에서 주고받는 data를 message라고 정의함. 이 메시지는 여러가지 타입의 필드로 구성 query, page_num,result_per_page,라는 필드
>      > 를 가지는 searchRequest라는 메시지를 정의 syntax는 proto3

*****


  >4.package 
  > package 는 message type 이름을 중첩없이 구분할 때 사용합니다. 메시지 사용 시 package를 명시함으로써 필드와 명확히 구분해주죠. 아래 예제에는
   
   open이라는 message를 타입으로하는 field 이름을 open으로 주어. 모호한 정의를 package로 구분. 사실 foo.bar라는 package를 굳이 쓰지 않는다고 사용이 불가한건아님
   
   *****
   
   
   
>  5. Service
   
>    > service는 RPC를 통해 서버가 클라이언트에게 제공할 함수의 형태를 정의. 서비스 명과 RPC 메소드명 모두 CAMELCASE 옵션을 주지않으면 단일 요청/응ㅇ답 동작
>    >  stream 옵션 주면 RPC 구현
   
   ```
   
  service SearchService {
     rpc Search (SearchRequest) returns (SearchResponse);
     }
     
     
  service SearchService {
     rpc Search (stream SearchRequest) returns (stream SearchResponse);
     }
  
  ```
   
*****
   
> 2.프로토콜 버퍼의 encoding/decoding 원리. grpc의 특 장점과 활용에 대한 설명
>     > serialize 동작 방식. mesage key-value encoding. , key는 field number뿐만 아니라, 해당 field의 data type을 지시하는 wire type 표현
>     >보통 1byte에 키필드넘버 5, 와이어타입 3bit . wire 타입은 선언한 data type 별로 지정됌 
     
     
     wire type에는

![0_tC507UO9Qv1Sj3kk](https://user-images.githubusercontent.com/75001605/167276875-e80cae68-50d2-4cdf-9900-33120bdf9cf4.png)

