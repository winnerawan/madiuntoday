package net.winnerawan.madiun.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Gallery;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public final int TYPE_MOVIE = 0;
    public final int TYPE_LOAD = 1;

    static Context context;
    List<Gallery> galleries;
    OnLoadMoreListener loadMoreListener;
    private static Callback callback;
    boolean isLoading = false, isMoreDataAvailable = true;
    private HashMap<Integer, Integer> mViewPageStates = new HashMap<>();
    /*
     * isLoading - to set the remote loading and complete status to fix back to back load more call
     * isMoreDataAvailable - to set whether more data from server available or not.
     * It will prevent useless load more request even after all the server data loaded
     * */


    public GalleryAdapter(Context context, List<Gallery> mGalleries) {
        this.context = context;
        this.galleries = mGalleries;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        if (viewType == TYPE_MOVIE) {
//            return new GalleryHolder(inflater.inflate(R.layout.item_gallery, parent, false));
//        } else {
//            return new LoadHolder(inflater.inflate(R.layout.item_image, parent, false));
//        }
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {

            case TYPE_MOVIE:
                View galleryView = inflater.inflate(R.layout.item_gallery, parent, false);
                viewHolder = new GalleryItemHolder(galleryView);
                break;

            case TYPE_LOAD:
//                View blockbusterView = inflater.inflate(R.layout.item_recycler_pager, parent, false);
//                viewHolder = new PagerItemHolder(blockbusterView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if (getItemViewType(position) == TYPE_MOVIE) {
//            ((GalleryHolder) holder).bindData(galleries.get(position));
            GalleryItemHolder galleryItemHolder = (GalleryItemHolder) holder;
            configurePagerHolder(galleryItemHolder, position);
        }
        //No else part needed as load holder doesn't bind any data
    }

    @Override
    public int getItemViewType(int position) {
        if (galleries.get(position).getType().equals("post")) {
            return TYPE_MOVIE;
        } else {
            return TYPE_LOAD;
        }
    }

    @Override
    public int getItemCount() {
        return galleries.size();
    }

    /* VIEW HOLDERS */

    public static class GalleryHolder extends RecyclerView.ViewHolder {

        ImageView imgPoster;


        public GalleryHolder(View itemView) {
            super(itemView);
        }

        void bindData(Gallery gallery) {
//            if (gallery.getImages() != null) {
//                Glide.with(itemView.getContext())
//                        .load(BASE_URL + "/images/" + movie.getImgs())
//                        .asBitmap()
//                        .centerCrop()
//                        .into(imgPoster);
//            }



            itemView.setOnClickListener(v -> {
//                if (gallery.ge() != null) {
//                    try {
//                        callback.onMovieSelected(movie);
//                    } catch (Exception e) {
//                        AppLogger.d("id error");
//                    }
//                }
            });
        }
    }

    private void configurePagerHolder(GalleryItemHolder holder, int position) {
        Gallery gallery = galleries.get(position);


        GalleryPagerAdapter pagerAdapter = new GalleryPagerAdapter(gallery.getImages(), context);
        holder.viewPager.setAdapter(pagerAdapter);
        if (gallery.getDescription()!=null) {
//            String regexAuthor = "(a-zA-Z)";
//            String description = gallery.getDescription();
//
//            String desc = description.replaceAll(regexAuthor, "");
//
//            Pattern pattern = Pattern.compile(regexAuthor);
//            Matcher matcher = pattern.matcher(gallery.getDescription());
//            String author = matcher.toString();
            holder.txtDescription.setText(android.text.Html.fromHtml(gallery.getDescription()).toString());
//            holder.txtAuthor.setText(author);
        }
//        pagerAdapter.registerDataSetObserver(holder.indicator.getDa());
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof GalleryItemHolder) {

            GalleryItemHolder viewHolder = (GalleryItemHolder) holder;

            mViewPageStates.put(holder.getAdapterPosition(), viewHolder.viewPager.getCurrentItem());
            super.onViewRecycled(holder);
        }
    }

    public static class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(View itemView) {
            super(itemView);
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


    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public interface Callback {
        void onMovieSelected(Gallery movie);
    }

    public void setCallback(Callback mCallback) {
        this.callback = mCallback;
    }
}