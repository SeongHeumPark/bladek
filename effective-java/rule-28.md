## 한정적 와일드 카드를 써서 API 유연성을 높여라

### 왜?

### PECS

- Produce - Extends, Cosumer - Super

- __생산자__나 __소비자__ 구실을 하는 메서드 인자의 자료형은 __와일드 카드 자료형__으로 하라

- `Stack` 클래스를 예로 들면

  ```java
  // pushAll() 함수는 Produce
  public void pushAll(Iterable<? extends E> src) {
  	for (E e : src) {
      	push(e);
  	}
  }

  // popAll() 함수는 Consumer
  public void popAll(Collection<? super E> dst) {
  	while (!isEmpty()) {
      	dst.add(pop());
  	}
  }
  ```


### 주의 사항

- 반환 값에는 와일드 카드 자료형을 쓰면 안된다.

### 이원성 문제

- 메서드 가운데 상당 수는 두 방법 중 어떤 것으로도 선언될 수 있다.

  ```Java
  // swap 메서드를 선언하는 두 가지 방법
  public static <E> void swap(List<E> list, int i, int j);	// 비한정적 형 인자
  public static void swap(List<?> list, int i, int j);		// 비한정적 와일드 카드
  ```

- __비한정적 와일드 카드__ 사용이 더 좋다.
  Why? 간 to the 단

- __핵심__은 __형 인자가 메서드 선언에 단 한 군데라도 나타나면 해당 인자를 와일드 카드로 바꿔라__

### 정리

- PECS