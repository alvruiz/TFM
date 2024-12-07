package com.viu.patronAPP.domain.ports.in;

import com.viu.patronAPP.domain.model.Province;
import com.viu.patronAPP.domain.model.Village;

import java.util.List;

public interface ProvinceUseCasesPort {

    List<Province> getProvinces();

    Province getProvinceById(String provinceId);

    List<Village> getVillagesByProvince(String provinceId);
}
