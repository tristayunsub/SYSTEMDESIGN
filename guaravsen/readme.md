https://sangminpark.blog/2017/06/21/%EC%BD%94%EB%94%A9-%EC%9D%B8%ED%84%B0%EB%B7%B0-part-4/

https://velog.io/@jahoy/Design-Data-Intensive-Application-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%A4%91%EC%8B%AC-%EC%96%B4%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98-%EC%84%A4%EA%B3%84
데이터 중심 어플리케이션 설계
data intensive application system.
https://blog.naver.com/PostView.naver?blogId=wpdls6012&logNo=221729382459&categoryNo=146&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=search&userTopListOpen=true&userTopListCount=5&userTopListManageOpen=false&userTopListCurrentPage=1

https://brunch.co.kr/@springboot/359#comment 기술블로그.. 기초적인거 다지기좋을ㄷ듯

consistent hashing.? 이런것들?


예전에 구글에서 일하시는 한분이 본인은 시스템 디자인 인터뷰가 가장 어렵다고 이야기한적 있다.
코딩인터뷰의 경우 문제 set이 존재하기때문에 문제를 많이 풀어보면 되지만, 시스템 디자인은 질문을 예측하는것도 어렵고 정답이란것도 존재하지 않기때문이다. 
구글에서 시스템 디자인 문제를 검색해봐도 결과에 정답은 나오지 않는다. 시스템 디자인은 대부분 스케일 문제를 다룬다.
“수억명의 사람들이 너의 서비스를 사용한다면 너는 어떻게 이 문제를 해결할래?” 이런 질문에 경험에서 우러나오는 사골같은 답을 할수 있다면 얼마나 좋을까마는 
사실 그런 시스템을 다 만들어본 엔지니어는 극히 소수일 것이다. 나 역시 시스템 분야로 PhD도 했고 수년간 같은 분야에서 다양한걸 만들었지만 여전히 이 질문은 어렵다.
그러나 디자인 인터뷰 역시 살아남는 방법은 있다. 그 방법이란 좀 싱겁지만…,

“공부를 많이하면 된다.”
https://www.youtube.com/watch?v=q0KGYwNbf-0&ab_channel=Cl%C3%A9mentMihailescu

google system design



디자인 질문들보면
queue 시스템을 디자인해봐라~
twitter 디자인해보라ㅏ~
tinyUrl 디자인해봐라

yahoo의 stock quote 시스템을 디자인

elevator 시스템 디자인해봐라

hadoop의 namenode를 새로 디자인해봐라

커뮤니케이션이 잘 ㅣ뤄지는가?
문제를ㄹ 정확히 아는가?

의도적으로 모호하게 주어짐. queue시스템을 디자인하라는 질문은 사실 밑도끝도없다.

 <어떤 Application이 Queue를 사용하는가?>, <Queue에는 무엇이 들어가는가?>, <Producer/Consumer의 숫자는 몇개인가?> 등 
 디자인에 꼭 필요한 디테일이 빠져있다. 그래서 디자인 질문을 받았을때 처음 해야하는것은 <질문>이다.
 여러번 충분한 질문을 통해서 인터뷰어가 생각하는 진짜 문제를 파악해내고 여기에서 Requirement를 알아내는것이 첫 단계에서 반드시 해야할 일이다. 
 이 과정에서 인터뷰어는 지원자의 커뮤니케이션 능력을 평가한다. 혹시 예전에 비슷한 시스템을 구현해본적이 있다고 질문을 생략한다면 
 이는 인터뷰가 망하는 길로 가는 지름길이다
 
 내가 받았던 디자인 질문중 특히 좋았던 것 한가지는 엘리베이터 시스템을 디자인하라는 것이었다. 무한하게 많은 수의 엘리베이터가 늘어서있다고 가정하고 사용자가 각 층에서 누르는 Up/Down 버튼에 맞추어 어떻게하면 최적의 서비스를 제공할수 있을까라는 질문이었다. 
 이 질문이 좋았던 이유는 엘리베이터라는 잘 알려진 문제가 그동안 생각하지 못했던 수많은 분산시스템의 컨셉을 드러내기 때문이다. 예를들어서,


Controller가 중앙에 한개 놓여있고 모든 엘리베이터가 통제에 따른다고 가정해보자. 그럼 Controller가 죽어버리면 엘리베이터의 Availability는 어떻게 될것인가?
그럼 엘리베이터를 분산시스템으로 구현해보자. 엘리베이터 사이의 커뮤니케이션이 불안정할때 스케줄이 항상 정확할수 있을까? (CAPS theorem)
엘리베이터는 사용자가 버튼을 누른 시간순으로 서비스를 제공한다. 그럼 엘리베이터의 시간은 모두에게 동일하게 흘러가는가? (Vector clock의 개념)


 분산시스템의 클래식한 문제들 — vector clock, CAPS theorem, distributed consensus, quorum vote, data hashing등 — 을 연상해낼수 있는지를 보는것이다. 
 뛰어난 인터뷰어일수록 이런 질문을 던진다. 이런 문제에 답하기 위한 쉬운 방법은 없다. 아래에 달린 긴 리스트에서 보듯, 그저 다양하게 많이 아는 수밖에…


Relational DB를 Shard해서 사용할것인가 아니면 Cassandra, Dynamo와 같은 분산 key-value DB를 사용할 것인가?
In-memory cache로 Redis 혹은 Memcached 를 사용할까?
Push model (모든 follower에게 tweet을 저장) 혹은 pull model (모든 following에게서 tweet을 읽음)을 사용할까?
Tweet의 아이디는 DB의 Sequential ID를 사용할까 아니면 snowflake와 같은 분산시스템을 따로 만들까?


코딩 질문이 한가지 질문에 optimal 알고리즘을 구현하기위한 것이라면 시스템 디자인은 시스템의 각 파트를 해결하기위해 제각각 여러개의 알고리즘을 끌어와서 큰 스케일에서 동작하는 그림을 보여주는 것이다. 그러므로 좋은 답을하기 위한 핵심은 얼마나 많이 그러한 알고리즘을 알고 있는가의 여부이다. 예를들어,

어떻게 분산된 컴퓨터에 데이터를 나눌 것인가 —> Consistent hashing
분산 컴퓨터들은 어떻게 자신이 담당할 문제의 영역을 선택할까? —> Paxos algorithm or zookeeper
데이터가 복제되어있을때 어떻게 consistency를 보장할까? —> Quorum vote
분산 컴퓨터가 어떻게 ID를 고유하게 만들까? —> Twitter’s snowflake
데이터 통신의 보안은? —> PKI encyprtion

