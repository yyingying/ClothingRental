package clothing_rental.canceline.com.clothingrental.details;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;

import clothing_rental.canceline.com.clothingrental.R;

/**
 * Created by kingShin on 2018/3/14.
 */

public class Adapter2 extends DelegateAdapter.Adapter<Adapter2.MyHolder> {

    private RelativeLayout layout1;
    //private RelativeLayout layout2;
    //private RelativeLayout layout3;

    private Context mContext;
    private FragmentManager mFragmentManager;

    public Adapter2(FragmentManager fragmentManager, Context context) {
        super();
        mContext = context;
        mFragmentManager = fragmentManager;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    // 重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    public Adapter2.MyHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        // 填充布局

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_details_2, null);
        Adapter2.MyHolder holder = new Adapter2.MyHolder(view);

        final MyDialogFragment myDialogFragment = new MyDialogFragment();
        layout1 = (RelativeLayout) view.findViewById(R.id.layout_1);
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialogFragment.show(mFragmentManager, "dialog_fragment");
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(Adapter2.MyHolder holder, int position) {
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }

    // 定义内部类继承ViewHolder
    class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(View view) {
            super(view);
        }
    }
}
