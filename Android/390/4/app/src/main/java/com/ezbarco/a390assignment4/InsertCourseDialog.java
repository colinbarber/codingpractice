package com.ezbarco.a390assignment4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ezbarco.a390assignment4.helper.DatabaseHelper;
import com.ezbarco.a390assignment4.model.Course;

public class InsertCourseDialog extends DialogFragment {
    private static final String TAG = "InsertCourseDialog";

    DatabaseHelper db;

    public interface OnInputListener{
        void sendInput(String input);
    }
    public OnInputListener mOnInputListener;

    private EditText mTitle, mCode;
    private TextView mActionSave, mActionCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_insert_course, container, false);
        mActionSave = view.findViewById(R.id.course_save_button);
        mActionCancel = view.findViewById(R.id.course_cancel_button);
        mTitle = view.findViewById(R.id.course_title_input);
        mCode = view.findViewById(R.id.course_code_input);
        db = new DatabaseHelper(getActivity());


        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        mActionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: saving input");
                String title = mTitle.getText().toString();
                String code = mCode.getText().toString();
                if (!title.equals("") && !code.equals("")) {
                    // add to database
                    db.createCourse(new Course(title, code));
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    //intent.setFlag(Intent.CLEAR_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    getDialog().dismiss();
                }
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //((MainActivity)getActivity()).refreshUI();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
        }
    }
}