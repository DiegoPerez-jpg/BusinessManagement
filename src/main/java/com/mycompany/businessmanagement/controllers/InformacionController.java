/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Informacion;

/**
 *
 * @author alexd
 */
public class InformacionController {
        //alex cambia esto
        public Informacion create(String text, String text1, String text2) {
            return new Informacion(0,text,text1,text2);
        }
}
