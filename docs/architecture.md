# 아키텍처

## MSA 멀티 모듈 레포지토리 Gradle 프로젝트
- `service:article`: 게시글 CRUD
- `service:article-read`: 게시글 read 
- `service:comment`: 게시글 댓글 CRUD 
- `service:hot-article`: 인기 게시글 
- `service:like`: 게시글 좋아요 
- `service:view`: 게시글 상세 조회 

## 아키텍처 기본 규칙
- 큰 틀의 계층은 3개의 계층이나, 명시적인 분리를 위해 4개의 영역으로 관리한다.
  1. Presentation Layer (*Controller)
  2. Business Layer (*Service, *UseCase)
  3. Implement Layer (*Reader, *Writer, *Validator 등 상황에 따라 추가가능)
  4. Data Access Layer (*Repository, *Client 등)

[4가지 계층 규칙]
- 규칙1. 레이어는 위에서 아래로 순방향으로만 참조 되어야한다.
- 규칙2. 레이어는 참조 방향이 역류 되지 않아야한다.
- 규칙3. 레이어의 참조가 하위 레이어를 건너 뛰지 않아야한다.
  - 단, Implement Layer가 wrapper class 역할만 하게된다면 Implement Layer를 생략하고, 로직이 늘어나서 Implement Layer를 분리해야할 시점이 되면 사용한다.
- 규칙4. 동일 레이어간에는 서로 참조하지 않아야한다.
  - Implement Layer는 예외적으로 서로 참조가 가능하다.
- 규칙5. Business Layer에서 *Service 간 조합이 필요한 경우 *UseCase를 생성해서 사용한다.
  - *UseCase에서는 *Service만 참조 가능하다.

