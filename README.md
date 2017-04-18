# mLibrary
封装的一个基类

项目中引用的依赖.

    compile rootProject.ext.dependencies["appcompat-v7"]
    compile rootProject.ext.dependencies["support-v4"]
    compile rootProject.ext.dependencies["design"]
    compile rootProject.ext.dependencies["recyclerview-v7"]
    compile rootProject.ext.dependencies["butterknife"]
    apt rootProject.ext.dependencies["butterknife-apt"]
    compile rootProject.ext.dependencies["eventbus"]
    compile rootProject.ext.dependencies["autolayout"]

## 用法

* Android Studio
	
	```
	compile 'com.github.wangrui1662729966:mLibrary:1.0.0-beta'
	```
	
	

## 目前对以下需求进行了封装
* 默认封装的DefaultApplicationKit
* 默认封装的DefaultAppCompatActivity
* 默认封装的DefaultFragmentActivity
* 默认封装的DefaultFragment
* 默认封装的DefaultHandleFragment
* 支持自定义UiHandler
* 支持UiCallback
* 支持ACache的缓存
* 支持EventBus事件总线
* 支持Router跳转路由

## 基本扩展用法示例

### ApplicationKit

	public class ApplicationKit extends DefaultApplicationKit implements IApplicationKit {
	
	    private ApplicationKit() {
	    }
	
	    private static class SingletonInstance {
	        private static final ApplicationKit INSTANCE = new ApplicationKit();
	    }
	
	    public static ApplicationKit getInstance() {
	        return SingletonInstance.INSTANCE;
	    }
	}

### BaseActivity

	public abstract class BaseActivity extends DefaultAppCompatActivity implements IView {
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        initContentView(savedInstanceState);
	        initWidget();
	    }
	
	    @Override
	    protected IApplicationKit getDefaultApplicationKit() {
	        return ApplicationKit.getInstance();
	    }
	
	    //界面布局的初始化操作
	    protected void initContentView(Bundle savedInstanceState) {
	
	    }
	
	    // 可能全屏或者没有ActionBar等
	    @Override
	    protected void initCustomBaseData() {
	
	    }
	
	    //如果需要接受消息(useUiHandler()=true)，则重写该方法接收消息
	    protected void handler(Message msg) {
	    }
	
	    @Override
	    public void onStart() {
	        super.onStart();
	        initData();
	    }
	
	}

### BaseFragment

	public abstract class BaseFragment extends DefaultFragment{
	
	    @Override
	    protected IApplicationKit getIApplicationKit() {
	        return ApplicationKit.getInstance();
	    }
	
	    @Override
	    protected void handler(Message msg) {
	
	    }
	
	}


### BaseFragmentActivity

	public abstract class BaseFragmentActivity extends DefaultFragmentActivity implements IView{
	
	    @NonNull
	    @Override
	    protected IApplicationKit getDefaultApplicationKit() {
	        return ApplicationKit.getInstance();
	    }
	
	    // 可能全屏或者没有ActionBar等
	    @Override
	    protected void initCustomBaseData() {
	
	    }
	
	    //如果需要接受消息(useUiHandler()=true)，则重写该方法接收消息
	    @Override
	    protected void handler(Message msg) {
	
	    }
	
	}


### BaseHandleFragment

	public abstract class BaseHandleFragment extends DefaultHandleFragment {
	
	    @Override
	    protected IApplicationKit getIApplicationKit() {
	        return ApplicationKit.getInstance();
	    }
	
	    @Override
	    protected void handler(Message msg) {
	
	    }
	
	
	}

### 自定义CallBack

	public interface IView extends IDefaultView {
	
	    void initData();
	
	}

### ACache 扩展 基本数据类型 保存

    /**
     * 保存 基本数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的基本数据
     */
    public void putEverything(String key, Object value) {
        File file = mCache.newFile(key);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(file), 1024);
            out.write(value.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mCache.put(file);
        }
    }


## 全局统一的gradle配置 ： config.gradle

	ext {
	
	    android = [
	            compileSdkVersion: 24,
	            buildToolsVersion: "24.0.3",
	
	            minSdkVersion    : 11,
	            targetSdkVersion : 24,
	
	            versionCode      : 100,
	            versionName      : '1.0.0',
	
	            VSupportSdk      : '24.2.1',
	    ]
	
	    dependencies = [
	            "appcompat-v7"             : "com.android.support:appcompat-v7:${android["VSupportSdk"]}",
	            "support-v4"               : "com.android.support:support-v4:${android["VSupportSdk"]}",
	            "design"                   : "com.android.support:design:${android["VSupportSdk"]}",
	            "recyclerview-v7"          : "com.android.support:recyclerview-v7:${android["VSupportSdk"]}",
	            "butterknife"              : "com.jakewharton:butterknife:8.4.0",
	            "butterknife-apt"          : "com.jakewharton:butterknife-compiler:8.4.0",
	            "mLibrary"                 : "com.github.wangrui1662729966:mLibrary:v1.0.0",
	            "autolayout"               : "com.zhy:autolayout:1.4.5",
	            "eventbus"                 : "org.greenrobot:eventbus:3.0.0",       
	            "multidex"                 : "com.android.support:multidex:1.0.1",
	    ]
	}

