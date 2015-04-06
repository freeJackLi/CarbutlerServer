package kankan.wheel.widget;

import kankan.wheel.R;

/**
 * Created by root-pc on 2015/4/4.
 */
public class WheelUtils {

    public static void initWheelView(WheelView wheelView) {

        wheelView.setVisibleItems(3); // Number of items
        wheelView.setWheelBackground(R.drawable.wheel_bg_holo);
        wheelView.setWheelForeground(R.drawable.wheel_val_holo);
        wheelView.setShadowColor(0x00000000, 0x00000000, 0x00000000);
    }
}
