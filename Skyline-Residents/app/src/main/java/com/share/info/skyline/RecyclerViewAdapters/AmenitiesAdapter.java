package com.share.info.skyline.RecyclerViewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.R;

import java.util.List;

public class AmenitiesAdapter extends RecyclerView.Adapter<AmenitiesAdapter.AmmenitiesViewHolder> {

    private Context context;
    private List<Amenity> ammenitiesList;

    public AmenitiesAdapter(Context context, List<Amenity> ammenitiesList) {
        this.context = context;
        this.ammenitiesList = ammenitiesList;
    }

    @NonNull
    @Override
    public AmenitiesAdapter.AmmenitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.amenity_item_list, null);

        return new AmenitiesAdapter.AmmenitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AmenitiesAdapter.AmmenitiesViewHolder holder, int position) {
        // add in reverse order
        Amenity amenityToAdd = ammenitiesList.get(ammenitiesList.size() - 1 - position);

        //holder.commentOwner.setText(commentList.get(position).getMessageOwner().getName());
        holder.amenityName.setText(amenityToAdd.getName());
        holder.amenityDetails.setText(amenityToAdd.getDetails());
    }

    @Override
    public int getItemCount() {
        return ammenitiesList.size();
    }

    class AmmenitiesViewHolder extends RecyclerView.ViewHolder{

        TextView amenityName;
        TextView amenityDetails;


        public AmmenitiesViewHolder(View itemView) {
            super(itemView);

            amenityName = (TextView) itemView.findViewById(R.id.amenityItemName);
            amenityDetails = (TextView) itemView.findViewById((R.id.amenityItemDetails));
        }
    }
}
