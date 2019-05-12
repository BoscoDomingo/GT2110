package interfaces;

public interface IMenu {

    public void menu(int ultimoNumero); //se llama a todos los menus en una pantalla (ejemplo: estamos en el perfil
    // de un usuario, se llama a perfil.menu(0) que dentro llamará a timeline.menu(5), suponiendo que las opciones
    // del 0 al 4 están cogidas por acciones del perfil, y del 5 al 8, de la timeline
}
