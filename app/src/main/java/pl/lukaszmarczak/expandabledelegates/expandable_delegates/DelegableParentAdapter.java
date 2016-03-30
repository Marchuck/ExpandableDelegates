package pl.lukaszmarczak.expandabledelegates.expandable_delegates;

import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.util.List;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public abstract class DelegableParentAdapter<PARENT, CHILD> {
    private DelegatesManager<PARENT, CHILD> delegatesManager;

    /**
     * Get int viewType as identifier which adapter compares with ExpandableParent's viewType to see
     * if this adapter is made for the Parent
     *
     * @return viewType
     */
    public abstract int getParentViewType();

    /**
     * @param holder ViewHolder which extends AbstractExpandableItemViewHolder
     *               (must, because 3rd party library needs it)
     * @param groupPosition
     * @param viewType - described above
     */

    public abstract void onBindGroupViewHolder(AbstractExpandableItemViewHolder holder, int groupPosition,
                                               int viewType);

    public abstract AbstractExpandableItemViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType);

    public List<XParent<PARENT, CHILD>> getDataSet() {
        return delegatesManager.getDataSet();
    }

    public void setManager(DelegatesManager<PARENT, CHILD> manager) {
        this.delegatesManager = manager;
    }
}
