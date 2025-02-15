package com.pustakalay.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import com.pustakalay.databinding.FragmentRoomsBinding;
import com.pustakalay.models.RoomSlot;
import com.pustakalay.adapters.RoomSlotAdapter;

import java.util.ArrayList;
import java.util.List;

public class RoomsFragment extends Fragment {
    private FragmentRoomsBinding binding;
    private List<RoomSlot> timeSlots = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRoomsBinding.inflate(inflater, container, false);
        initializeTimeSlots();
        setupRoomGrid();
        return binding.getRoot();
    }

    private void initializeTimeSlots() {
        // Generate time slots from 9 AM to 5 PM
        for (int hour = 9; hour <= 17; hour++) {
            timeSlots.add(new RoomSlot(
                String.format("%02d:00 - %02d:00", hour, hour+1),
                "Available"
            ));
        }
    }

    private void setupRoomGrid() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        binding.gridRooms.setLayoutManager(layoutManager);
        
        RoomSlotAdapter adapter = new RoomSlotAdapter(timeSlots, (position, status) -> {
            RoomSlot slot = timeSlots.get(position);
            if (status.equals("Available")) {
                showBookingConfirmation(position);
            } else {
                Toast.makeText(getContext(), "Slot already booked", Toast.LENGTH_SHORT).show();
            }
        });
        
        binding.gridRooms.setAdapter(adapter);
    }

    private void showBookingConfirmation(int position) {
        new AlertDialog.Builder(getContext())
            .setTitle("Confirm Booking")
            .setMessage("Book this time slot?")
            .setPositiveButton("Book", (dialog, which) -> {
                timeSlots.get(position).setStatus("Booked");
                binding.gridRooms.getAdapter().notifyItemChanged(position);
            })
            .setNegativeButton("Cancel", null)
            .show();
    }
} 