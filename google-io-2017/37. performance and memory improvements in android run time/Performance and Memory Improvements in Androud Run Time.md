## ART Memory Improvements

### Decription

- 작년에 ART에 대한 설명은 시작에 불과
- 주요 부품(?)만 로드되게 함으로써 더 빠르게 앱이 로드되고 더 적은 메모리를 앱이 사용하도록 함
- 더 적은 메모리를 앱이 사용한다고 하는 것은 아래와 같음
  - __reclaimed time__, __allocated time__이 단축됨
- JIT를 사용하면 훨씬 빠르게 실행되도록 할 수 있음

### Profile-guided dex file layout

![dex_files](images/dex_files.png)

- Dex 파일을 통해 많은 정보를 얻게됨 (클래스 계층 구조 등)
- 이 Dex 파일은 ART에 의해 메모리에 로드됨
- 이 파일을 로드하는 데는 RAM과 시작 시간 비용이 듦

![dex_file_page_accesses_1](images/dex_file_page_accesses_1.png)

- 메모리는 Dex 파일을 읽어드려 위처럼 수천 페이지로 보이게 됨
- 메가단위의 정보가 메모리에 로드됨

![dex_file_page_accesses_1](images/dex_file_page_accesses_2.png)

- 같은 페이지 안에는 일부 중요하지 않는 것도 있음
  - 일부 함수만 사용(?)
  - 사용되지 않는 문자열
- Android N에서 ART는 __프로파일 기반__의 컴파일이 도입
- Android O에서는 이 부분을 좀 더 영리하게 사용함
- [Dex 파일의 Locality(?)]()를 개선하고 있는데,
  아이디어는 __JIT 프로파일을 사용__하여 중요한 데이터를 함께 이동시킴

![dex_file_page_accesses_3](images/dex_file_page_accesses_3.png)

- 중요한 함수를 중요하지 않은 함수와 함께 둠
- 이러한 결과로 페이지 수가 많이 줄어듦 (특히 함수 부분)

![dex_file_ram_usage_after_launch](images/dex_file_ram_usage_after_launch.png)

- 상당한 메모리 사용율 감소가 있었음

### New Garbage Collector 

- [Android GC에 대한 설명](https://www.youtube.com/watch?v=pzfzz50W5Uo&index=14&list=PLq_uWB0WqY0tVIrFZ41Wr5BZc9B_wf-XW) - Android Performance Patterns

![concurrent_compaction](images/concurrent_compaction.png)

![gc_process](images/gc_process.png)

- 새 GC는 Region-Based임
- GC하는 부분은 세 가지 Phase로 나뉨
  - Pause Phase
    - Pause Phase는 모든 쓰레드들을 멈춤(?)
    - 대피할 영역(Source Region)을 확인
    - 모든 쓰레드들을 깨움(?)
  - Copying Phase
    - GC Phase 중 가장 큰 부분을 차지
    - Source regions에서 Destination regions으로 복사함
  - Reclaim Phase
    - Source regions에 대한 RAM을 해제함

![pause_phase](images/pause_phase.png)

- 대피할 곳(Source Region)을 확인(선정)
- 대피의 목적은 높은 분열 지역(region with high fragmentation) 밖으로 복사하기 위한 것
- 위에 예제에서는 중앙의 두 영역을 선택함 (20%로(이하로?) 분열되었기 때문)

![copying_phase_1](images/copying_phase_1.png)

- Source region의 객체는 참조되지 않으며 Destination region으로 복사함
- Source region의 객체는 Destination region의 객체의 주소를 가리킴 (GC Reference Update)

![copying_phase_2](images/copying_phase_2.png)

- 앱은 동시에 여러 스레드 구조를 가질 수 있음
- 때문에 GC가 실행되는 동안에 이 스레드들을 멈추지 않는 방법이 필요
  (반대로 말하자면, GC 중인 객체를 참조하는 것을 막을 방법이 필요)
- 그래서 나온 테크닉이 __Read Barrier__

![copying_phase_3](images/copying_phase_3.png)

- __Read Barrier__는 다른 스레드가 Source Region의 객체를 읽으려 할 때,  해당 읽기를 가로챔
- 위 그림은 A Thread가 bar 객체를 참조하려 함

![copying_phase_4](images/copying_phase_4.png)

- 가로챈 읽기를 Destination region의 복사된 객체의 주소를 참조하도록 함
- Source region에서 더이상 복사할 객체 없을 때 까지 반복

![reclaim_phase](images/reclaim_phase.png)

- Source Region에서 더이상 복사할 내용이 없으면 Reclaim Phase로 돌입
- 이런 상황이 되면 GC는 Source Region의 RAM을 비울 수 있게 됨

![collection_is_finished](images/collection_is_finished.png)

- GC 끝

![gc_pause_time](images/gc_pause_time.png)

- 이런 새로운 GC로 인해 많은 GC Pause 시간을 줄일 수 있었음 (즉, 성능 향상)

![system_wide_ram_benefits](images/system_wide_ram_benefits.png)

### Android Performance Case Study: Sheets

- [Google Sheets 앱](https://play.google.com/store/apps/details?id=com.google.android.apps.docs.editors.sheets)을 예제로 Nougat에서 Oreo로 오면서 얼마만큼 성능이 향상되었는지 지표로 보여줌

![android_performance_case_study](images/android_performance_case_study.png)

- New Garbage Collector
- Inliner improvements
- Code Sinking
- Class Hierachy Analysis

### Loop Optimizations

![loop_optimizations](images/loop_optimizations.png)

- 프로그램 대부분은 루프에서 시간을 소비하는 경향이 있다고 함
- 안드로이드에서는 이 부분의 시간을 줄이려고 분석하고 최적화했다고 함

![benchmark_performance](images/benchmark_performance.png)

- Nougat에 비해 향상된 속도를 보여줌
- 다음 부턴 선형대수와 관련된 얘기들이 나옴... (pass...)

![induction_variable_simplification](images/induction_variable_simplification.png)

![loop_unrolling](images/loop_unrolling.png)

![BCE](images/BCE.png)

![vectorization](images/vectorization.png)

- 위와 같이 네 가지 정도의 최적화 기법에 대해서 설명함
- 자세한 설명은 영상 참고를 부탁드림...