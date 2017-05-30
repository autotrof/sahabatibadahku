package sahabatibadahku.tyaspertiwi.com.sahabatibadahku;

import android.content.Context;
import android.widget.AutoCompleteTextView;

/**
 * Created by Agung on 5/30/2017.
 */

public class AutoCompleteKotaTextView extends AutoCompleteTextView {
    public AutoCompleteKotaTextView(Context context) {
        super(context);
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        String filter = "";
        super.performFiltering(text, keyCode);
    }
}
