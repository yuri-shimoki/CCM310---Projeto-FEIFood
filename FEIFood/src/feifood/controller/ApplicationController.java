package feifood.controller;

import feifood.model.ApplicationModel;

import feifood.view.JanelaDeLogin;

/**
 * A classe principal que é responsável por conectar todos os outros módulos
 * desta aplicação.
 * 
 * @author Yuri
 */
public class ApplicationController
{
    private ApplicationModel model;
    
    public ApplicationController()
    {
        model = new ApplicationModel(new JanelaDeLogin());
        model.setVisibilidadeDaJanela(true);
    }
}
