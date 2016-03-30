package pl.lukaszmarczak.expandabledelegates.expandable_delegates;

import android.support.annotation.LayoutRes;
import android.support.v4.util.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public class DelegatesManager<PARENT, CHILD> {
    SparseArrayCompat<DelegableChildAdapter<PARENT, CHILD>> childrenDelegates = new SparseArrayCompat<>();
    SparseArrayCompat<DelegableParentAdapter<PARENT, CHILD>> parentDelegates = new SparseArrayCompat<>();

    private List<XParent<PARENT, CHILD>> dataSet = new ArrayList<>();

    public DelegatesManager() {

    }

    public List<XParent<PARENT, CHILD>> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<XParent<PARENT, CHILD>> dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * add delagate parent
     *
     * @param delegableParentAdapter
     */
    public void addDelegateParent(DelegableParentAdapter<PARENT, CHILD> delegableParentAdapter) {
        delegableParentAdapter.setManager(this);
        parentDelegates.put(delegableParentAdapter.getParentViewType(), delegableParentAdapter);
    }

    /**
     * add delegate child
     *
     * @param delegableChildAdapter
     */
    public void addDelegateChild(DelegableChildAdapter<PARENT, CHILD> delegableChildAdapter) {
        delegableChildAdapter.setManager(this);
        childrenDelegates.put(delegableChildAdapter.getChildViewType(), delegableChildAdapter);
    }

    public DelegableParentAdapter<PARENT, CHILD> getDelegateParent(int viewType) {
        DelegableParentAdapter<PARENT, CHILD> parent = parentDelegates.get(viewType);
        if (parent == null) {
            throw new NullPointerException("NULL DELEGABLE PARENT FOR VIEWTYPE " + viewType);
        }
        return parent;
    }

    public DelegableChildAdapter<PARENT,CHILD> getDelegateChild(int viewType) {
        DelegableChildAdapter<PARENT,CHILD> child = childrenDelegates.get(viewType);
        if (child == null) {
            throw new NullPointerException("NULL DELEGABLE CHILD FOR VIEWTYPE " + viewType);
        }
        return child;
    }

    /**
     * Helper method which simply inflates View from layout resource (without using viewType)
     *
     * @param parent
     * @param resource
     * @return
     */
    public static View inflateMe(ViewGroup parent, @LayoutRes int resource) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, null, false);
    }
}
