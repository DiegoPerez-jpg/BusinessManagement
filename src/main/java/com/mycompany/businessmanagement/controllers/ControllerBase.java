/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.businessmanagement.controllers;

/**
 *
 * @author alexd
 */
public abstract class ControllerBase<T> {
    public T crear(T obj) {
        validar(obj);
        return obj;
    }

    public T actualizar(T obj) {
        validar(obj);
        return obj;
    }

    protected abstract void validar(T obj);
}
