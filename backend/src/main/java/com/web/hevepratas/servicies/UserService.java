package com.web.hevepratas.servicies;

import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.enums.UserRole;
import com.web.hevepratas.exceptions.AuthorizationException;
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
       User updatedUser = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com id " + id + " não encontrado"));

        User authenticatedUser = (User) authentication.getPrincipal();

        if(!updatedUser.getUserEmail().equals(authenticatedUser.getUserEmail())){
            if(!authenticatedUser.getUserRole().equals(UserRole.ADMIN)) {
                throw new AuthorizationException("Parece que você não tem permissão para realizar esta ação...");
            }
        }

        updatedUser.setUserName(dtoBody.getUserName());
        updatedUser.setUserEmail(dtoBody.getUserEmail());
        updatedUser.setUserPassword(encoder.encode(dtoBody.getUserPassword()));
        updatedUser.setUserRole(dtoBody.getUserRole());

        repository.save(updatedUser);

        return new UserDTO(updatedUser);

    }

    public User findByEmail(String email) {
        return repository.findByUserEmail(email);
    }

    public User saveByUserEntity(UserDTO dto) {
        User entity = null;

        try {
            if (dto != null) {
                entity = new User();

                entity.setUserName(dto.getUserName());
                entity.setUserEmail(dto.getUserEmail());
                entity.setUserPassword(encoder.encode(dto.getUserPassword()));
                entity.setUserRole(dto.getUserRole());

                entity = repository.save(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return entity;
    }
}
