package com.example.kike.testgrin.View.Implementation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.kike.testgrin.Adapter.AdapterCardDevice;
import com.example.kike.testgrin.Model.Device;
import com.example.kike.testgrin.Model.ErrorType;
import com.example.kike.testgrin.Presenter.Implementation.Fragment_Registered_Presenter;
import com.example.kike.testgrin.R;
import com.example.kike.testgrin.View.Base.BaseView;
import com.example.kike.testgrin.View.Interface.IFragment_Registered_View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment_Registered extends BaseView implements IFragment_Registered_View, View.OnClickListener
{

    @BindView(R.id.fbtnRegistered)
    FloatingActionButton btnRegistered;

    @BindView(R.id.fbtnOrder)
    FloatingActionButton fbtnOrder;

    @BindView(R.id.rvRegistered)
    RecyclerView rvRegistered;

    private List<Device> deviceList = new ArrayList<>();
    private AdapterCardDevice adapterCardDevice;
    private Fragment_Registered_Presenter presenter;

    public Fragment_Registered() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_registered, container, false);
        this.initControl(view);
        this.createRecyrcleView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.deviceList.clear();
    }

    @Override
    public void ShowListDevices(final List<Device> deviceList)
    {
        int counter = 0;
        for (Device item : deviceList)
        {
            item.setPosition(counter);
            counter++;
        }


        super.ClosedModal();
        this.deviceList.clear();
        this.deviceList.addAll(deviceList);
        this.adapterCardDevice.notifyDataSetChanged();
    }

    @Override
    public void Error(final ErrorType errorType, final String message) {
        super.ClosedModal();
        Snackbar.make(getView(),message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.fbtnRegistered:
                super.ShowModal(getActivity(), getString(R.string.modal_donwload_info), true, false);
                this.presenter.GetListDevice();
                break;
            case R.id.fbtnOrder:
                OrderListByDate();
                break;
        }
    }

    private void OrderListByDate()
    {
        Collections.sort(this.deviceList, new Comparator<Device>() {
            @Override
            public int compare(final Device deviceBefore, final Device deviceAfter) {
                return new Integer(deviceAfter.getPosition()).compareTo(new Integer(deviceBefore.getPosition()));
            }
        });

        this.adapterCardDevice.notifyDataSetChanged();
    }

    private void initControl(final View view)
    {
        ButterKnife.bind(this, view);
        this.btnRegistered.setOnClickListener(this);
        this.fbtnOrder.setOnClickListener(this);
        presenter = new Fragment_Registered_Presenter(this, getActivity());
    }

    private void createRecyrcleView()
    {
        this.adapterCardDevice = new AdapterCardDevice(this.deviceList, R.layout.card_device, null);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        this.rvRegistered.setHasFixedSize(true);
        this.rvRegistered.setLayoutManager(linearLayoutManager);
        this.rvRegistered.setItemAnimator(new DefaultItemAnimator());
        this.rvRegistered.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        this.rvRegistered.setAdapter(this.adapterCardDevice);
    }
}
