## 객체 생성을 막을 때는 private 생성자를 사용하라

### 기본 생성자가 자동 생성되지 못하도록 하여 객체 생성 방지
```
public class ProductUtil {
    private ProductUtil() {
        throw new AssertionError();
    }
}
```
