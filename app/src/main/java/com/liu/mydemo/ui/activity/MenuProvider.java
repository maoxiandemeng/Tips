package com.liu.mydemo.ui.activity;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import com.liu.mydemo.R;

/**
 * Created by liu on 2016/5/20.
 */
public class MenuProvider extends ActionProvider {
    private Context context;
    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public MenuProvider(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateActionView() {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view =inflater.inflate(R.layout.menu_view, null);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "menu view", Toast.LENGTH_SHORT).show();
//            }
//        });
        return null;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        subMenu.clear();
        subMenu.add(0, 0, 0, "菜单1").setIcon(android.R.drawable.ic_dialog_info).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(context, "submenu 1 was clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        subMenu.add(0, 1, 1, "菜单2").setIcon(android.R.drawable.ic_dialog_info).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(context, "submenu 2 was clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean hasSubMenu() {
        return true;
    }
}
