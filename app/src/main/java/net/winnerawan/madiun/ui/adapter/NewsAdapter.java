package net.winnerawan.madiun.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.utils.AppConstants;
import net.winnerawan.madiun.utils.AppLogger;
import net.winnerawan.madiun.utils.CommonUtils;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public final int TYPE_MOVIE = 0;
    public final int TYPE_LOAD = 1;

    static Context context;
    List<Post> posts;
    OnLoadMoreListener loadMoreListener;
    private static Callback callback;
    boolean isLoading = false, isMoreDataAvailable = true;
    private static boolean isAdsEnable;

    /*
     * isLoading - to set the remote loading and complete status to fix back to back load more call
     * isMoreDataAvailable - to set whether more data from server available or not.
     * It will prevent useless load more request even after all the server data loaded
     * */


    public NewsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_MOVIE) {
            return new MovieHolder(inflater.inflate(R.layout.item_news_row, parent, false));
        } else {
            return new LoadHolder(inflater.inflate(R.layout.row_load, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if (getItemViewType(position) == TYPE_MOVIE) {
            ((MovieHolder) holder).bindData(posts.get(position));
        }
//        else if (getItemViewType(position) == TYPE_LOAD) {
//            AppLogger.e("PROGRESS BAR SHOWING");
//            ((LoadHolder) holder).progressBar.setIndeterminate(true);
//        }
    }

    @Override
    public int getItemViewType(int position) {
        return posts.get(position).getType().equals("post") ? TYPE_MOVIE : TYPE_LOAD;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    /* VIEW HOLDERS */

    public static class MovieHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        TextView txtTitle;

        TextView txtDate;

        TextView txtCategory;


        public MovieHolder(View itemView) {
            super(itemView);
            SharedPreferences preferences = itemView.getContext().getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);

            isAdsEnable = preferences.getBoolean("KEY_ADS_ENABLE", false);

            imageView = (ImageView) itemView.findViewById(R.id.image);
            txtCategory = (TextView) itemView.findViewById(R.id.category);
            txtDate = (TextView) itemView.findViewById(R.id.date);
            txtTitle = (TextView) itemView.findViewById(R.id.title);
        }

        void bindData(Post post) {
            if (post.getJetpackFeaturedMediaUrl() != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    imageView.setClipToOutline(true);
                }
                Glide.with(itemView.getContext())
                        .load(post.getJetpackFeaturedMediaUrl())
                        .crossFade()
                        .centerCrop()
                        .error(R.mipmap.ic_launcher)
                        .into(imageView);
            }
            if (post.getTitle() != null) {
                txtTitle.setText(post.getTitle().getRendered());
            }

            if (post.getDate() != null) {
                txtDate.setText(CommonUtils.prettyDateFormat(post.getDate()));
            }

            itemView.setOnClickListener(v -> {
                if (!isAdsEnable) {
                    callback.onPostSelected(post);
                } else {
                    callback.onPostSelectedWithAds(post);
                }

            });
        }
    }

    public static class LoadHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadHolder(View itemView) {
            super(itemView);

            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /* notifyDataSetChanged is final method so we can't override it
         call adapter.notifyDataChanged(); after update the list
         */
    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public void addItems(List<Post> posts) {
        this.posts.addAll(posts);
        notifyDataChanged();
    }


    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public interface Callback {
        void onPostSelected(Post post);

        void onPostSelectedWithAds(Post post);
    }

    public void setCallback(Callback mCallback) {
        this.callback = mCallback;
    }
}