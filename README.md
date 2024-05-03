# 🐻 단통 (단국인의 소통)



## 👫 팀원 소개

### Backend

### 🙋🏻‍♀️ **김세은**

- 백엔드 및 데이터베이스 설계
- 도메인 설계
- 데이터베이스 운용
- 스프링 jpa, jwt, rest API 활용 주요 기능 구현
- dbsql 등 활용 쿼리 성능 최적화

### 🙇🏻‍♂️ 최재민

- 프로젝트 매니저
- 도메인 설계
- Devops 구축 및 운영
- 스프링 시큐리티 관련 로그인 기능
- mail sender, xslx 파일 변환 등 주요 기능
- 의존성 관리
- 테스트 셋 관리, 테스트 자동화(end to end test)

### Frontend

### 💁🏻‍♀️ 이가은

- 프론트엔드 및 디자인 설계
- 웹 성능 최적화
    - Light house를 통한 화면 분석
    - React Query 사용을 통한 데이터 실시간 Fetching, 동기화
- PWA 제작
- UX/UI 개선
- ReactJS 프레임워크를 통하여 웹화면의 컴포넌트를 설계
- 백엔드와 유저에서 효율적인 서버 데이터의 전달 도움



## **📌** 프로젝트 목표

- ‘단통’의 목표는 학생회와 학생들 간의 소통을 강화하는 플랫폼이다. 이 플랫폼에서는 학생회가 설문을 생성하고 행사 관련 게시물을 업로드하고, 일반 학생들이 행사 정보를 확인하고 설문에 응답할 수 있는 기능을 제공한다. 또한, 학생회는 설문 결과를 확인하고 엑셀로 다운로드할 수 있다. 프로젝트의 성공은 설문 생성, 게시물 업로드, 설문 응답의 기능 구현, 사용자 피드백 수집 및 개선 작업 완료로 측정할 예정이다. 팀원 개개인의 기술적인 경험을 바탕으로, 설문 생성 및 관리, 게시물 관리, 사용자 인터페이스, 데이터 처리와 결과 다운로드 기능을 포함하는 플랫폼 개발이 가능하다. 이 프로젝트는 학생회 활동의 효율성을 높이고, 학생회 운영의 불편함을 해소하고 일반 학생들의 행사 참여를 촉진하는 데 큰 관련성이 있다.



## ✅ 시스템 구현범위

- 단국대학교 이메일 인증을 통한 회원가입 및 로그인
- 설문 생성/마감일 설정 및 설문 답변 수정/삭제, 설문 확인, 설문 결과 엑셀로 추출하기
- 게시글 작성 및 이미지 업로드, 게시글 수정/삭제, 게시글 확인, 관심 게시글 설정
- 설문 완료 및 행사 게시글 업로드, 행사 마감 임박 알림, 알림 켜기/끄기 설정
- 친구 신청 보내기, 친구 수락하기, 친구목록 조회
- 내정보(학적사항) 확인 및 수정/저장
- 캘린더로 학생회 행사 일정 확인
- 홈 화면 구성: 행사 바로가기 링크, 행사 게시글 썸네일 리스트업, 내가 신청했던 행사 리스트업 및 내용/일정 확인



## 🎨 Rich Picture

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbSehJ0%2FbtsGunmfJf1%2FfPc4tnUhwIYjlael1PfUWK%2Fimg.png)



## 🛠️ 사용 기술 검토하기

- 스프링부트와 Mysql(RDB)를 같이 사용하는 이유
    - 객체지향언어의 객체와 RDB가 매칭하기 편리하다
    - ORM(JPA)을 통해 쿼리 작성없이 개발자 친화적으로 개발 가능하다.
    - 객체 중심의 개발을 통해 개발의 생산성을 높일 수 있다.
    - 스프링부트 프로젝트 특성상 프로그램의 확장 및 변경에 쉽게 반응하여 개발을 할 수 있다.
- PWA 사용 이유
    - 사용자가 앱을 설치할 때, 실제로는 웹사이트의 바로 가기를 생성하는 것이므로 전체 앱을 다운로드할 필요가 없다.
    - 업데이트 및 버그 수정 내용을 빠르게 반영할 수 있다.
    - 웹 페이지 임에도 모바일에 최적화되어 있기 때문에 접근성이 높다.
    - 앱 개발 기술 스택을 익히지 않아도 앱과 유사한 효과(푸시 알림, 부드러운 애니메이션과 화면 전환, 반응성 등)를 낼 수 있다.
- AWS 사용 서비스
    - EC2
        - 물리 서버의 비용 문제로 인해 사용량 만큼만 비용 지불을 하면 되는 EC2를 사용
    - S3
        - EC2 인스턴스 내에 파일 저장 시 인스턴스 용량과 관련된 문제가 발생하기 때문에 파일 저장을 외부에서 진행 할 수 있는 AWS S3 서비스를 사용
        - 파일 저장에 대한 내구성이 보장되어있고 용량에 대한 확장성이 매우 뛰어나 물리 파일 서버를 구비하는것에 비해 비용 절감이 가능하다
    - RDS
    - ELB(서비스 확장 시)
        - 서비스 확장 시 인스턴스 과부하로 인한 예상치 못한 종료를 방지하기 위해 사용하여 인스턴스 간의 부하를 조절한다
- 사용자 이메일 인증 토큰 정보의 경우 오랫동안 저장될 필요도 없으며 보안 상 짧은 유효기간으로 설정하는 것이 권장된다. 또한 복잡한 구조의 데이터로 저장되지 않고 사용자-토큰처럼 key-value의 구조로 저장되면 되기 때문에 해당 요구사항에 가장 적합한 Redis를 사용하게 되었다. Redis는 휘발성 인메모리 데이터베이스로서 일정 시간 이후 사라지도록 만료 시간을 설정할 수 있으며 NOSQL의 특성인 key-value 구조이기 때문에 채택하였다.
- 엑셀 파일 생성 및 수정
    - Apache poi 라이브러리 사용
        - 아파치 재단에서 만든 문서 생성 및 수정 라이브러리로 오픈소스로 제공
        - 상용화 된 마이크로소프트 오피스 파일 편집 라이브러리 중 가장 다양하고 안정적인 기능을 제공하기 때문에 선정하였다.
- mysql의 채택 이유: 객체지향 패러다임에 어울리는 관계형데이터 베이스 중 Mysql을 선택한 이유는 다음과 같다. mysql은 팀원들이 전공 과목으로도 접해본 경험이 많고, 데이터베이스 순위에서 여전히 2위를 지키고 있는 등 점유율 면에서도 높은 모습을 보여주며, 높은 점유율에 따른 많은 레퍼런스가 존재하기 때문에 채택하였다.
- 설문 생성 및 설문 결과를 엑셀로 저장하는 과정이 몇몇 팀원에게는 처음 접해보는 기술이기 때문에 어려움이 있을 것으로 예상된다. 이를 해결하기 위해 내부 스터디를 통한 문제 해결을 진행 할 예정이다.



## ⚠️ **Constraints**

- 짧은 개발기간으로 인한 기술 고도화 부재
    - 설문 생성 중간 저장 기능
        - 팀 내 기술적 이해도에 대한 한계와 짧은 개발 기간으로 인해 후순위 혹은 추후 개발 계획에 포함 될 수 밖에 없음
- 학교 시스템 혹은 데이터베이스와의 연계의 어려움
    - 학교 데이터베이스의 정보를 받아올 수 있는 API를 제공 받을 수 있도록 준비하고 있으나 기간이 오래걸리며 제공 여부에 대한 확실성이 부족하여 개발 계획에 포함 될 수 없는 어려움
- 엑셀 파일 생성의 기술적 난이도에 대한 어려움
    - 마이크로소프트 오피스 파일을 자바로 다루는 것의 높은 난이도와 러닝커브로 인한 어려움
    - 팀 내 엑셀 파일 생성 기능 구현 유경험자의 팀 내 스터디 및 기술 공유로 해결 예정



## 📦 Domain Model

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FpUCfp%2FbtsGu6Ekzs5%2FGbMN4UlSOR8agmzGKLzds1%2Fimg.png)

## 📖 ERD

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FnGdLc%2FbtsGq1LNLX0%2FsQOFzSy9FDoK2cv4aIvjV0%2Fimg.png)



## 🧅 Layered Architecture

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbp9F89%2FbtsGsXaFi71%2FGTVTmzifRI1btsmA9kuvz0%2Fimg.png)

## ⚙️ System Architecture

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcF65NS%2FbtsGtTZ5i90%2FomOngR4vnkJ44Vh8zhE5qK%2Fimg.png)

**VPC**: 자체 데이터 센터(단통의 경우 AWS)에서 운영하는 기존 네트워크와 아주 유사한 가상 네트워크이다.

**Public Subnet**: Gateway, ELB, 그리고 Public IP/Elastic IP를 가진 인스턴스를 내부에 가지고 있다. 특히, Public Subnet 내에 있는 NAT 인스턴스를 통하여 Private Subnet내에 있는 인스턴스들이 인터넷에 접속 가능하게 한다.

**Private Subnet**: 기본적으로 외부와 차단되어 있다. Private Subnet내의 인스턴스들은 private ip만을 가지고 있으며 internet inbound/outbound가 불가능 하고 오직 다른 서브넷(public subnet 또는 또다른 private subnet)과의 연결만이 가능합니다.

**Amazon S3(Simple Storage Service)**: 온라인 객체 스토리지 서비스로 저장할 수 있는 파일에 제한이 없다.

**Internet gateway**: VPC 내부의 인스턴스(라우팅 테이블)와 외부 인터넷 간 통신을 가능하게 한다.

**Load balancer**: 트래픽으로 인해 서버에 가해지는 부하를 분산, 조절해주는 장치이다.

**APM(Aplication Performance Management) Server**: 웹 서버의 API, HTTP 요청 등이 수행된 내역과 그 과정에서 발생한 오류를 수집하고 이에 대한 성능을 측정하는 서버이다.

**WAS(Web Application Server) 서버**: 사용자의 입력을 받아 이에 대한 로직을 수행하고 그 결과를 보여주는 동적인 데이터를 처리하는 소프트웨어이다. 웹 서버와 DBMS 사이에서 동작한다.

**Redis**: Key, Value 구조의 비정형 데이터를 저장하고 관리하기 위한 오픈 소스 기반의 비관계형 데이터 베이스 관리 시스템이다. 인메모리(데이터를 메모리에 저장) 형식으로 동작하기 때문에 속도가 빠르다.

**RDS(Relational Database Service)**: AWS에서 제공하는 관계형 데이터베이스로, 기본적으로 외부에서 접근이 불가능하다. 또한 데이터베이스 모니터링 기능도 지원해주는데, DB에서 발생하는 여러 로그(Error Log, General Log 등)를 확인할 수 있다.

**NAT gatewayNetwork Address Translation)** : 네트워크의 주소를 변환해주는 시스템이다. Private subnet 내의 EC2를 인터넷, AWS Service에 접근 가능하게 하고, 외부에서는 해당 EC2에 대한 접근을 막기위해 사용한다.



## 📚 Stacks

### Infra

| category | version |
| -------- | ------- |
| AWS ec2  |         |
| AWS S3   |         |
| AWS RDS  |         |

### Backend

| category   | version |
| ---------- | ------- |
| Springboot | 3.2.4   |
| Mysql      | 8.0.36  |
| Junit      | JUnit5  |
| Redis      | 7.2.4   |

### Frontend

| category    | version |
| ----------- | ------- |
| React       | 18.2.0  |
| Surge       | 8.19.2  |
| React Query | 5.0.0   |



## 🔎 Use Case Diagram

![DANTONG USECASE.png](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbchQyW%2FbtsGrUL8ZY1%2Fp0froeiDEkXO5hSkuBTB60%2Fimg.png)



## 📱 화면 구성 예시

![image-20240408173530931](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fdfy21S%2FbtsGsrXhKd4%2F0IC4Lgb2nFdrcCf6qq4Sr1%2Fimg.png)





## 🗓️ 개발 계획

### ⌨️ 애자일 스크럼 방식을 이용하여 개발 계획 수립

![Untitled](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbHrkns%2FbtsGuM62phr%2FqCtd02Z2llSfXXMOcb0zJ1%2Fimg.png)

- 총 5번의 스프린트를 통해 개발하는 계획
- 매주 월요일 스크럼을 통해 스프린트당 총 2회의 정기회의를 진행
    - 첫주 월요일 - 이번 스프린트 개발계획 수립 및 목표 설정
    - 두번째주 월요일 - 개발 현황 공유 및 문제 리포트
- 스프린트 시작 시 백로그에서 달성 목표에 해당하는 기능을 할당하여 칸반 보드로 관리
- 효율적인 업무 분배를 위해 각 스토리 혹은 태스크마다 포인트 설정 후 해당 포인트의 합이 비슷하도록 스크럼 시 업무 분배 진행
- 스프린트1(4/8~4/22)
    - 회원가입 및 로그인 기능
    - 게시글 생성 및 조회 기능
    - CI/CD 구축
    - 회원가입 및 로그인 화면 구성
- 스프린트2(4/22~5/6)
    - 설문 생성 및 참여 화면 구성
    - 설문 조사 현황 기능
    - 설문 화면 구성
- 스프린트3(5/6~5/20)
    - 캘린더에서 조회 기능 구현
    - 캘린더 화면 구성
    - 통합 테스트 생성
    - 사용자 테스트 진행
    - 프로토타입 릴리즈
- 스프린트4(5/20~6/3)
    - 개선안 A/B 테스트 진행
    - 최종 버전 릴리즈
- 스프린트5(6/3~6/17)
    - 서비스 안정화
    - 유지 보수 및 신규 기능 회의



## ✏️테스트 계획

### 기능 테스트

- TDD 방식으로 진행
    - 각 도메인 마다 service, repository 등 각각의 기능 설계
    - 각각의 기능에 대한 테스트를 먼저 실패하도록 생성
    - 실패하는 테스트를 성공하도록 기능을 구현하여 테스트 완료
    - 기능테스트부터 시작하여 사용자 예상 시나리오 통합 테스트 그리고 스프링부트 테스트까지 통합하여 진행
- 코드 품질 검사
    - 배포 과정에서 sonarcube를 통한 소프트웨어 품질 검사
    - 배포 완료 후 Jmeter를 통한 부하 테스트

### 사용자 테스트

- SW융합대학 학생회 행사 진행 시 사용 테스트
    - 실제 학생회 행사에 적용하여 게시글 작성부터 설문 결과 확인까지 모든 과정을 테스트하고 만족도 조사를 할 수 있도록 진행
    - 학생회 행사 적용 이후 만족도 조사 결과를 통해 플랫폼 도입 이유 설명
- A/B 테스트 진행
    - 두개의 사용자 군에게 행사 목록 조회 및 참여 과정이 다른 두개의 앱을 제공 후 만족도 조사
    - 만족도가 높은 군의 구현 내용을 기반으로 반대군의 좋은 점을 적용하여 수정