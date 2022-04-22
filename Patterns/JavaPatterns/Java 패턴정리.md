https://www.youtube.com/watch?v=ejXUhFKcbIU&ab_channel=%EC%BD%94%EB%93%9C%EC%97%86%EB%8A%94%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D

https://www.youtube.com/watch?v=UEjsbd3IZvA&list=PLsoscMhnRc7pPsRHmgN4M8tqUdWZzkpxY&ab_channel=%EC%9D%B4%EC%95%BC%EA%B8%B0%27sG
자바에서 싱글톤패턴 문제나옴.

팩토리 메서드

클라이언트와 오브젝트 생성 방법을 몰라도 요청만하면 쉽게 받을 수 있기에 사용한다.

클라이언트가 고양이나 돼지를 직접 만들지 않아도됀다.

근데 이 팩토리에 강아지 몇마리만들었는지 추적이나 팩토리 상태, 강아지에 날개를 달아주는 기능도 만들고싶음

추가기능 추가. 메소드를 만든다.

Animal 고양이나 강아지. 각각의 다른 기능이있는 팩토리에서 뭘만든다


interface -createanimal()

catfactory-createanimal() + count
dogfactory-creatanimal()

```

class Animal():
 def speak(self):
pass

class Cat(Animal):
  def speak(self):
  print("meow")

class Dog(Animal):
  def speak(self):
  print("bark")

  class AnimalFactory():
    def createAnimal(self):
       pass

         

  class CatFactory(AnimalFactory):
      def __init__(self):
       self.cat_count = 0
                   
      def createAnimal(self):
       self.cat_count += 1
       return Cat()
      def catCount(self):
        return self.cat_self

         
  class DogFactory(AnimalFactory):
    def haveDog(self):
      self.dog = self.createAnimal():
    def createAnimal(self):
       return Dog()
    def makeWings(self,dog:Dog):
      print("dog wings added")
         return dog

  cat_factory = CatFactory()
  cat = cat_factory.createAnimal()
  print(f"{cat_factory.catCount()} cats are created")


    dog_factory = DogFactory()
    dog = dog_factory.createAnimal()
      wing_dog = dog_factory.makeWings(dog)
        팩토리 인터페이스가 핵심이다.

        팩토리 메소드를 가지더라도.


        catcreator
        Dogmanager로 가져있는다.
        class 별로 생성이 되어있는지 확인.
          
   ```
   
   
   생성 패턴엔

factory

  factory method
  abstract factory
  builder pattern
    singleton
    prototype이있다.
팩토리, 싱글톤은 자주쓰임.  


  팩토리

      from enum import Enum
class AnimalEnum(Enum):
  CAT = 1
  DOG = 2

class Animal():
  def speak(self):
    pass

class Cat(Animal):
  def speak(self):
    print("meow")

class Dog(Animal):
  def speak(self):
    print("bark")

      #Factory function
#prefer enum
def FactoryFn(animal:AnimalEnum):
  if animal == AnimalEnum.CAT:
    return Cat()
  elif animal == AnimalEnum.DOG:
    return Dog()


cat = FactoryFn(AnimalEnum.CAT)
cat.speak()
dog = FactoryFn(AnimalEnum.DOG)
dog.speak()


  #Factory class
class AnimalFactory():
  def createAnimal(self,animal):
    if animal == AnimalEnum.CAT:
      return Cat()
    elif animal == AnimalEnum.DOG:
      return Dog()

animal_factory = AnimalFactory()
cat = animal_factory.createAnimal(AnimalEnum.CAT)
cat.speak()
dog = animal_factory.createAnimal(AnimalEnum.DOG)
dog.speak()
    

빌더 

-작게 분리된 인스턴스를 건축하듯 조합하여 객체를 생성
-객체의 생성 과정과 표현 방법을 분리 -> 동일 객체 생성에서도 서로 다른 결과를 만들어 낼 수 있음

프로토타입. 


-원본 객체를 복제하는 방법으로 객체 생성하는 패턴.
-비용이 큰 경우 주로 사용

구조패턴. 클래스나 객체를 조합해 더 큰 구조로 만들 수 있게 도와주는 패턴, 구조가 복잡한 시스템 개발하기 쉽게 해줌

1.어댑터

-호환성이 없는 클래스들의 인터페이스를 다른 클래스가 이용할 수 있도록 변환해주는 패턴
-인터페이스가 일치하지 않을때 이용

2.브리지
->구현부에서 추상층을 분리 -> 서로 독립적으로 확장하게금
->기능과 구현을 두개ㅡ이 별도 클래스로

3.퍼싸드 -> 자주사용 

-더 상위에 인터페이스를 구성함으로써 서브 클래스들의 기능을 간편하게 사용할 수 있도록 하는 패턴
->서브 클래스들 사이의 통합 인터페이스를 제공하는 wrapper 객ㄱ체가 필요

7.프록시
접근 어려운 객체와 여기에 연결하려는 객체사이의 인터ㅍ페이스 역할 수행하는 패턴
네트워크 연결, 메모리의 대용량 객체로의 접근 가능.

6.플라이웨이트 패턴
->가능한 한 공유해서 사용함으로써 메모리를 절약하는 패턴
->다수의 유사 객체를 생성하거나 조작할때 사용.


행위 패턴
클래스나 객체들이 서로 상호작용하는 방법이나 책임 분배 방법을 정의하는 패턴
하나의 객체로 수행할 수 없는 작업을 여러 객체로 분배하면서 결합도ㅡ를 최소화 할 수 있도록 도와줌,

10. 템플릿 메소드(Template Method)
   → 상위 클래스) 골격 정의 / 하위 클래스) 세부 처리를 구체화하는 구조의 패턴
   → 유사 서브 클래스를 묶어 공통된 내용을 사우이 클래스로 정의함 > 코드 양 줄이고, 유지보수를 용이하게 함
   
   11. 방문자(Visitor)
   → 데이터 구조에서 처리 기능을 분리하여 별도의 클래스로 구성하는 패턴
   → 분리된 처리 기능은 각 클래스를 방문하여 수행
   
   7. 옵서버(Observer)
   → 한 객체의 상태가 변화하면 객체에 상속된 다른 객체들에게 변화된 상태를 전달하는 패턴
   → 분산된 시스템 간 이벤트를 생성, 발행하고 이를 수신해야 할 때 이용
   
   3. 인터프리터(Interpreter)
   → 언어에 문법 표현을 정의하는 패턴
   → SQL, 통신 프로토콜 같은 것 개발 시
4. 반복자(Iterator)
   → 접근 잦은 객체에 대해 동일 인터페이스를 사용하도록 하는 패턴
   → 내부 표현 방법의 노출 없이 순차적 접근이 가능
