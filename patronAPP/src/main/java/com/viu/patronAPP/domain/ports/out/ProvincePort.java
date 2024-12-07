package com.viu.patronAPP.domain.ports.out;

import com.viu.patronAPP.domain.model.Province;

import java.util.List;

public interface ProvincePort {
    public List<Province> getProvinces();

    public Province getProvinceById(String provinceId);

}
