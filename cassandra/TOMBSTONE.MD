https://blog.voidmainvoid.net/469?category=912403

카산드라는 NULL값을 가지는 칼럼이없다.


대신 데이터를 사용할때 Null을 활용 가능하다. update users set location = null where username = 'alice'는 delete location from users where

user = 'alice'와 같다. 위와 같은 Null을 활용한 삭제는 여러 업데이트를 동시에 할때 유용하다.




 update users set email='kim@naver.com', location=Null where username='carol'



카산드라의 delete는 tomebstone이라는 삭제를 나타내는 flag로 칼럼을 업데이트하는걸 의미.


카산드라에서는 데이터의 TTL 또는 삭제에 의해 데이터가 바로 삭제되는 것이아니라 TOMBSTONE을 사용하여 데이터를 삭제합니다.


데이터를 바로 삭제하는것은 매우 어려운일. 데이터를 삭제하기 전에 툼스톤이라는 플래그 사용해서 해당 데이터가 삭제 대기로 옮긴 이후 추후에 데이터를 삭제


컴팩션? (Compaction.) 카산드라에서 컴팩션은 최고의 성능을 내기 위해. 적재된 데이터를 결합하는 행위 

압축이랑 컴팩션이랑 헷갈리면 안됨. 컴팩션 전략

- Size Tiered Compaction Strategy (STCS)


- Time Window Compaction Strategy (TWCS)


TTL을 위해 디자인되었습니다. 시간순으로 되어 있는 데이터가 변하지 않을 경우 적합합니다.


```
만약 sstable이 툼스톤 데이터만으로 가득 찼을 경우 sstable은 다른 sstable과 병합하지 않고 drop될 수 있습니다. 

만약 툼스톤으로만 가득찬 sstable이 있지만 컴팩션으로 인해 데이터가 삭제되지 않는다면 sstable이 오래된 데이터를 가지고 있을 가능성이 있습니다. 이 때 sstableexpiredblockers 

툴을 사용하여 sstable을 삭제할 수 있습니다. TimeWindowCompactionStrategy를 사용할 경우 유용합니다.
```



