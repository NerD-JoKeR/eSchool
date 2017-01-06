package project.persistence;

import project.model.Address;

import java.util.List;

/**
 * Created by JoKeR on 04.01.2017.
 */
public interface AddressMapper {
    /**
     * @return List of addresses
     */
    public List<Address> getAllAddresses();

    /**
     * @param id
     * @return Address
     */
    public Address getAddressById(int id);

    /**
     * @param id
     * @return List of Addresses
     */
    public List<Address> getAddressByStudentId(int id);

    /**
     * @param Address
     * @return the number of rows affected
     */
    public int insertAddress(Address Address);

    /**
     *
     * @param address
     * @return the number of rows affected
     */
    public int updateAddress(Address address);

    /**
     * @param id
     * @return the number of rows affected
     */
    public int deleteAddress(int id);
}
