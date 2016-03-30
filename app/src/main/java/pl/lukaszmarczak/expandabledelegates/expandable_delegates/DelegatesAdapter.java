package pl.lukaszmarczak.expandabledelegates.expandable_delegates;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public class DelegatesAdapter<PARENT, CHILD>
        extends AbstractExpandableItemAdapter<AbstractExpandableItemViewHolder,
        RecyclerView.ViewHolder> {

    private List<XParent<PARENT, CHILD>> dataSet = new ArrayList<>();
    private DelegatesManager<PARENT, CHILD> delegatesManager;

    public DelegatesAdapter(List<XParent<PARENT, CHILD>> dataSet,
                            DelegatesManager<PARENT, CHILD> delegatesManager) {
        this.dataSet = dataSet;
        this.delegatesManager = delegatesManager;
        this.delegatesManager.setDataSet(dataSet);
        setHasStableIds(true);
    }

    @Override
    public long getGroupId(int groupPosition) {
        /** no need to override this*/
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        /** no need to override this*/
        return groupPosition + childPosition;
    }

    @Override
    public int getGroupCount() {
        return dataSet.size();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return dataSet.get(groupPosition).getChildren().size();
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return dataSet.get(groupPosition).getParentViewType();
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return dataSet.get(groupPosition).getChildren().get(childPosition).getChildViewType();
    }

    @Override
    public AbstractExpandableItemViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.getDelegateParent(viewType).onCreateGroupViewHolder(parent, viewType);
    }

    @Override
    public RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.getDelegateChild(viewType).onCreateChildViewHolder(parent, viewType);
    }

    @Override
    public void onBindGroupViewHolder(AbstractExpandableItemViewHolder holder, int groupPosition, int viewType) {
        holder.itemView.setClickable(true);
        delegatesManager.getDelegateParent(viewType).onBindGroupViewHolder(holder, groupPosition, viewType);
    }

    @Override
    public void onBindChildViewHolder(RecyclerView.ViewHolder holder, int groupPosition, int childPosition, int viewType) {
        delegatesManager.getDelegateChild(viewType).onBindChildViewHolder(holder, groupPosition, childPosition, viewType);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(AbstractExpandableItemViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        // check is enabled
        return holder.itemView.isEnabled() && holder.itemView.isClickable();
    }
}
