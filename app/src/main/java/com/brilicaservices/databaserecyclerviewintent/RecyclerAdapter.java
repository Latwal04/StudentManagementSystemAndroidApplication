package com.brilicaservices.databaserecyclerviewintent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends
        RecyclerView.Adapter<
                RecyclerAdapter.StudentViewHolder> {

    private static final String TAG = RecyclerAdapter.class.getName();

    private final ListItemClickListener itemClickListener;

    private static int viewHolderCount;

    private ArrayList<StudentModel> studentModelArrayList;

    public interface ListItemClickListener {
        void onListItemClickListener(int clickedItemIndex);
    }

    public RecyclerAdapter(ArrayList<StudentModel> studentModelArrayList,
                           ListItemClickListener itemClickListener) {
        this.studentModelArrayList = studentModelArrayList;
        this.itemClickListener = itemClickListener;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType) {

        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(
                R.layout.item_layout,
                parent,
                false);

        StudentViewHolder studentViewHolder =
                new StudentViewHolder(view);
        studentViewHolder.recyclerNumber.setText(""+viewHolderCount);

        viewHolderCount = viewHolderCount + 1;

        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.StudentViewHolder holder,
                                 int position) {
        StudentModel student = studentModelArrayList.get(position);
        holder.studentName.setText("Name is: " + student.name);
    }

    @Override
    public int getItemCount() {
        return studentModelArrayList.size();
    }

    public class StudentViewHolder extends
            RecyclerView.ViewHolder
            implements View.OnClickListener{

        TextView studentName;
        TextView recyclerNumber;

        public StudentViewHolder(View itemView) {
            super(itemView);

            studentName = itemView.findViewById(R.id.student_name);
            recyclerNumber = itemView.findViewById(R.id.recycler_number);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            itemClickListener.onListItemClickListener(clickedPosition);
        }
    }
}
