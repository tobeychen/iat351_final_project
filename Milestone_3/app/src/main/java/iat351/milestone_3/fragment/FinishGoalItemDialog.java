package iat351.milestone_3.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import iat351.milestone_3.R;

/**
 * Created by xiaok_000 on 2016-11-14.
 */

public class FinishGoalItemDialog extends DialogFragment {
    private EditText mEditText;

    public FinishGoalItemDialog() {
        // Empty constructor required for DialogFragment
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        View view = inflater.inflate(R.layout.fragment_dialog_finish_goal_item, container);
//        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
//        getDialog().setTitle("Hello");
//
//        return view;
//    }
}
