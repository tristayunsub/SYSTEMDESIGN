logged batch와 반정규화. blob 형식?



이를 Denormalization이라고 한다. query-driven design





Denormalization을 하는 데 주의해야할 요소가 있다. Denormalization을 사용하면 하나의 데이터를 여러 번 insert해야 한다. 

즉, 애플리케이션 단에서 insert 쿼리 요청을 카산드라에게 여러 번 보내는 로직을 짜야 한다. 만약 애플리케이션에 문제가 있어 한 번의

insert 동작이 카산드라 내부에 적용되지 않는다면 데이터의 일관성은 파괴된다. 즉, 데이터 일관성에 대한 책임이 application 단으로 넘겨진다.


logged batch statement가 잇다. unlogged batch도 있지만. logged batch가 이 원자성을 보장해줌. 즉 배치로 묶인 여러 쿼리가 모두 성공하거나 실패하거나 둘중하나


결과. 그러나 고립성은 보장해주지 않음. 내부 쿼리 순서가 지켜지지 않을수 있음

카산드라는 logged batch 요청을 받으면 코디네이터 노드의 batchlog 테이블에 blob 형태로 요청 정보 기록.


이와 같은방식으로 배치를 수행. 원자성이 보장되지만. 애초에 원자성을 기대하지않고 nosql인 카산드라를 선택한게아닐까



