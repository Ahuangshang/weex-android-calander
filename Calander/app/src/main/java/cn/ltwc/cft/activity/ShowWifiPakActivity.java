package cn.ltwc.cft.activity;

import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.adapter.WifiAdapter;
import cn.ltwc.cft.beans.WifiInfo;
import cn.ltwc.cft.utils.WifiManage;
import cn.ltwc.cft.view.TitleView;

public class ShowWifiPakActivity extends BaseActivity {
    private TitleView title;
    private ListView wifiList;
    private WifiManage wifiManage;
    private List<WifiInfo> wifiInfos;
    private boolean hasRoot = true;
    private TextView empty;

    public ShowWifiPakActivity() {
        super(R.layout.activity_show_wifi_psk);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        title = (TitleView) findViewById(R.id.wifi_psk_title);
        wifiList = (ListView) findViewById(R.id.wifi_list);
        title.setTitletext("WIFI密码查看器");
        empty = (TextView) findViewById(R.id.empty);
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        wifiManage = new WifiManage();
        wifiInfos = new ArrayList<WifiInfo>();
        // File file = new File("/data/misc/wifi/wpa_supplicant.conf");
        // final File f = new File("/data/misc/wifi/wpa_supplicant.conf");
        // new Thread() {
        // @Override
        // public void run() {
        // super.run();
        // ArrayList<String> list = new ArrayList<String>();
        // // String cpath = getCommandLineString(f.getPath());
        // String s = "cat " + f.getPath();
        // list = runAndWait1(s, true);
        // for (int i = 0; i < list.size(); i++) {
        // LogUtil.e( list.get(i));
        // }
        //
        // }
        // }.start();
        try {
            Init();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            hasRoot = false;

        }
    }

    @Override
    public void bindView() {
        // TODO Auto-generated method stub
        WifiAdapter ad = new WifiAdapter(wifiInfos, ShowWifiPakActivity.this);
        wifiList.setAdapter(ad);
        if (hasRoot) {
            empty.setText("还没有连接过wifi");
        } else {
            empty.setText("需要root权限才可以查看，请获取root权限。");
        }
        wifiList.setEmptyView(empty);
    }

    public void Init() throws Exception {
        wifiInfos.addAll(wifiManage.Read());
    }

    // private static boolean waitForCommand(Command cmd) {
    // while (!cmd.isFinished()) {
    // synchronized (cmd) {
    // try {
    // if (!cmd.isFinished()) {
    // cmd.wait(2000);
    // }
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    //
    // if (!cmd.isExecuting() && !cmd.isFinished()) {
    // // Logger.errorST("Error: Command is not executing and is not
    // // finished!");
    // return false;
    // }
    // }
    //
    // // Logger.debug("Command Finished!");
    // return true;
    // }
    //
    // public static ArrayList<String> runAndWait1(String cmd, final boolean
    // root) {
    // final ArrayList<String> output = new ArrayList<String>();
    // Command cc = new Command(1, cmd) {
    // @Override
    // public void commandOutput(int i, String s) {
    // output.add(s);
    // // System.out.println("output "+root+s);
    // }
    //
    // @Override
    // public void commandTerminated(int i, String s) {
    //
    // System.out.println("error" + root + s);
    //
    // }
    //
    // @Override
    // public void commandCompleted(int i, int i2) {
    //
    // }
    // };
    // try {
    // RootTools.getShell(root).add(cc);
    // } catch (Exception e) {
    // // Logger.errorST("Exception when trying to run shell command", e);
    // e.printStackTrace();
    // return output;
    // }
    //
    // if (!waitForCommand(cc)) {
    // return output;
    // }
    //
    // return output;
    // }

}
