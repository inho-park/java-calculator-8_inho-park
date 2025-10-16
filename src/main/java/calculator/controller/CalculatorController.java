package calculator.controller;

import calculator.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.ResultView;

public class CalculatorController {
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final CalculatorService calculatorService = new CalculatorService();

    /**
     * main()에서 호출
     * View 및 덧셈 로직 호출
     */
    public void run() {
        try {
            String input = inputView.getInput();
            resultView.printResult(calculatorService.calculate(input));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
