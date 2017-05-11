## 직렬화된 객체 대신 직렬화 프락시를 고려해 보라

### 왜?

- 버그와 보완 결함이 생길 가능성이 줄어듦

### 직렬화 프락시 패턴

```java
public class Preiod implements Serializable {
	private final Date start;
	private final Date end;

	public Preiod(Date start, Date end) {
    	this.start = start;
    	this.end = end;
	}
  
	private static class SerializationProxy implements Serializable {
    	private final Date start;
    	private final Date end;
    
    	SerializationProxy(Preiod p) {
        	this.start = p.start;
        	this.end = p.end;
    	}
    
    	private static final long serialVersionUID = 234912893891241203L;
    
    	private Object readResolve() {
        	return new Preiod(start, end);
    	}
	}

	private Object writeReplace() {
    	return new SerializationProxy(this);
	}

	private void readObject(ObjectInputStraem stream) throws InvalidObjectException {
    	throw new InvalidObjectException("Proxy required");
	}
}
```

### 직렬화 프락시 패턴의 장점

- __가짜 바이트 스트림__ 공격, __내부 필드 탈취__ 공격에 대응 가능
- 클래스를 완전한 변경 불가능 클래스로 만들 수 있음
- 많은 생각이 필요 없음
- 등등..

### 직렬화 프락시 패턴의 위력

- 직렬화한 클래스를 역직렬화할 때 다른 클래스가 되도록 만들 수 있다.
  ex) `EnumSet`

### 직렬화 프락시 패턴의 제약사항

- 클라이언트가 확장할 수 있는 클래스에 적용 X
- 객체 그래프에 순환되는 부분이 있는 클래스에 적용 X

### 정리

- 클라이언트가 확장할 수 없는 클래스가 있을 때
- `readObject()`나 `writeObejct()`  함수를 구현해야 할 때
- __직렬화 프락시 패턴__ 도입을 고려하라.