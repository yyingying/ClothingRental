package clothing_rental.canceline.com.clothingrental.details;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;
import clothing_rental.canceline.com.clothingrental.data_base.Favourite;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
import clothing_rental.canceline.com.clothingrental.data_base.Order;
import clothing_rental.canceline.com.clothingrental.login.ui.LoginActivity0;
import clothing_rental.canceline.com.clothingrental.login.ui.LoginUtil;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by kingShin on 2018/3/14.
 */

@Route(path = "/details/DetailsActivity")
public class DetailsActivity extends BaseActivity {
    private RecyclerView mRecyclerViews;
    private FragmentManager fragmentManager;
    private Button clo_btn;
    private ImageView imageView;
    private Button rental_btn;
    private Boolean pressFlag;
    private String objectID;

    //Goods goods = new Goods();

    @Autowired(name = "Good")
    Goods goods;

    @Autowired(name = "goodsObjectID")
    String goodsObjectID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        clo_btn = findViewById(R.id.closeBtn);
        clo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        BmobQuery<Favourite> favouriteBmobQuery = new BmobQuery<>();
        favouriteBmobQuery.addWhereEqualTo("favouriteID",LoginUtil.getPersonID()+goods.getGoodsID().toString());
        favouriteBmobQuery.findObjects(new FindListener<Favourite>() {
            @Override
            public void done(List<Favourite> list, BmobException e) {
                if (e==null){
                    if(list.size()==0){
                        imageView.setBackgroundResource(R.drawable.un_favourite);
                        pressFlag = false;
                    }
                    imageView.setBackgroundResource(R.drawable.is_favourite);
                    pressFlag = true;
                    objectID = list.get(0).getObjectId();
                }
            }
        });

//        BmobQuery<Order> orderBmobQuery = new BmobQuery<>();
//        orderBmobQuery.addWhereEqualTo("orderID",LoginUtil.getPersonID()+goods.getGoodsID().toString());
//        orderBmobQuery.findObjects(new FindListener<Order>() {
//            @Override
//            public void done(List<Order> list, BmobException e) {
//                if(e==null){
//                    if(list.size()!= 0){
//                        rental_btn.setClickable(false);
//                        rental_btn.setBackgroundResource(R.drawable.btn_rental_unclickable);
//                    }
//                }
//            }
//        });

        imageView = findViewById(R.id.favouriteImageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pressFlag == false){
                    imageView.setBackgroundResource(R.drawable.is_favourite);
                    Favourite favourite = new Favourite();
                    favourite.setFavouriteID(LoginUtil.getPersonID()+goods.getGoodsID().toString());
                    favourite.setPersonID(LoginUtil.getPersonID());
                    favourite.setGoodSID(goods.getGoodsID());
                    favourite.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e==null){
                                Toast.makeText(DetailsActivity.this,"success",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    pressFlag = true;
                }else{
                    imageView.setBackgroundResource(R.drawable.un_favourite);
                    Favourite favourite1 = new Favourite();
                    favourite1.setObjectId(objectID);
                    favourite1.delete(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                Toast.makeText(DetailsActivity.this,"delect.success",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    pressFlag = false;
                }
            }
        });

        rental_btn = findViewById(R.id.rentalButton);
        rental_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/details/rental/RentalActivity")
                        .withParcelable("Good",goods)
                        .withString("goodsObjectID",goodsObjectID)
                        .navigation(getContext());
            }
        });
        //得到控件
        mRecyclerViews = findViewById(R.id.recycle_view);
        //设置布局管理器
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViews.setLayoutManager(layoutManager);
        //设置适配器

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
        mRecyclerViews.setAdapter(delegateAdapter);

        Adapter1 adapter1 = new Adapter1(goods, this);
        delegateAdapter.addAdapter(adapter1);

        Adapter2 adapter2 = new Adapter2(fragmentManager,this);
        delegateAdapter.addAdapter(adapter2);

        Adapter3 adapter3 = new Adapter3(goods,this);
        delegateAdapter.addAdapter(adapter3);

        delegateAdapter.notifyDataSetChanged();
}

    @Override
    protected void onStart() {
        super.onStart();
        BmobQuery<Order> orderBmobQuery = new BmobQuery<>();
        orderBmobQuery.addWhereEqualTo("orderID",LoginUtil.getPersonID()+goods.getGoodsID().toString());
        orderBmobQuery.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
                if(e==null){
                    if(list.size()!= 0){
                        rental_btn.setClickable(false);
                        rental_btn.setBackgroundResource(R.drawable.btn_rental_unclickable);
                    }
                }
            }
        });
    }
}
