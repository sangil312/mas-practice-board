---
name: Project Integration Test Pattern
description: 통합 테스트 작성 패턴 — IntegrationTestSupport 상속, @Transactional 클래스 레벨, tearDown 미사용
type: project
---

통합 테스트는 반드시 `IntegrationTestSupport`를 상속하며, 클래스 레벨에 `@Transactional`을 붙여 테스트 데이터를 롤백한다.
`@AfterEach`, `tearDown` 메서드는 사용하지 않는다.
`IntegrationTestSupport`에 `@Tag("integration")`, `@SpringBootTest`, `@ActiveProfiles("local-test")`가 이미 포함되어 있다.

**Why:** 컨벤션 문서 및 기존 테스트 코드(NoticeAttachmentWriterTest)에서 확인한 확정된 패턴
**How to apply:** Implement Layer(Writer, Reader 등) 및 Repository 테스트를 작성할 때 이 패턴을 그대로 따른다.
