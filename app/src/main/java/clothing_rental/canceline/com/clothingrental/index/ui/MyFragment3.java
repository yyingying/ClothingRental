package clothing_rental.canceline.com.clothingrental.index.ui;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;
import clothing_rental.canceline.com.clothingrental.mine.FavouriteActivity;

/**
 * Created by kingShin on 2018/2/7.
 */

public class MyFragment3 extends Fragment {
    private Button btn_openFavourite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        btn_openFavourite = (Button) view.findViewById(R.id.like_btn);
        btn_openFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/mine/Favourite");
            }
        });
        return view;
    }
}



