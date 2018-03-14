package clothing_rental.canceline.com.clothingrental.details;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import clothing_rental.canceline.com.clothingrental.R;

/**
 * Created by kingShin on 2018/3/14.
 */

public class MyDialogFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialogfragment_details_1,null);
        return view;
    }
}
