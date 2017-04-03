## 객체를 참조할 때는 그 인터페이스를 사용하라.

### 왜?

- 유연성

### 예제

```java
// 인터페이스를 이용하는 좋은 예제
List<Subscriber> subscribers = new Vector<>();

// 클래스를 이용하는 나쁜 예제
Vector<Subscriber> subscribers = new Vector<>();
```

- `Vector`를 다른 것으로 변경한다고 생각해보라.
- 어느 것이 더 유연한가?

### 정리

- __인터페이스__가 있다면 그 것을 이용하라.
- 없다면, __클래스__를 이용하라.