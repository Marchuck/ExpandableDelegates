package pl.lukaszmarczak.expandabledelegates;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.util.ArrayList;
import java.util.List;

import pl.lukaszmarczak.expandabledelegates.expandable_delegates.DelegableChildAdapter;
import pl.lukaszmarczak.expandabledelegates.expandable_delegates.DelegableParentAdapter;
import pl.lukaszmarczak.expandabledelegates.expandable_delegates.DelegatesAdapter;
import pl.lukaszmarczak.expandabledelegates.expandable_delegates.DelegatesManager;
import pl.lukaszmarczak.expandabledelegates.expandable_delegates.XChild;
import pl.lukaszmarczak.expandabledelegates.expandable_delegates.XParent;
import pl.lukaszmarczak.expandabledelegates.intterfaces.GroupClickListener;
import pl.lukaszmarczak.expandabledelegates.utils.ExpandableBuilder;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String ANONYMOUS = "Anonymous";
    public static final int PARENT_VIEWTYPE = 1;
    public static final int CHILD_VIEWTYPE = 3;
    public static final int CHILD_VIEWTYPE_2 = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView.Adapter delegatesAdapter = createAdapter();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler_view);

        ExpandableBuilder builder = new ExpandableBuilder(this);

        builder.withRecyclerView(recyclerView)
                .withAdapter(delegatesAdapter)
                .withGroupClickListener(new GroupClickListener() {
                    @Override
                    public void onGroupCollapse(int groupPosition, boolean fromUser) {
                        Log.d(TAG, "onGroupCollapse: ");
                    }

                    @Override
                    public void onGroupExpand(int groupPosition, boolean fromUser) {
                        Log.d(TAG, "onGroupExpand: ");
                    }
                })
                .build();
    }

    private RecyclerView.Adapter createAdapter() {
        DelegatesManager<Parent, Child> manager = new DelegatesManager<>();
        manager.addDelegateChild(new ChildAdapter(this));
        manager.addDelegateChild(new ExtendedChildAdapter());
        manager.addDelegateParent(new ParentAdapter());


        List<XParent<Parent, Child>> dataSet = new ArrayList<>();

        List<XChild<Child>> children = new ArrayList<>();
        children.add(new XChild<>(new Child("sleep", Color.GRAY), CHILD_VIEWTYPE));
        children.add(new XChild<>(new Child("eat", Color.YELLOW), CHILD_VIEWTYPE));
        children.add(new XChild<>(new Child("dance", Color.CYAN), CHILD_VIEWTYPE));
        children.add(new XChild<>(new Child("repeat", Color.RED), CHILD_VIEWTYPE));

        List<XChild<Child>> children2 = new ArrayList<>();
        children2.add(new XChild<>(new Child("sleep", Color.WHITE), CHILD_VIEWTYPE_2));
        children2.add(new XChild<>(new Child("eat", Color.LTGRAY), CHILD_VIEWTYPE_2));
        children2.add(new XChild<>(new Child("play", Color.GREEN), CHILD_VIEWTYPE_2));

        dataSet.add(new XParent<>(new Parent("Friday"), children2, PARENT_VIEWTYPE));
        dataSet.add(new XParent<>(new Parent("Saturday"), children, PARENT_VIEWTYPE));


        DelegatesAdapter<Parent, Child> delegatesAdapter = new DelegatesAdapter<>(dataSet, manager);
        return delegatesAdapter;
    }


    public static class Parent {
        public boolean clicked;

        public Parent(String parentName) {
            this.parentName = parentName;
        }

        public Parent() {
            this(ANONYMOUS);
        }

        public String parentName;
    }

    public static class Child {
        public String childName;
        public int backgroundColor;

        public Child(String childName, int backgroundColor) {
            this.childName = childName;
            this.backgroundColor = backgroundColor;
        }
    }

    public static class ChildAdapter extends DelegableChildAdapter<Parent, Child> {
        Context ctx;

        public ChildAdapter(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public int getChildViewType() {
            return CHILD_VIEWTYPE;
        }

        @Override
        public void onBindChildViewHolder(RecyclerView.ViewHolder holder, final int groupPosition,
                                          final int childPosition, int viewType) {
            XChild<Child> childWrapper = getDataSet().get(groupPosition).getChildren().get(childPosition);
            ChildViewHolder vh = (ChildViewHolder) holder;
            vh.textView.setText(childWrapper.getChild().childName);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ctx, "Clicked (" + groupPosition + ","
                            + childPosition + ")", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
            View v = DelegatesManager.inflateMe(parent, R.layout.child_item_1);
            return new ChildViewHolder(v);
        }
    }

    public static class ExtendedChildAdapter extends DelegableChildAdapter<Parent, Child> {

        @Override
        public int getChildViewType() {
            return CHILD_VIEWTYPE_2;
        }

        @Override
        public void onBindChildViewHolder(RecyclerView.ViewHolder holder, int groupPosition,
                                          int childPosition, int viewType) {
            XChild<Child> childWrapper = getDataSet().get(groupPosition).getChildren().get(childPosition);
            ExtendedChildViewHolder vh = (ExtendedChildViewHolder) holder;
            vh.rootView.setBackgroundColor(childWrapper.getChild().backgroundColor);
            vh.textView.setText(childWrapper.getChild().childName);
        }

        @Override
        public RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
            View v = DelegatesManager.inflateMe(parent, R.layout.child_item_2);
            return new ExtendedChildViewHolder(v);
        }
    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        View rootView;

        public ChildViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            textView = (TextView) rootView.findViewById(R.id.child_textView);
        }
    }

    public static class ExtendedChildViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        View rootView;

        public ExtendedChildViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            textView = (TextView) rootView.findViewById(R.id.child_textView_2);
        }
    }

    public static class ParentAdapter extends DelegableParentAdapter<Parent, Child> {

        @Override
        public int getParentViewType() {
            return PARENT_VIEWTYPE;
        }

        @Override
        public void onBindGroupViewHolder(AbstractExpandableItemViewHolder holder, final int groupPosition, int viewType) {
            final AVH avh = (AVH) holder;
            final Parent parentData = getDataSet().get(groupPosition).getParent();
            avh.parentName.setText(parentData.parentName);
            avh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: ");
                    parentData.clicked = !parentData.clicked;
                    avh.imageView.setImageResource(parentData.clicked ?
                            android.R.drawable.arrow_up_float : android.R.drawable.arrow_down_float);
                }
            });
        }

        @Override
        public AbstractExpandableItemViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
            View v = DelegatesManager.inflateMe(parent, R.layout.parent_item_1);
            return new AVH(v);
        }

        public static class AVH extends AbstractExpandableItemViewHolder {
            TextView parentName;
            ImageView imageView;

            public AVH(View itemView) {
                super(itemView);
                parentName = (TextView) itemView.findViewById(R.id.parent_textview);
                imageView = (ImageView) itemView.findViewById(R.id.parent_imageview);
            }
        }
    }
}
