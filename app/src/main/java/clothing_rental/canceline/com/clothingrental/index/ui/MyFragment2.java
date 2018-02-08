package clothing_rental.canceline.com.clothingrental.index.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import clothing_rental.canceline.com.clothingrental.R;

/**
 * Created by kingShin on 2018/2/7.
 */

public class MyFragment2 extends Fragment {
    public MyFragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        Log.e("HEHE", "1日狗");
        return view;
    }
}
