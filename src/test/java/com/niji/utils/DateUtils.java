package com.niji.utils;

import com.niji.pageObjects.DatePicker;
import com.niji.steps.globalSteps.ElementSteps;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    // argument year du type m+x, m-X ou m
    public static String getNewYear(String year){
        SimpleDateFormat format = null;
        if (year.length() == 2){
            format = new SimpleDateFormat("yy");
        } else {
            format = new SimpleDateFormat("yyyy");
        }
        if (year.equals("a")){
            // Année en cours
            Calendar cal = Calendar.getInstance();
            return format.format(cal.getTime());
        } else if (year.contains("a-")){
            // Année en cours - X
            String diff = year.substring(1);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, Integer.parseInt(diff));
            return format.format(cal.getTime());
        } else if (year.contains("a+")){
            // Année en cours + X
            String diff = year.substring(2);
            if (Integer.parseInt(diff) > 15){
                diff = "15";
            }
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, Integer.parseInt(diff));
            return format.format(cal.getTime());
        } else {
            // Sinon l'année a été directement renseignée
            return year;
        }
    }

    public static String getNewMonth(String month){
        if (month.equals("m")){
            // Mois en cours
            Calendar cal = Calendar.getInstance();
            return new SimpleDateFormat("MM").format(cal.getTime());
        } else if (month.contains("m-")){
            // Mois en cours - X
            String diff = month.substring(1);
            Calendar cal = Calendar.getInstance();
            String monthBefore = new SimpleDateFormat("MM").format(cal.getTime());
            cal.add(Calendar.MONTH, Integer.parseInt(diff));
            String monthAfter = new SimpleDateFormat("MM").format(cal.getTime());
            if (Integer.parseInt(monthBefore) < Integer.parseInt(monthAfter)){
                cal = Calendar.getInstance();
                cal.set(Calendar.MONTH, 0);
            }
            return new SimpleDateFormat("MM").format(cal.getTime());
        } else if (month.contains("m+")){
            // Mois en cours + X
            String diff = month.substring(2);
            Calendar cal = Calendar.getInstance();
            String monthBefore = new SimpleDateFormat("MM").format(cal.getTime());
            cal.add(Calendar.MONTH, Integer.parseInt(diff));
            String monthAfter = new SimpleDateFormat("MM").format(cal.getTime());
            if (Integer.parseInt(monthBefore) > Integer.parseInt(monthAfter)){
                cal = Calendar.getInstance();
                cal.set(Calendar.MONTH, 11);
            }
            return new SimpleDateFormat("MM").format(cal.getTime());
        } else {
            return month;
        }
    }

    // argument date = date au format dd/mm/yyyy
    public static String getNewStringDate(int operation, String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE, operation);
        Date d = c.getTime();
        return formatter.format(d);
    }

    public static Date getNewDate(int operation, String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date today = new Date();
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE, operation);
        Date d = c.getTime();
        return d;
    }

    public static void datePickerSelection(String date){
        String[] subDate = date.split("/", -1);
        Select select = new Select(DatePicker.getYearSelectList());
        select.selectByValue(subDate[0]);
        select = new Select(DatePicker.getMonthSelectList());
        switch (subDate[1]){
            case "1":
            case "01":
                select.selectByValue("1"); break;
            case "2":
            case "02":
                select.selectByValue("2"); break;
            case "3":
            case "03":
                select.selectByValue("3"); break;
            case "4":
            case "04":
                select.selectByValue("4"); break;
            case "5":
            case "05":
                select.selectByValue("5"); break;
            case "6":
            case "06":
                select.selectByValue("6"); break;
            case "7":
            case "07":
                select.selectByValue("7"); break;
            case "8":
            case "08":
                select.selectByValue("8"); break;
            case "9":
            case "09":
                select.selectByValue("9"); break;
            case "10":
                select.selectByValue("10"); break;
            case "11":
                select.selectByValue("11"); break;
            case "12":
                select.selectByValue("12"); break;
        }
        if (subDate[2].startsWith("0")) {
            ElementSteps.makeVisible(DatePicker.getActiveDayButton(subDate[2].substring(1)), true);
            DatePicker.getActiveDayButton(subDate[2].substring(1)).click();
        }
        else {
            ElementSteps.makeVisible(DatePicker.getActiveDayButton(subDate[2]), true);
            DatePicker.getActiveDayButton(subDate[2]).click();
        }
    }

    public static String getDateOnExpectedFormat(String[] date){
        // Param String is a table with three entries : MMM, dd and YYYY
        // In this case, the day is on the first entry
        if(date[0].length() < 3){
            // Switch month and day
            String day = date[0];
            String month = date[1];
            date[0] = month;
            date[1] = day;
        }
        // Result format : YYYY/MM/DD
        String result = "";
        //Year
        result = result + date[2];
        //Month
        switch (date[0]){
            case "Jan":
                result = result + "/01/"; break;
            case "Feb":
                result = result + "/02/"; break;
            case "Mar":
                result = result + "/03/"; break;
            case "Apr":
                result = result + "/04/"; break;
            case "May":
                result = result + "/05/"; break;
            case "Jun":
                result = result + "/06/"; break;
            case "Jul":
                result = result + "/07/"; break;
            case "Aug":
                result = result + "/08/"; break;
            case "Sep":
                result = result + "/09/"; break;
            case "Oct":
                result = result + "/10/"; break;
            case "Nov":
                result = result + "/11/"; break;
            case "Dec":
                result = result + "/12/"; break;
        }
        //Day
        result = result + date[1].replaceAll(",","");

        return result;
    }
}
