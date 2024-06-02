# Performance Ticket Reservation Market 
  코딩 공부를 시작하고 첫 프로젝트 이며, 
  
  자바를 활용해 공연 예약,예매 하는 웹사이트를 생각하며 만들었습니다.

## 목차
  - [개요](#개요)
  - [설명](#설명)
  - [참고자료](#참고자료)


## 개요
 - 프로젝트 이름: performance ticket market
 - 프로젝트 지속기간: 2024.04.11~2024.05.25
 - 개발 언어: Java



## 설명
  interpark ticket 웹 사이트를 모티브하여 만들었으며, 데이터를 파일로 관리하는 것부터
  
  DB로 관리하는 것까지 해보았습니다. 공연과 회원 정보를 관리하는 admin메뉴와 회원메뉴가 있고,
  
  회원은 공연에 특정 좌석을 예매 할 수 있습니다.
  
  소켓통신을 활용해 고객센터를 약소하게 만들었으며 1대1문의를 진행 할 수 있으며 
  
  지난 문의 내역과 답변을 볼 수 있습니다.

----
1. 데이터를 파일로 관리하는 버전
    한달간 배운 자바만을 활용해 만들었으며,

    ObjectStream으로 데이터를 파일과 주고받아 활용했습니다.
   
  - [파일버전](https://github.com/ckehf223/ticket-project/releases/tag/fileVersion)

2. 데이터를 DB 연결하여 PROCEDURE,TRIGGER를 사용해 관리한 버전
    공부를 하면서 배운 DB를 활용하여 데이터를 관리하였으며, 프로시저와 트리거를 활용했습니다.

  - [DB연결버전](https://github.com/ckehf223/ticket-project/releases/tag/DBVersion)


## 참고자료
  실행되는 모든 console화면
  
------
### startMenu
- 첫 메뉴 로그인/회원가입/ID,PW찾기/종료
<img src="./images/start 메뉴 .png" alt="실행시 처음 보여지는 메뉴" width="300">

### adminMenu
- 관리자 메뉴 회원,공연 관리

<img src="./images/관리자 메뉴.png" alt="관리자 메뉴" width="300">

### customerMenu
- 고객 메뉴 내정보,공연정보 확인/공연 예매,취소,결제,고객센터 문의

<img src="./images/회원 메뉴.png" alt="회원 메뉴" width="300">

### reservation performance
- 공연 예매시 좌석 선택

<img src="./images/공연 예매 1.png" alt="공연 예매 좌석 선택 화면" width="300">

- 좌석 선택 후

<img src="./images/공연 예매 2.png" alt="좌석 선택 후 화면" width="300">

### service center
- 고객센터 1대1 문의 하기 회원

<img src="./images/고객센터 1대1문의 클라이언트.png" alt="1대1 문의 회원" width="300">

-고객센터 1대1 문의 하기 서버

<img src="./images/고객센터 1대1문의 서버.png" alt="1대1 문의 서버" width="300">

-고객센터 내 문의 내역

<img src="./images/고객센터 내 문의내역.png" alt="내 문의내역" width="300">
