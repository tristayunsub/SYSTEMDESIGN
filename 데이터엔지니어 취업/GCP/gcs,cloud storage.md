https://techblog-history-younghunjo1.tistory.com/27?category=872491

클라우드 스토리지에 대한 이해

cloud storage use case로서, 컨텐츠 제공, 백업 데이터 저장, 대량의 데이터 객체 배포

project: cloud storage에서는 모든 데이터가 속하는 공간.

bucket: 양동이. 데이터를 담는 개념

이름이 gcp내에서 전역상에서 고유해야함.

버킷수는 작고 객체가 많을수록 좋은 설계

버킷 생성시 버킷의 이름, 지리적위치, repository 등급 지정.

라벨을 달수있음.


Object : 한국어로는 '객체' 라고하며, 여기서는 버킷에 저장되는 파일들이라고 보면 된다. 객체는 생성 갯수에는 제한이 없으며, 다음과 같이 2가지의 데이터로 구성이 된다.

객체 데이터 : GCS(Google Cloud Storage)에 저장되는 파일

객체 메타(meta)데이터 : key-value 값 형태이며 객체의 퀄리티를 설명


repository 등급에는 multi regional, regional, coldline 같은 개념이 존재


# 참고로, Coldline일때가 가장 high durable 하다고 하는데, 여기서 durable이라는 의미는 데이터가 손실될 가능성이 낮다는 것을 의미한다.

coldline은 재난복구, 아카이빙용, nearline은 백업, long tail 미디어,

regional 동일지역내 분석, 트랜스코딩같은것.







