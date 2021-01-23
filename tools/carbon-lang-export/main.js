/*
 * Copyright 2021 Nikola Trubitsyn
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

import {languageToProps, kotlinize, propsToPairsArray} from "./module.mjs"
import {LANGUAGES} from "./languages.mjs"

let props = LANGUAGES.map(languageToProps)
let pairs = propsToPairsArray(props)
let code = kotlinize(pairs)
console.log(code)