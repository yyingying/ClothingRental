package clothing_rental.canceline.com.clothingrental.details.rental;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.OptionsPickerView;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;
import clothing_rental.canceline.com.clothingrental.data_base.Adress;
import clothing_rental.canceline.com.clothingrental.login.ui.LoginUtil;
import clothing_rental.canceline.com.clothingrental.login.ui.RegisterActivity;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by kingShin on 2018/3/20.
 */

@Route(path = "/details/rental/AddAdressActivity")
public class AddAdressActivity extends BaseActivity {
    private ImageView close_btn;
    private Button save_btn;
    private EditText name_editxt;
    private EditText phone_editxt;
    private TextView mTextViewAddress;
    private EditText street_editxt;
    private String name;
    private String phone;
    private String place;
    private String adress;
    private String userID;
    private LinearLayout mPlace_lauout;
    OptionsPickerView pvOptions;
    //  省份
    ArrayList<ProvinceBean> provinceBeanList = new ArrayList<>();
    //  城市
    ArrayList<String> cities;
    ArrayList<List<String>> cityList = new ArrayList<>();
    //  区/县
    ArrayList<String> district;
    ArrayList<List<String>> districts;
    ArrayList<List<List<String>>> districtList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_adress);


        //退出设置
        close_btn = (ImageView) findViewById(R.id.closeImg);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //点击保存时，获取姓名、手机号码、地区、街道地址
        save_btn = (Button) findViewById(R.id.saveButton);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_editxt = findViewById(R.id.nameEditText);
                name = name_editxt.getText().toString();
                phone_editxt = findViewById(R.id.phoneEditText);
                phone = phone_editxt.getText().toString();
                place = mTextViewAddress.getText().toString();
                street_editxt = findViewById(R.id.streetEditText);
                adress = place+ street_editxt.getText().toString();
                userID = LoginUtil.getPerson().getAccount();

                //插入数据库
                final Adress myadress = new Adress();
                myadress.setUserID(userID);
                myadress.setPersonName(name);
                myadress.setPhone(phone);
                myadress.setAdress(adress);
                myadress.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(AddAdressActivity.this,"sucess",Toast.LENGTH_LONG).show();
                            //改变静态变量
                            if(LoginUtil.getFirst_set_address()==true){
                                LoginUtil.setAdress(myadress);
                            }
                            finish();
                        }
                        Toast.makeText(AddAdressActivity.this,"failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



        //  创建选项选择器
        pvOptions = new OptionsPickerView(this);
        mTextViewAddress = (TextView) findViewById(R.id.tv_address);

        //  获取json数据
        String province_data_json = JsonFileReader.getJson(this, "province_data.json");
        //  解析json数据
        parseJson(province_data_json);

        //  设置三级联动效果
        pvOptions.setPicker(provinceBeanList, cityList, districtList, true);


        //  设置选择的三级单位
        //pvOptions.setLabels("省", "市", "区");
        //pvOptions.setTitle("选择城市");

        //  设置是否循环滚动
        pvOptions.setCyclic(false, false, false);


        // 设置默认选中的三级项目
        pvOptions.setSelectOptions(0, 0, 0);
        //  监听确定选择按钮
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String city = provinceBeanList.get(options1).getPickerViewText();
                String address;
                //  如果是直辖市或者特别行政区只设置市和区/县
                if ("北京市".equals(city) || "上海市".equals(city) || "天津市".equals(city) || "重庆市".equals(city) || "澳门".equals(city) || "香港".equals(city)) {
                    address = provinceBeanList.get(options1).getPickerViewText()
                            + " " + districtList.get(options1).get(option2).get(options3);
                } else {
                    address = provinceBeanList.get(options1).getPickerViewText()
                            + " " + cityList.get(options1).get(option2)
                            + " " + districtList.get(options1).get(option2).get(options3);
                }
                mTextViewAddress.setText(address);
            }
        });


        //  点击弹出选项选择器
        mPlace_lauout = findViewById(R.id.placeLinearLayout);
        mPlace_lauout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }

    //  解析json填充集合
    public void parseJson(String str) {
        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                //  获取省份名称放入集合
                String provinceName = provinceObject.optString("name");
                provinceBeanList.add(new ProvinceBean(provinceName));
                //  获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray("city");
                cities = new ArrayList<>();//   声明存放城市的集合
                districts = new ArrayList<>();//声明存放区县集合的集合
                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString("name");
                    cities.add(cityName);
                    district = new ArrayList<>();// 声明存放区县的集合
                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray("area");
                    //  遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        district.add(areaName);
                    }
                    //  将区县的集合放入集合
                    districts.add(district);
                }
                //  将存放区县集合的集合放入集合
                districtList.add(districts);
                //  将存放城市的集合放入集合
                cityList.add(cities);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
