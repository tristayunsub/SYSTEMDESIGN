https://yeomko.tistory.com/27?category=878347

갈아먹는 BigQuery

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
