package clothing_rental.canceline.com.clothingrental.details;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;

/**
 * Created by kingShin on 2018/3/14.
 */

public class MyDialogFragment extends DialogFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialogfragment_details_1,null);
        return view;
    }
}
