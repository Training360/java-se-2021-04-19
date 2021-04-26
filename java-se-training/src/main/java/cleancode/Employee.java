package cleancode;

import lombok.Data;

import java.util.List;

@Data
public class Employee {

    private List<Address> addresses;

    public void addAddress(Address address) {
        addresses.add(address);

    }

    public int numberOfAddresses() {
        return addresses.size();
    }
}
