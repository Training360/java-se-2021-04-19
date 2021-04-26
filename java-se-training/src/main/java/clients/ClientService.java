package clients;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;

    public void createClient(String name) {
        var upperName = name.toUpperCase();
        if (clientRepository.clientNotExists(upperName)) {
            clientRepository.saveClient(upperName);
        }
    }

    public List<String> getNames() {
        return clientRepository.getNames();
    }
}
