package com.web.hevepratas.servicies;

import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.exceptions.ResourceNotFoundException;
import com.web.hevepratas.mappers.GlobalMapper;
import com.web.hevepratas.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserDTO save(UserDTO userDTO) {
        User entity = GlobalMapper.mapToUser(userDTO);
        entity.setUserPassword(encoder.encode(entity.getUserPassword()));

        return new UserDTO(repository.save(entity));
    }

    public Collection<UserDTO> allUsers() {
        Collection<User> users = repository.findAll();
        Collection<UserDTO> usersDto = new ArrayList<>();

        for(User user : users) {
            usersDto.add(new UserDTO(user));
        }

        return usersDto;
    }

    public UserDTO findById(Long id) {
        Optional<User> optionalObject = repository.findById(id);
        return  new UserDTO(optionalObject.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado")));
    }

    public String delete(Long id) {
        User userObject = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com id " + id + " não encontrado"));

        repository.delete(userObject);

        return "Usuário deletado com sucesso!";
    }

    public UserDTO update(Long id, UserDTO dtoBody, Authentication authentication) {
        User userObject = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com id " + id + " não encontrado"));

        System.out.println(userObject.getUserEmail());
        System.out.println(authentication.getName());

        for(GrantedAuthority authority : authentication.getAuthorities()){
            System.out.println(authority);
        }

        if(userObject == null){
            throw new RuntimeException("O usuário com id " + id + " foi encontrado, mas algo deu errado. Ele veio como null.");
        }

        if(!userObject.getUserEmail().equals(authentication.getName()) || !authentication.getAuthorities().isEmpty()) {
            if(authentication.getAuthorities().contains("ROLE_ADMIN")) {
                throw new RuntimeException("Não tem permissão para realizar esta ação");
            }
        }

        userObject.setUserName(dtoBody.getUserName());
        userObject.setUserEmail(dtoBody.getUserEmail());
        userObject.setUserPassword(encoder.encode(dtoBody.getUserPassword()));
        userObject.setUserRole(dtoBody.getUserRole());

        repository.save(userObject);

        return new UserDTO(userObject);
    }

    public User findByEmail(String email) {
        return repository.findByUserEmail(email);
    }
}
