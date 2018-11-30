package com.share.info.skyline.FeatureFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.share.info.skyline.Database.DataFetchCallback;
import com.share.info.skyline.Model.CondoController;
import com.share.info.skyline.R;
import com.share.info.skyline.RecyclerViewAdapters.AnnouncementsAdapter;

/**
 * Fragment to display the list of Announcements
 */
public class AnnouncementFragment extends Fragment implements DataFetchCallback{

    private RecyclerView recyclerView;
    private AnnouncementsAdapter announcementsAdapter;
    private CondoController condoController = CondoController.getInstance();

    SwipeRefreshLayout pullToRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.announcements_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.announcementsList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);

        announcementsAdapter = new AnnouncementsAdapter(getActivity(), condoController.getCondoAnnouncements());
        recyclerView.setAdapter(announcementsAdapter);


        pullToRefresh = (SwipeRefreshLayout) view.findViewById(R.id.announcementsPullToRefresh);

        final DataFetchCallback dataFetchCallback = this;
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                condoController.fetch(dataFetchCallback);
            }
        });

    }

    @Override
    public void onDataFetch() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
        pullToRefresh.setRefreshing(false);
    }
}
