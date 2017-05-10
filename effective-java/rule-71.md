## 초기화 지연은 신중하게 하라.

### 왜?

- 성능 저하

### 초기화 지연

- 필드 초기화를 실제로 그 값이 쓰일 때까지 미루는 것
- 값을 사용하는 곳이 없다면 필드는 결코 초기화되지 않음
- __static 필드__, __객체 필드__에 모두 적용 가능
- 최적화 기법의 한 종류
- 클래스나 객체 초기화 과정에서 발생하는 __해로운 순환성__을 해소하기 위해서도 사용

### 초기화 지연이 어울리는 곳

- 필드 사용 빈도가 낮음
- 초기화 비용이 높음
- 확실히 아는 방법은 __초기화 지연 적용 전후 성능 비교__

### 다중 스레드 환경에서 초기화 지연

- 매우 까다롭
- 반드시 적절한 동기화가 필요
- 대 부분의 경우, 지연된 초기화말고 일반 초기화를 하는 편이 나음

### 초기화 지연 예제

- 초기화 순환성 문제 해소: __동기화된 접근자__

  ```java
  private FieldType field;

  synchronized FieldType getField() {
  	if (field == null) {
      	field = computerFieldValue();
  	}

  	return field;
  }
  ```

  - __멀티 스레드__ 문제를 해결한 __싱글톤 패턴__에서 볼 수 있는 예제

- 성능 문제 때문에 정적 필드를 초기화 지연하고 싶은 경우: __초기화 지연 담당 클래스__

  ```java
  private static class FieldHolder {
  	static final FieldType field = computeFieldValue();
  }

  static FieldType getField() {
  	return FieldHolder.field;
  }
  ```

  - `getField()` 함수가 호출된 순간에 초기화 됨
  - 동기화 메소드로 선언하지 않아도 됨

- 성능 문제 때문에 객체 필드를 초기화 지연하고 싶은 경우: __이중 검사__

  ```java
  private volatile FieldType field;

  FieldType getField() {
  	FieldType result = field;

  	if (result == null) {
      	synchronized (this) {
          	result = field;
            
          	if (result == null) {
              	field = result = computeFieldValue();
          	}
      	}
  	}

  	return result;
  }
  ```

  - `result` 변수가 하는 일은 __이미 초기화된 필드는 딱 한 번만 읽도록 하는 것__
  - 성능을 높일 가능성도 있고, 우아함

- 여러번 초기화 되어도 상관 없는 개체 필드의 초기화 지연: __단일 검사__

  - __이중 검사__의 변종
  - 코드는 p.389 참고

### ViewStub

- 안드로이드 초기화 지연인듯
- 알아볼 것.

## 정리

- __정말로 필요하지 않으면 하자 마라.__