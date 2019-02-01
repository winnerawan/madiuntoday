package net.winnerawan.madiun.ui.gallery;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Image;

import java.util.ArrayList;
import java.util.List;

public class GalleryPagerAdapter extends PagerAdapter {


    private LayoutInflater mInflater;
    private Context mContext;

    private List<Image> mPagerList = new ArrayList<>();

    public GalleryPagerAdapter(List<Image> list, Context context) {

        if (list != null && list.size() > 0)
            mPagerList.addAll(list);

        this.mContext = context;

        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View rootView = mInflater.inflate(R.layout.item_image, container, false);

        ViewHolder holder = new ViewHolder(rootView);

        final Image data = mPagerList.get(position);

        if (data.getImage() != null) {
            Glide.with(mContext)
                    .load(data.getImage())
                    .crossFade()
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(holder.imageView);

        }

        container.addView(rootView);

        return rootView;
    }

    @Override
    public int getCount() {

        return mPagerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Don't call the super
        // super.destroyItem(container, position, object);

        container.removeView((View) object);
    }

    class ViewHolder {

        public ImageView imageView;

        ViewHolder(View rootView) {
            imageView = rootView.findViewById(R.id.imageView);
        }
    }
}
