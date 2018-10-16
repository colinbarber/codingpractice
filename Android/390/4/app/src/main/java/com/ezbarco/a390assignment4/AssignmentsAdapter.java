package com.ezbarco.a390assignment4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ezbarco.a390assignment4.model.Assignment;

import java.util.List;

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.MyViewHolder> {

    private Context context;
    private List<Assignment> AssignmentsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Assignment;

        public MyViewHolder(View view) {
            super(view);
            Assignment = view.findViewById(R.id.assignment);
        }
    }


    public AssignmentsAdapter(Context context, List<Assignment> AssignmentsList) {
        this.context = context;
        this.AssignmentsList = AssignmentsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assignment_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Assignment Assignment = AssignmentsList.get(position);
        holder.Assignment.setText(Assignment.getAssignmentName());
    }

    @Override
    public int getItemCount() {
        return AssignmentsList.size();
    }
}