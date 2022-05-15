# twitch-clone

* [Wiki](#wiki)
* [서버 실행 시 주의사항](#서버-실행-시-주의사항)
* [Problem Solving](#problem-solving)
    + [분산 트랜잭션 설계하기 (초급)](#트랜잭션-설계)

## Wiki

- [Ground Rule](https://github.com/f-lab-edu/twitch-clone/wiki/Ground-Rule)
    - Git 전략, Commit Convention, PR review
- [[회원] 전체 흐름](https://github.com/f-lab-edu/twitch-clone/wiki/%5B%ED%9A%8C%EC%9B%90%5D-%EC%A0%84%EC%B2%B4-%ED%9D%90%EB%A6%84)

## 서버 실행 시 주의사항

- property file에 DB 패스워드 정보가 없기 때문에 실행 시
    - --spring.datasource.password=패스워드 정보를 입력하여 실행 하여야 합니다.

## Problem Solving

### 트랜잭션 설계

![image](https://user-images.githubusercontent.com/55722186/166146912-be79d2aa-0e43-4e96-986d-6561893683c2.png)

- 이 모델은 Choreographed SAGA 패턴과 유사합니다.
    - 메시지 브로커 활용 대신 트랜잭션을 잡고 있는 점이 달라 장애 처리는 비교적 용이하지만,
      복수 개의 서비스에 연쇄적인 트랜잭션이 생기고 있어 관리와 추적이 힘들다는 단점이 같습니다.
    - 반면 2PC 패턴은 트랜잭션의 관리 주체가 한 곳으로 집중되어 트랜잭션 관리가 용이합니다.
- 반면 2PC 패턴은 트랜잭션의 관리 주체가 한 곳으로 집중되어 트랜잭션 관리가 용이합니다.
- api-module만이 Spring 의존도를 가지며 그 외 module은 도메인 로직을 가지도록 설계했습니다.  
  따라서 usecase에 따라 각 module에서 생성된 결과를 api-module에서 영속시킵니다.
- 사이드 프로젝트이기에 복잡도가 높지 않기에 로직의 추가가 api-module에 영향을 주어도 괜찮다고 생각했습니다.

아마 실무였다면 트랜잭션 타임을 줄이기 위해 MQ를 도입하고 Orchestration Saga 패턴을 고려했을 것 같습니다.  
하지만 사이드 프로젝트에 MQ까지 고려하는 것은 힘들었고, `도메인 분리와 모듈 프로그래밍`에 집중하기 위해 2PC 패턴의 구성을 선택했습니다.

좀 더 많은 예시와 생각은 블로그를 참고 부탁드립니다. [분산 트랜잭션 설계하기 (초급)](https://hyune-c.tistory.com/entry/%EB%B6%84%EC%82%B0-%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98-%EC%84%A4%EA%B3%84%ED%95%98%EA%B8%B0-%EC%B4%88%EA%B8%89)
