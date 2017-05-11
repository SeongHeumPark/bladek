## 개체 통제가 필요하다면 `readResolve()` 대신 `enum` 자료형을 이용하라

### 왜?

- 개체 수와 관련된 불변식을 강제할 수 없음

### `Elvis` 클래스

```java
public class Elvis implements Serializable {
	public static final Elvis INSTANCE = new Elvis();
	private Elvis() {
    	// ... 
    }

	public void leaveTheBuilding() {
    	// ...
	}
}
```

- `implements Serializable`을 붙이는 순간 더 이상 __싱글턴 클래스가 아님__
  why? `readObject()` 함수가 새로운 객체를 생성하는 __숨겨진 생성자__기 때문
  즉, `readObject()`로 만들어진 객체와 기존 객체는 같지 않음

### `readResolve()`

- `readObject()` 함수로 만들어진 객체를 다른 것으로 대체할 수 있음
- 역직렬화가 끝나서 만들어진 객체에 대해서 `readResolve()` 함수가 호출됨
- `readObject()` 함수에서 만들어진 객체는 __가비지 컬렉터__가 수집

```java
public Object readResolve() {
	return INSTANCE;
}
```

- `readResolve()` 함수를 이용해 다른 객체를 반환하므로 __모든 객체 필드는__ `transient`여야 함
  즉, __개체 통제를 위해 `readResolve()`를 활용할 때는, 객체 참조 자료형으로 선언된 모든 객체 필드는 `transient`로 선언__
- `readResovle()` 함수를 활용하지 않으면 __공격당할 수 있음__
- `readResolve()` 함수의 __접근 권한은 중요함__
  `final` 클래스인 경우 반드시 `private`로 지정
  비-`final` 클래스라면 신중하게 정해야 한다. p.426 참고

### 정리

- 개체 통제를 강제하고 싶다면 `enum` 자료형을 이용하라.
- 개체 통제가 가능한 클래스가 필요하다면, `readResolve()` 함수를 구현해야 한다.
- 또, 클래싀 모든 객체 필드는 __primitive type__이거나 아니라면 `transient` 키워드를 붙여야 한다.