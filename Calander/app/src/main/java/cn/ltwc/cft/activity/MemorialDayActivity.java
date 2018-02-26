package cn.ltwc.cft.activity;

import android.view.View;
import android.widget.ListView;
import cn.ltwc.cft.R;
import cn.ltwc.cft.view.TitleView;

/**
 * 
 * TODO:纪念日的Activity
 * 
 * @author huangshang 2015-11-15 下午1:26:42
 * @Modified_By:
 */
public class MemorialDayActivity extends BaseActivity {
	private TitleView title;// 标题栏
	private ListView list;// 列表视图
	private View emptyView;// 列表为空时的视图

	public MemorialDayActivity() {
		super(R.layout.activity_memorialday);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		title = (TitleView) findViewById(R.id.memoria_title);
		title.setTitletext("全部纪念日");
		list = (ListView) findViewById(R.id.memorial_list);
		// 设置list为空时候的视图
		emptyView = findViewById(R.id.memorial_emptyview);
		list.setEmptyView(emptyView);

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bindView() {
		// TODO Auto-generated method stub

	}

}
