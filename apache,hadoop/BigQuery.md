https://yeomko.tistory.com/27?category=878347



갈아먹는 BigQuery

P1. 빅쿼리 소개


P2. 스키마 및 데이터 모델

P3. 빅쿼리 SQL 분산 실행

P4. 빅쿼리 아키텍처


빅쿼리로 415.8gb에 달하는 데이터를 처리하여 16.2초만에 주어진 SQL문을 수행.


Map Reduce 방식을 통해서 데이터 웨어하우스를 구축하였다고 합니다. 

문제는 간단한 분석 작업 하나도 맵 리듀스 방식으로는 하루가 넘게 걸리는 등 속도가 너무 느리다는데 있었습니다. 

 

이를 극복하고자 시작한 것이 Dremel 프로젝트


Dremel은 structured 데이터를 분산 저장하고 SQL문을 통해 빠르게 데이터를 분석하는데 특화


 미친 성능을 보여주는 것은 다음 두 가지 특징 덕분입니다. 하나는 Columnar Storage, 다른 하나는 Tree 기반의 distribution입니다.
 
 
 record-oriented 와 colum -oriented의 차이. columnar storage
 
 columnar storage의 장첨
 
 1. minimize traffic. 

예를 들어 select top from foo 같은 커리가 들어올때 컬럼 기반 저장 방식에선 해당하는 컬럼만 조회하면 됨.

이는 데이터 양이 방대해 질 수록 효과가 극대화. 그러므로 빅쿼리에 앞으로 쿼리를 입결할때는 "select *" 와 같은 문법은 지양

2. Higher compression Ratio

컬럼 단위로 데이터를 저장한다는 것... 곧 같은 데이터 타입의 데이터들이 몰려서 저장되는것을 의미한다.

그 결과 압축하기에 더 용이. 일반적인 RDBMS는 데이터를 1:3비율로 압축. 컬럼 기반 저장은 1:10 ratio .

많이 압축한만큼 쿼리 수행성능 향상

하지만 Dremel은 이러한 컬럼 기반 저장에 대규모 분산 처리를 성공적으로 결합하여 성능을 극대화 


Tree Architecture Distribution
=

![다운로드](https://user-images.githubusercontent.com/75001605/167543507-547372e3-75da-40f2-a520-17ea43736b2e.png)
  

쿼리 연산을 분산시키기 위해서 트리 구조를 활용합니다

루트서버 sql 쿼리문 분석 -> 분산머신에서 동작하는 작은 단위 쿼리문으로 쪼갬. 이를 intermediate server로 전달.

이 서버들은 다시 실제 연산을 수행하는 leaf node에게 쿼리 전달.결과 값으로 반환되는 값을 합쳐서 부모 노드에게 전달.

 좀 더 자세한 내용은 Dremel 논문 리뷰



 빅 쿼리의 재미있는 특징 중에 하나인 Array와 Struct를 소개하면서 빅 쿼리가 지향하는 스키마 설계 방식
 
 
 
 Part2.데이터모델
 =
 
 
 Bigquery는ㄴ  정형화 된 데이터(structured data)를 저장합니다
 
  raw data를 곧바로 저장하는 데이터 레이크로는 그다지 적합하지 않고, 전처리를 거쳐서 정제된 데이터를 적재하는 데이터 웨어하우스로 적합합니다
  
  정규화로 데이터를 쪼개서 데이터를 저장하면. 트겆ㅇ 거래의 상세정보 확인시 조인연산이 들어가죠
  
  근데 이 경우 조인연산에 비용 많이들어감. 빅쿼리 분석 대상은 데이터가 매우 크니까 비효율적. 그래서 denormalization을 실행하는것
  
  
  ![다운로드 (1)](https://user-images.githubusercontent.com/75001605/167545360-dbbb9662-16b4-4197-82a0-5af73bc7e4ce.png)

  반정규화 거친 테이블에서는 다시 중복된 데이터가 저장된다는 문제점이 존재. 또한 중복되는 키 값에 대해서 group by 연산을 수행할 경우
  
  성능이 저하되는 문제점도 있음. 
  
  **nested and repeated**
  
  Nested와 Repeated Column은 중복되는 값을 한번만 저장해도 되며,
  
  미리 group by 연산을 수행한 것과 같은 효과를 내줍니다. 그 결과 데이터 중복은 줄어들고 성능이 향상됩니다
  
  빅 쿼리는 어떠한 방식으로 이러한 중첩되고 반복이 발생하는 스키마의 데이터를 컬럼 기반으로 저장할 수 있는 것일까
  
  Dremal Data model
  =
  
 
  Dremel의 논문 데이터모델 소개.
  
  ![다운로드 (2)](https://user-images.githubusercontent.com/75001605/167546115-0a5cfbae-befd-444c-935e-d4f690d74d69.png)
  
  required, optional, repeated. 등의 생소한 단어들이 보임. 이를 json방식으로 표현하자면
  
  group이란. 여러 필드들이 묶여있는 객체. repated는 배열., optional grou Links는 document에서 있어도되고 없어도도 괜찮은
  
  객체 멤버 변수 Links.
  
  메타데이터를 r1과 r2로 나누어보면
  
  
  
  ![다운로드 (3)](https://user-images.githubusercontent.com/75001605/167546940-ff86ed44-b32f-4cdc-b191-b8000f735091.png)

  
  얼핏 보기에도 RDBMS 테이블 형식으로 저장하기에는 다소 까다로워 보이는 형태
  
  oriented nosql 솔루션이 적합해 보입니다. 그러나 Dremel은 이를 컬럼을 기준으로 저장하는 방식을 제안
  
  
  이러한 복잡성을 핸들링하기 위해서 제안하는 것이 ***repitition level과 definition level***입니다.
  
 
 repetition level?
 
 
 ![다운로드 (4)](https://user-images.githubusercontent.com/75001605/167546968-efe41c71-c905-497d-ae2b-90d282933717.png)

 
 테이블
 
 value r  d 
  
  json 데이터를 칼럼별로 나누어 저장이 가능하다면. 우리는 이 컬럼별로 저장된 데이터만 보고 원래의 json 데이터를 복원
  
  그러면 repetition level이 필요한 배경과 개념
  
  만약 r과 d가 없다고 가정하고 Name.Language.Code를 보자. (이를 앞으로 value의 path라고 부른다). en-us, en,
  
  Null, en-gb, NULL 데이터가 있다. 우리는 이들이 어느 위치에서 등장하는지 알 수 없음.
  
  가령 아래처럼 분명 EN이라는 값이 저장되는 위치는 다르지만  컬럼별로 뗴어내어 저장할 경우 결과가 동일해짐
  
  
  ![다운로드 (5)](https://user-images.githubusercontent.com/75001605/167547090-4b45612c-7e1a-4b85-a1fa-3a05385a95b0.png)

```
이러한 상황을 방지하고자 도입한 개념이 repetition level입니다. 한 마디로 표현하면 자신 이전에 등장한 같은 path의 value와 함께 묶여져 있는 level입니다. 말만으로는 이해하기 어려워서 사례를 통해서 이해해보도록 하겠습니다. 문서 r1을 위에서 아래로 스캔하며 Name.Language.Code 변수들의 repetition level을 정해보겠습니다.

 

가장 먼저 첫 번째 Name 아래 en-us가 보입니다. 이는 이전에 등장한 같은 path의 value가 없었기 때문에 r은 0이 됩니다.

그 다음으로 넘어가면 en이 보입니다. 이는 같은 path를 지닌 en-us와 같은 Name.Language에 묶여있습니다. 그러므로 r=2가 됩니다.

 

아래로 넘어가보면 Name의 두 번째 원소는 아예 Language를 포함하고 있지 않습니다. 그러므로 Name.Language.Code는 Null 값이며, 

이때 같은 path를 지닌 이전 값과는 같은 Name 아래에 묶여있습니다. 그러므로 r=1이 됩니다. 


그 아래로 넘어가보면 마지막 Language가 보이고 en-gb라는 Code가 보입니다. 이는 이전에 등장한 동일한 path의 값 en과는 같은 Name에 속하므로 r=1이 됩니다. 

r2에도 동일한 방식을 적용하여 정리를 해보면 아래와 같은 결과를 얻을 수 있습니다.
```
 
 
 Definition level
 =
 
 repetition level을 통해서 우리는 변수들이 등장하는 위치에 대해서 알 수 있게 되었지만, 
 
 아직 컬럼 기반 저장 데이터만 보고 json을 복구해 내기에는 부족합니다. 바로 NULL 값의 문제
 
 이번에는 Name.Language.Country의 예시를 통해서 알아보겠습니다.

  
  ![다운로드 (6)](https://user-images.githubusercontent.com/75001605/167552420-70432a82-980f-4699-a4db-a842499e5dd3.png)

  
  value가 있는 값-> 해당 변수의 path의 level이 d가 됨. name.lang.country의 경우에는 3
  
  문제는 null 값. r1 을 보면 첫번쨰 coode: en의 원소에 country 값이 x . 이경우 name.lang 까지는 정의되어있으니 2라고 치자.
  
  반면 name 두번쨰 원소의 경우 아예 language가 정의되어있지않음. 자연스럽게 country가 null값을 가짐. 이 경우 definition levelㅇㄴ 1이됨.
  
  
  
  
  ![다운로드 (7)](https://user-images.githubusercontent.com/75001605/167552490-51f82bbd-1c12-44a3-a834-4409984d1693.png)

  
  Encoding
  
  이제 각각의 칼럼은 디스크 공간에 블록단위로 저장. 각각의 블록은 repetition level과 definition level을 포함하며,
  
  value들은 모두 압축되어 저장한다. NULL값은 별도로 명시적으로 저장하진 않음.( definition level이 원래 path의 level과 같지 않으면 null 값인걸 알수있으므로)
  
  
  마찬가지로 NULL 값이 아닌 value들은 definition level이 필요하지 않으므로 이를 저장하지 않습니다. (당연히 path의 level과 definition level은 같을 것이므로).
  
  또한 repetition level과 definition level은 사용가능한 최소한의 비트만 사용하여 저장합니다. 가령 repetition level의 최대 값이 3일 경우 2bit만 사용하여 인코딩합니다.
  
  
   Tree Execution을 다뤄봐야겠죠? SQL 쿼리가 실제로 어떻게 수 많은 머신들로 분산되어 실행되는 원리에 대해서 알아보도록 하겠습니다.
