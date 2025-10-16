package calculator.service;

import calculator.model.Calculator;

import java.math.BigInteger;

public class CalculatorService {
    private final Calculator calculator = new Calculator();

    /**
     * 덧셈 로직 서비스 호출
     * @param input input
     * @return result
     */
    public BigInteger calculate(String input) {
        // 문자열 분리
        String [] strArr = separateInput(input);
        String customStr = strArr[0];
        String intStr = strArr[1];

        // 커스텀 구분자 추출 로직 필요
        // 숫자 합산 로직 필요
//        BigInteger result = getSum(intStr);
        return new BigInteger("0");
    }

    /**
     * input => 구분자 추출, 숫자 추출용으로 구분하기
     * @param input String
     * @return new String[2]
     */
    private String [] separateInput(String input) {
        if (input.isEmpty()) {
            throw new RuntimeException("분리대상 문자열 비어있음 : " + input);
        } else {
            // 첫 번째 숫자가 나오는 위치를 찾음
            int index = findSeparateIndex(input);

            String customArr = input.substring(0, index);
            String intArr = input.substring(index);

            return new String[]{customArr, intArr};
        }
    }

    /**
     * input => 문자열 분리할 인덱스 찾기
     * @param input String
     * @return int index
     */
    private int findSeparateIndex(String input) {
        int index = -1;
        // 처음으로 나오는 숫자 인덱스 지정
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new RuntimeException("문자열에 숫자 미존재 : " + input);
        }
        return index;
    }


}
