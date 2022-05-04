의존성 관리
이전 예제에서 dependencies로 사용될 외부 라이브러리에 대한 의존성을 설정하였다. 여기서는 Gradle의 의존성 설정 방법에 대해서 자세히 알아보자.

아래와 같이 repositories를 사용해서 의존성을 가져올 주소를 설정한다. dependencies를 사용해서 설정된 Repository에서 가져올 아티팩트를 설정한다.

```
allprojects {
    repositories {
        mavenCentral()
        jcenter()
        maven {
            url "http://repo.mycompany.com/maven2"
        }
        ivy {
            url "../local-repo"
        }
    }
    
    dependencies {
        // 로컬 jar 파일의 의존성 설정
        compile fileTree(dir: 'libs', include: '*.jar')
        // 로컬 프로젝트간 의존성 설정
        compile project(':shared')
        // 컴파일 타임에 의존성을 받아옴 
        compile 'com.google.guava', name: 'guava:23.0'
        // 테스트시만 의존성을 받아옴 
        // 마이너 버전을 '+'로 설정해서 항상 4점대 최신 버전을 사용
        testCompile group: 'junit', name: 'junit', version: '4.+'
        // 컴파일할때는 사용하고, 아티팩트를 만들때는 포함하지 않음
        compileOnly 'org.projectlombok:lombok:1.16.18'
        // 실행할때 의존성을 받아옴(기본적으로 컴파일을 모두 포함)
        runtime('org.hibernate:hibernate:3.0.5')
    }
}

```



uploadArchives를 사용해서 생성된 아티팩트를 배포하기 위한 주소를 설정한다.

```

uploadArchives {
    repositories {
        ivy {
            credentials {
                username "username"
                password "pw"
            }
            url "http://repo.mycompany.com"
        }
    }
}

```

