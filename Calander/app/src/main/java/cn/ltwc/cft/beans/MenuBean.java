package cn.ltwc.cft.beans;

/**
 * TODO:右侧菜单的JavaBean
 *
 * @author huangshang 2015-11-15 上午10:09:39
 * @Modified_By:
 */
public class MenuBean {
    private int selectIcon;// 菜单选中的图标
    private int unSelectIcon;
    private String menuName;// 菜单名
    private boolean selected;//是否选中

    public MenuBean() {
    }

    public MenuBean(int selectIcon, String menuName) {
        this.selectIcon = selectIcon;
        this.menuName = menuName;
    }

    public MenuBean(int selectIcon, int unSelectIcon, String menuName, boolean selected) {
        this.selectIcon = selectIcon;
        this.unSelectIcon = unSelectIcon;
        this.menuName = menuName;
        this.selected = selected;
    }

    public int getSelectIcon() {
        return selectIcon;
    }

    public void setSelectIcon(int selectIcon) {
        this.selectIcon = selectIcon;
    }

    public int getUnSelectIcon() {
        return unSelectIcon;
    }

    public void setUnSelectIcon(int unSelectIcon) {
        this.unSelectIcon = unSelectIcon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "MenuBean{" +
                "selectIcon=" + selectIcon +
                ", unSelectIcon=" + unSelectIcon +
                ", menuName='" + menuName + '\'' +
                ", selected=" + selected +
                '}';
    }
}
