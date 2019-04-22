package dev.qamar.a24itest.helpers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qamar4P on 3/5/2018.
 *
 * @author qamar.dev@gmail.com
 */
public class LolAdapter<D,VH extends LolViewHold<D>> extends RecyclerView.Adapter<VH>{

    public List<D> items = new ArrayList<>();

    ViewHolderCreator<VH> viewHolderCreator;
    private final ItemViewClickListener<D> viewClickListener;

    public LolAdapter(@Nullable ItemViewClickListener<D> itemViewClickListener, @NonNull ViewHolderCreator<VH> viewHolderCreator){
        this.viewClickListener = itemViewClickListener;
        this.viewHolderCreator = viewHolderCreator;
    }

    @NotNull
    @Override
    public VH onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        VH vh = viewHolderCreator.create(parent);
        vh.viewClickListener = viewClickListener;
        return vh;
    }

    @Override
    public void onBindViewHolder(@NotNull VH holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void onNewData(DiffUtil.Callback callback, List<D> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        this.items.clear();
        this.items.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
    }
}