package com.viu.patronAPP.repository;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.FestivityEntity;
import com.viu.patronAPP.infrastructure.out.persistence.repository.FestivityRepositoryAdapter;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.festivity.FestivityMongoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FestivityRepositoryAdapterTest {
    @Mock
    private FestivityMongoRepository festivityRepository;
    @InjectMocks
    private FestivityRepositoryAdapter festivityRepositoryAdapter;
    Festivity festivity = Festivity.builder().id("1").name("test").startDate(null).endDate(null).patron("test").events(new ArrayList<>()).build();
    FestivityEntity festivityEntity = FestivityEntity.builder().id("1").name("test").startDate(null).endDate(null).patron("test").events(new ArrayList<>()).build();

    @Test
    public void testCreateFestivity() {
        when(festivityRepository.save(festivityEntity)).thenReturn(festivityEntity);
        festivityRepositoryAdapter.createFestivity(festivity);
        verify(festivityRepository).save(festivityEntity);
    }

    @Test
    public void testGetFestivityByEvent() {
        when(festivityRepository.findByEvents_Id("1")).thenReturn(Optional.ofNullable(festivityEntity));
        festivityRepositoryAdapter.getFestivityByEvent("1");
        verify(festivityRepository).findByEvents_Id("1");
    }

    @Test
    public void testGetAllFestivities() {
        when(festivityRepository.findAll(PageRequest.of(0, 1))).thenReturn(Page.empty());
        festivityRepositoryAdapter.getAllFestivities("0", "1");
        verify(festivityRepository).findAll(PageRequest.of(0, 1));
    }

    @Test
    public void testGetById() {
        when(festivityRepository.findById("1")).thenReturn(Optional.ofNullable(festivityEntity));
        festivityRepositoryAdapter.getById("1");
        verify(festivityRepository).findById("1");
    }

    @Test
    public void testGetByIdEntityNull() {
        when(festivityRepository.findById("1")).thenReturn(Optional.ofNullable(null));
        festivityRepositoryAdapter.getById("1");
        verify(festivityRepository).findById("1");
    }

    @Test
    public void testUpdateFestivity() {
        festivityRepositoryAdapter.updateFestivity(festivity);
        verify(festivityRepository).save(festivityEntity);
    }
}
