package unittest;

import drawevent.GameType;
import org.junit.Assert;
import validator.Validator;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * Unittest for Validator class
 */
public class ValidatorTest
{
    @org.junit.Test
    public void checkYear_GameTypeOTOS_InputValidYear_ResultTrue() throws Exception
    {
        String actualInput = "2001";
        boolean result = Validator.checkYear(actualInput, GameType.OTOS);
        Assert.assertTrue(result);
    }

    @org.junit.Test
    public void checkYear_GameTypeHATOS_InputValidYear_ResultTrue() throws Exception
    {
        String actualInput = "2005";
        boolean result = Validator.checkYear(actualInput, GameType.HATOS);
        Assert.assertTrue(result);
    }

    @org.junit.Test
    public void checkYear_GameTypeSKANDI_InputValidYear_ResultTrue() throws Exception
    {
        String actualInput = "2010";
        boolean result = Validator.checkYear(actualInput, GameType.SKANDI);
        Assert.assertTrue(result);
    }

    @org.junit.Test
    public void checkYear_GameTypeOTOS_InputNotANumber_ResultFalse() throws Exception
    {
        String actualInput = "abcd";
        boolean result = Validator.checkYear(actualInput, GameType.OTOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkYear_GameTypeOTOS_InputInvalidVYear_ResultFalse() throws Exception
    {
        String actualInput = "1942";
        boolean result = Validator.checkYear(actualInput, GameType.OTOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkYear_GameTypeHATOS_InputInvalidVYear_ResultFalse() throws Exception
    {
        String actualInput = "1986";
        boolean result = Validator.checkYear(actualInput, GameType.HATOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkYear_GameTypeSKANDI_InputInvalidVYear_ResultFalse() throws Exception
    {
        String actualInput = "1997";
        boolean result = Validator.checkYear(actualInput, GameType.SKANDI);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkYear_GameTypeOTOS_InputPastCurrentYear_ResultFalse() throws Exception
    {
        String actualInput = "2021";
        boolean result = Validator.checkYear(actualInput, GameType.OTOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkYear_GameTypeHATOS_InputPastCurrentYear_ResultFalse() throws Exception
    {
        String actualInput = "2017";
        boolean result = Validator.checkYear(actualInput, GameType.HATOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkYear_GameTypeSKANDI_InputPastCurrentYear_ResultFalse() throws Exception
    {
        String actualInput = "2024";
        boolean result = Validator.checkYear(actualInput, GameType.SKANDI);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkWeekNumber_InputValidNumber_ReturnTrue() throws Exception
    {
        String actualInput = "33";
        boolean result = Validator.checkWeekNumber(actualInput);
        Assert.assertTrue(result);
    }

    @org.junit.Test
    public void checkWeekNumber_InputLesserThanMinNumber_ReturnFalse() throws Exception
    {
        String actualInput = "-1";
        boolean result = Validator.checkWeekNumber(actualInput);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkWeekNumber_InputPastMaxNumber_ReturnFalse() throws Exception
    {
        String actualInput = "55";
        boolean result = Validator.checkWeekNumber(actualInput);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkWeekNumber_InputNotANumber_ReturnFalse() throws Exception
    {
        String actualInput = "asd";
        boolean result = Validator.checkWeekNumber(actualInput);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkNumbers_GameTypeOTOS_InputValidNumbers_ReturnTrue() throws Exception
    {
        byte[] numbers = new byte[] {1, 31, 11, 49, 75};
        boolean result = Validator.checkNumbers(numbers, GameType.OTOS);
        Assert.assertTrue(result);
    }

    @org.junit.Test
    public void checkNumbers_GameTypeOTOS_InputOneInvalidNumber_ReturnFalse() throws Exception
    {
        byte[] numbers = new byte[] {1, 31, 11, 99, 75};
        boolean result = Validator.checkNumbers(numbers, GameType.OTOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkNumbers_GameTypeOTOS_InputTwoInvalidNumbers_ReturnFalse() throws Exception
    {
        byte[] numbers = new byte[] {-21, 31, 101, 49, 75};
        boolean result = Validator.checkNumbers(numbers, GameType.OTOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkNumbers_GameTypeOTOS_InputMoreNumbersThanExpected_ReturnFalse() throws Exception
    {
        byte[] numbers = new byte[] {1, 31, 11, 49, 75, 44};
        boolean result = Validator.checkNumbers(numbers, GameType.OTOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkNumbers_GameTypeOTOS_InputLessNumbersThanExpected_ReturnFalse() throws Exception
    {
        byte[] numbers = new byte[] {1, 31, 11, 44};
        boolean result = Validator.checkNumbers(numbers, GameType.OTOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkNumbers_GameTypeHATOS_InputValidNumbers_ReturnTrue() throws Exception
    {
        byte[] numbers = new byte[] {1, 31, 13, 42, 24, 9, 0};
        boolean result = Validator.checkNumbers(numbers, GameType.HATOS);
        Assert.assertTrue(result);
    }

    @org.junit.Test
    public void checkNumbers_GameTypeHATOS_InputOneInvalidNumber_ReturnFalse() throws Exception
    {
        byte[] numbers = new byte[] {1, 21, 13, 42, 75, 13, 0};
        boolean result = Validator.checkNumbers(numbers, GameType.HATOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkNumbers_GameTypeHATOS_InputTwoInvalidNumbers_ReturnFalse() throws Exception
    {
        byte[] numbers = new byte[] {-21, 31, 101, 29, 15, 7, 6};
        boolean result = Validator.checkNumbers(numbers, GameType.HATOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkNumbers_GameTypeHATOS_InputMoreNumbersThanExpected_ReturnFalse() throws Exception
    {
        byte[] numbers = new byte[] {1, 31, 11, 41, 42, 44, 13, 8};
        boolean result = Validator.checkNumbers(numbers, GameType.HATOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkNumbers_GameTypeHATOS_InputLessNumbersThanExpected_ReturnFalse() throws Exception
    {
        byte[] numbers = new byte[] {1, 31, 11, 44};
        boolean result = Validator.checkNumbers(numbers, GameType.OTOS);
        Assert.assertFalse(result);
    }

    @org.junit.Test
    public void checkForNoRepeat_InputNumbersWithNoRepeat_ReturnTrue() throws Exception
    {
        byte[] numbers = new byte[] {1, 31, 11, 44, 73};
        boolean result = Validator.checkForNoRepeat(numbers);
        Assert.assertTrue(result);
    }

    @org.junit.Test
    public void checkForNoRepeat_InputNumbersWithRepeat_ReturnFalse() throws Exception
    {
        byte[] numbers = new byte[] {1, 31, 31, 24, 53};
        boolean result = Validator.checkForNoRepeat(numbers);
        Assert.assertFalse(result);
    }

}