/*
 * Copyright 2018 Nikola Trubitsyn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.trubitsyn.carbonnowsh

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ide.CopyPasteManager
import java.awt.datatransfer.DataFlavor
import java.net.URLEncoder

class OpenInCarbonNowShAction : AnAction() {

    companion object {
        const val CARBON_URL = "https://carbon.now.sh"
    }

    override fun actionPerformed(event: AnActionEvent) {
        PlatformDataKeys.COPY_PROVIDER.getData(event.dataContext)?.let {
            it.performCopy(event.dataContext)

            val psiFile = event.getData(CommonDataKeys.PSI_FILE)
            val extension = psiFile?.virtualFile?.extension
            val contents = CopyPasteManager
                .getInstance()
                .getContents<String>(DataFlavor.stringFlavor)
                ?.trimIndent()

            openInCarbonNowSh(contents, extension) {
                BrowserUtil.browse(it)
            }
        }
    }

    fun openInCarbonNowSh(contents: String?, extension: String?, browse: (url: String) -> Unit) {
        if (contents?.isNotEmpty() == true) {
            val url = buildUrl(
                language = Languages.forExtension(extension),
                code = encode(contents)
            )
            browse(url)
        }
    }

    private fun buildUrl(language: String, code: String): String {
        return "$CARBON_URL?l=$language&code=$code"
    }

    private fun encode(s: String): String {
        return URLEncoder.encode(s, "UTF-8")
            .replace("+", "%20")
            .replace("%", "%25")
    }

    override fun update(event: AnActionEvent?) {
        if (event == null) {
            return
        }
        val context = event.dataContext
        val provider = PlatformDataKeys.COPY_PROVIDER.getData(context) ?: return
        val isCopyAvailable = provider.isCopyEnabled(context) && provider.isCopyVisible(context)
        event.presentation.apply {
            isVisible = isCopyAvailable
            isEnabled = isCopyAvailable
        }
    }
}
