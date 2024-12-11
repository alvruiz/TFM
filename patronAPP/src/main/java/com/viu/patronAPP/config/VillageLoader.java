package com.viu.patronAPP.config;

import com.viu.patronAPP.domain.model.Coords;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.VillageEntity;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.village.VillageMongoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class VillageLoader {

    @Bean
    public CommandLineRunner loadVillages(VillageMongoRepository villageRepository) {
        return args -> {
            villageRepository.deleteAll();
            VillageEntity castronuñoEntity = VillageEntity.builder()
                    .name("Castronuño")
                    .id("1")
                    .coords(Coords.builder()
                            .latitude(41.390485)
                            .longitude(-5.262498)
                            .build())
                    .imageUrl("https://www.cyltv.es/mediao/8AC773D2-CB16-36DA-42443158195DB759.JPG")
                    .provinceId("49")
                    .build();
            VillageEntity villarejoEntity = VillageEntity.builder()
                    .name("Villarejo de Órbigo")
                    .id("2")
                    .coords(Coords.builder()
                            .latitude(42.445197)
                            .longitude(-5.903779)
                            .build())
                    .imageUrl("https://www.diocesisastorga.es/recursos/parroquias/d51460a45f36d2a8b6ba9c189e1a1e9d.jpg")
                    .provinceId("24")
                    .build();

            VillageEntity villaornateEntity = VillageEntity.builder()
                    .name("Villaornate y Castro")
                    .id("3")
                    .coords(Coords.builder()
                            .latitude(42.184812)
                            .longitude(-5.549757)
                            .build())
                    .imageUrl("https://leonsurdigital.com/upload/images/06_2023/9234_8621_img_28474-1.jpg")
                    .provinceId("24")
                    .build();

            VillageEntity villaobispoEntity = VillageEntity.builder()
                    .name("Villaobispo De Otero")
                    .id("4")
                    .coords(Coords.builder()
                            .latitude(42.499988)
                            .longitude(-6.058563)
                            .build())
                    .imageUrl("https://media2.clubrural.com/img990x400/pueblos/leon/villaobispo-de-otero/20160219064630-leon-villaobispo-de-otero.jpg")
                    .provinceId("24")
                    .build();

            VillageEntity palanquinosEntity = VillageEntity.builder()
                    .name("Palanquinos")
                    .id("5")
                    .coords(Coords.builder()
                            .latitude(42.471355)
                            .longitude(-5.480471)
                            .build())
                    .imageUrl("https://leonsurdigital.com/upload/images/04_2024/1263_4-palanquinos.jpg")
                    .provinceId("24")
                    .build();
            VillageEntity atapuercaEntity = VillageEntity.builder()
                    .name("Atapuerca")
                    .id("6")
                    .coords(Coords.builder()
                            .latitude(42.338611)
                            .longitude(-3.647222)
                            .build())
                    .imageUrl("https://example.com/atapuerca.jpg")
                    .provinceId("09")
                    .build();

            VillageEntity albaDeTormesEntity = VillageEntity.builder()
                    .name("Alba de Tormes")
                    .id("7")
                    .coords(Coords.builder()
                            .latitude(40.948056)
                            .longitude(-5.463611)
                            .build())
                    .imageUrl("https://example.com/alba_de_tormes.jpg")
                    .provinceId("37")
                    .build();

            VillageEntity tordesillasEntity = VillageEntity.builder()
                    .name("Tordesillas")
                    .id("8")
                    .coords(Coords.builder()
                            .latitude(41.310278)
                            .longitude(-4.975278)
                            .build())
                    .imageUrl("https://example.com/tordesillas.jpg")
                    .provinceId("49")
                    .build();

            VillageEntity candeledaEntity = VillageEntity.builder()
                    .name("Candeleda")
                    .id("9")
                    .coords(Coords.builder()
                            .latitude(40.208611)
                            .longitude(-5.3075)
                            .build())
                    .imageUrl("https://example.com/candeleda.jpg")
                    .provinceId("05")
                    .build();
            VillageEntity aguasalEntity = VillageEntity.builder()
                    .name("Aguasal")
                    .id("10")
                    .coords(Coords.builder()
                            .latitude(41.3040932127772)
                            .longitude(-4.626361159)
                            .build())
                    .imageUrl("https://www.mapa.gob.es/images/es/dsc_0330_a_tcm30-556517.jpg")
                    .provinceId("49")
                    .build();

            VillageEntity aguilarEntity = VillageEntity.builder()
                    .name("Aguilar-de-Campos")
                    .id("11")
                    .coords(Coords.builder()
                            .latitude(41.9762697026253)
                            .longitude(-5.1966922789)
                            .build())
                    .imageUrl("https://static.wixstatic.com/media/be2270_fbf3be35ba8c4b4d9437a42c3a39187f~mv2.jpg/v1/fill/w_497,h_332,al_c,q_80,enc_auto/be2270_fbf3be35ba8c4b4d9437a42c3a39187f~mv2.jpg")
                    .provinceId("49")
                    .build();
            VillageEntity adaliaEntity = VillageEntity.builder()
                    .name("Adalia")
                    .id("12")
                    .coords(Coords.builder()
                            .latitude(41.6438088287139)
                            .longitude(-5.1263019774)
                            .build())
                    .imageUrl("https://s3.ppllstatics.com/elnortedecastilla/www/multimedia/202005/25/media/cortadas/Imagen%20Iglesia%20del%20Salvador%20(2)-k1qG--624x385@El%20Norte.jpg")
                    .provinceId("49")
                    .build();
            VillageEntity alaejosEntity = VillageEntity.builder()
                    .name("Alaejos")
                    .id("15")
                    .coords(Coords.builder()
                            .latitude(41.3073713272509)
                            .longitude(-5.2174956654)
                            .build())
                    .imageUrl("https://www.turismocastillayleon.com/es/patrimonio-cultura/alaejos.ficheros/267611-hq_Alaejos01_Valladolid.jpg/h,267611-hq_Alaejos01_Valladolid.jpg")
                    .provinceId("49")
                    .build();
            VillageEntity arroyoEntity = VillageEntity.builder()
                    .name("Arroyo de la Encomienda")
                    .id("21")
                    .coords(Coords.builder()
                            .latitude(41.6247643815669)
                            .longitude(-4.7922456571)
                            .build())
                    .imageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Arroyo_de_la_Encomienda%2C_La_Flecha%2C_Plaza_de_Espa%C3%B1a%2C_Ayuntamiento_1.jpg/320px-Arroyo_de_la_Encomienda%2C_La_Flecha%2C_Plaza_de_Espa%C3%B1a%2C_Ayuntamiento_1.jpg")
                    .provinceId("49")
                    .build();

            VillageEntity ataquinesEntity = VillageEntity.builder()
                    .name("Ataquines")
                    .id("22")
                    .coords(Coords.builder()
                            .latitude(41.1852529705255)
                            .longitude(-4.7756640226)
                            .build())
                    .imageUrl("https://ucmedia.er2.co/es/des/f/0/1600/28620.jpg")
                    .provinceId("49")
                    .build();

            VillageEntity bahabonEntity = VillageEntity.builder()
                    .name("Bahabon")
                    .id("23")
                    .coords(Coords.builder()
                            .latitude(41.6529473)
                            .longitude(-4.7283877)
                            .build())
                    .imageUrl("https://upload.wikimedia.org/wikipedia/commons/9/91/Iglesia_de_Bahab%C3%B3n.jpg")
                    .provinceId("49")
                    .build();
            villageRepository.saveAll(Arrays.asList(castronuñoEntity, villarejoEntity, villaornateEntity, villaobispoEntity, palanquinosEntity,
                    atapuercaEntity, alaejosEntity, adaliaEntity, aguasalEntity, aguilarEntity, albaDeTormesEntity, tordesillasEntity, candeledaEntity));

            List<VillageEntity> villages = Arrays.asList(
                    VillageEntity.builder().name("Adalia").id("12").coords(Coords.builder().latitude(41.6438088287139).longitude(-5.1263019774).build()).imageUrl("https://media2.clubrural.com/img500x180/pueblos/valladolid/gallegos-de-hornija/20160219143040-valladolid-gallegos-de-hornija.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Aguasal").id("13").coords(Coords.builder().latitude(41.3040932127772).longitude(-4.626361159).build()).imageUrl("https://vivetupueblo.es/wp-content/uploads/2020/08/Aguasal.-Valladolid.-Castilla-y-Leon.-Calle.-7.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Aguilar de Campos").id("14").coords(Coords.builder().latitude(41.9762697026253).longitude(-5.1966922789).build()).imageUrl("https://1.bp.blogspot.com/-piFXc2N4nAU/VK8RVy-HvzI/AAAAAAAAAMA/_mkFqvuxn7o/s1600/Aguilar%2Bde%2BCampos_Aytº.%2B[1280x768].JPG").provinceId("49").build(),
                    VillageEntity.builder().name("Alaejos").id("15").coords(Coords.builder().latitude(41.3073713272509).longitude(-5.2174956654).build()).imageUrl("https://destinocastillayleon.es/index/wp-content/uploads/2013/04/b0afe6f8a97facaf7d848f4860384c5a.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Alcazaren").id("16").coords(Coords.builder().latitude(41.6529473).longitude(-4.7283877).build()).imageUrl("https://live.staticflickr.com/4013/4646811161_75b7997a6a_z.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Aldea de San Miguel").id("17").coords(Coords.builder().latitude(41.4457841943164).longitude(-4.6115167303).build()).imageUrl("https://4.bp.blogspot.com/-j-owuwwzG9Y/VLB5TDeQwHI/AAAAAAAAAQM/lm2Mkpqlei8/s1600/Aldea%2Bde%2BSan%2BMiguel%2B-%2BIglesia%2BParroquial%2Bde%2BSan%2BMiguel%2BArcangel_%2BS.XII-XV-XVIII%2B(11)%2B[1280x768].JPG").provinceId("49").build(),
                    VillageEntity.builder().name("Aldeamayor de San Martin").id("18").coords(Coords.builder().latitude(41.6529473).longitude(-4.7283877).build()).imageUrl("https://4.bp.blogspot.com/-Ul97yN_vsT0/VLE-c7F8bfI/AAAAAAAAATI/fbPq_0b1PTQ/s1600/Aldeamayor%2Bde%2BSan%2BMartín_Ayuntamiento%2By%2BPlaza%2BMayor%2B[1280x768].JPG").provinceId("49").build(),
                    VillageEntity.builder().name("Almenara de Adaja").id("19").coords(Coords.builder().latitude(41.2071466103346).longitude(-4.6799801076).build()).imageUrl("https://live.staticflickr.com/3468/3404550090_fb1563345a_b.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Amusquillo").id("20").coords(Coords.builder().latitude(41.7506158813509).longitude(-4.2901800697).build()).imageUrl("https://ucmedia.er2.co/es/corner-main/39/288/7874.jpg?1391495413").provinceId("49").build(),
                    VillageEntity.builder().name("Arroyo de la Encomienda").id("21").coords(Coords.builder().latitude(41.6247643815669).longitude(-4.7922456571).build()).imageUrl("https://3.bp.blogspot.com/-v7BcR2ThmD4/U5ZIyImg-ZI/AAAAAAAAv84/Pb4eM0NRZd8/s1600/IMG_6772.JPG").provinceId("49").build(),
                    VillageEntity.builder().name("Ataquines").id("22").coords(Coords.builder().latitude(41.1852529705255).longitude(-4.7756640226).build()).imageUrl("https://ucmedia.er2.co/es/des/f/0/1600/28620.jpg#google").provinceId("49").build(),
                    VillageEntity.builder().name("Bahabon").id("23").coords(Coords.builder().latitude(41.6529473).longitude(-4.7283877).build()).imageUrl("https://cdn.horariodemisas.com/wp-content/uploads/2020/10/parroquia-de-nuestra-senora-de-la-asuncion-bahabon-de-valcorba-2048x1371.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Barcial de la Loma").id("24").coords(Coords.builder().latitude(41.95412592339).longitude(-5.279930550125).build()).imageUrl("https://photo980x880.mnstatic.com/faa752a66f29e0bf8a3b5f4ab74b5a06/la-iglesia-1720461.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Barruelo del Valle").id("25").coords(Coords.builder().latitude(41.6751050124769).longitude(-5.0845655979).build()).imageUrl("https://cdn.pixabay.com/photo/2019/07/15/06/50/barruelo-del-valle-4338672_1280.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Becilla de Valderaduey").id("26").coords(Coords.builder().latitude(42.0976102087316).longitude(-5.2045977027).build()).imageUrl("https://ucmedia.er2.co/es/des-main/143/960/28624.jpg?1629635453").provinceId("49").build(),
                    VillageEntity.builder().name("Benafarces").id("27").coords(Coords.builder().latitude(41.6212175756859).longitude(-5.2789506929).build()).imageUrl("https://s3.ppllstatics.com/elnortedecastilla/www/multimedia/202206/14/media/cortadas/benafarces1-k44H-U1704055994433PI-1968x1216@El Norte.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Bercero").id("28").coords(Coords.builder().latitude(41.5584761250053).longitude(-5.072501904).build()).imageUrl("http://3.bp.blogspot.com/-lZMlhKuGLSw/VMg3Oa4K3BI/AAAAAAAAAhM/ZblRGeWt_Yk/s1600/Bercero_Ermita%2Bdel%2BCristo%2Bdel%2BHumilladero%2B[1280x768]_fhdr.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Berceruelo").id("29").coords(Coords.builder().latitude(41.5907018011593).longitude(-5.0169526654).build()).imageUrl("http://www.absolutvalladolid.com/wp-content/uploads/2010/06/Berceruelo.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Berrueces").id("30").coords(Coords.builder().latitude(41.9505147032558).longitude(-5.0965679158).build()).imageUrl("https://lh3.googleusercontent.com/p/AF1QipN-COY2AbxhS_yeCEfiCTIB0PnkVApQfXyasTAf=s1600-h768").provinceId("49").build(),
                    VillageEntity.builder().name("Bobadilla del Campo").id("31").coords(Coords.builder().latitude(41.1813472317549).longitude(-5.0354943446).build()).imageUrl("https://ucmedia.er2.co/es/des/a/f/1600/28629.jpg#google").provinceId("49").build(),
                    VillageEntity.builder().name("Bocigas").id("32").coords(Coords.builder().latitude(41.24336607488).longitude(-4.686725825204).build()).imageUrl("https://www.absolutvalladolid.com/wp-content/uploads/2011/03/Bocigas.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Bocos de Duero").id("33").coords(Coords.builder().latitude(41.6211609753566).longitude(-4.0587457057).build()).imageUrl("http://topriberadelduero.com/sites/default/files/alojamiento-bocos-duero-valladolid.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Boecillo").id("34").coords(Coords.builder().latitude(41.5389094260949).longitude(-4.7266046314).build()).imageUrl("http://www.fotopaises.com/Fotos-Paises/t/2007/9/21/499_1190379722.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Bolanos de Campos").id("35").coords(Coords.builder().latitude(42.013783403256).longitude(-5.27453245652).build()).imageUrl("https://imagenes.diariodevalladolid.es/files/vertical_image_414/uploads/2024/04/17/661ff66569399.jpeg").provinceId("49").build(),
                    VillageEntity.builder().name("Brahojos de Medina").id("36").coords(Coords.builder().latitude(41.221979349715).longitude(-5.04444258417).build()).imageUrl("https://media2.clubrural.com/img990x400/pueblos/valladolid/brahojos-de-medina/20160218181920-valladolid-brahojos-de-medina.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Bustillo de Chaves").id("37").coords(Coords.builder().latitude(42.1248958189153).longitude(-5.108424048).build()).imageUrl("https://ucmedia.er2.co/es/des/e/6/1600/28634.jpg#google").provinceId("49").build(),
                    VillageEntity.builder().name("Cabezon de Pisuerga").id("38").coords(Coords.builder().latitude(41.6529473).longitude(-4.7283877).build()).imageUrl("https://fotografias.antena3.com/clipping/cmsimages02/2022/04/27/7E38C62D-C715-4173-9638-16325B880CA1/puente-cabezon-pisuerga_98.jpg?crop=3648,2052,x0,y344&amp;width=1900&amp;height=1069&amp;optimize=low&amp;format=webply").provinceId("49").build(),
                    VillageEntity.builder().name("Cabezon de Valderaduey").id("39").coords(Coords.builder().latitude(41.6529473).longitude(-4.7283877).build()).imageUrl("https://s1.ppllstatics.com/elnortedecastilla/www/multimedia/2023/04/24/cabezon2-kj9C-U200136341584wvE-1200x840@ElNorte.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Cabreros del Monte").id("40").coords(Coords.builder().latitude(41.844715221929).longitude(-5.28440147511).build()).imageUrl("http://3.bp.blogspot.com/-wLTpSSwecqo/VNqp_-GgfrI/AAAAAAAAAtg/-pdMwW1kxC8/s1600/Cabreros%2Bdel%2BMonte_Iglesia%2BParroquial%2Bde%2BSan%2BJuan%2BBautista%2B[1024x768].jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Campaspero").id("41").coords(Coords.builder().latitude(41.5011044287228).longitude(-4.2251893317).build()).imageUrl("https://fotos.hoteles.net/articulos/campaspero-turismo-valladolid-4413-1.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Campillo El").id("42").coords(Coords.builder().latitude(37.6922172748497).longitude(-6.6417620247).build()).imageUrl("https://media2.clubrural.com/img990x400/pueblos/valladolid/el-campillo/20160218200535-valladolid-el-campillo.jpg").provinceId("49").build()


            );

            villageRepository.saveAll(villages);
            System.out.println("Villages loaded successfully");
        };
    }
}
