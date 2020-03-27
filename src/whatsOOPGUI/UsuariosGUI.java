package whatsOOPGUI;

import whatsOOPConsole.ListUsers;

public class UsuariosGUI {

    static ListUsers lista = new ListUsers();
    static String total = "";
    String usuTotal = "";
    private static int a= 23;

    public UsuariosGUI(ListUsers listaUsuarios) {
        this.lista = listaUsuarios;
    }

    public UsuariosGUI() {

    }

    public String listarUsuarios() {
        for (int i = 0; i < lista.listUsers.size(); i++) {
            usuTotal = usuTotal + "\n" + lista.listUsers.get(i).getName() + "\n";
            System.out.println(lista.listUsers.get(i).getName() + a);
            System.out.println(this.a);
        }
        total = usuTotal;
        return total;
    }

}
