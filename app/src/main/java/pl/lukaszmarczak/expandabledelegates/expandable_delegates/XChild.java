package pl.lukaszmarczak.expandabledelegates.expandable_delegates;

/**
 * Created by Łukasz Marczak
 *
 * @since 28.03.16
 */
public class XChild<CHILD>  {

    private CHILD child;
    private int childViewType;

    public XChild(CHILD child, int childViewType) {
        this.child = child;
        this.childViewType = childViewType;
    }

    public CHILD getChild() {
        return child;
    }

    public int getChildViewType() {
        return childViewType;
    }
}
