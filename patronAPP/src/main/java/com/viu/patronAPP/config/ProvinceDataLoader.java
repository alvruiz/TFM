package com.viu.patronAPP.config;

import com.viu.patronAPP.domain.model.Coords;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.ProvinceEntity;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.province.ProvinceMongoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ProvinceDataLoader {


    @Bean
    public CommandLineRunner loadProvinces(ProvinceMongoRepository provinceRepository) {
        return args -> {
            provinceRepository.deleteAll();
            List<ProvinceEntity> provinces = Arrays.asList(
                    ProvinceEntity.builder().id("1").name("A Coruña").coords(Coords.builder().latitude(43.3623).longitude(-8.4115).build()).imageUrl("https://www.turismo.gal/web/asturias/vista-panoramica-a-coruna.jpg").build(),
                    ProvinceEntity.builder().id("2").name("Albacete").coords(Coords.builder().latitude(38.9943).longitude(-1.8564).build()).imageUrl("https://sabersabor.es/wp-content/uploads/2021/07/Visita-guiada-a-Albacete.jpg").build(),
                    ProvinceEntity.builder().id("3").name("Alicante").coords(Coords.builder().latitude(38.3452).longitude(-0.4810).build()).imageUrl("https://www.barcelo.com/guia-turismo/wp-content/uploads/que-visitar-en-alicante.jpg").build(),
                    ProvinceEntity.builder().id("4").name("Almería").coords(Coords.builder().latitude(36.8340).longitude(-2.4637).build()).imageUrl("https://www.servigroup.com/assets/cache/uploads/gallery/proposals/excursiones/que-ver-almeria/1280x720/alcazaba-almeria-1701094733.jpg").build(),
                    ProvinceEntity.builder().id("5").name("Ávila").coords(Coords.builder().latitude(40.6566).longitude(-4.6818).build()).imageUrl("https://www.hola.com/horizon/landscape/ca3e997be14f-avila-murallas-panoramica-t.jpg").build(),
                    ProvinceEntity.builder().id("6").name("Badajoz").coords(Coords.builder().latitude(38.8794).longitude(-6.9706).build()).imageUrl("https://media.traveler.es/photos/61377022d7c7024f9175ecea/16:9/w_2000,h_1125,c_limit/131678.jpg").build(),
                    ProvinceEntity.builder().id("7").name("Barcelona").coords(Coords.builder().latitude(41.3851).longitude(2.1734).build()).imageUrl("https://www.barcelo.com/guia-turismo/wp-content/uploads/que-visitar-en-barcelona-1.jpg").build(),
                    ProvinceEntity.builder().id("8").name("Vizcaya").coords(Coords.builder().latitude(43.2630).longitude(-2.9349).build()).imageUrl("https://media.timeout.com/images/105672719/750/422/image.jpg").build(),
                    ProvinceEntity.builder().id("9").name("Burgos").coords(Coords.builder().latitude(42.3439).longitude(-3.6969).build()).imageUrl("https://www.espanafascinante.com/asset/thumbnail,1280,720,center,center/media/espanafascinante/images/2018/07/09/20180709103536242329.jpg").build(),
                    ProvinceEntity.builder().id("10").name("Cáceres").coords(Coords.builder().latitude(39.4752).longitude(-6.3722).build()).imageUrl("https://media.timeout.com/images/106012284/750/422/image.jpg").build(),
                    ProvinceEntity.builder().id("11").name("Cádiz").coords(Coords.builder().latitude(36.5271).longitude(-6.2886).build()).imageUrl("https://www.cadizturismo.com/storage/app/media/uploaded-files/p-cadiz_turismo.jpg").build(),
                    ProvinceEntity.builder().id("12").name("Castellón").coords(Coords.builder().latitude(39.9864).longitude(-0.0513).build()).imageUrl("https://www.ccsalera.com/wp-content/uploads/2022/11/monumentos-castellon-scaled.jpg").build(),
                    ProvinceEntity.builder().id("13").name("Ceuta").coords(Coords.builder().latitude(35.8893).longitude(-5.3167).build()).imageUrl("https://www.turismodeceuta.com/sites/default/files/ceuta-panoramica.jpg").build(),
                    ProvinceEntity.builder().id("14").name("Ciudad Real").coords(Coords.builder().latitude(38.9861).longitude(-3.9275).build()).imageUrl("https://abrasador.com/wp-content/uploads/2021/12/Plaza_Mayor_de_Ciudad_Reak-min-scaled.jpeg").build(),
                    ProvinceEntity.builder().id("15").name("Córdoba").coords(Coords.builder().latitude(37.8882).longitude(-4.7794).build()).imageUrl("https://multimedia.andalucia.org/media/C1DF41C3E6EF456A8B4A9B4845C32593/img/7B2A371526454055A71AC38C335E1834/16-20_Puente_Romano_y_Mezquita_de_Cordoba_Cordoba.jpg?responsive").build(),
                    ProvinceEntity.builder().id("16").name("Cuenca").coords(Coords.builder().latitude(40.0704).longitude(-2.1374).build()).imageUrl("https://repositorio.turismocastillalamancha.com/miscelanea/178474/36/2cbf/cuenca.jpg").build(),
                    ProvinceEntity.builder().id("17").name("Girona").coords(Coords.builder().latitude(41.9794).longitude(2.8214).build()).imageUrl("https://mesto-barcelona.cz/data/uploads/girona-opevneni.jpg").build(),
                    ProvinceEntity.builder().id("18").name("Granada").coords(Coords.builder().latitude(37.1773).longitude(-3.5986).build()).imageUrl("https://content.r9cdn.net/rimg/dimg/3b/c2/b4c4bfb9-city-27138-55689ae0.jpg?width=1366&height=768&xhint=1902&yhint=1090&crop=true").build(),
                    ProvinceEntity.builder().id("19").name("Guadalajara").coords(Coords.builder().latitude(40.6333).longitude(-3.1636).build()).imageUrl("https://www.guadalajara.es/images/guadalajara.jpg").build(),
                    ProvinceEntity.builder().id("20").name("Huelva").coords(Coords.builder().latitude(37.2595).longitude(-6.9485).build()).imageUrl("https://www.visitahuelva.es/sites/default/files/elementos/huelva-panoramica.jpg").build(),
                    ProvinceEntity.builder().id("21").name("Huesca").coords(Coords.builder().latitude(42.1407).longitude(-0.4085).build()).imageUrl("https://www.huesca.es/sites/default/files/editores/descubrehuesca.jpg").build(),
                    ProvinceEntity.builder().id("22").name("Jaén").coords(Coords.builder().latitude(37.7647).longitude(-3.7904).build()).imageUrl("https://www.diccionarioj.com/sites/default/files/images/Jaen.jpg").build(),
                    ProvinceEntity.builder().id("23").name("Las Palmas").coords(Coords.builder().latitude(28.0994).longitude(-15.4134).build()).imageUrl("https://www.laspalmasgc.es/uploads/migrated_images/gran-canaria-playa.jpg").build(),
                    ProvinceEntity.builder().id("24").name("León").coords(Coords.builder().latitude(42.5977).longitude(-5.5671).build()).imageUrl("https://www.leon.es/sites/default/files/styles/big/public/leon.jpg").build(),
                    ProvinceEntity.builder().id("25").name("Lleida").coords(Coords.builder().latitude(41.6161).longitude(0.6206).build()).imageUrl("https://www.diccionarios.com/imagenes/ciudades/lleida.jpg").build(),
                    ProvinceEntity.builder().id("26").name("La Rioja").coords(Coords.builder().latitude(42.4661).longitude(-2.4457).build()).imageUrl("https://www.larioja.org/webcomercial/el-reyno-de-navarra.jpg").build(),
                    ProvinceEntity.builder().id("27").name("Lugo").coords(Coords.builder().latitude(43.0032).longitude(-7.5582).build()).imageUrl("https://www.lugo.es/images/lugo_centro.jpg").build(),
                    ProvinceEntity.builder().id("28").name("Madrid").coords(Coords.builder().latitude(40.4168).longitude(-3.7038).build()).imageUrl("https://media.timeout.com/images/105651893/750/422/image.jpg").build(),
                    ProvinceEntity.builder().id("29").name("Malaga").coords(Coords.builder().latitude(36.7203).longitude(-4.4199).build()).imageUrl("https://www.malagaturismo.com/images/1.jpg").build(),
                    ProvinceEntity.builder().id("30").name("Melilla").coords(Coords.builder().latitude(35.2920).longitude(-2.9381).build()).imageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Vista_desde_el_cargadero_del_muelle_-_panoramio.jpg/1280px-Vista_desde_el_cargadero_del_muelle_-_panoramio.jpg").build(),
                    ProvinceEntity.builder().id("31").name("Murcia").coords(Coords.builder().latitude(37.9844).longitude(-1.1285).build()).imageUrl("https://www.murciadiario.com/images/murcia.jpg").build(),
                    ProvinceEntity.builder().id("32").name("Ourense").coords(Coords.builder().latitude(42.3365).longitude(-7.8637).build()).imageUrl("https://www.ourense.es/images/ourense-panorama.jpg").build(),
                    ProvinceEntity.builder().id("33").name("Asturias").coords(Coords.builder().latitude(43.3623).longitude(-5.8494).build()).imageUrl("https://oviedo.es/sites/default/files/styles/large/public/oviedo.jpg").build(),
                    ProvinceEntity.builder().id("34").name("Palencia").coords(Coords.builder().latitude(42.0078).longitude(-4.5346).build()).imageUrl("https://www.palencia.es/palencia.jpg").build(),
                    ProvinceEntity.builder().id("35").name("Islas Baleares").coords(Coords.builder().latitude(39.5711).longitude(2.6518).build()).imageUrl("https://www.palma-tourism.com/pa/galeria/images/palma.jpg").build(),
                    ProvinceEntity.builder().id("36").name("Navarra").coords(Coords.builder().latitude(42.8141).longitude(-1.6452).build()).imageUrl("https://www.pamplona.es/images/pamplona.jpg").build(),
                    ProvinceEntity.builder().id("37").name("Pontevedra").coords(Coords.builder().latitude(42.4338).longitude(-8.6480).build()).imageUrl("https://www.pontevedra.eu/panoramica.jpg").build(),
                    ProvinceEntity.builder().id("38").name("Salamanca").coords(Coords.builder().latitude(40.9674).longitude(-5.6654).build()).imageUrl("https://www.salamanca.com/imagenes/salamanca.jpg").build(),
                    ProvinceEntity.builder().id("39").name("Gipuzkoa").coords(Coords.builder().latitude(43.3172).longitude(-1.9819).build()).imageUrl("https://www.sansebastian.travel/imagenes/sansebastian.jpg").build(),
                    ProvinceEntity.builder().id("40").name("Santa Cruz de Tenerife").coords(Coords.builder().latitude(28.4629).longitude(-16.2472).build()).imageUrl("https://www.santacruzdetenerife.es/images/santa-cruz.jpg").build(),
                    ProvinceEntity.builder().id("41").name("Cantabria").coords(Coords.builder().latitude(43.1828).longitude(-3.9878).build()).imageUrl("https://www.santandercreativa.com/images/wm.jpg").build(),
                    ProvinceEntity.builder().id("42").name("Segovia").coords(Coords.builder().latitude(40.9481).longitude(-4.1187).build()).imageUrl("https://www.segoviaturismo.es/images/segovia.jpg").build(),
                    ProvinceEntity.builder().id("43").name("Sevilla").coords(Coords.builder().latitude(37.3886).longitude(-5.9823).build()).imageUrl("https://www.visitasevilla.es/sites/default/files/styles/max_600x600/public/2018-09/sevilla-catedral.jpg").build(),
                    ProvinceEntity.builder().id("44").name("Soria").coords(Coords.builder().latitude(41.7644).longitude(-2.4633).build()).imageUrl("https://www.soria.es/images/soria-panoramica.jpg").build(),
                    ProvinceEntity.builder().id("45").name("Tarragona").coords(Coords.builder().latitude(41.1187).longitude(1.2445).build()).imageUrl("https://www.tarragonaturisme.cat/images/tarragona-panoramica.jpg").build(),
                    ProvinceEntity.builder().id("46").name("Teruel").coords(Coords.builder().latitude(40.3430).longitude(-1.1062).build()).imageUrl("https://www.teruel.es/images/teruel-panoramica.jpg").build(),
                    ProvinceEntity.builder().id("47").name("Toledo").coords(Coords.builder().latitude(39.8628).longitude(-4.0273).build()).imageUrl("https://www.toledo-turismo.com/images/toledo-panoramica.jpg").build(),
                    ProvinceEntity.builder().id("48").name("Valencia").coords(Coords.builder().latitude(39.4699).longitude(-0.3763).build()).imageUrl("https://www.valenciabonita.es/wp-content/uploads/2021/11/valencia-panoramica.jpg").build(),
                    ProvinceEntity.builder().id("49").name("Valladolid").coords(Coords.builder().latitude(41.6523).longitude(-4.7282).build()).imageUrl("https://www.valladolidturismo.com/images/valladolid.jpg").build(),
                    ProvinceEntity.builder().id("50").name("Zamora").coords(Coords.builder().latitude(41.5035).longitude(-5.7448).build()).imageUrl("https://www.zamoradigital.net/images/zamora-panoramica.jpg").build(),
                    ProvinceEntity.builder().id("51").name("Zaragoza").coords(Coords.builder().latitude(41.6488).longitude(-0.8891).build()).imageUrl("https://www.zaragoza.es/web/turismo/images/zaragoza.jpg").build(),
                    ProvinceEntity.builder().id("52").name("Álava").coords(Coords.builder().latitude(42.8518).longitude(-2.6725).build()).imageUrl("https://cdn.getyourguide.com/img/location/5b6171e2ee418.jpeg").build()
            );
            provinceRepository.saveAll(provinces);

            System.out.println("Provinces loaded successfully");
        };
    }
}
