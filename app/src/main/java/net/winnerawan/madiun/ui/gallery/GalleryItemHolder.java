package net.winnerawan.madiun.ui.gallery;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.codesgood.views.JustifiedTextView;
import com.rd.PageIndicatorView;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.ui.helper.SquareViewPager;

public class GalleryItemHolder extends RecyclerView.ViewHolder {

    public SquareViewPager viewPager;
    public PageIndicatorView indicator;
    public TextView txtAuthor;
    JustifiedTextView txtDescription;

    public GalleryItemHolder(@NonNull View itemView) {
        super(itemView);
        viewPager =  itemView.findViewById(R.id.viewpager_images);
        indicator = itemView.findViewById(R.id.pageIndicatorView);
        txtAuthor = itemView.findViewById(R.id.author);
        txtDescription = itemView.findViewById(R.id.description);
    }
}


