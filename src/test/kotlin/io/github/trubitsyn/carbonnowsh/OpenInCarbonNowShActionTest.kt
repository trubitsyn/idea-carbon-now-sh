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

package io.github.trubitsyn.carbonnowsh

import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class OpenInCarbonNowShActionTest {
    val action = OpenInCarbonNowShAction()

    @Test
    fun testUrlCorrect() {
        val carbonUrl = "https://carbon.now.sh/?code="
        assertEquals(carbonUrl, OpenInCarbonNowShAction.CARBON_URL)
    }

    @Test
    fun testUrlWithParametersCorrect() {
        val contents =
                """
                    package foo

                    class Foo {

                        fun foo() {
                            println("foo")
                        }
                    }
                """.trimIndent()

        var url: String? = null
        action.openInCarbonNowSh(contents, {
            url = it
        })
        val actualUrl = "https://carbon.now.sh/?code=package%20foo%0A%0Aclass%20Foo%20%7B%0A%0A%20%20%20%20fun%20foo()%20%7B%0A%20%20%20%20%20%20%20%20println(%22foo%22)%0A%20%20%20%20%7D%0A%7D"
        assertEquals(actualUrl, url)
    }

    @Test
    fun testEmptyContents() {
        action.openInCarbonNowSh("", {
            fail()
        })
    }

    @Test
    fun testNullContents() {
        action.openInCarbonNowSh(null, {
            fail()
        })
    }
}