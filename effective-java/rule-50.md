## 다른 자료형이 적절하다면 문자열 사용은 피하라.

### 왜?

- 더 좋은 자료형이 많다.
  즉, 안전하지 않다.

### __문자열__로 해선 안될 일

- __문자열__은 __값 자료형__을 대신하기엔 부족하다.
  적당한 자료형이 없으면 새로 만들어야 한다.

- __문자열__은 __`enum` 자료형__을 대신하기엔 부족하다.
  `enum` 짱짱맨!!!

- __문자열__은 __혼합 자료형__을 대신하기엔 부족하다.

  ```Java
  String compoundKey = className + "#" + i.next();
  ```

- __문자열__은 __권한__을 표현하기엔 부족하다.

  ```Java
  public class ThreadLocal {
  	private ThreadLocal {
  	}

  	public static void set(String key, Object value);

  	public static Object get(String key);
  }
  ```

  - 위 코드가 통하려면 제공하는 문자열 키의 __유일성이 보장__되어야 한다.
    즉, 위조가 가능하다...
  - __위조 불가능__ 키로 변경

  ```Java
  public class ThreadLocal {
  	private ThreadLocal {
  	}

  	public static class Key {
      	Key() {
      	}
  	}

  	public static Key getKey() {
      	return new Key();
  	}

  	public static void set(Key key, Object value);
  	public static Object get(Key key);
  }
  ```

  - 정적 메서드가 필요한가???
  - __제네릭__ 사용

  ```Java
  public final class ThreadLocal<T> {
  	public ThreadLocal();
  	public void set(T value);
  	public T get();
  }
  ```

### 정리

- 너무 다 `String`으로 하려고 하지 마라.