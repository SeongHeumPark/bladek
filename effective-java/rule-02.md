## 생성자 인자가 많을 때는 Builder 패턴 적용을 고려하라

### 점층적 생성자 패턴
```
public class NutritionFacts {
  private final int servingSize;    // (mL)             필수
  private final int servings;       // (per container)  필수
  private final int calories;       //                  선택
  private final int fat;            // (g)              선택
  private final int sodium;         // (mg)             선택
  private final int carbohydrate    // (g)              선택
  
  public NutritionFacts(int servingSize, int servings) {
    this(servingSize, servings, 0);
  }
  
  public NutritionFacts(int servingSize, int servings, int calories) {
    this(servingSize, servings, calories, 0);
  }
  
  public NutritionFacts(int servingSize, int servings, int calories, int fat) {
    this(servingSize, servings, calories, fat, 0);
  }
  
  public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
    this(servingSize, servings, calories, fat, sodium, 0);
  }
  
  public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
    this.servingSize = servingSize;
    this.servings = servings;
    this.calories = calories;
    this.fat = fat;
    this.sodium = sodium;
    this.carbohydrate = carbohydrate;
  }
}
```
- 인자 수가 늘어나면 클라이언트 코드를 작성하기 어려
- 무엇보다 읽기 어려운 코드가 

### 자바빈 패턴
```
public class NutritionFacts {
  private int servingSize = -1;
  private int servings = -1;
  private int calories = 0;
  private int fat = 0;
  private int sodium = 0;
  private int carbohydrate = 0;
  
  public NutritionFacts() { }
  
  public void setServingSize(int val) { servingSize = val; }
  public void setServings(int val) { servings = val; }
  public void setCalories(int val) { calories = val; }
  public void setFat(int val) { fat = val; }
  public void setSodium(int val) { sodium = val; }
  public void setCarbohydrate(int val) { carbohydrate = val; }
}
```
- 1회 함수 호출로 객체 생성을 끝낼 수 없음
- 객체 일관성이 일시적으로 깨질 수 있음
- 변경 불가능한 클래스를 만들 수 없음
- 스레드 안정성도 보장할 수 없음

### 빌더 패턴
```
public class NutritionFacts {
  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;
  
  public static class Builder {
    private final int servingSize;
    private final int servings;
    private final int calories = 0;
    private final int fat = 0;
    private final int sodium = 0;
    private final int carbohydrate = 0;
    
    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }
    
    public Builder calories(int val) {
      calroies = val;
      return this;
    }
    
    public Builder fat(int val) {
      fat = val;
      return this;
    }
    
    public Builder sodium(int val) {
      sodium = val;
      return this;
    }
    
    public Builder carbohydrate(int val) {
      carbohydrate = val;
      return this;
    }
    
    public NutritionFacts build() {
      return new NutritionFacts(this);
    }
  }
  
  private NutritionFacts(Builder builder) {
    servingSize = builder.servingSize;
    servings = builder.servings;
    carlories = builder.calories;
    fat = builder.fat;
    sodium = builder.sodium;
    carbohydrate = builder.carbohydrate;
  }
}
```
