package com.cloudesire.capra

data class UrbanData(var nome: String, var codice: String, var zona: CodeName, var regione: CodeName,
                     var cm: CodeName, var provincia: CodeName, var sigla: String, var codiceCatastale: String,
                     var cap: String)

data class CodeName(var nome: String, var codice: String)
