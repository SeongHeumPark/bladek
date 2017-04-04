## `for` 문보다는 `for-each` 문을 사용하라.

### 왜?

- 컬렉션 이용시 실수 방지
- 가독성?

### 어디에 버그가 있을까?

```Java
enum Suit {
	CLUB, DIAMONE, HEART, SPADE
}
enum Rank {
	ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}
// ...
Collection<Suit> suits = Arrays.asList(Suit.values());
Collection<Rank> ranks = Arrays.asList(Rank.values());

List<Card> deck = new ArrayList<>();
for (Iterator<Suit> i = suits.iterator(); i.hasNext(); )
	for (Iterator<Rank> j = rank.iterator(); j.hasNext(); )
    	deck.add(new Card(i.next(), j.next()));
```

- `NoSuchElementException` 발생

```Java
// ...
for (Suit suit : suits)
	for (Rank rank : ranks)
    	deck.add(new Card(suit, rank));
```

- `for-each` 문으로 수정
- 얼마나 안전하고 읽기 좋게요~!

### `for` 문을 사용할 수 밖에 없는 상황

- 필터링
  컬렉션을 순회하던 도중 삭제를 해야할 때 __반복자의 `remove()` 함수__를 호출해야 함
- 변환
  순회 중 원소 일부 혹은 전부의 값을 변경해야할 경우, 반복자나 인덱스를 이용해야 함
- 병렬 순회
  여러 컬렉션을 병렬적으로 순회해야 하고, 모든 반복자나 첨자 변수가 발맞춰 나아가도록 구현해야할 경우, 반복자나 인덱스를 이용해야 함

### 정리

- `for` 문을 사용할 수 밖에 없는 상황 외에는 웬만하면 `for-each` 문을 사용하자.

