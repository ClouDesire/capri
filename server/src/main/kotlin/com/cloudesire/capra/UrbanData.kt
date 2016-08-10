package com.cloudesire.capra

import com.fasterxml.jackson.annotation.JsonSetter

data class UrbanData(val nome: String, var codice: String, var zona: CodeName, var regione: CodeName,
                     var cm: CodeName, var provincia: CodeName, var sigla: String, var codiceCatastale: String) {

    var cap: List<String> = listOf()

    @JsonSetter("cap") fun setCap(value: Any) {
        when (value) {
            is String -> cap = listOf(value)
            is List<*> -> cap = value as List<String>
        }
    }

    fun province(): String {
        return if (!provincia.nome.equals( "-" )) provincia.nome else cm.nome
    }

    fun abbreviation() = sigla

    fun region() = regione.nome

    data class CodeName(var nome: String, var codice: String)
}


