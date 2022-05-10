string에 따라서 동물을 만듬. 

런타임중에 어떤 동물을 만들건지 cat을 치면 meow

make speak 함수에서 animal 인터페이스 가 함수로 들어가서 그 오브젝트가 고양이면 meow이렇게

make speak 함수 변경할필요없이 확장에 대해 오픈

```
class Animal:
  def speak(self):
    pass

class Cat(Animal):
  def speak(self):
    print("meow")

class Lion(Animal):
  def speak(self):
    print("roar")

def makeSpeak(animal:Animal):
  animal.speak()
  ```
  
 ```
  def createAnimal(input_str:str)->Animal:
  if input_str == "cat":
    return Cat()
  elif input_str == "lion":
    return Lion()
 ```
    
```
    input_str = input('choose animal: ')

animal = createAnimal(input_str)
makeSpeak(animal)

```

  
  
