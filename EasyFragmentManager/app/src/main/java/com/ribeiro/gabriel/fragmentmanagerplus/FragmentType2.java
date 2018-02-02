package com.ribeiro.gabriel.fragmentmanagerplus;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ribeiro.gabriel.easyfragmentmanager.EasyFragmentController;


public class FragmentType2 extends Fragment {

    private EasyFragmentController easyFragmentController;

    public FragmentType2() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof EasyFragmentController)
            easyFragmentController = (EasyFragmentController) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_type2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((TextView)getView().findViewById(R.id.type))
                .setText("currentFragment type 2");

        getView().findViewById(R.id.btn_go_to_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyFragmentController.changeFragment(FragmentTypes.TYPE_1.name(), true);
            }
        });
    }
}
