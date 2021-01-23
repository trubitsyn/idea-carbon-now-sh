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

import mimeTypes from "mime-types";
import {LangLine} from "@itassistors/langline"

export function languageToProps(language) {
    const name = language['name']
    const mime = language['mime']
    const mode = language['mode']

    const carbonType = (mime !== undefined) ? mime : mode
    const extensionLists = [
        extensionsByMime(mime),
        extensionsByName(name)
    ]
    const dot = '.'
    const extensions = Array.from(new Set(
        extensionLists.flat()
    )).filter(it => it !== undefined).map(it => {
        if (it.startsWith(dot)) {
            return it.replace('.', '')
        } else {
            return it
        }
    })
    return {name, carbonType, extensions}
}

function extensionsByMime(mime) {
    return mimeTypes.extensions[mime]
}

function extensionsByName(name) {
    let safeName = name.trim()
    if (safeName.includes('/')) {
        let parts = safeName.split('/')
        return [
            extensionsByName(parts[0]),
            extensionsByName(parts[1])
        ].flat()
    }
    let language = new LangLine().withLanguageName(name.toLowerCase())
    return language.extensions
}

export function propsToPairsArray(props) {
    let pairs = []
    props.forEach(prop => {
        prop.extensions.forEach(extension => {
            pairs.push({
                extension: extension,
                carbonType: prop.carbonType
            })
        })
    })
    pairs.sort((a, b) => a.extension.localeCompare(b.extension))
    return pairs
}

export function safeVariableName(str) {
    return str
        .replace(/\+/g, '_plus_')
        .replace(/-/g, '_minus_')
        .replace(/#/g, '_sharp_')
        .replace(/\//g, '_slash_')
        .replace(/__/g, '_')
}

let packageName = `package dev.trubitsyn.carbonnowsh`
let objectStart = `object Languages {`
let objectEnd = `}`
let carbonTypeNameVar = `{carbonTypeName}`
let carbonTypeVar = `{carbonType}`
let extensionVar = `{extension}`
let constantTemplate = `private const val ${carbonTypeNameVar} = "${carbonTypeVar}"`
let mappingStart = `private val mapping = mapOf(`
let entryTemplate = `"${extensionVar}" to ${carbonTypeNameVar}`
let mappingEnd = `)`
let newline = `\n`
let blankline = newline.repeat(2)
let indent = ` `.repeat(4)
let comma = `,`
let func = `fun forExtension(extension: String?): String {
        if (extension == null) {
            return AUTO
        }
        return mapping[extension] ?: AUTO
    }`

export function kotlinize(extensionToCarbonTypePairs) {
    let constants = []
    let entries = []
    extensionToCarbonTypePairs.forEach((pair) => {
        let safeCarbonType = safeVariableName(pair.carbonType)
        let carbonTypeConstant = safeCarbonType.toUpperCase()
        let constant = constantTemplate
            .replace(carbonTypeNameVar, carbonTypeConstant)
            .replace(carbonTypeVar, pair.carbonType)
        if (!constants.includes(constant)) {
            constants.push(constant)
        }
        let entry = entryTemplate
            .replace(extensionVar, pair.extension)
            .replace(carbonTypeNameVar, carbonTypeConstant)
        entries.push(entry)
    })
    let autoConstant = constantTemplate
        .replace(carbonTypeNameVar, "AUTO")
        .replace(carbonTypeVar, "auto")
    constants.push(autoConstant)
    constants.sort()

    let doubleIndent = indent.repeat(2)
    let entrySeparator = comma + newline + doubleIndent
    let newlineIndent = newline + indent
    let code = [
        packageName,
        blankline,
        objectStart,
        newline,
        indent,
        constants.join(newlineIndent),
        blankline,
        indent,
        mappingStart,
        newline,
        doubleIndent,
        entries.join(entrySeparator),
        newline,
        indent,
        mappingEnd,
        blankline,
        indent,
        func,
        newline,
        objectEnd
    ]
    return code.join('')
}