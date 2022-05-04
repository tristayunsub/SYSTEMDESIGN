gradle 스크립트?


groovy를 이용해서 만든 DSL 모든 GRADLE 스크립트는 project와 tasks로 구성

모든 Gradle 빌드는 하나 이상의 projects로 구성된다. 

그리고 각 project는 하나 이상의 task들로 구성되어 있다. 이 task는 어떤 클래스를 컴파일하거나 JAR를 생성하거나 javadoc을 만드는 작업들을 의미한다.

```
task hello {
    doLast {
        println 'Hello world!'
    }
}
```

간단한 hello task 그리고 gradle -q hello로 해당 task 만 실행해 볼 수도 있음

