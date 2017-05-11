## 사용자 지정 직렬화 형식을 사용하면 좋을지 따져 보라

### 왜?

- 직렬화 호환성을 보장하기 위해

### `Name` 클래스

```java
public class Name implements Serializable {
	/**
	 * 성(last name). null이 될 수 없다.
	 * @serial
	 */
	private final String lastName;
  
  	/**
	 * 이름(first name). null이 될 수 없다.
	 * @serial
	 */
	private final String firstName;
  
  	/**
	 * 중간 이름(middle name). 없을 때는 null이다.
	 * @serial
	 */
	private final String middleName;
}
```

- __기본 직렬화 형식__ 사용 가능
  즉, __물리적 표현 == 논리적 내용__
- __불변식__이나 __보안__을 위해선 `readObject()` 메서드를 구현해야 마땅한 경우가 많음

### `StringList` 클래스

```java
public final class StringList implements Serializable {
	private int size = 0;
	private Entry head = null;

	private static class Entry implements Serializable {
    	String data;
    	Entry next;
    	Entry previous;
	}
}
```

- __물리적 표현__: 이중 연결 리스트
- __논리적 내용__: 문자열 리스트
- 기본 직렬화를 받아들일 경우, __양방향 연결 구조__가 직렬화 형식에 포함됨

### "물리적 표현 != 논리적 표현"인 경우 기본 직렬화의 문제점

- 공개 API가 현재 내부 표현 형태에 영원히 종속
- 너무 많은 공간을 차지하는 문제가 생길 수 있음
- 너무 많은 시간을 소비하는 문제가 생길 수 있음
- 스택 오버플로 문제가 생길 수 있음

### 수정된 `StringList` 클래스

```java
public final class StringList implements Serializable {
	private transient int size = 0;
	private transient Entry head = null;

	private static class Entry {
    	String data;
    	Entry next;
    	Entry previous;
	}

	public final void add(String s) {
    	// ...
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
    	s.defaultWriteObject();
    	s.writeInt(size);
    
    	for (Entry e = head; e != null; e = e.next) {
        	s.writeObject(e.data);
    	}
	}

	private void readObject(ObjectInputStream s) throws IOException {
    	s.defaultReadObject();
    	int numElements = s.readInt();
    
    	for (int i = 0; i < numElements; i++) {
        	add((String) s.readObject());
    	}
	}
}
```

- 객체의 모든 필드가 `transient`인 경우 `defaultWriteObject()`와 `defaultReadObject()`를 활용은 __권장사항__
  기술적으로 불가능한 것은 아님

### 주의사항

- 비-`transient` 필드로 만들어야 겠다는 결정은 함부로 내리지마라.

- 직렬화를 적용할 때, 객체의 상태 전부를 읽는 메서드에 __동기화__를 반드시 적용하라.

  ```java
  private synchronized void writeObject(ObjectOutputStream s) throws IOException {
  	s.defaultWriteObject();
  }
  ```

- 기본이건 사용자 정의건 직렬 버전 UID는 명시적으로 선언하라.

  ```java
  private static final long serialVersionUID = 10203901202L // 사실 무작위 Long 값
  ```

### 정리

- 어떤 직렬화 형식이 적절할지 따져보지도 않고 기본 직렬화 형식을 그대로 받아들이지 마라.