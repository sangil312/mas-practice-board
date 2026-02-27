---
name: Project Unit Test Pattern
description: 단위 테스트 작성 패턴 — @ExtendWith(MockitoExtension), @Tag 없음, Service 계층에 주로 적용
type: project
---

모킹이 필요한 테스트는 단위 테스트로 작성한다.
`@ExtendWith(MockitoExtension.class)`를 사용하며, `@Tag` 어노테이션은 붙이지 않는다.
Service 계층(NoticeService)은 하위 컴포넌트를 모두 Mock으로 처리하여 단위 테스트로 작성한다.

**Why:** 컨벤션 및 기존 테스트 코드(NoticeServiceTest, NoticeAttachmentValidatorTest)에서 확인한 패턴
**How to apply:** NoticeService, 도메인 단위 로직 등 외부 의존성을 모킹할 수 있는 계층의 테스트에 적용한다.
