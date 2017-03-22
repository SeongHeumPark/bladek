## `int` 상수 대신 `enum`을 사용하라

### 왜?

- 가독성
- 안전
- 강력

### 두 가지 `enum` 패턴

- `int enum` 패턴

  ```Java
  public class Constants {
  	public static final int APPLE_FUJI = 0;
  	public static final int APPLE_PIPPIN = 1;
  	public static final int APPLE_GRANNY_SMITH = 2;

  	public static final int ORANGE_NAVEL = 0;
  	public static final int ORANGE_TEMPLE = 1;
  	public static final int ORANGE_BLOOD = 2;
  }
  ```

  - 장점이 없다.
  - __형 안정성__이나 __편의성__ 관점에서 봤을 때 단점만 있다.
  - 별도의 __namespace__를 제공하지 않는다. (접두어 필요)
  - 프로그램이 깨지기 쉽다.
  - 문자열 변환이 쉽지 않다. (디버깅 했을 때 지옥이 펼쳐진다.)
  - `int enum` 그룹의 크기를 알아내기도 쉽지 않다.

- `String enum` 패턴

  - `int enum` 패턴의 변종

  - 문자열 비교를 하기 때문에 성능이 많이 떨어진다.

  - 사용자가 __하드코딩 된 문자열__을 코드 안에 박아버릴 수 있다.

    ```Java
    public class Constants {
    	public static final String KEY_NAME = "key_name";
    }

    public class Main {
    	public static void main(String[] args) {
        	Map<String, String> map = new HashMap<>();
        	map.add(Constants.KEY_NAME, "박성흠");
        
        	// ...
        
        	System.out.println((String) map.get("key_nema"));
    	}
    }
    ```

  - __하드코딩 된 문자열__에 오타가 있을 경우 컴파일 타임에 오류를 발견할 수 없다.

### ` enum` 자료형

```Java
public enum Apple {
	FUJI, PIPPIN, GRANNY_SMITH
}

public enum Orange {
	NAVEL, TEMPLE, BLOOD
}
```

- `enum` 자료형의 기본적인 __아이디어__
  - `public static final` 필드 형태로 제공
  - 실질적으로 `final`로 선언된 것이나 마찬가지
  - __객체 생성__이나 __계승을 통한 확장__이 불가능
- `enum` 자료형은 자바 문법에 __형 안전 `enum` 패턴__을 적용시켰다.
- 컴파일 시 __형 안전성__을 제공
- __namespace__를 제공하기 때문에 같은 이름의 상수가 존재해도 된다.
- 상수를 추가하거나 순서를 변경해도 다시 컴파일할 필요가 없다. (__격리 계층__ 구실)
- `toString()` 함수가 있어서 문자열 변환도 쉬움

### `enum` 자료형 심화

- 임의의 __메서드__, __필드__ 추가 가능
- 임의의 __인터페이스__ 구현 가능
- `Object`에 정의된 모든 고품질 메서드들이 포함됨
- `Comparable` 인터페이스, `Serializable` 인터페이스가 구현됨

### 풍부한 기능을 갖춘 `enum` 자료형 만들기

- 객체 필드 선언
  __<주의!>__ `enum`은 __변경 불가능__하므로 모든 객체 필드는 `final`로 선언되어야 함
- 생성자 통해 데이터 받고 저장
- 필요한 함수 사용
- __참 쉽죠?__

### 상수별 메서드 구현

- 상수에 따라 제각기 다른 행동을 하는 함수를 만든다고 가정

- `switch` 문을 사용할 수 있지만.. __유지보수__ 측면에서 좋지 않다.

- `abstract` 메소드를 이용한 __상수별 메서드 구현__하자

  ```Java
  public enum Operation {
  	PLUS { 
      	double apply(double x, double y) {
          	return x + y;
      	}
      },
      
      MINUS {
      	double apply(double x, double y) {
          	return x - y;
      	}
      }

  	// 이하 생략

  	abstract double apply(double x, double y);
  }
  ```

- 단점… `enum` 상수끼리 공유하는 코드를 만들기 어려움 (자세한건 책… 참고)
  __`switch` 문__과 __정책 `enum` 패턴__

### `toString()` 재정의 시 유의 사항

- `toString()` 호출 시 뱉어낸 문자열을 다시 `enum`으로 변환하는 `fromString()` 함수를 제공해야 한다.

  ```Java
  public enum Operation {
  	// 생략

  	private static final Map<String, Operation> stringToEnum = new HashMap<>();

  	static {
      	for (Operation op : values())
          	stringToEnum.put(op.toString(), op);
  	}

  	public static Operation fromString(String symbol) {
      	return stringToEnum.get(symbol);
  	}
  }
  ```


### 정리

- 이제 `enum` 자료형으로 바꿉시다.