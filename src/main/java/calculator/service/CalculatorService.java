package calculator.service;

import calculator.model.Calculator;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


public class CalculatorService {

    /**
     * 덧셈 로직 서비스 호출
     * @param input input
     * @return result
     */
    public BigInteger calculate(String input) {
        // 문자열 분리
        String [] strArr = sliceInput(input);
        String customStr = strArr[0];
        String intStr = strArr[1];
        // 구분자는 Map으로 관리하는 것이 탐색에 더 빠를 것이라 생각해 Map으로 관리
        Map<String, Boolean> separatorList = getSeparator(customStr);
        // 숫자 문자열 배열 만들기
        List<String> intList = getIntArr(intStr, separatorList);
        // 숫자 합산한 결과 반환하기
        Calculator calculator = new Calculator();
        return calculator.getSum(intList);
    }

    /**
     * input => 구분자 추출, 숫자 추출용으로 구분하기
     * @param input String
     * @return new String[2]
     */
    private String [] sliceInput(String input) {
        if (input.isEmpty()) {
            throw new RuntimeException("분리대상 문자열 비어있음 : " + input);
        } else {
            // 맨 앞이 // 로 시작하지 않으면 잘라내지 않고 그대로 반환
            if (input.startsWith("//") && input.contains("\\n")) {
                int index = input.indexOf("\\n");
                String customStr = input.substring(2, index);
                String intStr = input.substring(index + 2);
                return new String[]{customStr, intStr};
            } else {
                // 위 조건이 해당되지 않을 경우 기본 구분자로만 덧셈 연산 활용
                return new String[]{"", input};
            }
        }
    }

    /**
     * input => 커스텀 문자 등록
     * @param customStr String
     * @return Map<String, Boolean> separatorList
     */
    private Map<String, Boolean> getSeparator(String customStr) {
        Map<String, Boolean> separatorList = new HashMap<>();
        // 기본 구분자 등록
        separatorList.put(":", true);
        separatorList.put(",", true);
        if (validCheckSeparator(customStr)) {
            char [] chArr = customStr.toCharArray();
            // 커스텀 구분자 등록
            for (char c : chArr) {
                separatorList.put(String.valueOf(c), true);
            }
        }

        return separatorList;
    }

    /**
     * input => 커스텀 구분자 문자열에서 숫자 유무 검증
     * @param customStr String
     * @return Map<String, Boolean> separatorList
     */
    private Boolean validCheckSeparator(String customStr) {
        return !customStr.isEmpty() && !customStr.matches(".*\\d.*");
    }

    /**
     * input => 숫자 문자열 배열 만들기
     * @param intStr String, Map<String, Boolean> separatorList
     * @return List<String> intArr
     */
    private List<String> getIntArr(String intStr, Map<String, Boolean> separatorList) {
        if (intStr.isEmpty()) {
            throw new RuntimeException("합산할 숫자 문자열 생성을 위한 문자열 공백");
        } else {
            List<String> intList = new ArrayList<>();
            String temp = "";
            // 이전에 구분자가 또 사용되었는지 확인하기 위한 플래그
            boolean beforeState = false;
            for (int i = 0; i < intStr.length(); i++) {
                char ch = intStr.charAt(i);
                if (Character.isDigit(ch)) {
                    temp += String.valueOf(ch);
                    beforeState = false;
                } else if (separatorList.containsKey(String.valueOf(ch)) && !beforeState) {
                    beforeState = true;
                    intList.add(temp);
                    temp = "";
                } else {
                    throw new RuntimeException("구분자 검증 오류 : " + intStr + ", 유효하지 않은 구분자 : " + String.valueOf(ch));
                }
            }
            // 문자열 마지막에 구분자와 숫자 중 무엇이 나올지 알 수 없으므로 숫자가 남았을 경우 추가해주기
            if (temp.matches("-?\\d+(\\.\\d+)?")) intList.add(temp);
            return intList;
        }
    }
}
