package com.viu.patronAPP.unit.mapper;

import com.viu.patronAPP.domain.model.*;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.*;
import com.viu.patronAPP.infrastructure.out.persistence.mapper.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MapperTests {

    @Test
    public void testEventMapper() {
        Event event = Event.builder().id("1").name("test").description("test").startDate(null).endDate(null).coords(List.of()).maxCapacity(0).attendees(new ArrayList<>()).build();
        EventEntity eventEntity = EventMapper.mapEventDomainToEntity(event);
        Event eventResult = EventMapper.mapEventEntityToDomain(eventEntity);
        assert event.equals(eventResult);
        EventEntity eventEntityResult = EventMapper.mapEventDomainToEntity(eventResult);
        assert eventEntity.equals(eventEntityResult);
    }

    @Test
    public void testFestivityMapper() {
        Festivity festivity = Festivity.builder().id("1").name("test").startDate(null).endDate(null).patron("test").events(new ArrayList<>()).build();

        FestivityEntity festivityEntity = FestivityMapper.mapFestivityDomainToEntity(festivity);
        Festivity festivityResult = FestivityMapper.mapFestivityEntityToDomain(festivityEntity);
        assert festivity.equals(festivityResult);
    }

    @Test
    public void testProvinceMapper() {
        Province province = Province.builder().id("1").name("test").coords(null).image("test").autonomousCommunity("test").build();
        ProvinceEntity provinceEntity = ProvinceMapper.mapProvinceDomainToEntity(province);
        Province provinceResult = ProvinceMapper.mapProvinceEntityToDomain(provinceEntity);
        assert province.equals(provinceResult);
    }

    @Test
    public void testUserMapper() {
        User user = User.builder().id("1").name("test").surname("test").email("test").age(0).gender("test").rol(null).imageUrl("test").eventsParticipating(new ArrayList<>()).build();
        UserEntity userEntity = UserMapper.mapUserDomainToEntity(user);
        User userResult = UserMapper.mapUserEntityToDomain(userEntity);
        assert user.equals(userResult);
    }

    @Test
    public void testVillageMapper() {
        Village village = Village.builder().id("1").name("test").coords(null).imageUrl("test").province(Province.builder().build()).festivity(Festivity.builder().events(new ArrayList<>()).build()).build();
        VillageEntity villageEntity = VillageMapper.mapVillageDomainToEntity(village);
        Village villageResult = VillageMapper.mapVillageEntityToDomain(villageEntity);
        assert village.equals(villageResult);
    }
}
