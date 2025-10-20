# java-calculator-precourse
🧮 문자열 덧셈 계산기을 구현한다.

### 구현할 기능 목록
- [x] 문자열 파싱
  - 문자열 유효성 검증
  - 숫자 리스트 반환
- [x] 숫자 리스트 덧셈

### 기능 세부 사항
- 입력 문자열의 구분자는 다음과 같은 조건을 만족해야 한다.
  - 구분자를 지정하지 않은 경우 기본 구분자는 쉼표(`,`)와 콜론(`:`)이며, 이를 혼용해 쓸 수 있다.
  - 커스텀 구분자를 지정한 경우 구분자는 한 개의 문자여야 하며, 지정한 문자만 구분자로 사용할 수 있고, 기본 구분자도 사용할 수 없다.
  - 커스텀 구분자를 지정하는 방법은 `//`와 `\n` 사이에 한 개의 문자를 넣으면 된다.
  - 커스텀 구분자로 `.`과 `숫자`를 제외한 모든 문자가 가능하다.
- 입력 문자열의 숫자는 다음과 같은 조건을 만족해야 한다.
  - 양수만 가능하다. 음수는 불가능하다.
  - 소수도 사용 가능하다.
- 입력 문자열이 조건에 맞지 않는 경우 `IllegalArgumentException`이 발생한다.

### 패키지 구조 및 클래스
```text
src.main.java.calculator
⎿ parser
    ⎿ CustomDelimiterParser.java
    ⎿ DefaultDelimiterParser.java
    ⎿ ParserDispatcher.java
⎿ util
    ⎿ Calculator.java
    ⎿ DecimalParser.java
⎿ Application.java
```
`parser` 패키지는 문자열 파싱을 위한 클래스들이 모여있다.
- `CustomDelimiterParser` 클래스
  - 커스텀 구분자 문자열의 유효성을 검증한다.
  - 구분자와 구분자 + 숫자로 이루어진 문자열을 분리하여 `DecimalParser`의 `toBigDecimalList`함수로 처리하여 반환한다.
- `DefaultDelimiterParser` 클래스
  - 기본 구분자를 갖는 형식에 대해 유효성을 검증한다.
  - 구분자와 구분자 + 숫자로 이루어진 문자열을  `DecimalParser`의 `toBigDecimalList`함수로 처리하여 반환한다.
- `ParserDispatcher` 클래스
  - 입력된 문자열을 가장 먼저 받아 처리에 대한 중간 다리 역할을 하는 클래스이다.
  - 해당 문자열을 적절한 클래스(`CustomDelimiterParser` 혹은 `DefaultDelimiterParser`)로 처리하여 반환한다.

`util` 패키지는 변경이 발생하기 어려운 기본적인 기능을 하는 클래스들이 모여있다.
- `Calculator` 클래스
  - 숫자를 입력으로 받아 계산 결과를 반환한다.
  - 숫자 리스트를 입력으로 받아 덧셈을 계산하는 `add` 메서드를 가지고 있다.
- `DecimalParser` 클래스
  - 구분자와 문자열을 입력받으면, 구분자에 맞게 문자열을 분리하여 숫자 리스트로 변환한다.
  - BigDecimal 리스트로 변환하는 `toBigDecimalList` 메서드를 가지고 있다.

`Application`
- 해당 프로그램 전체의 시작점인 `main` 함수를 가지고 있다.
- 입력을 받아 `ParserDispatcher`에 전달하여 숫자 리스트를 결과로 받고,
- 숫자 리스트를 `Calculator`에 전달하여 덧셈 결과를 받아 출력한다.