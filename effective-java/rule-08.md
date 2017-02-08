## equals를 재정의할 때는 일반 규약을 따르라

### equals를 재정의하지 않아도 되는 경우

- 객체가 고유할 때
- 논리적 동일성 검사 방법이 있건 없건 상관없을 때
- 상위 클래스에서 재정의한 equals가 하위 클래스에서 사용하기 적당할 때
- 클래스가 private 또는 package-private로 선언되었고, equals 메서드를 호출할 일이 없을 때

### equals를 재정의하는 것이 바람직한 경우

- 클래스가 논리적 동일성의 개념을 지원할 때
- 상위 클래스에서 재정의한 equals가 하위 클래스의 필요를 충족시키지 못할 때

### equals를 재정의할 필요가 없는 경우

- 값 클래스일 때 _(최대 하나의 객체만 가질 수 있는 클래스)_
- __Singleton__, __enum__

### equals가 준수해야 하는 일반 규약 _(동치관계)_
- __반사성__: x.equals(x)는 true
- __대칭성__: x.equals(y)는 y.equals(x)가 true일 때, true
- __추이성__: x.equals(y)가 true이고 y.equals(z)가 true이면, x.equals(z)도 true
- __일관성__: equals를 통해 비교되는 정보에 아무 변화가 없다면 x.equals(y) 결과는 호출 횟수와 관계없이 항상 같음
- __null 아닌 참조 x에 대해서, x.equals(null)은 항상 false__
- 비교하는 값들은 null이 아님

### 반사성
- 나는 나, 너는 너
  일부러 깨기 힘들다. _(내가 내가 아닌?)_

### 대칭성
- CaseInsenitiveString과 String

  ```java
  public final class CaseInsensitiveString {
    private final String s;
    
    public CaseInsensitiveString(String s) {
      if (s == null) {
        throw new NullPointerException();
      }
      this.s = s;
    }
    
    // 대칭성이 깨지는 equals() 함수
    @Override
    public boolean equals(Object o) {
      if (o instanceof CaseInsensitiveString) {
        return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
      }
      
      if (o instanceof String) {
        return s.equalsIgnoreCase((String) o);
      }
      
      return false;
    }
    
    // 대칭성이 깨지지 않는 equals() 함수
    @Override
    public boolean equals(Object s) {
      if (o instanceof CaseInsensitiveString) {
        return ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
      }
      
      return false;
    }
  }
  ```

  선물용으로 포장된 물건과 그냥 물건 같은 느낌 어떻게 행돌할지 예측이 어렵다. (여친 생일인데 물건 주면...)

### 추이성
- x와 y가 같고 y와 z가 같으면 x와 z도 같다.
  명제에서 많이보던 것

- 객체 생성 가능 클래스를 계승하여 새로운 값 컴포넌트를 추가하면 equals 규약을 어기지 않을 수 없다.

- __리스코프 대체 원칙__
  어떤 자료형의 중요한 속성은 하위 자료형에도 그대로 유지되어서...
  그 자료형에도 잘 동작해야 한다는 원칙...

- 자바에서는 `Timestamp`와 `Date` 클래스가 있다. _(둘이 같이 쓰지 말라는 경고가 있음)_

- 문제를 깔끔히 피하는 방법: __View 메서드__

  ```java
  public class ColorPoint {
    private final Point point;
    private final Color color;
    
    ...
    
    /**
     * ColorPoint의 Point 뷰 반환
     */
    public Point asPoint() {
      return point;
    }
    
    @Override
    public boolean equals(Object o) {
      if (!(o instanceof ColorPoint)) {
        return false;
      }
      
      ColorPoint cp = (ColorPoint) o;
      
      return cp.asPoint().equals(point) && cp.color.equals(color);
    }
  }
  ```

### 일관성
- 일단 같다고 판단되면 무조건 같다.
  친자확인? 친자가 확인됐는데 나중에 다를 수 있나?
- __신뢰성이 보장되지 않은 자원들을 비교하는 equals를 구현하는 것은 삼가하라.__
  __URL 클래스__가 그러하다. _(같은 ip라고 같은 결과가 나오는가?)_

### null에 대한 비 동치성
- 말해 뭐하는가.. null이랑 비교해서 같을 수 있는 것은 아무것도 없다.
- `instanceof` 키워드는 null 체크도 해준다. _(클라우드 소스에서 수정할 부분이 있지 않을까?)_

### 훌륭한 equals를 구현하기 위한 지침 _(정리)_

- `==` 연산자를 사용하여 equals의 인자가 자기 자신인지 검사하라.
- `instanceof`를 사용하여 인자의 자료형이 정확한지 검사하라.
- equals의 인자를 정확한 자료형으로 반환하라.
- __중요 필드__ 각각이 인자로 주어진 객체의 해당 필드와 일치하는지 검사하라.
- equals 메서드 구현을 끝냈다면, __대칭성__, __추이성__, __일관성__ 세 속성이 만족되는지 검토하라.

### 주의사항

- equals를 구현할 때는 hashCode도 재정의하라.
- 너무 머리 쓰지 마라.
- equals 메서드 인자를 Object에서 다른 것으로 바꾸지 마라.