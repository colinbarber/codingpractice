package com.ezbarco.a390assignment4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.ezbarco.a390assignment4.model.Course;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder> {

    private Context context;
    private List<Course> CoursesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Course;
        public TextView Code;
        public TextView Average;

        public MyViewHolder(View view) {
            super(view);
            Course = view.findViewById(R.id.course);
            Code = view.findViewById(R.id.code);
            Average = view.findViewById(R.id.avg);
        }
    }


    public CoursesAdapter(Context context, List<Course> CoursesList) {
        this.context = context;
        this.CoursesList = CoursesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Course Course = CoursesList.get(position);
        holder.Course.setText(Course.getCoursetitle());
        holder.Code.setText(Course.getCourseCode());
        holder.Average.setText("Assignment Average: NA");
    }

    @Override
    public int getItemCount() {
        return CoursesList.size();
    }
}