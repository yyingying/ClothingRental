package clothing_rental.canceline.com.clothingrental.details;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;

/**
 * Created by kingShin on 2018/3/14.
 */

public class DetailsActivity extends BaseActivity {
    private RecyclerView mRecyclerViews;
    private Data mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //初始化数据
        initData();
        //得到控件
        mRecyclerViews = (RecyclerView)findViewById(R.id.recycle_view);
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViews.setLayoutManager(layoutManager);
        //设置适配器

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager,hasConsistItemType);
        mRecyclerViews.setAdapter(delegateAdapter);

        Adapter1 adapter1 = new Adapter1(data);
        delegateAdapter.addAdapter(adapter1);

        Adapter2 adapter2 = new Adapter2();
        delegateAdapter.addAdapter(adapter2);

        Adapter1 adapter3 = new Adapter3(data);
        delegateAdapter.addAdapter(adapter3);

        delegateAdapter.notifyDataSetChange();
    }

    private void initData() {
        //从云存储上获取数据并传输到mDatas
    }
}
