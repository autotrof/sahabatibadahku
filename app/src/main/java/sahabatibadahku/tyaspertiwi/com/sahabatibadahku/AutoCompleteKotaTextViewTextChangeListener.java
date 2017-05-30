package sahabatibadahku.tyaspertiwi.com.sahabatibadahku;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dialogs.DialogSetKota;

/**
 * Created by Agung on 5/30/2017.
 */

public class AutoCompleteKotaTextViewTextChangeListener implements TextWatcher {

    public static final String TAG = "AutoCompleteKotaTextViewTextChangeListener.java";
    Context context;

    public AutoCompleteKotaTextViewTextChangeListener(Context context) {
        this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
