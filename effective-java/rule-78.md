## 직렬화된 객체 대신 직렬화 프락시를 고려해 보라

### 왜?

- 버그와 보완 결함이 생길 가능성이 줄어듦

### 직렬화 프락시 패턴

```java
public class Preiod implements Serializable {
	// 생략...

	public Preiod(Date start, Date end) {
    	// ...
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

