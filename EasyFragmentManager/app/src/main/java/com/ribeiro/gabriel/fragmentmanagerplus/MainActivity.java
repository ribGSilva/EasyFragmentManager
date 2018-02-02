package com.ribeiro.gabriel.fragmentmanagerplus;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ribeiro.gabriel.easyfragmentmanager.EasyFragmentController;
import com.ribeiro.gabriel.easyfragmentmanager.EasyFragmentManager;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity implements EasyFragmentController {

    private EasyFragmentManager easyFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put(FragmentTypes.TYPE_1.name(), FragmentType1.class);
        classMap.put(FragmentTypes.TYPE_2.name(), FragmentType2.class);

        easyFragmentManager = EasyFragmentManager.newInstance(this,
                R.id.contentPanel, classMap, FragmentTypes.TYPE_1.name(), getSupportFragmentManager());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        easyFragmentManager.updateManagerOnBackStackChange();
    }

    @Override
    public void changeFragment(String newFragment, boolean addToBackStack) {
        easyFragmentManager.changeFragment(newFragment, addToBackStack);
    }
}
