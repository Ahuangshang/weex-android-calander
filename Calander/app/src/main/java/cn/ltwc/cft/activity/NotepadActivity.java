package cn.ltwc.cft.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.adapter.ShowNotesAdapter;
import cn.ltwc.cft.beans.NoteBean;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.db.NoteDB;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.viewutils.dialogutils.BtnClickListener;
import cn.ltwc.viewutils.dialogutils.DialogUtil;

/**
 * TODO:记事本的Activity
 *
 * @author huangshang 2015-11-15 下午1:27:04
 * @Modified_By:
 */
public class NotepadActivity extends BaseActivity {
    private TitleView title;// 标题栏
    private ListView list;// 列表视图
    private View emptyView;// 列表为空时的视图
    private List<NoteBean> notes;
    private ShowNotesAdapter adapter;

    public NotepadActivity() {
        super(R.layout.activity_notepad);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        reflushData();
    }

    private void reflushData() {
        List<NoteBean> temp = NoteDB.getInstance().getAll();
        notes.clear();
        notes.addAll(temp);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        title = (TitleView) findViewById(R.id.note_title);
        title.setTitletext("全部记事");
        title.setRightVisibility(View.VISIBLE);
        list = (ListView) findViewById(R.id.note_list);
        // 设置list为空时候的视图
        emptyView = findViewById(R.id.note_emptyview);
        list.setEmptyView(emptyView);
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        notes = new ArrayList<NoteBean>();

    }

    @Override
    public void bindView() {
        // TODO Auto-generated method stub
        // 右侧添加按钮的点击事件
        addNote();
        adapter = new ShowNotesAdapter(c, notes);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(c, AddNoteActivity.class);
                intent.putExtra(Constant.NOTE_BEAN, notes.get(position));
                intent.putExtra(Constant.FLAG, true);
                startActivity(intent);
            }
        });
        list.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                // TODO Auto-generated method stub
                dialog(position);
                return true;
            }
        });
    }

    /**
     * 右侧添加按钮的点击事件
     */
    private void addNote() {
        title.getRightIcon().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, AddNoteActivity.class);
                intent.putExtra(Constant.NOTE_BEAN, new NoteBean());
                intent.putExtra(Constant.FLAG, false);
                startActivity(intent);
            }
        });
    }

    protected void dialog(final int position) {
        DialogUtil dialogUtil = new DialogUtil(new WeakReference<Activity>(this));
        dialogUtil.setTop("提示");
        dialogUtil.setContent("删除此记事？");
        dialogUtil.setButtonShowType(DialogUtil.BUTTON_TYPE_TWO_BTN);
        dialogUtil.setContentShowType(DialogUtil.CONTENT_TYPE_TEXT);
        dialogUtil.setRightBtnClickListener(new BtnClickListener() {
            @Override
            public void btnClick() {
                NoteDB.getInstance().deleteItem(
                        notes.get(position).getCurrentTime());
                reflushData();
            }
        });
        dialogUtil.show();
    }

}
