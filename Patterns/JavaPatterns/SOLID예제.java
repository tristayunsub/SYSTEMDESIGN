SINGLE RESPONSIBILITY 단일책임원칙.

  한 파트에 대해선 하나의 책임만 가져야한다.

  def add(num1, num2):
    return num1 + num2

  def numPrint(num):
   print(num)

  굳이 이 함수를 줄이기 위해 합쳐져있는거만들지마라
def addPrint(num1, num2)
  num = num1 + num
    print(num)
      return num
```

class Cat:
  def __init__(self, age, name):
        self.age = age
        self.name = name

  def eat(self, food):
    pass
    
  def walk(self):
    pass
  def speak(self):
    pass

//   def print(Self):
// print(f"age:{self.age} name:{self.name}")

//   def log(self, logger):
//     logger.log(f"age:{self.age} name:{self.name}")
//       logger.log(datetime.now())
  def repr(self):
      return f"age:{self.age} name:{self.name}"

  kitty = Cat() 클라이언트 코드에서
  print(kitty.repr()) 상태리턴해서 프린트하거나
    logger.log(kitty.repr()) 아니면 로그남기거나하게끔 사용하게해라.
        
 고양이가 먹고싸는건 다하는데 로그남기거나 뭘 출력하거나하진 않으니 이런 기능은 추가하면 안됨.

        다른방식으로 구현.
        고양이 상태나타내는 represent 

        클라이언트 코드에서는 repr 함수가져다가 쓰게금.
```


// open-closed 원칙
확장에 대해서는 오픈, 수정에 대해서는 폐쇄 무슨소리?
  행위에 대한 문제들임.

open-closed 위배

  // class Animal:
  //    def __init_(self, a_type):
  //       self.a_type = a_type

          
  //  def hey(animal:Animal):
  //    if animal.a_type = 'Cat';
  //       print('meow')
  //   elif animal.a_type = 'Cat';
  //       print('bark')
  //   else:
  //      raise Error('wrong a_type')


// kitty = Animal('Cat')
// bingo = Animal('Dog')
// cow = Animal('Cow')
// sheep = Animal('Sheep')
//   hey(kitty)
//   hey(bingo)
      
    
  하면
  meow
  bark라고 대답해줌
  누가 cow나 sheep을 추가해달라고함.
    당연히 cow sheep 넣으면 실행 안됨.

 name  error is not defined

   왼쪽에는 동물범주안에 개고양이만있는데 확장하려하는데
   hey 함수까지 수정해줘야됨. 이러한 코드는 openclosed에 위배된다.

   abstract, interface 클래스 사용한다.


  
class Animal:
  def speak(self):
    pass

class Cat(Animal):
    def speak(self):
       print("meow")
         
class Dog(Animal):
   def speak(self):
      print("bark")

그냥 이거 class 하나 넣으면되
class Sheep(Animal):
      def speak(self):
       print("meh")
        
def hey(anima:Animal):
 animal.speak()

   kitty = Cat()
   bingo = Dog()

    hey(kitty)
    hey(bingo)
   
   
   리스코프 치환원칙?
    type t가있고 그아래에

      서브 type들이 존재. s1,s2,s3로바꿔도 생각했던대로 동작해야된다.

      고양이 class cat

      서브타입들이 black cat, gil cat있더라도

      cat이 black cat으로 치환해도 동작이 제대로 돌아간다.

      class Cat:
          def speak(self):
            print("meow")

      class BlackCat(Cat):
        def speak(self):
         print("black meow")

      def speak(cat:Cat):
         cat.speak()

cat = Cat()
speak(cat)

  cat = BlackCat()
speak(cat)
  black meow

    class Fish(Cat):
  def speak(self):
    raise Exception("Fish cannot speak")

cat = Fish()
speak(cat)

  Exception  Traceback (most recent call last)
<ipython-input-3-6a75a05f3205> in <module>()
      4 
      5 cat = Fish()
----> 6 speak(cat)

1 frames
<ipython-input-3-6a75a05f3205> in speak(self)
      1 class Fish(Cat):
      2   def speak(self):
----> 3     raise Exception("Fish cannot speak")
      4 
      5 cat = Fish()

Exception: Fish cannot speak

  만약 고양이를 fish로 대체하면 실행이 일어나지 않는다.
  fish cannot speak

  고양이 밑에 생선을 넣으면 안됨


Interface segregation 인터페이스 분리원칙.
  
  큰 인터페이스를 작은 인터페이스로 분리해버려라.

interface ICar

  {
    void drive():
    void turnLeft():
    void turnRight():
    
  }

class Genesis.ICar

  {
    void drive():
    void turnLeft():
    void turnRight():
    
  }



Avante nocopeCar = new Avante();
Genesis secondCar = new Genesis();

Icar
  안에 이것저것있고

  genesis, avante
도 인터페이스

  인터페이스를 너무 큰개념으로 가져가지마라

  수륙양용차 이런거. 

interface ICarBoat

  {
     void drive():
    void turnLeft():
    void turnRight():
    

    void steer();
    void steerLeft();
    void steerRight();
  }

class Gensis: ICarBoat

  {
     void drive():
    void turnLeft():
    void turnRight():
    //  void steer();
    // void steerLeft();
    // void steerRight();
    제네시스엔 이런거 필요없잖아 
    보트와 자동차로써 의 기능 두가지를 가지면 너무많다.
  }

ICar
IBoat 이렇게 만들어라.
  ```
  dependency inversion principle
  의존성 역전 원칙

  zoo는 high level


  cat, dog , sheep cow등은 low level
코드 수정 관리가 어려워짐

zoo -> animal <- cat,dog 이렇게.

 class Zoo:

   def __init__(self):
      self.animals = []

        
  def addAnimal(self, animal:Animal):
    self.animals.append(animal)

  def speakAll(self):
     for animal in self.animals:
         animal.speak()

zoo = Zoo()
zoo.addAnimal(Cat())
zoo.addAnimal(Dog())
zoo.speakAll()
  ```
