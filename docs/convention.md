# 코딩 컨벤션

## 클래스 규칙
[네이밍 규칙]
- Business Layer = `*Service`
- Implement Layer = `*Handler`, `*Reader`, `*Writer`, `*Manager`, `*Validator` 등 행위 기반의 명명을 사용한다.

[도메인 엔티티 클래스 규칙]
- 도메인 엔티티 생성 시 `BaseEntity`를 상속 받아서 생성한다.
- `@Column`, `@JoinColumn` 등 컬럼 매핑 어노테이션을 사용하지 않는다.
- `@OneToMany`, `@ManyToOne`, `@OneToOne`, `@ManyToMany` 등 불필요한 연관관계 매핑을 사용하지 않는다.
  - 연관관계 대신 연관 엔티티의 ID(`Long`)를 필드로 직접 보유한다.

## @Transactional 적용 규칙
- Implement Layer가 있다면, *Service에는 @Transactional을 사용하지 않는다.
  - @Transactional은 Implement Layer에만 사용한다.
- 읽기 함수는 필요하지 않으면 @Transactional을 사용하지 않는다.
- 쓰기 함수더라도 @Transactional 이 필요 없다면 사용하지 않는다.
    - 단일 JPA Entity 의 *Repository.save() 등
- 통합 테스트 클래스는 @Transactional을 사용한다.

## DTO 디렉토리 및 네이밍 규칙
- 각 Layer에서 사용되는 요청, 응답 DTO만 각 Layer의 `request`, `response` 디렉토리에 위치시킨다.
- Presentation Layer DTO의 클래스명에는 `Request`, `Response` 접미사를 사용할 수 있다.
- 그 외 레이어(Business, Implement, Data Access 등)에서 사용하는 DTO는 클래스명에 `Request`, `Response`를 포함하지 않는다.
  - 응답 객체: `*Result` 등 사용
  - 요청 객체: `Create*`, `Update*`, `Find*` 등 사용

## API Response DTO의 변환 규칙
- 로직이 필요한 변환의 경우 생성자 or `*Response.of(...)` 함수를 사용한다.
    - 복수 파라미터가 들어갈 수 있다.
    - 해당 함수는 도메인 엔티티 객체를 활용하여 API 스펙을 맞추기위한 Converter 역할을 하고있다.
- API 스펙에 맞춘 응답 DTO의 변환 책임은 컨트롤러에 있다.
  - Service Layer, Implement Layer에서 응답 DTO를 변환해서 응답하지 않는다.

## 컨트롤러 패키지 위치 규칙
- v1 API: `dev.article.controller.v1` 패키지

## Component Annotation 규칙
- Business Layer = `@Service`
- Implement Layer = `@Component`