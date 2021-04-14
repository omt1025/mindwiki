# Hello MiND WiKi

### `마인드맵`으로 생각과 정보를 `소통할 수 있는 SNS`

<img src="https://user-images.githubusercontent.com/45623764/114296148-80db2a80-9ae4-11eb-99be-0616bb5b223b.png" width="30%"><br/><br/><br/>

## 🔎 [마인드위키(mindwiki)](http://ec2-18-219-169-252.us-east-2.compute.amazonaws.com:8080/)  보러가기<br/>

- 테스트 ID : qq@q.com    테스트 PW : qqqq1111@

<br/>

<br/>

## 👬 팀원 소개

| 역할 | 이름     | 포지션   | Github                                                    |
| ---- | -------- | -------- | --------------------------------------------------------- |
| 팀장 | 🐹 황윤호 | Frontend |  [@hossi-py](https://github.com/hossi-py)    |
| 팀원 | 🐼 김정웅 | Backend  |  [@real100woong](https://github.com/real100woong)     |
|      | 🦁 신충현 | Backend  | [@chyn00](https://github.com/chyn00)    |
|      | 🐻 오민택 | Frontend | [@omt1025](https://github.com/omt1025)   |
|      | 🐸 윤지선 | Frontend | [@giga1615](https://github.com/giga1615) |

<br/>

<br/>

## 🗓  프로젝트 일정

2021.01.11 ~ 2021.02.19

<br/>

<br/>

## 🔮 기획의도

넘쳐나는 글자 속에서 원하는 정보를 얻기 힘들어진 요즘<br/>
다른 사람의 TMI가 아닌, 원하는 정보를 직관적으로 파악하도록 돕기위하여 기획

<br/>

<br/>


## ⚙️ 기능

- 직관적 정보 제공
  - 마인드맵을 이용한 게시글 작성 
- 소통을 통한 공동체의식 부여
  - 마인드맵을 다른사람과 공동 수정 가능
  - 팔로우, 팔로잉
  - 스크랩
  - 댓글 작성 
- 좋아요, 스크랩을 통한 MIND 저장 등

<br/>

<br/>

## 🧩 구조도

<img src="https://user-images.githubusercontent.com/45623764/114301095-24850480-9afe-11eb-89a4-df2e46f0e436.png" width="90%">

<br/>

<br/>


## 🛠 와이어프레임

![스크린샷 2021-04-11 오후 7 28 17](https://user-images.githubusercontent.com/45623764/114300613-1c2bca00-9afc-11eb-8f16-f522d8d55564.png)

<br/>

<br/>

## 📎 기술 스택

![기술스택-01](https://user-images.githubusercontent.com/45623764/114313585-06d19280-9b32-11eb-98af-2a8828410d35.png)

<br/>

## 📸 구현 화면

> ### 시작화면
>
> - 마인드위키 로고를 클릭하면 3가지의 버튼이 등장

<img src="https://user-images.githubusercontent.com/45623764/114313699-85c6cb00-9b32-11eb-91a9-64eeb96d998c.jpeg" width="32%">&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114313702-88c1bb80-9b32-11eb-8f1b-96010fc8e84b.jpeg" width="32%">

<br/>

> ### 회원가입, 로그인, 시연영상 화면
>
> - 회원가입
>   - 아이디는 무조건 이메일 형식이어야 함
>   - 비밀번호는 특수문자, 영문 포함 8~12자
>   - 관심태그는 무조건 하나 있어야하고, 최대 3개까지 지정 가능
> - 로그인
>   - 카카오, 구글로그인 가능
>   - 회원가입 하지 않은 사람이 카카오, 구글로그인 실행시, 회원가입 화면으로 이동하며
>     이메일과 유저 이름의 정보는 카카오, 구글 계정에서 가져와 입력되어있음
> - 시연영상 
>   - 유튜브에 업로드 되어있는 시연영상페이지로 이동

<img src="https://user-images.githubusercontent.com/45623764/114313894-6a0ff480-9b33-11eb-9013-1d44384c775c.jpeg" width="32%">&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114313891-68463100-9b33-11eb-81de-117159adfff9.jpeg" width="32%">&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114313903-798f3d80-9b33-11eb-803f-44f883bebb9e.jpeg" width="32%">

<br/>

> ### 메인페이지
>
> - 실시간 탭
>   - 모든 사용자가 게시한 게시물이 최근 게시물부터 차례대로 보여짐
>   - 다른 사용자가 작성한 마인드도 함께 수정이 가능함
>   - 좋아요를 누르면 MY WIKI탭에서 별도로 좋아요누른 게시물만 따로 볼 수 있음
>   - 스크랩을 누르면 마이페이지 -> Scrap Mind에서 별도로 소장하여 볼 수 있음
> - 관심태그 탭
>   - 회원가입때 등록한 관심태그와 관련된 게시물이 보여짐
>   - 관심태그 수정 가능
> - MY WIKI 탭
>   - 좋아요 누른 게시물만 보여짐
> - 작성자를 누르면, 작성자의 프로필 화면이 뜨고 팔로우할 수 있음

<img src="https://user-images.githubusercontent.com/45623764/114314124-5e70fd80-9b34-11eb-843b-310ffc7c8a15.jpeg" width="32%">&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114314122-5a44e000-9b34-11eb-85aa-8f69f109b2d7.jpeg" width="32%">&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114314132-64ff7500-9b34-11eb-9549-da6586ae73a2.jpeg" width="32%">

<br/>

> ### 마인드 생성
>
> - 하단 nav에서 가운데 버튼 클릭
> - 썸네일을 지정하지 않으면, 작성한 마인드맵이 캡처되어 썸네일로 저장됨(뒤에 나옴)

<img src="https://user-images.githubusercontent.com/45623764/114314330-21f1d180-9b35-11eb-9c12-c80a16ca89aa.jpeg" width="32%">

<br/>

> ### 마인드 수정
>
> - 마인드맵이 생성되었으면 각 노드를 클릭하여 자식노드를 생성

<img src="https://user-images.githubusercontent.com/45623764/114314418-84e36880-9b35-11eb-9aa5-8f9b8e67d379.jpeg" width="32%">&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114314424-8876ef80-9b35-11eb-93c7-65df05ea5d4a.jpeg" width="32%">

<br/>

> ### 마인드 작성 완료
>
> - 모든 수정이 완료되면, 오른쪽 상단의 완료를 누름
> - 썸네일을 지정하지 않은 게시물의 경우, 작성한 마인드가 캡처되어 썸네일로 지정됨

<img src="https://user-images.githubusercontent.com/45623764/114314604-5ca83980-9b36-11eb-8ca8-0ab157706841.jpeg" width="32%">&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114314609-616ced80-9b36-11eb-87cb-e8ba67eee2cd.jpeg" width="32%">

<br/>

> ### 게시물 상세페이지
>
> - 각 게시물을 클릭하면 상세 페이지로 이동
>   - 확대, 축소, 전체화면 조절이 가능
>   - 그래프를 옆으로 눞이거나, 위로 세우거나 선택이 가능함
>   - 상단 ...을 클릭하면 마인드 수정이 가능
> - 댓글쓰기를 클릭하여 댓글을 작성
>   - 상단 댓글 검색을 통해 원하는 댓글을 찾을 수 있음
>   - 본인이 작성한 댓글을 삭제할 수 있음

<img src="https://user-images.githubusercontent.com/45623764/114314812-39ca5500-9b37-11eb-92c3-205aa5a3cd96.jpeg" width="32%">&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114314819-40f16300-9b37-11eb-81b2-ddda27e11fe8.jpeg" width="32%">&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114314833-4a7acb00-9b37-11eb-8b4b-a2f4d3cb62cc.jpeg" width="32%">

<br/>

> ### 프로필 페이지 & 내정보 수정
>
> - 프로필 페이지
>   - 계정의 게시물, 스크랩 수 파악 가능
>   - 팔로워, 팔로잉 수 파악 가능,
>     - 클릭하면, 팔로워, 팔로잉 리스트를 볼 수 있음
>   - 내가 작성한 MIND와 스크랩한 MIND를 한 눈에(가로 스크롤) 볼 수 있음
>   - 수정하기 버튼을 클릭하면 프로필 수정 페이지로 이동
> - 프로필 수정 페이지
>   - 프로필 사진 변경이 가능
>   - 이메일과 이름은 변경 불가
>   - 닉네임, 핸드폰만 변경가능
> - 팔로우 목록
>   - 탭으로 구분되어있어 팔로워와 팔로잉을 한 페이지에서 볼 수 있음
>   - 팔로잉 탭의 경우, 팔로우 취소가능(쓰레기통 아이콘이 있음)

<img src="https://user-images.githubusercontent.com/45623764/114314963-de4c9700-9b37-11eb-9f63-5b7764264ce5.jpeg" width="32%">&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114314999-00461980-9b38-11eb-8eb0-14e0562a07e3.jpeg" width="32%">&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114315155-88c4ba00-9b38-11eb-8ae1-b73d6a1d744d.jpeg" width="32%">

<br/>

> ### 검색 & 활동 알림 페이지
>
> - 하단 nav 돋보기를 클릭하면 검색페이지로 이동
>   - 입력한 키워드에 맞는 계정, 게시글 제목, 해시태그에 해당하는 게시글이 보여짐
>   - 프로필의 경우, 누르면 해당 계정의 페이지로 이동하며 팔로우 할 수 있음
>   - 게시글의 경우, 누르면 해당 게시글의 상세페이지로 이동함
> - 하단 nav 종 아이콘을 클릭하면 활동 알림 페이지로 이동
>   - 다른 사용자가 본인의 게시글을 좋아요/스크랩 하는 경우 알림이 쌓임

<img src="https://user-images.githubusercontent.com/45623764/114315250-10aac400-9b39-11eb-916a-ef086fa3c8f6.jpeg" width="32%">&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114315383-beb66e00-9b39-11eb-9871-f4615e44569e.jpeg" width="32%">

<br/>

> ### 로그아웃, 비밀번호 변경, 회원탈퇴
>
> - 메인페이지의 상단 ...을 누르면 드롭메뉴가 등장

<img src="https://user-images.githubusercontent.com/45623764/114315455-105ef880-9b3a-11eb-8e35-b8915a853765.jpeg" width="32%">&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114315480-1fde4180-9b3a-11eb-97e3-ebf6b4d0045c.jpeg" width="32%">&nbsp;&nbsp;&nbsp;<img src="https://user-images.githubusercontent.com/45623764/114315483-210f6e80-9b3a-11eb-8886-7280b27ae623.jpeg" width="32%">

<br/>

<br/>

## 👀 시연영상

[마인드위키 시연영상 보러가기](https://youtu.be/n6_M2kYGV94)
