## 생성자 인자가 많을 때는 Builder 패턴 적용을 고려하라

### 점층적 생성자 패턴
<pre><code>			public class Product {
		private final int code;
		private final String name;
		private final String serial;
		private final String date;

        public Product(int code) {
            this(code, "none");
        }

        public Product(int code, String name) {
            this(code, name, "empty");
        }

        public Product(int code, String name, String serial) {
            this(code, name, serial, "0000-00-00");
        }

        public Product(int code, String name, String serial, String date) {
            this.code = code;
            this.name = name;
            this.serial = serial;
            this.date = date;
        }
}
</code></pre>

- 인자 수가 늘어나면 클라이언트 코드를 작성하기 어려
- 무엇보다 읽기 어려운 코드가 

### 자바빈 패턴
```
public class Product {
    private int code;
    private String name;
    private String serial;
    private String date;

    public Product() { }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
```
- 1회 함수 호출로 객체 생성을 끝낼 수 없음
- 객체 일관성이 일시적으로 깨질 수 있음
- 변경 불가능한 클래스를 만들 수 없음
- 스레드 안정성도 보장할 수 없음

### 빌더 패턴
```
public class Product {
    private final int code;
    private final String name;
    private final String serial;
    private final String date;

    public static class Builder {
        private int code;
        private String name = "none";
        private String serial = "empty";
        private String date = "0000-00-00";

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder serial(String serial) {
            this.serial = serial;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    private Product(Builder builder) {
        code = builder.code;
        name = builder.name;
        serial = builder.serial;
        date = builder.date;
    }
}
```
