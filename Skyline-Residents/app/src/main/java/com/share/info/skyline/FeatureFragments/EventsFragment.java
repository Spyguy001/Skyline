package com.share.info.skyline.FeatureFragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.share.info.skyline.Database.DataFetchCallback;
import com.share.info.skyline.LoginActivity;
import com.share.info.skyline.Model.CondoController;
import com.share.info.skyline.Model.Event;
import com.share.info.skyline.R;
import com.share.info.skyline.RecyclerViewAdapters.EventsAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Fragment to display the list of Events
 */
public class EventsFragment extends Fragment implements DataFetchCallback {

    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    private CondoController condoController = CondoController.getInstance();

    SwipeRefreshLayout pullToRefresh;
    private FloatingActionButton addEventFab;

    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private EditText eventTime;

    private Date eventToAddDate;

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

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);

        eventsAdapter = new EventsAdapter(getActivity(), condoController.getCondoEvent());
        recyclerView.setAdapter(eventsAdapter);

        pullToRefresh = (SwipeRefreshLayout) view.findViewById(R.id.eventsPullToRefresh);

        final DataFetchCallback dataFetchCallback = this;
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                condoController.fetch(dataFetchCallback);
            }
        });

        addEventFab = (FloatingActionButton) view.findViewById(R.id.addEventFab);
        addEventFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddEventBox();
            }
        });

    }

    private void showAddEventBox() {

        View promptView = LayoutInflater.from(getContext()).inflate(R.layout.add_event_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);

        final EditText eventName = (EditText) promptView.findViewById(R.id.add_event_name);
        final EditText eventDetails = (EditText) promptView.findViewById(R.id.add_event_details);
        final EditText eventLocation = (EditText) promptView.findViewById(R.id.add_event_location);
        eventTime = (EditText) promptView.findViewById(R.id.add_event_date);

        eventTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateTimePicker();
            }
        });

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                if (eventName.getText().toString().isEmpty() ||
                                        eventTime.getText().toString().isEmpty()) {
                                    android.support.v7.app.AlertDialog.Builder builder =
                                            new android.support.v7.app.AlertDialog.Builder(getContext());
                                    builder.setMessage("Please provide a date and name for the event");
                                    builder.show();
                                } else {
                                    pullToRefresh.setRefreshing(true);
                                    createAndAddEvent(
                                            eventName.getText().toString(),
                                            eventDetails.getText().toString(),
                                            eventLocation.getText().toString()
                                    );
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void dateTimePicker() {

        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {

                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth, i, i1);
                        eventToAddDate = newDate.getTime();
                        eventTime.setText(dateFormatter.format(eventToAddDate));
                    }
                }, 0, 0, false);


                timePickerDialog.show();
            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    private void createAndAddEvent(String name, String details, String location) {
        Event event = new Event();

        // generate and set id
        event.setId(String.valueOf(System.currentTimeMillis()));

        event.setTitle(name);
        event.setDescription(details);
        event.setDate(eventToAddDate);
        event.setLocation(location);

        condoController.addEvent(event);

        condoController.fetch(this);

    }

    @Override
    public void onDataFetch() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
        pullToRefresh.setRefreshing(false);
    }
}
