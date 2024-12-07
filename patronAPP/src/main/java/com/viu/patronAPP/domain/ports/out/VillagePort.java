package com.viu.patronAPP.domain.ports.out;

import com.viu.patronAPP.domain.model.Village;

import java.util.List;

public interface VillagePort {
    public List<Village> getVillagesByProvince(String provinceId);
}
