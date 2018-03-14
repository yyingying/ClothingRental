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
    private Button btn_openOrders;
    private Button btn_openMessage;
    private Button btn_openPledge;
    private Button btn_openCertification;
    private Button btn_openGingeCertification;
    private Button btn_openQuestion;
    private Button btn_openSettings;

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

        btn_openOrders = (Button) view.findViewById(R.id.orders_btn);
        btn_openOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/mine/Orders");
            }
        });

        btn_openMessage = view.findViewById(R.id.message_btn);
        btn_openMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/mine/Message");
            }
        });

        btn_openPledge = view.findViewById(R.id.pledge_btn);
        btn_openPledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/mine/Pledge");
            }
        });

        btn_openCertification = view.findViewById(R.id.certification_btn);
        btn_openCertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/mine/Certification");
            }
        });

        btn_openGingeCertification = view.findViewById(R.id.gingeCertification_btn);
        btn_openGingeCertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/mine/GingeCertification");
            }
        });

        btn_openQuestion = view.findViewById(R.id.question_btn);
        btn_openQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/mine/Question");
            }
        });

        btn_openSettings = view.findViewById(R.id.settings_btn);
        btn_openSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/mine/Settings");
            }
        });

        return view;
    }
}



