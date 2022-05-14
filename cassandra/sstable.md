https://blog.voidmainvoid.net/467?category=912403





sorted string table이란? storage engine을 구성하기 위한 데이터 저장에 사용되는 데이터 구조중에 하나이다.


파일에 데이터가 정렬되어 저장되는게 특징

sstable 요구 조건.

데이터는 key와 value로 구성됨. key 기준으로 데이터가 정렬되어있어야 함.

sstable 내에는 중복되는 key를 가진 데이터는 없어야함


SSTABLE의 장점. 

MERGE가 쉽고 빠르다. 데이터가 SSTABLE로 저장된 여러개의 segment(디스크 파일)이 있다고 가정.

각 segment내 데이터는 키 기준으로 정렬되어있으므로 merge sort 알고리즘을 사용해서 병함.




SSTable을 구성하려면 데이터가 sorting 되어 들어와야 한다.

그러나 일반적으로 데이터가 들어올 때 sortring된 데이터만 들어오는 경우는 없다.



SSTable의 segment를 구성하려면 데이터가 들어갈 위치를 찾아야 하고, 

중간에 끼워넣어야 하기 때문에 효율적이지 않다. 그렇다면 SSTable segment를 활용하여 어떻게 효율적인 데이터 저장 구조를 구현할 수 있을까?




처음 들어온 데이터는 메모리에 구성되어 있는 balanced tree에 저장된다.

balanced tree는 ***AVL이나 red-black tree***를 이용해 구성하면 된다.


메모리의 balanced tree에 어느정도 데이터가 쌓이면 (BT1), SSTable segment를 생성하여 BT1의 데이터를 저장한다.


balanced tree에는 데이터가 순서대로 저장되어 있기 때문에, SSTable segment에는 append-only만으로 효율적으로 데이터를 저장할 수 있다


데이터의 조회

Balanced Tree를 가장 먼저 조회하고, 없다면 SSTable segment 들중에 가장 최근에 생성된 segment 순으로 조회한다.


장점

SSTable segment 파일은 append-only 만으로 데이터가 저장되기 때문에

sequential write 연산만을 사용하여 데이터가 저장되어 매우 효율적이다. SSD 뿐만 아니라 magnetic 디스크 저장 장치에도 빠르게 데이터를 저장할 수 있다.


SSTable의 segment는 immutable 하기 때문에, OS 파일 캐시가 사용되면 더욱 빠르게 조회가 가능해진다.


한마디로 레드블랙트리가 왜중요한지~ 나오는구나 관계형데이터베이스에선 안쓰이고.

