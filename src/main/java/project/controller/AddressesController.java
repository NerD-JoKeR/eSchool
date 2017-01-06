package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.model.Address;
import project.persistence.AddressMapper;

import java.util.List;

/**
 * Created by JoKeR on 04.01.2017.
 */

@Controller
public class AddressesController {

    @Autowired
    private AddressMapper addressMapper;

    @GetMapping("/addressesList")
    public String addressesList(Model model) {
        List<Address> addressList = addressMapper.getAllAddresses();
        model.addAttribute("addressesList", addressList);
        return "addressesList";
    }

    @GetMapping("/addAddress")
    public String addAddressForm(Model model) {
        model.addAttribute("address", new Address());
        return "addAddress";
    }

    @PostMapping("/addAddress")
    public String addAddressSubmit(@ModelAttribute Address address) {
        System.out.println(address);
        addressMapper.insertAddress(address);
        return "redirect:/addressesList";
    }

    @GetMapping("/editAddress/{studentId}")
    public String editAddressForm(Model model, @PathVariable(value = "studentId") int id) {
        Address address = addressMapper.getAddressById(id);
        model.addAttribute("address", address);
        return "editAddress";
    }

    @PostMapping("/editAddress")
    public String editAddressSumbit(@ModelAttribute Address address) {
        addressMapper.updateAddress(address);
        return "redirect:/addressesList";
    }

    @GetMapping("/deleteAddress/{addressId}")
    public String deleteAddress(@PathVariable(value = "addressId") int id) {
        addressMapper.deleteAddress(id);
        return "redirect:/addressesList";
    }
}
