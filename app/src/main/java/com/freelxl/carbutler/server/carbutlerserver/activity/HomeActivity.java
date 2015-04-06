package com.freelxl.carbutler.server.carbutlerserver.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;

import com.freelxl.baselibrary.utils.ToastUtils;
import com.freelxl.carbutler.server.carbutlerserver.R;
import com.freelxl.carbutler.server.carbutlerserver.fragment.ContentFragment;
import com.freelxl.carbutler.server.carbutlerserver.fragment.MenuFragment;

public class HomeActivity extends FragmentActivity {

    private DrawerLayout drawerLayout;
    private boolean isOpen;
    private ContentFragment contentFragment;
    private MenuFragment menuFragment;
    private long lastBackTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerStateChanged(int arg0) {

            }

            @Override
            public void onDrawerSlide(View arg0, float arg1) {

            }

            @Override
            public void onDrawerOpened(View arg0) {
                isOpen = true;

            }

            @Override
            public void onDrawerClosed(View arg0) {
                isOpen = false;

            }
        });
        contentFragment = new ContentFragment();
        menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment, "CONTENT").commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, menuFragment, "MENU").commit();
    }

    public void toggle() {
        if (isOpen) {
            drawerLayout.closeDrawers();
        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (System.currentTimeMillis() - lastBackTime > 3 * 1000) {
                ToastUtils.showToast("再按一下退出");
                lastBackTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
