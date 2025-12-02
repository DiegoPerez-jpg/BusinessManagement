package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Informacion;
import com.mycompany.businessmanagement.util.RegexUtil;

public class InformacionController extends ControllerBase<Informacion> {

    @Override
    protected void validar(Informacion info) {

        if (info.getNif() == null || !info.getNif().matches(RegexUtil.REGEX_NIF_CIF_NIE)) {
            throw new IllegalArgumentException("NIF inválido");
        }

        if (info.getEmail() != null && !info.getEmail().isBlank() &&
            !info.getEmail().matches(RegexUtil.REGEX_EMAIL)) {
            throw new IllegalArgumentException("Email inválido");
        }

        if (info.getTelefono() != null && !info.getTelefono().isBlank() &&
            !info.getTelefono().matches(RegexUtil.REGEX_TELEFONO)) {
            throw new IllegalArgumentException("Teléfono inválido");
        }
    }

    public static InformacionController create() {
        return new InformacionController();
    }
}
