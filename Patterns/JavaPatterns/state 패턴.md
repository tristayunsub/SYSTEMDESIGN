

FSM ?

상태를 가지는거 신호등? 녹색 주황색 빨간색 하나의 상태만 가지는 기계

그 상태는 녹색에서 주황색으로 또 빨간색으로 변화하죠. 무슨색이냐고 물어보면? 녹색입니다등 리스폰스 준다.

프로그래밍 태스크 새로운 클래스 만들어달라 그럼 디자인 코딩 테스트 리뷰단계 DONE 단계

디자인, 코딩-> 테스트- > 리뷰 -> TASK 완료

중간중간 안되면 이전으로.

```
class TrafficLight:
 def __init__(self):
   self.state = 'green' //기본스테이트
   
   #enum
   def setState(self, state:str): //현재 상태 설정할수있는 setSTtae 빨간색, 초록색 두개만가지고있으니.
         self.state =state
         
   def speak(self):
     if self.state = 'green':
      print('green ligth')
     elif self.state = 'red':
      print('red ligth')
     
    def wait(self):
     print('wait.. the light changed')
      if self.state == 'green':
       self.state = 'red'
      elif slef.state == 'red':
        slef.state
 ```
 
 ```
 t_light = TrafficLight() 라는 오브젝트 생성
 t_light.speak()
 
 이러면 green light 나오고
 
 여기서 
 t.light.wait() 함수 호출하면 
 t.light.speak()
 wait... the light changed
 red light가 뜸
 ```
 
 근데 현재 코드로 는 복잡성을 극복하기힘듬
 
 
 traffic light안엔 state 오브젝트가 존재. 이 스테이트 인터페이스가 speak wait 함수, red, green 
 
 스테이트 패턴
 
 ```
class TrafficLight:
  def __init__(self):
    self.state = GreenLight()

  def setState(self,state):
    self.state = state
  
  def speak(self):
    self.state.status()

  def wait(self):
    self.state.changeLight(self)
        
 ```       

```
class State:
  def status(self):
    pass
  def changeLight(self,traffic_light:TrafficLight):
    pass


class GreenLight(State):
  def status(self):
    print('green light')

  def changeLight(self,traffic_light:TrafficLight):
    print('wait.. the light changed')
    traffic_light.setState(RedLight())
  

class RedLight(State):
  def status(self):
    print('red light')

  def changeLight(self,traffic_light:TrafficLight):
    print('wait.. the light changed')
    traffic_light.setState(GreenLight())
  

```
