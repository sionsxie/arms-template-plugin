package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun armsActivity(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsActivityKt(provider) else armsActivityJava(provider)

private fun armsActivityKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value}
import android.app.Activity
import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import cn.skytech.iglobalwin.app.base.SimpleBaseActivity
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter
import ${provider.appPackageName.value}.R
import kotlinx.android.synthetic.main.base_title.*

${commonAnnotation(provider)}
class ${provider.pageName.value}Activity : SimpleBaseActivity<${provider.pageName.value}Presenter>() , ${provider.pageName.value}Contract.View {
    override fun setupActivityComponent(appComponent: AppComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].toLowerCase()}${provider.pageName.value.substring(1, provider.pageName.value.length)}Module(${provider.pageName.value}Module(this))
                .build()
                .inject(this)
    }
    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.${provider.activityLayoutName.value} //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }
    
    override fun initData(savedInstanceState: Bundle?) {
        setToolBar(toolbar, "${provider.pageName.value}")
        
        initListener()
    }
    private fun initListener() {
    
    }
    
    override fun getActivity(): Activity = this
}
    
"""

private fun armsActivityJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value};
import android.app.Activity
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import com.jess.arms.di.component.AppComponent;
import cn.skytech.iglobalwin.app.base.SimpleBaseActivity;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;
import ${provider.appPackageName.value}.R;

${commonAnnotation(provider)}
public class ${provider.pageName.value}Activity extends SimpleBaseActivity<${provider.pageName.value}Presenter> implements ${provider.pageName.value}Contract.View {
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }
    
    @Override
    public int initView(@Nullable Bundle savedInstanceState){
        return R.layout.${provider.activityLayoutName.value}; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }
    
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //setToolBar(toolbar, "${provider.pageName.value}");
        
        initListener();
    }
    
    public void initListener() {
    
    }
    
    public Activity getActivity(){
        return this;
    }
}
    
"""