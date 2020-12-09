# JUST SNS

## 개요
### 4일 간의 프로젝트
4일 휴가 기간 _(2020.11.09 - 2020.11.12)_ 동안 java, spring boot 학습을 위해 게시판을 만들어 보려고 함  
계획을 매일매일 세우고 수정할 예정 ~~(애자일 방법론)~~  
4일 간의 개발 과정을 담기위해 유튜브 스트리밍, 동영상 저장 [![youtube](https://img.shields.io/badge/youtube-channel-red?logo=youtube)](https://www.youtube.com/playlist?list=PLkgj-h1OBMYo7kP6fpMCxt6SPOG-GoyWb)  
api 서버를 만들고 시간 여유가 되면 front도 개발 예정  
front end 프레임워크를 사용할지 그냥 할지 고민 중  
### 회고
[4일 간의 작업 회고](https://unhosted.tistory.com/47)  
### 그 후
아직 못 끝낸 작업들 및 보완 사항을 계속해서 추가할 예정  

## 스펙
java 11  
spring boot 2.3.5  
mysql  
vue js 2.6

## 기능
* 회원
    1. 회원가입  
    2. 로그인(토큰 발급)  
    3. 로그아웃(토큰 폐기)  
* 게시판
    1. 게시글 목록 조회  
    2. 게시글 쓰기
    3. 게시글 보기
    4. 게시글 수정
    5. 게시글 삭제
    
> 헤더에 bearer 토큰을 담아 전송  

## 개발 일지
[DevLog.md](https://github.com/ByeongUkChoi/justSNS/blob/main/DevLog.md)
## 도움이 된 자료들  
[spring-security-react-ant-design-polls-app](https://github.com/callicoder/spring-security-react-ant-design-polls-app)  
spring security, jwt 부분 참조  

[spring-jpa-best-practices](https://github.com/cheese10yun/spring-jpa-best-practices)  
dto 부분 참조  

---
## API Document
### 회원가입
#### Request
##### URL
```http request
POST /auth/signup
```
##### Parameter
|Name           |Type   |Description|Required|
|---------------|-------|-----------|--------|
|username       |String |사용자 ID    |O       |
|pwssword       |String |사용자 PW    |O       |
|name           |String |사용자 이름   |O       |
|email          |String |사용자 email |O       |
#### Response
```http request
HTTP/1.1 201 Created
```
##### Parameter
|Name           |Type   |Description|
|---------------|-------|-----------|
|username       |String |사용자 ID    |
|name           |String |사용자 이름   |
|email          |String |사용자 email |
|createdAt      |timestamp|생성 시간|
|updatedAt      |timestamp|수정 시간|
##### example
```json
{
    "username": "test01",
    "name": "Byeonguk Choi",
    "email": "test@test.com",
    "createdAt": "2020-11-09T07:55:30Z",
    "updatedAt": "2020-11-09T08:15:00Z"
}
```
### 로그인(토큰 발급)
#### Request
##### URL
```http request
POST /auth/signin
```
##### Parameter
|Name           |Type   |Description|Required|
|---------------|-------|-----------|--------|
|username       |String |사용자 ID    |O       |
|pwssword       |String |사용자 PW    |O       |
#### Response
```http request
HTTP/1.1 200 OK
```
##### Parameter
|Name       |Type   |Description|
|-----------|-------|-----------|
|accessToken|String |토큰        |
|expiresIn  |int    |토큰 유효 시간(초)|

---
### 게시글 목록 조회  
#### Request
##### URL
```http request
GET /posts
```
##### Parameter
|Name   |Type   |Description|Required|
|-------|-------|-----------|--------|
|page   |int    |페이지 번호(default : 0)|X|
|size   |int    |페이지 크기(default : 10)|X|
#### Response
```http request
HTTP/1.1 200 OK
```
##### Parameter
|Name       |Type   |Description|
|-----------|-------|-----------|
|item       |array  |결과 목록|
|id         |int    |게시글 id|
|subject    |String |게시글 id|
|content    |String |게시글 본문|
|authorId   |int    |게시글 작성자 id|
|createdAt  |timestamp|게시글 시간|
|page       |object|페이징 정보|
|number     |int|현재 페이지 번호|
|size       |int|페이지 크기|
|totalElements|int|총 개수|
##### example
```json
{
    "item": [
        {
            "id": 1,
            "subject": "Test subject",
            "content": "This is...",
            "authorId": 1,
            "createdAt": "2020-11-09T07:55:30Z",
            "updatedAt": "2020-11-09T08:15:00Z"
        }
    ],
    "page":
    {
        "number": 1,
        "size": 5,
        "totalElements": 13
    }
}
```
### 게시글 쓰기
#### Request
##### URL
```http request
POST /posts
```
##### Parameter
|Name   |Type   |Description|Required|
|-------|-------|-----------|--------|
|subject|String |제목        |O       |
|content|String |본문        |O       |
##### example
```json
{
    "subject": "Test subject",
    "content": "This is..."
}
```
#### Response
```http request
HTTP/1.1 201 Created
```
##### Parameter
|Name       |Type   |Description|
|-----------|-------|-----------|
|id         |int    |게시글 id|
|subject    |String |게시글 id|
|content    |String |게시글 본문|
|authorId   |int    |게시글 작성자 id|
|createdAt  |timestamp|생성 시간|
|updatedAt  |timestamp|수정 시간|
##### example
```json
{
    "id": 1,
    "subject": "Test subject",
    "content": "This is...",
    "authorId": 1,
    "createdAt": "2020-11-09T07:55:30Z",
    "updatedAt": "2020-11-09T08:15:00Z"
}
```
### 게시글 상세 조회
#### Request
##### URL
```http request
GET /posts/(:id)
```
##### Parameter
|Name   |Type   |Description|Required|
|-------|-------|-----------|--------|
#### Response
```http request
HTTP/1.1 200 OK
```
##### Parameter
|Name       |Type   |Description|
|-----------|-------|-----------|
|id         |int    |게시글 id|
|subject    |String |게시글 id|
|content    |String |게시글 본문|
|authorId   |int    |게시글 작성자 id|
|createdAt  |timestamp|생성 시간|
|updatedAt  |timestamp|수정 시간|
##### example
```json
{
    "id": 1,
    "subject": "Test subject",
    "content": "This is...",
    "authorId": 1,
    "createdAt": "2020-11-09T07:55:30Z",
    "updatedAt": "2020-11-09T08:15:00Z"
}
```
### 게시글 수정
#### Request
##### URL
```http request
PATCH /posts/(:id)
```
##### Parameter
|Name   |Type   |Description|Required|
|-------|-------|-----------|--------|
|subject|String |수정 할 제목  |X       |
|content|String |수정 할 본문  |X       |
##### example
```json
{
    "subject": "Test subject - modified"
}
```
#### Response
```http request
HTTP/1.1 200 OK
```
##### Parameter
|Name       |Type   |Description|
|-----------|-------|-----------|
|id         |int    |게시글 id|
|subject    |String |게시글 id|
|content    |String |게시글 본문|
|authorId   |int    |게시글 작성자 id|
|createdAt  |timestamp|생성 시간|
|updatedAt  |timestamp|수정 시간|
##### example
```json
{
    "id": 1,
    "subject": "Test subject - modified",
    "content": "This is...",
    "authorId": 1,
    "createdAt": "2020-11-09T07:55:30Z",
    "updatedAt": "2020-11-09T08:15:00Z"
}
```
### 게시글 삭제
#### Request
##### URL
```http request
DELETE /posts/(:id)
```
##### Parameter
|Name   |Type   |Description|Required|
|-------|-------|-----------|--------|
#### Response
```http request
HTTP/1.1 200 OK
