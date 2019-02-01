package net.winnerawan.madiun.ui.category;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.ui.base.BaseViewHolder;


public class CategoryOtherAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private SparseBooleanArray selectedItems;

    private final static int VIEW_TYPE_EMPTY = 0;
    private final static int VIEW_TYPE_NORMAL = 1;
    private final static int VIEW_TYPE_HEADER = 2;
    private List<Category> list;
    private int selectedPosition = 0;
    private boolean isItemEnabled = false;

    public CategoryOtherAdapter(List<Category> list) {
        this.list = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_other_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        /*if (list != null && list.size() > 0) {
            return list.size();
        }
        return 1;*/
        if (list.size() == 1) {
            return 0;
        }
        return list.size();
    }

    public void addItem(Category categories) {
        if (list.size() == 0) list.add(0, new Category()); //fixme
        list.add(categories);
        notifyDataSetChanged();
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = (position + 1);
    }

    public void setEnabledUpdateItem(boolean b) {
        this.isItemEnabled = b;
    }

    public void addItems(List<Category> categories) {
        list.clear();
        list.addAll(categories);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onAddItemCategoryClick(Category category, int position);
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.category_name_tv)
        TextView categoryNameTv;
        @BindView(R.id.category_add_iv)
        ImageView categoryAddIv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            Category category = list.get(position);
            categoryNameTv.setText(category.getName());

            if (position == 0) {
                categoryNameTv.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary));
                categoryAddIv.setVisibility(View.GONE);
            }

            if (isItemEnabled && position != 0) {
                categoryAddIv.setVisibility(View.VISIBLE);
            } else {
                categoryAddIv.setVisibility(View.GONE);
            }

            categoryAddIv.setOnClickListener(v -> {
                if (callback != null) {
                    list.remove(position);
                    notifyDataSetChanged();

//                    notifyItemChanged(position);
//                    notifyItemRangeChanged(position, list.size());
                    callback.onAddItemCategoryClick(category, position);
                }
            });


        }

        @Override
        protected void clear() {

        }
    }

}
