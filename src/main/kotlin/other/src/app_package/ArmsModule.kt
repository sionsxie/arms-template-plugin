package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.armsAnnotation

fun armsModule(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsModuleKt(provider) else armsModuleJava(provider)

private fun armsModuleKt(provider: ArmsPluginTemplateProviderImpl): String {
    val scope = if (provider.needActivity.value && provider.needFragment.value)
        "@ActivityScope"
    else if (provider.needActivity.value)
        "@ActivityScope"
    else if (provider.needFragment.value)
        "@FragmentScope"
    else ""
    return """
        
package ${provider.moudlePackageName.value}
${
        if (provider.needActivity.value && provider.needFragment.value)
            "import com.jess.arms.di.scope.ActivityScope"
        else if (provider.needActivity.value)
            "import com.jess.arms.di.scope.ActivityScope"
        else if (provider.needFragment.value)
            "import com.jess.arms.di.scope.FragmentScope"
        else ""
    }
import dagger.Module
import dagger.Provides
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.modelPackageName.value}.${provider.pageName.value}Model

$armsAnnotation
@Module
 //构建${provider.pageName.value}Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ${provider.pageName.value}Module(private val view:${provider.pageName.value}Contract.View) {
    $scope
    @Provides
    fun provide${provider.pageName.value}View():${provider.pageName.value}Contract.View{
        return this.view
    }
    $scope
    @Provides
    fun provide${provider.pageName.value}Model(model:${provider.pageName.value}Model):${provider.pageName.value}Contract.Model{
        return model
    }
}   
"""
}


fun armsModuleJava(provider: ArmsPluginTemplateProviderImpl): String {
    return """
package ${provider.moudlePackageName.value};
${
        if (provider.needActivity.value && provider.needFragment.value)
            "import com.jess.arms.di.scope.ActivityScope;"
        else if (provider.needActivity.value)
            "import com.jess.arms.di.scope.ActivityScope;"
        else if (provider.needFragment.value)
            "import com.jess.arms.di.scope.FragmentScope;"
        else ""
    }
import dagger.Binds;
import dagger.Module;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.modelPackageName.value}.${provider.pageName.value}Model;

$armsAnnotation
@Module
 //构建${provider.pageName.value}Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
public abstract class ${provider.pageName.value}Module {
    
    @Binds
    abstract ${provider.pageName.value}Contract.Model bind${provider.pageName.value}Model(${provider.pageName.value}Model model);
}   
"""
}