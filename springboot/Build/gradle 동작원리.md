https://kotlinworld.com/321

build gradle이란 무엇인고?

build grade은 파일 자체가 project(오브젝트)로, project 오브젝트는 project 인터페이스를 구현하는 구현체입니다. project 오브젝트는 project 단위에서

필요한 작업을 수행하기 위해 모든 메서드와 프로퍼티를 모아둔 슈퍼 객체 ㄷㄷ

우리가 build gradle에 작성하는 수많은 코드들은 모두 project 오브젝트의 프로퍼티와 메소드가 되며

project 오브젝트는 프로젝트 이름부터 변수, 메서드를 모두 포함


plugin, repository, dependencies, application들이 존재. 

빌드하면 build task는 이 메서드 들을 수행

{}로 감싸여진 부분은 메서드의 인자로 받아지는 groovy의 클로저. groovy의 클로저는 java나 kotlin의 람다와 같다


build.gradle의 프로퍼티. project 개게를 위한 프로퍼티 정의

project.[프로퍼티명] = [값]


혹은 생략
[프로퍼티명] = [값]

예를들어 project 객체의 group을 재정의하고 싶다면 다음과 같이 쓴다

group으로 쓰던, project.group으로 쓰던 같은 프로퍼티에 접근


```
group = 'com.example'
project.group = "com.kotlinworld"

repositories {
    println group //출력
    mavenCentral()
    }
    
    하지만 이렇게 지정하는 것은 project 객체에 미리 정의된 프로퍼티만 정의하는 것이 가능.
    
    
    커스텀 메서드.
    
    groovy 람다식 closure과 gradle의 ext
    
    ext.[메서드명] = {param1, param2 -> [메서드바디] }
    
    
    예를들어 blogName 출력하는 커스텀 메서드 
    
    project.ext.getBlogName{
    return project.blogName
    }
    
   
