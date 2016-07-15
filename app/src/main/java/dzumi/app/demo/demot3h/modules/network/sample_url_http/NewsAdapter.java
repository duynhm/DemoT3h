package dzumi.app.demo.demot3h.modules.network.sample_url_http;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import dzumi.app.demo.demot3h.R;

/**
 * Created by Dzumi on 7/15/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    Context mContext;
    int layout;
    List<News> newsList;
    public NewsAdapter(Context context, int layout, List<News> newsList) {
        mContext = context;
        this.layout = layout;
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //tao ra view
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //set du lieu
        holder.tvPubDate.setText(newsList.get(position).getPubDate());
        holder.tvDescription.setText(newsList.get(position).getDescription());
        holder.tvTitle.setText(newsList.get(position).getTitle());
        Picasso.with(mContext).load(newsList.get(position).getImageLink()).error(R.drawable.vietnam).into(holder.imageView);
    }

    //so luong item hien thi
    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvDescription;
        TextView tvPubDate;
        ImageView imageView;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvPubDate = (TextView) itemView.findViewById(R.id.tvPubDate);
            imageView = (ImageView) itemView.findViewById(R.id.image);

            cardView = (CardView) itemView;
            //TODO: bat su kien click tại đây
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Onclick", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
