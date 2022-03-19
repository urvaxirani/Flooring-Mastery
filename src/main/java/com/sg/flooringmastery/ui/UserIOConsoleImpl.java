/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author urvax
 */
public class UserIOConsoleImpl implements UserIO{
    
     Scanner scan = new Scanner(System.in);

    @Override
    public void print(String message) {

        System.out.println(message);

    }

    @Override
    public String readString(String prompt) {

        System.out.println(prompt);
        return scan.nextLine();

    }

    @Override
    public int readInt(String prompt) {

        System.out.println(prompt);
        return Integer.parseInt(scan.nextLine());

    }

    @Override
    public int readInt(String prompt, int min, int max) {

        int userInt;

        do {

            System.out.println(prompt + " Between " + min + " And " + max);
            userInt = Integer.parseInt(scan.nextLine());

        } while (userInt < min || userInt > max);
        return userInt;

    }

    @Override
    public double readDouble(String prompt) {

        System.out.println(prompt);
        return Double.parseDouble(scan.nextLine());

    }

    @Override
    public double readDouble(String prompt, double min, double max) {

        Double userDouble;

        do {

            System.out.println(prompt + " Between " + min + " And " + max);
            userDouble = Double.parseDouble(scan.nextLine());

        } while (userDouble < min || userDouble > max);
        return userDouble;

    }

    @Override
    public float readFloat(String prompt) {

        System.out.println(prompt);
        return Float.parseFloat(scan.nextLine());

    }

    @Override
    public float readFloat(String prompt, float min, float max) {

        Float userFloat;

        do {

            System.out.println(prompt + " Between " + min + " And " + max);
            userFloat = Float.parseFloat(scan.nextLine());

        } while (userFloat < min || userFloat > max);
        return userFloat;

    }

    @Override
    public long readLong(String prompt) {

        System.out.println(prompt);
        return Long.parseLong(scan.nextLine());

    }

    @Override
    public long readLong(String prompt, long min, long max) {

        Long userLong;

        do {

            System.out.println(prompt + " Between " + min + " And " + max);
            userLong = Long.parseLong(scan.nextLine());

        } while (userLong < min || userLong > max);
        return userLong;

    }

    @Override
    public String readString(String prompt, String min, String max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
         boolean isValid;
        BigDecimal result = null;
        do {
            isValid = true;
            print(prompt);
            try {
                result = new BigDecimal(scan.nextLine()).setScale(2, RoundingMode.HALF_UP);
            } catch (NumberFormatException ex) {
                //print("Please Enter Number:");
                isValid = false;
            }
        } while (!isValid);
        return result;}

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        boolean isValid;
        BigDecimal result = null;
        BigDecimal bdMin = (min).setScale(2, RoundingMode.HALF_UP);
        BigDecimal bdMax = (max).setScale(2, RoundingMode.HALF_UP);
        do {
            isValid = true;
            //String prompt = null;
            print(prompt + " Between $" + bdMin + " And $" + bdMax);
            try {
                result = new BigDecimal(scan.nextLine()).setScale(2, RoundingMode.HALF_UP);
                if (result.compareTo(min) == -1 || result.compareTo(max) == 1) {
                    print("Enter Amount Between $" + bdMin + " And $" + bdMax);
                    isValid = false;
                }
            } catch (NumberFormatException ex) {
                print("Please Enter Number:");
                isValid = false;
            }
        } while (!isValid);
        return result;}
    
    @Override
    public LocalDate readLocalDate(String prompt) {
        boolean invalidInput = true;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = null;
        while (invalidInput) {
            try {
                String inputValue = this.readString(prompt);
                date = LocalDate.parse(inputValue, dateTimeFormatter);
                invalidInput = false;
            } catch (DateTimeParseException e) {
                this.print("ERROR: Please Enter Valid Date");
            }
        }
        return date;
    }
    
}
