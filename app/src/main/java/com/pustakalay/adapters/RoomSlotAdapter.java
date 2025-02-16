package com.pustakalay.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pustakalay.databinding.ItemRoomSlotBinding;
import com.pustakalay.models.RoomSlot;

import java.util.List;

public class RoomSlotAdapter extends RecyclerView.Adapter<RoomSlotAdapter.RoomSlotViewHolder> {
    private final List<RoomSlot> slots;
    private final OnSlotClickListener listener;

    public interface OnSlotClickListener {
        void onSlotClick(int position, String status);
    }

    public RoomSlotAdapter(List<RoomSlot> slots, OnSlotClickListener listener) {
        this.slots = slots;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoomSlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRoomSlotBinding binding = ItemRoomSlotBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false);
        return new RoomSlotViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomSlotViewHolder holder, int position) {
        RoomSlot slot = slots.get(position);
        holder.binding.tvTime.setText(slot.getTime());
        holder.binding.tvStatus.setText(slot.getStatus());
        holder.itemView.setOnClickListener(v -> 
            listener.onSlotClick(position, slot.getStatus()));
    }

    @Override
    public int getItemCount() {
        return slots.size();
    }

    static class RoomSlotViewHolder extends RecyclerView.ViewHolder {
        private final ItemRoomSlotBinding binding;

        RoomSlotViewHolder(ItemRoomSlotBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
