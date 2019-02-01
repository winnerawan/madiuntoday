package net.winnerawan.madiun.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.codesgood.views.JustifiedTextView;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.di.component.ActivityComponent;
import net.winnerawan.madiun.ui.base.BaseFragment;

import javax.inject.Inject;

public class AboutFragment extends BaseFragment implements AboutView, OnMapReadyCallback {

    @Inject
    AboutMvpPresenter<AboutView> presenter;

    @BindView(R.id.mdt)
    TextView tabMdt;
    @BindView(R.id.dev)
    TextView tabDev;
    @BindView(R.id.text_merchant_phone)
    TextView txtPhone;
    @BindView(R.id.text_merchant_name)
    TextView txtTitle;
    @BindView(R.id.text_merchant_address)
    TextView txtAddress;
    @BindView(R.id.divider_address)
    View dividerAddress;
    @BindView(R.id.containerAddress)
    LinearLayout containerAddress;
    @BindView(R.id.ic_addr)
    ImageView icAddr;
    @BindView(R.id.text_merchant_phone_not_available)
    TextView mTel;
    @BindView(R.id.text_merchant_description)
    JustifiedTextView txtDesc;
    private GoogleMap gGoogleMap;

    public static AboutFragment newInstance() {

        Bundle args = new Bundle();

        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
            createMap(savedInstanceState);
        }


        //behavior select tab
        tabMdt.setOnClickListener(v -> {
            setupMap();
            setNonActive();
            setContact(getString(R.string.discon), getString(R.string.teldiscon));
            setAddressMdt();
            tabMdt.setBackground(getResources().getDrawable(R.drawable.selector_social_tab));
            tabMdt.setTextColor(getResources().getColor(android.R.color.white));
        });

        tabDev.setOnClickListener(v -> {
            moveMapToDeveloperHome();
            setContact(getString(R.string.devcon), getString(R.string.teldevcon));
            setNonActive();
            setAddressDev();
            tabDev.setBackground(getResources().getDrawable(R.drawable.selector_social_tab));
            tabDev.setTextColor(getResources().getColor(android.R.color.white));
        });
        return view;
    }

    @OnClick(R.id.button_call)
    void call() {
        Intent dial = new Intent(Intent.ACTION_DIAL);
        dial.setData(Uri.parse(mTel.getText().toString()));
        startActivity(dial);
    }

    @Override
    protected void setUp(View view) {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gGoogleMap = googleMap;
        setupMap();
        setAddressMdt();
        setContact(getString(R.string.discon), getString(R.string.teldiscon));
    }

    private void setAddressDev() {
        icAddr.setBackground(getResources().getDrawable(R.drawable.ic_action_verified_user));
        txtTitle.setText(getString(R.string.dev_need_to_work).toUpperCase());
        txtAddress.setText(getString(R.string.dev_email).toUpperCase());
        txtDesc.setVisibility(View.GONE);
    }

    private void setAddressMdt() {
        icAddr.setBackground(getResources().getDrawable(R.drawable.food_ic_merchant_detail));
        txtTitle.setText(getString(R.string.app_name).toUpperCase());
        txtAddress.setText(getString(R.string.address_mdt));
        txtDesc.setVisibility(View.VISIBLE);
        txtDesc.setText(getString(R.string.mdt_desc));
    }

    private void createMap(Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapview);
        if (mapFragment!=null) {
            mapFragment.onCreate(savedInstanceState);
            mapFragment.getMapAsync(this);
        }
    }

    private void setupMap() {
        LatLng position = new LatLng(-7.6251331, 111.5213201);
        gGoogleMap.addMarker(new MarkerOptions().position(position).title(getString(R.string.dinas_kominfo)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_map_marker)));
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(position, 15.0f);
        gGoogleMap.animateCamera(location);
    }

    private void moveMapToDeveloperHome() {
        LatLng position = new LatLng(-7.6400607, 111.5375317);
        gGoogleMap.addMarker(new MarkerOptions().position(position).title(getString(R.string.dev)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_map_marker)));
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(position, 15.0f);
        gGoogleMap.animateCamera(location);
    }

    private void setContact(String contact, String tel) {
        txtPhone.setText(contact);
        mTel.setText(tel);
    }

    private void setNonActive() {
        tabDev.setBackground(getResources().getDrawable(R.drawable.selector_social_tab_for_white));
        tabDev.setTextColor(getResources().getColor(android.R.color.black));
        tabMdt.setBackground(getResources().getDrawable(R.drawable.selector_social_tab_for_white));
        tabMdt.setTextColor(getResources().getColor(android.R.color.black));
    }
}
