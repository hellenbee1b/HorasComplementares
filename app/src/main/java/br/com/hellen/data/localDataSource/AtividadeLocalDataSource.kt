package br.com.hellen.data.localDataSource

import android.content.SharedPreferences
import br.com.hellen.data.models.AtividadeModel
import com.google.gson.Gson

class AtividadeLocalDataSource(private val sharedPreferences: SharedPreferences) {

    fun getAtividade(curso:String): AtividadeModel? {
        val atividadeJSON = sharedPreferences.getString(curso, "")
        atividadeJSON?.let{
            return Gson().fromJson(atividadeJSON, AtividadeModel::class.java)
        }
        return null
    }

    fun putAtividade(atividade: AtividadeModel){
        sharedPreferences.edit().apply{
            putString("curso", atividade.curso)
            putInt("horasTotais", atividade.horasTotais)
            putLong("dataInicio", atividade.dataInicio!!.time)
            putLong("dataTermino", atividade.dataTermino!!.time)
            putString("professor", atividade.professor)
            putString("categoria", atividade.categoria)
            putString("objetivo", atividade.objetivo)
            putString("descricao", atividade.descricao)
        }.apply()
    }

    fun deleteAtividade(atividade:AtividadeModel){
        sharedPreferences.edit().remove(atividade.curso).apply()
    }
}