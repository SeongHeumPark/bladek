## 형 안정 다형성 컨테이너를 쓰면 어떨지 따져보라

### 왜?

- 좀 더 유연하면서 형 안정성을 보장하기 위해

### 다형성 컨테이너

- `Set`이나 `Map`은 하나의 원소만 담는 __컨테이너__에 가장 많이 사용

- __컨테이너__ 대신 __키__를 형 인자로 지정하는 것

- __컨테이너__에 값을 넣거나 뺄 때마다 __키__를 제공하는 것

  ```java
  public class Favorites {
  	public <T> void putFavorite(Class<T> type, T instance);
  	public <T> T getFavorite(Class<T> type);
  }
  ```

