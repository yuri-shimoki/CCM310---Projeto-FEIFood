package feifood.controller;

/**
 * A classe principal que é responsável por conectar todos os outros módulos
 * desta aplicação.
 */
public class ApplicationController
{
    public ApplicationController()
    {
        var loginController = new LoginController();
    }
}
