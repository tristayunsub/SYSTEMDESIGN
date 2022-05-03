https://jaimemin.tistory.com/1835?category=1060172


타임 리프

서버사이드렌더링(SSR), NATURAL TEMPLATE


1. 서버 사이드 렌더링?
HTML 최종결과를 서버에서 만들어 웹브라우저에 전달

정적인 화면에서 자주 사용

JSP, 타임리프가 대표적인 예시, 현재는 타임리프를 주로 사용

SSR을 사용하더라도, 자바스크립트를 통해 일부 화면을 동적으로 변ㄱㅇ 가능.(



![다운로드](https://user-images.githubusercontent.com/75001605/166585074-24de6023-d2bd-4b90-b45a-694ce47c7732.jpg)


2. natural template




타임리프는 순수 HTML을 최대한 유지하기 대문에, 웹브라우저에서 파일을 직접 열어도 HTML 파일 내용 확인가능.



(퍼블리셔로부터 파일을 전달 받을때 리뷰하기 유용함)


서버를 통해 뷰템플릿을 거치면 동적으로 변경된 결과 확인



한마디로 타임리프 파일을 열었을때, 동적으로 결과가 렌더링 되지는 않지만. HTML 마크업 결과가 어떻게 구성되는지 바로 확인 가능


순수 HTML을 그대로 유지하면서 View Template도 사용할 수 있는 타임리프의 특징을 natural template이라고 한다.


**타임리프에서 기본적으로 제공하는 객체**

```
${#request}
${#response}
${#session}
${#servletContext}
${#locale}
 ```


param: HTTP 요청 쿼리 파라미터 접근

url이 http://localhost:8080/example? age=20 일 때 param.age로 접근 가능

session: HTTP session 접근

@: 스프링 빈 접근




**타임리프 템플릿 레이아웃**


공통적으로 사용하는 코드 블록들을 레이아웃에 넘겨 사용

공통적으로 사용하는 라이브러리 들을 모아놓은 헤더 파일을 만들고 해당 파일을 템플릿으로 사용

또한, 회사명이나 저작자를 명시하는 footer도 템플릿으로도 자주 사용



th:replace 기능을 통해 ~{::title}에 title을 넘기고, ~{::link}에 link를 넘김 (th:block이므로 여러 link 넘기기 가능)

출처: https://jaimemin.tistory.com/1835?category=1060172 [꾸준함]
template/layout/baseExample 레이아웃을 활용하여 template/layout/layoutExample html을 렌더링 하는 예시

간단 예시

LayoutExample GetMapping

```
@GetMapping("/layoutExample") 
public String layoutExample() 
{ return "template/layout/layoutExample"; }
```


template/layout/layoutExample 렌더링 전

```
<html xmlns:th="http://www.thymeleaf.org"> 
  <head th:replace="template/layout/baseExample :: common_header(~{::title},~{::link})"> 
    <title>공통 파일 예시</title> <link rel="stylesheet" th:href="@{/css/example.css}"> </head> <body> 예제 </body> </html>
```


template/layout/baseExample

```
<html xmlns:th="http://www.thymeleaf.org"> 
<head th:fragment="common_header(title, links)"> 
<title th:replace="${title}">대체될 타이틀</title> 

<!-- 공통으로 사용하는 css --> 
<link rel="stylesheet" th:href="@{/css/bootstrap.min.css}"> 
<link rel="stylesheet" th:href="@{/themes/smoothness/jquery-ui.css}"> 

<!-- 공통으로 사용하는 js --> <script type="text/javascript" th:src="@{example.js}"></script> 

<!-- 추가될 link들 --> <th:block th:replace="${links}" /> </head> </html>

```




