## 작명 패턴 대신 어노테이션을 사용하라

### 왜?

- 테스트가 입맛대로(?) 컨트롤(?) 하기 쉬워진다.

### 작명 패턴

- 이름이 `test`로 시작하는 메서드
- 단점
  - 오타시 알아채기 힘듦
  - 특정한 프로그램 요소에만 적용되도록 만들 수 없음
    한 클래스의 모든 메서드를 테스트하고 싶어서 클래스 이름 앞에 `test`를 붙였다. (동작 X)
  - 프로그램 요소에 인자를 전달할 마땅한 방법이 없음
    특정 예외가 발생했을 때만 테스트하고 싶을 때, __유일한 방법은 예외를 인자로 넘기는 것__ (좋은 방법 아님)

### 어노테이션

- 작명 패턴의 문제를 깔끔히 해결함

```Java
/**
 * 어노테이션이 붙은 메서드가 테스트 메서드임을 표시.
 * 무인자 정적 메서드에만 사용 가능. (강제할 수 없음)
 */
// 메타 어노테이션
@Retention(RetentionPolicy.RUNTIME)	// Test가 실행 시간에도 유지되어야 하는 어노테이션임을 명시
@Target(ElementType.METHOD)	// Test가 메서드 선언부에만 적용할 수 있다는 어노테이션임을 명시
public @interface Test {
}

public class Sample {
	@Test public static void m1() {}			// 이 테스트는 성공해야 함
  
	public static void m2() {}
  
	@Test public static void me() {				// 이 테스트는 실패해야 함
    	throw new RuntimeException("Boom");
	}

	public static void m4() {}

	@Test public void m5() {}					// 잘못된 사용: static 메서드가 아님 (컴파일 가능)

	public static void m6() {}

	@Test public static void m7() {				// 이 테스트는 실패해야 함
    	throw new RuntimeException("Crash");
	}

	public static void m8() {}
}
```

### __한정적 자료형 토큰__을 이용한 어노테이션

```java
/**
 * 이 어노테이션이 붙은 메서드는 테스트 메서드이며,
 * 테스트에 성공하려면 지정된 예외를 발생시켜야 한다.
 */
@Retention(RetentioPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
	Class<? extends Exception> value();
}

public class Sample2 {
	@ExceptionTest(ArithmeticException.class)
	public static void m1() {
    	int i = 0;
    	i = i / i;
	}

	@ExceptionTest(ArithmeticException.class)
	public static void m2() {
    	int[] a = new int[0];
     	int i = a[1];
	}

	// 이하 생략
}
```

### 정리

- 작명 패턴은 이제 사용하지 말자.
- 테스트 도구 개발자가 아니라면 어노테이션 자료형을 정의할 필요가 없다.