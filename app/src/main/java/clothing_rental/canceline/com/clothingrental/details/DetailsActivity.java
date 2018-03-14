package clothing_rental.canceline.com.clothingrental.details;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;

/**
 * Created by kingShin on 2018/3/14.
 */

public class DetailsActivity extends BaseActivity {
    private RecyclerView mRecyclerViews;
    private Data mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //初始化数据
        initData();
        //得到控件
        mRecyclerViews = findViewById(R.id.recycle_view);
        //设置布局管理器
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViews.setLayoutManager(layoutManager);
        //设置适配器

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
        mRecyclerViews.setAdapter(delegateAdapter);

        Adapter1 adapter1 = new Adapter1(mDatas, this);
        delegateAdapter.addAdapter(adapter1);

        Adapter2 adapter2 = new Adapter2(this);
        delegateAdapter.addAdapter(adapter2);

        Adapter3 adapter3 = new Adapter3(mDatas,this);
        delegateAdapter.addAdapter(adapter3);

        delegateAdapter.notifyDataSetChanged();
    }

    private void initData() {
        //从云存储上获取数据并传输到mDatas
    }
}
