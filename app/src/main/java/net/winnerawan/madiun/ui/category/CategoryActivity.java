package net.winnerawan.madiun.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.TabEvents;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.ui.base.BaseActivity;
import net.winnerawan.madiun.ui.helper.OnStartDragListener;
import net.winnerawan.madiun.ui.helper.SimpleItemTouchHelperCallback;
import net.winnerawan.madiun.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import org.greenrobot.eventbus.EventBus;

public class CategoryActivity extends BaseActivity implements CategoryView, CategoryAdapter.Callback, CategoryOtherAdapter.Callback, OnStartDragListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.category_rv)
    RecyclerView categoryRv;
    @BindView(R.id.category_other_rv)
    RecyclerView categoryOtherRv;
    @Inject
    CategoryAdapter adapter;
    @Inject
    CategoryOtherAdapter otherAdapter;
    @Inject
    CategoryMvpPresenter<CategoryView> presenter;

    private int position = 0;
    private MenuItem menuEdit;
    private MenuItem menuCompleted;

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(this);
        adapter.setCallback(this);
        adapter.setDragStartListener(this);
        otherAdapter.setCallback(this);
        setUp();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);

        position = getIntent().getIntExtra(AppConstants.EXTRAS_TAB_POSITION, 0);

        categoryRv.setLayoutManager(new LinearLayoutManager(this));
        categoryRv.setAdapter(adapter);

        categoryOtherRv.setLayoutManager(new LinearLayoutManager(this));
        categoryOtherRv.setAdapter(otherAdapter);

        categoryRv.setHasFixedSize(true);
        categoryOtherRv.setHasFixedSize(true);

        adapter.setSelectedPosition(position);
        ViewCompat.setNestedScrollingEnabled(categoryRv, false);
        ViewCompat.setNestedScrollingEnabled(categoryOtherRv, false);
        presenter.loadCategories();
        presenter.loadCategoriesOther();

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(categoryRv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_category, menu);
        menuEdit = menu.findItem(R.id.menu_edit);
        menuCompleted = menu.findItem(R.id.menu_completed);
        menuEdit.setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        if (item.getItemId() == R.id.menu_edit) {
            item.setVisible(false);
            menuCompleted.setVisible(true);
            adapter.setEnabledUpdateItem(true);
            otherAdapter.setEnabledUpdateItem(true);
            adapter.notifyDataSetChanged();
            otherAdapter.notifyDataSetChanged();
        }

        if (item.getItemId() == R.id.menu_completed) {
            item.setVisible(false);
            menuEdit.setVisible(true);
            adapter.setEnabledUpdateItem(false);
            otherAdapter.setEnabledUpdateItem(false);
            adapter.notifyDataSetChanged();
            otherAdapter.notifyDataSetChanged();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setMenuEditEnabled() {
//        menuEdit.setEnabled(true);
    }

    @Override
    public void displayCategories(List<Category> categories) {
        adapter.addItems(categories);
    }

    @Override
    public void displayCategoriesOther(List<Category> categories) {
        otherAdapter.addItems(categories);
    }

    @Override
    public void categoriesAdapterNotifyChanged() {
        adapter.notifyDataSetChanged();
        otherAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemCategoryClick(Category category, int position) {
        Intent intent = new Intent();
        intent.putExtra(AppConstants.EXTRAS_TAB_POSITION, position);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onRemoveItemCategory(Category category, int position) {
        otherAdapter.addItem(category);
        otherAdapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
        presenter.removeItemCategory(category);
    }

    @Override
    public void onAddItemCategoryClick(Category category, int position) {
        adapter.addItem(category);
        adapter.notifyDataSetChanged();
        otherAdapter.notifyDataSetChanged();
        presenter.addItemCategory(category);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onChangePosition(List<Category> list) {
        presenter.replaceCurrentCategories(list);
    }

    @Override
    public void postTabEvent() {
        EventBus.getDefault().post(new TabEvents());
    }
}
