package cn.ltwc.cft.activity;

import android.text.TextUtils;
import android.widget.TextView;

import cn.ltwc.cft.R;

/**
 * Description:
 * Created on 2019/5/21 0021 14:04:15
 * author:Ahuangshang
 */
////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//                  佛祖保佑       永不宕机     永无BUG             //
////////////////////////////////////////////////////////////////////
public class ErrorShowActivity extends BaseActivity {
    private TextView text;
    String content;

    public ErrorShowActivity() {
        super(R.layout.activity_test);
    }

    @Override
    public void initView() {
        text = findViewById(R.id.text);

    }

    @Override
    public void initData() {
        content = getIntent().getStringExtra("info");

    }

    @Override
    public void bindView() {
        if (!TextUtils.isEmpty(content)) {
            text.setText(content);
        }

    }
}
