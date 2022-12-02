package com.example_slide.Watchers;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DateWatcher implements TextWatcher {

    private Context context;
    private EditText editText;
    public DateWatcher(Context context, EditText editText) {
        this.context=context;
        this.editText=editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        try {
            DateFormat format=DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
            Date formatted=(Date) format.parse(editable.toString());
            System.out.println(formatted);
            if(formatted == null)
            {
                editText.setError("Invalid Date");
            }


        } catch (ParseException e) {

            editText.setError("Invalid Date");
        }
    }
}
