package calculator.model;

import java.math.BigInteger;
import java.util.List;

public class Calculator {
    private BigInteger result;

    public Calculator() {
        result = new BigInteger("0");
    }

    public BigInteger getSum(List<String> intList) {
        if (intList.isEmpty()) {
            throw new RuntimeException("합산할 숫자 배열 공백");
        } else {
            intList.forEach(s -> {
                result = result.add(new BigInteger(s));
            });
            return result;
        }
    }
}
