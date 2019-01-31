package net.winnerawan.madiun.ui.gallery;

import com.androidnetworking.error.ANError;
import io.reactivex.functions.Consumer;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Gallery;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.data.network.response.GalleryResponse;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

import java.util.ArrayList;
import java.util.List;

public class GalleryPresenter<V extends GalleryView> extends BasePresenter<V> implements GalleryMvpPresenter<V> {

    @Inject
    public GalleryPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getGalleries(Category category) {
        getCompositeDisposable().add(getDataManager().getGalleries(category)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(galleries -> {
                    if (galleries!=null) {
                        reGenerateGalleries(galleries);
                    }
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }

    private void reGenerateGalleries(List<Gallery> galleries) {
        for (Gallery gallery: galleries) {
            getCompositeDisposable().add(getDataManager().fetchGallery(gallery.getLink())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribeOn(getSchedulerProvider().io())
                    .subscribe(galleryResponse -> {
                        if (galleryResponse!=null) {
                            gallery.setDescription(galleryResponse.getDescription());
                            gallery.setImages(galleryResponse.getImages());
                            getMvpView().showGalleries(galleries);
                            getMvpView().stopShimmer();
                        }
                    }, throwable -> {

                    }));
        }
    }
}
