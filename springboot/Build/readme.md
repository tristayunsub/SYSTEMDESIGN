https://www.youtube.com/watch?v=Adegb7rFGag&list=PL1DJtS1Hv1PiGXmgruP1_gM2TSvQiOsFL&ab_channel=%ED%86%A0%EC%8A%A4


토스 슬래시 21 build

코드모듈화, gradle build 설정. properties 설정 png crunch disable

tds/core 분리


gradle 빌드 설정

빌드 속도.. 3분에서 5분 모듈화 build script

build variant. 보안성 강화. 빌드시에는 항상 cpu 사용량 100프로

빌드 명령어 mainframer?

mainramer는 rsync와 ssh 로 코드를 업로드하고

gradle 빌드 명령어 실행. 빌드 결과물을 rsync로 다시 컴퓨터로 동기화


동기화 진행상태의 출려이아노딤. info 옵션 progress2 

빌드결과를 싱크하는 문제.

external tool 빌드전에 실행되어야하는 external tooldmf build variant별로 바꿔야함

gradle cache가 꼬이는 문제.

빌드와 실행 


1. 소스 코드 파일을 컴퓨터에서 실행할 수 있는 독립 sw 가공물로 = artifact

변환시키는 과정. 하나의 파일로 만들어낸다.

굉장히 복잡한 단계

1)소스 코드를 컴파일 -> 테스트코드 컴파일 -> 테스트 실행 -> 테스트코드 리포트 작성 -> 기타 추가 설정한 작업 진행 -> 패키징 -> artifact를 만들어낸다

자동 테스트 해주는 코드를 추가 작성 연습해야함. 이것도 빌드과정에 포함된다.

패키징 jre에 포함된것. 이외 오픈소스를 만들고싶을때. 스프링부트 이런것도 다른거 가져다 쓰는것

빌드를 수동으로 무조건 실수가 나온다. 빌드 툴이라는게 존재한다.


ant, maven gradle

ant: xml사용. 간단하고. 근데 복잡한거 하려면 빌드 스크립트가 장황해짐.

gradle -> groovy 언어를 사용. 외부 라이브러리 관리, 유연한 빌드 스크립트

jvm은 인기가 많음. 


https://k39335.tistory.com/75

gradle을 위한 groovy 문법

https://medium.com/@goinhacker/%EC%9A%B4%EC%98%81-%EC%9E%90%EB%8F%99%ED%99%94-1-%EB%B9%8C%EB%93%9C-%EC%9E%90%EB%8F%99%ED%99%94-by-gradle-7630c0993d09


gradle 성능향상을 위한 기능제공

https://kotlinworld.com/321

동작원리

속도
Gradle은 성능 향상을 위해서 다양한 기능을 제공한다. 빌드 시스템에서 빌드 속도는 매우 중요한 부분이다. 

gradle init

project /

```
 
project/
    gradlew
    gradlew.bat
    gradle/wrapper/
        gradle-wrapper.jar
        gradle-wrapper.properties
    build.gradle
    settings.gradle
``` 
    
    gradle wrapper란. 이미 존재하는 프로젝트를 새로운 환경에서 설치할때 별도의 설치나 설정과정 없이 곧바로 빌드하기 위함
    
    항상 wrapper 사용 권장.
    
    gradlew 파일은 유닉스용 실행 스크립트. gradle로 컴파일이나 빌드할때
    
    gradle build
    
    이 경우 Java나 Gradle이 설치되어 있어야 하고, 새로받은 프로젝트의 Gradle 버전과 로컬에 설치된 Gradle 버전이 호환되지 않으면 문제가 발생할 수 있다.
    
    따라서 Wrapper를 사용하면 아래와 같이 실행한다.

```
> ./gradlew build
```

**gradlew.bat** 파일은 윈도우용 실행 배치 스크립트이다. 윈도우에서 실행 가능.



**gradle/wrapper/gradle-wrapper.jar ** 파일은 Wrapper 파일이다. 

gradlew나 gradlew.bat 파일이 프로젝트 내에 설치하는 이 파일을 사용하여 gradle task를 실행



gradle/wrapper/gradle-wrapper.properties 파일은 Gradle Wrapper 설정 파일이다. 

이 파일의 wrapper 버전 등을 변경하면 task 실행시, 자동으로 새로운 Wrapper 파일을 로컬 캐시에 다운로드 받는다.
    

build.gradle 파일은 의존성이나 플러그인 설정을 위한 스크립트 파일

settings.gradle 파일은 프로젝트의 구성정보를 기록하는 파일. 어떤 하위 프로젝트들이 어떤 관계로 구성되어 있는지 기술.


```
rootProject.name = 'algorithm'

include 'java'
include 'kotlin'

```

rootProject.name은 최상위 프로젝트의 이름이다. 기본적으로는 프로젝트 폴더명으로 만들어진다.

algorithm 프로젝트의 하위프로젝트로 java, kotlin를 포함시켰다. 여기서 만약 하위 프로젝트의 하위 프로젝트를 만드려면 include 'java::sub'


```
mkdir java
mkdir kotlin
```

그리고 각각의 하위 프로젝트의 기본 폴더트리
```
>mkdir -p java/src/main/java
>mkdir -p kotlin/src/main/kotlin

```
