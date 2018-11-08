package com.fourfifteen.group.nku_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    private final LinkedList<String> mTaskList;
    private LayoutInflater mInflater;

    public TaskListAdapter(Context context, LinkedList<String> taskList) {
        mInflater = LayoutInflater.from(context);
        this.mTaskList = taskList;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        public final TextView taskItemView;
        final TaskListAdapter mAdapter;
        public TaskViewHolder(View itemView, TaskListAdapter adapter) {
            super (itemView);
            taskItemView = itemView.findViewById(R.id.task);
            this.mAdapter = adapter;
        }
    }

    public TaskListAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.tasklist_item, parent, false);
        return new TaskViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListAdapter.TaskViewHolder holder, int position) {
        String mCurrent = mTaskList.get(position);
        holder.taskItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }
}
