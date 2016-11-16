package iat351.milestone_3.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iat351.milestone_3.R;
import iat351.milestone_3.other.CustomExpandableListAdapter;
import iat351.milestone_3.activity.FinshGoalItemActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GoalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GoalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    CheckBox is_done;

    public GoalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoalFragment newInstance(String param1, String param2) {
        GoalFragment fragment = new GoalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_goal, container, false);
        //initialize list view
        expandableListView =(ExpandableListView)v .findViewById(R.id.expandableListView);
        prepareListData();
        expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getContext(),"clicked", Toast.LENGTH_LONG).show();
                //FinishGoalItemDialog editNameDialog = new FinishGoalItemDialog();
                //editNameDialog.show(getActivity().getFragmentManager(), "dialog");
                    Intent intent = new Intent(getContext(), FinshGoalItemActivity.class);
                    startActivity(intent);
                return true;
            }
        });

        return v;
    }

    private void prepareListData(){
        expandableListTitle = new ArrayList<String>();
        expandableListDetail = new HashMap<String, List<String>>();

        // Adding child data
        expandableListTitle.add("IAT 351 Midterm");
        expandableListTitle.add("IAT 351 Milestone 3");
        expandableListTitle.add("IAT 351 Assignment 3");

        // Adding child data
        List<String> midterm351 = new ArrayList<String>();
        midterm351.add("Unit 1");
        midterm351.add("Unit 2");
        midterm351.add("Unit 3");
        midterm351.add("Unit 4");
        midterm351.add("Unit 5");
        midterm351.add("Unit 6");
        midterm351.add("Unit 7");
        midterm351.add("Unit 8");
        midterm351.add("Unit 9");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Loading Activity");
        nowShowing.add("Calendar Fragment");
        nowShowing.add("Goal Fragment");
        nowShowing.add("Summary Fragment");
        nowShowing.add("Setting Fragment");
        nowShowing.add("Add Event Activity");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Display Bar");
        comingSoon.add("LED-display");
        comingSoon.add("Scroll Bar");
        comingSoon.add("The Canyons");
        comingSoon.add("My Own Component");

        expandableListDetail.put(expandableListTitle.get(0), midterm351); // Header, Child data
        expandableListDetail.put(expandableListTitle.get(1), nowShowing);
        expandableListDetail.put(expandableListTitle.get(2), comingSoon);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //if (context instanceof OnFragmentInteractionListener) {
        //    mListener = (OnFragmentInteractionListener) context;
        //} else {
        //    throw new RuntimeException(context.toString()
        //            + " must implement OnFragmentInteractionListener");
        //}
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
