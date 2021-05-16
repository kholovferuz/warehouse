package uz.pdp.springbootapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootapp.DTO.ClientDTO;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Client;
import uz.pdp.springbootapp.Repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    // ADD client
    public Result addClient(ClientDTO clientDTO) {
        boolean existsByName = clientRepository.existsByName(clientDTO.getName());
        if (existsByName) {
            return new Result("This client already exists", false);
        }
        Client newClient = new Client();
        newClient.setName(clientDTO.getName());
        newClient.setPhoneNumber(clientDTO.getPhoneNumber());
        clientRepository.save(newClient);
        return new Result("Client added", true);
    }

    // READ all clients
    public List<Client> readALlClients() {
        return clientRepository.findAll();
    }

    // READ clients by id
    public Client readClientById(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElseGet(Client::new);
    }

    // UPDATE client by id
    public Result updateClient(ClientDTO clientDTO, Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            boolean existsByName = clientRepository.existsByName(clientDTO.getName());
            if (existsByName) {
                return new Result("This client already exists", false);
            }

            Client editedClient = optionalClient.get();
            editedClient.setName(clientDTO.getName());
            editedClient.setPhoneNumber(clientDTO.getPhoneNumber());

            clientRepository.save(editedClient);
            return new Result("Client updated", true);
        }
        return new Result("Client with this id is not found", false);
    }

    // DELETE client by id
    public Result deleteClient(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            clientRepository.deleteById(id);
            return new Result("Client deleted", true);
        }
        return new Result("Client with this id is not found", false);

    }
}
