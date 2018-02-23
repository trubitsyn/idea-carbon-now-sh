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
    fun baseUrlShouldBeCorrect() {
        val carbonUrl = "https://carbon.now.sh/?code="
        assertEquals(carbonUrl, OpenInCarbonNowShAction.CARBON_URL)
    }

    @Test
    fun contentsShouldBeProperlyEncoded() {
        val contents =
                """
                    <table align="center" class="headcontainer" width="100%" height="33%">
                        <tbody>
                            <tr>
                                <td>All the rows go here!</td>
                            </tr>
                        </tbody>
                    </table>
                """.trimIndent()

        var url: String? = null
        action.openInCarbonNowSh(contents, {
            url = it
        })
        val actualUrl = "https://carbon.now.sh/?code=%253Ctable%2520align%253D%2522center%2522%2520class%253D%2522headcontainer%2522%2520width%253D%2522100%2525%2522%2520height%253D%252233%2525%2522%253E%250A%2520%2520%2520%2520%253Ctbody%253E%250A%2520%2520%2520%2520%2520%2520%2520%2520%253Ctr%253E%250A%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520%2520%253Ctd%253EAll%2520the%2520rows%2520go%2520here%2521%253C%252Ftd%253E%250A%2520%2520%2520%2520%2520%2520%2520%2520%253C%252Ftr%253E%250A%2520%2520%2520%2520%253C%252Ftbody%253E%250A%253C%252Ftable%253E"
        assertEquals(actualUrl, url)
    }

    @Test
    fun emptyContentsShouldNotBeAccepted() {
        action.openInCarbonNowSh("", {
            fail()
        })
    }

    @Test
    fun nullContentsShouldNotBeAccepted() {
        action.openInCarbonNowSh(null, {
            fail()
        })
    }
}