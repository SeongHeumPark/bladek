## 객체화된 기본 자료형 대신 기본 자료형을 이용하라.

### 왜?

- 퇴근을 늦게 한다.

### __Boxed Primitive Type__

- `Integer`, `Double`, `Boolean`
- __신원__을 가진다.

```java
Comparator<Integer> naturalOrder = new Comparator<>() {
	public int compare(Integer first, Integer second) {
    	return first < second ? -1 : (first == second ? 0 : 1);
	}
}
```

- `==` 연산의 경우 신원 조회를 진행함
  즉, __first__와 __second__는 같지 않다.
- `null` 값이 포함된다.

```java
public class Unbelievable {
	static Integer i;

	public static void main(String[] args) {
    	if (i == 42) {
        	System.out.println("Unbelievable");
    	}
	}
}
```

- 여기서 `Integer`와 기본 자료형을 비교하면 __auto-unboxing__이 일어나지만 `null` 값이다.
  즉, `NullPointerException` 발생

### __Boxed Primitive Type__은 언제 사용하나?

- 컬렉션의 요소, 키, 값
- 형인자 자료형

### 정리

- 왠만하면 __Primitive type__을 사용하라.