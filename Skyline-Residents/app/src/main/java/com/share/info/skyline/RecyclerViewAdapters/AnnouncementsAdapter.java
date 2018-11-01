package com.share.info.skyline.RecyclerViewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.R;

import java.util.List;

public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsAdapter.AnnouncementsViewHolder> {

    private Context context;
    private List<Announcement> announcementList;

    public AnnouncementsAdapter(Context context, List<Announcement> announcementList) {
        this.context = context;
        this.announcementList = announcementList;
    }

    @NonNull
    @Override
    public AnnouncementsAdapter.AnnouncementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.announcement_list_item, null);

        return new AnnouncementsAdapter.AnnouncementsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AnnouncementsAdapter.AnnouncementsViewHolder holder, int position) {
        // add in reverse order
        final Announcement annoucnementToAdd = announcementList.get(announcementList.size() - 1 - position);

        //holder.commentOwner.setText(commentList.get(position).getMessageOwner().getName());
        holder.announcementTitle.setText(annoucnementToAdd.getTitle());
        holder.announcementDetails.setText(annoucnementToAdd.getDescription());
        holder.announcementDate.setText(annoucnementToAdd.getDate().toString());

        if (annoucnementToAdd.getImportant() == null || !annoucnementToAdd.getImportant()) {
            holder.announcementFlag.setImageResource(0);
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(annoucnementToAdd.getDescription());
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }

    class AnnouncementsViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;

        TextView announcementTitle;
        TextView announcementDetails;
        TextView announcementDate;

        ImageView announcementFlag;


        public AnnouncementsViewHolder(View itemView) {
            super(itemView);

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.accouncementItemRelativeLayout);

            announcementTitle = (TextView) itemView.findViewById(R.id.announcementItemDetails);
            announcementDetails = (TextView) itemView.findViewById((R.id.announcementItemDetails));
            announcementDate = (TextView) itemView.findViewById(R.id.announcementItemDate);

            announcementFlag = (ImageView) itemView.findViewById(R.id.announceItemFlag);
        }
    }
}
