## 인터페이스는 자료형을 정의할 때만 사용하라

### 왜?

- 인터페이스의 __역할__이 모호해 짐
  __역할:__ 자료형, 어떤 일을 할 수 있는지 클라이언트에게 알림

### 상수 인터페이스

- 클래스가 어떤 상수를 어떻게 사용하는지는 구현 세부사항임
- 상수 정의를 인터페이스에 정의하면 구현 세부사항이 클래스의 공개 API에 스며듦 ___what?___
- 사용하지 말 것.

### 정적 임포트

```java
import static com.effectivejava.science.PhysicalConstants.*;

// PhysicalConstants를 사용할 일이 많다면 정적 임포트가 적절
public class Test {
	double atoms(double mols) {
		return AVOGADROS_NUMBER * mols;
	}
}
```

