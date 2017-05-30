package sahabatibadahku.tyaspertiwi.com.sahabatibadahku;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.JadwalAdzan;

/**
 * Created by Agung on 5/31/2017.
 */

public class JadwalAdzanAdapter extends ArrayAdapter<JadwalAdzan> {

    private final Context context;
    private JadwalAdzan jadwalAdzan;

    public JadwalAdzanAdapter(Context context, int resource, Context context1, JadwalAdzan jadwalAdzan) {
        super(context, resource);
        this.context = context1;
        this.jadwalAdzan = jadwalAdzan;
    }
}
