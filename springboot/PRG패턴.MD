POST, REDIRECT , GET PATERN
https://jaimemin.tistory.com/1825?category=1060172




상황 설명

아래의 그림과 같이 회원 등록 폼 페이지를 호출하는 컨트롤러

URL이 동일하다고 가정.

회원을 폼에 등록하면 해당 폼은 회원 등록하는 컨트롤러를 호출. 해당 컨트롤러는 회원 개인정보 View를 호출

이때, 회원 개인정보 페이지의 URL은 여전히 회원 등록하는 컨트롤러 URL이므로 새로고침을 할 때마다 회원을 신규 등록하는 문제점이 발생 

(회원을 구분 짓는 Primary Key가 BigInteger 타입이고 AutoIncrement라는 가정)

![다운로드 (2)](https://user-images.githubusercontent.com/75001605/166594083-bf8a7d34-3fc5-4940-975d-f9f4220c196e.jPG)

문제 해결방법

앞선 상황에서 문제점은 회원 등록하는 URL에서 변하지 않은 상태에서 새로고침 했기 때문에 발생


즉, URL이 바뀌어야 문제가 해결


기존의 회원등록 컨트롤러는 forward 방식으로 회원 개인정보 View를 호출했으므로 URL 변동 X


따라서, forward 방식이 아닌 아래와 같이 redirect 방식으로 View를 호출해야함


![다운로드 (5)](https://user-images.githubusercontent.com/75001605/166594749-6fc880f1-b278-4277-8ffb-a57edb095c09.png)

RedirectAttributes

URL 인코딩 및 @PathVariable, 쿼리 파라미터 처리해주는 객체

추가된 속성이 pathVariable에 포함되면 PathVariable에 바인딩해주고 나머지는 쿼리 파라미터로 처리

```
@PostMapping("/register")
public String registerMember(Member member, RedirectAttributes redirectAttributes) {
  redirectAttributes.addAttribute("memberId", member.getId());
  redirectAttributes.addAttribute("successYn", "Y");
  
  return "redirect:/member/{memberId}";
}

// memberId의 경우 pathVariable이므로 PathVariable에 바인딩, successYn의 경우 쿼리파라미터로 처리
// memberId가 2라고 가정한다면 전체적인 url은 /member/2?successYn=Y

```
