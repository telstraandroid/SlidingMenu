package com.jeremyfeinstein.slidingmenu.example.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.example.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class ThreeLayerActivity extends FragmentActivity implements NavigationMenu.Callback, ServiceMenu.Callback {

    private SlidingMenu menu;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_layer);

        // check if the content frame contains the menu frame
        if (findViewById(R.id.menu_frame) == null) {
            menu = new SlidingMenu(this);
            menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
            menu.setTouchModeBehind(SlidingMenu.TOUCHMODE_MARGIN);
            menu.setShadowWidthRes(R.dimen.shadow_width);
            menu.setShadowDrawable(R.drawable.shadow);
            menu.setBehindOffset(0);
            menu.setBehindScrollScale(0.0f);
            menu.setContentOff(getResources().getDimensionPixelSize(R.dimen.content_off));
            menu.setFadeDegree(0.1f);
            menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
            menu.setMenu(R.layout.two_level_menus);
            menu.showMenu(false);
        }

        Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.service_list);
        getSupportFragmentManager().beginTransaction().hide(fragmentById).commit();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setHomeButtonEnabled(true);
            getActionBar().setDisplayShowHomeEnabled(true);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void onNavigationOptionSelected(int position) {
        showHiddenMenu();
        Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.service_list);
        if (fragmentById.isHidden()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("service_menu")
                    .show(fragmentById)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .commit();

        }

    }

    private boolean showHiddenMenu() {
        if (!menu.isMenuShowing()) {
            menu.showMenu();
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (showHiddenMenu()) {
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onServiceOptionSelected(int position) {
        if (menu.isMenuShowing()) {
            menu.showContent();
        }
    }

    @Override
    public void onBackPressed() {
        if (showHiddenMenu()) {
            return;
        }
        super.onBackPressed();
    }


}