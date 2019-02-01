package net.winnerawan.madiun.ui.tv;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.YoutubeItem;
import net.winnerawan.madiun.data.network.model.YoutubeItem;
import net.winnerawan.madiun.ui.base.BaseViewHolder;
import net.winnerawan.madiun.utils.CommonUtils;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<YoutubeItem> mYoutubeItemResponseList;

    public VideosAdapter(List<YoutubeItem> postResponseList) {
        mYoutubeItemResponseList = postResponseList;
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
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mYoutubeItemResponseList != null && mYoutubeItemResponseList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mYoutubeItemResponseList != null && mYoutubeItemResponseList.size() > 0) {
            return mYoutubeItemResponseList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<YoutubeItem> apps) {
        mYoutubeItemResponseList.addAll(apps);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onYoutubeItemSelected(YoutubeItem video);
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.img_content)
        ImageView imageView;

        @BindView(R.id.txt_title)
        TextView txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            final YoutubeItem video = mYoutubeItemResponseList.get(position);

            if (video.getSnippet().getTitle()!=null) {
                txtTitle.setText(video.getSnippet().getTitle());
            }
            if (video.getSnippet().getThumbnails().getHigh()!=null) {
                Glide.with(itemView.getContext())
                        .load(video.getSnippet().getThumbnails().getHigh().getUrl())
                        .asBitmap()
                        .centerCrop()
                        .error(R.mipmap.ic_launcher)
                        .into(imageView);
            }

            itemView.setOnClickListener(v -> {
                if (video.getYoutubeVideo().getVideoId() != null) {
                    try {
                        mCallback.onYoutubeItemSelected(video);
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
