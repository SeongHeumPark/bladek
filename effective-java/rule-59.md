## 불필요한 점검지정 예외 사용은 피하라.

### 왜?

- 사용하기 불편해 진다.

### 무점검 예외

- 점검지정 예외를 무점검 예외로 바꾸는 방법

  ```Java
  try {
  	obj.action(args);
  } catch(TheCheckedException e) {
  	// 예외 상황 처리
  }

  // 상태 검사 메서드를 거쳐서 무점검 예외 메서드 호출
  if (obj.actionPermitted(args)) {
  	obj.action(args);
  } else {
  	// 예외 상황 처리
  }
  ```

  - `boolean` 값을 반환하는 메서드를 먼저 호출하여 반환 값에 따라 처리하도록 한다.

### 정리

- 사용하기 편한 API를 만들려면 불필요한 점검지정 예외 사용은 피해라.