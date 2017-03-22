## 비트 필드 대신 `EnumSet`을 사용하라

### 왜?

- 형 안전성
- `Set` 인터페이스의 풍부한 기능들 사용 가능
- `Set`을 구현한 같은 수준의 __상호운용성__ 제공 가능
- 비트 필드와 비등한 성능 (내부적으로 __비트 벡터__를 사용)

### `int enum`을 이용한 집합 표현

```Java
public class Text {
	public static final int STYLE_BOLD			= 1 << 0; // 1
	public static final int STYLE_ITALIC		= 1 << 1; // 2
	public static final int STYLE_UNDERLINE		= 1 << 2; // 4
	public static final int STYLE_STRIKETHROUGH	= 1 << 3; // 8

	public void applyStyles(int styles) { ... }
}
  
// 호출 시
text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
```

- `int enum` 패턴의 문제점을 그대로 앉고 있음

### `EnumSet` 을 이용한 집합 표현

```Java
public class Text {
	public enum Style {
    	BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
	}

	public void applyStyles(Set<Style> styles) { ... }
}

// 호출 시
text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
```

- `Set` 인터페이스를 구현 (`Set` 인터페이스의 풍부한 기능 사용가능)
- __형 안전성__ 제공
- 다른 `Set` 구현들과 같은 수준의 __상호운용성__ 제공
- 내부적으로 __비트 벡터__ 사용 (즉, 비트 필드에 필적하는 성능이 나옴)

### `EnumSet`의 단점

- 변경 불가능 `EnumSet`을 만들 수 없다.
- __Google Guava__ 라이브러리 참고 (변경 불가능한 `EnumSet`을 만들 수 있다.)

### 정리

- 집합이 필요하면 `EnumSet`을 활용하라.