package net.winnerawan.madiun.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.adapter.PostAdapter;
import net.winnerawan.madiun.ui.base.BaseActivity;
import net.winnerawan.madiun.ui.detail.DetailActivity;

public class SearchActivity extends BaseActivity implements SearchView, PostAdapter.Callback {

    @Inject
    SearchMvpPresenter<SearchView> mPresenter;

    @Inject
    PostAdapter adapter;
    @BindView(R.id.recycler_news)
    RecyclerView mRecyclerView;

    @BindView(R.id.input_search)
    EditText txtSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
        //search action
        txtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.search(txtSearch.getText().toString());
                return true;
            }
            return false;
        });
    }

    @OnClick(R.id.img_clear_search)
    void clear() {
        txtSearch.setText("");
    }

    @OnClick(R.id.img_back)
    void back() {
        finish();
    }

    @Override
    protected void setUp() {
        adapter.setCallback(this);
    }

    @Override
    public void onPostSelected(Post post) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("post", post);
        intent.putExtra("url", post.getLink());
        intent.putExtra("image", post.getJetpackFeaturedMediaUrl());
        startActivity(intent);
    }

    @Override
    public void showNews(List<Post> news) {
        adapter.addItems(news);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(adapter);
    }
}
