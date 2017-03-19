package com.intelligentz.skoolvan.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.intelligentz.skoolvan.R;
import com.intelligentz.skoolvan.constants.Data;
import com.intelligentz.skoolvan.model.SearchSet;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

/**
 * Created by Lakshan on 2017-03-18.
 */

public class SearchSpinnerRecyclerAdaptor extends RecyclerView.Adapter<SearchSpinnerRecyclerAdaptor.RecyclerViewHolder> implements View.OnClickListener{
    private static int lastvisitedpos = 0;
    ArrayList<SearchSet> searchList = null;
    Context context = null;
    public SearchSpinnerRecyclerAdaptor (ArrayList<SearchSet> searchList, Context context) {
        this.context = context;
        this.searchList = searchList;
    }
    @Override
    public SearchSpinnerRecyclerAdaptor.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search_spinner_set,parent,false);
        SearchSpinnerRecyclerAdaptor .RecyclerViewHolder recyclerViewHolder = new SearchSpinnerRecyclerAdaptor.RecyclerViewHolder(view, context, searchList);
        return recyclerViewHolder;
    }
    @Override
    public void onBindViewHolder(SearchSpinnerRecyclerAdaptor.RecyclerViewHolder holder, int position) {
        searchList.get(position).setFrom_spinner(holder.from_spinner);
        searchList.get(position).setTo_spinner(holder.school_spinner);
    }
    @Override
    public int getItemCount() {
        return searchList.size();
    }
    @Override
    public void onClick(View view) {

    }

    public void removeSearchSet(int position) {
        searchList.remove(position);
        notifyItemRemoved(position);
    }
    public ArrayList<SearchSet> getSearchList() {
        return searchList;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        SearchableSpinner from_spinner;
        SearchableSpinner school_spinner;
        ImageView remove_btn;
        ArrayList<SearchSet> searchList = null;
        Context context = null;
        public RecyclerViewHolder(View itemView, Context context, ArrayList<SearchSet> searchList) {
            super(itemView);
            this.context =context;
            this.searchList = searchList;
            from_spinner = (SearchableSpinner) itemView.findViewById(R.id.input_from);
            school_spinner = (SearchableSpinner) itemView.findViewById(R.id.input_school);
            from_spinner.setTitle("From");
            school_spinner.setTitle("School");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_item, Data.froms);
            from_spinner.setAdapter(adapter);
            ArrayAdapter schoolAdaptor = new ArrayAdapter(context,
                    android.R.layout.simple_spinner_item, Data.schools);
            school_spinner.setAdapter(schoolAdaptor);
            remove_btn = (ImageView) itemView.findViewById(R.id.remove_btn);
            remove_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            removeSearchSet(getAdapterPosition());
        }
    }
}

