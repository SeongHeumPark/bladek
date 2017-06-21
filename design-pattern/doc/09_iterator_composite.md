# 이터레이터와 컴포지트 패턴

## Description

- 디자인 원칙 정리
- 이터레이터 패턴 정리
- 이터레이터 패턴 예제 코드
- 컴포지트 패턴 정리
- 컴포지트 패턴 예제 코드

## 디자인 원칙

### 09. 단일 역할 원칙 

- __한 클래스__에 __한 가지 역할__

### 응집도

- 한 클래스 or 모듈이 __특정 목적__ or __역할__을 얼마나 __일관되게 지원__하는지 나타내는 척도
  낮을수록 여러가지 역할을 지원하는 것.
## 이터레이터 패턴

### 정의

- __책__
  - __컬렉션 내부 구현 방법__을 캡슐화
  - __외부__에서 __모든 인자에 접근 가능__하도록 함
- __나__
  - __반복__을 캡슐화
  - __내부 구현 방법__을 모르지만 __접근 가능__

### `Iterator` 인터페이스

- 코드

  ```java
  public interface Iterator {
  	boolean hasNext();
  	Object next();
  }
  ```

### `java.util.Iterator` 인터페이스

- 코드

  ```java
  public interface Iterator {
  	boolean hasNext();
  	Object next();
  	void remove();
  }
  ```
  - `remove()` 함수를 제공하고 싶지 않다면, `UnsupportedOperationException`을 던지면 됨
  - __Multi-Thread__ 환경에서는 `remove()` 함수를 조심히 구현해야 함
## 컴포지트 패턴

### 정의

- __책__
  - 객체들을 __트리 구조__로 구성
  - __부분__과 __전체__를 나타내는 __계층구조__로 만들 수 있음
  - 개별 객체와 다른 객체들로 구성된 __복합 객체__를 똑같은 방법으로 다룰 수 있음
- __나__
  - __트리 구조__
  - __리프 == 노드__가 될 수 있도록 함
  - A 객체와 B 객체의 __통합객체(=Component)__를 만듦

### `Component`, `Composite`, `Leaf` 클래스

- `Component` 추상화 클래스

  ```java
  public abstract class Component {
  	public void opeartion() {
      	throw new UnsupportedOperationException();
  	}

  	public void add(Component component) {
      	throw new UnsupportedOperationException();
  	}

  	public void remove(Component component) {
      	throw new UnsupportedOperationException();
  	}

  	public Component getChild(int position) {
      	throw new UnsupportedOperationException();
  	}
  }
  ```

  - `Composite`와 `Leaf` 클래스에서 오버라이딩되지 않은 함수들을 사용했을 때,

    `UnsupportedOperationException`을 던짐

- `Composite` 클래스

  ```java
  public class Composite extends Component {
  	@Override
  	public void add(Component component) {
      	// ...
  	}

  	@Override
  	public void remove(Component component) {
      	// ...
  	}

  	@Override
  	public Component getChild(int position) {
      	// ...
  	}

  	@Override
  	public void operation() {
      	// ...
  	}
  }
  ```

- `Leaf` 클래스

  ```java
  public class Leaf extends Component {
  	@Override
  	public void operation() {
      	// ...
  	}
  }
  ```

### 단일 역할 원칙을 깨는 패턴

- __컴포지트 패턴__은 단일 역할 원칙은 깨지만, __투명성__을 확보
  __투명성__: 어떤 원소가 __복합 객체__인지, __리프 노드__인지 투명하게 느낌
- 안정성은 떨어짐
- __컴포지트 패턴__은 __디자인 결정 사항__에 속함
  - __단일 역할 원칙__을 깨지 않으면서도 처리할 순 있음, 그러나 __코드가 복잡해짐__
    __분기문__과 `instanceof` 연산자를 사용이 늘어남

## 정리

- 가이드라인은 가이드라인일 뿐
