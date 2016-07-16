package bhouse.jtenun;

import android.content.Context;

/**
 * Created by JERRY on 6/27/2016.
 */
public class TenunDetail {
    public String name;
    public String imageName;
    public boolean isFav;

    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(this.imageName, "drawable", context.getPackageName());
    }
}
