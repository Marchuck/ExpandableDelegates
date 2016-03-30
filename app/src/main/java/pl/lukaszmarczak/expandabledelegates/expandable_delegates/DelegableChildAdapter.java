package pl.lukaszmarczak.expandabledelegates.expandable_delegates;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public abstract class DelegableChildAdapter<PARENT, CHILD> {
    private DelegatesManager<PARENT, CHILD> delegatesManager;

    public abstract int getChildViewType();

    public abstract void onBindChildViewHolder(RecyclerView.ViewHolder holder, int groupPosition,
                                               int childPosition, int viewType);

    public abstract RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType);

    public List<XParent<PARENT, CHILD>> getDataSet() {
        return delegatesManager.getDataSet();
    }
    public void setManager(DelegatesManager<PARENT, CHILD> manager) {
        this.delegatesManager = manager;
    }
}
