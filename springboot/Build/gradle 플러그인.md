java와 kotlin을 빌드하기 위한 플러그인만 적용



->  checkstyle  소스코드 정적 분석 툴. 

```
apply plugin: 'checkstyle'
checkstyle {
    toolVersion = "7.6"
    configFile = rootProject.file('codequality/checkstyle.xml') as File
    ignoreFailures = true
    showViolations = true
}
```
apply plugin: 'checkstyle'

을 하면 해당 플러그인에 포함된 기능들을 사용할 수 가 있다. 이 플러그인에는 checkstyle이라는 함수가 있고, 함수에 {} 클로저를 넘겨서 설정한다.


toolVersion이라는 속성으로 checkstyle의 버전을 설정하고, 

configFile로 적용할 룰을 설정할 수 있는 파일을 연결한다

 ignoreFailures를 true로 설정하면 빌드할때 소스코드가 룰에 어긋나도 빌드 에러를 발생 시키지 않는다. 
 
 Warning만 보여준다. showViolations은 빌드시, 위반사항에 대한 내용을 콘솔에 보여줄지를 결정한다.
 
 
 covertura는 테스트 커버리지 도구이다.
 
 
 license-gradle-plugin
