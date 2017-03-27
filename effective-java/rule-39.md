## 필요하다면 방어적 복사본을 만들라

### 왜?

- 안전성 보장

### `Period` 클래스

```java
public class Period {
	private final Date start;
	private final Date end;

	// 방어적 복사본 미적용
	public Period(Date start, Date end) {
    	if (start.compareTo(end) > 0) {
        	throw new IllegalArgumentException(start + " after " + end);
    	}
    
    	this.start = start;
    	this.end = end;
	}

	// 방어적 복사본 미적용
	public Date end() {
    	return this.end.getTime();
	}

	// 방어적 복사본 적용
	public Period(Date start, Date end) {
    	this.start = new Date(start.getTime());
    	this.end = new Date(end.getTime());
    
    	if (this.start.compareTo(this.end) > 0) {
        	throw new IllegalArgumentException(this.start + " after " + this.end);
    	}
	}

	// 방어적 복사본 적용
	public Date end() {
    	return new Date(this.end.getTime());
	}
}

public class Test {
	public static void main(String[] args) {
    	Date start = new Date();
    	Date end = new Date();
    	Period p = new Period(start, end);
    
    	// p 내부 변경 시도
    	end.setYear(78);
    	p.end.setYear(78);
	}
}
```

- 방어적 복사본의 __오버헤드__가 크고 사용자가 __내부 컴포넌트를 부적절하게 변경하지 않는다는 보장__이 있다면,
  __문서__로 남겨라.

### 정리

- 항상 불변식은 깨질 수 있다는 생각을 가지고 프로그래밍 할 것.
- 객체의 __컴포넌트__로는 가능하다면 __변경 불가능 객체__를 사용해야 한다.