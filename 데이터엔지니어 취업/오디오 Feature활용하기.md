https://heung-bae-lee.github.io/2020/02/20/data_engineering_06/





다이나믹 스키마와 파티션


사용? 데이터가 파티션을 왜 사용하느냐. 데이터가 증가하면 할수록 query문의 성능이 느려짐. 이런 부분을 방지하고자.


데이터 매니지먼트라고 보면됨. 

vertical partitioning은 중복적인 데이터는 erd를 테이블로 분리해서 관리하게끔. 근데 정규화하고도 칼럼수가 늘어나면, 해당 table을 읽는데 속도가 느려질ㄹ 수도 있음.


또한 어떤 column들은 지속적으로 업데이트가 될 수 있지만, 어떤 column들은 지속적으로 업데이트가 되지 않을 수 있기 때문에 사용.

RDBM에서는 나눌수 있지만, 데이터가 엄청나게 많은 양이어서 데이터를 처리하는데 속도의 향상을 기대한다면 사용.

그렇지 않은 경우는 많이 사용하지 않는다.


버티컬 파티션. Going beyond normalization

버티컬 파티션은 무조건 사용된다?.

테이블을 더작은테이블로 나누는 작업해서 normalization 후에도 경우에따라 칼럼을 나눔


동일한 컬럼이지만 데이터의 양을 나누어서 각각 저장하는 방식이다. Partition key를 통해 데이터를 나누어 주는데, AWS안의 DynamoDB를 만들면서 확인해 볼 것이다.

Horizontal partition

spotify developer artist's top tracks를 들어가보면, 아래 그림과 같이 path parameter로 해당 artist id를 받아서 결과를 출력해준다.

```
artist 한명이 여러개의 track을 가지고 있는 경우도 있으므로 artist id만을 partition key로 사용할 수 없다. 

왜냐하면 partition key로 사용되는 경우 해당 column에 동일한 값을 사용할 수 없기 때문이다.

그러므로 artist_id를 partition key로 해놓고, track id를 sort key로 사용할 것이다.

```

 Partition key 부분은 RDS에서 Primary Key와 동일한 역할(빠르게 참조할 수 있게 해주는)을 하는 부분이라고 생각하면된다. 
 
 그러므로 해당 artist_id에 해당하는 row는 유일


Provisioned는 서버를 띄우기에 그 상황안에서 free tier를 사용가능한 사람만 사용가능.



Read capacity units와 write capacity units


 boto3를 설치한다. boto3는 AWS가 제공하는 Python SDK의 이름이다
 
 
 
 데이터파이프라인: 중간에 파이프가 고장난다면?
 
 스케쥴링, 에러핸들링, 데이터 백필은?
 
 https://getchan.github.io/data/airflow_1/
 굉장히좋다 이브로그
 

 뭐 한예로 데이터레이크 역할을 할 S3버켓을만들어보자
 
 일종의폴더로
 
 
 
 . parquet화 해서 사용하면 데이터를 통해서 performance가 빨라지지만, 그렇게 performance를 좋게하려면 정확하게 define을 해 주어야 한다. 보
 
 
 통 json 형식으로 가장 raw 형태로 저장한다음, processing job이 한 번 돌은후에, 새로운 data가 가장 raw data가 S3에 들어왔을때, 
 
 trigger가 되어서 해당 parquet화를 하고 싶은 몇개의 데이터만 뽑은 후 다시 돌아서 다른 S3 bucket안에 저장하는 데이터 파이프라인을 거친다.
