package com.staffApp.Watchers;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class NameWatcher implements TextWatcher {

    private Context context;
    private EditText editText;
    public NameWatcher(Context context, EditText editText) {
        this.editText=editText;
        this.context=context;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable != null &&  editable.length() != 0) {

          if(editable.toString().matches("[0-9]+"))
          {
              editText.setError("Name must constains letter");
          }
           /* for (int i = 0; i < editable.length(); ++i) {
                char c = editable.toString().charAt(i);
                if (Character.isAlphabetic(c) == false) {
                    editText.setError("Name must constains letter");
                }
            }
*/
        }

    }
}
