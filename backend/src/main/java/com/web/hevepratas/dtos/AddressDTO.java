package com.web.hevepratas.dtos;

import java.util.List;

import com.web.hevepratas.entities.Address;
import com.web.hevepratas.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressDTO {

    private Long id;
    private String city;
    private String street;
    private String cep;
    private String complement;
    private int houseNumber;
//    private User user;


    public AddressDTO () {}

    public AddressDTO (Address entity)  {
        this.id = entity.getId();
        this.city = entity.getCity();
        this.street = entity.getStreet();
        this.cep = entity.getCep();
        this.complement = entity.getComplement();
        this.houseNumber = entity.getHouseNumber();
//        this.user = entity.getUser();
    }

    private TranDetails save(TranDetails entity) {

        // Recupera todos os deal que possuem o deal num passado. Caso não existe nengum deal com esse deal_num, ele cria o primeiro, passando a versão 1
        List<TranDetails> existingDeals = repository.findAllByDealNum(entity.getDealnum());
        if(existingDeals.isEmpty()) {
            entity.setVersion(1L);

            return repository.save(entity);
        }

        // Verifica se existe algum dela com o deal_num e tran_num passados, caso exista, ela vai simplesmente atualizar as informações daquele deal
        TranDetails existingEntity = repository.findByTrannumAndDealnum(entity.getTrannum(), entity.getDealnum());

        if(existingEntity != null) {
            return repository.save(entity);
        }


        // Se ele não cair em nenhuma das verificações ele vai cair aqui. Significa que temos um deal com o deal_num passado, mas não com o tran_num. 
        // Então ele vai salvar essa nova vewrsão do deal, com esse tran+num, na base de dados

        TranDetails tranDetailHighVersion = repository.findTopByDealnumOrderByVersionDesc(entity.getDealnum());

        dasd
    }

}
