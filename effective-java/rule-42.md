## varargs는 신중히 사용하라

### 왜?

- 혼란스런 결과를 초래한다.
- 성능상 문제

### 가변 인자 메서드

- 예제

```java
static int sum(int... args) {
	int sum = 0;
	
	for (int arg : args) {
    	sum += arg;
	}
	
	return sum;
}
```

- varargs 메서드를 __호출할 때마다 배열이 만들어지고 초기화 된다.__ (성능 고려해야 함)

```java
ReturnType1 suspect1(Object... args) { ... }
<T> ReturnType2 suspect2(T... args) { ... }
```

- __마지막 인자가 배열이라고 해서 무조건 뜯어고칠 생각을 버려라.__
- 자세한 건 책 참고

### 정리

- varargs는 정말 가변적인 메서드를 정의할 때만 사용하라.
- 남용되면 곤란하다.