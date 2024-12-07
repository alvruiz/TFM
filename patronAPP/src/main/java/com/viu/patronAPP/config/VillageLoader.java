package com.viu.patronAPP.config;

import com.viu.patronAPP.domain.model.Coords;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.VillageEntity;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.village.VillageMongoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class VillageLoader {

    @Bean
    public CommandLineRunner loadVillages(VillageMongoRepository villageRepository) {
        return args -> {
            villageRepository.deleteAll();
            VillageEntity castronuñoEntity = VillageEntity.builder()
                    .name("Castronuño")
                    .coords(Coords.builder()
                            .latitude(41.390485)
                            .longitude(-5.262498)
                            .build())
                    .imageUrl("https://www.cyltv.es/mediao/8AC773D2-CB16-36DA-42443158195DB759.JPG")
                    .provinceId("49")
                    .build();
            VillageEntity villarejoEntity = VillageEntity.builder()
                    .name("Villarejo de Órbigo")
                    .coords(Coords.builder()
                            .latitude(42.445197)
                            .longitude(-5.903779)
                            .build())
                    .imageUrl("https://www.diocesisastorga.es/recursos/parroquias/d51460a45f36d2a8b6ba9c189e1a1e9d.jpg")
                    .provinceId("24")
                    .build();

            VillageEntity villaornateEntity = VillageEntity.builder()
                    .name("Villaornate y Castro")
                    .coords(Coords.builder()
                            .latitude(42.184812)
                            .longitude(-5.549757)
                            .build())
                    .imageUrl("https://leonsurdigital.com/upload/images/06_2023/9234_8621_img_28474-1.jpg")
                    .provinceId("24")
                    .build();

            VillageEntity villaobispoEntity = VillageEntity.builder()
                    .name("Villaobispo De Otero")
                    .coords(Coords.builder()
                            .latitude(42.499988)
                            .longitude(-6.058563)
                            .build())
                    .imageUrl("https://media2.clubrural.com/img990x400/pueblos/leon/villaobispo-de-otero/20160219064630-leon-villaobispo-de-otero.jpg")
                    .provinceId("24")
                    .build();

            VillageEntity palanquinosEntity = VillageEntity.builder()
                    .name("Palanquinos")
                    .coords(Coords.builder()
                            .latitude(42.471355)
                            .longitude(-5.480471)
                            .build())
                    .imageUrl("https://leonsurdigital.com/upload/images/04_2024/1263_4-palanquinos.jpg")
                    .provinceId("24")
                    .build();
            VillageEntity atapuercaEntity = VillageEntity.builder()
                    .name("Atapuerca")
                    .coords(Coords.builder()
                            .latitude(42.338611)
                            .longitude(-3.647222)
                            .build())
                    .imageUrl("https://example.com/atapuerca.jpg")
                    .provinceId("09")
                    .build();

            VillageEntity albaDeTormesEntity = VillageEntity.builder()
                    .name("Alba de Tormes")
                    .coords(Coords.builder()
                            .latitude(40.948056)
                            .longitude(-5.463611)
                            .build())
                    .imageUrl("https://example.com/alba_de_tormes.jpg")
                    .provinceId("37")
                    .build();

            VillageEntity tordesillasEntity = VillageEntity.builder()
                    .name("Tordesillas")
                    .coords(Coords.builder()
                            .latitude(41.310278)
                            .longitude(-4.975278)
                            .build())
                    .imageUrl("https://example.com/tordesillas.jpg")
                    .provinceId("49")
                    .build();

            VillageEntity candeledaEntity = VillageEntity.builder()
                    .name("Candeleda")
                    .coords(Coords.builder()
                            .latitude(40.208611)
                            .longitude(-5.3075)
                            .build())
                    .imageUrl("https://example.com/candeleda.jpg")
                    .provinceId("05")
                    .build();
            villageRepository.saveAll(Arrays.asList(castronuñoEntity, villarejoEntity, villaornateEntity, villaobispoEntity, palanquinosEntity,
                    atapuercaEntity, albaDeTormesEntity, tordesillasEntity, candeledaEntity));
            System.out.println("Villages loaded successfully");
        };
    }
}
