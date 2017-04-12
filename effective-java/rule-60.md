## 표준 예외를 사용하라.

### 왜?

- 배우기 쉽고
- 읽기 쉽고
- 리소스(메모리, 시간)가 확보 된다.

### 표준 예외

- `IllegalArgumentException`
  - `null`이 아닌 인자의 값이 잘못되었을 때
- `IllegalStateException`
  - 객체 상태가 메서드 호출을 처리하기에 적절치 않을 때
- `NullPointerException`
  - `null` 값을 받으면 안되는 인자에 `null`이 전달되었을 때
- `IndexOutOfBoundException`
  - 인자로 주어진 첨자가 허용 범위를 벗어났을 때
- `ConcurentModificationException`
  - 병렬적 사용이 금지된 객체에 대한 병렬 접근이 탐지되었을 때
- `UnsupportedOperationException`
  - 객체가 해당 메서드를 지원하지 않을 때

### 정리

- 표준 예외는 알아두고 씁시다. (이름 뿐만 아니라 의미까지도)