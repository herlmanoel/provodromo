package com.provodromo.provodromo.service.base;

import java.util.Set;

public interface BaseServiceNew<RequestDTO, ResponseDTO, ID> {

    ResponseDTO findById(ID id);
    Set<ResponseDTO> findAll();
    ResponseDTO update(ID id, RequestDTO dto);
    ResponseDTO create(RequestDTO dto);
    void deleteById(ID id);
}
