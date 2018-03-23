package clothing_rental.canceline.com.clothingrental.mine;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kingShin on 2018/3/21.
 */

public class CacheActivity {
    public static List<Activity>activityList = new LinkedList<Activity>();

    public CacheActivity(){}

    //添加到Activity容器中
    public static void addActivity(Activity activity){
        if(!activityList.contains(activity)){
            activityList.add(activity);
        }
    }

    //便利所有Activity并finish
    public static void finishActivity(){
        for(Activity activity:activityList){
            activity.finish();
        }
    }
}
