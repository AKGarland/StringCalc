import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringCalculatorTest {

    @Test
    public void returnsTwoWhenOnePlusOne() {
        assertThat(calculate("1+1"), is(2));
    }

    @Test
    public void returnsThreeWhenOnePlusTwo() {
        assertThat(calculate("1+2"), is(3));
    }

    @Test
    public void returnsFourWhenOnePlusThree() {
        assertThat(calculate("1+3"), is(4));
    }

    @Test
    public void returnsOneWhenTwoMinusOne() {
        assertThat(calculate("2-1"), is(1));
    }

    @Test
    public void returnsTwoWhenTwoDividedByOne() {
        assertThat(calculate("2/1"), is(2));
    }

    @Test
    public void returnsThreeWhenTwelveDividedByFour() {
        assertThat(calculate("12/4"), is(3));
    }

    @Test
    public void returnsFourWhenTwoTimesTwo() {
        assertThat(calculate("2*2"), is(4));
    }

    @Test
    public void returnsThirtyWhenThreeTimesTen() {
        assertThat(calculate("3*10"), is(30));
    }

    @Test
    public void returnsErrorWhenUnrecognisedSymbolIsUsed() {
        boolean thrown = false;

        try {
            calculate("2,3");
        } catch (ExceptionHasMessage e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void returnsSixWhenOnePlusTwoPlusThree() {
        assertThat(calculate("1+2+3"), is(6));
    }

    @Test
    public void returnsSixWhenOnePlusSevenMinusTwo() {
        assertThat(calculate("1+7-2"), is(6));
    }

    @Test
    public void returnsSixWhenTwelveMinusThreeTimesTwo() { assertThat(calculate("12-3*2"), is(6)); }

    private int calculate(String calculation) {
        int result = 0;

        String[] numbers = calculation.split("\\+|-|\\*|/");
        String[] operators = calculation.split("(?<=[0-9])");

        if (calculation.contains("+") || calculation.contains("-")) {
            result += Integer.parseInt(numbers[0]);

            for (int i = 0; i < operators.length; i++) {
                if (operators[i].contains("+")) {
                    result += Integer.parseInt(numbers[i]);
                    System.out.println(numbers[i]);
                } else if (operators[i].contains("-")) {
                    result -= Integer.parseInt(numbers[i]);
                }
            }
        }

        if (calculation.contains("/")) {
            String[] numbersAsString = calculation.split("/");
            result = Integer.parseInt((numbersAsString[0])) / Integer.parseInt(numbersAsString[1]);
        } else if (calculation.contains("*")) {
            String[] numbersAsString = calculation.split("\\*");
            result = Integer.parseInt(numbersAsString[0]) * Integer.parseInt(numbersAsString[1]);
        } else {
            ExceptionHasMessage something;
        }

        return result;
    }
}