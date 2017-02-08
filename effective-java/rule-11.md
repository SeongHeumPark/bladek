## clone을 재정의할 때는 신중하라

### Cloneable
- 어떤 객체의 복제를 허용한다는 사실을 알리는 __믹스인 인터페이스__
- __믹스인 인터페이스__
  + 클래스가 자신의 "본래 타입"에 추가하여 구현할 수 있는 타입
  + 선택 가능한 기능을 제공
  + 그 기능을 제공 받고자 하는 클래스에서 선언
    * 클라우드 소스 중 `DataHomeActionInterface`가 이에 해당


### clone의 일반 규약
- 객체의 복사본을 만들어서 반환한다.
- 이 때, "복사"의 정확한 의미는 클래스마다 다르다.
  1. x.clone() != x __반드시 참__
  2. x.clone().getClass() == x.getClass() __참은 되겠지만, 반드시 그럴 필욘 없음__
  3. x.clone().equals(x) __참은 되겠지만, 반드시 그럴 필욘 없음__
- 객체를 복사하면 보통 새로운 객체가 만들어지는데, 내부 자료구조까지 복사해야 될 수 있다.
- __어떤 생성자도 호출되지 않는다.__

### 주의사항
- 비-final 클래스에 clone을 재정의할 때는 반드시 super.clone을 호출해 얻은 객체를 반환해야 함
- 실질적으로 Cloneable 인터페이스를 구현하는 클래스는 제대로 동작하는 public clone 메서드를 제공해야 함
- 라이브러리가 할 수 있는 일을 클라이언트에게 미루지 말라.
- clone은 또 다른 형태의 생성자다.
  원래 객체를 손상시키는 일이 없도록 해야 하고, 복사본의 불변식도 제대로 만족시켜야 한다.
- clone의 아키텍처는 변경 가능한 객체를 참조하는 final 필드의 일반적 용법과 호환되지 않는다.
  즉, clone을 쓰려면 final 키워드는 쓰면 안된다?

### 현명한 clone 제공 방법
- 복사 생성자
  <pre><code>public Product(Product product)</pre></code>

- 복사 팩터리
  <pre><code>public static Product newInstance(Product product)</pre></code>

### 결론
- 앵간하면 clone을 막는 것이 좋을 것 같다.