package com.app_v1_1.Watchers;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EmailWatcher  implements TextWatcher {

    private Context context;
    private EditText editText;
    public EmailWatcher(Context context, EditText editText) {
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
         boolean ris=false;
            for (int i = 0; i < editable.length(); ++i) {
                char c = editable.toString().charAt(i);
                if (c == '@') {

                    ris = true;
                }
            }

            if(ris==false)
            {
                editText.setError("Invalid Email");
            }


    }

}
