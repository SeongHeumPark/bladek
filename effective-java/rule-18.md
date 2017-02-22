## 추상 클래스 대신 인터페이스를 사용하라

### 왜?

- 다양한 구현이 가능한 자료형을 정의하는 가장 좋은 방법
- 유연하고 강력한 API를 만들 수 있음
- 추상 클래스는 자료형으로 사용하는데 많은 제약이 발생 _(ex 다중 상속)_

### __인터페이스__와 __추상 클래스__의 __차이__

- ~~__추상 클래스__는 __구현된 메서드 포함 가능__, __인터페이스__는 __불가능__~~ 
  __Java 1.8__에 `default` 메서드를 통해 구현을 일부 포함 시킬 수 있음
- __추상 클래스__가 규정하는 자료형을 구현하기 위해선 반드시 __계승__해야 함

### 인터페이스 사용의 장점

- __구현__이 __간단__
  이미 있는 클래스를 개조해서 새로운 인터페이스를 구현하기 간단하다.

- __믹스인__을 정의하는데 이상적

- __비 계층적인 자료형 프레임워크__를 만들 수 있도록 함

  ```java
  public interface Singer {
  	AudioClip sing(Song s);
  }

  public interface SongWriter {
  	Song compose(boolean hit);
  }

  public interface SingerSongWriter extends Singer, SongWriter {
  	AudioClip strum();
  	void actSensitive();
  }
  ```

  - 이를 __추상 클래스__로 표현하면 계층이 생김
  - __조합 증폭__
    __필요한 속성이 n개면 지원해야하는 조합 수는 2의 n개__

- __포장 클래스 숙어__를 통해 안전하면서도 강력한 기능 개선이 가능

### 추상 클래스 사용의 장점

- ~~발전시키기 쉽다.~~

  __Java 1.8__에 `default` 메서드로 인해 추상 클래스의 장점이 거의 없다?????

### __추상 골격 클래스__ (abstract skeletal implementation)

- __추상 클래스__와 __인터페이스__의 장점을 결합한 클래스

- __인터페이스__로 자료형 정의

- __추상 클래스__로 구현 _(=골격 구현 클래스)_

- 클래스명 앞에 `Abstract`가 들어감 ___(관습)___

  ```java
  public class MyMap {
  	public interface Entry<K, V> {
  		K getKey();
  		V getValue();
  	}
  }

  // 골격 구현
  public abstract class AbstractMyMapEntry<K, V> implements MyMap.Entry<K, V> {
  	public abstract K getKey();
  	public abstract V getValue();
  }
  ```

- __골격 구현 클래스__는 계승을 위한 클래스이므로 __규칙 17__의 __지침__을 모두 따라야 함

### 인터페이스 설계는 신중히

- 공개되고 널리 구현된 다음 수정이 거의 불가능
- 그러니 애초에 설계를 잘해야 함
- 다양한 방법으로 구현해 볼 것