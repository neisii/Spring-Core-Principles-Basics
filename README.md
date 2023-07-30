# 비즈니스 요구사항과 설계
* 회원
* 주문/할인
* 인터페이스를 만들고 구현체를 언제든지 갈아끼울 수 있도록 설계한다.
* 순수 자바로 구현한다.



# 도메인 설계
* 회원 도메인
  * 요구사항
        * 가입/조회
        * 회원 등급 일반/VIP
        * 자체 DB 구축 또는 외부 시스템 연동할 지 미정
  * 클라이언트 - 회원 서비스 - 회원 저장소(메모리, DB, 외부 연동)
  * <interface>MemberService MemberServiceImpl

# BeanFactory와 ApplicationContext
* BeanFactory `<` ApplicationContext