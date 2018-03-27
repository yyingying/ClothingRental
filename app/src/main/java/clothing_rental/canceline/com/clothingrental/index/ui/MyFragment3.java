package clothing_rental.canceline.com.clothingrental.index.ui;


import android.app.AlertDialog;
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
import clothing_rental.canceline.com.clothingrental.login.ui.LoginUtil;
import clothing_rental.canceline.com.clothingrental.mine.CacheActivity;
import clothing_rental.canceline.com.clothingrental.mine.FavouriteActivity;

/**
 * Created by kingShin on 2018/2/7.
 */

public class MyFragment3 extends Fragment {
    private Button btn_openFavourite;
    private Button btn_openOrders;
    private Button btn_openMessage;
    private Button btn_passwordModify;
    private Button btn_openAddressManager;
    private Button btn_openQuestion;
    private Button btn_exitID;
    private TextView to_login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        if(LoginUtil.isLogin()==false){
            to_login = view.findViewById(R.id.toLoginTextView);
            to_login.setVisibility(View.VISIBLE);
            to_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Router.navTo("/login/input");
                }
            });
        }

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

        btn_passwordModify = view.findViewById(R.id.password_modify_btn);
        btn_passwordModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginUtil.isLogin()==false){
                    showdialogVisitor(view);
                }else {
                    Router.navTo("/login/passwordModifyActivity");
                }
            }
        });

        btn_openAddressManager = view.findViewById(R.id.addressMan_btn);
        btn_openAddressManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginUtil.isLogin()==false){
                    showdialogVisitor(view);
                }else {
                    Router.navTo("/mine/AdressManagementActivity");
                }
            }
        });

        btn_openQuestion = view.findViewById(R.id.question_btn);
        btn_openQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/mine/Question");
            }
        });

        btn_exitID = view.findViewById(R.id.exitID_btn);
        btn_exitID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginUtil.isLogin()==false){
                    showdialogVisitor(view);
                } else {
                    showdialog(view);
                }
            }
        });
        return view;
    }

    public void showdialog(View view){
        AlertDialog.Builder alertdialogbuider = new AlertDialog.Builder(getContext());
        alertdialogbuider.setMessage("您确定要退出账号？");
        alertdialogbuider.setPositiveButton("确定",click1);
        alertdialogbuider.setNegativeButton("取消",click2);
        AlertDialog alertDialog1 = alertdialogbuider.create();
        alertDialog1.show();
    }

    public void showdialogVisitor(View view){
        AlertDialog.Builder alertdialogbuider = new AlertDialog.Builder(getContext());
        alertdialogbuider.setMessage("租赁商品前需要登录，是否跳转到登录界面？");
        alertdialogbuider.setPositiveButton("确定",click_visitor);
        alertdialogbuider.setNegativeButton("取消",click2);
        AlertDialog alertDialog1 = alertdialogbuider.create();
        alertDialog1.show();
    }

    private DialogInterface.OnClickListener click1 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            LoginUtil.setState(false);
            LoginUtil.setPerson(null);
            CacheActivity.finishActivity();
            Router.navTo("/login/input");
        }
    };
    private DialogInterface.OnClickListener click2 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    };

    private DialogInterface.OnClickListener click_visitor = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            CacheActivity.finishActivity();
            Router.navTo("/login/input");
        }
    };
}



