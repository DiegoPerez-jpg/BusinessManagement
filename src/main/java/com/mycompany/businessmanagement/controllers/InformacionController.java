package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Informacion;
import com.mycompany.businessmanagement.util.RegexUtil;

public class InformacionController extends ControllerBase<Informacion> {

    @Override
    protected void validar(Informacion info) {
        String n=info.getNif();
        String e=info.getEmail();
        String t=info.getTelefono();
        if(n!=null&&e!=null&&t!=null){
            try {
                RegexUtil.regexCIF_NIE_NIF(n);
                RegexUtil.email(e);
                RegexUtil.telefonoEspanol(t);
            } catch (Exception ex) {
                System.getLogger(InformacionController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }

    

    public static InformacionController create() {
        return new InformacionController();
    }
}
