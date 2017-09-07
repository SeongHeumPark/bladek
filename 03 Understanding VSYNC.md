## Understanding VSYNC

### 두 가지 개념

- __재생률 (Refresh Rate)__
  - 초당 화면 갱신 속도
  - Hz 단위
- __프레임 속도 (Frame Rate)__
  - GPU가 초당 몇 프레임을 그릴 수 있는지
  - fps 단위

### Refresh Rate vs. Frame Rate

- 재생률과 프레임 속도는 동일하다는 보장이 없음
- __Refresh Rate < Frame Rate__
  - __테어링 현상__이 일어남 -> __더블 버퍼링__을 이용해 해결
  - __더블 버퍼링__
    - Back Buffer, Frame Buffer 두 가지를 이용해 화면 갱신을 동기화
    - __GPU__ —Write—> __Back Buffer__ —Copay—> __Frame Buffer__ —Read—> __Device Display__
    - 여기서 VSYNC는 Back Buffer와 Frame Buffer 사이에 위치
      Back Buffer가 갱신 중이면 Frame Buffer로의 복사를 미룸
- __Refresh Rate > Frame Rate__
  - 같은 프레임을 노출하는 경우가 생김 -> 사용자 눈에는 느려져 보임
  - LAG, JANK, HITCHING이라고 부르며, 개발자의 철천지원수
  - __더블 버퍼링을 쓰고 있는 안드로이드에서는 이 케이스에만 주의하면 됨__