package clothing_rental.canceline.com.clothingrental.mine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import java.util.List;
import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.data_base.Favourite;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
import clothing_rental.canceline.com.clothingrental.login.ui.LoginUtil;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by kingShin on 2018/2/9.
 */

@Route(path="/mine/Favourite")

public class FavouriteActivity extends Activity {
    private Button btn_exit;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        //退出按钮
        btn_exit = findViewById(R.id.exit_btn);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //布局
        mRecyclerView = findViewById(R.id.favouriteRecyclerView);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
        mRecyclerView.setAdapter(delegateAdapter);

        if (LoginUtil.isLogin()==true){
            BmobQuery<Favourite> favouriteBmobQuery = new BmobQuery<>();
            favouriteBmobQuery.addWhereEqualTo("personID", LoginUtil.getPerson().getAccount());
            favouriteBmobQuery.findObjects(new FindListener<Favourite>() {
                @Override
                public void done(List<Favourite> list, BmobException e) {
                    if (e == null) {
                        Toast.makeText(FavouriteActivity.this, "sucess", Toast.LENGTH_LONG).show();
                        delegateAdapter.addAdapter(new ItemAdapter(list));
                        delegateAdapter.notifyDataSetChanged();
                    }

                }
            });
        }
    }

     class ItemAdapter extends DelegateAdapter.Adapter<ItemViewHolder> {
        private List<Favourite> favourites;
        //private Context mcontext;
        public ItemAdapter(List<Favourite> list) {
            this.favourites = list;
            //this.mcontext = context;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new LinearLayoutHelper();
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_layout_favourite,parent,false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ItemViewHolder holder, int position) {
            final Favourite favourite = favourites.get(position);
            BmobQuery<Goods> goodsBmobQuery = new BmobQuery<>();
            goodsBmobQuery.addWhereEqualTo("goodsID",favourite.getGoodSID());
            goodsBmobQuery.findObjects(new FindListener<Goods>() {
                @Override
                public void done(final List<Goods> list, BmobException e) {
                    if(e==null){
                        holder.textView.setText(String.valueOf(list.get(0).getName()));
                        Glide.with(getApplicationContext()).load(list.get(0).getPhoto().getUrl()).into(holder.imageView);

                        holder.layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onItemClick(list.get(0),holder.getAdapterPosition());
                            }
                        });
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return favourites.size() ;
        }
    }

    private void onItemClick(Goods good, int adapterPosition) {
        ARouter.getInstance().build("/details/DetailsActivity")
                .withParcelable("Good",good)
                .navigation();

        finish();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private LinearLayout layout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.favouriteGoodsImageView);
            textView = itemView.findViewById(R.id.favouriteGoodsTextView);
            layout = itemView.findViewById(R.id.favouriteLinearLayout);
        }
    }
}