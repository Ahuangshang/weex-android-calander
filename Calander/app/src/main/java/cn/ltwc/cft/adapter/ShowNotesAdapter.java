package cn.ltwc.cft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.beans.NoteBean;

public class ShowNotesAdapter extends BaseAdapter {
    private Context c;
    private List<NoteBean> notes;

    public ShowNotesAdapter(Context c, List<NoteBean> notes) {
        super();
        this.c = c;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View vv, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHodler hodler;
        if (vv == null) {
            vv = LayoutInflater.from(c).inflate(R.layout.item_show_notes, null);
            hodler = new ViewHodler();
            hodler.title = (TextView) vv.findViewById(R.id.show_title);
            hodler.content = (TextView) vv.findViewById(R.id.show_content);
            hodler.time = (TextView) vv.findViewById(R.id.show_time);
            vv.setTag(hodler);
        } else {
            hodler = (ViewHodler) vv.getTag();
        }
        NoteBean bean = (NoteBean) getItem(position);
        String time = bean.getCompleteTime();
        String title = time.substring(0, time.lastIndexOf("-"));
        hodler.title.setText(title);
        hodler.content.setText("     " + bean.getNoteContent());
        hodler.time.setText(time);
        String sh = "";
        if (position == 0) {
            hodler.title.setVisibility(View.VISIBLE);
        } else {
            sh = ((NoteBean) getItem(position - 1)).getCompleteTime()
                    .substring(
                            0,
                            ((NoteBean) getItem(position - 1))
                                    .getCompleteTime().lastIndexOf("-"));
            if (sh.equals(title)) {
                hodler.title.setVisibility(View.GONE);
            } else {
                hodler.title.setVisibility(View.VISIBLE);
            }
        }

        return vv;
    }

    class ViewHodler {
        TextView title, content, time;
    }

}
