package com.web.hevepratas.servicies;

import com.web.hevepratas.dtos.AddressDTO;
import com.web.hevepratas.entities.Address;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.enums.UserRole;
import com.web.hevepratas.exceptions.AuthorizationException;
import com.web.hevepratas.exceptions.ResourceNotFoundException;
import com.web.hevepratas.mappers.GlobalMapper;
import com.web.hevepratas.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserService userService;
    private final AddressRepository repository;

    public AddressDTO findByUserId(Long userId) {
        return new AddressDTO(catchAddressByUserId(userId));
    }


    public String update(Long userId, AddressDTO dtoBody, Authentication authentication) {
        String retResponse = null;
        User authenticatedUser = (User) authentication.getPrincipal();
        Address addressObject = catchAddressByUserId(userId);
        User addressUser = GlobalMapper.mapToUser(userService.findById(userId));

        if(!addressUser.getUserEmail().equals(authenticatedUser.getUserEmail())){
            if(!authenticatedUser.getUserRole().equals(UserRole.ADMIN)){
                throw new AuthorizationException("Parece que você não tem permissão para realizar esta ação...");
            }
        }

        addressObject.setCity(dtoBody.getCity());
        addressObject.setStreet(dtoBody.getStreet());
        addressObject.setCep(dtoBody.getCep());
        addressObject.setComplement(dtoBody.getComplement());
        addressObject.setHouseNumber(dtoBody.getHouseNumber());

        try {
            repository.save(addressObject);
            retResponse = "Endereço atualizado com sucesso";
        } catch (Exception e) {
            retResponse = "Não foi possível atualizar o endereço... " + e.getMessage();
        }

        return retResponse;
    }


    private Address catchAddressByUserId(Long userId) {
        Address retAddress = null;
        retAddress = repository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar nenhum usuário com o id " + userId));

       return retAddress;
    }

}
