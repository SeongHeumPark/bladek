## 복구 가능 상태에는 점검지정 예외를 사용하고, 프로그래밍 오류에는 실행시점 예외를 이용하라.

### 왜?

- 프로그래머들의 혼란을 막기 위해

### 세 가지 throwable

- __checked exception__
- __runtime exception__
- __error__

### 일반 규칙

- caller 측에서 __복구__할 것으로 여겨지는 상황에 대해서는 __checked exception__을 사용
- __프로그래밍 오류__를 표현할 때는 __runtime exception__을 사용
- 사용자 정의 __unchecked throwable__은 `RuntimeException`의 __하위__로 만들어야 함

### 정리

- 복구 가능하거나 가능할 것 같은 상황: __checked exception__
- 프로그래밍 오류: __runtime exception__
- 잘 모르겠음: __unchecked throwable__ maybe..