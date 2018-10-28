package com.share.info.skyline.FeatureFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.share.info.skyline.Database.CondoController;
import com.share.info.skyline.R;
import com.share.info.skyline.RecyclerViewAdapters.EventsAdapter;

public class EventsFragment extends Fragment {

    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    private CondoController condoController = CondoController.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.events_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.eventsList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        eventsAdapter = new EventsAdapter(getActivity(), condoController.getCondoEvent());
        recyclerView.setAdapter(eventsAdapter);


    }
}
