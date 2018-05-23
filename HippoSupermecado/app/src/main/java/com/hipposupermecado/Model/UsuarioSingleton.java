package com.hipposupermecado.Model;

public class UsuarioSingleton {

    private static final UsuarioSingleton INSTANCE = new UsuarioSingleton();

    public UsuarioLogado usuarioLogado = new UsuarioLogado();

    private UsuarioSingleton () {}

    public static UsuarioSingleton getInstance() {
        return INSTANCE;
    }
}
