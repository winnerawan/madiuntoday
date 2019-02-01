package net.winnerawan.madiun.ui.category;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.ui.base.BaseViewHolder;
import net.winnerawan.madiun.ui.helper.ItemTouchHelperAdapter;
import net.winnerawan.madiun.ui.helper.ItemTouchHelperViewHolder;
import net.winnerawan.madiun.ui.helper.OnStartDragListener;

public class CategoryAdapter
        extends RecyclerView.Adapter<BaseViewHolder>
        implements ItemTouchHelperAdapter {

    private final static int VIEW_TYPE_EMPTY = 0;
    private final static int VIEW_TYPE_NORMAL = 1;
    private final static int VIEW_TYPE_HEADER = 2;
    private List<Category> list;
    private int selectedPosition = 0;
    private boolean isItemEnabled = false;
    private int fromPosition = 0;
    private int toPosition = 0;

    public CategoryAdapter(List<Category> list) {
        this.list = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_category_header_rv, parent, false));
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_category_rv, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_empty_rv, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (list != null && list.size() > 0) {
//            if (position == 0) return VIEW_TYPE_HEADER;
            return VIEW_TYPE_NORMAL;
        }
        return VIEW_TYPE_EMPTY;
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 1;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (toPosition == 0 || toPosition == 1) {
            return false;
        }
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
//        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
        selectedPosition = toPosition;
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    void addItems(List<Category> categories) {
        list.clear();
        list.addAll(categories);
        notifyDataSetChanged();
    }

    void addItem(Category category) {
        list.add(category);
        notifyDataSetChanged();
//        notifyItemInserted(0);
//        notifyItemRangeInserted(0, newArticles.size());

//        notifyItemChanged(list.size());
//        notifyItemRangeChanged(list.size() - 1, list.size());
    }

    private Callback callback;
    private OnStartDragListener dragStartListener;

    void setCallback(Callback callback) {
        this.callback = callback;
    }

    void setDragStartListener(OnStartDragListener dragStartListener) {
        this.dragStartListener = dragStartListener;
    }

    void setSelectedPosition(int position) {
        selectedPosition = (position + 1);
    }

    void setEnabledUpdateItem(boolean b) {
        this.isItemEnabled = b;
    }

    public interface Callback {
        void onItemCategoryClick(Category category, int position);

        void onRemoveItemCategory(Category category, int position);

        void onChangePosition(List<Category> list);
    }

    class ViewHolder extends BaseViewHolder implements ItemTouchHelperViewHolder {
        @BindView(R.id.category_name_tv)
        TextView categoryNameTv;
        @BindView(R.id.category_setting_iv)
        ImageView categorySettingIv;
        @BindView(R.id.category_decrease_iv)
        ImageView categoryDecreaseIv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onBind(int position) {
            super.onBind(position);
            Category category = list.get(position);
            categoryNameTv.setText(category.getName());

            if (position == 0 || position == 1) {
                categoryNameTv.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary));
                categoryDecreaseIv.setVisibility(View.GONE);
                categorySettingIv.setVisibility(View.GONE);
            }

            if (selectedPosition == position) {
                itemView.setBackgroundResource(R.color.light_gray);
            } else {
                itemView.setBackgroundResource(R.color.white);
            }

            if (isItemEnabled && position != 0 && position != 1) {
                categoryDecreaseIv.setVisibility(View.VISIBLE);
            } else {
                categoryDecreaseIv.setVisibility(View.GONE);
            }

            if (position != 0 && position != 1) {
                itemView.setOnClickListener(v -> {
                    if (callback != null) {
                        callback.onItemCategoryClick(category, position);
                    }
                });
            }

            categoryDecreaseIv.setOnClickListener(v -> {
                if (callback != null) {
                    callback.onRemoveItemCategory(category, position);
                    list.remove(position);
                    notifyItemChanged(position);
                    notifyItemRangeChanged(position, list.size());
                }
            });

            categorySettingIv.setOnTouchListener((v, event) -> {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
//                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    if (dragStartListener != null) {
                        fromPosition = position;
                        dragStartListener.onStartDrag(ViewHolder.this);
                    }
                }
                return false;
            });
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
            if (callback != null) {
                callback.onChangePosition(list);
            }
        }

        @Override
        protected void clear() {

        }

    }

    class HeaderViewHolder extends BaseViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void clear() {

        }
    }

    class EmptyViewHolder extends BaseViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void clear() {

        }
    }
}
