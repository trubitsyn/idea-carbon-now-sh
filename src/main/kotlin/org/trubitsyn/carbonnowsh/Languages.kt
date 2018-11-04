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

package org.trubitsyn.carbonnowsh

object Languages {
    private const val AUTO = "auto"
    private const val PLAINTEXT = "text"
    private const val APACHE = "text/apache"
    private const val BASH = "application/x-sh"
    private const val C = "text/x-csrc"
    private const val CPP = "text/x-c++src"
    private const val CSHARP = "text/x-csharp"
    private const val CLOJURE = "clojure"
    private const val COBOL = "cobol"
    private const val COFFEESCRIPT = "coffeescript"
    private const val CRYSTAL = "crystal"
    private const val CSS = "css"
    private const val D = "d"
    private const val DART = "dart"
    private const val DIFF = "text/x-diff"
    private const val DJANGO = "django"
    private const val DOCKER = "dockerfile"
    private const val ELIXIR = "elixir"
    private const val ELM = "elm"
    private const val ERLANG = "erlang"
    private const val FORTRAN = "fortran"
    private const val FSHARP = "mllike"
    private const val OCAML = "mllike"
    private const val GRAPHQL = "graphql"
    private const val GO = "go"
    private const val GROOVY = "groovy"
    private const val HANDLEBARS = "handlebars"
    private const val HASKELL = "haskell"
    private const val HAXE = "haxe"
    private const val HTML = "htmlmixed"
    private const val JAVA = "text/x-java"
    private const val JAVASCRIPT = "javascript"
    private const val JSON = "application/json"
    private const val JSX = "jsx"
    private const val JULIA = "julia"
    private const val KOTLIN = "text/x-kotlin"
    private const val LATEX = "stex"
    private const val LISP = "commonlisp"
    private const val LUA = "lua"
    private const val MARKDOWN = "markdown"
    private const val MATHEMATICA = "mathematica"
    private const val MYSQL = "text/x-mysql"
    private const val NGINX = "nginx"
    private const val NIM = "nimrod"
    private const val NTRIPLES = "application/n-triples"
    private const val OBJECTIVEC = "text/x-objectivec"
    private const val OCTAVE = "text/x-octave"
    private const val PASCAL = "pascal"
    private const val PERL = "perl"
    private const val PHP = "text/x-php"
    private const val POWERSHELL = "powershell"
    private const val PYTHON = "python"
    private const val R = "r"
    private const val RUBY = "ruby"
    private const val RUST = "rust"
    private const val SASS = "sass"
    private const val SCALA = "text/x-scala"
    private const val SMALLTALK = "smalltalk"
    private const val SQL = "sql"
    private const val SPARQL = "application/sparql-query"
    private const val STYLUS = "stylus"
    private const val SWIFT = "swift"
    private const val TCL = "tcl"
    private const val TURTLE = "text/turtle"
    private const val TYPESCRIPT = "application/typescript"
    private const val VBNET = "vb"
    private const val VERILOG = "verilog"
    private const val VHDL = "vhdl"
    private const val VUE = "vue"
    private const val XML = "xml"
    private const val YAML = "yaml"

    private val mapping = hashMapOf(
            "" to PLAINTEXT,
            // Apache omitted
            "sh" to BASH,
            "c" to C,
            "cpp" to CPP,
            "cxx" to CPP,
            "h" to C,
            "hpp" to CPP,
            "cs" to CSHARP,
            "clj" to CLOJURE,
            "cljs" to CLOJURE,
            "cljc" to CLOJURE,
            "edn" to CLOJURE,
            "cbl" to COBOL,
            "cob" to COBOL,
            "cpy" to COBOL,
            "coffee" to COFFEESCRIPT,
            "litcoffee" to COFFEESCRIPT,
            "cr" to CRYSTAL,
            "css" to CSS,
            "d" to D,
            "dart" to DART,
            "diff" to DIFF,
            // Django omitted
            // Dockerfile omitted
            "ex" to ELIXIR,
            "exs" to ELIXIR,
            "elm" to ELM,
            "erl" to ERLANG,
            "hrl" to ERLANG,
            "f" to FORTRAN,
            "for" to FORTRAN,
            "f90" to FORTRAN,
            "fs" to FSHARP,
            "fsi" to FSHARP,
            "fsx" to FSHARP,
            "fsscript" to FSHARP,
            "ml" to OCAML,
            "mli" to OCAML,
            // GraphQL omitted
            "go" to GO,
            "groovy" to GROOVY,
            // Handlebars omitted
            "hs" to HASKELL,
            "lhs" to HASKELL,
            "hx" to HAXE,
            "hxml" to HAXE,
            "html" to HTML,
            "htm" to HTML,
            "java" to JAVA,
            "js" to JAVASCRIPT,
            "json" to JSON,
            "jsx" to JSX,
            "jl" to JULIA,
            "kt" to KOTLIN,
            "kts" to KOTLIN,
            "tex" to LATEX,
            "lisp" to LISP,
            "lsp" to LISP,
            "l" to LISP,
            "cl" to LISP,
            "fasl" to LISP,
            "scm" to LISP,
            "ss" to LISP,
            "lua" to LUA,
            "md" to MARKDOWN,
            "markdown" to MARKDOWN,
            // Mathematica omitted
            // MySQL omitted
            // NGINX omitted
            "nim" to NIM,
            "nt" to NTRIPLES,
            // duplicate "h" key
            "h" to OBJECTIVEC,
            "m" to OBJECTIVEC,
            "mm" to OBJECTIVEC,
            "M" to OBJECTIVEC,
            "text/x-octave" to OCTAVE,
            "pp" to PASCAL,
            "pas" to PASCAL,
            "inc" to PASCAL,
            "pl" to PERL,
            "pm" to PERL,
            "t" to PERL,
            "pod" to PERL,
            "php" to PHP,
            "phtml" to PHP,
            "php3" to PHP,
            "php4" to PHP,
            "php5" to PHP,
            // will it break when PHP 8 is out?
            "php7" to PHP,
            "phps" to PHP,
            "php-s" to PHP,
            "ps1" to POWERSHELL,
            "ps1xml" to POWERSHELL,
            "psc1" to POWERSHELL,
            "psd1" to POWERSHELL,
            "psm1" to POWERSHELL,
            "pssc" to POWERSHELL,
            "cdxml" to POWERSHELL,
            "py" to PYTHON,
            "pyc" to PYTHON,
            "pyd" to PYTHON,
            "pyo" to PYTHON,
            "pyw" to PYTHON,
            "pyz" to PYTHON,
            "r" to R,
            "R" to R,
            "RData" to R,
            "rds" to R,
            "rda" to R,
            "rb" to RUBY,
            "rs" to RUST,
            "rlib" to RUST,
            "sass" to SASS,
            "scss" to SASS,
            "scala" to SCALA,
            "sc" to SCALA,
            // Smalltalk omitted
            "sparql" to SPARQL,
            "sql" to SQL,
            "styl" to STYLUS,
            "swift" to SWIFT,
            "tcl" to TCL,
            "tbc" to TCL,
            "logo" to TURTLE,
            "turtle" to TURTLE,
            "ts" to TYPESCRIPT,
            "tsx" to TYPESCRIPT,
            "vb" to VBNET,
            "v" to VERILOG,
            "vhdl" to VHDL,
            "vue" to VUE,
            "xml" to XML,
            "yml" to YAML,
            "yaml" to YAML
    )

    fun forExtension(extension: String?): String {
        if (extension == null) {
            return AUTO
        }
        return mapping[extension] ?: AUTO
    }
}