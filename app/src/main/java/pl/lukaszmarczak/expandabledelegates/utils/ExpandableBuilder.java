package pl.lukaszmarczak.expandabledelegates.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import pl.lukaszmarczak.expandabledelegates.intterfaces.GroupClickListener;


/**
 * Created by Łukasz Marczak
 *
 * @since 15.02.16
 */
public class ExpandableBuilder {
    public static final String TAG = ExpandableBuilder.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private Context context;
    private RecyclerView.Adapter myItemAdapter;
    private RecyclerViewExpandableItemManager mRecyclerViewExpandableItemManager;
    private
    @Nullable
    Bundle savedInstanceState;
    private
    @Nullable
    GroupClickListener groupClickListener;

    public ExpandableBuilder(@NonNull Context ctx) {
        this.context = ctx;
    }

    public ExpandableBuilder withRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        return this;
    }

    public ExpandableBuilder withAdapter(RecyclerView.Adapter myItemAdapter) {
        this.myItemAdapter = myItemAdapter;
        return this;
    }

    public ExpandableBuilder withSavedInstanceState(@Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        return this;
    }

    public ExpandableBuilder withGroupClickListener(@Nullable GroupClickListener groupClickListener) {
        this.groupClickListener = groupClickListener;
        return this;
    }

    public void collapseAll() {
        if (mRecyclerViewExpandableItemManager == null) {
            Log.e(TAG, "collapseAll failed. Have you called .build() ?");
            return;
        }
        mRecyclerViewExpandableItemManager.collapseAll();
    }

    public void expand(int groupPosition) {
        if (mRecyclerViewExpandableItemManager == null) {
            Log.e(TAG, "collapseAll failed. Have you called .build() ?");
            return;
        }
        mRecyclerViewExpandableItemManager.expandGroup(groupPosition);
    }

    public void collapse(int groupPosition) {
        if (mRecyclerViewExpandableItemManager == null) {
            Log.e(TAG, "collapseAll failed. Have you called .buid() ?");
            return;
        }
        mRecyclerViewExpandableItemManager.collapseGroup(groupPosition);
    }

    public void scrollToGroup(int groupPosition, int childrenItemHeight) {
        if (mRecyclerViewExpandableItemManager == null) {
            Log.e(TAG, "scrollToGroup failed. Have you called .buid() ?");
            return;
        }
        mRecyclerViewExpandableItemManager.scrollToGroup(groupPosition, childrenItemHeight);
    }

    public void scrollToGroup(int groupPosition, int childrenItemHeight, int topMargin, int bottomMargin) {
        if (mRecyclerViewExpandableItemManager == null) {
            Log.e(TAG, "scrollToGroup failed. Have you called .buid() ?");
            return;
        }
        mRecyclerViewExpandableItemManager.scrollToGroup(groupPosition, childrenItemHeight, topMargin, bottomMargin);
    }

    public void scrollToGroupWithTotalChildrenHeight(int groupPosition, int totalChildrenHeight, int topMargin, int bottomMargin) {
        if (mRecyclerViewExpandableItemManager == null) {
            Log.e(TAG, "scrollToGroupWithTotalChildrenHeight failed. Have you called .buid() ?");
            return;
        }
        mRecyclerViewExpandableItemManager.scrollToGroupWithTotalChildrenHeight(groupPosition, totalChildrenHeight, topMargin, bottomMargin);
    }

    /**
     * Builds
     */
    public void build() {
        if (context == null || mRecyclerView == null || myItemAdapter == null)
            throw new NullPointerException("Nullable value detected");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);

        final Parcelable savedState = (savedInstanceState != null) ? savedInstanceState.getParcelable("RecyclerViewExpandableItemManager") : null;
        mRecyclerViewExpandableItemManager = new RecyclerViewExpandableItemManager(savedState);
        if (groupClickListener != null) {
            mRecyclerViewExpandableItemManager.setOnGroupExpandListener(new RecyclerViewExpandableItemManager.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition, boolean b) {
                    Log.d(TAG, "onGroupExpand " + groupPosition + ", " + b);
                    groupClickListener.onGroupExpand(groupPosition, b);
                }
            });
            mRecyclerViewExpandableItemManager.setOnGroupCollapseListener(new RecyclerViewExpandableItemManager
                    .OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int groupPosition, boolean b) {
                    Log.d(TAG, "onGroupCollapse " + groupPosition + ", " + b);
                    groupClickListener.onGroupCollapse(groupPosition, b);
                }
            });
        }

        RecyclerView.Adapter mWrappedAdapter = mRecyclerViewExpandableItemManager.createWrappedAdapter(myItemAdapter); // wrap for expanding

        final GeneralItemAnimator animator = new RefactoredDefaultItemAnimator();
        // Change animations are enabled by default since support-v7-recyclerview v22.
        // Need to disable them when using animation indicator.
        animator.setSupportsChangeAnimations(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWrappedAdapter);  // requires *wrapped* adapter
        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerViewExpandableItemManager.attachRecyclerView(mRecyclerView);
    }

    public void onDestroy() {
        mRecyclerViewExpandableItemManager.release();
        mRecyclerViewExpandableItemManager = null;
        myItemAdapter =null;
        mRecyclerView = null;
        groupClickListener = null;
    }

}