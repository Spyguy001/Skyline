package com.share.info.skyline.FeatureFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.share.info.skyline.Model.CondoController;
import com.share.info.skyline.R;
import com.share.info.skyline.RecyclerViewAdapters.AmenitiesAdapter;

public class AmenitiesFragment extends Fragment {
    private RecyclerView recyclerView;
    private AmenitiesAdapter amenitiesAdapter;
    private CondoController condoController = CondoController.getInstance();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.amenities_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.amenitiesList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);

        amenitiesAdapter = new AmenitiesAdapter(getActivity(), condoController.getCondoAmenities());
        recyclerView.setAdapter(amenitiesAdapter);

    }

}
