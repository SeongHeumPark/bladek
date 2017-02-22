## 전략을 표현하고 싶을 때는 함수 객체를 사용하라

### 왜?

- 유연성 증대

### 함수 객체

- 인자로 전달된 객체에 어떤 것을 하는 메서드가 하나인 객체?
- 해당 메서드의 포인터 구실을 하는 객체?

### 자바 전략 패턴의 3단 변화

- 실행 가능 전략 클래스

  ```java
  public class StringLengthCompartor {
  	public int compare(String s1, String s2) {
  		return s1.length() - s2.length();
  	}
  }
  ```

- 실행 가능 전략 클래스 + 싱글턴 패턴

  ```java
  public class StringLengthCompartor {
  	public static final StringLengthCompartor INSTANCE = new StringLengthCompartor();
    
  	private StringLengthCompartor() {
  	}

  	public int compare(String s1, String s2) {
  		return s1.length() - s2.length();
  	}
  }
  ```

- 전략 인터페이스

  ```java
  public interface Comparator<T> {
  	public int compare(T t1, T t2);
  }
  ```

### 전략 인터페이스 사용

- 인터페이스 구현

  ```java
  public class StringLengthComparator implements Comparator<String> {
  	// 내부 동일
  	@Override
  	public int compare(String s1, String s2) {
  		return s1.length() - s2.length();
  	}
  }
  ```

- 익명 클래스

  ```java
  Arrays.sort(stringArray, new Compartor<String>() {
  	public int compare(String s1, String s2) {
  		return s1.length() - s2.length;
  	}
  });
  ```

  __익명 클래스__는 여러 번 수행된다면 `private static final` 필드에 저장하고 재사용하는 것을 고려해 볼 것

- 외부에 공개하는 클래스

  ```java
  public class Host {
  	private static class StringLengthComparator implements Comparator<String>, Serializable {
  		public int compare(String s1, String s2) {
  			return s1.length() - s2.length();
  		}
  	}

  	// 이 비교자는 직렬화 가능
  	public static final Comparator<String>
  		STRING_LENGTH_COMPARATOR = new StringLengthComparator();
  }
  ```

  ​