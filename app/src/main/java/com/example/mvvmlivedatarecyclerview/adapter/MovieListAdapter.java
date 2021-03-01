package com.example.mvvmlivedatarecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvmlivedatarecyclerview.R;
import com.example.mvvmlivedatarecyclerview.model.MovieModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder>{

    private Context context;
    private List<MovieModel> movieList;
    private ItemClickListener itemClickListener;

    public MovieListAdapter(Context context, List<MovieModel> movieList, ItemClickListener itemClickListener) {
        this.context = context;
        this.movieList = movieList;
        this.itemClickListener = itemClickListener;
    }

    public void setMovieList(List<MovieModel> movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTitle.setText(movieList.get(position).getTitle().toString());
        Glide.with(context)
                .load(movieList.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onMovieClick(movieList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.movieList != null){
            return this.movieList.size();
        }else {
            return 0;
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.textView);
           imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public interface ItemClickListener{
        public void onMovieClick(MovieModel movieModel);
    }
}
