package com.grab.assignmentgrab.ui.main;

import android.os.Bundle;

import com.grab.assignmentgrab.R;
import com.grab.assignmentgrab.base.BaseActivity;
import com.grab.assignmentgrab.ui.list.ListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ListFragment()).commit();
    }
}
