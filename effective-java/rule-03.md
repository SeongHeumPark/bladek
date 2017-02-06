## private 생성자나 enum 자료형은 싱글턴 패턴을 따르도록 설계하라

### 싱글턴 구현 방법
- public final 필드를 이용한 싱글턴 _(JDK 1.5 이전)_

	```
	public class ProductManager {
		public static final ProductManager INSTANCE = new ProductManager();

		private ProductManager() { }
	}
	```

- 정적 팩터리 메소드를 이용한 싱글턴 _(JDK 1.5 이전)_

```
public class ProductManager {
	private static final ProductManager INSTANCE = new ProductManager();

	private ProductManager() { }

	public static ProductManager getInstance() {
		return INSTANCE;
	}
}
```

- enum 클래스를 이용한 싱글턴 _(JDK 1.5 이상)_
```
public enum ProductManager {
	INSTANCE;
}
```