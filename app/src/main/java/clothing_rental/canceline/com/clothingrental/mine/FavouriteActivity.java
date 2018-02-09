package clothing_rental.canceline.com.clothingrental.mine;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;

/**
 * Created by kingShin on 2018/2/9.
 */

@Route(path="/mine/Favourite")

public class FavouriteActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
    }
}
