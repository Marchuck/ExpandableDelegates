package pl.lukaszmarczak.expandabledelegates.expandable_delegates;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Łukasz Marczak
 *
 * @since 28.03.16
 */
public class XParent<PARENT, CHILD>  {

    private PARENT parent;
    private List<XChild<CHILD>> children = new ArrayList<>();
    private int parentViewType;

    public XParent(PARENT parent, List<XChild<CHILD>> children, int parentViewType) {
        this.parent = parent;
        this.children = children;
        this.parentViewType = parentViewType;
    }

    public PARENT getParent() {
        return parent;
    }

    public List<XChild<CHILD>> getChildren() {
        return children;
    }

    public int getParentViewType() {
        return parentViewType;
    }
}
