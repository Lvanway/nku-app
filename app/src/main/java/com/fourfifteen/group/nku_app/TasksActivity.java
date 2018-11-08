package com.fourfifteen.group.nku_app;

        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.Button;

        import java.util.LinkedList;

public class TasksActivity extends AppCompatActivity {

    FloatingActionButton addTaskButton;
    private RecyclerView mRecyclerView;
    private TaskListAdapter mAdapter;
    private final LinkedList<String> mTaskList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tasks);
        addTaskButton = findViewById (R.id.addTaskButton);
        mRecyclerView = findViewById(R.id.taskview);
        mAdapter = new TaskListAdapter(this, mTaskList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager (this));



        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int taskListSize = mTaskList.size();
                mTaskList.addLast("Task " + taskListSize);
                mRecyclerView.getAdapter().notifyItemInserted(taskListSize);
            }
        });
    }

}
