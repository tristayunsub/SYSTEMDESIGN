
https://blog.naver.com/zonghoooon/222118359120

카테고리 이론?

구체적인 예가 들어있어야됨 머리에.. 

반복문과 조건문을 리팩토링할수있따. co carteisan closed category.

메타 프로그래밍?

카테고리 이론은 그렇게 유용하진 않음. 그러나 언어를 빌려 코드를 깔끔하게정리가능.

정리할수없는 코드를 섦

algebraic data type?

recursion scheme

recursive structure?

ternary operator


catamorphism?

    recursion schemes
    
    explicit function call stacks and 
    
    scheme all the program and data is regarded as experessions. expressions are atom and brackets 
    
    and parenthesized sequence
    
    expression = atom | '('{expressions}')'
    
    atom = number | string | symbol | char | boolean
    
    scheme calculation rules
    
    atom literal is calcuated as its value
    * 2+ 3 = 5
    
    2. 기호는 다른 키워드와 달리 식별자 또는 변수로 취급.
    
    특별한 형식?
    
    scheme 의 모든 구조는 표현식. 실행을 제어하는 표현식도필요.
    
    lazy evaluation
    
    조건문 conditional expression.
    
    if else 는 if로, if elseif는 cond로
    
    if문
    
    (if(=a 0) 1(+a1))
    
    *Scheme에서의 cond문

(cond ((= a 0) 0 ) ((= a 1) 1) (else 2)))

-> a=0이면 0, a=1이면 1 아니면 2를 반환한다.


lambda(X)(*xx)

->x의 제곱을 구하는 함수 정의

let((Square(lambda(x)(*xx))(square10)

define: let의 겨우와 달리 최상위 환경? top level environment에서 변수나 함수 정의 define 사용

​

*Scheme에서 define

(define square( lambda (x) (* x x)))

-> x의 제곱을 구하는 square함수를 정의합니다.

scheme에서 동적 유형 검사.

dynamic type chekcing

동적 유형 검사: 변수가 아닌 값이 자료형을가지고, 값의 자료형은 런타임때 실제로 필요할때 까지 확인되지 않음.

만약 타입이 잘못되면 중단됨.

tail recursion과 non tail recursion

함수를 호출할때 매개변수, 리턴값 돌아갈위치를 스택에 저장

이게 심해지면 스택오버플로. 이걸 해결하기위해 꼬리재귀로 모든 재귀의 단계를 마지막으로 미룬다?



list 데이터 구조

car: 리스트의 헤드에접근
cdr: 리스트 꼬리접근
cons:리스트에 헤드 추가

LIST A 가 (! 2 3 4 5)
(car A)

1

(cdr A)

(2345) ? 머지?

(cons 2 A)

(2 1 2 3 4 5

