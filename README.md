# twitch-clone

## Wiki

- [Ground Rule](https://github.com/f-lab-edu/twitch-clone/wiki/Ground-Rule)
    - Git 전략, Commit Convention, PR review
- [[회원] 전체 흐름](https://github.com/f-lab-edu/twitch-clone/wiki/%5B%ED%9A%8C%EC%9B%90%5D-%EC%A0%84%EC%B2%B4-%ED%9D%90%EB%A6%84)  

## 서버 실행 시 주의사항

- property file에 DB 패스워드 정보가 없기 때문에 실행 시
  - --spring.datasource.password=패스워드 정보를 입력하여 실행 하여야 합니다.