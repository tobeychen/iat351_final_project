package iat351.milestone_3.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import iat351.milestone_3.R;

/**
 * Created by xiaok_000 on 2016-11-13.
 */

//public class LoadingFragment extends Fragment{
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.loading_scene,
//                container, false);
//        final Scene scene = Scene.getSceneForLayout(container,
//                R.layout.loging_scene, getActivity());
//        Button goButton = (Button)rootView.findViewById(R.id.goButton);
//        goButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TransitionManager.go(scene);
//            }
//        });
//        return rootView;
//    }
//}
