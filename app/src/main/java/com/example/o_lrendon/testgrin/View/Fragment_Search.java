package com.example.o_lrendon.testgrin.View;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.o_lrendon.testgrin.Adapter.AdapterCardDevice;
import com.example.o_lrendon.testgrin.Model.Device;
import com.example.o_lrendon.testgrin.Model.ErrorType;
import com.example.o_lrendon.testgrin.Presenter.Fragment_Search_Presenter;
import com.example.o_lrendon.testgrin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment_Search extends BaseView implements IFragment_Search_View, View.OnClickListener
{
    @BindView(R.id.fbtnDevicesSearch)
    FloatingActionButton btnDevicesSearch;

    @BindView(R.id.rvDevicesSearch)
    RecyclerView rvDevicesSearch;

    private List<Device> deviceList = new ArrayList<>();
    private AdapterCardDevice adapterCardDevice;
    private Fragment_Search_Presenter presenter;

    public Fragment_Search(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        this.initControl(view);
        this.createRecyrcleView();
        return view;
    }

    @Override
    public void ShowDetectedDevice(final Device device) {
        this.deviceList.add(0,device);
        this.adapterCardDevice.notifyItemInserted(0);
    }

    @Override
    public void Error(final ErrorType errorType, String message) {
        super.ClosedModal();
        switch (errorType)
        {
            case NotSupportBluettoth:
            case NotSearchDevice:
            case Fail:
                Snackbar.make(getView(),message, Snackbar.LENGTH_LONG).show();
                break;
            case BluettothOff:
                final Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableBluetooth);
                break;
        }
    }

    @Override
    public void Success(String message) {
        super.ClosedModal();
        Snackbar.make(getView(),message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void SuccessValidBluetooth()
    {
        this.deviceList.clear();
        this.adapterCardDevice.notifyDataSetChanged();
        this.presenter.GetListDevice();
    }

    @Override
    public void ShowModal(final String message) {
        super.ShowModal(getActivity(), message, true, true);
    }

    @Override
    public void ClosedModal() {
        super.ClosedModal();
    }

    @Override
    public void DeviceUpload(final String nameDevice, final String strength) {
        if ((nameDevice != null && !nameDevice.isEmpty()) && (strength != null && !strength.isEmpty())){
            this.ShowModal(getString(R.string.modal_device_upload));
            this.presenter.DeviceUpload(nameDevice, strength);
        }
        else
        {
            this.Error(ErrorType.Fail, getString(R.string.message_not_data) );
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.fbtnDevicesSearch :
                this.presenter.ValidateBluetooth();
                break;
        }
    }

    private void initControl(final View view)
    {
        ButterKnife.bind(this, view);
        this.btnDevicesSearch.setOnClickListener(this);
        presenter = new Fragment_Search_Presenter(this, getActivity());
    }

    private void createRecyrcleView()
    {
        this.adapterCardDevice = new AdapterCardDevice(this.deviceList, R.layout.card_device, this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        this.rvDevicesSearch.setHasFixedSize(true);
        this.rvDevicesSearch.setLayoutManager(linearLayoutManager);
        this.rvDevicesSearch.setItemAnimator(new DefaultItemAnimator());
        this.rvDevicesSearch.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        this.rvDevicesSearch.setAdapter(this.adapterCardDevice);
    }
}
