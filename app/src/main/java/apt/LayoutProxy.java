package apt;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;

/**
 * Created by husongzhen on 17/11/10.
 */

public class LayoutProxy {
    public void inject(Activity activity) {
        Resources resources = activity.getResources();
        int layoutId = resources.getIdentifier("layout", "layout", activity.getPackageName());
        activity.setContentView(layoutId);
        int id = resources.getIdentifier("name", "id", activity.getPackageName());
        View v = activity.findViewById(id);
    }
}
