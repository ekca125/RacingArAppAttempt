package net.ekcapaper.racingar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.ekcapaper.racingar.components.R;
import net.ekcapaper.racingar.components.adapter.AdapterListBasic;
import net.ekcapaper.racingar.components.model.People;
import net.ekcapaper.racingar.components.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class AdapterGameLobby extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //모델로 변경
    private List<People> items = new ArrayList<>();

    private Context ctx;
    private AdapterListBasic.OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, People obj, int position);
    }

    public void setOnItemClickListener(final AdapterListBasic.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterGameLobby(Context context, List<People> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            name = (TextView) v.findViewById(R.id.name);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people_chat, parent, false);
        vh = new AdapterGameLobby.OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int temp) {
        if (holder instanceof AdapterListBasic.OriginalViewHolder) {
            AdapterListBasic.OriginalViewHolder view = (AdapterListBasic.OriginalViewHolder) holder;

            int position = holder.getAdapterPosition();

            People p = items.get(position);
            view.name.setText(p.name);
            Tools.displayImageRound(ctx, view.image, p.image);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
