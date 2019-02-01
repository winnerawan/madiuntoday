package net.winnerawan.madiun.ui.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import net.winnerawan.madiun.utils.CommonUtils;

public class HeadLineAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<Post> mPostResponseList;

    public HeadLineAdapter(List<Post> postResponseList) {
        mPostResponseList = postResponseList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_headline, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_headline, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mPostResponseList != null && mPostResponseList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mPostResponseList != null && mPostResponseList.size() > 0) {
            return mPostResponseList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<Post> apps) {
        mPostResponseList.addAll(apps);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onPostSelected(Post post);
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.img_content)
        ImageView imageView;

        @BindView(R.id.txt_title)
        TextView txtTitle;

        @BindView(R.id.txt_time)
        TextView txtDate;

//        @BindView(R.id.date)
//        TextView txtDate;
//
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            final Post post = mPostResponseList.get(position);

            if (post.getJetpackFeaturedMediaUrl() != null) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    imageView.setClipToOutline(true);
                }
                Glide.with(itemView.getContext())
                        .load(post.getJetpackFeaturedMediaUrl())
                        .centerCrop()
                        .crossFade()
                        .into(imageView);
            }
            if (post.getTitle()!=null) {
                txtTitle.setText(post.getTitle().getRendered());
            }

            if (post.getDate()!=null) {
                txtDate.setText(CommonUtils.prettyDateFormat(post.getDate()));
            }

            itemView.setOnClickListener(v -> {
                if (post.getId() != null) {
                    try {
                        mCallback.onPostSelected(post);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {


        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }


    }
}
