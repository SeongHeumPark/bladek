# Effective Git

## 일시

- 2017.03.28 (화) 14:00 ~ 17:00

## 정리

### OSS Link

- [링크](https://oss.navercorp.com/edu/effective-git)

### 몇가지만 잘해도 됨

### 환경도 전략

- __git reflog__
  최근 작업 내역들을 보고 돌아갈 수 있다. (Android Studio의 Local History 개념)
- __좋은 쉘__을 갖는다.
  - __zsh 추천__
  - git remote
  - git branch --

### git 내부 구조

- svn은 증분 방식, git은 스냅샷 방식으로 저장
- git은 파일(Object)로 관리
- Object의 세 가지 종류
  - commit
    - tree
    - commit 정보
  - tree (디렉토리)
    - 디렉토리 구조
    - hash ID (SHA1)
  - raw object (파일)
    - hash ID 값으로 파일을 저장
- 빈 폴더는 add할 수 없다.
  hash ID를 만들 수 없기 때문



### git clone의 정체

- remote에 있는 파일을 받아오는 것
- 로컬에서는 `cp -r` 명령어

### branch의 정체

- 참조다.
- branch가 늘어나는 것은 참조가 늘어나는 것이다.
- deteched 줄기 어디에 동 떨어져 있는 것

### branch를 어떻게 줄 것인가

- 메인터넌스 브랜치
- 개발 브랜치
- 주제 브랜치
- 그냥 팀에 맞게 잘 하는게 중요하다!
- 어떤 기능 구현을 완료하기 전에 중간 저장을 하고 싶으면 wip: 를 붙여서 커밋하라. (long term으로 개발한 경우)
  이렇게 해서 rebase로 옮길 수 있다?
- 3일에 한 번은 rebase 하라.
  고친 부분이 3줄 이하면 충돌날 수 있다.
  rebase를 자주하면 이런 상황을 줄일 수 있다.

### 커밋 가이드라인

- 타이틀은 50자 이내
- 본문은 자세히지만 72자 이내 (커맨드 한 줄에 들어가는 글자 수)
- 이미 커밋된 파일을 수정하는 방법 (추가) -> 기존에는 되게 복잡했다.)
  git add -u . (현재 저장소에 들어간 파일만 추가?)
  —fix-up
  git commit —autosquash

### 역시 조작하기

- 커밋 쪼개기
  - Hard : 
  - Soft : git add -p

### upstream origin

- origin
  - clone 해온 프로젝트
- upstream
  - upstream branch
    - 내 코드일 가능성이 높다.
  - upstream project
    - 최종적으로 가야할 프로젝트

### git bisect start 시작커밋번호 끝커밋번호

- 한 커밋씩 찾아가며 원인 분석 가능
- 테스트 코드를 이용해서 자동으로 찾아다니게 할 수 있다. (원하는 리턴에 따라 break)