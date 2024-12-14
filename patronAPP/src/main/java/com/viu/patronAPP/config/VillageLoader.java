package com.viu.patronAPP.config;

import com.viu.patronAPP.domain.model.Coords;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.FestivityEntity;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.VillageEntity;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.festivity.FestivityMongoRepository;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.village.VillageMongoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class VillageLoader {

    @Bean
    public CommandLineRunner loadVillages(VillageMongoRepository villageRepository, FestivityMongoRepository festivityRepository) {
        return args -> {
            villageRepository.deleteAll();
            festivityRepository.deleteAll();


            List<VillageEntity> villages = Arrays.asList(
                    VillageEntity.builder().name("Castronuño").id("1").coords(Coords.builder().latitude(41.390485).longitude(-5.262498).build()).imageUrl("https://www.cyltv.es/mediao/8AC773D2-CB16-36DA-42443158195DB759.JPG").provinceId("49").build(),
                    VillageEntity.builder().name("Villarejo de Órbigo").id("2").coords(Coords.builder().latitude(42.445197).longitude(-5.903779).build()).imageUrl("https://www.diocesisastorga.es/recursos/parroquias/d51460a45f36d2a8b6ba9c189e1a1e9d.jpg").provinceId("24").build(),
                    VillageEntity.builder().name("Villaornate y Castro").id("3").coords(Coords.builder().latitude(42.184812).longitude(-5.549757).build()).imageUrl("https://leonsurdigital.com/upload/images/06_2023/9234_8621_img_28474-1.jpg").provinceId("24").build(),
                    VillageEntity.builder().name("Villaobispo De Otero").id("4").coords(Coords.builder().latitude(42.499988).longitude(-6.058563).build()).imageUrl("https://media2.clubrural.com/img990x400/pueblos/leon/villaobispo-de-otero/20160219064630-leon-villaobispo-de-otero.jpg").provinceId("24").build(),
                    VillageEntity.builder().name("Palanquinos").id("5").coords(Coords.builder().latitude(42.471355).longitude(-5.480471).build()).imageUrl("https://leonsurdigital.com/upload/images/04_2024/1263_4-palanquinos.jpg").provinceId("24").build(),
                    VillageEntity.builder().name("Atapuerca").id("6").coords(Coords.builder().latitude(42.338611).longitude(-3.647222).build()).imageUrl("https://example.com/atapuerca.jpg").provinceId("09").build(),
                    VillageEntity.builder().name("Alba de Tormes").id("7").coords(Coords.builder().latitude(40.948056).longitude(-5.463611).build()).imageUrl("https://example.com/alba_de_tormes.jpg").provinceId("37").build(),
                    VillageEntity.builder().name("Tordesillas").id("8").coords(Coords.builder().latitude(41.310278).longitude(-4.975278).build()).imageUrl("https://abrasador.com/wp-content/uploads/2024/01/5-Cosas-que-ver-en-Tordesillas.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Candeleda").id("9").coords(Coords.builder().latitude(40.208611).longitude(-5.3075).build()).imageUrl("https://media-cdn.tripadvisor.com/media/photo-c/1280x250/0d/93/d0/2e/piscinas-naturales-candeleda.jpg").provinceId("05").build(),
                    VillageEntity.builder().name("Aguasal").id("10").coords(Coords.builder().latitude(41.3040932127772).longitude(-4.626361159).build()).imageUrl("https://www.mapa.gob.es/images/es/dsc_0330_a_tcm30-556517.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Aguilar de Campos").id("11").coords(Coords.builder().latitude(41.9762697026253).longitude(-5.1966922789).build()).imageUrl("https://static.wixstatic.com/media/be2270_fbf3be35ba8c4b4d9437a42c3a39187f~mv2.jpg/v1/fill/w_497,h_332,al_c,q_80,enc_auto/be2270_fbf3be35ba8c4b4d9437a42c3a39187f~mv2.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Adalia").id("12").coords(Coords.builder().latitude(41.6438088287139).longitude(-5.1263019774).build()).imageUrl("https://s3.ppllstatics.com/elnortedecastilla/www/multimedia/202005/25/media/cortadas/Imagen%20Iglesia%20del%20Salvador%20(2)-k1qG--624x385@El%20Norte.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Alaejos").id("15").coords(Coords.builder().latitude(41.3073713272509).longitude(-5.2174956654).build()).imageUrl("https://www.turismocastillayleon.com/es/patrimonio-cultura/alaejos.ficheros/267611-hq_Alaejos01_Valladolid.jpg/h,267611-hq_Alaejos01_Valladolid.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Arroyo de la Encomienda").id("21").coords(Coords.builder().latitude(41.6247643815669).longitude(-4.7922456571).build()).imageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Arroyo_de_la_Encomienda%2C_La_Flecha%2C_Plaza_de_Espa%C3%B1a%2C_Ayuntamiento_1.jpg/320px-Arroyo_de_la_Encomienda%2C_La_Flecha%2C_Plaza_de_Espa%C3%B1a%2C_Ayuntamiento_1.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Ataquines").id("22").coords(Coords.builder().latitude(41.1852529705255).longitude(-4.7756640226).build()).imageUrl("https://ucmedia.er2.co/es/des/f/0/1600/28620.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Bahabon").id("23").coords(Coords.builder().latitude(41.6529473).longitude(-4.7283877).build()).imageUrl("https://upload.wikimedia.org/wikipedia/commons/9/91/Iglesia_de_Bahab%C3%B3n.jpg").provinceId("49").build(),
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
                    VillageEntity.builder().name("Campillo El").id("42").coords(Coords.builder().latitude(37.6922172748497).longitude(-6.6417620247).build()).imageUrl("https://media2.clubrural.com/img990x400/pueblos/valladolid/el-campillo/20160218200535-valladolid-el-campillo.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Esguevillas de Esgueva").id("43").coords(Coords.builder().latitude(41.7533698198745).longitude(-4.3751444797).build()).imageUrl("https://www.biodiversidadvirtual.org/etno/data/media/29/Iglesia-de-Esguevillas-de-Esgueva-(Valladolid)-72523.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Fombellida").id("44").coords(Coords.builder().latitude(41.749569778988).longitude(-4.18805011851).build()).imageUrl("https://www.hernandezrabal.com/espana/castillaleon/valladolid/vdfonigl.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Fompedraza").id("45").coords(Coords.builder().latitude(41.5185685944398).longitude(-4.1446783669).build()).imageUrl("https://vasdepuebloenpueblo.files.wordpress.com/2015/06/iglesia-de-san-bartolome-iii.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Fontihoyuelo").id("46").coords(Coords.builder().latitude(42.168710754076).longitude(-5.05438760987).build()).imageUrl("https://s2.ppllstatics.com/elnortedecastilla/www/multimedia/202207/03/media/cortadas/fontiyoyuelo2-kM8H-U170555963782K8F-1248x770@El Norte.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Fresno el Viejo").id("47").coords(Coords.builder().latitude(41.180412206643).longitude(-5.16445348322).build()).imageUrl("https://www.cyltv.es/media/imagenesf/197039c0-eeec-3db5-eb60aed2c5842e23.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Fuensaldana").id("48").coords(Coords.builder().latitude(41.7130890195157).longitude(-4.7458438517).build()).imageUrl("https://s1.ppllstatics.com/elnortedecastilla/www/multimedia/202207/05/media/cortadas/fuensaldana1-kb3D-U170556138921EFE-1248x770@El Norte.jpg").provinceId("49").build(),
                    VillageEntity.builder().name("Fuente el Sol").id("49").coords(Coords.builder().latitude(41.1721514067333).longitude(-4.9327304661).build()).imageUrl("https://ucmedia.er2.co/es/des/3/5/1600/28669.jpg#google").provinceId("49").build(),
                    VillageEntity.builder().name("Fuente Olmedo").id("50").coords(Coords.builder().latitude(41.2497748134535).longitude(-4.6409573222).build()).imageUrl("https://2.bp.blogspot.com/-chQbVrKr6T8/VUVulRWVGlI/AAAAAAAABdk/wBiEhaf5me0/s1600/Fuente%2BOlmedo%2B_%2BIglesia%2BParroquial%2Bde%2BSan%2BJuan%2BEvangelista_S.XVII-XVIII%2B(5)%2B(Copiar).JPG").provinceId("49").build()

            );

            villageRepository.saveAll(villages);
            System.out.println("Villages loaded successfully");

            List<FestivityEntity> festivities = Arrays.asList(
                    FestivityEntity.builder().name("Fiesta Patronal San Pedro").id("1").villageId("1").patron("San Pedro").startDate(LocalDate.of(2025, 4, 1)).endDate(LocalDate.of(2025, 4, 2)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Santa María").id("2").villageId("2").patron("Santa María").startDate(LocalDate.of(2025, 6, 15)).endDate(LocalDate.of(2025, 6, 17)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Juan").id("3").villageId("3").patron("San Juan").startDate(LocalDate.of(2025, 8, 23)).endDate(LocalDate.of(2025, 8, 24)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Miguel").id("4").villageId("4").patron("San Miguel").startDate(LocalDate.of(2025, 9, 29)).endDate(LocalDate.of(2025, 9, 30)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Antonio").id("5").villageId("5").patron("San Antonio").startDate(LocalDate.of(2025, 10, 13)).endDate(LocalDate.of(2025, 10, 14)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Virgen del Carmen").id("6").villageId("6").patron("Virgen del Carmen").startDate(LocalDate.of(2025, 7, 16)).endDate(LocalDate.of(2025, 7, 17)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San José").id("7").villageId("7").patron("San José").startDate(LocalDate.of(2025, 3, 19)).endDate(LocalDate.of(2025, 3, 20)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Francisco").id("8").villageId("8").patron("San Francisco").startDate(LocalDate.of(2025, 10, 4)).endDate(LocalDate.of(2025, 10, 5)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Santa Teresa").id("9").villageId("9").patron("Santa Teresa").startDate(LocalDate.of(2025, 10, 15)).endDate(LocalDate.of(2025, 10, 16)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Nuestra Señora de la Asunción").id("10").villageId("10").patron("Nuestra Señora de la Asunción").startDate(LocalDate.of(2025, 8, 15)).endDate(LocalDate.of(2025, 8, 16)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Isidro").id("11").villageId("11").patron("San Isidro").startDate(LocalDate.of(2025, 5, 15)).endDate(LocalDate.of(2025, 5, 16)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Santiago Apóstol").id("12").villageId("12").patron("Santiago Apóstol").startDate(LocalDate.of(2025, 7, 25)).endDate(LocalDate.of(2025, 7, 26)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Bartolomé").id("13").villageId("13").patron("San Bartolomé").startDate(LocalDate.of(2025, 8, 24)).endDate(LocalDate.of(2025, 8, 25)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Roque").id("14").villageId("14").patron("San Roque").startDate(LocalDate.of(2025, 8, 16)).endDate(LocalDate.of(2025, 8, 17)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Santa Ana").id("15").villageId("15").patron("Santa Ana").startDate(LocalDate.of(2025, 7, 26)).endDate(LocalDate.of(2025, 7, 27)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Nicolás").id("16").villageId("16").patron("San Nicolás").startDate(LocalDate.of(2025, 12, 6)).endDate(LocalDate.of(2025, 12, 7)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Nuestra Señora del Rosario").id("17").villageId("17").patron("Nuestra Señora del Rosario").startDate(LocalDate.of(2025, 10, 7)).endDate(LocalDate.of(2025, 10, 8)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Fermín").id("18").villageId("18").patron("San Fermín").startDate(LocalDate.of(2025, 7, 7)).endDate(LocalDate.of(2025, 7, 8)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Vicente").id("19").villageId("19").patron("San Vicente").startDate(LocalDate.of(2025, 1, 22)).endDate(LocalDate.of(2025, 1, 23)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Andrés").id("20").villageId("20").patron("San Andrés").startDate(LocalDate.of(2025, 11, 30)).endDate(LocalDate.of(2025, 12, 1)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Santa Bárbara").id("21").villageId("21").patron("Santa Bárbara").startDate(LocalDate.of(2025, 12, 4)).endDate(LocalDate.of(2025, 12, 5)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Valentín").id("22").villageId("22").patron("San Valentín").startDate(LocalDate.of(2025, 2, 14)).endDate(LocalDate.of(2025, 2, 15)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Blas").id("23").villageId("23").patron("San Blas").startDate(LocalDate.of(2025, 2, 3)).endDate(LocalDate.of(2025, 2, 4)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Jorge").id("24").villageId("24").patron("San Jorge").startDate(LocalDate.of(2025, 4, 23)).endDate(LocalDate.of(2025, 4, 24)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Cristóbal").id("25").villageId("25").patron("San Cristóbal").startDate(LocalDate.of(2025, 7, 10)).endDate(LocalDate.of(2025, 7, 11)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Sebastián").id("26").villageId("26").patron("San Sebastián").startDate(LocalDate.of(2025, 1, 20)).endDate(LocalDate.of(2025, 1, 21)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Virgen de Guadalupe").id("27").villageId("27").patron("Virgen de Guadalupe").startDate(LocalDate.of(2025, 12, 12)).endDate(LocalDate.of(2025, 12, 13)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Virgen de Fátima").id("28").villageId("28").patron("Virgen de Fátima").startDate(LocalDate.of(2025, 5, 13)).endDate(LocalDate.of(2025, 5, 14)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Santa Clara").id("29").villageId("29").patron("Santa Clara").startDate(LocalDate.of(2025, 8, 11)).endDate(LocalDate.of(2025, 8, 12)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Ramón").id("30").villageId("30").patron("San Ramón").startDate(LocalDate.of(2025, 8, 31)).endDate(LocalDate.of(2025, 9, 1)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Agustín").id("31").villageId("31").patron("San Agustín").startDate(LocalDate.of(2025, 8, 28)).endDate(LocalDate.of(2025, 8, 29)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Gregorio").id("32").villageId("32").patron("San Gregorio").startDate(LocalDate.of(2025, 5, 9)).endDate(LocalDate.of(2025, 5, 10)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Mateo").id("33").villageId("33").patron("San Mateo").startDate(LocalDate.of(2025, 9, 21)).endDate(LocalDate.of(2025, 9, 22)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Santa Lucía").id("34").villageId("34").patron("Santa Lucía").startDate(LocalDate.of(2025, 12, 13)).endDate(LocalDate.of(2025, 12, 14)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Pablo").id("35").villageId("35").patron("San Pablo").startDate(LocalDate.of(2025, 6, 29)).endDate(LocalDate.of(2025, 6, 30)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Rafael").id("36").villageId("36").patron("San Rafael").startDate(LocalDate.of(2025, 10, 24)).endDate(LocalDate.of(2025, 10, 25)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Joaquín").id("37").villageId("37").patron("San Joaquín").startDate(LocalDate.of(2025, 7, 26)).endDate(LocalDate.of(2025, 7, 27)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Santa Catalina").id("38").villageId("38").patron("Santa Catalina").startDate(LocalDate.of(2025, 11, 25)).endDate(LocalDate.of(2025, 11, 26)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Santa Cruz").id("39").villageId("39").patron("Santa Cruz").startDate(LocalDate.of(2025, 5, 3)).endDate(LocalDate.of(2025, 5, 4)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Marcos").id("40").villageId("40").patron("San Marcos").startDate(LocalDate.of(2025, 4, 25)).endDate(LocalDate.of(2025, 4, 26)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Esteban").id("41").villageId("41").patron("San Esteban").startDate(LocalDate.of(2025, 12, 26)).endDate(LocalDate.of(2025, 12, 27)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Virgen de los Dolores").id("42").villageId("42").patron("Virgen de los Dolores").startDate(LocalDate.of(2025, 9, 15)).endDate(LocalDate.of(2025, 9, 16)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Lorenzo").id("43").villageId("43").patron("San Lorenzo").startDate(LocalDate.of(2025, 8, 10)).endDate(LocalDate.of(2025, 8, 11)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Felipe").id("44").villageId("44").patron("San Felipe").startDate(LocalDate.of(2025, 5, 3)).endDate(LocalDate.of(2025, 5, 4)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Judas Tadeo").id("45").villageId("45").patron("San Judas Tadeo").startDate(LocalDate.of(2025, 10, 28)).endDate(LocalDate.of(2025, 10, 29)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Marcos").id("46").villageId("46").patron("San Marcos").startDate(LocalDate.of(2025, 4, 25)).endDate(LocalDate.of(2025, 4, 26)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Juan de la Cruz").id("47").villageId("47").patron("San Juan de la Cruz").startDate(LocalDate.of(2025, 12, 14)).endDate(LocalDate.of(2025, 12, 15)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Martín").id("48").villageId("48").patron("San Martín").startDate(LocalDate.of(2025, 11, 11)).endDate(LocalDate.of(2025, 11, 12)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal San Valentín").id("49").villageId("49").patron("San Valentín").startDate(LocalDate.of(2025, 2, 14)).endDate(LocalDate.of(2025, 2, 15)).build(),
                    FestivityEntity.builder().name("Fiesta Patronal Virgen de la Merced").id("50").villageId("50").patron("Virgen de la Merced").startDate(LocalDate.of(2025, 9, 24)).endDate(LocalDate.of(2025, 9, 25)).build()

            );
            festivityRepository.saveAll(festivities);
            System.out.println("Festivities loaded successfully");
        };
    }
}
