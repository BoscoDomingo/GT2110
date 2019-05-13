package interfaces;

public interface IMenu {

    public boolean menu(); //se llama a todos los menus en una pantalla (ejemplo: estamos en el perfil
    // de un usuario, se llama a perfil.menu() que dentro tendrá una opción que llamará a timeline.menu()
    //EJEMPLO DE UN MENÚ
   /*
    boolean accionValida = false, goBack = false;
    Scanner scan = new Scanner(System.in);
    while (!accionValida) {
        int selector = scan.nextInt();
        switch (selector) {
        //METED CASES SÓLO DEL 0 AL 8
            case 0:
                accionValida = true;
                //vuestro código aqui
                break;

                //...
            case 8:
                accionValida = true;
                //LLAMADA A CUALQUIER OTRO MENU QUE SE PUEDA MOSTRAR SI LO NECEISTAIS, INCLUIDAS MÁS ACCIONES SI 8 NO
                //SON SUFICIENTES
                if(otraCosa.menu() = true) accionValida = false; //Significa que vuelve del otro menu a este
                break;
            case 9:
                accionValida = true;
                goBack = true;
                break;
            default:
                System.out.println("Por favor, introduzca un número válido (del 0 al ");//VUESTRO NUMERO.SI HAY 3
                //OPCIONES, PONÉIS EL 2
        }
    }
    return goBack; //Si es true, se vuelve a mostrar el menú que haya llamado a este
    */
}
