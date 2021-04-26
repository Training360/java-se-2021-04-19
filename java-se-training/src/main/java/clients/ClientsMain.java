package clients;

public class ClientsMain {

    public static void main(String[] args) {
        var repo = new ClientRepository();
        var services = new ClientService(repo);

        services.createClient("John Doe");
        services.createClient("Jane Doe");
        services.createClient("John Doe");

        System.out.println(services.getNames());
    }
}
