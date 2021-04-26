package clients;


import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    private List<String> names = new ArrayList<>();

    public boolean clientNotExists(String name) {
        return !names.contains(name);
    }

    public void saveClient(String name) {
        names.add(name);
    }

    public List<String> getNames() {
        return names;
    }
}
