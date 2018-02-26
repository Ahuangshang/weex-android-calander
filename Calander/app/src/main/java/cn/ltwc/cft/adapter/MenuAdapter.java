package cn.ltwc.cft.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.beans.MenuBean;

/**
 * TODO:右侧菜单的适配器
 *
 * @author huangshang 2015-11-15 上午10:03:11
 * @Modified_By:
 */

@SuppressLint("InflateParams")
public class MenuAdapter extends BaseAdapter {
    private Context c;
    private List<MenuBean> menuList;// 右侧菜单的集合

    public MenuAdapter(Context c, List<MenuBean> rightMenuList) {
        super();

        this.c = c;
        this.menuList = rightMenuList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return menuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHodle hodle = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.menu_grid,
                    null);
            hodle = new ViewHodle();
            hodle.menuIcon = (ImageView) convertView.findViewById(R.id.rmimg);
            hodle.menuName = (TextView) convertView.findViewById(R.id.rmtxt);
            convertView.setTag(hodle);

        } else {
            hodle = (ViewHodle) convertView.getTag();
        }
        hodle.menuIcon.setImageResource(menuList.get(position).isSelected() ? menuList.get(position).getSelectIcon() : menuList.get(position).getUnSelectIcon());
        hodle.menuName.setText(menuList.get(position).getMenuName());
        hodle.menuName.setTextColor(menuList.get(position).isSelected() ? c.getResources().getColor(R.color.black) : c.getResources().getColor(R.color.b2b2b2));
        return convertView;
    }

    class ViewHodle {
        private ImageView menuIcon;// 菜单图标
        private TextView menuName;// 菜单名字

    }
}
