package com.staffApp.Watchers;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class AgeWatcher  implements TextWatcher {

    private Context context;
    private EditText editText;
    public AgeWatcher(Context context) {
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
            for (int i = 0; i < editable.length(); ++i) {
                char c = editable.toString().charAt(i);
                if (Character.isDigit(c) == false) {
                    Toast.makeText(context, "insert only numbers", Toast.LENGTH_SHORT).show();
                }
            }
            int n = Integer.parseInt(editable.toString());
            System.out.println(n);
            if (n < 0 || n > 110) {

                editText.setError("Invalid Age");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
