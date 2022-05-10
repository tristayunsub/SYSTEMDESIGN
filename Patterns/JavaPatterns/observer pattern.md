https://www.youtube.com/watch?v=1dwx3REUo34&list=PLDV-cCQnUlIaGcUNSYWeDpHy_3RlMgFkU&index=8&ab_channel=%EC%BD%94%EB%93%9C%EC%97%86%EB%8A%94%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D


감시자들이 한곳을 계속 바라보는것.

폴링, 폴링인터벌? 이벤트가 일어났는지도 알수가없음

옵저버 패턴 ㅏ용하면 이벤트가 발생하는 순간 반응하게함

아침에 알림이 울리면 또는 버튼 클릭하면 관련 함수들이 등장한다.

옵저버패턴 클래스 구조





업데이트를가지고잇음 옵저버는

노드 옵저버1 2 는 업데이트 또 각각가지고있음

이벤트안엔 옵저버들을 소유하고있음. 이벤트안에 리스트로 보관이됨

리스트에 추가하기위해 이벤트는 addobserver(), notify()

강아지와 고양이가 옵저버, 이벤트가 주인. 주인이 오면 동물이 반응하게끔

이벤트에 반응하는 update

상속 고양이, update에서 뮤, 

개는 업데이트에서 bark



owner클래스내부에

self 애니멀 소유

또 register(self, animal:Observer(타입)) 동물추가해주는함수

notify는 update

그리고 owner onwer()
       cat =cat
       dog = dog
       
       owner.register(Cat)
       owner.register(dog)
       
       owner.notify()
       
       왜이렇게 간단하지?
       
```
class Observer:
  def update(self):
    pass

class Cat(Observer):
  def update(self):
    print('meow')

class Dog(Observer):
  def update(self):
    print('bark')
```

```
class Owner:
  def __init__(self):
    self.animals = []
  def register(self,animal:Observer):
    self.animals.append(animal)
  
  def notify(self):
    for animal in self.animals:
      animal.update()

```






