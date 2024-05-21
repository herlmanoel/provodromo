package com.provodromo.provodromo.service.base;

import java.util.Set;

public interface BaseServiceNew<DTO, ID> {

    DTO findById(ID id);
    Set<DTO> findAll();
    DTO update(ID id, DTO dto);
    DTO create(DTO dto);
    void deleteById(ID id);
}
