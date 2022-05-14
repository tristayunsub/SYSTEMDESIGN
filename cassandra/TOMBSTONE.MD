카산드라는 NULL값을 가지는 칼럼이없다.


대신 데이터를 사용할때 Null을 활용 가능하다. update users set location = null where username = 'alice'는 delete location from users where

user = 'alice'와 같다. 위와 같은 Null을 활용한 삭제는 여러 업데이트를 동시에 할때 유용하다.




 update users set email='kim@naver.com', location=Null where username='carol'



카산드라의 delete는 tomebstone이라는 삭제를 나타내는 flag로 칼럼을 업데이트하는걸 의미.












