package com.omaressam.hangin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceHolder> {

    private List<Place> places;
    private PlaceItemClick placeItemClick;

    public PlaceAdapter(List<Place> places, PlaceItemClick placeItemClick ) {
        this.places = places;
        this.placeItemClick = placeItemClick;
    }

    @NonNull
    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_place, parent, false);
        return new PlaceHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceHolder holder, final int position) {
        Place place = places.get(position);
        holder.bindView(place);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeItemClick.onItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }
}
