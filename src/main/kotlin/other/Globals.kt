package other

import java.text.SimpleDateFormat
import java.util.*

fun commonAnnotation(provider: ArmsPluginTemplateProviderImpl) = """
/**
 * Created on ${SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date(System.currentTimeMillis()))}
 * @author Love_xie 
 * module name is ${provider.pageName.value}${if(provider.needActivity.value) "Activity" else "Fragment"}
 */
""".trimIndent()

val armsAnnotation = """
/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on ${SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date(System.currentTimeMillis()))}
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
""".trimIndent()