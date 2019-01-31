package net.winnerawan.madiun.ui.webview;


import net.winnerawan.madiun.di.PerActivity;
import net.winnerawan.madiun.ui.base.MvpPresenter;

@PerActivity
public interface WebviewMvpPresenter<V extends WebviewView> extends MvpPresenter<V> {
}
