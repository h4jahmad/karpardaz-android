/*The MIT License (MIT)
 *Copyright (c) 2017 Hamsaa.ir
 *
 *Permission is hereby granted, free of charge, to any person obtaining a copy
 *of this software and associated documentation files (the "Software"), to deal
 *in the Software without restriction, including without limitation the rights
 *to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 *furnished to do so, subject to the following conditions:
 *
 *The above copyright notice and this permission notice shall be included in all
 *copies or substantial portions of the Software.
 *
 *THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *SOFTWARE.
 * */

package ir.enshaee.karpardaz.view.dialog;


import android.content.Context;

import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class SolarCalendarDialog extends PersianDatePickerDialog {

    private Context mContext;

    public SolarCalendarDialog(Context context) {
        super(context);
        PersianCalendar calendar = new PersianCalendar();
        int[] date = getCurrentDateArray();
        calendar.setPersianDate(date[0], date[1], date[2]);
    }


    public static String getCurrentDate() {
        PersianDate pd = new PersianDate();
        PersianDateFormat pdf = new PersianDateFormat("Y/n/d");

        return pdf.format(pd);
    }

    private static int[] getCurrentDateArray() {
        PersianDate pd = new PersianDate();
        return new int[]{
                Integer.valueOf(new PersianDateFormat("Y").format(pd)),//Get Current Year.
                Integer.valueOf(new PersianDateFormat("n").format(pd)),//Get Current Month Number.
                Integer.valueOf(new PersianDateFormat("j").format(pd))};//Get Current Day Number.
    }

}
