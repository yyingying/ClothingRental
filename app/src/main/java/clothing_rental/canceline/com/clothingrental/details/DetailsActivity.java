package clothing_rental.canceline.com.clothingrental.details;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
import clothing_rental.canceline.com.clothingrental.login.ui.LoginActivity0;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by kingShin on 2018/3/14.
 */

@Route(path = "/details/DetailsActivity")
public class DetailsActivity extends BaseActivity {
    private RecyclerView mRecyclerViews;
    private Data mDatas;
    private FragmentManager fragmentManager;

    //Goods goods = new Goods();

    @Autowired(name = "Good")
    Goods goods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

//        Toast.makeText(DetailsActivity.this,goods.getName(),Toast.LENGTH_LONG).show();

        //inject
        //初始化数
        //initData("1234",5,1001,"5678",16,1);

//        Toast.makeText(DetailsActivity.this,goods.getName(), Toast.LENGTH_LONG).show();
//        init(goods);
//        Toast.makeText(DetailsActivity.this, goods.getName(), Toast.LENGTH_LONG).show();
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

//    private void initData(String name,Integer Stock,Integer id,String place,Integer price,Integer price2)
//    {
//        goods.setName(name);
//        goods.setStock(Stock);
//        goods.setGoodsID(id);
//        goods.setPlace(place);
//        goods.setPrice(price);
//        goods.setRental_price(price2);
//    }
}
