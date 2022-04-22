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
   
   
   


