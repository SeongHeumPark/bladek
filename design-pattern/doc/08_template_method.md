# 템플릿 메소드 패턴

## Description

- 디자인 원칙 정리
- 템플릿 메소드 패턴 정리
- 템플릿 메소드 패턴 예제 코드

## 디자인 원칙

### 08. 헐리우드 원칙

- 먼저 연락하지 마세요. 저희가 연락 드리겠습니다.
- __의존성 부패__ 방지
  - __의존성 부패__: 고수준 요소가 저수준에 의존하고 그 저수준이 고수준을 의존하고 그 고수준이 또 다른 구성요소에 의존하고… 지옥...
- 저수준 요소는 __절대 고수준 요소를 직접 호출할 수 없음__
- __의존성 뒤집기 원칙__과 연관됨
  - __의존성 뒤집기 원칙__: __추상화된 것(고수준)__을 사용할 것
- __템플릿 메소드 패턴__이 해당 원칙에 적합

## 템플릿 메소드

### 정의

- 메소드에서 __알고리즘의 골격(템플릿)__을 정의

- 알고리즘의 여러 단계 중 일부는 __서브 클래스__에서 구현

- __알고리즘 구조는 유지__하면서 __서브 클래스__에서 특정 단계를 __재정의__

- 코드

  ```java
  public abstract class AbstractClass {
  	final void templateMethod() {
      	primitiveOperation1();
      	primitiveOperation2();
      	concreateOperation();
  	}

  	abstract void primitiveOperation1();

  	abstract void primitiveOperation2();

  	final void concreateOperation() {
      	// ...
  	}
  }
  ```



### 후크 메소드

- __추상 클래스__에 들어있는 기본적인 내용 or 아무 동작하지 않는 코드만 있는 메소드

- 코드

  ``` java
  public abstract class AbstractClass {
  	final void templateMethod() {
      	primitiveOperation1();
      	primitiveOperation2();
      	concreateOperation();
      	hook();
  	}
    
  	abstract void primitiveOperation1();
    
  	abstract void primitiveOperation2();

  	final void concreateOperation() {
      	// ...
  	}
    
  	void hook() {
  		// ...
  	}
  }
  ```

- __필요한 서브 클래스만__ 재정의


### 야생의 템플릿 메소드

- `Comparable` 인터페이스의 `compareTo()` 메소드
  - `Arrays` 클래스의 `sort()` 메소드에서 필요
  - `Arrays.sort()` 메소드를 어떤 클래스에서든 사용 가능하도록 설계
  - 어떤 클래스의 __대소관계__가 필요했기에 `Comparable` 인터페이스를 구현하도록 함
  - 자칫, __전략 패턴__으로 보일 수 있음
- `Jframe` 클래스의 `update()` 메소드
  - __후크 메소드__ 존재
- `Applet` 클래스
  - __후크 메소드__ 존재
- __Android__의 `BaseAdapter` 클래스
  - `Adapter` 인터페이스를 구현하여 `getView()` 호출
  - `getView()` 함수는 __서브클래스에서 구현__되되록 함
  - [Adapter.java](https://github.com/android/platform_frameworks_base/blob/master/core/java/android/widget/Adapter.java) [BaseAdapter.java](https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/widget/BaseAdapter.java)


### 템플릿 메소드 패턴 vs. 전략 패턴

- __상속__ vs. __구성__
- __추상 클래스__ vs. __인터페이스__
- 알고리즘에 대한 __의존성__
  - __템플릿 메소드 패턴__ > __전략 패턴__
  - 즉, __전략 패턴__이 더 유연

## 정리

- `interface`로 __템플릿 메소드 패턴을 구현__했다면 __전략 패턴__과 많이 헷갈림
- 두 패턴의 차이점을 잘 알아둘 것
