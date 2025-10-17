package com.example.appconversacion.ViewModel

import androidx.lifecycle.ViewModel
import com.example.appconversacion.Model.UsuarioErrores
import com.example.appconversacion.Model.UsuarioState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UsuarioViewModel : ViewModel () {
    private val _estado = MutableStateFlow(value = UsuarioState())

    val estado: StateFlow<UsuarioState> = _estado

    fun onNombreChange(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    //Actualizar el campo de nombre de usuario

    fun onCorreoChange(valor: String){
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    // Actualizar campo del correor electronico

    fun onClaveChange(valor: String){
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    // Actualizar campo de la clave del usuario

    fun onDireccionChange(valor: String){
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    //Actualizar campo de direccion de usuario

    fun onAceptarCondicionesChange(valor: Boolean){
        _estado.update { it.copy(aceptarTerminos = valor) }
    }

    fun validarFormulario(): Boolean{
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            nombre = if (estadoActual.nombre.isBlank()) "Este campo es obligatorio" else null,
            correo = if (!estadoActual.correo.contains("@")) "Correo invalido" else null,
            clave = if (estadoActual.clave.length > 6) " Debe tener mas de seis caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank())"El campo es obligatorio" else null,
        )

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.direccion,
            errores.clave
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }

        return !hayErrores
    }
}