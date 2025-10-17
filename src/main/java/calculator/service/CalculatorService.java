package calculator.service;

import calculator.model.Calculator;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
// import java.util.List;
// import java.util.ArrayList;


public class CalculatorService {
    private Calculator calculator = new Calculator();

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
        // 구분자는 Map으로 관리하는 것이 탐색에 더 빠를 것이라 생각해 Map으로 관리
        Map<String, Boolean> separatorList = getSeparator(customStr);
//        List<Integer> intArr = getIntArr(intStr);

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

    /**
     * input => 커스텀 문자 등록
     * @param customArr String
     * @return Map<String, Boolean> separatorList
     */
    private Map<String, Boolean> getSeparator(String customArr) {
        customArr = validCheckSeparator(customArr);
        Map<String, Boolean> separatorList = new HashMap<>();
        // 기본 구분자 등록
        separatorList.put(":", true);
        separatorList.put(",", true);
        if (!customArr.isEmpty()) {
            char [] chArr = customArr.toCharArray();
            // 커스텀 구분자 등록
            for (char c : chArr) {
                separatorList.put(String.valueOf(c), true);
            }
        }

        return separatorList;
    }

    /**
     * input => 커스텀 문자 유효성 검증 후 앞 뒤 자르기 ("//", "\n")
     * @param customArr String
     * @return Map<String, Boolean> separatorList
     */
    private String validCheckSeparator(String customArr) {
        if (customArr.isEmpty()) {
            return customArr;
        } else {
            // 문자열 앞 뒤 검사 후 숫자 있는지 확인하기
            if (customArr.startsWith("//") && customArr.endsWith("\\n") && !customArr.matches(".*\\d.*")) {
                customArr = customArr.substring(2);
                return customArr.substring(0, customArr.length() - 2);
            } else {
                throw new RuntimeException("커스텀 구분자 문자열 형식이 올바르지 않음 : " + customArr);
            }
        }
    }

}
