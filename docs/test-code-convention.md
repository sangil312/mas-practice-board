# 테스트 코드 컨벤션

### 테스트 코드 품질
- Given-When-Then 패턴 사용
- 테스트 메서드명은 테스트 대상 메서드 명을 기반으로 작성
- 각 테스트는 독립적으로 실행 가능해야 함
- Edge case와 예외 상황 테스트 포함
- 불필요한 테스트 작성 금지, 의미 있는 검증만 수행

### 계층별 테스트 전략
[Controller]
- `ControllerTestSupport` 상속 대상 테스트: Controller
- `IntegrationTestSupport` 상속 대상 테스트: Service, Implement, Repository (단 단위테스트의 경우 상속 X)
- **Controller**: MockMvc를 사용
- **Service / UseCase**: 단위 테스트
- **Implement Layer (Reader, Writer 등)**: 통합 테스트 (외부 API 호출 등은 mocking 처리)
- **Repository**: 통합 테스트 (Data JPA를 사용한 경우 테스트 작성 제외)

### 통합 테스트 규칙
- `IntegrationTestSupport` 클래스를 반드시 상속하여 작성 (이 클래스에 `@SpringBootTest` 포함)
- `@Transactional` 어노테이션을 사용하여 테스트 데이터를 롤백 (`@AfterEach`, `tearDown` 등 사용 하지 않음)
- mocking이 필요한 경우 `@MockitoBean`을 사용하고, **`IntegrationTestSupport` 클래스의 필드에 선언**하여 하위 테스트 클래스에서 공유