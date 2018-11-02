package com.share.info.skyline.RecyclerViewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.share.info.skyline.Model.Event;
import com.share.info.skyline.R;

import java.util.List;

public class EventsAdapter extends
        RecyclerView.Adapter<EventsAdapter.EventsViewHolder>{

    private Context context;
    private List<Event> eventList;

    public EventsAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }


    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_list_item, null);

        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EventsViewHolder holder, int position) {
        // add in reverse order
        Event eventToAdd = eventList.get(eventList.size() - 1 - position);

        //holder.commentOwner.setText(commentList.get(position).getMessageOwner().getName());
        holder.eventTitle.setText(eventToAdd.getTitle());
        holder.eventDetails.setText(eventToAdd.getDescription());
        holder.eventDate.setText(eventToAdd.getDate().toLocaleString());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventsViewHolder extends RecyclerView.ViewHolder{

        TextView eventTitle;
        TextView eventDetails;
        TextView eventDate;


        public EventsViewHolder(View itemView) {
            super(itemView);

            eventTitle = (TextView) itemView.findViewById(R.id.eventItemTitle);
            eventDetails = (TextView) itemView.findViewById((R.id.eventItemDetails));
            eventDate = (TextView) itemView.findViewById(R.id.eventItemDate);
        }
    }
}
