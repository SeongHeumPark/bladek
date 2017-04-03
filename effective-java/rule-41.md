## 오버로딩할 때는 주의하라

### 왜?

- 어떤 오버로딩된 메서드가 호출될지 모른다.

### `classify()` 오버로딩 메서드

```java
public class CollectionClassifier {
	public static String classify(Set<?> s) {
    	return "Set";
	}

	public static String classify(List<?> lst) {
    	return "List";
	}

	public static String classify(Collection<?> c) {
    	return "Unknown Collection";
	}

	public static void main(String[] args) {
    	Collection<?>[] collections = {
        	new HashSet<String>, new ArrayList<BigInteger>, new HashMap<String, String>().values()
    	};
    
    	for (Collection<?> c : collections) {
        	// 어떤 메서드가 호출될까?
        	System.out.println(classify(c));
    	}
	}
}
```

- 오버로딩된 메서드는 컴파일 시점에 어떤 것이 호출될지 결정된다.

```java
public class CollectionClassifier {
	public static classify(Collection<?> c) {
    	if (c instanceof Set) {
        	return "Set";
    	} else if (c instanceof List) {
        	reutrn "List";
    	} else {
        	return "Unknown Collection";
    	}
	}

	// 이하 동일
}
```

- `instanceof` 키워드를 사용하여 위와 같이 수정하면 정상동작

```java
public class CollectionClassifier {
	public static classifySet(Set<?> s) {
    	return "Set";
	}

	public static classifyList(List<?> lst) {
    	return "List";
	}
  
	public static classifyCollection(Collection<?> c) {
    	return "Unknown Collection";
	}

	public static void main(String[] args) {
    	Collection<?>[] collections = {
        	new HashSet<String>, new ArrayList<BigInteger>, new HashMap<String, String>().values()
    	};
    
    	for (Collection<?> c : collections) {
        	if (c instanceof Set) {
            	System.out.println(classifySet(c));
        	} else if (c instanceof List) {
            	System.out.println(classifyList(c));
        	} else {
            	System.out.println(classifyCollection(c));
        	}
    	}
	}
}
```

- __작명 패턴__을 사용하여 고칠 수도 있다.

### 생성자는?

- 생성자는 __다른 이름을 사용할 수 없다.__
- 해결책
  - __정적 팩터리 메서드__

### 그럼 언제 오버로딩을 사용하나?

- 오버로딩의 인자 __자료형 간 서로 형변환할 수 없다면__ 사용 가능하다.

### 정리

- 같은 수의 인자를 갖는 두 개의 오버로딩 메서드를 API에 포함시키지 마라.
- 오버로딩되어야 한다면 __작명 패턴__을 사용하라.
- 아니면 피하라.
- 피할 수 없다면 오버로딩을 생각하라.