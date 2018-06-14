package com.brilicaservices.databaserecyclerviewintent;
import android.content.Intent;
import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class ItemLayout extends AppCompatActivity
        implements RecyclerAdapter.ListItemClickListener{

    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    StudentModel studentModel;

    RecyclerAdapter recyclerAdapter;

    ArrayList<StudentModel> studentModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_item);
        databaseHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recycler_view_sample);

        studentModelArrayList.addAll(databaseHelper.allStudentsDetails());

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new
                RecyclerAdapter(studentModelArrayList,
                this);

        recyclerView.setAdapter(recyclerAdapter);
    }


    @Override
    public void onListItemClickListener(int clickedItemIndex) {
        StudentModel studentModel1 = studentModelArrayList.get(clickedItemIndex);
        Intent intent=new Intent(ItemLayout.this,StudentInfo.class);
        intent.putExtra("Name",studentModel1.getName());
        intent.putExtra("College",studentModel1.getCollegeName());
        intent.putExtra("PhoneNo",studentModel1.getPhoneNumber());
        intent.putExtra("Address",studentModel1.getAddress());
        startActivity(intent);
        Toast.makeText(getApplicationContext(), studentModelArrayList.get(clickedItemIndex).name, Toast.LENGTH_SHORT).show();
    }
}