## 인자의 유효성을 검사하라

### 왜?

- 빠른 퇴근을 위해

### 유효성을 검사하지 않을 때 문제

- __이상한 예외__를 내며 죽는다.
- __잘못된 결과__를 낸다.
  - 심각한 경우, 프로그램 실행시 마다 객체의 값이 변하는 것이다.

### 유효성 검사

- `public` 메서드

  - __주석__을 이용하라.

  ```java
  /**
   * 메소드 설명 블라블라
   *
   * @param ...
   * @return ...
   * @throws NullPointerException (인자가 null일 때)
   */
  public Builder nickname(String nickname) {
  	if (nickname == null) {
      	throw new NullPointerException("nickname is null");
  	}
  }
  ```

- `private` 메서드

  - `Assertion`을 이용하라.

  ```java
  private static void sort(long a[], int offset, int length) {
  	assert a != null;
  	assert offset >= 0 && offset <= a.length;
  	assert length >= 0 && length <= a.length - offset;

  	// 계산 수행
  }
  ```

  - __확증문__: 클라이언트가 패키지를 어떻게 이용하건 __확증 조건__은 __항상 참__이 되어야 한다.

### 정리

- __메서드__나 __생성자__를 구현할 때 받는 인자에 제한이 있는지 따져보라.
- 있다면 문서로 남기든 검사를 하든 습관을 들여야 한다.

