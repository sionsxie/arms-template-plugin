package other

import com.android.tools.idea.wizard.template.Language
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.res.layout.simpleLayout
import other.src.app_package.*
import other.src.armsManifest
import java.io.File

fun RecipeExecutor.armsRecipe(provider: ArmsPluginTemplateProviderImpl, data: ModuleTemplateData) {
    if (provider.needActivity.value) {
        mergeXml(armsManifest(provider, data), File(data.manifestDir, "AndroidManifest.xml"))
    }

    if (provider.needActivity.value && provider.generateActivityLayout.value) {
        save(simpleLayout(provider), File(data.resDir, "layout/${provider.activityLayoutName.value}.xml"))
    }

    if (provider.needFragment.value && provider.generateFragmentLayout.value) {
        save(simpleLayout(provider), File(data.resDir, "layout/${provider.fragmentLayoutName.value}.xml"))
    }

    val languageSuffix = if (data.projectTemplateData.language == Language.Java) "java" else "kt"
    val isKt = data.projectTemplateData.language == Language.Kotlin
    if (provider.needActivity.value) {
        val activityFile = File(data.rootDir, "${fFmSlashedPackageName(provider.activityPackageName.value)}/${provider.pageName.value}Activity.$languageSuffix")
        save(armsActivity(isKt, provider), activityFile)
        open(activityFile)
    }
    if (provider.needFragment.value) {
        val fragmentFile = File(data.rootDir, "${fFmSlashedPackageName(provider.fragmentPackageName.value)}/${provider.pageName.value}Fragment.$languageSuffix")
        save(armsFragment(isKt, provider), fragmentFile)
        open(fragmentFile)
    }


    if (provider.needContract.value) {
        val contractFile = File(data.rootDir, "${fFmSlashedPackageName(provider.contractPackageName.value)}/${provider.pageName.value}Contract.$languageSuffix")
        save(armsContract(isKt, provider), contractFile)
    }
    if (provider.needPresenter.value) {
        val presenterFile = File(data.rootDir, "${fFmSlashedPackageName(provider.presenterPackageName.value)}/${provider.pageName.value}Presenter.$languageSuffix")
        save(armsPresenter(isKt, provider), presenterFile)
    }
    if (provider.needModel.value) {
        val modelFile = File(data.rootDir, "${fFmSlashedPackageName(provider.modelPackageName.value)}/${provider.pageName.value}Model.$languageSuffix")
        save(armsModel(isKt, provider), modelFile)
    }

    if (provider.needDagger.value) {
        val componentFile = File(data.rootDir, "${fFmSlashedPackageName(provider.componentPackageName.value)}/${provider.pageName.value}Component.$languageSuffix")
        val moduleFile = File(data.rootDir, "${fFmSlashedPackageName(provider.moudlePackageName.value)}/${provider.pageName.value}Module.$languageSuffix")
        save(armsComponent(isKt, provider), componentFile)
        save(armsModule(isKt, provider), moduleFile)
    }
}

fun fFmSlashedPackageName(oVar: String): String {
    return "src/main/java/${oVar.replace('.', '/')}"
}