package clients;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ClientRepository repo;

    @InjectMocks
    ClientService service;

    @Test
    void createClient() {
        when(repo.clientNotExists(any())).thenReturn(true);

        service.createClient("John Doe");

        verify(repo).saveClient(eq("JOHN DOE"));
    }

    @Test
    void notCreateClient() {
        service.createClient("John Doe");

        verify(repo, never()).saveClient(any());
    }
}