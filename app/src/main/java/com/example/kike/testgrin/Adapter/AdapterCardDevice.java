package com.example.kike.testgrin.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kike.testgrin.Model.Device;
import com.example.kike.testgrin.R;
import com.example.kike.testgrin.View.Interface.IFragment_Search_View;

import java.util.List;


public class AdapterCardDevice extends RecyclerView.Adapter<AdapterCardDevice.CardUserHolder>
{
    private final List<Device> deviceList;
    private final int resource;
    private final IFragment_Search_View fragmentView;

    public AdapterCardDevice(final List<Device> deviceList, final int resource, final IFragment_Search_View view) {
        this.deviceList = deviceList;
        this.resource = resource;
        this.fragmentView = view;
    }

    @Override
    public CardUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(this.resource, parent, false);
        return new CardUserHolder(view);
    }

    @Override
    public void onBindViewHolder(CardUserHolder holder, int position) {
        final Device deviceItem = deviceList.get(position);
        holder.tvDeviceName.setText(deviceItem.getName());
        holder.tvStrength.setText(deviceItem.getStrength());

        if(deviceItem.getCreated() == null)
        {
            holder.tvDateLabel.setVisibility(View.GONE);
            holder.tvDate.setVisibility(View.GONE);
            holder.ivUploadDevice.setVisibility(View.VISIBLE);
            holder.ivUploadDevice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentView.DeviceUpload(deviceItem.getName(), deviceItem.getStrength());
                }
            });
        }
        else
        {
            holder.ivUploadDevice.setVisibility(View.GONE);
            holder.tvDateLabel.setVisibility(View.VISIBLE);
            holder.tvDate.setVisibility(View.VISIBLE);
            holder.tvDate.setText(deviceItem.getCreated().toLocaleString());
        }
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public class CardUserHolder extends RecyclerView.ViewHolder {

        public final TextView tvDeviceName, tvStrength , tvDate, tvDateLabel;
        public final ImageView ivUploadDevice;

        public CardUserHolder(View itemView) {
            super(itemView);
            tvDeviceName = itemView.findViewById(R.id.tvDeviceName);
            tvStrength = itemView.findViewById(R.id.tvStrength);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDateLabel = itemView.findViewById(R.id.tvDateLabel);
            ivUploadDevice = itemView.findViewById(R.id.ivUploadDevice);
        }
    }
}
