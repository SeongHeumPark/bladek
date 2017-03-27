## 메서드 시그너처는 신중하게 설계하라

### 왜?

- 배우기 쉽고
- 사용하기 쉽고
- 오류 가능성 적고

### API 설계시 팁

- __메서드 이름은 신중하게 고르라.__

  - __Convention__을 따르라.

- __편의 메서드를 제공하는 데 너무 열 올리지 마라.__

  - __pull is weight__
  - 추가할 지 애매하다면 __빼버려라.__

- __인자 리스트를 길게 만들지 마라.__

  - __4개__ 이하 _(없는게 젤 좋지!)_
  - __같은 자료형이 길게 나열된__ 인자는 더 위험하다. _(헷갈림)_

- __인자의 자료형으로는 클래스보다 인터페이스가 좋다.__

  - 특정한 구현에 종속되지 않도록

- __인자 자료형으로 `boolean`을 쓰는 것보다는, 원소가 2개인 `enum` 자료형을 쓰는 것이 났다.__

  - 코드를 읽기 쉬워진다.

    ```java
    public enum TemperatureScale { 
    	FAHRENHEIT, CELSIUS
    }

    // 자동 완성을 둘 째 치고 어느 것이 더 읽기 쉬운가??
    Thermometer.newInstance(TemperatureScale.CELSIUS);
    Thermometer.newInstance(true);
    ```

### 정리

- OK