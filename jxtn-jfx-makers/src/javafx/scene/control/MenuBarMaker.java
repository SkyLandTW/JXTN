// @formatter:off
/*
 * Unlicensed, generated by javafx.ftl
 */

package javafx.scene.control;

/**
 * {@link MenuBar}建構器。
 *
 * @author JarReflectionDataLoader-1.0.0
 * @version jfxrt.jar
 * @param <Z> 要建構的物件型態(需繼承{@link MenuBar})
 * @param <B> 建構器本身的型態(需繼承{@link MenuBarMaker})
 */
@javax.annotation.Generated("Generated by javafx.ftl")
@SuppressWarnings("all")
public class MenuBarMaker<Z extends MenuBar, B extends MenuBarMaker<Z, B>>
        extends javafx.scene.control.ControlMaker<Z, B>
        implements MenuBarMakerExt<Z, B>
{

    private boolean hasMenus;
    private java.util.Collection<javafx.scene.control.Menu> valMenus;

    private boolean hasUseSystemMenuBar;
    private boolean valUseSystemMenuBar;

    private boolean bound1UseSystemMenuBar;
    private boolean bound2UseSystemMenuBar;
    private javafx.beans.value.ObservableValue<? extends Boolean> obsrv1UseSystemMenuBar;
    private javafx.beans.property.Property<Boolean> obsrv2UseSystemMenuBar;

    @Override
    public void applyTo(Z instance)
    {
        super.applyTo(instance);
        if (this.hasMenus)
            instance.getMenus().addAll(this.valMenus);
        if (this.hasUseSystemMenuBar)
            instance.setUseSystemMenuBar(this.valUseSystemMenuBar);
        if (this.bound1UseSystemMenuBar)
            instance.useSystemMenuBarProperty().bind(this.obsrv1UseSystemMenuBar);
        if (this.bound2UseSystemMenuBar)
            instance.useSystemMenuBarProperty().bindBidirectional(this.obsrv2UseSystemMenuBar);
    }

    /**
     * 設定集合屬性{@link MenuBar#getMenus}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     * @deprecated 屬性值並非{@link javafx.collections.ObservableList}
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public final B menus(java.util.Collection<? extends javafx.scene.control.Menu> value)
    {
        this.hasMenus = true;
        this.valMenus = (java.util.Collection<javafx.scene.control.Menu>) value;
        return (B) this;
    }

    /**
     * 設定集合屬性{@link MenuBar#getMenus}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B menus(javafx.scene.control.Menu... value)
    {
        this.hasMenus = true;
        this.valMenus = java.util.Arrays.asList(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link MenuBar#getMenus}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B menusAdd(java.util.Collection<? extends javafx.scene.control.Menu> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasMenus = true;
        if (this.valMenus == null)
            this.valMenus = new java.util.ArrayList<>(value.size());
        this.valMenus.addAll(value);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link MenuBar#getMenus}的內容。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B menusAdd(javafx.scene.control.Menu... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasMenus = true;
        if (this.valMenus == null)
            this.valMenus = new java.util.ArrayList<>(value.length);
        this.valMenus.addAll(java.util.Arrays.asList(value));
        return (B) this;
    }

    /**
     * 增加集合屬性{@link MenuBar#getMenus}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B menusAddNonNull(java.util.Collection<? extends javafx.scene.control.Menu> value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasMenus = true;
        if (this.valMenus == null)
            this.valMenus = new java.util.ArrayList<>(value.size());
        for (javafx.scene.control.Menu i : value)
            if (i != null)
                this.valMenus.add(i);
        return (B) this;
    }

    /**
     * 增加集合屬性{@link MenuBar#getMenus}的內容，排除null項目。
     *
     * @param value 新的集合內容
     * @return 目前的建構器(this)
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final B menusAddNonNull(javafx.scene.control.Menu... value)
    {
        java.util.Objects.requireNonNull(value);
        this.hasMenus = true;
        if (this.valMenus == null)
            this.valMenus = new java.util.ArrayList<>(value.length);
        for (javafx.scene.control.Menu i : value)
            if (i != null)
                this.valMenus.add(i);
        return (B) this;
    }

    /**
     * 設定屬性{@link MenuBar#setUseSystemMenuBar(boolean)}。
     *
     * @param value 新的屬性值
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public B useSystemMenuBar(boolean value)
    {
        this.hasUseSystemMenuBar = true;
        this.valUseSystemMenuBar = value;
        return (B) this;
    }

    /**
     * 設定屬性{@link MenuBar#useSystemMenuBarProperty}的連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindUseSystemMenuBar(javafx.beans.value.ObservableValue<? extends Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1UseSystemMenuBar = true;
        this.obsrv1UseSystemMenuBar = source;
        this.bound2UseSystemMenuBar = false;
        this.obsrv2UseSystemMenuBar = null;
        return (B) this;
    }

    /**
     * 設定屬性{@link MenuBar#useSystemMenuBarProperty}的雙向連結。
     *
     * @param value 新的屬性連結(單向)
     * @return 目前的建構器(this)
     */
    @SuppressWarnings("unchecked")
    public final B bindBidirectionalUseSystemMenuBar(javafx.beans.property.Property<Boolean> source)
    {
        java.util.Objects.requireNonNull(source);
        this.bound1UseSystemMenuBar = false;
        this.obsrv1UseSystemMenuBar = null;
        this.bound2UseSystemMenuBar = true;
        this.obsrv2UseSystemMenuBar = source;
        return (B) this;
    }

    /**
     * 建構{@link MenuBar}物件。
     *
     * @return 新的{@link MenuBar}物件實體
     */
    @Override
    @SuppressWarnings("unchecked")
    public MenuBar build()
    {
        MenuBar instance = new MenuBar();
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }

    /**
     * 建構{@link MenuBar}物件。
     *
     * @return 新的{@link MenuBar}物件實體
     */
    @SuppressWarnings("unchecked")
    public MenuBar build(javafx.scene.control.Menu[] arg0)
    {
        MenuBar instance = new MenuBar(arg0);
        this.applyTo((Z) instance);
        this.doAfterBuild((Z) instance);
        return instance;
    }
}
