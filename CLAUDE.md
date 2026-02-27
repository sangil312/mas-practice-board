# CLAUDE.md

## 기본 규칙
- 설명은 항상 한국어로만 한다.

## 프로젝트 문서
- 아키텍처: @docs/architecture.md
- 코딩 컨벤션: @docs/convention.md

## 명령어
```bash
# 전체 테스트 실행
./gradlew test

# 단위 테스트만 실행 (@Tag("integration") 제외)
./gradlew unitTest

# 통합 테스트만 실행 (@Tag("integration") 포함)
./gradlew integrationTest
```
